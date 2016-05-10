package by.training.transport.soap;

import static by.training.exception.HTTPException.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.xml.ws.WebServiceException;

import com.sun.xml.internal.ws.client.ClientTransportException;

import by.training.bean.metric.Metric;
import by.training.bean.options.MetricType;
import by.training.dao.TransportDAO;
import by.training.editor.TransportEditor;
import by.training.exception.HTTPException;
import by.training.transport.soap.wsdl.SOAPTransportService;
import by.training.transport.soap.wsdl.WebServiceInterface;

public class SOAPTransport implements TransportDAO {

    private static final String       WSDL  = "?wsdl";
    private static final List<Metric> EMPTY = new ArrayList<>(0);

    private Object                    block;
    private HTTPException             status;
    private SOAPTransportService      transportService;
    private WebServiceInterface       serviceInterface;

    public SOAPTransport(final String address) {
        block = new Object();
        setAddress(address);
    }

    @Override
    public Metric getLast(final MetricType typeMetric) {
        Metric metric;

        synchronized (block) {
            metric = serviceInterface.getLast(typeMetric.name());
        }

        return metric;
    }

    @Override
    public List<Metric> getList(final MetricType typeMetric, final Date from, final Date to) {
        List<Metric> list;

        synchronized (block) {
            try {
                list = Arrays.asList(serviceInterface.getList(typeMetric.name(), from, to));
            } catch (ClientTransportException e) {
                list = EMPTY;
            }
        }

        return list;
    }

    @Override
    public void setAddress(final String address) {
        try {
            transportService = new SOAPTransportService(TransportEditor.getURL(address + WSDL));
            synchronized (block) {
                serviceInterface = transportService.getSOAPTransportPort();
            }
            status = HTTP_200;
        } catch (WebServiceException e) {
            status = HTTPException.HTTP_404;
        }
    }

    @Override
    public HTTPException getStatus() {
        return status;
    }

    @Override
    public void close() {
    }

}
