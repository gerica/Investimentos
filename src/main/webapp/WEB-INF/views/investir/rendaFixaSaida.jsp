<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- CSS -->
<link rel="stylesheet" href="<c:url value="/resources/css/jquery-ui.min.css" />" rel="stylesheet" media="screen, projection">
<!-- jquery mask -->
<script type="text/javascript" src="<c:url value="/resources/js/jquery.maskMoney.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/angular/comumAngular.js" />"></script>
<!-- SCRIPT DA PAGINA -->
<script type="text/javascript" src="<c:url value="/resources/js/investir/rendaFixaSaida.js" />"></script>
<!-- <div class="one columns">&nbsp;</div> -->
<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">Diário de Operações (Renda Fixa)</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>
<div class="panel panel-default">
	<div class="panel-heading">Renda Fixa</div>
	<!-- /.panel-heading -->
	<div class="panel-body">
		<!-- Nav tabs -->
		<ul class="nav nav-tabs">
			<li class="active"><a href="#home" data-toggle="tab">Cadastro</a></li>
			<!-- 			onclick="selectTab('historicoOperacao');" -->
		</ul>
		<!-- Tab panes -->
		<div class="tab-content">
			<div class="tab-pane fade in active" id="home">
				<div class="row">
					<div class="col-lg-12">
						<div class="panel panel-default">
							<div class="panel-body">
								<form:form commandName="rendaFixaForm" id="formPage">
									<form:input path="id" type="hidden" value="${id}" />
									<div class="row">
										<div class="alert alert-success alert-dismissable"></div>
										<div class="alert alert-danger alert-dismissable"></div>
									</div>
									<div class="row">
										<div class="col-lg-6">
											<div class="form-group">
												<form:label path="dataSaida">Data Saída:</form:label>
												<form:input path="dataSaida" cssClass="form-control" placeholder="Data saida" cssErrorClass="error" type="date" />
											</div>
										</div>
										<div class="col-lg-6">
											<div class="form-group">
												<form:label path="resgate">Resgate:</form:label>
												<form:input path="resgate" cssClass="form-control dinheiro" placeholder="Resgate." />
											</div>
										</div>
									</div>

									<!-- gravar -->
									<button type="button" class="btn btn-primary" name="gravarRendaFixaSaida" value="Gravar" onclick="doAjaxPost(this)">Gravar</button>
									<!-- limpar -->
									<button type="button" class="btn btn-primary" name="limparRendaFixa" value="Limpar" onclick="doAjaxPost(this)">Limpar</button>
								</form:form>
							</div>
							<!-- /.row (nested) -->
						</div>
						<!-- /.panel-body -->
					</div>
					<!-- /panel-default -->
				</div>
			</div>
		</div>
	</div>
</div>
