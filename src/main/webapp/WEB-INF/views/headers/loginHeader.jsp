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
    <ul class="nav navbar-nav">
      <c:if test="${loggedUser.isUser == true }">
          <li><a class="nav-link" href="${contextPath}/song/list">All Songs </a></li>
          <li><a class="nav-link" href="${contextPath}/song/my-songs">My Songs</a></li>
      </c:if>
      <c:if test="${loggedUser.isAdmin == true || loggedUser.isSuperAdmin == true }">
          <li><a class="nav-link" href="${contextPath}/active-users">User List</a></li>
          <li><a class="nav-link" href="${contextPath}/admin/user-reg-apps">User Requests</a></li>
           <li><a class="nav-link" href="${contextPath}/admin/purchase-requests">Purchase Requests</a></li>
       </c:if>
    </ul>
    <ul class="nav navbar-nav navbar-right">
    <li><a href="${contextPath}/cart/myCart"><span></span> My Cart</a></li>
      <li><a href="${contextPath}/user/myProfile"><span></span> My Profile</a></li>
      <li><a class="nav-link"  href="#" onclick="document.forms['logoutForm'].submit()">Logout</a></li>
    </ul>
  </div>
</nav>
 <form id="logoutForm" method="POST" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>