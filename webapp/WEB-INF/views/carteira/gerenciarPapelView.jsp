<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- CSS -->
<link rel="stylesheet" href="<c:url value="/resources/css/jquery-ui.min.css" />" rel="stylesheet" media="screen, projection">
<!-- SCRIPT DA PAGINA -->
<script type="text/javascript" src="<c:url value="/resources/js/carteira/gerenciarPapelView.js" />"></script>
<!-- <div class="one columns">&nbsp;</div> -->
<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">Empresa: ${empresa.nome}, Papel: ${papel.nome }</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>

<div class="panel panel-default">
	<div class="panel-body">
		<!-- Nav tabs -->
		<ul class="nav nav-tabs">
			<li class="active"><a href="#home" data-toggle="tab">Empresa</a></li>
			<li><a href="#noticia" data-toggle="tab">Notícia</a></li>
			<li><a href="#fundamentalista" onclick="selectTab('fundamentalista/analiseFundamentalistaView/${papel.id }');" data-toggle="tab">Analise
					Fundamentalista</a></li>
			<li><a href="#grafica" data-toggle="tab">Analise Gráfica</a></li>
		</ul>
		<!-- Tab panes -->
		<div class="tab-content">
			<div class="tab-pane fade in active" id="home">
				<div class="row">
					<div class="col-lg-12">
						<div class="panel panel-default">
							<div class="panel-body">
								<div class="table-responsive">
									<form:form commandName="gerenciarPapelForm" id="formPage">
										<form:input path="id" type="hidden" value="${gerenciarPapelForm.id}" />
										<div class="alert alert-success alert-dismissable"></div>
										<div class="alert alert-danger alert-dismissable"></div>
										<table class="table table-bordered table-striped" id="tabelaEmpresa">
											<tbody>
												<tr>
													<th width="15%">Empresa</th>
													<td class="text-muted">${gerenciarPapelForm.nome }</td>
												</tr>
												<tr>
													<th>Setor</th>
													<td class="text-muted">${gerenciarPapelForm.setor }</td>
												</tr>
												<tr>
													<th>Subsetor</th>
													<td class="text-muted">${gerenciarPapelForm.subsetor }</td>
												</tr>
											</tbody>
										</table>
										<!-- botões -->
										<button type="button" class="btn btn-primary" name="/Investimentos/site/consolidar" value="Consolidar" onclick="doAjaxPost(this)">Consolidar</button>
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
			<div class="tab-pane fade" id="noticia">
				<p>he blessing of the Core Team, the Sass Team took this opportunity to switch to a more vanilla 3-digit SemVer numbering scheme. Thus,
					bootstrap-sass v3.3.2+1 was re-released as bootstrap-sass v3.3.3, with only the version number changed compared to v3.3.2+1.</p>
			</div>
			<div class="tab-pane fade" id="fundamentalista">
				<div class="row">
					<div class="col-lg-12">
						<div class="panel panel-default">
							<div class="panel-body" id="conteudoFundamenalistaTabela1"></div>
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