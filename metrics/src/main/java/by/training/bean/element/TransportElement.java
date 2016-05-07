package by.training.bean.element;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

import by.training.bean.options.Transport;
import by.training.dao.TransportDAO;

@XmlAccessorType(XmlAccessType.FIELD)
public class TransportElement {

    @XmlAttribute(name = "type", required = true)
    private int    transport;

    @XmlAttribute(required = false)
    private String address;

    public TransportElement() {
        super();
    }

    public TransportElement(final Transport transport, final String address) {
        super();
        this.transport = transport.ordinal();
        this.address = address;
    }

    public Transport getTransport() {
        return Transport.values()[transport];
    }

    public void setTransport(final Transport transport) {
        this.transport = transport.ordinal();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(final String address) {
        this.address = address;
    }

    public TransportDAO createDAO() {
        return Transport.values()[transport].createDAO(address);
    }

}
