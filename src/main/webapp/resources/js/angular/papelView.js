$(document).ready(function() { // document and jquery ready
	var $alerts = $('div[class^="alert"]');
	$alerts.hide();

	$('#formPage').keydown(function(e) {
		var key = e.which;
		if (key == 13) {
			e.preventDefault();
			this.setAttribute('action', '/Investimentos/site/gravarPapel');
			submitPost(this);
		}
	});
	
	$('a[id^="linkAjaxGet_"]').click(function(event) {
		event.preventDefault();
		addModelProgressBar();
		var url = $(this).attr('href');
		$.get(url, function(data, status) {
			closeModelProgressBar();
			toRenderPageAfterAjax(data);
		}).error(function(data, status, headers, config) {
			alert("Ocorreu algum erro interno!!!");
		})
	});

});

function submitPost(frm) {
	var papel = new Object();
	var empresa = new Object();
	papel.empresa = empresa;

	$.each($('input, select ,textarea', $(frm)), function(k) {
		var input = $(this);
		if (input.attr("name") == "empresa.id") {
			empresa.id = input.val();
		} else {
			var tempName = input.attr("name");
			if (tempName != "undefined" && tempName != "") {
				papel[tempName] = input.val();
			}
		}
	});

	console.log(JSON.stringify(papel));
	$.ajax({
		contentType : 'application/json; charset=utf-8',
		type : $(frm).attr('method'),
		url : $(frm).attr('action'),
		data : JSON.stringify(papel),
		success : function(response) {
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
				$('#nome').val('');
				if (response.result != undefined && response.result.length > 0) {
					tratarReturnList(response.result);
				}

			} else {
				var $alertFail = $('div[class^="alert alert-danger"]');

				errorInfo = "";
				for (i = 0; i < response.message.length; i++) {
					errorInfo += "<br>" + (i + 1) + ". " + response.message[i].defaultMessage;
				}
				$alertFail.append('<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>');
				$alertFail.append("Por favor corrigir o(s) seguintes erros: " + errorInfo);
				$alertFail.show();
			}
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
			"Acoes", "Empresas"
	]);
	for (var i = 0; i < result.length; i++) {
		array.push([
				result[i].id, result[i].nome
		]);
	}
	var $container = $('.table-responsive');
	$container.empty();
	makeTable($container, array);
}

function makeTable(container, data) {
	var table = $("<table/>").addClass('table table-striped table-bordered table-hover');
	var thead = $("<thead/>");
	var th1 = $("<th/>");
	th1.attr('width', '10%');
	th1.text(data[0][0]);
	thead.append(th1);

	var th2 = $("<th/>");
	th2.text(data[0][1]);
	thead.append(th2);

	table.append(thead);
	$.each(data, function(rowIndex, r) {
		var row = $("<tr/>");
		$.each(r, function(colIndex, c) {
			if (rowIndex != 0) {
				if (colIndex == 0) {
					var td = $("<td/>");

					var a3 = $('<a href="#" onclick="linkIn(\'/Investimentos/site/alterarPapel/' + c + '\');" title="Alterar"/>');
					var img3 = $('<p class="fa fa-pencil"></p>');
					a3.append(img3);
					td.append(a3);
					td.append(" ");
					
					var a2 = $('<a href="#" onclick="showConfirmRemove(\'/Investimentos/site/excluirPapel/' + c + '\');" title="Excluir"/>');
					var img2 = $('<p class="glyphicon glyphicon-remove"></p>');
					a2.append(img2);
					td.append(a2);
					td.append(" ");
					
					var a = $('<a href="#" onclick="linkIn(\'/Investimentos/site/cotacaoView/' + c + '\');" title="Visualizar Cotações"/>');
					var img = $('<p class="fa fa-external-link"></p>');
					a.append(img);
					td.append(a);
					td.append(" ");
					

					row.append(td);
				} else {
					row.append($("<td/>").text(c));
				}
			}
		});
		if (rowIndex != 0) {
			table.append(row);
		}
	});
	container.append(table);
}