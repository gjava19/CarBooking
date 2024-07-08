<%@ page import="java.util.ArrayList" %>
<%@ page import="Models.QuizAppareParameters" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Quiz List</title>
    <link rel="stylesheet" href="styles/quiz.css">

</head>
<body>
<jsp:include page="Header.jsp"/>
<main>
    <div class="container">
        <h2>Quiz List</h2>
        <table>
            <thead>
            <tr>
                <th>Name</th>
                <th>Description</th>
                <th>Creator</th>
            </tr>
            </thead>
            <tbody>
            <% ArrayList<QuizAppareParameters> quizes = (ArrayList<QuizAppareParameters>) request.getAttribute("quizes");
                for(QuizAppareParameters curQuiz : quizes){
                %>
            <tr>
                <td><%=curQuiz.getQuizName()%></td>
                <td><%=curQuiz.getQuizDescription()%></td>
                <td><%=curQuiz.getCreatorName()%></td>
            </tr>
            <%}%>
            </tbody>
        </table>

    </div>
    <div class="button-container">
        <a class="btn" href="quiz/show?name=slay quiz">Show quiz</a>
        <a class="btn" href="quiz/create">Create quiz</a>
    </div>
</main>

</body>
</html>
