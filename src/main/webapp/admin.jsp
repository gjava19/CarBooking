<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Administration</title>
    </head>
    <body>
        <form action="adminDeleteUser" method="post">
            <input type="text" name="username"/>
            <input type="submit" value="Delete User">
        </form>
        <form action="admin" method="post">
            <input type="text" name="quizName"/>
            <input type="submit" value="Delete Quiz">
        </form>
    </body>
</html>
