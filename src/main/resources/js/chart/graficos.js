google.load("visualization", "1", {
	packages : [ "corechart" ]
});

google.setOnLoadCallback(function() {
	var tipo = $('input[name=tipo_chart]').val();
	var papel = $('input[name=papelId]').val();
	if (papel == undefined) {
		papel = 0;
	}
	if (tipo == 'chartLine') {
		getJsonLineChart(papel);
	} else if (tipo == 'chartCandle') {
		getJsonCandleChart(papel);

	}
});

function getJsonLineChart(papel) {
	$(function() {
		$.ajax({
			type : "GET",
			url : '/Investimentos/site/graficos/lineChart/' + papel,
			datatype : 'JSON',
			success : function(data) {
				var count = 0;
				$.each(data, function() {
					$('#containerInformacoes').append(
							'<hr class="style-six"><div class="row"><div id="chart_div_' + count + '"><div></div>');
					drawLineChart(this, count);
					count++;
				});
			},
			error : function() {
				alert('something bad happened');
			}
		});
	});
}

function getJsonCandleChart(papel) {
	$(function() {
		$.ajax({
			type : "GET",
			url : '/Investimentos/site/graficos/candleChart/' + papel,
			datatype : 'JSON',
			success : function(data) {
				var count = 0;
				$.each(data, function() {
					$('#containerInformacoes').append(
							'<hr class="style-six"><div class="row"><div id="chart_div_' + count + '"><div></div>');
					drawCandletChart(this, count);
					count++;
				});
			},
			error : function() {
				alert('something bad happened');
			}
		});
	});
}

function drawLineChart(dataJson, count) {
	var data = new google.visualization.DataTable();
	data.addColumn('date', 'Month');
	data.addColumn('number', dataJson.papel);

	var cotacoesArray = [];
	for (var i = 0; i < dataJson.cotacoes.length; i++) {
		cotacoesArray.push([ new Date(dataJson.dataCotacao[i]), dataJson.cotacoes[i] ]);
	}
	data.addRows(cotacoesArray);

	var options = {
		hAxis : {
			title : 'Tempo'
		},
		vAxis : {
			title : 'Valor'
		},
		crosshair : {
			color : '#000',
			trigger : 'selection'
		},
		backgroundColor : '#f1f8e9'
	};

//	var chart = new google.visualization.ColumnChart(document.getElementById('chart_div_' + count));
	var chart = new google.visualization.LineChart(document.getElementById('chart_div_' + count));
	chart.draw(data, options);
	// chart.setSelection([ {
	// row : 5,
	// column : 2
	// } ]);
}

function drawCandletChart(dataJson, count) {
	var array = [];
	for (var i = 0; i < dataJson.candles.length; i++) {
		array.push([ new Date(dataJson.candles[i].data), dataJson.candles[i].minimo, dataJson.candles[i].abertura,
				dataJson.candles[i].fechamento, dataJson.candles[i].maximo ]);
	}

	var data = google.visualization.arrayToDataTable(array, true);

	var options = {
		title : 'Grafico de Candle do papel: ' + dataJson.papel,
		legend : 'none',
		hAxis : {
			title : 'Cotacoes'
		},
		vAxis : {
			title : 'Valor'
		},
		backgroundColor : '#f1f8e9'
	};

	var chart = new google.visualization.CandlestickChart(document.getElementById('chart_div_' + count));

	chart.draw(data, options);
}

$(document).ready(function() { // document and jquery ready
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
});
