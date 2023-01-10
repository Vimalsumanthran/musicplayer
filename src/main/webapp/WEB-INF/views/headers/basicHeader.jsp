<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Music Player</title>
<link href="<c:url value="/resources/css/musicplayer.css" />"
 rel="stylesheet">
 <link href="<c:url value="/resources/css/bootstrap.min.css" />"
  rel="stylesheet">
<%@ page isELIgnored="false"%>
<script src="<c:url value="/resources/js/jquery-1.11.1.min.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
</head>
<body>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>


<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">Music Player</a>
    </div>

    <ul class="nav navbar-nav navbar-right">
        <li><a href="${contextPath}/registration"><span></span> Sign Up</a></li>
        <li><a href="${contextPath}/login"><span></span> Login</a></li>
    </ul>
  </div>
</nav>