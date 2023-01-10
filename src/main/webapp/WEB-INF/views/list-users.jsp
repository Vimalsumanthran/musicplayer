<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<jsp:include page="/WEB-INF/views/headers/loginHeader.jsp" />

 <div class="container full-width">
  <div class="col-md-offset-1 col-md-10">
  <c:set var="contextPath" value="${pageContext.request.contextPath}"/>

   <div class="panel panel-info">
    <div class="panel-heading row" style="margin: 0px;">
    <div class="col-md-12" style="padding: 0px;">
     <div class="panel-title col-md-6" style="padding: 0px;">
     <c:choose>
         <c:when test="${not empty adminList}">
             Admin List
         </c:when>
         <c:otherwise>
             User List
         </c:otherwise>
     </c:choose>
     </div>
      <div class="text-right col-md-6">
           <c:choose>
               <c:when test="${not empty adminList}">
                   <a href="${contextPath}/active-users"  class="btn btn-success pull-right">User List</a>
               </c:when>
               <c:otherwise>
                   <c:if test="${loggedUser.isSuperAdmin == true}">
                    <a href="${contextPath}/superadmin/admin-list"  class="btn btn-success pull-right">Admin List</a>
                   </c:if>
               </c:otherwise>
           </c:choose>

      </div>
      </div>
    </div>
    <div class="panel-body">
     <table class="table table-striped table-bordered">
      <tr>
       <th>First Name</th>
       <th>Last Name</th>
       <th>User Name</th>
       <th>Email</th>
       <th>Gender</th>
       <th width="25%">Action</th>
      </tr>

      <c:forEach var="tempUser" items="${users}">

       <c:url var="makeAsAdminLink" value="/superadmin/makeasadmin">
        <c:param name="userId" value="${tempUser.id}" />
       </c:url>

       <c:url var="removeAdminLink" value="/superadmin/removeadmin">
        <c:param name="userId" value="${tempUser.id}" />
       </c:url>
       <c:url var="blockUserLink" value="/admin/blockUser">
               <c:param name="userId" value="${tempUser.id}" />
       </c:url>
      <c:url var="unBlockUserLink" value="/admin/unblockUser">
              <c:param name="userId" value="${tempUser.id}" />
      </c:url>
      <c:url var="viewUserLink" value="/admin/viewUser">
         <c:param name="userId" value="${tempUser.id}" />
      </c:url>
 <c:if test="${loggedUser.id != tempUser.id}">
       <tr>
        <td>${tempUser.firstName}</td>
        <td>${tempUser.lastName}</td>
        <td>${tempUser.username}</td>
        <td>${tempUser.email}</td>
        <td>${tempUser.gender.type}</td>
        <td width="25%">


         <c:if test="${tempUser.isBlocked==false}"><a class="btn btn-warning" href="${blockUserLink}" onclick="if (!(confirm('Are you sure you want to block user from purchasing?'))) return false"> Block </a></c:if>
         <c:if test="${tempUser.isBlocked==true}"><a class="btn btn-info" href="${unBlockUserLink}"> Un Block </a></c:if>
         <c:if test="${loggedUser.isSuperAdmin == true && tempUser.isAdmin==false}"><a class="btn btn-success" href="${makeAsAdminLink}"> Make as Admin </a></c:if>
         <c:if test="${loggedUser.isSuperAdmin == true && tempUser.isAdmin==true}"><a class="btn btn-primary" href="${removeAdminLink}"> Remove Admin </a></c:if>
            <a class="btn btn-default" href="${viewUserLink}"> View </a>
        </td>

       </tr>
</c:if>
      </c:forEach>

     </table>

    </div>
   </div>
  </div>

 </div>
 <jsp:include page="/WEB-INF/views/headers/loginFooter.jsp" />
 
</body>

</html>