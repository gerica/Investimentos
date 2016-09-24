<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- jQuery -->
<%-- <script src="<c:url value="/resources/startbootstrap-sb-admin-2-1.0.6/bower_components/jquery/dist/jquery.min.js" />"></script> --%>
<script src="<c:url value="/resources/js/angular/papelView.js" />"></script>
<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">Empresa: ${empresa.nome}</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">Cadatrar Papel</div>
			<div class="panel-body">
				<div class="row">
					<div class="col-lg-12">
						<form:form commandName="papelForm" id="formPage">
							<form:input path="empresa.id" type="hidden" value="${empresa.id }" />
							<c:if test="${not empty papelForm.id}">
								<form:input path="id" type="hidden" value="${papelForm.id }" />
							</c:if>
							<form:errors path="*" element="div" cssClass="error" />
							<div class="alert alert-success alert-dismissable"></div>
							<div class="alert alert-danger alert-dismissable"></div>
							<div class="form-group">
								<form:label path="nome" cssErrorClass="error" for="field1">Papel:</form:label>
								<form:input path="nome" maxlength="255" cssClass="form-control" placeholder="Informe o nome da empresa." />
							</div>
							<!-- gravar -->
							<button type="button" class="btn btn-primary" name="/Investimentos/site/gravarPapel" value="Gravar" onclick="doAjaxPost(this)">Gravar</button>
							<!-- limpar -->
							<button type="button" class="btn btn-primary" name="/Investimentos/site/limparPapel" value="Limpar" onclick="doAjaxPost(this)">Limpar</button>
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
								<th>Papel</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="listValue" items="${lists}">
								<tr>
									<td><a href="#" onclick="linkIn('alterarPapel/${listValue.id}');" title="Alterar">
											<p class="fa fa-pencil"></p>
									</a> <a href="#" onclick="showConfirmRemove('excluirPapel/${listValue.id}');" title="Excluir">
											<p class="glyphicon glyphicon-remove"></p>
									</a> <a href="#" onclick="linkIn('cotacaoView/${listValue.id}');" title="Visualizar Cotações">
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