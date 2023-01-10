<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
   pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
   <head>
      <link href="
      <c:url value="/resources/css/bootstrap.min.css" />
      "
      rel="stylesheet">
      <script src="
      <c:url value="/resources/js/jquery-1.11.1.min.js" />
      "></script>
      <script src="
      <c:url value="/resources/js/bootstrap.min.js" />
      "></script>
   </head>
   <body>
      <jsp:include page="/WEB-INF/views/headers/loginHeader.jsp" />
      <div class="container ">
         <div class="col-md-offset-2 col-md-8">
            <div class="panel panel-info">
               <div class="panel-heading">
                  <div class="panel-title">User Details</div>
               </div>
               <div class="panel-body">
                  <div class="col-md-12 row">
                     <div class="col-md-6">
                        <b>First Name</b>
                     </div>
                     <div class="col-md-6">
                        ${user.firstName}
                     </div>
                     <hr>
                  </div>
                  <div class="col-md-12 row">
                     <div class="col-md-6">
                        <b>  Last Name</b>
                     </div>
                     <div class="col-md-6">
                        ${user.lastName}
                     </div>
                     <hr>
                  </div>
                  <div class="col-md-12 row">
                     <div class="col-md-6">
                        <b>  Email</b>
                     </div>
                     <div class="col-md-6">
                        ${user.email}
                     </div>
                     <hr>
                  </div>
                  <div class="col-md-12 row">
                     <div class="col-md-6">
                        <b>  Gender</b>
                     </div>
                     <div class="col-md-6">
                        ${user.gender.type}
                     </div>
                     <hr>
                  </div>
                  <div class="col-md-12 row">
                     <div class="col-md-6">
                        <b>  Correspondence Address</b>
                     </div>
                     <div class="col-md-6">
                        ${corresAddress.addressLine1} ${corresAddress.addressLine2}<br>
                        ${corresAddress.city}<br>
                        ${corresAddress.state} - ${corresAddress.zipCode}
                     </div>
                  </div>
                  <div class="col-md-12 row">
                     <hr>
                  </div>
                  <div class="col-md-12 row">
                     <div class="col-md-6">
                        <b>  Permanent Address</b>
                     </div>
                     <div class="col-md-6">
                        ${perAddress.addressLine1} ${perAddress.addressLine2}</br>
                        ${perAddress.city}<br>
                        ${perAddress.state} - ${perAddress.zipCode}
                     </div>
                     <hr>
                  </div>
               </div>
            </div>
         </div>
      </div>
      <jsp:include page="/WEB-INF/views/headers/loginFooter.jsp" />
   </body>
</html>