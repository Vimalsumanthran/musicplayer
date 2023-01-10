<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<jsp:include page="/WEB-INF/views/headers/basicHeader.jsp" />
<div class="container center_div border" style="padding-top:10px">
   <form:form method="POST" modelAttribute="userForm" class="form-signin">
      <c:if test="${not empty SuccessMsg}">
         <h4 class="form-signin-heading text-center text-success p-2">
            <c:out value="${SuccessMsg}"/>
         </h4>
      </c:if>
      <h2 class="form-signin-heading text-center" style="padding:10px">Create your account</h2>
      <div class="col-md-12 row">
         <div class="col-md-6">
            <spring:bind path="User.firstName">
               <div class=" form-group ${status.error ? 'has-error' : ''}">
                  <form:input type="text" required="true" path="User.firstName" class="form-control" placeholder="FirstName"
                     autofocus="true"></form:input>
                  <form:errors path="User.firstName"></form:errors>
               </div>
            </spring:bind>
         </div>
         <div class="col-md-6">
            <spring:bind path="User.lastName">
               <div class="form-group ${status.error ? 'has-error' : ''}">
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
               <div class=" form-group ${status.error ? 'has-error' : ''}">
                  <form:input type="email" required="true" path="User.email" class="form-control" placeholder="Email"
                     autofocus="true"></form:input>
                  <form:errors path="User.email"></form:errors>
               </div>
            </spring:bind>
         </div>
         <div class="col-md-6">
            <spring:bind path="User.gender.id">
               <div class="form-group ${status.error ? 'has-error' : ''}">
                  <form:select path = "User.gender.id" required="true" class="form-control" >
                     <option value = "" label = "Select Gender"/>
                        <c:forEach var="gender" items="${genderDetailsList}">
                     <option value="${gender.id}">${gender.type}</option>
                     </c:forEach>
                  </form:select>
                  <form:errors path="User.gender"></form:errors>
               </div>
            </spring:bind>
         </div>
      </div>
      <div class="col-md-12 row">
         <div class="col-md-12"><span ><b>Correspondence Address</b></span></div>
         <div class="col-md-3">
            <spring:bind path="UserAddress1.addressLine1">
               <div class=" form-group ${status.error ? 'has-error' : ''}">
                  <form:input type="text" required="true" path="UserAddress1.addressLine1" class="form-control" placeholder="Address Line 1"
                     autofocus="true"></form:input>
                  <form:errors path="UserAddress1.addressLine1"></form:errors>
               </div>
            </spring:bind>
         </div>
         <div class="col-md-3">
            <spring:bind path="UserAddress1.addressLine2">
               <div class=" form-group ${status.error ? 'has-error' : ''}">
                  <form:input type="text" required="true" path="UserAddress1.addressLine2" class="form-control" placeholder="Address Line 2"
                     autofocus="true"></form:input>
                  <form:errors path="UserAddress1.addressLine2"></form:errors>
               </div>
            </spring:bind>
         </div>
         <div class="col-md-2">
            <spring:bind path="UserAddress1.city">
               <div class=" form-group ${status.error ? 'has-error' : ''}">
                  <form:input type="text" required="true" path="UserAddress1.city" class="form-control" placeholder="City"
                     autofocus="true"></form:input>
                  <form:errors path="UserAddress1.city"></form:errors>
               </div>
            </spring:bind>
         </div>
         <div class="col-md-2">
            <spring:bind path="UserAddress1.state">
               <div class=" form-group ${status.error ? 'has-error' : ''}">
                  <form:input type="text" required="true" path="UserAddress1.state" class="form-control" placeholder="State"
                     autofocus="true"></form:input>
                  <form:errors path="UserAddress1.state"></form:errors>
               </div>
            </spring:bind>
         </div>
         <div class="col-md-2">
            <spring:bind path="UserAddress1.zipCode">
               <div class=" form-group ${status.error ? 'has-error' : ''}">
                  <form:input type="text" required="true" path="UserAddress1.zipCode" class="form-control" placeholder="Zip Code"
                     autofocus="true"></form:input>
                  <form:errors path="UserAddress1.zipCode"></form:errors>
               </div>
            </spring:bind>
         </div>
      </div>
      <div class="col-md-12 row">
         <div class="col-md-12"><span><b>Permanent Address</b></span></div>
         <div class="col-md-3">
            <spring:bind path="UserAddress2.addressLine1">
               <div class=" form-group ${status.error ? 'has-error' : ''}">
                  <form:input type="text" required="true" path="UserAddress2.addressLine1" class="form-control" placeholder="Address Line 1"
                     autofocus="true"></form:input>
                  <form:errors path="UserAddress2.addressLine1"></form:errors>
               </div>
            </spring:bind>
         </div>
         <div class="col-md-3">
            <spring:bind path="UserAddress2.addressLine2">
               <div class=" form-group ${status.error ? 'has-error' : ''}">
                  <form:input type="text" required="true" path="UserAddress2.addressLine2" class="form-control" placeholder="Address Line 2"
                     autofocus="true"></form:input>
                  <form:errors path="UserAddress2.addressLine2"></form:errors>
               </div>
            </spring:bind>
         </div>
         <div class="col-md-2">
            <spring:bind path="UserAddress2.city">
               <div class=" form-group ${status.error ? 'has-error' : ''}">
                  <form:input type="text" required="true" path="UserAddress2.city" class="form-control" placeholder="City"
                     autofocus="true"></form:input>
                  <form:errors path="UserAddress2.city"></form:errors>
               </div>
            </spring:bind>
         </div>
         <div class="col-md-2">
            <spring:bind path="UserAddress2.state">
               <div class=" form-group ${status.error ? 'has-error' : ''}">
                  <form:input type="text" required="true" path="UserAddress2.state" class="form-control" placeholder="State"
                     autofocus="true"></form:input>
                  <form:errors path="UserAddress2.state"></form:errors>
               </div>
            </spring:bind>
         </div>
         <div class="col-md-2">
            <spring:bind path="UserAddress2.zipCode">
               <div class=" form-group ${status.error ? 'has-error' : ''}">
                  <form:input type="text" required="true" path="UserAddress2.zipCode" class="form-control" placeholder="Zip Code"
                     autofocus="true"></form:input>
                  <form:errors path="UserAddress2.zipCode"></form:errors>
               </div>
            </spring:bind>
         </div>
      </div>
      <div class="col-md-12 row">
         <div class="col-md-12">
            <spring:bind path="User.username">
               <div class="form-group ${status.error ? 'has-error' : ''}">
                  <form:input type="text" path="User.username" required="true" class="form-control" placeholder="Username"
                     autofocus="true"></form:input>
                  <form:errors path="User.username"></form:errors>
               </div>
            </spring:bind>
         </div>
      </div>
      <div class="col-md-12 row">
         <div class="col-md-6">
            <spring:bind path="User.password">
               <div class="form-group ${status.error ? 'has-error' : ''}">
                  <form:input type="password" path="User.password" required="true" class="form-control" placeholder="Password"></form:input>
                  <form:errors path="User.password"></form:errors>
               </div>
            </spring:bind>
         </div>
         <div class="col-md-6">
            <spring:bind path="User.passwordConfirm">
               <div class="form-group ${status.error ? 'has-error' : ''}">
                  <form:input type="password" required="true" path="User.passwordConfirm" class="form-control"
                     placeholder="Confirm your password"></form:input>
                  <form:errors path="User.passwordConfirm"></form:errors>
               </div>
            </spring:bind>
         </div>
      </div>
      <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
   </form:form>
</div>
<jsp:include page="/WEB-INF/views/headers/loginFooter.jsp" />
</body>
</html>