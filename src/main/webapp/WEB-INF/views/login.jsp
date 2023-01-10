<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<jsp:include page="/WEB-INF/views/headers/basicHeader.jsp" />
<div class="container center_div border" style="padding:50px;width: 500px;">
   <h4 class="form-signin-heading text-center text-success p-2">
      <c:out value="${flashAttr}"/>
   </h4>
   <c:set var="contextPath" value="${pageContext.request.contextPath}"/>
   <form method="POST" action="${contextPath}/login" class="form-signin">
      <h2 class="form-signin-heading text-center" style="padding:5px">Login</h2>
      <div class="col-md-12 row">
         <div class="col-md-12">
            <div class="form-group ">
               <input name="username" type="text" class="form-control" placeholder="Username"/>
            </div>
         </div>
      </div>
      <div class="col-md-12 row">
         <div class="col-md-12">
            <div class="form-group">
               <input name="password" type="password" class="form-control" placeholder="Password"/>
            </div>
         </div>
      </div>
      <div class="col-md-12 row text-center">
         <div class="col-md-12">
            <div class="form-group ${error != null ? 'has-error' : ''}">
               <span>${message}</span>
               <span>${error}</span>
            </div>
         </div>
      </div>
      <div class="col-md-12 row" style="padding:5px">
         <div class="col-md-12"><button class="btn btn-lg btn-primary btn-block" type="submit">Log In</button></div>
      </div>
      <div class="col-md-12 row" style="padding:5px">
         <div class="col-md-12"><a href="${contextPath}/registration" class="btn btn-lg btn-default btn-block" type="submit">Sign Up</a></div>
      </div>
      <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
   </form>
</div>
<jsp:include page="/WEB-INF/views/headers/loginFooter.jsp" />
</body>
</html>