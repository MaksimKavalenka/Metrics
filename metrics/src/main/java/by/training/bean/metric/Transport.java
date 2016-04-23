package by.training.bean.metric;

import by.training.bean.metric.transport.OperatingSystemTransport;
import by.training.bean.metric.transport.Transporter;

public enum Transport {

    OPERATING_SYSTEM(new OperatingSystemTransport(), "Operating system");

    private Transporter transporter;
    private String      name;

    private Transport(final Transporter transporter, final String name) {
        this.transporter = transporter;
        this.name = name;
    }

    public Transporter getTransporter() {
        return transporter;
    }

    public String getName() {
        return name;
    }

}
