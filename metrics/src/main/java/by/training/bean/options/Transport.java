package by.training.bean.options;

import by.training.dao.TransportDAO;
import by.training.transport.rest.*;
import by.training.transport.soap.SOAPTransport;

public enum Transport {

    REST("REST", "http://localhost:8080/metrics/rest/metric") {
        @Override
        public TransportDAO createDAO(final String address) {
            return new RESTTransport(address);
        }
    },

    SOAP("SOAP", "http://localhost:8080/metrics/soap") {
        @Override
        public TransportDAO createDAO(final String address) {
            return new SOAPTransport(address);
        }
    };

    private String name;
    private String defaultAddress;

    private Transport(final String name, final String defaultAddress) {
        this.name = name;
        this.defaultAddress = defaultAddress;
    }

    public String getName() {
        return name;
    }

    public String getDefaultAddress() {
        return defaultAddress;
    }

    public abstract TransportDAO createDAO(String address);

}
