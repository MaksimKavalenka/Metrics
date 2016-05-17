package by.training.options;

import by.training.bean.element.ParametersElement;
import by.training.dao.TransportDAO;
import by.training.transport.jmx.JMXTransport;
import by.training.transport.rest.*;
import by.training.transport.rmi.RMITransport;
import by.training.transport.soap.SOAPTransport;

public enum Transport {

    REST("REST", new ParametersElement("http://localhost:8080/metrics/rest/metric", null, null)) {
        @Override
        public TransportDAO createDAO(final ParametersElement parameters) {
            return new RESTTransport(parameters);
        }

    },

    SOAP("SOAP", new ParametersElement("http://localhost:8080/metrics/soap", null, null)) {
        @Override
        public TransportDAO createDAO(final ParametersElement parameters) {
            return new SOAPTransport(parameters);
        }

    },

    RMI("RMI", new ParametersElement(null, "localhost", "8082")) {
        @Override
        public TransportDAO createDAO(final ParametersElement parameters) {
            return new RMITransport(parameters);
        }
    },

    JMX("JMX", new ParametersElement(null, "localhost", "8083")) {
        @Override
        public TransportDAO createDAO(final ParametersElement parameters) {
            return new JMXTransport(parameters);
        }
    };

    private String            name;
    private ParametersElement defaultParameters;

    private Transport(final String name, final ParametersElement defaultParameters) {
        this.name = name;
        this.defaultParameters = defaultParameters;
    }

    public String getName() {
        return name;
    }

    public ParametersElement getDefaultParameters() {
        return defaultParameters;
    }

    public abstract TransportDAO createDAO(ParametersElement parameters);

}
