$(document).ready(function() { // document and jquery ready

	var $alerts = $('div[class^="alert"]');
	$alerts.hide();

	$('#formPage').keydown(function(e) {
		var key = e.which;
		if (key == 13) {
			e.preventDefault();
			// this.setAttribute('action', '/Investimentos/site/#');
			// submitPost(this);
		}
	});
});

function submitPost(frm) {
	var siteFeed = new Object();

	$.each($('input, select ,textarea', $(frm)), function(k) {
		var input = $(this);
		var tempName = input.attr("name");
		if (tempName != "undefined" && tempName != "") {
			siteFeed[tempName] = input.val();
		}

	});

	$.ajax({
		contentType : 'application/json; charset=utf-8',
		dataType : "json",
		type : $(frm).attr('method'),
		url : $(frm).attr('action'),
		data : JSON.stringify(siteFeed),
		success : function(response) {
			var limpar = true;
			if (response.controller == 'site/config/gravarConfiguracao') {
				limpar = false
			}
			ajaxResponseSucess(frm, response, limpar);
			closeModelProgressBar();
		},
		error : function(e) {
			ajaxResponseFail(e);
		}
	});
}

function ajaxResponseSucess(frm, response, limpar) {
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

		if (limpar && $(frm).length > 0) {
			$(frm).get(0).reset();
			limparForm(frm);
		}
		if (response.result != undefined) {
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

function toRenderPageAfterAjaxTab(response) {
	console.log(response.controller);
	if(response.controller == 'site/feed/crudFeedPage.json'){
		createFormFeed(null);
		if (response.result != undefined && response.result.length > 0) {
			tratarReturnList(response.result);
		}
	} else if(response.controller == 'site/config/configuracaoCotacao.json'){
		createFormConfig(response.objeto);
	}
		
}

function tratarForm(response) {
	createFormFeed(response.objeto);
	if (response.result != undefined && response.result.length > 0) {
		tratarReturnList(response.result);
	}
}

function tratarReturnList(result) {
	var $container = $("#tabConteudo");
	$container.empty();
	var array = [];
	array.push([
			"Ações", "Site", "Ativo"
	]);
	for (var i = 0; i < result.length; i++) {
		array.push([
			result[i]
		]);
	}
	makeTableHistorico($container, array);
}

function createFormFeed(formData) {
	
	var idSiteFeed = "";
	var urlSiteFeed = "";
	if (formData != null) {
		idSiteFeed = formData.id;
		urlSiteFeed = formData.url;
	}
	
	var $containerForm = $("#formConteudo");
	$containerForm.empty();
	
	var form = $('<form id="formFeeds" method="post">');
	var inputHidden = $('<input name="id" type="hidden" value="' + idSiteFeed + '" />')
	var divRow1 = $('<div class="row">');
	var divAlertSuccess = $('<div class="alert alert-success alert-dismissable" style="display: none;">');
	var divAlertDanger = $('<div class="alert alert-danger alert-dismissable" style="display: none;">');
	var divRow2 = $('<div class="row">');
	var divColLg = $('<div class="col-lg-6">');
	var divFormGroup = $('<div class="form-group">');
	var labelSite = $('<label for="url">Url:</label>');
	var inputSite = $('<input id="url" name="url" class="form-control" placeholder="Informe a url do site" type="text" value="'
			+ urlSiteFeed + '">');
	var buttonValidar = $('<button type="button" class="btn btn-primary" name="feed/validarFeedSite" value="Validar" onclick="doAjaxPost(this)">Validar</button>');
	var buttonSalvar = $('<button type="button" class="btn btn-primary" name="feed/gravarFeedSite" value="Gravar" onclick="doAjaxPost(this)">Gravar</button>');
	
	form.append(inputHidden);
	form.append(divRow1);
	form.append(divRow2);
	form.append(buttonValidar);
	form.append("&nbsp;");
	form.append(buttonSalvar);
	
	divRow1.append(divAlertSuccess);
	divRow1.append(divAlertDanger);
	
	divRow2.append(divColLg);
	divColLg.append(divFormGroup);
	divFormGroup.append(labelSite);
	divFormGroup.append(inputSite);
	
	$containerForm.append(form);
}

function createFormConfig(objForm) {

	var $containerForm = $("#formConteudoConfig");
	$containerForm.empty();

	var form = $('<form id="formConfig" method="post">');
	var inputHidden = $('<input name="id" type="hidden" value="' + objForm.id + '" />')
	var divRow1 = $('<div class="row">');
	var divAlertSuccess = $('<div class="alert alert-success alert-dismissable" style="display: none;">');
	var divAlertDanger = $('<div class="alert alert-danger alert-dismissable" style="display: none;">');
	var divRow2 = $('<div class="row">');
	
	var divColLgQtdApresentacao = $('<div class="col-lg-4">');
	var divFormGroupQtdApresentacao = $('<div class="form-group">');
	var labelQtdApresentacao = $('<label for="qtdDiasApresentarCotacoes">Quantidade de dias para apresentação de cotações:</label>');
	var inputQtdApresentacao = $('<input id="qtdDiasApresentarCotacoes" name="qtdDiasApresentarCotacoes" class="form-control" placeholder="Informe a quantidade de dias para apresentação" type="text" value="'
			+ objForm.qtdDiasApresentarCotacoes + '">');
	var pQtdApresentacao = $('<p class="help-block">Informe a quantidade de dias que será apresentada para as cotações.</p>')
	
	var divColLgRiscoLoss = $('<div class="col-lg-4">');
	var divFormGroupRiscoLoss = $('<div class="form-group">');
	var labelRiscoLoss = $('<label for="riscoStopLoss">Risco Stop Loss:</label>');
	var inputRiscoLoss = $('<input id="riscoStopLoss" name="riscoStopLoss" class="form-control" placeholder="Informe o risco do stop loss" type="text" value="'
			+ objForm.riscoStopLoss + '">');
	var pRiscoLoss = $('<p class="help-block">Risco assumido para o calculo do stop loss. Ex.:2</p>')
	
	var divColLgRiscoWin = $('<div class="col-lg-4">');
	var divFormGroupRiscoWin = $('<div class="form-group">');
	var labelRiscoWin = $('<label for="riscoStopWin">Risco Stop Win:</label>');
	var inputRiscoWin = $('<input id="riscoStopWin" name="riscoStopWin" class="form-control" placeholder="Informe o risco do stop win" type="text" value="'
			+ objForm.riscoStopWin + '">');
	var pRiscoWin = $('<p class="help-block">Risco assumido para o calculo do stop Win. Ex.:2</p>')
	
	var divRow3 = $('<div class="row">');
	
	var divColLgCalculoStopLoss = $('<div class="col-lg-4">');
	var divFormGroupCalculoStopLoss = $('<div class="form-group">');
	var labelCalculoStopLoss = $('<label for="qtdDiasCalculoStopLoss">Dias para o calculo do stop loss:</label>');
	var inputCalculoStopLoss = $('<input id="qtdDiasCalculoStopLoss" name="qtdDiasCalculoStopLoss" class="form-control" placeholder="Informe os dias para o calculo do stop loss" type="text" value="'
			+ objForm.qtdDiasCalculoStopLoss + '">');
	var pCalculoStopLoss = $('<p class="help-block">Informe quantos dias será utilizado para o calculo do stop loss</p>')
	
	var divColLgCalculoStopWin = $('<div class="col-lg-4">');
	var divFormGroupCalculoStopWin = $('<div class="form-group">');
	var labelCalculoStopWin = $('<label for="qtdDiasCalculoStopWin">Dias para o calculo do stop Win:</label>');
	var inputCalculoStopWin = $('<input id="qtdDiasCalculoStopWin" name="qtdDiasCalculoStopWin" class="form-control" placeholder="Informe os dias para o calculo do stop win" type="text" value="'
			+ objForm.qtdDiasCalculoStopWin + '">');
	var pCalculoStopWin = $('<p class="help-block">Informe quantos dias será utilizado para o calculo do stop win</p>')
	
	var buttonSalvar = $('<button type="button" class="btn btn-primary" name="config/gravarConfiguracao" value="Gravar" onclick="doAjaxPost(this)">Gravar</button>');

	form.append(inputHidden);
	form.append(divRow1);
	form.append(divRow2);
	form.append(divRow3);
	form.append(buttonSalvar);

	divRow1.append(divAlertSuccess);
	divRow1.append(divAlertDanger);
	
	divRow2.append(divColLgQtdApresentacao);
	divColLgQtdApresentacao.append(divFormGroupQtdApresentacao);
	divFormGroupQtdApresentacao.append(labelQtdApresentacao);
	divFormGroupQtdApresentacao.append(inputQtdApresentacao);
	divFormGroupQtdApresentacao.append(pQtdApresentacao);
	
	divRow2.append(divColLgRiscoLoss);
	divColLgRiscoLoss.append(divFormGroupRiscoLoss);
	divFormGroupRiscoLoss.append(labelRiscoLoss);
	divFormGroupRiscoLoss.append(inputRiscoLoss);
	divFormGroupRiscoLoss.append(pRiscoLoss);
	
	divRow2.append(divColLgRiscoWin);
	divColLgRiscoWin.append(divFormGroupRiscoWin);
	divFormGroupRiscoWin.append(labelRiscoWin);
	divFormGroupRiscoWin.append(inputRiscoWin);
	divFormGroupRiscoWin.append(pRiscoWin);
	
	divRow3.append(divColLgCalculoStopLoss);
	divColLgCalculoStopLoss.append(divFormGroupCalculoStopLoss);
	divFormGroupCalculoStopLoss.append(labelCalculoStopLoss);
	divFormGroupCalculoStopLoss.append(inputCalculoStopLoss);
	divFormGroupCalculoStopLoss.append(pCalculoStopLoss);

	divRow3.append(divColLgCalculoStopWin);
	divColLgCalculoStopWin.append(divFormGroupCalculoStopWin);
	divFormGroupCalculoStopWin.append(labelCalculoStopWin);
	divFormGroupCalculoStopWin.append(inputCalculoStopWin);
	divFormGroupCalculoStopWin.append(pCalculoStopWin);

	$containerForm.append(form);
}

function makeTableHistorico(container, data) {
	var table = $("<table/>").addClass('table table-striped table-bordered table-hover');
	var thead = $("<thead/>");
	thead.append($("<th/>").text(data[0][0]));
	thead.append($("<th/>").text(data[0][1]));
	thead.append($("<th/>").text(data[0][2]));

	table.append(thead);
	$.each(data, function(rowIndex, r) {
		var row = $("<tr/>");
		$.each(r, function(colIndex, c) {
			if (rowIndex != 0) {
				var td = $("<td/>");
				var a2 = $('<a href="#" onclick="doAjaxGetWithUrl(\'feed/alterarSiteFeed/' + c.id + '\');" title="Alterar"/>');
				var img2 = $('<p class="fa fa-pencil"></p>');
				a2.append(img2);
				td.append(a2);
				td.append(" ");

				var a = $('<a href="#" onclick="showConfirmRemove(\'feed/excluirSiteFeed/' + c.id + '\');" title="Excluir"/>');
				var img = $('<p class="glyphicon glyphicon-remove"></p>');
				a.append(img);
				td.append(a);
				td.append(" ");

				var a3 = $('<a href="#" onclick="doAjaxGetWithUrl(\'feed/ativarInativarSiteFeed/' + c.id + '\');" title="Ativar/Inativar"/>');
				var img3 = $('<p class="fa fa-power-off"></p>');
				a3.append(img3);
				td.append(a3);
				td.append(" ");

				row.append(td);

				if(c.ativo){
					var url = $("<label>");
					url.text(c.nomeSite);
				}else {
					var url = $("<strike>");
					url.text(c.nomeSite);
					
				}
				row.append($("<td/>").append(url));
				row.append($("<td/>").text(c.ativoDesc));
				table.append(row);
			}
		});
	});
	container.append(table);
}