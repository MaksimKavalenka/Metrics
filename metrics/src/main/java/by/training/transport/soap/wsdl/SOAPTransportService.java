package by.training.transport.soap.wsdl;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;

@WebServiceClient(name = "SOAPTransportService", targetNamespace = "http://soap.transport.training.by/", wsdlLocation = "http://localhost:8080/metrics/soap?wsdl")
public class SOAPTransportService extends Service {

    private final static URL                 SOAPTRANSPORTSERVICE_WSDL_LOCATION;
    private final static WebServiceException SOAPTRANSPORTSERVICE_EXCEPTION;
    private final static QName               SOAPTRANSPORTSERVICE_QNAME = new QName(
            "http://soap.transport.training.by/", "SOAPTransportService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8080/metrics/soap?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        SOAPTRANSPORTSERVICE_WSDL_LOCATION = url;
        SOAPTRANSPORTSERVICE_EXCEPTION = e;
    }

    public SOAPTransportService() {
        super(__getWsdlLocation(), SOAPTRANSPORTSERVICE_QNAME);
    }

    public SOAPTransportService(final WebServiceFeature... features) {
        super(__getWsdlLocation(), SOAPTRANSPORTSERVICE_QNAME, features);
    }

    public SOAPTransportService(final URL wsdlLocation) {
        super(wsdlLocation, SOAPTRANSPORTSERVICE_QNAME);
    }

    public SOAPTransportService(final URL wsdlLocation, final WebServiceFeature... features) {
        super(wsdlLocation, SOAPTRANSPORTSERVICE_QNAME, features);
    }

    public SOAPTransportService(final URL wsdlLocation, final QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public SOAPTransportService(final URL wsdlLocation, final QName serviceName,
            final WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    @WebEndpoint(name = "SOAPTransportPort")
    public WebServiceInterface getSOAPTransportPort() {
        return super.getPort(new QName("http://soap.transport.training.by/", "SOAPTransportPort"),
                WebServiceInterface.class);
    }

    @WebEndpoint(name = "SOAPTransportPort")
    public WebServiceInterface getSOAPTransportPort(final WebServiceFeature... features) {
        return super.getPort(new QName("http://soap.transport.training.by/", "SOAPTransportPort"),
                WebServiceInterface.class, features);
    }

    private static URL __getWsdlLocation() {
        if (SOAPTRANSPORTSERVICE_EXCEPTION != null) {
            throw SOAPTRANSPORTSERVICE_EXCEPTION;
        }
        return SOAPTRANSPORTSERVICE_WSDL_LOCATION;
    }

}
