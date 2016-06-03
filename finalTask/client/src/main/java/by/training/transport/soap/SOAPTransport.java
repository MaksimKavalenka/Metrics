package by.training.transport.soap;

import static by.training.constants.StubConstants.*;
import static by.training.exception.HTTPException.*;

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

    private static final String     WSDL   = "?wsdl";

    private HTTPException           status = HTTP_404;

    private SOAPTransportService    transportService;
    private SOAPWebServiceInterface serviceInterface;

    public SOAPTransport(final ParametersElement parameters) {
        setParameters(parameters);
    }

    @Override
    public Metric getLast(final MetricType metricType) {
        Metric metric = DEFAULT_VALUE;

        try {
            synchronized (this) {
                metric = serviceInterface.getLast(metricType.name());
            }
            status = HTTP_200;
        } catch (WebServiceException e) {
            status = HTTP_503;
        }

        return metric;
    }

    @Override
    public List<Metric> getList(final MetricType metricType, final Date from, final Date to) {
        List<Metric> list = DEFAULT_LIST;

        try {
            synchronized (this) {
                list = Arrays.asList(serviceInterface.getList(metricType.name(), from, to));
            }
            status = HTTP_200;
        } catch (WebServiceException e) {
            status = HTTP_503;
        }

        return list;
    }

    @Override
    public void setParameters(final ParametersElement parameters) {
        try {
            transportService = new SOAPTransportService(
                    TransportEditor.getUrl(parameters.getAddress() + WSDL));
            synchronized (this) {
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
