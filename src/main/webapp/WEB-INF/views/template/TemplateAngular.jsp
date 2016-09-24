<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<title><tiles:insertAttribute name="title" /></title>
<!--  Mobile Specific Metas  -->
  <meta name="viewport" content="width=device-width, initial-scale=1">

<!--   FONT -->
  <link href="<c:url value="/resources/js/bootstrap-3.3.4-dist/css/bootstrap-theme.min.css" />" rel="stylesheet" type="text/css">
  <link href="<c:url value="/resources/js/bootstrap-3.3.4-dist/css/bootstrap.css" />" rel="stylesheet" type="text/css">

<!--   CSS -->
  <link rel="stylesheet" href="<c:url value="/resources/css/getskeleton/css/normalize.css" />" rel="stylesheet" media="screen, projection">

<!-- SCRIPT -->
  <script type="text/javascript" src="<c:url value="/resources/js/jquery-1.11.2.min.js" />"></script>
  <script type="text/javascript" src="<c:url value="/resources/js/angular/pk/angular.min.js" />"></script>
  <script type="text/javascript" src="<c:url value="/resources/js/angular/pk/angular-animate.min.js" />"></script>
  <script type="text/javascript" src="<c:url value="/resources/js/bootstrap-3.3.4-dist/js/ui-bootstrap-tpls-0.12.1.min.js" />"></script>
<%--   <script type="text/javascript" src="<c:url value="/resources/js/bootstrap-3.3.4-dist/js/ui-bootstrap-tpls-0.5.0.js" />"></script> --%>
  
<!--   FAVICON -->
  <link rel="icon" type="image/png" href="<c:url value="/resources/css/getskeleton/images/favicon.png" />">

</head>
<body>	
	<div>
		<!-- Body Page -->
		<tiles:insertAttribute name="body" />
	</div>
</body>
</html>