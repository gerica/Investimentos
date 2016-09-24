<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>

<div class="container">
	<div class="row" style="margin-top: 10%">
		<c:url value="/logar" var="urlLogar" />
		<form:form action="${urlLogar}" commandName="userForm" id="dummy">
			<c:if test="${not empty errorMsg}">
				<div id="errorMsg" class="error">
					<spring:message text="${errorMsg}" htmlEscape="true" />
				</div>
			</c:if>

			<div class="col-md-4 col-md-offset-4">
				<div class="login-panel panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">Informe o login e senha</h3>
					</div>
					<div class="panel-body">
						<form role="form">
							<fieldset>
								<div class="form-group">
									<form:input path="nome" size="20" cssErrorClass="error" cssClass="form-control" placeholder="Informe o nome." />
								</div>
								<div class="form-group">
									<form:password path="senha" size="20" cssErrorClass="error" placeholder="Informe a senha." cssClass="form-control" />
								</div>
								<!-- Change this to a button or input when using this as a form -->
								<!-- 								<a href="index.html" class="btn btn-lg btn-success btn-block">Login</a> -->
								<input class="btn btn-lg btn-success btn-block" type="submit" value="Login" />
							</fieldset>
						</form>
					</div>
				</div>
			</div>
		</form:form>
	</div>
</div>
