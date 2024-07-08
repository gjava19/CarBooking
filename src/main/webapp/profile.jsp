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
    <main class="profile-container">
        <div class="profile-main">
            <div class="profile-info">
                <img src="images/register.svg" alt="User Profile Picture" class="profile-picture">
                <h1 class="username"><c:out value="${userInfo.getUsername()}"/></h1>
            </div>
            <div class="friend-data">
                <c:set value="${userInfo.getFriends()}" var="friendList"/>
                <div class="friends-list">
                    <h2>My Friends</h2>
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
                </div>

                <c:set value="${userInfo.getRequested()}" var="friendRequestList"/>
                <div class="friends-list">
                    <h2>Friend Request</h2>

                    <c:choose>
                        <c:when test="${friendRequestList.isEmpty()}">
                            <p>You have no friend requests</p>
                        </c:when>
                        <c:otherwise>
                            <ul class="friends">
                                <c:forEach var="curFriendRequest" items="${friendRequestList}">
                                    <li class="friend">
                                        <img src="images/register.svg" alt="Friend 1" class="friend-picture">
                                        <span class="friend-name"><c:out value="${curFriendRequest}"/></span>
                                    </li>
                                </c:forEach>
                            </ul>
                        </c:otherwise>
                    </c:choose>
                </div>
                <c:set value="${userInfo.getSent()}" var="sentFriendRequestList"/>
                <div class="friends-list">
                    <h2>Sent Friend Request</h2>

                    <c:choose>
                        <c:when test="${sentFriendRequestList.isEmpty()}">
                            <p>You have no sent friend requests</p>
                        </c:when>
                        <c:otherwise>
                            <ul class="friends">
                                <c:forEach var="curSentFriendRequest" items="${sentFriendRequestList}">
                                    <li class="friend">
                                        <img src="images/register.svg" alt="Friend 1" class="friend-picture">
                                        <span class="friend-name"><c:out value="${curSentFriendRequest}"/></span>
                                    </li>
                                </c:forEach>
                            </ul>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
            <div class="button-container">
                <a class="button" href="quiz"> Quizes </a>
            </div>
        </div>
    </main>
    <footer class="footer">
        <p>&copy; 2024 QuizGrad. All rights reserved.</p>
    </footer>
</div>

</body>
</html>
