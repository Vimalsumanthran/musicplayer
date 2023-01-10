<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<jsp:include page="/WEB-INF/views/headers/loginHeader.jsp" />

 <div class="container full-width">
  <div class="col-md-offset-1 col-md-10">
   <div class="panel panel-info">
    <div class="panel-heading">
     <div class="panel-title">Pending User Application List</div>
    </div>
    <div class="panel-body">
     <table class="table table-striped table-bordered">
      <tr>
       <th>First Name</th>
       <th>Last Name</th>
       <th>Email</th>
       <th>Gender</th>
       <th>Action</th>
      </tr>

      <c:forEach var="regApps" items="${userRegapps}">

       <c:url var="approveLink" value="/admin/approveUser">
        <c:param name="appId" value="${regApps.id}" />
       </c:url>

       <c:url var="rejectLink" value="/admin/rejectUser">
        <c:param name="appId" value="${regApps.id}" />
       </c:url>

       <tr>
        <td>${regApps.user.firstName}</td>
        <td>${regApps.user.lastName}</td>
        <td>${regApps.user.email}</td>
        <td>${regApps.user.gender.type}</td>

        <td>
         <!-- display the update link --> <a href="${approveLink}">Approve</a>
         | <a href="${rejectLink}"
         onclick="if (!(confirm('Are you sure you want to reject this user?'))) return false">Reject</a>
        </td>

       </tr>

      </c:forEach>

     </table>

    </div>
   </div>
  </div>

 </div>
 <jsp:include page="/WEB-INF/views/headers/loginFooter.jsp" />
 
</body>

</html>