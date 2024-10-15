package com.example.lab_2jt;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class AppContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // Read context parameters
        String prelude = sce.getServletContext().getInitParameter("prelude");
        String coda = sce.getServletContext().getInitParameter("coda");

        // Store them in application scope attributes
        sce.getServletContext().setAttribute("prelude", prelude);
        sce.getServletContext().setAttribute("coda", coda);

        // Log the values for confirmation
        System.out.println("AppContextListener: Prelude = " + prelude);
        System.out.println("AppContextListener: Coda = " + coda);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Clean up resources if needed
    }
}
