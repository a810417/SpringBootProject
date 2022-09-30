<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>New Message</title>

<c:set var="contextRoot" value="${pageContext.request.contextPath}" />
<link href="${contextRoot}/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<jsp:include page="../layout/navbar.jsp"></jsp:include>

<div class= "container">
<div class="card">
  <h5 class="card-header">New Message</h5>
  <div class="card-body">
   <form:form action="${contextRoot}/messages/post" method="post" modelAttribute="messages">
    
   <div class="input-group">
      <form:textarea class="form-control" path="text"/>
   </div>
   <br />
    <button type="submit" class="btn btn-primary mb-2">送出</button>
<!--      <input type="submit" name="submit" value="送出" /> -->
   </form:form>
    
  </div>
</div>
<div class="card">
  <h5 class="card-header">Lastest Message Time: <span><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss EEEE" value="${lastestMsg.added}"/></span></h5>
  <div class="card-body">

${lastestMsg.text}

 </div>
</div>
</div>
</body>
</html>