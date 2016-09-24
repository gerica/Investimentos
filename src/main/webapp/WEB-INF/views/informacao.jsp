<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags"  prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page session="true"%>
<!-- SCRIPT DA PAGINA -->
<script type="text/javascript" src="<c:url value="/resources/js/geral/informacao.js" />"></script>
<div class="twelve columns" id="containerInformacoes">
	<c:url value="/site/preIncluirNota" var="urlPreInclurNota"/>
	 <a href="${urlPreInclurNota }" STYLE="float: right; padding-right: 5px;">		      
		<img src="<c:url value="/resources/img/application--pencil.png" />"  TITLE="Inclur Novo" ALT="Inclur Novo"/>
	</a>
<c:if test="${not empty lists}">
<div class="row" STYLE="padding-bottom: 10px;">
	<center><label class="titulo">Notas</label></center>
	<hr class="style-six">
<c:set var="count" value="0" scope="page" />
 <c:forEach var="listValue" items="${lists}">
 <c:if test="${count%3==0 || count==0}">
     <div class="row" STYLE="padding: 2px 2px 2px 2px">
 </c:if>
 <c:set var="count" value="${count + 1}" scope="page"/>
       	<div class="four columns" id="div_hightliht_${count}">
		     <strong>${listValue.titulo} - ${listValue.sumario} </strong>
		     	<c:url value="/site/excluirNota/${listValue.id}" var="urlExcluirNota" /> 
		        <a href="${urlExcluirNota}" STYLE="float: right; padding-right: 2px" id="urlExcluirConfirm" class="confirm">		      
		      		<img src="<c:url value="/resources/img/application--minus.png" />"  TITLE="Excluir" ALT="Excluir"/>
		      	</a>
		      	<c:url value="/site/preAlterarNota/${listValue.id}" var="urlPreAlterarNota"/>
		        <a href="${urlPreAlterarNota }" STYLE="float: right; padding-right: 2px">		      
		      		<img src="<c:url value="/resources/img/application--plus.png" />"  TITLE="Alterar" ALT="Alterar"/>
		      	</a>
				<br>
		     ${listValue.descricao}
   		</div>
 <c:if test="${count%3==0 || count==0}">
	 </div>
 </c:if>
 </c:forEach>
 </div>
</c:if>
<c:if test="${not empty listsInvest}">
<hr class="style-six">
<div class="row">
	<center><label class="titulo">Invesmtimentos</label></center>
	<table class="u-full-width">
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
	  <c:set var="totalInvestimento" value="0" scope="page"/>
	  <c:set var="totalRendaVariavel" value="0" scope="page"/>
	  <c:set var="lucroPrejuizo" value="" scope="page"/>
	  <c:forEach var="invest" items="${listsInvest}">
	  <c:set var="totalInvestimento" value="${totalInvestimento + invest.totalInvestimento }" scope="page"/>
	  <c:set var="totalRendaVariavel" value="${totalRendaVariavel + invest.saldoLucroPrejuizo }" scope="page"/>
	    <tr>
	      <td>${invest.papel}</td>
	      <td><fmt:formatDate pattern="dd-MM-yyyy" value="${invest.dataInvestimento}" /></td>
		  <td><fmt:formatNumber value="${invest.valorInvestimento}" type="currency" pattern="R$ #,##0.00"/></td>
		  <td><fmt:formatNumber value="${invest.totalInvestimento}" type="currency" pattern="R$ #,##0.00"/></td>
	      <td><fmt:formatDate pattern="dd-MM-yyyy" value="${invest.dataUltimaCotacao}" /></td>
		  <td><fmt:formatNumber value="${invest.valorUltimaCotacao}" type="currency" pattern="R$ #,##0.00"/></td>
		  <td class="${invest.classCssLucroPrejuizo}"><fmt:formatNumber value="${invest.porcentagemLucroPrejuizo}" type="percent" maxIntegerDigits="4" maxFractionDigits="4"/></td>
		  <td class="${invest.classCssLucroPrejuizo}"><fmt:formatNumber value="${invest.lucroPrejuizo}" type="currency" pattern="R$ #,##0.00"/></td>
		  <td class="${invest.classCssLucroPrejuizo}"><fmt:formatNumber value="${invest.saldoLucroPrejuizo}" type="currency" pattern="R$ #,##0.00"/></td>
	    </tr>
	  </c:forEach>
	  </tbody>
	  <tfoot>
        <tr>
            <td></td>
            <td></td>
            <td></td>
            <td><fmt:formatNumber value="${totalInvestimento}" type="currency" pattern="R$ #,##0.00"/></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <c:choose>
		      <c:when test="${totalInvestimento > totalRendaVariaval }"> <c:set var="lucroPrejuizo" value="lucro" scope="page"/></c:when>
		      <c:otherwise><c:set var="lucroPrejuizo" value="prejuizo" scope="page"/></c:otherwise>
			</c:choose>
            <c:if test="">
            	<c:set var="lucroPrejuizo" value="" scope="page"/>
            </c:if>
            <td class="${lucroPrejuizo}"><fmt:formatNumber value="${totalRendaVariavel}" type="currency" pattern="R$ #,##0.00"/></td>
        </tr>
    </tfoot>
	</table>
</div>
</c:if>
</div>       