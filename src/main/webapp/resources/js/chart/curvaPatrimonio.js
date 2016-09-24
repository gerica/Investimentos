$(document).ready(function() { // document and jquery ready
	onLoadPage('/Investimentos/site/curvaPatrimonio/montarTabelaPatrimonio.json');
});

function onLoadPage(url) {
	$.ajax({
		contentType : 'application/json; charset=utf-8',
		dataType : "json",
		type : 'get',
		url : url,
		success : function(response) {
			montarGrafico(response.result);
			tratarReturnList(response.result);
		},
		error : function(e) {
			ajaxResponseFail(e);
		}
	});
}

function tratarReturnList(result) {
	var array = [];
	array.push([
			"Data", "Valor Total", "2%", "6%", "Variação"
	]);
	for (var i = 0; i < result.length; i++) {
		array.push([
			result[i]
		]);
	}
	var $container = $('.table-responsive');
	$container.empty();
	montaTabela($container, array);
}

function montaTabela(container, data) {
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

				row.append($("<td/>").text(c.dataFormatada));
				row.append($("<td/>").text("R$ " + c.valorTotalFormatado));
				row.append($("<td/>").text("R$ " + c.valor2Porcento));
				row.append($("<td/>").text("R$ " + c.valor6Porcento));
				row.append($("<td/>").text("R$ " + c.variacaoFormatada));
				table.append(row);
			}
		});
	});
	container.append(table);
}

function montarGrafico(result) {
	var count = 0;
	var containerGrafico = $("#containerGraficos");
	containerGrafico.empty();
	// $.each(result, function() {

	var divCol = $('<div class="col-lg-12">');
	var divPanel = $('<div class="panel panel-default">');
	var divHeading = $('<div class="panel-heading">Gráfico</div>');
	var divBody = $('<div class="panel-body">');
	var divChart = $('<div class="flot-chart">');
	var divContent = $('<div class="flot-chart-content" id="flot-bar-chart-' + count + '"></div>');
	divCol.append(divPanel);
	divPanel.append(divHeading);
	divPanel.append(divBody);
	divBody.append(divChart);
	divChart.append(divContent);
	containerGrafico.append(divCol);

	drawBarChart(result, count);
	count++;
	// });
}

function drawBarChart(dataJson, count) {
	var dtoValues = [];
	for (var i = 0; i < dataJson.length; i++) {
		rendaFixa = new Object()
		rendaFixa.y = dataJson[i].dataFormatada;
		rendaFixa.x = dataJson[i].valorTotalFormatado;
		dtoValues.push(rendaFixa);
	}

	Morris.Bar({
		element : "flot-bar-chart-" + count,
		data : dtoValues,
		xkey : 'y',
		ykeys : [
			'x'
		],
		labels : [
				'R$ ',
		],
		hideHover : 'auto',
		resize : true
	});
}