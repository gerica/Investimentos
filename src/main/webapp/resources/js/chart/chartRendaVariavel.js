$(document).ready(function() { // document and jquery ready
	var $alerts = $('div[class^="alert"]');
	$alerts.hide();

	$('#formPage').keydown(function(e) {
		var key = e.which;
		if (key == 13) {
			e.preventDefault();
			this.setAttribute('action', '/Investimentos/site/graficos/pesquisar');
			submitPost(this);
		}
	});

	$("#idComboEmpresa").on("change", function(e) {
		var dropValue = $(this).val();

		$.ajax({
			type : "GET",
			url : '/Investimentos/site/graficos/montarComboPapel/' + dropValue,
			datatype : 'JSON',
			success : function(response) {
				var options = $("#idComboPapel");
				options.empty();
				options.append($("<option />").val("0").text("--- Selecione ---"));
				// don't forget error handling!
				$.each(response.result, function() {
					options.append($("<option />").val(this.id).text(this.valor));
				});
			},
			error : function() {
				alert('something bad happened');
			}
		});
	});

});

function submitPost(frm) {
	var dto = new Object();
	var empresa = new Object();
	var papel = new Object();
	dto.papel = papel;
	dto.empresa = empresa;

	$.each($('input, select ,textarea', $(frm)), function(k) {
		var input = $(this);
		var tempName = input.attr("name");
		if (tempName == "papel.id") {
			papel.id = input.val();
		} else if (tempName == "empresa.id") {
			empresa.id = input.val();
		} else {
			dto[tempName] = input.val();
		}
	});

	$.ajax({
		contentType : 'application/json; charset=utf-8',
		dataType : "json",
		type : $(frm).attr('method'),
		url : $(frm).attr('action'),
		data : JSON.stringify(dto),
		success : function(response) {

			var $alerts = $('div[class^="alert"]');
			$alerts.hide();
			$alerts.empty();

			// we have the response
			if (response.status == "sucesso") {
				// var $alertSuccess = $('div[class^="alert alert-success"]');
				// $alertSuccess.append('<button type="button" class="close"
				// data-dismiss="alert" aria-hidden="true">&times;</button>');
				// var successInfo = "1. " + response.message + "";
				// $alertSuccess.append(successInfo);
				// $alertSuccess.show();

				if (response.result != undefined) {
					montarGrafico(response);
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

function montarGrafico(response) {
	var count = 0;
	var containerGrafico = $("#containerGraficos");
	containerGrafico.empty();
	$.each(response.result, function() {

		var divCol = $('<div class="col-lg-12">');
		var divPanel = $('<div class="panel panel-default">');
		var divHeading = $('<div class="panel-heading">Gráfico: ' + this.papel + '</div>');
		var divBody = $('<div class="panel-body">');
		var divChart = $('<div class="flot-chart" style="float:left; width:88%;">');
		var divContent = $('<div class="flot-chart-content" id="flot-line-chart-' + count + '"></div>');
		var pChoices = $('<p id="choices-' + count + '" style="float:right; width:12%;"></p>');
		divCol.append(divPanel);
		divPanel.append(divHeading);
		divPanel.append(divBody);
		divBody.append(divChart);
		divChart.append(divContent);
		divBody.append(pChoices);
		containerGrafico.append(divCol);

		if (response.objeto == 'chartLine') {
			drawLineChart(this, count);
		} else if (response.objeto == 'chartCandle') {
			drawCandleChart(this, count);
		}
		count++;
	});
}

function drawLineChart_(dataJson, count) {

	var dtoValues = [];
	var min = 100000;
	for (var i = 0; i < dataJson.cotacoes.length; i++) {
		chartObj = new Object();
		chartObj.y = dataJson.cotacoes[i].data;
		chartObj.x = dataJson.cotacoes[i].fechamento;
		chartObj.z = dataJson.cotacoes[i].mme;
		chartObj.r = dataJson.cotacoes[i].stopLoss;

		if (chartObj.x < min) {
			min = chartObj.x;
		}
		dtoValues.push(chartObj);
	}

	/*
	 * Play with this code and it'll update in the panel opposite.
	 * 
	 * Why not try some of the options above?
	 */
	// min = (min - (min * 0.01));
	Morris.Line({
		element : "flot-line-chart-" + count,
		data : dtoValues,
		xkey : 'y',
		ykeys : [
				'x', 'z', 'r'
		],
		labels : [
				dataJson.papel + ' R$', 'MMe', 'Stop Loss'
		],
		ymin : 'auto ' + min,
	});
}

function drawLineChart(dataJson, count) {

	var dtoValues = [];
	var mme = [];
	var stops = [];
	var wins = [];
	var min = 100000;
	for (var i = 0; i < dataJson.cotacoes.length; i++) {
		chartObj = new Object();
		chartObj.data = dataJson.cotacoes[i].data;
		chartObj.fechamento = dataJson.cotacoes[i].fechamento;
		chartObj.mme = dataJson.cotacoes[i].mme;
		chartObj.stopLoss = dataJson.cotacoes[i].stopLoss;
		chartObj.stopWin = dataJson.cotacoes[i].stopWin;

		dtoValues.push([
				new Date(chartObj.data), chartObj.fechamento
		]);
		mme.push([
				new Date(chartObj.data), chartObj.mme
		]);
		stops.push([
		            new Date(chartObj.data), chartObj.stopLoss
		            ]);
		wins.push([
				new Date(chartObj.data), chartObj.stopWin
		]);

		if (chartObj.stopLoss < min) {
			min = (chartObj.stopLoss * 0.88);
		}
	}

	var options = {
		series : {
			lines : {
				show : true,
			},
			points : {
				show : true,
				radius : 4,
				symbol : "circle",
			}
		},
		grid : {
			hoverable : true,
		// IMPORTANT! this is needed for tooltip to work
		},
		xaxis : {
			mode : "time",
			minTickSize : [
					1, "hour",
			],
			twelveHourClock : true,
			timeformat : "%d/%m/%Y"
		},
		yaxis : {
			min : min,
			tickFormatter : function(val, axis) {
				return val;
			},

		},
		tooltip : true,
		tooltipOpts : {
			content : "'%s' de %x é R$ %y",
			shifts : {
				x : -60,
				y : 25
			}
		}
	};

	var datasets = {
		"cotacao" : {
			data : dtoValues,
			label : "Cotações"
		},
		"mme" : {
			data : mme,
			label : "MMe",
		},
		"stopLoss" : {
			data : stops,
			label : "Stop Loss",

		},
		"stopWin" : {
			data : wins,
			label : "Stop Win",
		}
	};

	var i = 0;
	$.each(datasets, function(key, val) {
		val.color = i;
		++i;
	});

	// insert checkboxes
	var choiceContainer = $("#choices-" + count);
	$.each(datasets, function(key, val) {
		choiceContainer.append("<br/><input type='checkbox' name='" + key + "' checked='checked' id='id" + key + "'></input>"
				+ "<label for='id" + key + "'>" + val.label + "</label>");
	});

	choiceContainer.find("input").click(plotAccordingToChoices);

	function plotAccordingToChoices() {

		var data = [];

		choiceContainer.find("input:checked").each(function() {
			var key = $(this).attr("name");
			if (key && datasets[key]) {
				data.push(datasets[key]);
			}
		});

		if (data.length > 0) {
			$.plot("#flot-line-chart-" + count, data, options);
		}
	}

	plotAccordingToChoices();
}

function drawCandleChart(dataJson, count) {
	var dtoValues = [];
	var mme = [];
	var stops = [];
	var min;

	for (var i = 0; i < dataJson.candles.length; i++) {
		chartObj = new Object();
		var ano = parseInt(dataJson.candles[i].data.substring(0, 4));
		var mes = parseInt(dataJson.candles[i].data.substring(5, 7)) - 1;
		var dia = parseInt(dataJson.candles[i].data.substring(8, 10));

		chartObj.data = Date.UTC(ano, mes, dia);
		chartObj.abertura = parseFloat(dataJson.candles[i].abertura);
		chartObj.fechamento = parseFloat(dataJson.candles[i].fechamento);
		chartObj.minimo = parseFloat(dataJson.candles[i].minimo);
		chartObj.maximo = parseFloat(dataJson.candles[i].maximo);

		dtoValues.push([
				chartObj.data, chartObj.abertura, chartObj.fechamento, chartObj.minimo, chartObj.maximo
		]);
		if (min == undefined) {
			min = chartObj.data;
		}
		if (chartObj.data < min) {
			min = chartObj.data;
		}
	}

	var lw = Date.UTC(0, 0, 0, 12) - Date.UTC(0, 0, 0, 0);
	var data = $.plot.candlestick.createCandlestick({
		label : dataJson.papel,
		data : dtoValues,
		candlestick : {
			show : true,
			lineWidth : lw
		}
	});

	var options = {
		series : {
			candlestick : {
				active : true
			}
		},
		xaxis : {
			mode : "time",
			min : min,
			minTickSize : [
					1, "hour",
			],
			twelveHourClock : true,
			timeformat : "%d/%m/%Y"
		},
		yaxes : {
			tickFormatter : function(val, axis) {
				return val;
			}
		},

	};

	$.plot($("#flot-line-chart-" + count), data, options);
}

function monaComboPapel() {
	$("#idComboEmpresa").on("change", function(e) {
		var dropValue = $(this).val();

		$.ajax({
			type : "GET",
			url : '/Investimentos/site/graficos/montarComboPapel/' + dropValue,
			datatype : 'JSON',
			success : function(data) {
				var options = $("#idComboPapel");
				options.empty();
				options.append($("<option />").val("0").text("--- Selecione ---"));
				// don't forget error handling!
				$.each(data, function() {
					options.append($("<option />").val(this.id).text(this.valor));
				});
			},
			error : function() {
				alert('something bad happened');
			}
		});
	});
}