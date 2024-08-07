package Servlets;

import MVController.FriendshipController;
import MVController.UserController;
import db.DBConnector;
import db.DBQuizCommunicator;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebServlet;
import java.sql.SQLException;

@WebServlet
public class Listener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        DBConnector dbConnector  = new DBConnector();
        UserController userController = new UserController(dbConnector);
        FriendshipController friendshipController = new FriendshipController(dbConnector);

        DBQuizCommunicator quizCommunicator;
        try {
            quizCommunicator = new DBQuizCommunicator(dbConnector.getCon());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        servletContextEvent.getServletContext().setAttribute("userController", userController);
        servletContextEvent.getServletContext().setAttribute("friendshipController", friendshipController);
        servletContextEvent.getServletContext().setAttribute("quizCommunicator", quizCommunicator);

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }

}
