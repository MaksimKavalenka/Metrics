package by.training.bean.element;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)
public class ParametersElement {

    @XmlAttribute(required = false)
    private String address;

    @XmlAttribute(required = false)
    private String host;

    @XmlAttribute(required = false)
    private String port;

    public ParametersElement() {
    }

    public ParametersElement(final String address, final String host, final String port) {
        this.address = address;
        this.host = host;
        this.port = port;
    }

    public ParametersElement(final ParametersElement parameters) {
        setParameters(parameters);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(final String address) {
        this.address = address;
    }

    public String getHost() {
        return host;
    }

    public void setHost(final String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(final String port) {
        this.port = port;
    }

    public void setParameters(final ParametersElement parameters) {
        setParameters(parameters.getAddress(), parameters.getHost(), parameters.getPort());
    }

    public void setParameters(final String address, final String host, final String port) {
        this.address = address;
        this.host = host;
        this.port = port;
    }

}
