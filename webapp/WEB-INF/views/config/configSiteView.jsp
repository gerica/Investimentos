<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- CSS -->
<link rel="stylesheet" href="<c:url value="/resources/css/jquery-ui.min.css" />" rel="stylesheet" media="screen, projection">
<!-- SCRIPT DA PAGINA -->
<script type="text/javascript" src="<c:url value="/resources/js/config/configSiteView.js" />"></script>
<!-- <div class="one columns">&nbsp;</div> -->
<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">Configuração do Usuário</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>

<div class="panel panel-default">
	<div class="panel-heading">Usuário</div>
	<!-- /.panel-heading -->
	<div class="panel-body">
		<!-- Nav tabs -->
		<ul class="nav nav-tabs">
			<li class="active"><a href="#home" data-toggle="tab">Cadastro</a></li>
			<li><a href="#feeds" data-toggle="tab" onclick="selectTab('feed/crudFeedPage.json');">Feed</a></li>
			<li><a href="#configuracao" data-toggle="tab" onclick="selectTab('config/configuracaoCotacao.json');">Configurações</a></li>
		</ul>
		<!-- Tab panes -->
		<div class="tab-content">
			<div class="tab-pane fade in active" id="home">
				<div class="row">
					<div class="col-lg-12">
						<div class="panel panel-default">
							<div class="panel-body">
								<form:form commandName="configSiteForm" id="formPage">
									<form:input path="id" type="hidden" value="${configSiteForm.id}" />
									<div class="row">
										<div class="alert alert-success alert-dismissable"></div>
										<div class="alert alert-danger alert-dismissable"></div>
									</div>
									<div class="row">
										<div class="col-lg-2">
											<div class="form-group">
												<form:label path="nome">Nome:</form:label>
												<form:input path="nome" cssErrorClass="error" cssClass="form-control" placeholder="Informe o nome." />
											</div>
										</div>
										<div class="col-lg-2">
											<div class="form-group">
												<form:label path="Senha">Senha:</form:label>
												<form:password path="senha" cssErrorClass="error" placeholder="Informe a senha." cssClass="form-control" />
											</div>
										</div>
									</div>
									<!-- gravar -->
									<button type="button" class="btn btn-primary" name="gravarRendaFixa" value="Gravar" onclick="doAjaxPost(this)">Gravar</button>
								</form:form>
							</div>
							<!-- /.row (nested) -->
						</div>
						<!-- /.panel-body -->
					</div>
					<!-- /panel-default -->
				</div>
			</div>
			<div class="tab-pane fade" id="feeds">
				<div class="row">
					<div class="col-lg-12">
						<div class="panel panel-default">
							<div class="panel-body" id="formConteudo"></div>
							<!-- /.row (nested) -->
						</div>
						<!-- /.panel-body -->
					</div>
					<!-- /panel-default -->
				</div>
				<div class="row">
					<div class="col-lg-12">
						<div class="panel panel-default">
							<div class="panel-heading">
								<i class="fa fa-table fa-fw"></i> Sites Feeds
								<div class="pull-right">
									<div class="btn-group">
										<button type="button" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown">
											Ação <span class="caret"></span>
										</button>
										<ul class="dropdown-menu pull-right" role="menu">
											<li><a href="#" onclick="doAjaxPostWithoutPage('feed/gerenciarFeeds.json')">Gerenciar Feeds</a></li>
										</ul>
									</div>
								</div>
							</div>
							<div class="panel-body">
								<div id="tabConteudo"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="tab-pane fade" id="configuracao">
				<div class="row">
					<div class="col-lg-12">
						<div class="panel panel-default">
							<div class="panel-body" id="formConteudoConfig"></div>
							<!-- /.row (nested) -->
						</div>
						<!-- /.panel-body -->
					</div>
					<!-- /panel-default -->
				</div>
			</div>
		</div>
	</div>