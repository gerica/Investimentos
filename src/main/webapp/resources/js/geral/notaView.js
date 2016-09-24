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

	// select all anchors with a class of 'confirm'
	$('a.confirm').click(function(event) {

		// prevent the default action of opening the link
		event.preventDefault()

		// grab the url of the current link
		var url = $(this).attr('href');

		// set the confirmation message
		// var confirm_box = fnOpenNormalDialog();
		var confirm_box = confirm('Deseja inativar esse registro?');

		// if the confirmation is true (user clicks ok) direct the browser to
		// the link's url
		if (confirm_box) {
			window.location = url;
			// uncomment below and remove above if you want the link to open in
			// a new window
			// window.open(url,'_blank');
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
			ajaxResponseSucess(frm, response);
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

				// console.log(r);
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