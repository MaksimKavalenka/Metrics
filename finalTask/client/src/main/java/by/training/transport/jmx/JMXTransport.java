package by.training.transport.jmx;

import static by.training.constants.ResponseStatus.*;
import static by.training.constants.StubConstants.*;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.management.InstanceNotFoundException;
import javax.management.MBeanException;
import javax.management.MBeanServerConnection;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.ReflectionException;
import javax.management.openmbean.CompositeData;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

import by.training.bean.element.ParametersElement;
import by.training.bean.metric.Metric;
import by.training.constants.ResponseStatus;
import by.training.dao.TransportDAO;
import by.training.options.MetricType;
import by.training.parser.CompositeDataParser;

public class JMXTransport implements TransportDAO {

    private static ObjectName     name;

    private ResponseStatus        status = NOT_FOUND;

    private JMXConnector          jmxConnector;
    private MBeanServerConnection mbeanServerConnection;

    static {
        try {
            name = new ObjectName("by.training:type=metrics,name=jmx");
        } catch (MalformedObjectNameException e) {
            e.printStackTrace();
        }
    }

    public JMXTransport(final ParametersElement parameters) {
        setParameters(parameters);
    }

    @Override
    public Metric getLast(final MetricType metricType) {
        Metric metric = DEFAULT_VALUE;

        try {
            CompositeData compositeData;

            synchronized (this) {
                compositeData = (CompositeData) mbeanServerConnection.invoke(name, "getLast",
                        new Object[] {metricType.name()}, new String[] {String.class.getName()});
            }

            metric = CompositeDataParser.parseSingleData(compositeData);
            status = OK;
        } catch (InstanceNotFoundException | MBeanException | ReflectionException | IOException e) {
            status = SERVICE_UNAVAILABLE;
        }

        return metric;
    }

    @Override
    public List<Metric> getList(final MetricType metricType, final Date from, final Date to) {
        List<Metric> list = DEFAULT_LIST;

        try {
            CompositeData[] compositeDatas;

            synchronized (this) {
                compositeDatas = (CompositeData[]) mbeanServerConnection.invoke(name, "getList",
                        new Object[] {metricType.name(), from, to},
                        new String[] {String.class.getName(), Date.class.getName(),
                                Date.class.getName()});
            }

            list = CompositeDataParser.parseArrayData(compositeDatas);
            status = OK;
        } catch (InstanceNotFoundException | MBeanException | ReflectionException | IOException e) {
            status = SERVICE_UNAVAILABLE;
        }

        return list;
    }

    @Override
    public void setParameters(final ParametersElement parameters) {
        try {
            JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://"
                    + parameters.getHost() + ":" + parameters.getPort() + "/jmxrmi");
            jmxConnector = JMXConnectorFactory.connect(url);

            synchronized (this) {
                mbeanServerConnection = jmxConnector.getMBeanServerConnection();
            }

            status = OK;
        } catch (Exception e) {
            status = NOT_FOUND;
        }
    }

    @Override
    public ResponseStatus getStatus() {
        return status;
    }

    @Override
    public void close() {
        try {
            if (jmxConnector != null) {
                jmxConnector.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
