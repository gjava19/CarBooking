<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="Models.Quiz" %>
<%@ page import="Models.QuestionType" %>
<%@ page import="Models.QuestionParameters" %>
<%@ page import="java.util.Map" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Show Quiz</title>
</head>
<body>
<h1>Quiz Details</h1>
<%
    Quiz quiz = (Quiz) request.getAttribute("quiz");
    if (quiz != null) {
%>
<p><strong>Name:</strong> <%= quiz.getName() %></p>
<p><strong>Description:</strong> <%= quiz.getDescription() %></p>
<!-- Display other Quiz attributes here -->
<h2>Questions</h2>
<ul>
    <%
        for (Map.Entry<QuestionType, QuestionParameters> entry : quiz.getQuestions().entrySet()) {
            QuestionType question = entry.getKey();
            QuestionParameters params = entry.getValue();
    %>
    <li>
        <p><strong>Question:</strong> <%= question.getQuestion() %></p>
        <p><strong>Response:</strong> <%= question.getResponse() %></p>
        <p><strong>Time (seconds):</strong> <%= params.getTimeSec() %></p>
        <p><strong>Score:</strong> <%= params.getScore() %></p>
    </li>
    <%
        }
    %>
</ul>
<%
} else {
%>
<p>No quiz found.</p>
<%
    }
%>
</body>
</html>
