<%@ page import="MVController.UserController" %>
<%@ page import="Models.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="styles/index.css">
    <link rel="stylesheet" href="styles/header.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100;0,300;0,400;0,500;0,700;0,900;1,100;1,300;1,400;1,500;1,700;1,900&display=swap" rel="stylesheet">
</head>
<body>
  <div class="header">
    <a href="home"> <img alt="QuizGradIcon" src="images/QuizGrandIcon.png" class="header-icon"/> </a>

    <div class="header-right">
      <div class="header-menu">
        <div class="roboto-regular menu-item">How it works?</div>
        <div class="roboto-regular menu-item">Features</div>
        <div class="roboto-regular menu-item">About us</div>
      </div>
        <c:choose>
            <c:when test="${cookie.whoami.value != null}">
                <a href="profile" class="roboto-regular login-button">Hi <c:out value="${cookie.whoami.value}"/> !</a>
            </c:when>
            <c:otherwise>
                <a href="login" class="roboto-regular login-button">Login</a>
            </c:otherwise>
        </c:choose>
    </div>

  </div>
</body>
</html>
