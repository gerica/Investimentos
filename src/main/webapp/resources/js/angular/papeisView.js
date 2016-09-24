$(document).ready(function() {
	var $alerts = $('div[class^="alert"]');
	$alerts.hide();

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

	$.ajax({
		contentType : 'application/json; charset=utf-8',
		dataType : "json",
		type : $(frm).attr('method'),
		url : $(frm).attr('action'),
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

					var a2 = $('<a href="#" onclick="linkIn(\'/Investimentos/site/alterarPapel/' + c + '\');" />');
					var img2 = $('<img src="/Investimentos/resources/img/application--plus.png" TITLE="Alter" ALT="Alterar"/>');
					a2.append(img2);
					td.append(a2);
					td.append(" ");
					var a3 = $('<a href="#" onclick="linkIn(\'/Investimentos/site/cotacaoView/' + c + '\');" />');
					var img3 = $('<img src="/Investimentos/resources/img/paper-bag--arrow.png" TITLE="Cotação" ALT="Cotação"/>');
					a3.append(img3);
					td.append(a3);
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