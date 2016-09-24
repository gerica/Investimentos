$(document).ready(function() {
	$('#dataTables-feeds').DataTable({
		responsive : true
	});

});

function atualializarLido(objeto, id) {
	var url = 'feed/atualizarFeedLidoNaoLido/' + id + '/true';
	var tr = $(objeto).parents('tr');
	var span = $(tr).children('td').children('span:last');

	if ($(tr).hasClass('gradeX')) {
		$(tr).removeClass('gradeX');
		$(tr).addClass('success');
	}

	if ($(span).hasClass('fa fa-square-o')) {
		$(span).removeClass('fa fa-square-o');
		$(span).addClass('fa fa-check-square');
	}

	if (id != undefined) {
		$.ajax({
			contentType : 'application/json; charset=utf-8',
			dataType : 'json',
			type : 'get',
			url : url,
			success : function(response) {
			},
			error : function(e) {
				ajaxResponseFail(e);
			}
		});
	}

}

function alterarCssTR(tr) {
	var isLido;
	if ($(tr).hasClass('gradeX')) {
		$(tr).removeClass('gradeX');
		$(tr).addClass('success');
		isLido = true;

	} else {
		$(tr).removeClass('success');
		$(tr).addClass('gradeX');
		isLido = false;
	}
	return isLido;
}

function alterarCssCheck(span) {
	if ($(span).hasClass('fa fa-square-o')) {
		$(span).removeClass('fa fa-square-o');
		$(span).addClass('fa fa-check-square');
	} else {
		$(span).removeClass('fa fa-check-square');
		$(span).addClass('fa fa-square-o');
	}
}

function atualizarLidoNaoLido(objeto, id) {
	var span = objeto;
	var tr = $(objeto).parents('tr');

	alterarCssCheck(span);
	var isLido = alterarCssTR(tr);
	var url = 'feed/atualizarFeedLidoNaoLido/' + id + '/' + isLido;

	if (id != undefined) {
		$.ajax({
			contentType : 'application/json; charset=utf-8',
			dataType : 'json',
			type : 'get',
			url : url,
			success : function(response) {
			},
			error : function(e) {
				ajaxResponseFail(e);
			}
		});
	}
}

function adicionarRemoverFavorito(objeto, id) {
	var span = objeto;
	var onOff;

	if ($(span).hasClass('glyphicon glyphicon-star-empty')) {
		$(span).removeClass('glyphicon glyphicon-star-empty');
		$(span).addClass('glyphicon glyphicon-star');
		onOff = true;
	} else {
		$(span).removeClass('glyphicon glyphicon-star');
		$(span).addClass('glyphicon glyphicon-star-empty');
		onOff = false;
	}
	var url = 'feed/adicionarRemoverFavoritos/' + id + '/' + onOff;

	if (id != undefined) {
		$.ajax({
			contentType : 'application/json; charset=utf-8',
			dataType : 'json',
			type : 'get',
			url : url,
			success : function(response) {
			},
			error : function(e) {
				ajaxResponseFail(e);
			}
		});
	}
}
