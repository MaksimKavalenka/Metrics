package by.training.transport.soap;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.xml.ws.WebServiceException;

import org.codehaus.jettison.json.JSONException;

import by.training.bean.metric.Metric;
import by.training.bean.options.MetricType;
import by.training.dao.TransportDAO;
import by.training.transport.soap.wsdl.SOAPTransportService;
import by.training.transport.soap.wsdl.WebServiceInterface;

public class SOAPTransport implements TransportDAO {

    private static final String WSDL = "?wsdl";

    private WebServiceInterface serviceInterface;

    public SOAPTransport(final String address) {
        serviceInterface = new SOAPTransportService(getURL(address)).getSOAPTransportPort();
    }

    private URL getURL(final String address) {
        URL url = null;

        try {
            url = new URL(address + WSDL);
        } catch (MalformedURLException e) {
            throw new WebServiceException(e);
        }

        return url;
    }

    @Override
    public Metric getLast(final MetricType typeMetric) throws JSONException {
        Metric metric;

        synchronized (serviceInterface) {
            metric = serviceInterface.getLast(typeMetric.name());
        }

        return metric;
    }

    @Override
    public List<Metric> getList(final MetricType typeMetric, final Date from, final Date to)
            throws JSONException {
        List<Metric> list;

        synchronized (serviceInterface) {
            list = Arrays.asList(serviceInterface.getList(typeMetric.name(), from, to));
        }

        return list;
    }

    @Override
    public boolean setAddress(final String address) {
        synchronized (serviceInterface) {
            try {
                serviceInterface = new SOAPTransportService(getURL(address)).getSOAPTransportPort();
            } catch (WebServiceException e) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void close() {
    }

}
