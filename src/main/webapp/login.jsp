<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="login-page">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>QuizGrad Login</title>
    <link rel="stylesheet" href="styles/login.css">

    <script>
        function toggleForms(formId) {
            var forms = document.getElementsByClassName('form');
            for (var i = 0; i < forms.length; i++) {
                forms[i].style.display = 'none';
            }
            document.getElementById(formId).style.display = 'block';
        }

        function verifySecurityQuestion(event) {
            event.preventDefault();
            var email = document.getElementById('forgot-password-email').value;
            var answer = document.getElementById('security-question').value;
            var correctAnswer = 'chocolate'; // This should be fetched and verified from the server in a real application

            if (answer.toLowerCase() === correctAnswer.toLowerCase()) {
                document.getElementById('reset-password-email').value = email;
                toggleForms('reset-password-form');
            } else {
                alert('Incorrect answer. Please try again.');
            }
        }
    </script>
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
                <p>Don't have an account? <a onclick="toggleForms('register-form')">Register here</a></p>
                <p>Forgot your password? <a onclick="toggleForms('forgot-password-form')">Reset here</a></p>
            </div>
        </div>
        <div id="register-form" class="form" style="display: none;">
            <h2>Register</h2>
            <form method="post">
                <input type="hidden" name = "formId" value = "register">
                <input type="text" id="register-username" name="username" placeholder="Username" required>
                <input type="password" id="register-password" name="password" placeholder="Password" required>
                <input type="password" id="secret-word" name="secret-word" placeholder="Write your favourite desert as a secret word" required>
                <button type="submit">Register</button>
            </form>
            <div class="toggle">
                <p>Already have an account? <a onclick="toggleForms('login-form')">Login here</a></p>
            </div>
        </div>
        <div id="forgot-password-form" class="form" style="display: none;">
            <h2>Forgot Password</h2>
            <form onsubmit="verifySecurityQuestion(event)">
                <input type="email" id="forgot-password-email" name="email" placeholder="Email" required>
                <input type="text" id="security-question" name="security-question" placeholder="What is your favourite dessert?" required>
                <button type="submit">Verify Answer</button>
            </form>
            <div class="toggle">
                <p>Remembered your password? <a onclick="toggleForms('login-form')">Login here</a></p>
                <p>Don't have an account? <a onclick="toggleForms('register-form')">Register here</a></p>
            </div>
        </div>
        <div id="reset-password-form" class="form" style="display: none;">
            <h2>Reset Password</h2>
            <form>
                <input type="email" id="reset-password-email" name="email" placeholder="Email" readonly required>
                <input type="password" id="new-password" name="password" placeholder="New Password" required>
                <button type="submit">Reset Password</button>
            </form>
            <div class="toggle">
                <p>Remembered your password? <a onclick="toggleForms('login-form')">Login here</a></p>
                <p>Don't have an account? <a onclick="toggleForms('register-form')">Register here</a></p>
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