<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Map" %>
<%@ page import="Models.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Show Quiz</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f9f9f9;
            margin: 0;
            padding: 0;
        }
        .container {
            width: 80%;
            margin: 0 auto;
            background-color: #fff;
            padding: 20px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            margin-top: 50px;
        }
        h1 {
            text-align: center;
            color: #333;
        }
        .quiz-details {
            margin-bottom: 30px;
        }
        .quiz-details p {
            font-size: 18px;
            color: #555;
        }
        h2 {
            color: #333;
            border-bottom: 2px solid #ccc;
            padding-bottom: 10px;
        }
        ul {
            list-style: none;
            padding: 0;
        }
        li {
            background-color: #f1f1f1;
            margin-bottom: 10px;
            padding: 15px;
            border-radius: 5px;
        }
        li p {
            margin: 0;
            font-size: 16px;
            color: #555;
        }
        .no-quiz {
            text-align: center;
            color: #888;
            font-size: 20px;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Quiz Details</h1>
    <%
        Quiz quiz = (Quiz) request.getAttribute("quiz");

        if (quiz != null) {
    %>
    <div class="quiz-details">
        <p><strong>Name:</strong> <%= quiz.getName() %></p>
        <p><strong>Description:</strong> <%= quiz.getDescription() %></p>
        <p><strong>Created by:</strong> <%= quiz.getUserName() %></p>
    </div>
    <h2>Questions</h2>
    <ul>
        <%
            for (Map.Entry<QuestionType, QuestionParameters> entry : quiz.getQuestions().entrySet()) {
                QuestionType question = entry.getKey();
                QuestionParameters params = entry.getValue();
        %>
        <li>
            <p><strong>Question:</strong> <%= question.getQuestion() %></p>
            <%
                String result = "";
                if (question.getType().equals("MultipleChoice")){
                    MultipleChoice temp = (MultipleChoice) question;
                    for(int index = 0; index < temp.getChoices().length; index++) {
                        result += temp.getChoices()[index] + "   ";
                    }

            %>
                <p><strong>variants :</strong> <%= result %></p>
            <%}%>

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
    <p class="no-quiz">No quiz found.</p>
    <%
        }
    %>
</div>
</body>
</html>
