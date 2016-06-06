package by.training.transport.jmx;

import java.lang.management.ManagementFactory;
import java.util.Date;
import java.util.List;

import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;

import by.training.bean.metric.Metric;
import by.training.options.MetricType;
import by.training.storage.StorageMXBean;

public class JMXTransport implements JMXWebServiceMXBean {

    private static ObjectName  objectName;
    private static MBeanServer server;

    public static void publish() throws InstanceAlreadyExistsException, MBeanRegistrationException,
            NotCompliantMBeanException, MalformedObjectNameException {
        JMXTransport transport = new JMXTransport();
        objectName = new ObjectName("by.training:type=metrics,name=jmx");
        server = ManagementFactory.getPlatformMBeanServer();
        server.registerMBean(transport, objectName);
    }

    public static void shutdown() throws MBeanRegistrationException, InstanceNotFoundException {
        server.unregisterMBean(objectName);
    }

    @Override
    public Metric getLast(final String metricType) {
        MetricType mType = MetricType.valueOf(metricType);
        return StorageMXBean.getMXBean(mType).getStorage().getLast();
    }

    @Override
    public List<Metric> getList(final String metricType, final Date from, final Date to) {
        MetricType mType = MetricType.valueOf(metricType);
        return StorageMXBean.getMXBean(mType).getStorage().getList(from, to);
    }

}
