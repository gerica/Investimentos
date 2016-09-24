<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title><tiles:insertAttribute name="title" /></title>
<!--  Mobile Specific Metas  -->
  <meta name="viewport" content="width=device-width, initial-scale=1">

<!--   FONT -->
  <link href="<c:url value="/resources/css/getskeleton/css/fontgoogle.css" />" rel="stylesheet" type="text/css">

<!--   CSS -->
  <link rel="stylesheet" href="<c:url value="/resources/css/getskeleton/css/normalize.css" />" rel="stylesheet" media="screen, projection">
  <link rel="stylesheet" href="<c:url value="/resources/css/getskeleton/css/skeleton.css" />" rel="stylesheet" media="screen, projection">
  <link rel="stylesheet" href="<c:url value="/resources/css/getskeleton/css/custom.css" />" rel="stylesheet" media="screen, projection">
  <link rel="stylesheet" href="<c:url value="/resources/css/hrs.css" />" rel="stylesheet" media="screen, projection">
  <link rel="stylesheet" href="<c:url value="/resources/css/jquery-ui.min.css" />" rel="stylesheet" media="screen, projection">
  <link rel="stylesheet" href="<c:url value="/resources/css/getskeleton/css/github-prettify-theme.css" />" rel="stylesheet" media="screen, projection">

<!-- SCRIPT -->
  <script type="text/javascript" src="<c:url value="/resources/js/jquery-1.11.2.min.js" />"></script>
  <script type="text/javascript" src="<c:url value="/resources/js/jquery.maskMoney.js" />"></script>
  <script type="text/javascript" src="<c:url value="/resources/js/getskeleton/run_prettify.js" />"></script>
  <script type="text/javascript" src="<c:url value="/resources/js/getskeleton/site.js" />"></script>
  <script type="text/javascript" src="<c:url value="/resources/js/jquery-transit.mim.js" />"></script>
  <script type="text/javascript" src="<c:url value="/resources/js/jquery-ui.min.js" />"></script>
  <script type="text/javascript" src="<c:url value="/resources/js/geral/geral.js" />"></script>
  
<!--   FAVICON -->
  <link rel="icon" type="image/png" href="<c:url value="/resources/css/getskeleton/images/favicon.png" />">

</head>
<body class="code-snippets-visible" data-twttr-rendered="true">	
	<div class="container">
		<div class="row">
			<!-- Header -->
			<tiles:insertAttribute name="header" />
		</div>
		<div class="navbar-spacer"></div>
		<nav class="navbar">
			<!-- Menu Page -->
			<tiles:insertAttribute name="menu" />
		</nav>
		<div class="row">
		 
			<!-- Body Page -->
			<tiles:insertAttribute name="body" />
		</div>
		<div class="row">
			<!-- Footer Page -->
			<tiles:insertAttribute name="footer" />
		</div>
	</div>
</body>
</html>