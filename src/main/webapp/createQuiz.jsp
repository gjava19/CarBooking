<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Create Quiz</title>
</head>
<body>
<h1>Create Quiz</h1>
<form action="<%= request.getContextPath() %>/quiz/create" method="post">
    <label for="name">Quiz Name:</label>
    <input type="text" id="name" name="name" required><br><br>
    <label for="description">Description:</label>
    <textarea id="description" name="description" required></textarea><br><br>
    <!-- Add more fields for other Quiz attributes like randomQuestion, immediateAnswer, multiplePageQuiz, etc. -->
    <label for="questions">Questions (JSON format):</label>
    <textarea id="questions" name="quizData" required></textarea><br><br>
    <input type="submit" value="Create Quiz">
</form>
</body>
</html>
