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
            <div id="register-form" class="form">
                <h2>Register</h2>
                <form action="api/register" method="post">
                    <input type="hidden" name = "formId" value = "register">
                    <input type="text" id="register-username" name="username" placeholder="Username" required>
                    <input type="password" id="register-password" name="password" placeholder="Password" required>
                    <input type="password" id="secret-word" name="secret-word" placeholder="Write your favourite desert as a secret word" required>
                    <button type="submit">Register</button>
                </form>
                <div class="toggle">
                    <p>Already have an account? <a href="login">Login here</a></p>
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