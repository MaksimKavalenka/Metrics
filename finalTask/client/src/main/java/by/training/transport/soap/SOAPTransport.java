package by.training.transport.soap;

import static by.training.constants.ResponseStatus.*;
import static by.training.constants.StubConstants.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.xml.ws.WebServiceException;

import by.training.bean.element.ParametersElement;
import by.training.bean.metric.Metric;
import by.training.constants.ResponseStatus;
import by.training.dao.TransportDAO;
import by.training.editor.TransportEditor;
import by.training.options.MetricType;
import by.training.transport.soap.wsdl.SOAPTransportService;
import by.training.transport.soap.wsdl.SOAPWebServiceInterface;

public class SOAPTransport implements TransportDAO {

    private static final String     WSDL   = "?wsdl";

    private ResponseStatus          status = NOT_FOUND;

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
            status = OK;
        } catch (WebServiceException e) {
            status = SERVICE_UNAVAILABLE;
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
            status = OK;
        } catch (WebServiceException e) {
            status = SERVICE_UNAVAILABLE;
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
            status = OK;
        } catch (WebServiceException e) {
            status = NOT_FOUND;
        }
    }

    @Override
    public ResponseStatus getStatus() {
        return status;
    }

    @Override
    public void close() {
    }

}
