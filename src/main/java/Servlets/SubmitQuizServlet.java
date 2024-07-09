package Servlets;

import Models.*;
import db.DBQuizCommunicator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


public class SubmitQuizServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DBQuizCommunicator quizCommunicator = (DBQuizCommunicator) getServletContext().getAttribute("quizCommunicator");

        String quizName = request.getParameter("quizName");
        Quiz quiz = null;
        try {
            quiz = quizCommunicator.getQuizByName(quizName);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {

            if (quiz != null) {
                int score = 0;
                for (Map.Entry<QuestionType, QuestionParameters> entry : quiz.getQuestions().entrySet()) {
                    QuestionType question = entry.getKey();
                    QuestionParameters params = entry.getValue();
                    String userResponse = request.getParameter("response_" + params.getIndex());

                    if (question.getResponse().equals(userResponse)) {
                        score += params.getScore();
                    }
                }

                request.setAttribute("score", score);
                request.getRequestDispatcher("quizResult.jsp").forward(request, response);
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Quiz not found");
            }

        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
