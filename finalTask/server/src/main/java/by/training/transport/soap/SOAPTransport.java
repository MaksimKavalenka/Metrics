package by.training.transport.soap;

import java.util.Date;
import java.util.List;

import javax.jws.WebService;

import by.training.bean.metric.Metric;
import by.training.options.MetricType;
import by.training.storage.StorageMXBean;

@WebService(endpointInterface = "by.training.transport.soap.SOAPWebServiceInterface")
public class SOAPTransport implements SOAPWebServiceInterface {

    @Override
    public Metric getLast(final String metricType) {
        MetricType mType = MetricType.valueOf(metricType);
        return StorageMXBean.getMXBean(mType).getStorage().getLast();
    }

    @Override
    public Metric[] getList(final String metricType, final Date from, final Date to) {
        MetricType mType = MetricType.valueOf(metricType);
        List<Metric> list = StorageMXBean.getMXBean(mType).getStorage().getList(from, to);
        return list.toArray(new Metric[list.size()]);
    }

}
