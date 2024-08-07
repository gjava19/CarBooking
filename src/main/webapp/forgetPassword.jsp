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
            <div id="forgot-password-form" class="form">
                <h2>Forgot Password</h2>
                <form action="api/forget-password" method="post">
                    <input type="hidden" name="formId" value="verify">
                    <input type="text" id="forgot-password-username" name="username" placeholder="Username" required>
                    <input type="text" id="security-question" name="security-question" placeholder="What is your favourite dessert?" required>
                    <button type="submit">Verify Answer</button>
                </form>
                <div class="toggle">
                    <p>Remembered your password? <a href="login">Login here</a></p>
                    <p>Don't have an account? <a href="register">Register here</a></p>
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