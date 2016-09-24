<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
<link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
</head>
<body>
    <div align="center">
        <h2>Login Form</h2>
        <table CLASS="tabela">
        <form:form action="logar" commandName="userForm">
        <form:errors path="*" element="div" cssClass="errors" />
                <tr>
                    <td align="left" width="20%"><form:label path="nome" cssErrorClass="error" >Nome:</form:label></td>
                    <td align="left" width="40%"><form:input path="nome" size="30" cssErrorClass="error"/></td>
                </tr>
                <tr>
                    <td><form:label path="nome" cssErrorClass="error" >Senha:</form:label></td>
                    <td><form:password path="senha" size="30" cssErrorClass="error"/></td>
                </tr>
                <tr>
                    <td></td>
                    <td align="center"><input type="submit" value="Login"/></td>
                </tr>
        </form:form>
        </table>
    </div>
</body>
</html>