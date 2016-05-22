package by.training.transport.jmx;

import java.util.Date;
import java.util.List;

import by.training.bean.metric.Metric;

public interface JMXWebServiceMXBean {

    Metric getLast(String metricType);

    List<Metric> getList(String metricType, Date from, Date to);

}
