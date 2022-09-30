<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Page</title>
<c:set var="contextRoot" value="${pageContext.request.contextPath}" />
</head>
<body>
<jsp:include page="../layout/navbar.jsp"></jsp:include>
<h1>修改留言</h1>


<div class="card">
  <div class="card-header">
   Type Message
  </div>
  <div class="card-body">
   
   <form:form  method="post" modelAttribute="messages">
   
   <form:input path="id" type="hidden" />
   <form:input path="added" type="hidden" />
   
    
   <div class="input-group">
      <form:textarea class="form-control" path="text"/>
   </div>
   <br />
    <button type="submit" class="btn btn-primary mb-2">送出</button>
<!--      <input type="submit" name="submit" value="送出" /> -->
   </form:form>
   
  </div>
</div>
</body>
</html>