package by.training.bean.element;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import by.training.bean.metric.Period;
import by.training.bean.metric.RefreshInterval;
import by.training.bean.metric.Transport;
import by.training.bean.metric.TypeMetric;

@XmlAccessorType(XmlAccessType.FIELD)
public class MetricElement {

    private String title;
    private int    metricType;
    private int    transport;
    private int    period;
    private int    refreshInterval;

    public MetricElement() {
        super();
    }

    public MetricElement(final String title, final TypeMetric metricType, final Transport transport,
            final Period period, final RefreshInterval refreshInterval) {
        super();
        this.title = title;
        this.metricType = metricType.ordinal();
        this.transport = transport.ordinal();
        this.period = period.ordinal();
        this.refreshInterval = refreshInterval.ordinal();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public TypeMetric getMetricType() {
        return TypeMetric.values()[metricType];
    }

    public void setMetricType(final TypeMetric metricType) {
        this.metricType = metricType.ordinal();
    }

    public Transport getTransport() {
        return Transport.values()[transport];
    }

    public void setTransport(final Transport transport) {
        this.transport = transport.ordinal();
    }

    public Period getPeriod() {
        return Period.values()[period];
    }

    public void setPeriod(final Period period) {
        this.period = period.ordinal();
    }

    public RefreshInterval getRefreshInterval() {
        return RefreshInterval.values()[refreshInterval];
    }

    public void setRefreshInterval(final RefreshInterval refreshInterval) {
        this.refreshInterval = refreshInterval.ordinal();
    }

}
