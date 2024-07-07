package Servlets;

import MVController.UserController;
import Models.Quiz;
import Models.QuestionParameters;
import Models.QuestionType;
import db.DBConnector;
import db.DBQuizCommunicator;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

@WebServlet(name = "quiz", value = "/quiz")
public class QuizServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        DBQuizCommunicator quizCommunicator = (DBQuizCommunicator) getServletContext().getAttribute("quizCommunicator");

        String quizName = request.getParameter("name");

        Quiz quiz = null;
        try {
            quiz = quizCommunicator.getQuizByName(quizName);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (quiz != null) {
            request.setAttribute("quiz", quiz);
            request.getRequestDispatcher("/showQuiz.jsp").forward(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Quiz not found");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            DBQuizCommunicator quizCommunicator = (DBQuizCommunicator) getServletContext().getAttribute("quizCommunicator");

            ObjectMapper objectMapper = new ObjectMapper();
            Quiz quiz = objectMapper.readValue(request.getParameter("quizData"), Quiz.class);
            if (quizCommunicator.createQuiz(quiz)) {
                response.sendRedirect(request.getContextPath() + "/quiz?name=" + quiz.getName());
            } else {
                response.sendError(HttpServletResponse.SC_CONFLICT, "Quiz already exists.");
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error creating quiz.");
        }
    }

}
