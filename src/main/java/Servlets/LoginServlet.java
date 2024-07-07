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

@WebServlet(name = "LoginServlet", value = "/login")

public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DBConnector dbConnector;
    private UserController userController;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
        System.out.println("Hellooo P");

        String formId = req.getParameter("formId");
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if ("login".equals(formId)) {
            // Handle login logic
            System.out.println(formId + username + password);
            try {
                userController.loginUser(username, password);
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

