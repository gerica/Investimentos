$(document).ready(function() { // document and jquery ready

	var $alerts = $('div[class^="alert"]');
	$alerts.hide();

	$("#idComboEmpresa").on("change", function(e) {
		var dropValue = $(this).val();

		$.ajax({
			type : "GET",
			url : '/Investimentos/site/montarComboPapel/' + dropValue,
			datatype : 'JSON',
			success : function(response) {
				var options = $("#idComboPapel");
				options.empty();
				options.append($("<option />").val("0").text("--- Selecione ---"));
				// don't forget error handling!
				$.each(response.result, function() {
					console.log(this);
					options.append($("<option />").val(this.id).text(this.valor));
				});
			},
			error : function() {
				alert('something bad happened');
			}
		});
	});

	$('#formPage').keydown(function(e) {
		var key = e.which;
		if (key == 13) {
			e.preventDefault();
			this.setAttribute('action', '/Investimentos/site/gravarOperacao');
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
	var cotacao = new Object();
	var papel = new Object();
	var empresa = new Object();
	cotacao.papel = papel;
	cotacao.empresa = empresa;

	$.each($('input, select ,textarea', $(frm)), function(k) {
		var input = $(this);
		if (input.attr("name") == "papel.id") {
			papel.id = input.val();
		} else if (input.attr("name") == "empresa.id") {
			empresa.id = input.val();
		} else {
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

					cotacao[tempName] = parseFloat(tempMoeda);
				} else {
					cotacao[tempName] = input.val();
				}
			}
		}
	});

	// console.log(JSON.stringify(cotacao));
	$.ajax({
		contentType : 'application/json; charset=utf-8',
		dataType : "json",
		type : $(frm).attr('method'),
		url : $(frm).attr('action'),
		data : JSON.stringify(cotacao),
		success : function(response) {
			ajaxResponseSucess(frm, response);
			closeModelProgressBar();
		},
		error : function(e) {
			ajaxResponseFail(e);
		}
	});
}

function ajaxResponseSucess(frm, response) {
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
		$(frm).get(0).reset();
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
}

function tratarReturnList(result) {
	var array = [];
	array.push([
			"Ações", "Data", "Empresa", "Papel", "Operação", "Preço Unitário", "Stop Loss", "Entrada"
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
	thead.append($("<th/>").text(data[0][5]));
	thead.append($("<th/>").text(data[0][6]));
	thead.append($("<th/>").text(data[0][7]));

	table.append(thead);
	$.each(data, function(rowIndex, r) {
		var row = $("<tr/>");
		$.each(r, function(colIndex, c) {
			if (rowIndex != 0) {

				var td = $("<td/>");
				// console.log(c);
				// console.log(c.avaliacaoEntradaRealizada);
				if (c.avaliacaoEntradaRealizada) {
					var a1 = $('<a href="#" onclick="linkInProccess(\'operacaoAvaliacaoEntrada/' + c.id
							+ '\');" title="Avaliar o preço para na entrada"/>');
					var img1 = $('<span class="glyphicon glyphicon-share"></span>');
					a1.append(img1);
					td.append(a1);
					td.append(" ");
				}

				var a2 = $('<a href="#" onclick="showConfirmRemove(\'apagarOoperacaoEntrada/' + c.id + '\');" title="Apagar"/>');
				var img2 = $('<p class="glyphicon glyphicon-remove"></p>');
				a2.append(img2);
				td.append(a2);
				td.append(" ");

				var a3 = $('<a href="#" onclick="linkIn(\'visualizarOperacao/' + c.id + '\');" title="Visualizar operação"/>');
				var img3 = $('<span class="glyphicon glyphicon-unchecked"> </span>');
				a3.append(img3);
				td.append(a3);
				td.append(" ");

				var a4 = $('<a href="#" onclick="linkIn(\'preOperacaoSaida/' + c.id + '\');" title="Operação Saída"/>');
				var img4 = $('<p class="fa fa-external-link"></p>');
				a4.append(img4);
				td.append(a4);
				td.append(" ");

				// console.log(r);
				row.append(td);
				row.append($("<td/>").text(c.data));
				row.append($("<td/>").text(c.empresa));
				row.append($("<td/>").text(c.papel));
				row.append($("<td/>").text(c.tipoOperacao));
				row.append($("<td/>").text('R$ ' + c.precoUnitario));
				row.append($("<td/>").text('R$ ' + c.stopLoss));
				row.append($("<td/>").text(c.porcentagemEntrada));
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
function submitPostToAotherPage(frm) {

	$.ajax({
		contentType : 'application/json; charset=utf-8',
		dataType : "json",
		type : $(frm).attr('method'),
		url : $(frm).attr('action'),
		success : function(response) {
			closeModelProgressBar();
			toRenderPageAfterAjax(response);
		},
		error : function(e) {
			ajaxResponseFail(e);
		}
	});
}
// function selectTab(href) {
// var url = href
// addModelProgressBar();
// $.get(url, function(data, status) {
// closeModelProgressBar();
// toRenderPageAfterAjaxTab(data);
// }).error(function(data, status, headers, config) {
// closeModelProgressBar();
// alert("Ocorreu algum erro interno!!!");
// })
// }

// function toRenderPageAfterAjaxTab(data) {
// var divConteudo = $("#tabConteudo");
// divConteudo.empty();
// divConteudo.append(data);
// }

function toRenderPageAfterAjaxTab(response) {
	if (response.result != undefined && response.result.length > 0) {

		var result = response.result;
		var array = [];
		array.push([
				"Data Entrada", "Empresa", "Papel", "Operação", "Preço Unitário", "Quantidade", "Total", "Data Saida", "Preço Unitário",
				"Quantidade", "Total"
		]);
		for (var i = 0; i < result.length; i++) {
			array.push([
				result[i]
			]);
		}
		var $container = $("#tabConteudo");
		$container.empty();
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
	thead.append($("<th/>").text(data[0][6]));
	thead.append($("<th/>").text(data[0][7]));
	thead.append($("<th/>").text(data[0][8]));
	thead.append($("<th/>").text(data[0][9]));
	thead.append($("<th/>").text(data[0][10]));

	table.append(thead);
	$.each(data, function(rowIndex, r) {
		var row = $("<tr/>");
		$.each(r, function(colIndex, c) {
			if (rowIndex != 0) {

				row.append($("<td/>").text(c.dataEntrada));
				row.append($("<td/>").text(c.nomeEmpresa));
				row.append($("<td/>").text(c.nomePapel));
				row.append($("<td/>").text(c.tipoOperacao));
				row.append($("<td/>").text('R$ ' + c.precoUnitarioEntrada));
				row.append($("<td/>").text(c.quantidadeEntrada));
				row.append($("<td/>").text('R$ ' + c.totalEntrada));
				console.log(c.saidas);
				var tdDataSaida = $("<td/>");
				var tdPrecoSaida = $("<td/>");
				var tdQtdSaida = $("<td/>");
				var tdTotal = $("<td/>");
				$.each(c.saidas, function(saidaIndex, s) {
					tdDataSaida.append(s.dataSaida);
					tdDataSaida.append("<br>");
					tdPrecoSaida.append('R$ ' + s.precoUnitarioSaida);
					tdPrecoSaida.append("<br>");
					tdQtdSaida.append(s.quantidadeSaida);
					tdQtdSaida.append("<br>");
					tdTotal.append(s.totalSaida);
					tdTotal.append("<br>");
				});
				row.append(tdDataSaida);
				row.append(tdPrecoSaida);
				row.append(tdQtdSaida);
				row.append(tdTotal);

				table.append(row);

			}
		});
	});
	container.append(table);
}