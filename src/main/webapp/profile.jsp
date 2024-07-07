<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 7/7/2024
  Time: 5:20 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="styles/profile.css">
</head>
<body>

<div class="profile-container">
    <jsp:include page="Header.jsp"/>
    <main class="profile-main">
        <div class="profile-info">
            <img src="images/register.svg" alt="User Profile Picture" class="profile-picture">
            <h1 class="username">Username</h1>
        </div>
        <div class="friends-list">
            <h2>Friends</h2>
            <ul class="friends">
                <li class="friend">
                    <img src="images/register.svg" alt="Friend 1" class="friend-picture">
                    <span class="friend-name">Friend 1</span>
                </li>
                <li class="friend">
                    <img src="images/register.svg" alt="Friend 2" class="friend-picture">
                    <span class="friend-name">Friend 2</span>
                </li>
                <li class="friend">
                    <img src="images/register.svg" alt="Friend 3" class="friend-picture">
                    <span class="friend-name">Friend 3</span>
                </li>
            </ul>
        </div>
    </main>
    <footer class="footer">
        <p>&copy; 2024 QuizGrad. All rights reserved.</p>
    </footer>
</div>

</body>
</html>
