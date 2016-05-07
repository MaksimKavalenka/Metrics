package by.training.bean.options;

import by.training.dao.TransportDAO;
import by.training.transport.rest.*;
import by.training.transport.soap.SOAPTransport;

public enum Transport {

    REST("REST") {
        @Override
        public TransportDAO createDAO(final String address) {
            return new RESTTransport(address);
        }
    },

    SOAP("SOAP") {
        @Override
        public TransportDAO createDAO(final String address) {
            return new SOAPTransport(address);
        }
    };

    private String name;

    private Transport(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract TransportDAO createDAO(String address);

}
