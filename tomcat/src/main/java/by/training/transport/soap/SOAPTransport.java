package by.training.transport.soap;

import java.util.Date;
import java.util.List;

import javax.jws.WebService;

import by.training.bean.metric.Metric;
import by.training.bean.options.MetricType;

@WebService(endpointInterface = "by.training.transport.soap.WebServiceInterface")
public class SOAPTransport implements WebServiceInterface {

    @Override
    public Metric getLast(final String metricType) {
        return MetricType.valueOf(metricType).getStorageEditor().getLast();
    }

    @Override
    public Metric[] getList(final String metricType, final Date from, final Date to) {
        List<Metric> list = MetricType.valueOf(metricType).getStorageEditor().getList(from, to);
        return list.toArray(new Metric[list.size()]);
    }

}
