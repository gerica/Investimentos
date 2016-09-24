<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page isELIgnored="false"%>

<!-- Timeline CSS -->
<link href="<c:url value="/resources/startbootstrap-sb-admin-2-1.0.6/dist/css/timeline.css" />" rel="stylesheet">

<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">Dashboard</h1>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
	<div class="row">
		<div class="col-lg-3 col-md-6">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<div class="row">
						<div class="col-xs-3">
							<i class="fa fa-comments fa-5x"></i>
						</div>
						<div class="col-xs-9 text-right">
							<div class="huge">${qtdNotas}</div>
							<div>Novo Comentário!</div>
						</div>
					</div>
				</div>
				<a href="#" onclick="linkIn('preIncluirNota');">
					<div class="panel-footer">
						<span class="pull-left">Detalhes</span> <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
						<div class="clearfix"></div>
					</div>
				</a>
			</div>
		</div>
		<div class="col-lg-3 col-md-6">
			<div class="panel panel-green">
				<div class="panel-heading">
					<div class="row">
						<div class="col-xs-3">
							<i class="fa fa-tasks fa-5x"></i>
						</div>
						<div class="col-xs-9 text-right">
							<div class="huge">12</div>
							<div>Nova tarefa!</div>
						</div>
					</div>
				</div>
				<a href="#">
					<div class="panel-footer">
						<span class="pull-left">Detalhes</span> <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
						<div class="clearfix"></div>
					</div>
				</a>
			</div>
		</div>
	</div>
	<!-- /.row -->
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">
					<i class="fa fa-table fa-fw"></i> Renda Variável
					<div class="pull-right">
						<div class="btn-group">
							<button type="button" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown">
								Ação <span class="caret"></span>
							</button>
							<ul class="dropdown-menu pull-right" role="menu">
								<li><a href="#" onclick="doAjaxPostWithoutPage('atualizarBMFTodos')">Atualizar BMF</a></li>
							</ul>
						</div>
					</div>
				</div>
				<!-- /.panel-heading -->
				<div class="panel-body">
					<div class="row">
						<div class="col-lg-12">
							<div class="table-responsive">
								<table class="table table-bordered table-hover table-striped">
									<thead>
										<tr>
											<th>Papel</th>
											<th>Data Invest</th>
											<th>Valor Unitário</th>
											<th>Investimento</th>
											<th>Data Cotação</th>
											<th>Cotação</th>
											<th>Porcentagem</th>
											<th>Lucro/Prejuízo</th>
											<th>Saldo</th>
										</tr>
									</thead>
									<tbody>
										<c:set var="totalInvestimento" value="0" scope="page" />
										<c:set var="totalRendaVariavel" value="0" scope="page" />
										<c:set var="lucroPrejuizo" value="" scope="page" />
										<c:forEach var="invest" items="${listsInvest}">
											<c:set var="totalInvestimento" value="${totalInvestimento + invest.totalInvestimento }" scope="page" />
											<c:set var="totalRendaVariavel" value="${totalRendaVariavel + invest.saldoLucroPrejuizo }" scope="page" />
											<tr>
												<td>${invest.papel}</td>
												<td><fmt:formatDate pattern="dd-MM-yyyy" value="${invest.dataInvestimento}" /></td>
												<td><fmt:formatNumber value="${invest.valorInvestimento}" type="currency" pattern="R$ #,##0.00" /></td>
												<td><fmt:formatNumber value="${invest.totalInvestimento}" type="currency" pattern="R$ #,##0.00" /></td>
												<td><fmt:formatDate pattern="dd-MM-yyyy" value="${invest.dataUltimaCotacao}" /></td>
												<td><fmt:formatNumber value="${invest.valorUltimaCotacao}" type="currency" pattern="R$ #,##0.00" /></td>
												<td class="${invest.classCssLucroPrejuizo}"><fmt:formatNumber value="${invest.porcentagemLucroPrejuizo}" type="percent"
														maxIntegerDigits="4" maxFractionDigits="4"
													/></td>
												<td class="${invest.classCssLucroPrejuizo}"><fmt:formatNumber value="${invest.lucroPrejuizo}" type="currency" pattern="R$ #,##0.00" /></td>
												<td class="${invest.classCssLucroPrejuizo}"><fmt:formatNumber value="${invest.saldoLucroPrejuizo}" type="currency"
														pattern="R$ #,##0.00"
													/></td>
											</tr>
										</c:forEach>
									</tbody>
									<tfoot>
										<tr>
											<td></td>
											<td></td>
											<td></td>
											<td><fmt:formatNumber value="${totalInvestimento}" type="currency" pattern="R$ #,##0.00" /></td>
											<td></td>
											<td></td>
											<td></td>
											<td></td>
											<c:choose>
												<c:when test="${totalInvestimento > totalRendaVariavel }">
													<c:set var="lucroPrejuizo" value="danger" scope="page" />
												</c:when>
												<c:otherwise>
													<c:set var="lucroPrejuizo" value="success" scope="page" />
												</c:otherwise>
											</c:choose>
											<td class="${lucroPrejuizo}"><fmt:formatNumber value="${totalRendaVariavel}" type="currency" pattern="R$ #,##0.00" /></td>
										</tr>
									</tfoot>
								</table>
							</div>
							<!-- /.table-responsive -->
						</div>
					</div>
					<!-- /.row -->
				</div>
				<!-- /.panel-body -->
			</div>
			<!-- /panel panel-default -->
		</div>
		<!-- /col-lg-12 -->
	</div>
	<!-- /.row -->
	<div class="row">
		<div class="col-lg-8">
			<!-- /.panel -->
			<div class="panel panel-default">
				<div class="panel-heading">
					<i class="fa fa-table fa-fw"></i> Renda Fixa
					<div class="pull-right">
						<div class="btn-group">
							<button type="button" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown">
								Ação <span class="caret"></span>
							</button>
							<ul class="dropdown-menu pull-right" role="menu">
								<li><a href="#">Action</a></li>
								<li><a href="#">Another action</a></li>
								<li><a href="#">Something else here</a></li>
								<li class="divider"></li>
								<li><a href="#">Separated link</a></li>
							</ul>
						</div>
					</div>
				</div>
				<!-- /.panel-heading -->
				<div class="panel-body">
					<div class="row">
						<div class="col-lg-12">
							<div class="table-responsive">
								<table class="table table-striped table-bordered table-hover">
									<thead>
										<tr>
											<th>Data de Entrada</th>
											<th>Investimento</th>
											<th>Banco</th>
											<th>Taxa</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="listValueFixa" items="${listsRendaFixa}">
											<tr>
												<td>${listValueFixa.dataEntrada}</td>
												<td>${listValueFixa.investimento}</td>
												<td>${listValueFixa.nomeBanco}</td>
												<td>${listValueFixa.taxa}</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
							<!-- /.table-responsive -->
						</div>
					</div>
					<!-- /.row -->
				</div>
				<!-- /.panel-body -->
			</div>
		</div>
		<div class="col-lg-4">
			<div class="panel panel-default">
				<div class="panel-heading">
					<i class="fa fa-bell fa-fw"></i> Notificações
				</div>
				<!-- /.panel-heading -->
				<div class="panel-body">
					<div class="list-group">
						<c:forEach var="listValue" items="${lists}">
							<a href="#" onclick="doAjaxGetWithoutPage('recuperar/${listValue.id}')" class="list-group-item"> <i class="fa fa-comment fa-fw"></i>
								${listValue.titulo} - ${listValue.sumario} <span class="pull-right text-muted small"><em> ${listValue.getDescricaoCurta()}</em> </span>
							</a>
						</c:forEach>
						</a>
					</div>
					<!-- /.list-group -->
					<a href="#" class="btn btn-default btn-block">Ver Todas Notas</a>
				</div>
				<!-- /.panel-body -->
			</div>
		</div>
		<!-- /.col-lg-4 -->
	</div>
</div>
<!-- /#page-wrapper -->