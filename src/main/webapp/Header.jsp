<%@ page import="MVController.UserController" %>
<%@ page import="Models.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/index.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/header.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100;0,300;0,400;0,500;0,700;0,900;1,100;1,300;1,400;1,500;1,700;1,900&display=swap" rel="stylesheet">
</head>
<body>
<div class="header">
    <img alt="QuizGradIcon" src="${pageContext.request.contextPath}/images/QuizGrandIcon.png" class="header-icon"/>
    <div class="header-right">
        <c:choose>
            <c:when test="${cookie.whoami.value != null}">
                <div class="header-menu">
                    <div><a href="${pageContext.request.contextPath}/profile" class="roboto-regular menu-link"><b><c:out value="${cookie.whoami.value}"/>'s Profile </b></a></div>
                    <div><a href="${pageContext.request.contextPath}/quiz" class="roboto-regular menu-link"><b>Quizzes</b> </a></div>
                    <div><a href="${pageContext.request.contextPath}/notifications.jsp" class="roboto-regular menu-link"><b>Notifications</b> </a></div>
                    <c:choose>
                        <c:when test="${cookie.whoami.value == 'admin'}">
                            <div><a href="${pageContext.request.contextPath}/admin.jsp" class="roboto-regular menu-link"><b>Admin</b> </a></div>
                        </c:when>
                        <c:otherwise>
                        </c:otherwise>
                    </c:choose>
                </div>
                <a href="${pageContext.request.contextPath}/logout" class="roboto-regular login-button">Logout</a>
            </c:when>
            <c:otherwise>
                <div class="header-menu">
                    <div class="roboto-regular menu-link">How it works?</div>
                    <div class="roboto-regular menu-link">Features</div>
                    <div class="roboto-regular menu-link">About us</div>
                </div>
                <a href="${pageContext.request.contextPath}/login" class="roboto-regular login-button">Login</a>
            </c:otherwise>
        </c:choose>
    </div>
</div>
</body>
</html>
