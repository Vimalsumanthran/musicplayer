<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

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
       <th>Purchased By</th>
        <th>Purchased On</th>
       <th>Action</th>
      </tr>

      <c:forEach var="purchaseRequest" items="${purchaseRequests}">

       <c:url var="approveLink" value="/admin/approvePurchaseReq">
        <c:param name="appId" value="${purchaseRequest.id}" />
       </c:url>

       <c:url var="rejectLink" value="/admin/rejectPurchaseReq">
        <c:param name="appId" value="${purchaseRequest.id}" />
       </c:url>
        <c:url var="viewPurchaseLink" value="/admin/viewPurchaseReq">
           <c:param name="reqId" value="${purchaseRequest.id}" />
        </c:url>

       <tr>
        <td>${purchaseRequest.cart.user.firstName} ${purchaseRequest.cart.user.lastName}</td>
        <td><fmt:formatDate pattern = "dd-MM-yyyy" value = "${purchaseRequest.createdOn}" /></td>

        <td>
         <a class="btn btn-success" href="${approveLink}">Approve</a>
          <a class="btn btn-danger" href="${rejectLink}"
         onclick="if (!(confirm('Are you sure you want to reject this purchase?'))) return false">Reject</a>
         <a class="btn btn-info" href="${viewPurchaseLink}">View Details</a>
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