package by.training.transport.jmx;

import static by.training.constants.StubConstants.*;
import static by.training.exception.HTTPException.*;

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
import by.training.dao.TransportDAO;
import by.training.exception.HTTPException;
import by.training.options.MetricType;
import by.training.parser.CompositeDataParser;

public class JMXTransport implements TransportDAO {

    private static ObjectName     name;

    private HTTPException         status = HTTP_404;

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
            CompositeData compositeData = (CompositeData) mbeanServerConnection.invoke(name,
                    "getLast", new Object[] {metricType.name()},
                    new String[] {String.class.getName()});
            metric = CompositeDataParser.parseSingleData(compositeData);
        } catch (InstanceNotFoundException | MBeanException | ReflectionException | IOException e) {
            status = HTTP_503;
        }

        return metric;
    }

    @Override
    public List<Metric> getList(final MetricType metricType, final Date from, final Date to) {
        List<Metric> list = DEFAULT_LIST;

        try {
            CompositeData[] compositeDatas = (CompositeData[]) mbeanServerConnection.invoke(name,
                    "getList", new Object[] {metricType.name(), from, to}, new String[] {
                            String.class.getName(), Date.class.getName(), Date.class.getName()});
            list = CompositeDataParser.parseArrayDatas(compositeDatas);
        } catch (InstanceNotFoundException | MBeanException | ReflectionException | IOException e) {
            status = HTTP_503;
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

            status = HTTP_200;
        } catch (Exception e) {
            status = HTTP_404;
        }
    }

    @Override
    public HTTPException getStatus() {
        return status;
    }

    @Override
    public void close() {
        try {
            jmxConnector.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
