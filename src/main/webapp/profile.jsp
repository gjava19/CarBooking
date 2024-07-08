<%@ page import="Models.User" %>
<%@ page import="java.util.HashSet" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>QuizGrand - Profile</title>
    <link rel="stylesheet" href="styles/profile.css">
</head>
<body>


    <jsp:include page="Header.jsp"/>
    <c:set value="${userInfo.getFriends()}" var="friendList"/>

    <main class="profile-container">
        <div class="profile-main">
        <div class="profile-info">
            <img src="images/register.svg" alt="User Profile Picture" class="profile-picture">
            <h1 class="username"><c:out value="${userInfo.getUsername()}"/></h1>
        </div>
        <div class="friends-list">
            <h2>Friends</h2>

            <c:choose>
                <c:when test="${friendList.isEmpty()}">
                    <p>You have no friends</p>
                </c:when>
                <c:otherwise>
                    <ul class="friends">
                        <c:forEach var="curFriend" items="${friendList}">
                            <li class="friend">
                                <img src="images/register.svg" alt="Friend 1" class="friend-picture">
                                <span class="friend-name"><c:out value="${curFriend}"/></span>
                            </li>
                        </c:forEach>
                    </ul>
                </c:otherwise>
            </c:choose>
            <div class="button-container">
                <a class="button" href="quiz"> Quizes </a>
            </div>
        </div>
        </div>
    </main>
    <footer class="footer">
        <p>&copy; 2024 QuizGrad. All rights reserved.</p>
    </footer>
</div>

</body>
</html>
