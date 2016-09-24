<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- CSS -->
<link rel="stylesheet" href="<c:url value="/resources/css/jquery-ui.min.css" />" rel="stylesheet" media="screen, projection">

<!-- Flot Charts JavaScript -->
<script src="<c:url value="/resources/startbootstrap-sb-admin-2-1.0.6/bower_components/flot/excanvas.min.js" />"></script>
<script src="<c:url value="/resources/startbootstrap-sb-admin-2-1.0.6/bower_components/flot/jquery.flot.js" />"></script>
<script src="<c:url value="/resources/startbootstrap-sb-admin-2-1.0.6/bower_components/flot/jquery.flot.pie.js" />"></script>
<script src="<c:url value="/resources/startbootstrap-sb-admin-2-1.0.6/bower_components/flot/jquery.flot.resize.js" />"></script>
<script src="<c:url value="/resources/startbootstrap-sb-admin-2-1.0.6/bower_components/flot/jquery.flot.time.js" />"></script>
<script src="<c:url value="/resources/startbootstrap-sb-admin-2-1.0.6/bower_components/flot/jquery.flot.symbol.js" />"></script>
<script src="<c:url value="/resources/startbootstrap-sb-admin-2-1.0.6/bower_components/flot.tooltip/js/jquery.flot.tooltip.min.js" />"></script>
<script src="<c:url value="/resources/startbootstrap-sb-admin-2-1.0.6/js/jquery.flot.candlestick.js" />"></script>
<script src="<c:url value="/resources/startbootstrap-sb-admin-2-1.0.6/js/JUMFlot.min.js" />"></script>
<%-- <script src="<c:url value="/resources/startbootstrap-sb-admin-2-1.0.6/js/jquery.numberformatter-1.2.3.min.js" />"></script> --%>

<!-- Morris Charts CSS -->
<%-- <link href="<c:url value="/resources/startbootstrap-sb-admin-2-1.0.6/bower_components/morrisjs/morris.css" />" rel="stylesheet"> --%>

<!-- Morris Charts JavaScript -->
<%-- <script src="<c:url value="/resources/startbootstrap-sb-admin-2-1.0.6/bower_components/raphael/raphael-min.js" />"></script> --%>
<%-- <script src="<c:url value="/resources/startbootstrap-sb-admin-2-1.0.6/bower_components/morrisjs/morris.min.js" />"></script> --%>


<!-- SCRIPT DA PAGINA -->
<script type="text/javascript" src="<c:url value="/resources/js/chart/chartRendaVariavel.js" />"></script>

<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">Graficos</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>

<div class="panel panel-default">
	<div class="panel-heading">Grafico</div>
	<!-- /.panel-heading -->
	<div class="panel-body">
		<!-- Nav tabs -->
		<ul class="nav nav-tabs">
			<li class="active"><a href="#home" data-toggle="tab">Gráfico</a></li>
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

								<form:form commandName="graficoRendaVariavelForm" id="formPage">
									<div class="row">
										<div class="alert alert-success alert-dismissable"></div>
										<div class="alert alert-danger alert-dismissable"></div>
									</div>

									<div class="row">
										<div class="col-lg-4">
											<div class="form-group">
												<form:label path="empresa.id" for="idComboEmpresa">Empresa:</form:label>
												<form:select path="empresa.id" cssClass="form-control" id="idComboEmpresa">
													<form:option value="0" label="--- Todos ---" />
													<form:options items="${empresaLista}" />
												</form:select>
											</div>
										</div>
										<div class="col-lg-4">
											<div class="form-group">
												<form:label path="papel.id" for="idComboPapel">Papel:</form:label>
												<form:select path="papel.id" cssClass="form-control" id="idComboPapel">
													<form:option value="0" label="--- Todos ---" />
												</form:select>
											</div>
										</div>
										<div class="col-lg-4">
											<div class="form-group">
												<form:label path="chartType" for="field1">Tipo:</form:label>
												<form:select path="chartType" cssClass="form-control">
													<form:option value="" label="--- Selecione ---" />
													<form:options items="${tiposGraficosLista}" />
												</form:select>
											</div>
										</div>
									</div>
									<!-- gravar -->
									<button type="button" class="btn btn-primary" name="graficos/pesquisar" value="Gravar" onclick="doAjaxPost(this)">Pesquisar</button>
									<!-- limpar -->
									<!-- 									<button type="button" class="btn btn-primary" name="graficos/limpar" value="Limpar" onclick="doAjaxPost(this)">Limpar</button> -->

								</form:form>
							</div>
						</div>
					</div>
				</div>
				<div class="row" id="containerGraficos"></div>
			</div>
			<div class="tab-pane fade" id="historico">
				<p>he blessing of the Core Team, the Sass Team took this opportunity to switch to a more vanilla 3-digit SemVer numbering scheme. Thus,
					bootstrap-sass v3.3.2+1 was re-released as bootstrap-sass v3.3.3, with only the version number changed compared to v3.3.2+1.</p>
			</div>
		</div>