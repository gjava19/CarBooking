<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Map" %>
<%@ page import="Models.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Show Quiz</title>
    <link rel="stylesheet" href="../styles/showQuiz.css">
</head>
<body>
<jsp:include page="../Header.jsp"/>
<main>
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
        <form action="result" method="post">
            <ul>
                <%
                    for (Map.Entry<QuestionType, QuestionParameters> entry : quiz.getQuestions().entrySet()) {
                        QuestionType question = entry.getKey();
                        QuestionParameters params = entry.getValue();
                %>
                <li>
                    <%
                        if(question.getType().equals("PictureResponse")){
                    %>
                        <p>
                            <strong>Question:</strong>
                            <img src=<%= question.getQuestion() %> width="300" height=auto>
                        </p>
                    <%
                        }else{
                    %>
                        <p><strong>Question:</strong> <%= question.getQuestion() %></p>
                    <%}%>
                    <p><strong>Time (seconds):</strong> <%= params.getTimeSec() %></p>
                    <p><strong>Score:</strong> <%= params.getScore() %></p>
                    <input type="hidden" name="questionType_<%= params.getIndex() %>" value="<%= question.getType() %>">
                    <input type="hidden" name="questionIndex_<%= params.getIndex() %>" value="<%= params.getIndex() %>">
                    <label for="response_<%= params.getIndex() %>">Your Answer:</label>
                    <%
                        if (question.getType().equals("MultipleChoice")) {
                            MultipleChoice temp = (MultipleChoice) question;
                            for (int index = 0; index < temp.getChoices().length; index++) {
                    %>
                    <div>
                        <input type="radio" id="response_<%= params.getIndex() %>_<%= index %>" name="response_<%= params.getIndex() %>" value="<%= temp.getChoices()[index] %>">
                        <label for="response_<%= params.getIndex() %>_<%= index %>"><%= temp.getChoices()[index] %></label>
                    </div>
                    <%
                        }
                    } else {
                    %>
                    <input type="text" id="response_<%= params.getIndex() %>" name="response_<%= params.getIndex() %>">
                    <%
                        }
                    %>
                </li>
                <%
                    }
                %>
                <input type="hidden" name="quizName" value="<%= quiz.getName() %>" />
            </ul>
            <input type="hidden" name="questionCount" value="<%= quiz.getQuestions().size() %>">
            <button type="submit">Submit</button>
        </form>
        <%
        } else {
        %>
        <p class="no-quiz">No quiz found.</p>
        <%
            }
        %>
    </div>
</main>
</body>
</html>
