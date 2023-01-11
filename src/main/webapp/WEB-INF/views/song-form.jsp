<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
          <form:form action="${contextPath}/song/saveSong" cssClass="form-horizontal"
                              method="post" modelAttribute="song" enctype="multipart/form-data">
            <div class="panel panel-info">
               <div class="panel-heading">
                  <div class="panel-title">Song Creation</div>
               </div>
               <div class="panel-body">

                     <form:hidden path="id" />
                     <div class="col-md-12 row">
                        <div class="col-md-4">
                           <spring:bind path="genre">
                              <div class="form-group form-spacing">
                                 <label for="genre" >Genre</label>
                                 <form:select path = "genre.id" required="true" class="form-control" >
                                    <option value = "" label = "Select Genre"/>
                                       <c:forEach var="genre" items="${genreDetailsList}">
                                    <option value="${genre.id}">${genre.genreType}</option>
                                    </c:forEach>
                                 </form:select>
                              </div>
                           </spring:bind>
                        </div>
                        <div class="col-md-4">
                           <spring:bind path="songName">
                              <div class="form-group form-spacing">
                                 <label for="songName" >Song Name</label>
                                 <form:input type="text"  required="true" path="songName" class="form-control" placeholder="Song Name"
                                    autofocus="true"></form:input>
                              </div>
                           </spring:bind>
                        </div>
                        <div class="col-md-4">
                           <spring:bind path="artist">
                              <div class="form-group form-spacing">
                                 <label for="artist" >Artist Name</label>
                                 <form:input type="text"  required="true" path="artist" class="form-control" placeholder="Artist Name"
                                    autofocus="true"></form:input>
                              </div>
                           </spring:bind>
                        </div>
                     </div>
                     <div class="col-md-12 row">
                        <div class="col-md-4">
                           <div class="form-group form-spacing">
                              <spring:bind path="minutes">
                                 <div class="col-md-5" style="padding-left: 0px;padding-right: 0px;">
                                    <label for="minutes" >Duration</label>
                                    <form:input type="number"  required="true" path="minutes" class="form-control" placeholder="Minutes"
                                       autofocus="true"></form:input>
                                 </div>
                              </spring:bind>
                              <div class="col-md-2" style="padding-left: 25px;padding-right: 0px;padding-top: 30px;"">:</div>
                              <spring:bind path="seconds">
                                 <div class="col-md-5" style="padding-left: 0px;padding-right: 0px;padding-top: 25px;">
                                    <form:input type="number"  required="true" path="seconds" class="form-control" placeholder="Seconds"
                                       autofocus="true"></form:input>
                                 </div>
                              </spring:bind>
                           </div>
                        </div>
                        <div class="col-md-4">
                           <spring:bind path="price">
                              <div class="form-group form-spacing">
                                 <label for="songName" >Price</label>
                                 <form:input type="number"  required="true" path="price" class="form-control" placeholder="Price"
                                    autofocus="true"></form:input>
                              </div>
                           </spring:bind>
                        </div>
                        <div class="col-md-4">
                           <spring:bind path="songFile">
                              <div class="form-group form-spacing ${status.error ? 'has-error' : ''}">
                                 <label for="fileName" >Song File</label>
                                 <form:input type = "file"   required="true" path="songFile" class="form-control" placeholder="FIle Name"
                                    autofocus="true"></form:input>
                                    <form:errors path="songFile"></form:errors>
                              </div>
                           </spring:bind>
                        </div>
                     </div>
                        <button type="submit" id="btnSave" class="btn btn-primary pull-right">Create Song</button>
               </div>

            </div>
</form:form>
         </div>
      </div>
      <jsp:include page="/WEB-INF/views/headers/loginFooter.jsp" />
   </body>
</html>