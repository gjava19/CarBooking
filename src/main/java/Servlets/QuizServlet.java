package Servlets;

import MVController.UserController;
import Models.QuizAppareParameters;
import db.DBQuizCommunicator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class QuizServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        DBQuizCommunicator quizCommunicator = (DBQuizCommunicator) getServletContext().getAttribute("quizCommunicator");
        UserController userController = (UserController) getServletContext().getAttribute("userController");

        ArrayList<QuizAppareParameters> quizes = null;
        try {
            quizes = quizCommunicator.getAllQuiz();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        req.setAttribute("quizes", quizes);

        req.getRequestDispatcher("quiz.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
