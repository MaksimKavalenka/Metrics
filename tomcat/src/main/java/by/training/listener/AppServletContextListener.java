package by.training.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import by.training.bean.options.MetricType;

@WebListener
public class AppServletContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(final ServletContextEvent event) {
        for (MetricType typeMetric : MetricType.values()) {
            new Thread(typeMetric).start();
        }
    }

    @Override
    public void contextDestroyed(final ServletContextEvent event) {
        for (MetricType typeMetric : MetricType.values()) {
            typeMetric.deactivate();
        }
    }

}
