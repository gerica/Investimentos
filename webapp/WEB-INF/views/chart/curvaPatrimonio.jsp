<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- CSS -->
<link rel="stylesheet" href="<c:url value="/resources/css/jquery-ui.min.css" />" rel="stylesheet" media="screen, projection">
<!-- Morris Charts CSS -->
<link href="<c:url value="/resources/startbootstrap-sb-admin-2-1.0.6/bower_components/morrisjs/morris.css" />" rel="stylesheet">

<!-- Morris Charts JavaScript -->
<script src="<c:url value="/resources/startbootstrap-sb-admin-2-1.0.6/bower_components/raphael/raphael-min.js" />"></script>
<script src="<c:url value="/resources/startbootstrap-sb-admin-2-1.0.6/bower_components/morrisjs/morris.min.js" />"></script>



<!-- SCRIPT DA PAGINA -->
<script type="text/javascript" src="<c:url value="/resources/js/chart/curvaPatrimonio.js" />"></script>

<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">Curva de Património</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>

<div class="panel panel-default">
	<div class="panel-heading">Curva de Património</div>
	<!-- /.panel-heading -->
	<div class="panel-body">
		<!-- Nav tabs -->
		<ul class="nav nav-tabs">
			<li class="active"><a href="#home" data-toggle="tab">Curva de Património</a></li>
			<li><a href="#historico" data-toggle="tab">Histórico</a></li>
		</ul>
		<!-- Tab panes -->
		<div class="tab-content">
			<div class="tab-pane fade in active" id="home">
				<div class="row">
					<div class="col-lg-7">
						<div class="panel panel-default">
							<div class="panel-body">
								<div class="table-responsive"></div>
							</div>
						</div>
					</div>
<!-- 				</div> -->
<!-- 				<div class="row"> -->
					<div class="col-lg-5">
						<div id="containerGraficos"></div>
					</div>
				</div>
			</div>
			<div class="tab-pane fade" id="historico">
				<p>he blessing of the Core Team, the Sass Team took this opportunity to switch to a more vanilla 3-digit SemVer numbering scheme. Thus,
					bootstrap-sass v3.3.2+1 was re-released as bootstrap-sass v3.3.3, with only the version number changed compared to v3.3.2+1.</p>
			</div>
		</div>