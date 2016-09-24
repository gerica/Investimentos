<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- CSS -->
<link rel="stylesheet" href="<c:url value="/resources/css/jquery-ui.min.css" />" rel="stylesheet" media="screen, projection">
<!-- SCRIPT DA PAGINA -->
<script type="text/javascript" src="<c:url value="/resources/js/geral/notaView.js" />"></script>
<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">Notificações</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>

<div class="panel panel-default">
	<div class="panel-heading">Nota</div>
	<!-- /.panel-heading -->
	<div class="panel-body">
		<!-- Nav tabs -->
		<ul class="nav nav-tabs">
			<li class="active"><a href="#home" data-toggle="tab">Cadastro</a></li>
			<li><a href="#historico" data-toggle="tab">Histórico</a></li>
			<!-- 			onclick="selectTab('historicoOperacao');" -->
		</ul>
		<!-- Tab panes -->
		<div class="tab-content">
			<div class="tab-pane fade in active" id="home">
				<div class="row">
					<div class="col-lg-12">
						<div class="panel panel-default">
							<div class="panel-body">
								<form:form commandName="notaForm" id="formPage">
									<c:if test="${not empty notaForm.id}">
										<form:input path="id" type="hidden" value="${notaForm.id}" />
									</c:if>

									<div class="row">
										<div class="alert alert-success alert-dismissable"></div>
										<div class="alert alert-danger alert-dismissable"></div>
									</div>
									<div class="row">
										<div class="col-lg-4">
											<div class="form-group">
												<form:label path="data">Data:</form:label>
												<form:input path="data" cssClass="form-control" type="date" />
											</div>
										</div>
										<div class="col-lg-4">
											<div class="form-group">
												<form:label path="titulo">Titulo:</form:label>
												<form:input path="titulo" cssClass="form-control" placeholder="Titulo." />
											</div>
										</div>
										<div class="col-lg-4">
											<div class="form-group">
												<form:label path="sumario">Sumário:</form:label>
												<form:input path="sumario" cssClass="form-control" placeholder="Sumário." />
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-lg-12">
											<div class="form-group">
												<form:label path="descricao">Descrição:</form:label>
												<form:textarea path="descricao" cssClass="form-control" placeholder="Informe descrição." rows="3" cols="50" />
											</div>
										</div>
									</div>

									<!-- gravar -->
									<button type="button" class="btn btn-primary" name="gravarNota" value="Gravar" onclick="doAjaxPost(this)">Gravar</button>
									<!-- limpar -->
									<button type="button" class="btn btn-primary" name="limparNota" value="Limpar" onclick="doAjaxPost(this)">Limpar</button>
							</div>
							</form:form>
						</div>
						<!-- /.row (nested) -->
					</div>
					<!-- /.panel-body -->
				</div>
				<!-- /panel-default -->
			</div>
			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-heading">Lista de Operações</div>
						<!-- /.panel-heading -->
						<div class="panel-body">
							<div class="table-responsive">
								<table class="table table-striped table-bordered table-hover">
									<thead>
										<tr>
											<th>Ações</th>
											<th>Data</th>
											<th>Titulo</th>
											<th>Sumário</th>
											<th>Descriçao</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="listValue" items="${lists}">
											<tr>
												<td><a href="#" onclick="linkIn('preAlterarNota/${listValue.id}');">
														<p class="fa fa-pencil"></p>
												</a><a href="#" onclick="showConfirmRemove('excluirNota/${listValue.id}')">
														<p class="glyphicon glyphicon-remove"></p>
												</a></td>
												<td><fmt:formatDate pattern="dd-MM-yyyy" value="${listValue.data}" /></td>
												<td>${listValue.titulo}</td>
												<td>${listValue.sumario}</td>
												<td>${listValue.descricao}</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</div>