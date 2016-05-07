package by.training.transport.soap;

import java.util.Date;
import java.util.List;

import org.codehaus.jettison.json.JSONException;

import by.training.bean.metric.Metric;
import by.training.bean.options.MetricType;
import by.training.dao.TransportDAO;
import by.training.transport.soap.wsdl.SOAPTransportService;
import by.training.transport.soap.wsdl.WebServiceInterface;

public class SOAPTransport implements TransportDAO {

    private WebServiceInterface serviceInterface;

    public SOAPTransport(final String address) {
        SOAPTransportService webService = new SOAPTransportService();
        serviceInterface = webService.getSOAPTransportPort();
    }

    @Override
    public Metric getLast(final MetricType typeMetric) throws JSONException {
        return serviceInterface.getLast(typeMetric);
    }

    @Override
    public List<Metric> getList(final MetricType typeMetric, final Date from, final Date to)
            throws JSONException {
        String fromDate = String.valueOf(from.getTime());
        String toDate = String.valueOf(to.getTime());

        return serviceInterface.getList(typeMetric, fromDate, toDate);
    }

    @Override
    public void close() {
    }

}
