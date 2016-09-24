<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>

<div class="three columns">&nbsp;</div>
<div class="nine columns">
	<c:url value="/logar" var="urlLogar" />
	<form:form action="${urlLogar}" commandName="userForm" id="dummy">
		<div class="row">
			<c:if test="${not empty errorMsg}">
				<div id="errorMsg" class="error">
					<spring:message text="${errorMsg}" htmlEscape="true" />
				</div>
			</c:if>

			<form:errors path="*" element="div" cssClass="error" />
			<div class="four columns">
				<form:label path="nome" cssErrorClass="error" for="dummy0">Nome:</form:label>
				<form:input path="nome" size="20" cssErrorClass="error" placeholder="Informe o nome." />
			</div>
			<div class="four columns">
				<form:label path="nome" cssErrorClass="error" for="dummy1">Senha:</form:label>
				<form:password path="senha" size="20" cssErrorClass="error" placeholder="Informe a senha." />
			</div>
			<div class="four columns">&nbsp;</div>
		</div>
		<div class="row">
			<input class="button-primary" type="submit" value="Login" />
		</div>
	</form:form>
</div>