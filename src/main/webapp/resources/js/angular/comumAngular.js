$(document).ready(function() {

	$('a[id^="linkAjaxGet_"]').click(function(event) {
		event.preventDefault();
		addModelProgressBar();

		// $('#carregando').css('display', 'inline');
		var url = $(this).attr('href');
		$.get(url, function(data, status) {
			closeModelProgressBar();
			toRenderPageAfterAjax(data);
		}).error(function(data, status, headers, config) {
			alert("Ocorreu algum erro interno!!!");
		})
	});

	// Mascara para dinheriro
	$("input.dinheiro").maskMoney({
		prefix : 'R$ ',
		allowNegative : true,
		thousands : '.',
		decimal : ',',
		affixesStay : false
	});
	// Mascara para porcentagem
	$("input.porcentagem").maskMoney({
		suffix : '%',
		allowNegative : false,
		thousands : '.',
		decimal : ',',
		affixesStay : false
	});

});

function toRenderPageAfterAjax(data) {
	var divConteudo = $("#page-wrapper");
	divConteudo.empty();
	divConteudo.append(data);
}

function addModelProgressBar() {
	$('#carregando').modal('show')
	var $bar = $('.progress-bar-success');
	$('.progress').addClass('active');
	$bar.width(0);

	var progress = setInterval(function() {

		if ($bar.width() >= 600) {
			clearInterval(progress);
			$('.progress').removeClass('active');
		} else {
			$bar.width($bar.width() + 60);
		}
		// $bar.text($bar.width());
	}, 1000);
}

function closeModelProgressBar() {
	$('#carregando').modal('hide');
}

