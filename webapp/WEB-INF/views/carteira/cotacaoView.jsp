<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- jQuery -->
<%-- <script src="<c:url value="/resources/startbootstrap-sb-admin-2-1.0.6/bower_components/jquery/dist/jquery.min.js" />"></script> --%>
<%-- <script src="<c:url value="/resources/js/jquery.form.js" />"></script> --%>
<!-- jquery mask -->
<script type="text/javascript" src="<c:url value="/resources/js/jquery.maskMoney.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/angular/comumAngular.js" />"></script>

<script src="<c:url value="/resources/js/angular/cotacaoView.js" />"></script>
<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">Empresa: ${papel.empresa.nome}</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">Cadatrar Cotação</div>
			<div class="panel-body">
				<form:form commandName="cotacaoForm" id="formPage" enctype="multipart/form-data">
					<form:input path="papel.id" type="hidden" value="${papel.id }" />
					<div class="row">
						<div class="alert alert-success alert-dismissable"></div>
						<div class="alert alert-danger alert-dismissable"></div>
						<div class="col-lg-3">
							<div class="form-group">
								<form:label path="data" for="field1">Data:</form:label>
								<form:input path="data" cssClass="form-control" type="date" />
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-3">
							<div class="form-group">
								<form:label path="abertura">Abertura:</form:label>
								<form:input path="abertura" cssClass="form-control dinheiro" placeholder="Abertura." />
							</div>
						</div>
						<div class="col-lg-3">
							<div class="form-group">
								<form:label path="fechamento">Fechamento:</form:label>
								<form:input path="fechamento" cssClass="form-control dinheiro" placeholder="Fechamento." />
							</div>
						</div>
						<div class="col-lg-3">
							<div class="form-group">
								<form:label path="maxima">Máxima:</form:label>
								<form:input path="maxima" cssClass="form-control dinheiro" placeholder="Máxima" />
							</div>
						</div>
						<div class="col-lg-3">
							<div class="form-group">
								<form:label path="minima">Mínima:</form:label>
								<form:input path="minima" cssClass="form-control dinheiro" placeholder="Mínima" />
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-12">
							<div class="form-group">
								<label>Arquivo:</label> <input placeholder="Informe o arquivo." type="file" id="file" name="file" />
							</div>
						</div>
					</div>
					<!-- gravar -->
					<button type="button" class="btn btn-primary" name="/Investimentos/site/gravarCotacao" value="Gravar" onclick="doAjaxPost(this)">Gravar</button>
					<!-- upload -->
					<button type="button" class="btn btn-primary" name="/Investimentos/site/uploadPapel" value="Upload" onclick="uploadFormData(this)">Upload</button>
					<!-- atualizar pela BMF -->
					<button type="button" class="btn btn-primary" name="/Investimentos/site/atualizarBMF" value="AtualizarBMF" onclick="doAjaxPost(this)">Atualizar
						Pela BMF</button>
					<!-- limpar -->
					<button type="button" class="btn btn-primary" name="/Investimentos/site/limparCotacao" value="Limpar" onclick="doAjaxPost(this)">Limpar</button>
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				</form:form>
			</div>
		</div>
	</div>
	<!-- /.row (nested) -->
</div>
<!-- /.panel-body -->
</div>
<!-- /panel-default -->
</div>
</div>
<div class="row" id="divTabela">
	<!-- style="overflow-y:auto;" -->
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">Lista de Cotações</div>
			<!-- /.panel-heading -->
			<div class="panel-body">
				<div class="table-responsive">
					<table class="table table-striped table-bordered table-hover">
						<thead>
							<tr>
								<th>Data</th>
								<th>Abertura</th>
								<th>Fechamento</th>
								<th>Máxima</th>
								<th>Mínima</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="listValue" items="${lists}">
								<tr>
									<td><fmt:formatDate pattern="dd-MM-yyyy" value="${listValue.data}" /></td>
									<td><fmt:formatNumber value="${listValue.abertura}" type="currency" pattern="R$ #,##0.00" /></td>
									<td><fmt:formatNumber value="${listValue.fechamento}" type="currency" pattern="R$ #,##0.00" /></td>
									<td><fmt:formatNumber value="${listValue.maxima}" type="currency" pattern="R$ #,##0.00" /></td>
									<td><fmt:formatNumber value="${listValue.minima}" type="currency" pattern="R$ #,##0.00" /></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>