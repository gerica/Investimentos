<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- jQuery -->
<%-- <script src="<c:url value="/resources/startbootstrap-sb-admin-2-1.0.6/bower_components/jquery/dist/jquery.min.js" />"></script> --%>
<script src="<c:url value="/resources/js/angular/papeisView.js" />"></script>
<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">Papeis</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>
<div class="row">
	<div class="col-lg-12">
		<div class="alert alert-success alert-dismissable"></div>
		<div class="alert alert-danger alert-dismissable"></div>
		<div class="panel panel-default">
			<div class="panel-heading">Papeis</div>
			<!-- /.panel-heading -->
			<div class="panel-body">
				<div class="table-responsive">
					<table class="table table-striped table-bordered table-hover">
						<thead>
							<tr>
								<th WIDTH="10%">Ação</th>
								<th>Papel</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="listValue" items="${lists}">
								<tr>
									<td><a href="analisarCotacaoAltaView/${listValue.id}" id="linkAjaxGet_head_" ${listValue.id}
										title="Analizar Tendencia das Cotações do Papel"
									> <span class="glyphicon glyphicon-export"></span>
									</a> <a href="cotacaoView/${listValue.id}" id="linkAjaxGet_head_" ${listValue.id} title="Visualizar Cotação">
											<p class="fa fa-external-link"></p>
									</a> <a href="gerenciarPapel/${listValue.id}" id="linkAjaxGet_head_papel_" ${listValue.id} title="Gerenciar Papel">
											<p class="fa fa-laptop"></p>
									</a></td>
									<td>${listValue.nome}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">Atualizar papeis Bovespa</div>
			<div class="panel-body">
				<div class="row">
					<div class="col-lg-12">
						<form:form role="form">
							<button type="button" class="btn btn-primary btn-lg btn-block" name="atualizarBMFTodos" onclick="doAjaxPost(this)">Atualizar Todos
								Papeis Pela BMF</button>
						</form:form>
					</div>
				</div>
				<!-- /.row (nested) -->
			</div>
			<!-- /.panel-body -->
		</div>
		<!-- /panel-default -->
	</div>