package by.training.transport.soap;

import java.util.LinkedList;

import javax.jws.WebService;

import by.training.bean.metric.Metric;
import by.training.bean.options.MetricType;

@WebService(endpointInterface = "by.training.transport.soap.WebServiceInterface")
public class SOAPTransport implements WebServiceInterface {

    @Override
    public Metric getLast(final MetricType metricType) {
        return metricType.getStorageEditor().getLast();
    }

    @Override
    public LinkedList<Metric> getList(final MetricType metricType, final String from,
            final String to) {
        return (LinkedList<Metric>) metricType.getStorageEditor().getList(from, to);
    }

}
