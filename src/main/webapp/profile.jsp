<%@ page import="Models.User" %>
<%@ page import="java.util.HashSet" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="styles/profile.css">
</head>
<body>

<div class="profile-container">
    <jsp:include page="Header.jsp"/>

    <%
        User userInfo = (User) request.getAttribute("userInfo");
        HashSet<String> friendList = userInfo.getFriends();

    %>

    <main class="profile-main">
        <div class="profile-info">
            <img src="images/register.svg" alt="User Profile Picture" class="profile-picture">
            <h1 class="username"><%=userInfo.getUsername()%></h1>
        </div>
        <div class="friends-list">
            <h2>Friends</h2>
            <% if(friendList.isEmpty()){%>
                <p>You have no friends</p>
            <%}else{
            %><ul class="friends"><%
                for(String curFriend : friendList){
                    %>
            <li class="friend">
                <img src="images/register.svg" alt="Friend 1" class="friend-picture">
                <span class="friend-name"><%=curFriend%></span>
            </li>
            <%
                }
            }%>
            </ul>
        </div>
    </main>
    <footer class="footer">
        <p>&copy; 2024 QuizGrad. All rights reserved.</p>
    </footer>
</div>

</body>
</html>
