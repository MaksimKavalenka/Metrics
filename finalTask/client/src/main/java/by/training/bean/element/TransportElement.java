package by.training.bean.element;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import by.training.options.Transport;

@XmlAccessorType(XmlAccessType.FIELD)
public class TransportElement {

    @XmlAttribute(name = "type", required = true)
    private int               transport;

    @XmlElement(required = true)
    private ParametersElement parameters;

    public TransportElement() {
    }

    public TransportElement(final Transport transport, final ParametersElement parameters) {
        this.transport = transport.ordinal();
        this.parameters = parameters;
    }

    public Transport getTransport() {
        return Transport.values()[transport];
    }

    public void setTransport(final Transport transport) {
        this.transport = transport.ordinal();
    }

    public ParametersElement getParameters() {
        return parameters;
    }

    public void setParameters(final ParametersElement parameters) {
        this.parameters = parameters;
    }

}
