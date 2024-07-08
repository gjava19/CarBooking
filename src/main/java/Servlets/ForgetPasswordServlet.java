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
import java.sql.SQLException;

@WebServlet(name = "ForgetPassServlet", value = "/forgetPass")

public class ForgetPasswordServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
        System.out.println("Forget Passs");

        String formId = req.getParameter("formId");
        String username = req.getParameter("username");
//        String secretWord = req.getParameter("security-question");

        UserController userController = (UserController) getServletContext().getAttribute("userController");
        System.out.println(formId + username);
//        try {
//            if (userController.checkSecretWord(username, secretWord)){
//
//                String password = req.getParameter("password");
//                System.out.println(formId + username + password);
//                userController.changePasswordRequest(username, password);
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }

//        if ("verify".equals(formId)) {
            String secretWord = req.getParameter("security-question");
            System.out.println(formId + username + secretWord);
            try {
                if (userController.checkSecretWord(username, secretWord)) {
                    System.out.println("checked secret word");
                    resp.sendRedirect( "resetPassword");

                    String password = req.getParameter("password");
                    System.out.println("entered new password");
                    try {
                        userController.changePasswordRequest(username, password);
                        System.out.println(formId + username + password);
                        resp.sendRedirect("login");
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    req.getRequestDispatcher("forgetPassword.jsp").forward(req, resp);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
//        }
//        else if ("reset".equals(formId)) {
//            String password = req.getParameter("password");
//            try {
//                userController.changePasswordRequest(username, password);
//                resp.sendRedirect("login.jsp");
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//        }

    }

}

