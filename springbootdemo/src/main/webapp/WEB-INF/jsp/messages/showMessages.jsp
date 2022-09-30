<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
            <!DOCTYPE html>
            <html>

            <head>
                <meta charset="UTF-8">
                <title>Show Messages</title>
                <c:set var="contextRoot" value="${pageContext.request.contextPath}" />
            </head>

            <body>
                <jsp:include page="../layout/navbar.jsp"></jsp:include>
                <div class="container">
                    <h1>Show Messages Page</h1>

                    <c:forEach var="message" items="${page.content}">

                        <div class="card">
                            <div class="card-header">
                                Lastest Message Time:
                                <span>
                                    <fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss EEEE" value="${message.added}" />
                                </span>
                                <span> </span>
                            </div>
                            <div class="card-body" id="${message.id}">

                                ${message.text}

                            </div>
                            <!-- PathVariable -->
                            <!-- <a href="${contextRoot}/messages/edit/${message.id}">Edit</a> -->
                            <!-- RequestParam -->
                            <div class="edit-place">
                                <a href="${contextRoot}/messages/edit?id=${message.id}">Edit</a> | 
                                <a onclick="return confirm('確定刪除?')" href="${contextRoot}/messages/del/${message.id}">Delete</a>
                            </div>
                        </div>

                    </c:forEach>
                    <br />
                    <c:forEach var="pageNumber" begin="1" end="${page.totalPages}">
                        <c:choose>
                            <c:when test="${pageNumber != page.number+1}">
                                <a href="${contextRoot}/messages/page?p=${pageNumber}" class="pageNum">${pageNumber}</a>
                                <!-- <c:if test="${pageNumber < page.totalPages}">|</c:if> -->
                            </c:when>
                            <c:otherwise>
                                ${pageNumber}
                                <!-- <c:if test="${pageNumber < page.totalPages}">|</c:if> -->
                            </c:otherwise>

                        </c:choose>
                        <c:if test="${pageNumber != page.totalPages}">|</c:if>
                    </c:forEach>
                </div>

            </body>

            </html>