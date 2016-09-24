$(document).ready(function() { // document and jquery ready

	var $alerts = $('div[class^="alert"]');
	$alerts.hide();

	$('#formPage').keydown(function(e) {
		var key = e.which;
		if (key == 13) {
			e.preventDefault();
			this.setAttribute('action', '/Investimentos/site/gravarRendaFixa');
			submitPost(this);
		}
	});
});

function linkInProccess(href) {
	var url = href
	$.get(url, function(response, status) {
		var $alerts = $('div[class^="alert"]');
		$alerts.hide();
		$alerts.empty();

		// we have the response
		if (response.status == "sucesso") {
			var $alertSuccess = $('div[class^="alert alert-success"]');
			$alertSuccess.append('<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>');
			successInfo = "1. " + response.message + "";
			$alertSuccess.append(successInfo);
			$alertSuccess.show();

			if (response.result != undefined && response.result.length > 0) {
				tratarReturnList(response.result);
			}

		} else {
			var $alertFail = $('div[class^="alert alert-danger"]');
			$alerts.hide();
			$alerts.empty();
			errorInfo = "";
			if (response.message instanceof Array) {
				for (i = 0; i < response.message.length; i++) {
					errorInfo += "<br>" + (i + 1) + ". " + response.message[i].defaultMessage;
				}
			} else {
				errorInfo += "<br>1. " + response.message;
			}
			$alertFail.append('<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>');
			$alertFail.append("Por favor corrigir o(s) seguintes erros: " + errorInfo);
			$alertFail.show();
		}

	}).error(function(data, status, headers, config) {
		alert("Ocorreu algum erro interno!!!");
	})
}

function submitPost(frm) {
	var rendaFixa = new Object();

	$.each($('input, select ,textarea', $(frm)), function(k) {
		var input = $(this);

		var tempName = input.attr("name");
		if (tempName != "undefined" && tempName != "") {
			if (input.hasClass("dinheiro")) {
				var tempMoeda = input.val().replace('R$ ', '');
				// .replace(/\,/g, '') para remover todas as virgula
				if (tempMoeda.length < 8) {
					tempMoeda = tempMoeda.replace(',', '.')
				} else {
					tempMoeda = tempMoeda.replace('.', '').replace(',', '.')
				}
				// console.log(tempMoeda);

				rendaFixa[tempName] = parseFloat(tempMoeda);
			} else if (input.hasClass("porcentagem")) {
				var tempPercent = input.val().replace('%', '');
				// .replace(/\,/g, '') para remover todas as virgula
				if (tempPercent.length < 8) {
					tempPercent = tempPercent.replace(',', '.')
				} else {
					tempPercent = tempPercent.replace('.', '').replace(',', '.')
				}
				console.log(tempPercent);

				rendaFixa[tempName] = parseFloat(tempPercent) / 100;
				console.log(rendaFixa[tempName]);
			} else {
				rendaFixa[tempName] = input.val();
			}
		}

	});

	// console.log(JSON.stringify(cotacao));
	$.ajax({
		contentType : 'application/json; charset=utf-8',
		dataType : "json",
		type : $(frm).attr('method'),
		url : $(frm).attr('action'),
		data : JSON.stringify(rendaFixa),
		success : function(response) {
			ajaxResponseSucess(frm, response);
			closeModelProgressBar();
		},
		error : function(e) {
			ajaxResponseFail(e);
		}
	});
}

function tratarReturnList(result) {
	var array = [];
	array.push([
			"Ações", "Data de Entrada", "Investimento", "Banco", "Taxa"

	]);
	for (var i = 0; i < result.length; i++) {
		array.push([
			result[i]
		]);
	}
	var $container = $('.table-responsive');
	$container.empty();
	makeTable($container, array);
}

function makeTable(container, data) {
	var table = $("<table/>").addClass('table table-striped table-bordered table-hover');
	var thead = $("<thead/>");
	thead.append($("<th/>").text(data[0][0]));
	thead.append($("<th/>").text(data[0][1]));
	thead.append($("<th/>").text(data[0][2]));
	thead.append($("<th/>").text(data[0][3]));
	thead.append($("<th/>").text(data[0][4]));

	table.append(thead);
	$.each(data, function(rowIndex, r) {
		var row = $("<tr/>");
		$.each(r, function(colIndex, c) {
			if (rowIndex != 0) {

				var td = $("<td/>");
				// console.log(c);
				// console.log(c.avaliacaoEntradaRealizada);

				var a1 = $('<a href="#" onclick="linkIn(\'preAlterar/' + c.id + '\');" title="Alterar"/>');
				var img1 = $('<p class="fa fa-pencil"></p>');
				a1.append(img1);
				td.append(a1);
				td.append(" ");

				var a2 = $('<a href="#" onclick="showConfirmRemove(\'apagarRendaFixa/' + c.id + '\');" title="Apagar"/>');
				var img2 = $('<p class="glyphicon glyphicon-remove"></p>');
				a2.append(img2);
				td.append(a2);
				td.append(" ");

				var a3 = $('<a href="#" onclick="linkIn(\'preRendaFixaSaida/' + c.id + '\');" title="Sair da Renda Fixa"/>');
				var img3 = $('<p class="fa fa-sign-out"></p>');
				a3.append(img3);
				td.append(a3);
				td.append(" ");

				// console.log(r);
				row.append(td);
				row.append($("<td/>").text(c.dataEntrada));
				row.append($("<td/>").text('R$ ' + c.investimento));
				row.append($("<td/>").text(c.nomeBanco));
				row.append($("<td/>").text(c.taxa));
				table.append(row);
			}
		});
	});
	container.append(table);
}

function doAjaxPostToOtherPage(bt) {
	var frm = $(bt).closest("form").get()[0];
	$(frm).get(0).setAttribute('action', $(bt).attr('name'));
	addModelProgressBar();
	submitPostToAotherPage($(frm));
}

function toRenderPageAfterAjaxTab(response) {
	if (response.result != undefined && response.result.length > 0) {
		var result = response.result;
		var $container = $("#tabConteudo");
		$container.empty();
		var array = [];
		array.push([
				"Data de Entrada", "Investimento", "Banco", "Taxa", "Data Saída", "Resgate"
		]);
		for (var i = 0; i < result.length; i++) {
			array.push([
				result[i]
			]);
		}
		console.log(array);
		makeTableHistorico($container, array);
	}
}

function makeTableHistorico(container, data) {
	var table = $("<table/>").addClass('table table-striped table-bordered table-hover');
	var thead = $("<thead/>");
	thead.append($("<th/>").text(data[0][0]));
	thead.append($("<th/>").text(data[0][1]));
	thead.append($("<th/>").text(data[0][2]));
	thead.append($("<th/>").text(data[0][3]));
	thead.append($("<th/>").text(data[0][4]));
	thead.append($("<th/>").text(data[0][5]));

	table.append(thead);
	$.each(data, function(rowIndex, r) {
		var row = $("<tr/>");
		$.each(r, function(colIndex, c) {
			if (rowIndex != 0) {
				var td = $("<td/>");
				// console.log(r);
				row.append($("<td/>").text(c.dataEntrada));
				row.append($("<td/>").text('R$ ' + c.investimento));
				row.append($("<td/>").text(c.nomeBanco));
				row.append($("<td/>").text(c.taxa));
				row.append($("<td/>").text(c.dataSaida));
				row.append($("<td/>").text('R$ ' + c.resgate));
				table.append(row);
			}
		});
	});
	container.append(table);
}