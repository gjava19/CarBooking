package Servlets;

import MVController.UserController;
import Models.User;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import javax.servlet.http.Cookie;

public class ProfileServlet extends HttpServlet {
    private static final String WHOAMI = "whoami";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        Cookie infoCookie = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(WHOAMI)) {
                    infoCookie = cookie;
                }
            }
        }
       ;
        if (infoCookie != null && infoCookie.getValue() != null){
            UserController userController = (UserController) getServletContext().getAttribute("userController");
            User myuser = userController.getUserInfo(infoCookie.getValue());

            if(myuser != null) {
                request.setAttribute("userInfo", myuser);
                request.getRequestDispatcher("profile.jsp").forward(request, response);
            }else {
                // Delete infoCookie when user not found with this cookie value
                infoCookie.setMaxAge(0);
                response.addCookie(infoCookie);
            }
        }
        response.sendRedirect( "login.jsp" );
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);

    }
}
