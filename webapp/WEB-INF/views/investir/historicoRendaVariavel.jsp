<div class="col-lg-12">
	<div class="panel panel-default">
		<div class="panel-heading">Lista de Operações</div>
		<!-- /.panel-heading -->
		<div class="panel-body">
			<div class="table-responsive">
				<table class="table table-striped table-bordered table-hover">
					<thead>
						<tr>
							<th>Data Entrada</th>
							<th>Empresa</th>
							<th>Papel</th>
							<th>Operação</th>
							<th>Preço Unitário</th>
							<th>Quantidade</th>
							<th>Total</th>
							<th>Data Saida</th>
							<th>Preço Unitário</th>
							<th>Quantidade</th>
							<th>Total</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="listValueHistory" items="${listsHistorico}">
							<tr>
								<td><fmt:formatDate pattern="dd-MM-yyyy" value="${listValueHistory.dataEntrada}" /></td>
								<td>${listValueHistory.nomeEmpresa}</td>
								<td>${listValueHistory.nomePapel}</td>
								<td>${listValueHistory.tipoOperacao}</td>
								<td><fmt:formatNumber value="${listValueHistory.precoUnitarioEntrada}" type="currency" pattern="R$ #,##0.00" /></td>
								<td>${listValueHistory.quantidadeEntrada}</td>
								<td><fmt:formatNumber value="${listValueHistory.totalEntrada}" type="currency" pattern="R$ #,##0.00" /></td>
								<td><fmt:formatDate pattern="dd-MM-yyyy" value="${listValueHistory.dataSaida}" /></td>
								<td><fmt:formatNumber value="${listValueHistory.precoUnitarioSaida}" type="currency" pattern="R$ #,##0.00" /></td>
								<td>${listValueHistory.quantidadeSaida}</td>
								<td class="${listValueHistory.classCssLucroPrejuizo}"><fmt:formatNumber value="${listValueHistory.totalSaida}" type="currency"
										pattern="R$ #,##0.00"
									/></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>