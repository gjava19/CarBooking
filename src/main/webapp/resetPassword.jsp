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
            <div id="reset-password-form" class="form" style="display: none;">
                <h2>Reset Password</h2>
                <form action="api/reset-password" method="post">
                    <input type="hidden" name="formId" value="reset">
                    <input type="text" id="reset-password-username" name="username" placeholder="Username" readonly required>
                    <input type="password" id="new-password" name="password" placeholder="New Password" required>
                    <button type="submit">Reset Password</button>
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