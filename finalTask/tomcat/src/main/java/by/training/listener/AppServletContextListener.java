package by.training.listener;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import by.training.storage.StorageMXBean;
import by.training.transport.rmi.RMITransport;

@WebListener
public class AppServletContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(final ServletContextEvent event) {
        StorageMXBean.init();
        try {
            RMITransport.registry();
        } catch (RemoteException | AlreadyBoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(final ServletContextEvent event) {
        StorageMXBean.deactivate();
    }

}
