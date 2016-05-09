package by.training.bean.element;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import by.training.bean.options.RefreshInterval;

@XmlAccessorType(XmlAccessType.FIELD)
public class OptionsElement {

    @XmlElement(required = true)
    private String            title;

    @XmlElement(name = "metricType", required = true)
    private MetricTypeElement metricTypeElement;

    @XmlElement(name = "transport", required = true)
    private TransportElement  transportElement;

    @XmlElement(name = "period", required = true)
    private PeriodElement     periodElement;

    @XmlElement(required = true)
    private int               refreshInterval;

    public OptionsElement() {
    }

    public OptionsElement(final String title, final MetricTypeElement metricTypeElement,
            final TransportElement transportElement, final PeriodElement periodElement,
            final RefreshInterval refreshInterval) {
        this.title = title;
        this.metricTypeElement = metricTypeElement;
        this.transportElement = transportElement;
        this.periodElement = periodElement;
        this.refreshInterval = refreshInterval.ordinal();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public MetricTypeElement getMetricTypeElement() {
        return metricTypeElement;
    }

    public void setMetricTypeElement(final MetricTypeElement metricTypeElement) {
        this.metricTypeElement = metricTypeElement;
    }

    public TransportElement getTransportElement() {
        return transportElement;
    }

    public void setTransportElement(final TransportElement transportElement) {
        this.transportElement = transportElement;
    }

    public PeriodElement getPeriodElement() {
        return periodElement;
    }

    public void setPeriodElement(final PeriodElement periodElement) {
        this.periodElement = periodElement;
    }

    public RefreshInterval getRefreshInterval() {
        return RefreshInterval.values()[refreshInterval];
    }

    public void setRefreshInterval(final RefreshInterval refreshInterval) {
        this.refreshInterval = refreshInterval.ordinal();
    }

}
