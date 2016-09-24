$(document).ready(function() { // document and jquery ready

	var $alerts = $('div[class^="alert"]');
	$alerts.hide();

	$('#formPage').keydown(function(e) {
		var key = e.which;
		if (key == 13) {
			e.preventDefault();
			this.setAttribute('action', '/Investimentos/site/gravarNota');
			submitPost(this);
		}
	});

});

function submitPost(frm) {
	var nota = new Object();

	$.each($('input, select ,textarea', $(frm)), function(k) {
		var input = $(this);
		var tempName = input.attr("name");
		if (tempName != "undefined" && tempName != "") {
			nota[tempName] = input.val();
		}
	});

	// console.log(JSON.stringify(cotacao));
	$.ajax({
		contentType : 'application/json; charset=utf-8',
		dataType : "json",
		type : $(frm).attr('method'),
		url : $(frm).attr('action'),
		data : JSON.stringify(nota),
		success : function(response) {

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

				if (response.controller == 'site/consolidar') {
					montarAbaEmpresa(response);
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

		},
		error : function(e) {
			ajaxResponseFail(e);
		}
	});
}

function montarAbaEmpresa(response) {

	var $container = $("#tabelaEmpresa");
	$container.empty();
	var table = $("<table id='tabelaEmpresa'/>").addClass('table table-bordered table-striped  table-hover');
	var tbody = $("<tbody/>");
	var tr = $("<tr/>");
	var th = $("<th width='15%'/>");
	var td = $("<td/>").addClass('text-muted warning');
	th.text("Emrpesa");
	td.text(response.objeto.nome);
	tr.append(th);
	tr.append(td);
	tbody.append(tr);
	tr = $("<tr/>");
	th = $("<th width='15%'/>");
	td = $("<td/>").addClass('text-muted warning');
	th.text("Setor");
	td.text(response.objeto.setor);
	tr.append(th);
	tr.append(td);
	tbody.append(tr);
	tr = $("<tr/>");
	th = $("<th width='15%'/>");
	td = $("<td/>").addClass('text-muted warning');
	th.text("Subsetor");
	td.text(response.objeto.subsetor);
	tr.append(th);
	tr.append(td);
	tbody.append(tr);
	table.append(tbody);
	$container.append(table);
}

function tratarReturnList(result) {
	var array = [];
	array.push([
			"Ações", "Data", "Título", "Sumário", "Descrição"
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

				var a2 = $('<a href="#" onclick="linkIn(\'preAlterarNota/' + c.id + '\');" />');
				var img2 = $('<p class="fa fa-pencil"></p>');
				a2.append(img2);
				td.append(a2);
				td.append(" ");

				var a3 = $('<a href="#" onclick="showConfirmRemove(\'excluirNota/' + c.id + '\');" />');
				var img3 = $('<p class="glyphicon glyphicon-remove"></p>');
				a3.append(img3);
				td.append(a3);
				td.append(" ");

				row.append(td);
				row.append($("<td/>").text(c.data));
				row.append($("<td/>").text(c.titulo));
				row.append($("<td/>").text(c.sumario));
				row.append($("<td/>").text(c.descricao));
				table.append(row);
			}
		});
	});
	container.append(table);
}

function toRenderPageAfterAjaxTab(response) {
	if (response.controller == 'analiseFundamentalistaView') {
		montarAbaAnaliseFundamentalista(response);
	}
}

function montarAbaEmpresa(response) {

	var $container = $("#tabelaEmpresa");
	$container.empty();
	var table = $("<table id='tabelaEmpresa'/>").addClass('table table-bordered table-striped  table-hover');
	var tbody = $("<tbody/>");
	var tr = $("<tr/>");
	var th = $("<th width='15%'/>");
	var td = $("<td/>").addClass('text-muted warning');
	th.text("Emrpesa");
	td.text(response.objeto.nome);
	tr.append(th);
	tr.append(td);
	tbody.append(tr);
	tr = $("<tr/>");
	th = $("<th width='15%'/>");
	td = $("<td/>").addClass('text-muted warning');
	th.text("Setor");
	td.text(response.objeto.setor);
	tr.append(th);
	tr.append(td);
	tbody.append(tr);
	tr = $("<tr/>");
	th = $("<th width='15%'/>");
	td = $("<td/>").addClass('text-muted warning');
	th.text("Subsetor");
	td.text(response.objeto.subsetor);
	tr.append(th);
	tr.append(td);
	tbody.append(tr);
	table.append(tbody);
	$container.append(table);
}

