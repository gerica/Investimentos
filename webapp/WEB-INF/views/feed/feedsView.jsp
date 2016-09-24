<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- CSS -->
<!-- DataTables CSS -->
<link rel="stylesheet"
	href="<c:url value="/resources/startbootstrap-sb-admin-2-1.0.6/bower_components/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.css" />"
	rel="stylesheet"
>
<!-- DataTables Responsive CSS -->
<link rel="stylesheet"
	href="<c:url value="/resources/startbootstrap-sb-admin-2-1.0.6/bower_components/datatables-responsive/css/dataTables.responsive.css" />"
	rel="stylesheet"
>

<!-- DataTables JavaScript -->
<script type="text/javascript"
	src="<c:url value="/resources/startbootstrap-sb-admin-2-1.0.6/bower_components/datatables/media/js/jquery.dataTables.min.js" />"
></script>
<script type="text/javascript"
	src="<c:url value="/resources/startbootstrap-sb-admin-2-1.0.6/bower_components/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.min.js" />"
></script>
<!-- SCRIPT DA PAGINA -->
<script type="text/javascript" src="<c:url value="/resources/js/feed/feedsView.js" />"></script>
<!-- <div class="one columns">&nbsp;</div> -->
<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">Notícias</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>

<div class="panel panel-default">
	<div class="panel-heading">Feeds</div>
	<!-- /.panel-heading -->
	<div class="panel-body">
		<!-- Nav tabs -->
		<ul class="nav nav-tabs">
			<li class="active"><a href="#home" data-toggle="tab">RSS</a></li>
		</ul>
		<!-- Tab panes -->
		<div class="tab-content">
			<div class="tab-pane fade in active" id="home">
				<div class="row">
					<div class="col-lg-12">
						<div class="panel panel-default">
							<div class="panel-heading">
								<i class="fa fa-table fa-fw"></i> Feeds
								<div class="pull-right">
									<div class="btn-group">
										<button type="button" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown">
											Ação <span class="caret"></span>
										</button>
										<ul class="dropdown-menu pull-right" role="menu">
											<li><a href="#" onclick="doAjaxPostWithoutPage('feed/lerTodosFeeds.json')">Ler todos</a></li>
										</ul>
									</div>
								</div>
							</div>
							<div class="panel-body">
								<div class="dataTable_wrapper">
									<table class="table table-striped table-bordered table-hover" id="dataTables-feeds">
										<thead>
											<tr>
												<th width="3%"></th>
												<th width="9%">Data</th>
												<th width="12%">Feed</th>
												<th width="30%">Título</th>
												<th width="45%">Descricão</th>
												<th width="1%"></th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="value" items="${listFeeds}">
												<tr class="${value.getCssLido()}" title="${value.nomeFeed}">
													<td><span class="${value.getCssFavorito()}" onclick="adicionarRemoverFavorito(this,${value.id})" title="Adicionar/Remover Favoritos"></span>
														<span class="${value.getCssLidoCheck()}" onclick="atualizarLidoNaoLido(this,${value.id})" title="Lido/Não Lido"></span></td>
													<td onclick="atualializarLido(this,${value.id});">${value.dataHora }</td>
													<td onclick="atualializarLido(this,${value.id});">${value.nomeFeed }</td>
													<td onclick="atualializarLido(this,${value.id});">${value.title }</td>
													<td onclick="atualializarLido(this,${value.id});">${value.description }</td>
													<td onclick="atualializarLido(this,${value.id});"><a target="blank" href="${value.link }">
															<button type="button" class="btn btn-success btn-circle">
																<i class="fa fa-link"></i>
															</button>
													</a></td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
								<!-- /.table-responsive -->
							</div>
							<!-- /.panel-body -->
						</div>
						<!-- /.panel -->
					</div>
					<!-- /.col-lg-12 -->
				</div>
				<!-- /.row -->
			</div>
		</div>
	</div>
</div>