<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<jsp:include page="/WEB-INF/views/headers/loginHeader.jsp" />

 <div class="container full-width">
    <h4 class="form-signin-heading text-center text-success p-2">
       <c:out value="${successFlashAttr}"/>
    </h4>
    <h4 class="form-signin-heading text-center text-danger p-2">
           <c:out value="${errorFlashAttr}"/>
        </h4>
  <div class="col-md-offset-1 col-md-10">
    <c:set var="contextPath" value="${pageContext.request.contextPath}"/>

   <div class="panel panel-info">
    <div class="panel-heading row" style="margin: 0px;">
    <div class="col-md-12" style="padding: 0px;">
     <div class="panel-title col-md-6" style="padding: 0px;">Song List</div>
     <c:if test="${loggedUser.isAdmin == true}">
     <div class="text-right col-md-6"> <a href="${contextPath}/admin/create-song" id="btnSave" class="btn btn-success pull-right">Create Song</a></div>
     </c:if>
     </div>
    </div>
    <div class="panel-body row" style="padding: 25px;">
    <div class="col-md-12 ">
     <form id="filterForm" method="GET"  action="${contextPath}/song/list">
    <div class="col-md-7"></div>
    <div class="col-md-1 text-right" style="padding-top:10px;padding-bottom:20px">Filter By : </div>
    <div class="col-md-3">
        <select name="genre" id="genre" class="form-control" >
            <option value = "" label = "Select Genre"/>
               <c:forEach var="genre" items="${genreDetailsList}">
            <option value="${genre.id}"<c:if test="${genre.id == filterGenre}"> selected </c:if>>${genre.genreType}</option>
            </c:forEach>
         </select>
    </div>
    <div class="col-md-1"><button type="submit" id="btnSave" class="btn btn-info pull-left">Search</a></div>
    </form>
    </div>
     <table class="table table-striped table-bordered">
      <tr>
       <th>Song Name</th>
       <th>Song Genre</th>
       <th>Artist Name</th>
       <th>Duration</th>
       <th>Download Count</th>
       <th>Action</th>
      </tr>

      <!-- loop over and print our students -->
      <c:forEach var="tempSong" items="${songs}">

       <c:url var="addToCartLink" value="/cart/addtoCart">
        <c:param name="songId" value="${tempSong.id}" />
       </c:url>

       <tr>
        <td>${tempSong.songName}</td>
        <td>${tempSong.genre.genreType}</td>
        <td>${tempSong.artist}</td>
        <td>${tempSong.minutes}:${tempSong.seconds}</td>
        <td>${tempSong.downloadCount}</td>
        <td>
        <c:if test="${loggedUser.isBlocked == false  }">
            <a class="btn btn-success" href="${addToCartLink}">Add to cart</a>
         </c:if>
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