function ajaxResponseSucess(frm, response) {
	var $alerts = $('div[class^="alert"]');
	$alerts.hide();
	$alerts.empty();

	// we have the response
	if (response.status == "sucesso") {
		var $alertSuccess = $('div[class^="alert alert-success"]');
		$alertSuccess.append('<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>');
		var successInfo = "1. " + response.message + "";
		$alertSuccess.append(successInfo);
		$alertSuccess.show();
		// addModelMensagem(successInfo, $alertSuccess);

		if ($(frm).length > 0) {
			$(frm).get(0).reset();
			limparForm(frm);
		}
		if (response.result != undefined) {
			// TODO verificar, pois pode gerar erro
			// && response.result.length > 0
			tratarReturnList(response.result);
		}

	} else {
		var $alertFail = $('div[class^="alert alert-danger"]');
		$alerts.hide();
		$alerts.empty();
		var errorInfo = "";
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
	closeModelProgressBar();
}

function ajaxResponseSucessForm(response) {
	var $alerts = $('div[class^="alert"]');
	$alerts.hide();
	$alerts.empty();

	// we have the response
	if (response.status == "sucesso") {
		var $alertSuccess = $('div[class^="alert alert-success"]');
		$alertSuccess.append('<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>');
		var successInfo = "1. " + response.message + "";
		$alertSuccess.append(successInfo);
		$alertSuccess.show();
		tratarForm(response);

	} else {
		var $alertFail = $('div[class^="alert alert-danger"]');
		$alerts.hide();
		$alerts.empty();
		var errorInfo = "";
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
	closeModelProgressBar();
}

function limparForm(frm) {
	$.each($('input, select ,textarea', $(frm)), function(k) {
		var input = $(this);
		var tempName = input.attr("name");
		if (tempName != "undefined" && tempName != "") {
			input.val('');
		}
	});

}

function ajaxResponseFail(e) {
	var $alerts = $('div[class^="alert"]');
	$alerts.hide();

	var $alertFail = $('div[class^="alert alert-danger"]');
	$alertFail.append('<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>');
	$alertFail.append("Erro na execução: " + e.statusText);
	$alertFail.show();
	closeModelProgressBar();
}

function doAjaxPost(bt) {
	var frm = $(bt).closest("form").get()[0];
	$(frm).get(0).setAttribute('action', $(bt).attr('name'));
	addModelProgressBar();
	submitPost($(frm));
}

function linkIn(href) {
	var url = href
	addModelProgressBar()
	$.get(url, function(data, status) {
		closeModelProgressBar();
		toRenderPageAfterAjax(data);
	}).error(function(data, status, headers, config) {
		alert("Ocorreu algum erro interno!!!");
	})
}

function doAjaxPostWithoutPage(url) {
	addModelProgressBar();
	$.ajax({
		contentType : 'application/json; charset=utf-8',
		dataType : "json",
		type : 'post',
		url : url,
		success : function(response) {
			var $alerts = $('div[class^="alert"]');
			$alerts.hide();
			$alerts.empty();

			// we have the response
			if (response.status == "sucesso") {
				var $alertSuccess = $('div[class^="alert alert-success"]');
				var successInfo = "1. " + response.message + "";
				addModelMensagem(successInfo, $alertSuccess);

			} else {
				var $alertFail = $('div[class^="alert alert-danger"]');
				$alerts.hide();
				$alerts.empty();
				var errorInfo = "";
				if (response.message instanceof Array) {
					for (i = 0; i < response.message.length; i++) {
						errorInfo += "<br>" + (i + 1) + ". " + response.message[i].defaultMessage;
					}
				} else {
					errorInfo += "<br>1. " + response.message;
				}
				addModelMensagem(errorInfo, $alertFail);
			}

			closeModelProgressBar();
		},
		error : function(e) {
			ajaxResponseFail(e);
		}
	});
}

function addModelMensagem(message, divAlert) {
	$('#modalMensagem').modal('show');
	divAlert.append(message);
	divAlert.show();
}

function doAjaxGetWithoutPage(url) {
	// addModelProgressBar();
	$.ajax({
		contentType : 'application/json; charset=utf-8',
		dataType : "json",
		type : 'get',
		url : url,
		success : function(response) {
			// we have the response
			if (response.status == "sucesso") {
				addModelConteudo(response.result);
			} else {
				var $alertFail = $('div[class^="alert alert-danger"]');
				$alerts.hide();
				$alerts.empty();
				var errorInfo = "";
				if (response.message instanceof Array) {
					for (i = 0; i < response.message.length; i++) {
						errorInfo += "<br>" + (i + 1) + ". " + response.message[i].defaultMessage;
					}
				} else {
					errorInfo += "<br>1. " + response.message;
				}
				addModelMensagem(errorInfo, $alertFail);
			}

			// closeModelProgressBar();
		},
		error : function(e) {
			ajaxResponseFail(e);
		}
	});
}

function addModelConteudo(result) {
	var nota = result[0];
	$('#modalMostrarConteudo').modal('show');
	var $panelHeading = $('#panel-heading');
	$panelHeading.empty();
	$panelHeading.append(nota.titulo + ' - ' + nota.sumario + ' - ' + nota.data);
	var $panelBody = $('#panel-body');
	$panelBody.empty();
	$panelBody.append(nota.descricao);
}

function showConfirmRemove(url) {
	$('#modalExcluirRegistro').modal('show');
	var $confirm = $('#excluirRegistro');
	$confirm.attr("name", url);
}

function closeConfirmRemove() {
	$('#modalExcluirRegistro').modal('hide');
}

function doAjaxGet(bt) {
	closeConfirmRemove();
	addModelProgressBar();
	var url = $(bt).attr('name');
	$.ajax({
		contentType : 'application/json; charset=utf-8',
		dataType : "json",
		type : 'get',
		url : url,
		success : function(response) {
			ajaxResponseSucess(null, response);
		},
		error : function(e) {
			ajaxResponseFail(e);
		}
	});
}

function doAjaxGetWithUrl(url) {
	console.log(url);
	var url = url;
	$.ajax({
		contentType : 'application/json; charset=utf-8',
		dataType : "json",
		type : 'get',
		url : url,
		success : function(response) {
			ajaxResponseSucessForm(response);
		},
		error : function(e) {
			ajaxResponseFail(e);
		}
	});
}

function selectTab(href) {
	var url = href
	addModelProgressBar();
	$.get(url, function(response, status) {
		toRenderPageAfterAjaxTab(response);
		// if (response.result != undefined && response.result.length > 0) {
		// }
		closeModelProgressBar();
	}).error(function(data, status, headers, config) {
		closeModelProgressBar();
		alert("Ocorreu algum erro interno!!!");
	})
}