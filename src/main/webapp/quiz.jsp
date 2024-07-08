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
<div class="container">
    <h2>Quiz List</h2>
    <table>
        <thead>
        <tr>
            <th onclick="sortTable(0)">Name</th>
            <th onclick="sortTable(1)">Description</th>
            <th onclick="sortTable(2)">Creator</th>
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
<script>
    function sortTable(columnIndex) {
        var table, rows, switching, i, x, y, shouldSwitch, switchCount = 0;
        table = document.querySelector("table");
        switching = true;
        var direction = "asc";
        while (switching) {
            switching = false;
            rows = table.rows;
            for (i = 1; i < (rows.length - 1); i++) {
                shouldSwitch = false;
                x = rows[i].getElementsByTagName("TD")[columnIndex];
                y = rows[i + 1].getElementsByTagName("TD")[columnIndex];
                if (direction === "asc") {
                    if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
                        shouldSwitch = true;
                        break;
                    }
                } else if (direction === "desc") {
                    if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {
                        shouldSwitch = true;
                        break;
                    }
                }
            }
            if (shouldSwitch) {
                rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
                switching = true;
                switchCount++;
            } else {
                if (switchCount === 0 && direction === "asc") {
                    direction = "desc";
                    switching = true;
                }
            }
        }
    }
</script>

    <p>
        <a href="quiz/show?name=slay quiz"> Show quiz </a>
    </p>

    <p>
        <a href="quiz/create"> Create quiz </a>
    </p>

</body>
</html>
