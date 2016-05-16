package by.training.transport.soap;

import static by.training.exception.HTTPException.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.xml.ws.WebServiceException;

import by.training.bean.element.ParametersElement;
import by.training.bean.metric.Metric;
import by.training.dao.TransportDAO;
import by.training.editor.TransportEditor;
import by.training.exception.HTTPException;
import by.training.options.MetricType;
import by.training.transport.soap.wsdl.SOAPTransportService;
import by.training.transport.soap.wsdl.SOAPWebServiceInterface;

public class SOAPTransport implements TransportDAO {

    private static final List<Metric> DEFAULT = new ArrayList<>(0);
    private static final String       WSDL    = "?wsdl";

    private Object                    block;
    private HTTPException             status;
    private SOAPTransportService      transportService;
    private SOAPWebServiceInterface   serviceInterface;

    public SOAPTransport(final ParametersElement parameters) {
        block = new Object();
        setParameters(parameters);
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
            } catch (WebServiceException e) {
                list = DEFAULT;
            }
        }

        return list;
    }

    @Override
    public void setParameters(final ParametersElement parameters) {
        try {
            transportService = new SOAPTransportService(
                    TransportEditor.getURL(parameters.getAddress() + WSDL));
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
