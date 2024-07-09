<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Quiz Results</title>
    <link rel="stylesheet" href="../styles/quizResult.css">
</head>
<body>
<jsp:include page="../Header.jsp"/>
<main>
    <div class="container">
        <h1>Quiz Results</h1>
        <p><strong>Your Score:</strong> <%= request.getAttribute("score") %></p>
        <a href="${pageContext.request.contextPath}/quiz">Back to Quizzes</a>
    </div>
</main>
</body>
</html>
