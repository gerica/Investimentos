<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!-- <div class="one columns">&nbsp;</div> -->
<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">Analise de tendência. (Compra)</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>

<div class="panel panel-default">
	<div class="panel-heading">Empresa: ${papel.empresa.nome} - Papel: ${papel.nome }</div>
	<!-- /.panel-heading -->
	<div class="panel-body">
		<!-- Nav tabs -->
		<ul class="nav nav-tabs">
			<li class="active"><a href="#home" data-toggle="tab">Analise</a></li>
		</ul>
		<!-- Tab panes -->
		<div class="tab-content">
			<div class="tab-pane fade in active" id="home">
				<div class="row">
					<div class="col-lg-12">
						<div class="panel panel-default">
							
							<!-- /.panel-heading -->
							<div class="panel-body">
								<div class="table-responsive">
									<table class="table table-striped table-bordered table-hover">
										<thead>
											<tr>
												<th>Data</th>
												<th>Ruptura de Baixa</th>
												<th>Soma</th>
												<th>Rompeu</th>
												<th>Qtd Baixas</th>
												<th>Media Ruptura</th>
												<th>StopLoss</th>
												<th>Progetido</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="listValue" items="${lists}">
												<tr>
													<td><fmt:formatDate pattern="dd-MM-yyyy" value="${listValue.data}" /></td>
													<td><fmt:formatNumber value="${listValue.ruptura}" type="currency" pattern="R$ #,##0.000" /></td>
													<td><fmt:formatNumber value="${listValue.soma}" type="currency" pattern="R$ #,##0.000" /></td>
													<td><c:out value="${listValue.rompeu ? 1: 0}" /></td>
													<td><fmt:formatNumber value="${listValue.numeroRupturas}" type="NUMBER" /></td>
													<td><fmt:formatNumber value="${listValue.mediaRupturas}" type="currency" pattern="R$ #,##0.000" /></td>
													<td><fmt:formatNumber value="${listValue.stop}" type="currency" pattern="R$ #,##0.000" /></td>
													<td class="success"><fmt:formatNumber value="${listValue.valorProtegido}" type="currency"
															pattern="R$ #,##0.000"
														/></td>
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
		</div>
	</div>
</div>