package Servlets;

import MVController.FriendshipController;
import MVController.UserController;
import Models.User;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


public class ApproveFriendRequestServlet extends HttpServlet {
    private static final String WHOAMI = "whoami";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        Cookie infoCookie = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(WHOAMI)) {
                    infoCookie = cookie;
                }
            }
        }

        String myid = request.getParameter("myid");
        String userid = request.getParameter("userid");

        FriendshipController friendshipController = (FriendshipController) getServletContext().getAttribute("friendshipController");
        UserController userController = (UserController) getServletContext().getAttribute("userController");

        System.out.println("myid: " + myid);
        System.out.println("userid: " + userid);
        System.out.println("ApproveFriendRequestServlet");
        try {
            friendshipController.approveFriendship(Integer.parseInt(myid), Integer.parseInt(userid));;
            User myuser = userController.getUserInfo(infoCookie.getValue());
            request.setAttribute("userInfo", myuser);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        response.sendRedirect("profile");
    }

}