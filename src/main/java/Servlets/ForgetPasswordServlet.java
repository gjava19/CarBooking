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

@WebServlet(name = "ForgetPassServlet", value = "/forgetPass")

public class ForgetPasswordServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DBConnector dbConnector;
    private UserController userController;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
        System.out.println("Forget Passs");



    }

    @Override public void init(ServletConfig config) throws ServletException {
        super.init(config);
        dbConnector = new DBConnector();
        userController = new UserController(dbConnector);
    }
}

