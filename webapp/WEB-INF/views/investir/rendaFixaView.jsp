<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- SCRIPT DA PAGINA -->
<%-- <script type="text/javascript" src="<c:url value="/resources/js/investir/operacaoView.js" />"></script> --%>
<!-- jquery mask -->
<script type="text/javascript" src="<c:url value="/resources/js/jquery.maskMoney.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/angular/comumAngular.js" />"></script>
<!-- SCRIPT DA PAGINA -->
<script type="text/javascript" src="<c:url value="/resources/js/investir/rendaFixaView.js" />"></script>

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
			<li><a href="#historico" data-toggle="tab" onclick="selectTab('historicoRendaFixa');">Histórico</a></li>
		</ul>
		<!-- Tab panes -->
		<div class="tab-content">
			<div class="tab-pane fade in active" id="home">
				<div class="row">
					<div class="col-lg-12">
						<div class="panel panel-default">
							<div class="panel-body">
								<form:form commandName="rendaFixaForm" id="formPage">
									<c:if test="${not empty rendaFixaForm.id}">
										<form:input path="id" type="hidden" value="${rendaFixaForm.id}" />
									</c:if>

									<div class="row">
										<div class="alert alert-success alert-dismissable"></div>
										<div class="alert alert-danger alert-dismissable"></div>
									</div>
									<div class="row">
										<div class="col-lg-6">
											<div class="form-group">
												<form:label path="dataEntrada">Data Entrada:</form:label>
												<form:input path="dataEntrada" placeholder="Data entrada" cssClass="form-control" type="date" />
											</div>
										</div>
										<div class="col-lg-6">
											<div class="form-group">
												<form:label path="investimento">Investimento:</form:label>
												<form:input path="investimento" cssClass="form-control dinheiro" class="dinheiro" placeholder="Investimento." />
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-lg-6">
											<div class="form-group">
												<form:label path="nomeBanco">Banco:</form:label>
												<form:input path="nomeBanco" cssClass="form-control" placeholder="Nome do Banco." />
											</div>
										</div>
										<div class="col-lg-6">
											<div class="form-group">
												<form:label path="taxa">Taxa:</form:label>
												<form:input path="taxa" size="4" cssClass="form-control porcentagem" placeholder="Taxa." />
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-lg-12">
											<div class="form-group">
												<form:label path="observacao">Observação:</form:label>
												<form:textarea path="observacao" cssClass="form-control" placeholder="Informe o motivo da operação (sentimentos,observações)." rows="3"
													cols="50"
												/>
											</div>
										</div>
									</div>
									<!-- gravar -->
									<button type="button" class="btn btn-primary" name="gravarRendaFixa" value="Gravar" onclick="doAjaxPost(this)">Gravar</button>
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
												<th>Ações</th>
												<th>Data de Entrada</th>
												<th>Investimento</th>
												<th>Banco</th>
												<th>Taxa</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="listValue" items="${lists}">
												<tr>
													<td><a href="#" onclick="linkIn('preAlterar/${listValue.id}');" title="Alterar">
															<p class="fa fa-pencil"></p>
													</a> <a href="#" onclick="showConfirmRemove('apagarRendaFixa/${listValue.id}');" title="Apagar">
															<p class="glyphicon glyphicon-remove"></p>
													</a> <a href="#" onclick="linkIn('preRendaFixaSaida/${listValue.id}');" title="Sair da Renda Fixa">
															<p class="fa fa-sign-out"></p>
													</a></td>
													<td>${listValue.dataEntrada}</td>
													<td>${listValue.investimento}</td>
													<td>${listValue.nomeBanco}</td>
													<td>${listValue.taxa}</td>
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
				<div class="row">
					<div class="col-lg-12">
						<div class="panel panel-default">
							<div class="panel-body">
								<div id="tabConteudo"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>