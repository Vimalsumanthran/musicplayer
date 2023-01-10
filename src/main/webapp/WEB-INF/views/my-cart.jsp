<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>

<jsp:include page="/WEB-INF/views/headers/loginHeader.jsp" />

 <div class="container full-width">
  <div class="col-md-offset-1 col-md-10">
  
   <div class="panel panel-info">
    <div class="panel-heading">
     <div class="panel-title">My Cart Items</div>
    </div>
    <div class="panel-body">
     <table class="table table-striped table-bordered">
      <tr>
       <th>Song Name</th>
       <th>Song Genre</th>
       <th>Artist Name</th>
       <th>Duration</th>
       <th>Price</th>
       <th>Action</th>
      </tr>

      <c:set var="total" value="${0}"/>
      <c:forEach var="tempItem" items="${items}">
        <c:set var="total" value="${total + tempItem.song.price}" />
       <c:url var="removeFromCartLink" value="/cart/removeFromCart">
        <c:param name="itemId" value="${tempItem.id}" />
       </c:url>
       <tr>
        <td>${tempItem.song.songName}</td>
        <td>${tempItem.song.genre.genreType}</td>
        <td>${tempItem.song.artist}</td>
        <td>${tempItem.song.minutes}:${tempItem.song.seconds}</td>
        <td class="text-right">&#x20b9; ${tempItem.song.price}</td>
        <td><a class="btn btn-warning" href="${removeFromCartLink}" onclick="if (!(confirm('Are you sure you want to remove song from cart?'))) return false"> Remove </a></td>
       </tr>

      </c:forEach>
      <tr>
        <td colspan=6 class="text-right">Total Amount : &#x20b9; ${total}</td>
      <tr>

     </table>
     <c:url var="puchaseCartLink" value="/cart/purchaseCart"></c:url>
               <div class=" text-right">
                <c:if test="${loggedUser.isBlocked == false &&  fn:length(items)>0 }">
                  <a href="${puchaseCartLink}"  class="btn btn-info pull-right">Purchase Now</a>
                 </c:if>
               </div>
    </div>
   </div>
  </div>

 </div>
 <jsp:include page="/WEB-INF/views/headers/loginFooter.jsp" />
 
</body>

</html>