package Servlets;

import MVController.FriendshipController;
import MVController.UserController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


public class AddFriendServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String myid = request.getParameter("myid");
        String userid = request.getParameter("userid");

        FriendshipController friendshipController = (FriendshipController) getServletContext().getAttribute("friendshipController");

        try {
            friendshipController.sendFriendship(Integer.parseInt(myid), Integer.parseInt(userid));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        response.sendRedirect("profile");
    }

}