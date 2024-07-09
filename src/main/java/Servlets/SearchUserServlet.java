package Servlets;

import MVController.UserController;

import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


public class SearchUserServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String query = request.getParameter("query");

        UserController userController = (UserController) getServletContext().getAttribute("userController");

        // Forward the request back to the JSP page
        request.setAttribute("results", userController.getFilterUser(query));
        request.getRequestDispatcher("/profile").forward(request, response);
    }
}