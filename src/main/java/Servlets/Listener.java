package Servlets;

import MVController.UserController;
import db.DBConnector;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebServlet;

@WebServlet
public class Listener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        DBConnector dbConnector  = new DBConnector();
        UserController userController = new UserController(dbConnector);
        servletContextEvent.getServletContext().setAttribute("userController", userController);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }

}
