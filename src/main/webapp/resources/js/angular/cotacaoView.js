$(document).ready(function() { // document and jquery ready
	var $alerts = $('div[class^="alert"]');
	$alerts.hide();

	$('#formPage').keydown(function(e) {
		var key = e.which;
		if (key == 13) {
			e.preventDefault();
			this.setAttribute('action', '/Investimentos/site/gravarCotacao');
			submitPost(this);
		}
	});

});

function submitPost(frm) {
	var cotacao = new Object();
	var papel = new Object();
	cotacao.papel = papel;

	$.each($('input, select ,textarea', $(frm)), function(k) {
		var input = $(this);
		if (input.attr("name") == "papel.id") {
			papel.id = input.val();
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

	console.log(JSON.stringify(cotacao));
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
			"Data", "Abertura", "Fechamento", "Maxima", "Minima"
	]);
	for (var i = 0; i < result.length; i++) {
		array.push([
				result[i].data, result[i].abertura, result[i].fechamento, result[i].maxima, result[i].minima
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
				row.append($("<td/>").text(c));
			}

		});

		if (rowIndex != 0) {
			table.append(row);
		}

	});
	container.append(table);
}

// using FormData() object
function uploadFormData(bt) {
	var frm = $(bt).closest("form").get()[0];
	$(frm).get(0).setAttribute('action', $(bt).attr('name'));

	var oMyForm = new FormData();
	oMyForm.append("file", file.files[0]);

	var other_data = $(frm).serializeArray();
	$.each(other_data, function(key, input) {
		oMyForm.append(input.name, input.value);
	});
	addModelProgressBar();
	$.ajax({
		type : $(frm).attr('method'),
		url : $(frm).attr('action'),
		data : oMyForm,
		dataType : 'text',
		processData : false,
		contentType : false,
		success : function(response) {
			ajaxResponseSucess(frm, JSON.parse(response));
			closeModelProgressBar();
		},
		error : function(e) {
			ajaxResponseFail(e);
		}
	});
}