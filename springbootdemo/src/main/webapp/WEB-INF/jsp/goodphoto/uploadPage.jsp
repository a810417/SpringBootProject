<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Upload Photo Page</title>
<c:set var="contextRoot" value="${pageContext.request.contextPath}" />
</head>
<body>
<jsp:include page="../layout/navbar.jsp"></jsp:include>
<div class="container">
   
<h1>Upload Photo</h1>
<br/>
<h3>${errorMsg}</h3>

<form action="${pageContext.request.contextPath}/uploadPhoto" method="post" enctype="multipart/form-data">
  Photo Name : <input id="photoName" name="photoName" />
  <br />
  <br/>
  Choose : <input type="file" name="file" />
    <br />
    <br/>
    <input type="submit" value="Upload">

</form>


</div>
</body>
</html>