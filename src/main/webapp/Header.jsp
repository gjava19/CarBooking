<%@ page import="MVController.UserController" %>
<%@ page import="Models.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
        <%
            Cookie[] cookies = request.getCookies();
            String me = null;
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("whoami")) {
                        me = cookie.getValue();

                    }
                }
            }
            UserController userController = (UserController) application.getAttribute("userController");



            if(me != null){
//                User myuser = userController.getUserInfo(Integer.parseInt(me));
//                if(myuser != null) {
//                    System.out.println("myuser" + myuser.toString());
//                }
        %>
            <a href="profile" class="roboto-regular login-button">Hi <%=me%> !</a>
            <%}else{%>
            <a href="login" class="roboto-regular login-button">Login</a>
            <%}%>

    </div>

  </div>
</body>
</html>
