$(document).ready(function() { // document and jquery ready

	var $alerts = $('div[class^="alert"]');
	$alerts.hide();

	$('#formPage').keydown(function(e) {
		var key = e.which;
		if (key == 13) {
			e.preventDefault();
			this.setAttribute('action', '/Investimentos/site/gravarOperacaoSaida');
			submitPost(this);
		}
	});
});

function submitPost(frm) {
	var operacao = new Object();
	var operacaoEntrada = new Object();
	operacao.operacaoEntrada = operacaoEntrada;

	$.each($('input, select ,textarea', $(frm)), function(k) {
		var input = $(this);
		if (input.attr("name") == "operacaoEntrada.id") {
			operacaoEntrada.id = input.val();
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
					operacao[tempName] = parseFloat(tempMoeda);
				} else {
					operacao[tempName] = input.val();
				}
			}
		}
	});

//	console.log(JSON.stringify(operacao));
	$.ajax({
		contentType : 'application/json; charset=utf-8',
		dataType : "json",
		type : $(frm).attr('method'),
		url : $(frm).attr('action'),
		data : JSON.stringify(operacao),
		success : function(response) {
			ajaxResponseSucess(frm, response);
			closeModelProgressBar();
		},
		error : function(e) {
			ajaxResponseFail(e);
		}
	});
}

function toRenderPageAfterAjax(data) {
	var divConteudo = $("#page-wrapper");
	divConteudo.empty();
	divConteudo.append(data);
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
		var url = '/Investimentos/site/operacaoView';
		$.get(url, function(data, status) {
			toRenderPageAfterAjax(data);
			closeModelProgressBar();
		}).error(function(data, status, headers, config) {
			alert("Ocorreu algum erro interno!!!");
		})

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
		closeModelProgressBar();
	}
}
