package by.training.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import by.training.data.hibernate.HibernateUtil;

@WebListener
public class AppServletContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(final ServletContextEvent event) {
        HibernateUtil.getSessionFactory();
    }

    @Override
    public void contextDestroyed(final ServletContextEvent event) {
    }

}
