<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="login-page">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>QuizGrad Login</title>
    <link rel="stylesheet" href="styles/styles.css">

</head>
<body>
<jsp:include page="Header.jsp"/>
<main>

    <div class="out-container">
    <div class="left-container">
        <div id="login-form" class="form">
            <h2>Login</h2>
            <form action="api/login" method="post">
                <input type="hidden" name = "formId" value = "login">
                <input type="text" id="login-username" name="username" placeholder="Username" required>
                <input type="password" id="login-password" name="password" placeholder="Password" required>
                <button type="submit">Login</button>
            </form>
            <div class="toggle">
                <p>Don't have an account? <a href="register">Register here</a></p>
                <p>Forgot your password? <a href="forget-password">Reset here</a></p>
            </div>
        </div>


    </div>
        <div class="right-container">
            <img src="images/register.svg" alt="Graduation Cap"/>
        </div>
    </div>
</main>
</body>
</html>