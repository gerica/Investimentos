<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- JQUERY -->
<%-- <script src="<c:url value="/resources/startbootstrap-sb-admin-2-1.0.6/bower_components/jquery/dist/jquery.min.js" />"></script> --%>
<!-- SCRIPT DA PAGINA -->
<script type="text/javascript" src="<c:url value="/resources/js/investir/operacaoSaida.js" />"></script>
<!-- jquery mask -->
<script type="text/javascript" src="<c:url value="/resources/js/jquery.maskMoney.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/angular/comumAngular.js" />"></script>
<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">Diário de Operações (Renda Variável)</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">Cadatrar Operação</div>
			<div class="panel-body">
				<form:form commandName="operacaoSaidaForm" id="formPage">
					<form:input path="operacaoEntrada.id" type="hidden" value="${operacaoEntrada.id }" />
					<div class="row">
						<div class="alert alert-success alert-dismissable"></div>
						<div class="alert alert-danger alert-dismissable"></div>
					</div>
					<div class="row">
						<div class="col-lg-3">
							<div class="form-group">
								<form:label path="data">Data:</form:label>
								<form:input path="data" cssClass="form-control" type="date" />
							</div>
						</div>
						<div class="col-lg-3">
							<div class="form-group">
								<form:label path="precoUnitario">Preço Unitário:</form:label>
								<form:input path="precoUnitario" cssClass="form-control dinheiro" placeholder="Preço Unitário." />
							</div>
						</div>
						<div class="col-lg-3">
							<div class="form-group">
								<form:label path="quantidade">Quantidade:</form:label>
								<form:input path="quantidade" cssClass="form-control" placeholder="Quantidade." />
							</div>
						</div>
						<div class="col-lg-3">
							<div class="form-group">
								<form:label path="despesa">Despesa:</form:label>
								<form:input path="despesa" cssClass="form-control dinheiro" placeholder="Despesa." />
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-6">
							<div class="form-group">
								<form:label path="observacao">Observação:</form:label>
								<form:textarea path="observacao" cssClass="form-control" placeholder="Informe o motivo da operação (sentimentos,observações)." rows="3"
									cols="50"
								/>
							</div>
						</div>
					</div>
					<div id="botoesHideAfterSuccess">
						<!-- gravar -->
						<button type="button" class="btn btn-primary" name="gravarOperacaoSaida" value="Gravar" onclick="doAjaxPost(this)">Gravar</button>
						<!-- limpar -->
						<button type="button" class="btn btn-primary" name="limparOperacaoSaida" value="Limpar" onclick="doAjaxPost(this)">Limpar</button>
					</div>
				</form:form>
			</div>
			<!-- /.row (nested) -->
		</div>
		<!-- /.panel-body -->
	</div>
	<!-- /panel-default -->
</div>
