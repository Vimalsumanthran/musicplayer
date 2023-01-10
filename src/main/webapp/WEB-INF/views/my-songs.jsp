<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<jsp:include page="/WEB-INF/views/headers/loginHeader.jsp" />

 <div class="container full-width">
  <div class="col-md-offset-1 col-md-10">
  
   <div class="panel panel-info">
    <div class="panel-heading">
     <div class="panel-title">My Songs</div>
    </div>
    <div class="panel-body">
     <table class="table table-striped table-bordered">
      <tr>
       <th>Song Name</th>
       <th>Song Type</th>
       <th>Artist Name</th>
       <th>Duration</th>
       <th>Download Count</th>
       <th>Action</th>
      </tr>

      <c:forEach var="tempSong" items="${songs}">

       <c:url var="downloadLink" value="/song/download">
        <c:param name="songId" value="${tempSong.song.id}" />
       </c:url>

       <tr>
        <td>${tempSong.song.songName}</td>
        <td>${tempSong.song.genre.genreType}</td>
        <td>${tempSong.song.artist}</td>
        <td>${tempSong.song.minutes}:${tempSong.song.seconds}</td>
        <td>${tempSong.song.downloadCount}</td>
        <td>

          <a class="btn btn-success" href="${downloadLink}">Download Song</a>
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