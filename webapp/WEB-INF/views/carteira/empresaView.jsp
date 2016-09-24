<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- jQuery -->
<%-- <script src="<c:url value="/resources/startbootstrap-sb-admin-2-1.0.6/bower_components/jquery/dist/jquery.min.js" />"></script> --%>
<script src="<c:url value="/resources/js/carteira/empresaView.js" />"></script>
<!-- <div id="page-wrapper"> -->
<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">Empresas</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">Cadatrar Empresa</div>
			<div class="panel-body">
				<div class="row">
					<div class="col-lg-12">
						<form:form commandName="carteiraForm" id="formPage">
							<c:if test="${not empty carteiraForm.id}">
								<form:input path="id" type="hidden" value="${carteiraForm.id}" />
							</c:if>
							<div class="alert alert-success alert-dismissable"></div>
							<div class="alert alert-danger alert-dismissable"></div>
							<div class="form-group">
								<form:label path="nome" cssErrorClass="error">Empresa:</form:label>
								<form:input path="nome" maxlength="255" cssClass="form-control" placeholder="Informe o nome da empresa." />
							</div>
							<!-- gravar -->
							<button type="button" class="btn btn-primary" name="/Investimentos/site/gravar" value="Gravar" onclick="doAjaxPost(this)">Gravar</button>
							<!-- 				limpar -->
							<button type="button" class="btn btn-primary" name="/Investimentos/site/limpar" value="Limpar" onclick="doAjaxPost(this)">Limpar</button>
						</form:form>
					</div>
				</div>
				<!-- /.row (nested) -->
			</div>
			<!-- /.panel-body -->
		</div>
		<!-- /panel-default -->
	</div>
</div>
<c:if test="${not empty lists}">
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">Lista de Empresas</div>
				<!-- /.panel-heading -->
				<div class="panel-body">
					<div class="table-responsive">
						<table class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<th WIDTH="10%">Ação</th>
									<th>Empresa</th>
								</tr>
							</thead>
							<tbody>
								<c:set var="count" value="0" scope="page" />
								<c:forEach var="listValue" items="${lists}">
									<tr>
										<td><a href="#" onclick="linkIn('alterarCateira/${listValue.id}');" title="Alterar">
												<p class="fa fa-pencil"></p>
										</a> <a href="#" onclick="showConfirmRemove('excluirCateira/${listValue.id}');" title="Excluir">
												<p class="glyphicon glyphicon-remove"></p>
										</a> 
<%-- 										<a href="gerenciarEmpresa/${listValue.id}" id="linkAjaxGet_head_empresa_" ${listValue.id} title="Gerenciar Empresa"> --%>
<!-- 												<p class="fa fa-laptop"></p> -->
<!-- 										</a> -->
										 <a href="#" onclick="linkIn('papelView/${listValue.id}');" title="Visualizar Papel">
												<p class="fa fa-external-link"></p>
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
</c:if>




