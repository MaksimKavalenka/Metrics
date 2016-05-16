package by.training.transport.soap.wsdl;

import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;

@WebServiceClient(name = "SOAPTransportService", targetNamespace = "http://soap.transport.training.by/")
public class SOAPTransportService extends Service {

    private final static QName SOAPTRANSPORTSERVICE_QNAME = new QName(
            "http://soap.transport.training.by/", "SOAPTransportService");

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
    public SOAPWebServiceInterface getSOAPTransportPort() {
        return super.getPort(new QName("http://soap.transport.training.by/", "SOAPTransportPort"),
                SOAPWebServiceInterface.class);
    }

    @WebEndpoint(name = "SOAPTransportPort")
    public SOAPWebServiceInterface getSOAPTransportPort(final WebServiceFeature... features) {
        return super.getPort(new QName("http://soap.transport.training.by/", "SOAPTransportPort"),
                SOAPWebServiceInterface.class, features);
    }

}
