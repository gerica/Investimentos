$(document).ready(function() { // document and jquery ready
//	gerenciarFeeds();
	loadTopCincoFeeds('feed/topCincoFeeds.json');
});

function loadTopCincoFeeds(url) {
	$.ajax({
		contentType : 'application/json; charset=utf-8',
		dataType : "json",
		type : 'get',
		url : url,
		success : function(response) {
			montaLista(response);
		},
		error : function(e) {
			ajaxResponseFail(e);
		}
	});
}

function gerenciarFeeds() {
	$.ajax({
		contentType : 'application/json; charset=utf-8',
		dataType : "json",
		type : 'post',
		url : 'feed/gerenciarFeeds.json',
		success : function(response) {
		},
		error : function(e) {
			ajaxResponseFail(e);
		}
	});
}

function montaLista(response) {
	var result = response.result;
	if (response.result != undefined && response.result.length > 0) {
		var $container = $('.dropdown-messages');
		$container.empty();
		for (var i = 0; i < result.length; i++) {
			var li = $('<li title=' + result[i].nomeFeed + '>');
			var a = $('<a href=' + result[i].link + ' target=\'blank\' onclick="atualializarLido(' + result[i].id + ');">');
			var div = $('<div>');
			var divConteudo = $('<div>');
			var strong = $('<strong>');
			var span = $('<span class="pull-right text-muted">');
			var em = $('<em>');
			var li_dividir = $('<li class="divider"></li>');
			li.append(a);
			a.append(div);
			a.append(divConteudo);
			div.append(strong);
			strong.text(result[i].nomeFeed);

			div.append(span);
			span.append(em);
			em.text(result[i].data);
			divConteudo.append(result[i].title);
			$container.append(li);
			$container.append(li_dividir);
		}
		var liTodos = $('<li>');
		var aTodos = $('<a class=\'text-center\' href=\'#\' onclick=\"linkIn(\'feed\/verTodosRss\');">');
		var strongTodos = $('<strong>');
		var iTodos = $('<i class=\'fa fa-angle-right\'>');
		var li_dividir = $('<li class="divider"></li>');

		liTodos.append(aTodos);
		aTodos.append(strongTodos);
		strongTodos.append('Ler Todas Mensagens');
		aTodos.append(iTodos);
		$container.append(liTodos);

		var spanNaoLido = $('#qtdNaoLido');
		spanNaoLido.empty();
		spanNaoLido.append('(' + response.message + ')');
	}
}

function atualializarLido(id) {
	var url = 'feed/atualizarFeedLidoNaoLido/' + id + '/true';

	if (id != undefined) {
		$.ajax({
			contentType : 'application/json; charset=utf-8',
			dataType : 'json',
			type : 'get',
			url : url,
			success : function(response) {
				loadTopCincoFeeds('feed/topCincoFeeds.json');
			},
			error : function(e) {
				ajaxResponseFail(e);
			}
		});
	}

}
