package Servlets;

import MVController.UserController;
import db.DBConnector;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "RegisterServlet", value = "/register")

public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DBConnector dbConnector;
    private UserController userController;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        request.getRequestDispatcher("register.jsp").forward(request, resp);

        System.out.println("Hellooo registeeer");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
        System.out.println("Hellooo registeer");

        String formId = req.getParameter("formId");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String secretWord = req.getParameter("secret-word");

        if ("register".equals(formId)) {
            // Handle registration logic

            System.out.println(formId + username + password + secretWord);
            try {
                userController.createUser(username, password, secretWord);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }

    @Override public void init(ServletConfig config) throws ServletException {
        super.init(config);
        dbConnector = new DBConnector();
        userController = new UserController(dbConnector);
    }
}

