<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html lang="en" class="home-page">
<head>
    <title>Quiz Grad</title>
    <link rel="stylesheet" href="styles/index.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100;0,300;0,400;0,500;0,700;0,900;1,100;1,300;1,400;1,500;1,700;1,900&display=swap" rel="stylesheet">
</head>
<body>
<jsp:include page="Header.jsp"/>

    <main class="intro-main">
        <div class="intro-content">
            <h1>Welcome to QuizGrad</h1>
            <p>Your ultimate platform to create, share, and take quizzes.</p>
            <div class="intro-buttons">
                <a href="quiz" class="intro-button signup">Get Started</a>
                <a href="register" class="intro-button login">Sign Up</a>
            </div>
        </div>
        <div class="intro-image">
            <img src="images/graduation-cap-large.png" alt="Quiz Image">
        </div>
    </main>
    <footer class="footer">
        <p>&copy; 2024 QuizGrad. All rights reserved.</p>
        <div>Time on server <%= new java.util.Date()%></div>
    </footer>
</body>
</html>
