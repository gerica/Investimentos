<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags"  prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- SCRIPT DA PAGINA -->
<!-- <script type="text/javascript" src="https://www.google.com/jsapi"></script> -->
<script type="text/javascript" src="<c:url value="/resources/js/chart/googleChart.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/chart/graficos.js" />"></script>
<div class="twelve columns" id="containerInformacoes">
	<center><label class="titulo">Graficos Investimentos</label></center>
	<form:form commandName="graficoRendaVariavelForm" id="field">
		<input name="tipo_chart" type="hidden" value="${graficoRendaVariavelForm.chartType}"/>
		<input name="papelId" type="hidden" value="${graficoRendaVariavelForm.papel.id}"/>
     	<form:errors path="*" element="div" cssClass="error" />
		 <c:if test="${not empty successMsg}">
		       <div id="successMsg" class="success">
		           <spring:message text="${successMsg}" htmlEscape="true" />
		       </div>
	     </c:if>
	      <c:if test="${not empty errorMsg}">
		       <div id="errorMsg" class="error">
		           <spring:message text="${errorMsg}" htmlEscape="true" />
		       </div>
		 </c:if>
	     <div class="row">
	     	<div class="four columns">
	       		<form:label path="empresa.id" cssErrorClass="error" for="idComboEmpresa">Empresa:</form:label>
	       		<form:select path="empresa.id" cssErrorClass="error" id="idComboEmpresa">
		       		<form:option value="0" label="--- Todos ---"/>
	   				<form:options items="${empresaLista}" />
	       		</form:select>
	   		</div>
	   		<div class="four columns">
		   		<form:label path="papel.id" cssErrorClass="error" for="idComboPapel">Papel:</form:label>
	       		<form:select path="papel.id" cssErrorClass="error" id="idComboPapel">
		       		<form:option value="0" label="--- Todos ---"/>
		       		<c:if test="${not empty papelLista}">
		       			<form:options items="${papelLista}" />
		       		</c:if>
	       		</form:select>
	   		</div>
	   		<div class="four columns">
		   		<form:label path="chartType" cssErrorClass="error" for="field1">Tipo:</form:label>
	       		<form:select path="chartType" cssErrorClass="error">
		       		<form:option value="" label="--- Selecione ---"/>
	   				<form:options items="${tiposGraficosLista}" />
	       		</form:select>
	   		</div>
	   	 </div>
	     
	     <div class="row">
	        <input class="button-primary" type="submit" name="/site/graficos/pesquisar" value="Pesquisar">
	        <input class="button-primary" type="submit" name="/site/graficos/limpar" value="Limpar">
	     </div>
	</form:form>
</div>       