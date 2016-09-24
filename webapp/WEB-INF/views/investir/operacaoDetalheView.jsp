<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">Diário de Operações Detalhe</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">Detalhe</div>
			<div class="panel-body">
				<div class="row">
					<div class="row">
						<div class="col-lg-3">
							<div class="form-group">
								<label>Data:</label>
								<p class="form-control-static">
									<fmt:formatDate pattern="dd-MM-yyyy" value="${operacaoEntrada.data}" />
								</p>
							</div>
						</div>
						<div class="col-lg-3">
							<div class="form-group">
								<label>Empresa:</label>
								<p class="form-control-static">${operacaoEntrada.empresa.nome}</p>
							</div>
						</div>
						<div class="col-lg-3">
							<div class="form-group">
								<label>Papel:</label>
								<p class="form-control-static">${operacaoEntrada.papel.nome}</p>
							</div>
						</div>
						<div class="col-lg-3">
							<div class="form-group">
								<label>Operação:</label>
								<p class="form-control-static">${operacaoEntrada.tipoOperacao}</p>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-3">
							<div class="form-group">
								<label>Preço Unitário:</label>
								<p class="form-control-static">
									<fmt:formatNumber value="${operacaoEntrada.precoUnitario}" type="currency" pattern="R$ #,##0.00" />
								</p>
							</div>
						</div>
						<div class="col-lg-3">
							<div class="form-group">
								<label>Quantidade:</label>
								<p class="form-control-static">${operacaoEntrada.quantidade}</p>
							</div>
						</div>
						<div class="col-lg-3">
							<div class="form-group">
								<label>Despesa:</label>
								<p class="form-control-static">
									<fmt:formatNumber value="${operacaoEntrada.despesa}" type="currency" pattern="R$ #,##0.00" />
								</p>
							</div>
						</div>
						<div class="col-lg-3">
							<div class="form-group">
								<label>Stop Loss:</label>
								<p class="form-control-static">
									<fmt:formatNumber value="${operacaoEntrada.stopLoss}" type="currency" pattern="R$ #,##0.00" />
								</p>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-6">
							<div class="form-group">
								<label>Observação:</label>
								<p class="form-control-static">${operacaoEntrada.observacao}</pç>
							</div>
						</div>
						<div class="col-lg-6">
							<div class="form-group">
								<label>Analise Entrada:</label>
								<p class="form-control-static">
									<fmt:formatNumber value="${operacaoEntrada.avaliacaoEntrada/100}" type="PERCENT" minFractionDigits="2" />
								</p>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- /.row (nested) -->
		</div>
		<!-- /.panel-body -->
	</div>
	<!-- /panel-default -->
</div>
</div>