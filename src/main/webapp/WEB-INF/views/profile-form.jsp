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
      <div class="container full-width">
         <div class="col-md-offset-2 col-md-8">
            <form:form action="${contextPath}/user/myProfile" cssClass="form-horizontal"
               method="post" modelAttribute="userForm">
               <div class="panel panel-info">
                  <div class="panel-heading">
                     <div class="panel-title">Edit My Details</div>
                  </div>
                  <div class="panel-body">
                     <form:hidden path="User.id" />
                     <div class="col-md-12 row">
                        <div class="col-md-6">
                           <spring:bind path="User.firstName">
                              <label for="firstName" class="col-md-3 control-label" style="padding-left: 0px;text-align: left;">First Name</label>
                              <div class=" form-group form-spacing">
                                 <form:input type="text" required="true" path="User.firstName" class="form-control" placeholder="FirstName"
                                    autofocus="true"></form:input>
                                 <form:errors path="User.firstName"></form:errors>
                              </div>
                           </spring:bind>
                        </div>
                        <div class="col-md-6">
                           <spring:bind path="User.lastName">
                              <label for="firstName" class="col-md-3 control-label" style="padding-left: 0px;text-align: left;">Last Name</label>
                              <div class="form-group form-spacing">
                                 <form:input type="text"  required="true" path="User.lastName" class="form-control" placeholder="Last Name"
                                    autofocus="true"></form:input>
                                 <form:errors path="User.lastName"></form:errors>
                              </div>
                           </spring:bind>
                        </div>
                     </div>
                     <div class="col-md-12 row">
                        <div class="col-md-6">
                           <spring:bind path="User.email">
                              <label for="email" class="col-md-3 control-label" style="padding-left: 0px;text-align: left;">Email</label>
                              <div class=" form-group form-spacing">
                                 <form:input type="text" required="true" path="User.email" class="form-control" placeholder="Email"
                                    autofocus="true"></form:input>
                                 <form:errors path="User.email"></form:errors>
                              </div>
                           </spring:bind>
                        </div>
                        <div class="col-md-6">
                           <spring:bind path="User.gender.id">
                              <label for="gender" class="col-md-3 control-label" style="padding-left: 0px;text-align: left;">Gender</label>
                              <div class="form-group form-spacing">
                                 <form:select path="User.gender.id" cssClass="form-control">
                                    <option value = "NONE" label = "Select Gender"/>
                                       <c:forEach items="${genderDetailsList}" var="allGender">
                                          <form:option  value="${allGender.id}" label="${allGender.type}"  />
                                       </c:forEach>
                                 </form:select>
                                 <form:errors path="gender"></form:errors>
                              </div>
                           </spring:bind>
                        </div>
                     </div>
                     <div class="col-md-12 row">
                     <form:hidden path="UserAddress1.id" />
                     <div class="col-md-12"><span ><b>Correspondence Address</b></span></div>
                     <div class="col-md-3">
                     <spring:bind path="UserAddress1.addressLine1">
                     <div class=" form-group form-spacing">
                     <form:input type="text" required="true" path="UserAddress1.addressLine1" class="form-control" placeholder="Address Line 1"
                        autofocus="true"></form:input>
                     <form:errors path="UserAddress1.addressLine1"></form:errors>
                     </div>
                     </spring:bind>
                     </div>
                     <div class="col-md-3">
                     <spring:bind path="UserAddress1.addressLine2">
                     <div class=" form-group form-spacing">
                     <form:input type="text" required="true" path="UserAddress1.addressLine2" class="form-control" placeholder="Address Line 2"
                        autofocus="true"></form:input>
                     <form:errors path="UserAddress1.addressLine2"></form:errors>
                     </div>
                     </spring:bind>
                     </div>
                     <div class="col-md-2">
                     <spring:bind path="UserAddress1.city">
                     <div class=" form-group form-spacing">
                     <form:input type="text" required="true" path="UserAddress1.city" class="form-control" placeholder="City"
                        autofocus="true"></form:input>
                     <form:errors path="UserAddress1.city"></form:errors>
                     </div>
                     </spring:bind>
                     </div>
                     <div class="col-md-2">
                     <spring:bind path="UserAddress1.state">
                     <div class=" form-group form-spacing">
                     <form:input type="text" required="true" path="UserAddress1.state" class="form-control" placeholder="State"
                        autofocus="true"></form:input>
                     <form:errors path="UserAddress1.state"></form:errors>
                     </div>
                     </spring:bind>
                     </div>
                     <div class="col-md-2">
                     <spring:bind path="UserAddress1.zipCode">
                     <div class=" form-group form-spacing">
                     <form:input type="text" required="true" path="UserAddress1.zipCode" class="form-control" placeholder="Zip Code"
                        autofocus="true"></form:input>
                     <form:errors path="UserAddress1.zipCode"></form:errors>
                     </div>
                     </spring:bind>
                     </div>
                     </div>
                     <div class="col-md-12 row">
                     <form:hidden path="UserAddress2.id" />
                     <div class="col-md-12"><span><b>Permanent Address</b></span></div>
                     <div class="col-md-3">
                     <spring:bind path="UserAddress2.addressLine1">
                     <div class=" form-group form-spacing">
                     <form:input type="text" required="true" path="UserAddress2.addressLine1" class="form-control" placeholder="Address Line 1"
                        autofocus="true"></form:input>
                     <form:errors path="UserAddress2.addressLine1"></form:errors>
                     </div>
                     </spring:bind>
                     </div>
                     <div class="col-md-3">
                     <spring:bind path="UserAddress2.addressLine2">
                     <div class=" form-group form-spacing">
                     <form:input type="text" required="true" path="UserAddress2.addressLine2" class="form-control" placeholder="Address Line 2"
                        autofocus="true"></form:input>
                     <form:errors path="UserAddress2.addressLine2"></form:errors>
                     </div>
                     </spring:bind>
                     </div>
                     <div class="col-md-2">
                     <spring:bind path="UserAddress2.city">
                     <div class=" form-group form-spacing">
                     <form:input type="text" required="true" path="UserAddress2.city" class="form-control" placeholder="City"
                        autofocus="true"></form:input>
                     <form:errors path="UserAddress2.city"></form:errors>
                     </div>
                     </spring:bind>
                     </div>
                     <div class="col-md-2">
                     <spring:bind path="UserAddress2.state">
                     <div class=" form-group form-spacing">
                     <form:input type="text" required="true" path="UserAddress2.state" class="form-control" placeholder="State"
                        autofocus="true"></form:input>
                     <form:errors path="UserAddress2.state"></form:errors>
                     </div>
                     </spring:bind>
                     </div>
                     <div class="col-md-2">
                     <spring:bind path="UserAddress2.zipCode">
                     <div class=" form-group form-spacing">
                     <form:input type="text" required="true" path="UserAddress2.zipCode" class="form-control" placeholder="Zip Code"
                        autofocus="true"></form:input>
                     <form:errors path="UserAddress2.zipCode"></form:errors>
                     </div>
                     </spring:bind>
                     </div>
                     </div>
                     <div class="text-right">
                       <button type="submit" id="btnSave" class="btn btn-primary pull-right">Update Profile</button>
                     </div>
                  </div>
               </div>
            </form:form>
         </div>
      </div>
      <jsp:include page="/WEB-INF/views/headers/loginFooter.jsp" />
   </body>
</html>