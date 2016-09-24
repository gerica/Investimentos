<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags"  prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="span-18 first">
     <form:form commandName="carteiraForm" id="field">
	 <c:if test="${not empty successMsg}">
	       <div id="successMsg" class="success">
	           <spring:message text="${successMsg}" htmlEscape="true" />
	       </div>
	   </c:if>
     	<form:errors path="*" element="div" cssClass="error" />
        <fieldset>
          <legend>Cadastrar Empresa</legend>
          <div class="span-5 prepend-6">
           <p>
           	<form:label path="nome" cssErrorClass="error" for="field1">Empresa:</form:label>
          		<form:input path="nome" maxlength="30" cssErrorClass="error" id="field1"/>
           </p>
          </div>
			<div class="span-5 prepend-7">
           <p>
             <input type="submit" name="site/gravar" value="Gravar">
             <input type="submit" name="site/limpar" value="Limpar">
           </p>
          </div>
        </fieldset>
      </form:form>
      
	  <c:if test="${not empty lists}">
 		<fieldset>
          <legend>Empresas</legend>
			<HR>
			<c:forEach var="listValue" items="${lists}">
				<div class="span-10 border">${listValue.nome}</div>
			</c:forEach>
			<HR>
 		</fieldset>
	</c:if>
	  
</div>       