<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<title><tiles:insertAttribute name="title" /></title>
<!-- Bootstrap Core CSS -->
<link href="<c:url value="/resources/startbootstrap-sb-admin-2-1.0.6/bower_components/bootstrap/dist/css/bootstrap.min.css" />" rel="stylesheet">
<!-- MetisMenu CSS -->
<link href="<c:url value="/resources/startbootstrap-sb-admin-2-1.0.6/bower_components/metisMenu/dist/metisMenu.min.css" />" rel="stylesheet">
<!-- Custom CSS -->
<link href="<c:url value="/resources/startbootstrap-sb-admin-2-1.0.6/dist/css/sb-admin-2.css" />" rel="stylesheet">
<!-- Custom Fonts -->
<link href="<c:url value="/resources/startbootstrap-sb-admin-2-1.0.6/bower_components/font-awesome/css/font-awesome.min.css" />" rel="stylesheet"
	type="text/css"
>
<!-- jQuery -->
<script src="<c:url value="/resources/startbootstrap-sb-admin-2-1.0.6/bower_components/jquery/dist/jquery.min.js" />"></script>
<!-- Bootstrap Core JavaScript -->
<script src="<c:url value="/resources/startbootstrap-sb-admin-2-1.0.6/bower_components/bootstrap/dist/js/bootstrap.min.js" />"></script>
<!-- Metis Menu Plugin JavaScript -->
<script src="<c:url value="/resources/startbootstrap-sb-admin-2-1.0.6/bower_components/metisMenu/dist/metisMenu.min.js" />"></script>
<!-- Custom Theme JavaScript -->
<script src="<c:url value="/resources/startbootstrap-sb-admin-2-1.0.6/dist/js/sb-admin-2.js" />"></script>
<!-- Angular JS -->
<script type="text/javascript" src="<c:url value="/resources/js/angular/pk/angular.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/angular/pk/angular-animate.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/angular/pk/angular-sanitize.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/bootstrap-3.3.4-dist/js/ui-bootstrap-tpls-0.12.1.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/angular/comumAngular.js" />"></script>

<!-- jquery mask -->
<script type="text/javascript" src="<c:url value="/resources/js/jquery.maskMoney.js" />"></script>
<%--   <script type="text/javascript" src="<c:url value="/resources/js/jquery-1.11.2.min.js" />"></script> --%>
<%--   <script type="text/javascript" src="<c:url value="/resources/js/angular/pk/angular.min.js" />"></script> --%>
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
	<div class="modal fade" id="carregando" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" >
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">Processando...</h4>
				</div>
				<div class="modal-body">
					<div>
						<div class="progress progress-striped active">
							<div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100" style="width: 40%">
								<span class="sr-only">0% Complete (success)</span>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="modalMensagem" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" >
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">Mensagem...</h4>
				</div>
				<div class="modal-body">
					<div class="alert alert-success alert-dismissable"></div>
					<div class="alert alert-danger alert-dismissable"></div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="modalMostrarConteudo" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" >
		<div class="modal-dialog">
			<div class="panel panel-info">
				<div class="panel-heading">
					<label id="panel-heading"></label>
				</div>
				<div class="panel-body">
					<p id="panel-body"></p>
				</div>
				<div class="panel-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="modalExcluirRegistro" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" >
		<div class="modal-dialog">
			<div class="panel panel-warning">
				<div class="panel-heading">Atencao</div>
				<div class="panel-body">
					<p>Desenha excluir esse registro.</p>
				</div>
				<div class="panel-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>
					<button type="button" class="btn btn-primary" id="excluirRegistro" name="#" onclick="doAjaxGet(this)">Confirmar</button>
				</div>
			</div>
		</div>
	</div>
	<div id="wrapper">
		<!-- HEAD Page -->
		<tiles:insertAttribute name="header" />
		<tiles:insertAttribute name="body" />
	</div>
	<tiles:insertAttribute name="footer" />
</body>
</html>
