<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- jQuery -->
<%-- <script src="<c:url value="/resources/startbootstrap-sb-admin-2-1.0.6/bower_components/jquery/dist/jquery.min.js" />"></script> --%>
<%-- <script type="text/javascript" src="<c:url value="/resources/js/angular.js" />"></script> --%>
<!-- SCRIPT DA PAGINA -->
<script type="text/javascript" src="<c:url value="/resources/js/investir/operacaoView.js" />"></script>
<!-- jquery mask -->
<script type="text/javascript" src="<c:url value="/resources/js/jquery.maskMoney.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/angular/comumAngular.js" />"></script>
<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">Diário de Operações</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>

<div class="panel panel-default">
	<div class="panel-heading">Renda Variável</div>
	<!-- /.panel-heading -->
	<div class="panel-body">
		<!-- Nav tabs -->
		<ul class="nav nav-tabs">
			<li class="active"><a href="#home" data-toggle="tab">Cadastro</a></li>
			<li><a href="#historico" data-toggle="tab" onclick="selectTab('historicoOperacao');">Histórico</a></li>
			<!-- 			onclick="selectTab('historicoOperacao');" -->
		</ul>
		<!-- Tab panes -->
		<div class="tab-content">
			<div class="tab-pane fade in active" id="home">
				<div class="row">
					<div class="col-lg-12">
						<div class="panel panel-default">
							<div class="panel-body">
								<form:form commandName="operacaoEntradaForm" id="formPage">
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
												<form:label path="data" for="idComboEmpresa">Empresa:</form:label>
												<form:select path="empresa.id" cssClass="form-control" id="idComboEmpresa">
													<form:option value="0" label="--- Selecione ---" />
													<form:options items="${empresaLista}" />
												</form:select>
											</div>
										</div>
										<div class="col-lg-3">
											<div class="form-group">
												<form:label path="data" for="idComboPapel">Papel:</form:label>
												<form:select path="papel.id" cssClass="form-control" id="idComboPapel">
													<form:option value="0" label="--- Selecione ---" />
												</form:select>
											</div>
										</div>
										<div class="col-lg-3">
											<div class="form-group">
												<form:label path="tipoOperacao">Operacao:</form:label>
												<form:select path="tipoOperacao" cssClass="form-control">
													<form:option value="" label="--- Selecione ---" />
													<form:options items="${operacoesLista}" />
												</form:select>
											</div>
										</div>
									</div>
									<div class="row">
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
										<div class="col-lg-3">
											<div class="form-group">
												<form:label path="observacao">Observação:</form:label>
												<form:textarea path="observacao" cssClass="form-control" placeholder="Informe o motivo da operação (sentimentos,observações)." rows="3"
													cols="50"
												/>
											</div>
										</div>
									</div>
									<div class="row">
										<!-- gravar -->
										<button type="button" class="btn btn-primary" name="gravarOperacao" value="Gravar" onclick="doAjaxPost(this)">Gravar</button>
										<!-- limpar -->
										<button type="button" class="btn btn-primary" name="limparOperacao" value="Limpar" onclick="doAjaxPost(this)">Limpar</button>
										<!-- historico -->
									</div>

								</form:form>
							</div>
							<!-- /.row (nested) -->
						</div>
						<!-- /.panel-body -->
					</div>
					<!-- /panel-default -->
				</div>
				<div class="row">
					<div class="col-lg-12">
						<div class="panel panel-default">
							<div class="panel-heading">Lista de Operações</div>
							<!-- /.panel-heading -->
							<div class="panel-body">
								<div class="table-responsive">
									<table class="table table-striped table-bordered table-hover">
										<thead>
											<tr>
												<th>Ações</th>
												<th>Data</th>
												<th>Empresa</th>
												<th>Papel</th>
												<th>Operação</th>
												<th>Preço Unitário</th>
												<th>Stop Loss</th>
												<th>Entrada</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="listValue" items="${lists}">
												<tr>
													<td><c:if test="${not listValue.isAvaliacaoEntradaRealizada()}">
															<a href="#" onclick="linkInProccess('operacaoAvaliacaoEntrada/${listValue.id}');" title="Avaliar o preço para na entrada"> <span class="glyphicon glyphicon-share"></span>
															</a>
														</c:if> <a href="#" onclick="showConfirmRemove('apagarOoperacaoEntrada/${listValue.id}');" title="Apagar">
															<p class="glyphicon glyphicon-remove"></p>
													</a><a href="#" onclick="linkIn('visualizarOperacao/${listValue.id}');" title="Visualizar operação"> <span class="glyphicon glyphicon-unchecked"> </span>
													</a> <a href="#" onclick="linkIn('preOperacaoSaida/${listValue.id}');" title="Operação Saída">
															<p class="fa fa-external-link"></p>
													</a></td>
													<td><fmt:formatDate pattern="dd-MM-yyyy" value="${listValue.data}" /></td>
													<td>${listValue.empresa.nome}</td>
													<td>${listValue.papel.nome}</td>
													<td>${listValue.tipoOperacao}</td>
													<td><fmt:formatNumber value="${listValue.precoUnitario}" type="currency" pattern="R$ #,##0.00" /></td>
													<td><fmt:formatNumber value="${listValue.stopLoss}" type="currency" pattern="R$ #,##0.00" /></td>
													<td><fmt:formatNumber value="${listValue.avaliacaoEntrada/100}" type="PERCENT" minFractionDigits="2" /></td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="tab-pane fade" id="historico">
				<div class="row" id="tabConteudo">
					<div class="col-lg-12">
						<div class="panel panel-default">
							<div id="tabConteudo"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>