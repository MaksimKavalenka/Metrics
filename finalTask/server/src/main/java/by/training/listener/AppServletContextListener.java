package by.training.listener;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;

import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import by.training.storage.StorageMXBean;
import by.training.transport.jms.JMSTransport;
import by.training.transport.jmx.JMXTransport;
import by.training.transport.rmi.RMITransport;

@WebListener
public class AppServletContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(final ServletContextEvent event) {
        StorageMXBean.init();
        publishAll();
    }

    @Override
    public void contextDestroyed(final ServletContextEvent event) {
        destroyAll();
        StorageMXBean.deactivate();
    }

    private void publishAll() {
        try {
            RMITransport.publish();
        } catch (RemoteException | AlreadyBoundException e) {
            e.printStackTrace();
        }

        try {
            JMXTransport.publish();
        } catch (InstanceAlreadyExistsException | MBeanRegistrationException
                | NotCompliantMBeanException | MalformedObjectNameException e) {
            e.printStackTrace();
        }

        try {
            JMSTransport.publish();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void destroyAll() {
        try {
            JMSTransport.destroy();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