function montarAbaAnaliseFundamentalista(response) {
	var $container1 = $("#conteudoFundamenalistaTabela1");
	var $container2 = $("#conteudoFundamenalistaTabela1");
	var $container3 = $("#conteudoFundamenalistaTabela1");
	var $container4 = $("#conteudoFundamenalistaTabela1");
	$container1.empty();
	$container2.empty();
	$container3.empty();
	$container4.empty();
	// ************** container 1
	var table = $("<table id='tabelaEmpresa'/>").addClass('table table-bordered table-striped  table-hover');
	var tbody = $("<tbody/>");

	// LINHA: COTAÇÃO, DATA ULT. COT
	var tr = $("<tr/>");
	var th1 = $("<th width='20%'/>");
	var th2 = $("<th width='20%'/>");

	var td1 = $("<td/>").addClass('text-muted warning');
	var td2 = $("<td/>").addClass('text-muted warning');

	th1.text("Cotação");
	th2.text("Data últ. cot.");
	td1.text(response.objeto.cotacao);
	td2.text(response.objeto.dataUltimaCotacao);

	tr.append(th1);
	tr.append(td1);
	tr.append(th2);
	tr.append(td2);
	tbody.append(tr);

	// LINHA: Min 52 Semana, Max 52 Semana
	tr = $("<tr/>");
	th1 = $("<th/>");
	th2 = $("<th/>");

	td1 = $("<td/>").addClass('text-muted warning');
	td2 = $("<td/>").addClass('text-muted warning');

	th1.text("Min 52 Semana");
	th2.text("Max 52 Semana");
	td1.text(response.objeto.min52Semanas);
	td2.text(response.objeto.max52Semanas);

	tr.append(th1);
	tr.append(td1);
	tr.append(th2);
	tr.append(td2);
	tbody.append(tr);

	// LINHA: VALOR DE MERCADO, ÚLTI BALANCO PROCESS
	tr = $("<tr/>");
	th1 = $("<th/>");
	th2 = $("<th/>");

	td1 = $("<td/>").addClass('text-muted warning');
	td2 = $("<td/>").addClass('text-muted warning');

	th1.text("Valor de mercado");
	th2.text("Últi balanco process");
	td1.text(response.objeto.valorMercado);
	td2.text(response.objeto.ultimoBalanco);

	tr.append(th1);
	tr.append(td1);
	tr.append(th2);
	tr.append(td2);
	tbody.append(tr);

	// LINHA: VOL. MEDIO 2M
	tr = $("<tr/>");
	th1 = $("<th/>");

	td1 = $("<td colspan='3'/>").addClass('text-muted warning');

	th1.text("Vol. médio 2m");
	td1.text(response.objeto.volumeNegociacao2Meses);

	tr.append(th1);
	tr.append(td1);
	tbody.append(tr);

	// LINHA: VAZIO
	// tr = $("<tr/>");
	// td1 = $("<td colspan='6'/>");
	// tr.append(td1);
	// tbody.append(tr);

	// ADICINAR NA PAGINA
	table.append(tbody);
	$container1.append(table);

	// ************** container 2
	table = $("<table id='tabelaEmpresa'/>").addClass('table table-bordered table-striped  table-hover');
	tbody = $("<tbody/>");

	// LINHA: OSCILAÇÕES, INDICADORES FUNDAMENTALISTAS
	tr = $("<tr/>");
	th1 = $("<th colspan='2'/>");
	th2 = $("<th colspan='4'/>");
	th1.text("Oscilações");
	th2.text("Indicadores Fundamentalistas");

	tr.append(th1);
	tr.append(th2);
	tbody.append(tr);

	// LINHA: DIA, P/L, LPA
	tr = $("<tr/>");
	th1 = $("<th width='10%'/>");
	th2 = $("<th width='20%'/>");
	th3 = $("<th width='15%'/>");

	if (response.objeto.dia.replace('%','').replace(',','.') > 0) {
		css = "warning";
	} else {
		css = "danger";
	}
	td1 = $("<td/>").addClass('text-muted '+css);
	td2 = $("<td/>").addClass('text-muted warning');
	td3 = $("<td/>").addClass('text-muted warning');

	th1.text("Dia");
	th2.text("P/L");
	th3.text("LPA");
	td1.text(response.objeto.dia);
	td2.text(response.objeto.p_l);
	td3.text(response.objeto.lucroPorAcao);

	tr.append(th1);
	tr.append(td1);
	tr.append(th2);
	tr.append(td2);
	tr.append(th3);
	tr.append(td3);
	tbody.append(tr);

	// LINHA: MES, P/VP, VPA
	tr = $("<tr/>");
	th1 = $("<th/>");
	th2 = $("<th/>");
	th3 = $("<th/>");

	
	if (response.objeto.mes.replace('%','').replace(',','.') > 0) {
		css = "warning";
	} else {
		css = "danger";
	}
	td1 = $("<td/>").addClass('text-muted '+css);
	td2 = $("<td/>").addClass('text-muted warning');
	td3 = $("<td/>").addClass('text-muted warning');

	th1.text("Mês");
	th2.text("P/VP");
	th3.text("VPA");
	td1.text(response.objeto.mes);
	td2.text(response.objeto.p_vp);
	td3.text(response.objeto.valorPatrimonialPorAcao);

	tr.append(th1);
	tr.append(td1);
	tr.append(th2);
	tr.append(td2);
	tr.append(th3);
	tr.append(td3);
	tbody.append(tr);

	// LINHA: 30 DIAS, P/EBIT, MARG. BRUTA
	tr = $("<tr/>");
	th1 = $("<th/>");
	th2 = $("<th/>");
	th3 = $("<th/>");

	if (response.objeto.trintaDias.replace('%','').replace(',','.') > 0) {
		css = "warning";
	} else {
		css = "danger";
	}
	td1 = $("<td/>").addClass('text-muted '+css);
	td2 = $("<td/>").addClass('text-muted warning');
	td3 = $("<td/>").addClass('text-muted warning');

	th1.text("30 dias");
	th2.text("P/EBIT");
	th3.text("Marg. BRUTA");
	td1.text(response.objeto.trintaDias);
	td2.text(response.objeto.p_ebit);
	td3.text(response.objeto.margemBruto);

	tr.append(th1);
	tr.append(td1);
	tr.append(th2);
	tr.append(td2);
	tr.append(th3);
	tr.append(td3);
	tbody.append(tr);

	// LINHA: 12 MESES, PSR, MARG. EBIT
	tr = $("<tr/>");
	th1 = $("<th/>");
	th2 = $("<th/>");
	th3 = $("<th/>");

	if (response.objeto.dozeMeses.replace('%','').replace(',','.') > 0) {
		css = "warning";
	} else {
		css = "danger";
	}
	td1 = $("<td/>").addClass('text-muted '+css);
	td2 = $("<td/>").addClass('text-muted warning');
	td3 = $("<td/>").addClass('text-muted warning');

	th1.text("12 Meses");
	th2.text("PSR");
	th3.text("Marg. EBIT");
	td1.text(response.objeto.dozeMeses);
	td2.text(response.objeto.psr);
	td3.text(response.objeto.margemEbit);

	tr.append(th1);
	tr.append(td1);
	tr.append(th2);
	tr.append(td2);
	tr.append(th3);
	tr.append(td3);
	tbody.append(tr);

	// LINHA: 2015, P/ATIVOS, MARG. LIQUID
	tr = $("<tr/>");
	th1 = $("<th/>");
	th2 = $("<th/>");
	th3 = $("<th/>");

	if (response.objeto.oscilacao2015.replace('%','').replace(',','.') > 0) {
		css = "warning";
	} else {
		css = "danger";
	}
	td1 = $("<td/>").addClass('text-muted '+css);
	td2 = $("<td/>").addClass('text-muted warning');
	td3 = $("<td/>").addClass('text-muted warning');

	th1.text("2015");
	th2.text("P/ATIVOS");
	th3.text("Marg. Liquid.");
	td1.text(response.objeto.oscilacao2015);
	td2.text(response.objeto.p_ativos);
	td3.text(response.objeto.margemLiquida);

	tr.append(th1);
	tr.append(td1);
	tr.append(th2);
	tr.append(td2);
	tr.append(th3);
	tr.append(td3);
	tbody.append(tr);

	// LINHA: 2014, P/CAP. GIRO, EBTI/ATIVO
	tr = $("<tr/>");
	th1 = $("<th/>");
	th2 = $("<th/>");
	th3 = $("<th/>");

	if (response.objeto.oscilacao2014.replace('%','').replace(',','.') > 0) {
		css = "warning";
	} else {
		css = "danger";
	}
	td1 = $("<td/>").addClass('text-muted '+css);
	
	if (response.objeto.p_capitacaoGiro.replace('%','').replace(',','.') > 0) {
		css = "warning";
	} else {
		css = "danger";
	}
	td2 = $("<td/>").addClass('text-muted '+css);
	td3 = $("<td/>").addClass('text-muted warning');

	th1.text("2014");
	th2.text("P/cap. giro");
	th3.text("EBIT/Ativo");
	td1.text(response.objeto.oscilacao2014);
	td2.text(response.objeto.p_capitacaoGiro);
	td3.text(response.objeto.ebit_ativo);

	tr.append(th1);
	tr.append(td1);
	tr.append(th2);
	tr.append(td2);
	tr.append(th3);
	tr.append(td3);
	tbody.append(tr);

	// LINHA: 2013, P/ATIV. CIRC. LIQUID., ROIC
	tr = $("<tr/>");
	th1 = $("<th/>");
	th2 = $("<th/>");
	th3 = $("<th/>");

	if (response.objeto.oscilacao2013.replace('%','').replace(',','.') > 0) {
		css = "warning";
	} else {
		css = "danger";
	}
	td1 = $("<td/>").addClass('text-muted '+css);
	
	if (response.objeto.p_ativoCirculanteLiquido.replace('%','').replace(',','.') > 0) {
		css = "warning";
	} else {
		css = "danger";
	}
	td2 = $("<td/>").addClass('text-muted '+css);
	td3 = $("<td/>").addClass('text-muted warning');

	th1.text("2013");
	th2.text("P/Ativ. Circ. Liquid.");
	th3.text("ROIC");
	td1.text(response.objeto.oscilacao2013);
	td2.text(response.objeto.p_ativoCirculanteLiquido);
	td3.text(response.objeto.roic);

	tr.append(th1);
	tr.append(td1);
	tr.append(th2);
	tr.append(td2);
	tr.append(th3);
	tr.append(td3);
	tbody.append(tr);

	// LINHA: 2012, DIV.YIELD, ROE
	tr = $("<tr/>");
	th1 = $("<th/>");
	th2 = $("<th/>");
	th3 = $("<th/>");

	if (response.objeto.oscilacao2012.replace('%','').replace(',','.') > 0) {
		css = "warning";
	} else {
		css = "danger";
	}
	td1 = $("<td/>").addClass('text-muted '+css);
	td2 = $("<td/>").addClass('text-muted warning');
	td3 = $("<td/>").addClass('text-muted warning');

	th1.text("2012");
	th2.text("Div. Yield");
	th3.text("ROE");
	td1.text(response.objeto.oscilacao2012);
	td2.text(response.objeto.dividendoYield);
	td3.text(response.objeto.roe);

	tr.append(th1);
	tr.append(td1);
	tr.append(th2);
	tr.append(td2);
	tr.append(th3);
	tr.append(td3);
	tbody.append(tr);

	// LINHA: 2011, EV/EBIT, LIQUIDES CORR.
	tr = $("<tr/>");
	th1 = $("<th/>");
	th2 = $("<th/>");
	th3 = $("<th/>");

	if (response.objeto.oscilacao2011.replace('%','').replace(',','.') > 0) {
		css = "warning";
	} else {
		css = "danger";
	}
	td1 = $("<td/>").addClass('text-muted '+css);
	td2 = $("<td/>").addClass('text-muted warning');
	td3 = $("<td/>").addClass('text-muted warning');

	th1.text("2011");
	th2.text("EV/ EBIT");
	th3.text("Liquidez Corr.");
	td1.text(response.objeto.oscilacao2011);
	td2.text(response.objeto.ev_ebit);
	td3.text(response.objeto.liquidesCorrente);

	tr.append(th1);
	tr.append(td1);
	tr.append(th2);
	tr.append(td2);
	tr.append(th3);
	tr.append(td3);
	tbody.append(tr);

	// LINHA: 2010, GRIRO ATIVOS, DIV BRUTA/PATRIM
	tr = $("<tr/>");
	th1 = $("<th/>");
	th2 = $("<th/>");
	th3 = $("<th/>");

	if (response.objeto.oscilacao2010.replace('%','').replace(',','.') > 0) {
		css = "warning";
	} else {
		css = "danger";
	}
	td1 = $("<td/>").addClass('text-muted '+css);
	td2 = $("<td/>").addClass('text-muted warning');
	td3 = $("<td/>").addClass('text-muted warning');

	th1.text("2010");
	th2.text("Giro Ativos");
	th3.text("Div Bruta/Patrim");
	td1.text(response.objeto.oscilacao2010);
	td2.text(response.objeto.giroAtivos);
	td3.text(response.objeto.dividaBruta_Patrimonio);

	tr.append(th1);
	tr.append(td1);
	tr.append(th2);
	tr.append(td2);
	tr.append(th3);
	tr.append(td3);
	tbody.append(tr);

	// LINHA: CRESCIMENTO RECEITA 5 ANOS
	tr = $("<tr/>");
	th1 = $("<th/>");
	th2 = $("<th/>");
	th3 = $("<th/>");

	td1 = $("<td/>").addClass('text-muted warning');
	if (response.objeto.crescrimentoReceitaLiquidaCincoAnos.replace('%','').replace(',','.') > 0) {
		css = "warning";
	} else {
		css = "danger";
	}
	td2 = $("<td/>").addClass('text-muted '+css);
	td3 = $("<td/>").addClass('text-muted warning');

	th2.text("Cres. Receita 5 anos");
	td2.text(response.objeto.crescrimentoReceitaLiquidaCincoAnos);

	tr.append(th1);
	tr.append(td1);
	tr.append(th2);
	tr.append(td2);
	tr.append(th3);
	tr.append(td3);
	tbody.append(tr);

	// ADICINAR NA PAGINA
	table.append(tbody);
	$container2.append(table);

	// ************** CONTAINER 3

	table = $("<table id='tabelaEmpresa'/>").addClass('table table-bordered table-striped  table-hover');
	tbody = $("<tbody/>");

	// LINHA: DADOS BALANÇO PATRIMONIAL
	tr = $("<tr/>");
	th1 = $("<th colspan='4'/>");
	th1.text("Dados Balanço Patrimonial");

	tr.append(th1);
	tbody.append(tr);

	// LINHA: ATIVO, DIV. BRUTA
	tr = $("<tr/>");
	th1 = $("<th width='15%'/>");
	th2 = $("<th width='15%'/>");

	td1 = $("<td/>").addClass('text-muted warning');
	td2 = $("<td/>").addClass('text-muted warning');

	th1.text("Ativo");
	th2.text("Dívida Bruta");
	td1.text(response.objeto.ativo);
	td2.text(response.objeto.dividaBruta);

	tr.append(th1);
	tr.append(td1);
	tr.append(th2);
	tr.append(td2);
	tbody.append(tr);

	// LINHA: DISPONIBILIDADE, DIV. LIQUIDA
	tr = $("<tr/>");
	th1 = $("<th/>");
	th2 = $("<th/>");

	td1 = $("<td/>").addClass('text-muted warning');
	td2 = $("<td/>").addClass('text-muted warning');

	th1.text("Dispinibilidade");
	th2.text("Dívida Líquida");
	td1.text(response.objeto.disponibilidades);
	td2.text(response.objeto.dividaLiquida);

	tr.append(th1);
	tr.append(td1);
	tr.append(th2);
	tr.append(td2);
	tbody.append(tr);

	// LINHA: ATIVO CIRCULANTE, PATRIMONIL LIQUIDO
	tr = $("<tr/>");
	th1 = $("<th/>");
	th2 = $("<th/>");

	td1 = $("<td/>").addClass('text-muted warning');
	td2 = $("<td/>").addClass('text-muted warning');

	th1.text("Ativo Circulante");
	th2.text("Patrimônio Líquido");
	td1.text(response.objeto.disponibilidades);
	td2.text(response.objeto.patrimonioLiquido);

	tr.append(th1);
	tr.append(td1);
	tr.append(th2);
	tr.append(td2);
	tbody.append(tr);

	// ADICINAR NA PAGINA
	table.append(tbody);
	$container2.append(table);

	// ************** CONTAINER 4

	table = $("<table id='tabelaEmpresa'/>").addClass('table table-bordered table-striped  table-hover');
	tbody = $("<tbody/>");

	// LINHA: DADOS DEMOSTRATIVOS DE RESULTADOS
	tr = $("<tr/>");
	th1 = $("<th colspan='4'/>");
	th1.text("Dados Demostrativos de Resultados");

	tr.append(th1);
	tbody.append(tr);

	// LINHA: ÚLTIMOS 12 MESES,ÚLTIMOS 2 MESES
	tr = $("<tr/>");
	th1 = $("<th colspan='2'/>");
	th2 = $("<th colspan='2'/>");

	th1.text("Últimos 12 meses");
	th2.text("Últimos 3 meses");

	tr.append(th1);
	tr.append(th2);
	tbody.append(tr);

	// LINHA: RECEITA LÍQUIDA
	tr = $("<tr/>");
	th1 = $("<th width='15%'/>");
	th2 = $("<th width='15%'/>");

	td1 = $("<td/>").addClass('text-muted warning');
	td2 = $("<td/>").addClass('text-muted warning');

	th1.text("Receita Líquida");
	th2.text("Receita Líquida");
	td1.text(response.objeto.receitaLiquidaDozeMeses);
	td2.text(response.objeto.receitaLiquidaTresMeses);

	tr.append(th1);
	tr.append(td1);
	tr.append(th2);
	tr.append(td2);
	tbody.append(tr);

	// LINHA: EBIT
	tr = $("<tr/>");
	th1 = $("<th/>");
	th2 = $("<th/>");

	td1 = $("<td/>").addClass('text-muted warning');
	td2 = $("<td/>").addClass('text-muted warning');

	th1.text("EBIT");
	th2.text("EBIT");
	td1.text(response.objeto.ebitDozeMeses);
	td2.text(response.objeto.ebitTresMeses);

	tr.append(th1);
	tr.append(td1);
	tr.append(th2);
	tr.append(td2);
	tbody.append(tr);

	// LINHA: LUCRO LÍQUIDO
	tr = $("<tr/>");
	th1 = $("<th/>");
	th2 = $("<th/>");

	td1 = $("<td/>").addClass('text-muted warning');
	td2 = $("<td/>").addClass('text-muted warning');

	th1.text("Lucro Líquido");
	th2.text("Lucro Líquido");
	td1.text(response.objeto.lucroLiquidoTresMeses);
	td2.text(response.objeto.lucroLiquidoTresMeses);

	tr.append(th1);
	tr.append(td1);
	tr.append(th2);
	tr.append(td2);
	tbody.append(tr);

	// ADICINAR NA PAGINA
	table.append(tbody);
	$container2.append(table);
}