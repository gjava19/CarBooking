package Servlets;

import MVController.UserController;
import Models.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "RegisterServlet", value = "/register")
public class RegisterServlet extends HttpServlet {
    private static final String WHOAMI = "whoami";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String secretWord = request.getParameter("secret-word");

        UserController userController = (UserController) getServletContext().getAttribute("userController");

        try {
            User user = userController.createUser(username, password, secretWord);
            response.addCookie(new Cookie(WHOAMI, user.getUsername()));
            response.sendRedirect( "profile");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}

