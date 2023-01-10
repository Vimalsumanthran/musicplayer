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
     <div class="panel-title">Purchase Request Details</div>
    </div>
    <div class="panel-body ">
    <div class="col-md-12 row">
        <div class="col-md-3"><b>Requested By : </b> ${purchaseRequest.cart.user.firstName} ${purchaseRequest.cart.user.lastName}</div>
        <div class="col-md-2"><b>Requested On : </b> <fmt:formatDate pattern = "dd-MM-yyyy" value = "${purchaseRequest.createdOn}" /></div>
    </div>

     <table class="table table-striped table-bordered">
      <tr>
       <th>Song Name</th>
       <th>Song Genre</th>
       <th>Artist Name</th>
       <th>Duration</th>
       <th>Price</th>
      </tr>

      <c:set var="total" value="${0}"/>
      <c:forEach var="tempItem" items="${items}">
        <c:set var="total" value="${total + tempItem.song.price}" />
       <c:url var="addToCartLink" value="/cart/addtoCart">
        <c:param name="songId" value="${tempItem.id}" />
       </c:url>

       <tr>
        <td>${tempItem.song.songName}</td>
        <td>${tempItem.song.genre.genreType}</td>
        <td>${tempItem.song.artist}</td>
        <td>${tempItem.song.minutes}:${tempItem.song.seconds}</td>
        <td class="text-right">&#x20b9; ${tempItem.song.price}</td>

       </tr>

      </c:forEach>
      <tr>
        <td colspan=5 class="text-right">Total Amount : &#x20b9; ${total}</td>
      <tr>

     </table>
      <c:url var="approveLink" value="/admin/approvePurchaseReq">
             <c:param name="appId" value="${purchaseRequest.id}" />
            </c:url>

            <c:url var="rejectLink" value="/admin/rejectPurchaseReq">
             <c:param name="appId" value="${purchaseRequest.id}" />
            </c:url>
               <div class=" text-right">
               <c:if test="${purchaseRequest.status == 1  }">
               <a class="btn btn-success" href="${approveLink}">Approve</a>
                         <a class="btn btn-danger" href="${rejectLink}"
                        onclick="if (!(confirm('Are you sure you want to reject this purchase?'))) return false">Reject</a>
                </c:if>
               </div>
    </div>
   </div>
  </div>

 </div>
 <jsp:include page="/WEB-INF/views/headers/loginFooter.jsp" />
 
</body>

</html>