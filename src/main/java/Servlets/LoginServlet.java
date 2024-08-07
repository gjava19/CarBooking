package Servlets;

import MVController.UserController;
import Models.User;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class LoginServlet extends HttpServlet {
    private static final String WHOAMI = "whoami";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String formId = request.getParameter("formId");
        String username = request.getParameter("username");
        String password = request.getParameter("password");


        UserController userController = (UserController) getServletContext().getAttribute("userController");

        try {
            User user = userController.loginUser(username, password);
            response.addCookie(new Cookie(WHOAMI, user.getUsername()));
            response.sendRedirect( "profile");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

}

