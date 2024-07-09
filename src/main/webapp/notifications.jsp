<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>QuizGrad - Notifications</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/notifications.css">
</head>
<body>
<jsp:include page="Header.jsp"/>
<main class="notifications-container">
    <h1>Notifications</h1>
    <div class="send-notification">
        <h2>Send Quiz Challenge</h2>
        <form action="notifications" method="post">
            <label for="recipient">Recipient:</label>
            <input type="text" id="recipient" name="recipient" placeholder="Enter username" required>
            <label for="quizName">Quiz Name:</label>
            <input type="text" id="quizName" name="quizName" placeholder="Enter quiz name" required>
            <button type="submit">Send Challenge</button>
        </form>
    </div>
    <div class="received-notifications">
        <h2>Received Notifications</h2>
        <c:choose>
            <c:when test="${empty receivedNotifications}">
                <p>No notifications received</p>
            </c:when>
            <c:otherwise>
                <ul class="notification-list">
                    <c:forEach var="notification" items="${receivedNotifications}">
                        <li class="notification-item">
                            <p><strong>From:</strong> <c:out value="${notification.user}"/></p>
                            <p><strong>Quiz ID:</strong> <c:out value="${notification.quizId}"/></p>
                            <p><strong>Message:</strong> <c:out value="${notification.message}"/></p>
                            <p><strong>Date:</strong> <c:out value="${notification.date}"/></p>
                        </li>
                    </c:forEach>
                </ul>
            </c:otherwise>
        </c:choose>
    </div>
    <div class="sent-notifications">
        <h2>Sent Notifications</h2>
        <c:choose>
            <c:when test="${empty sentNotifications}">
                <p>No notifications sent</p>
            </c:when>
            <c:otherwise>
                <ul class="notification-list">
                    <c:forEach var="notification" items="${sentNotifications}">
                        <li class="notification-item">
                            <p><strong>To:</strong> <c:out value="${notification.user}"/></p>
                            <p><strong>Quiz ID:</strong> <c:out value="${notification.quizId}"/></p>
                            <p><strong>Message:</strong> <c:out value="${notification.message}"/></p>
                            <p><strong>Date:</strong> <c:out value="${notification.date}"/></p>
                        </li>
                    </c:forEach>
                </ul>
            </c:otherwise>
        </c:choose>
    </div>
</main>
<footer class="footer">
    <p>&copy; 2024 QuizGrad. All rights reserved.</p>
</footer>
</body>
</html>
