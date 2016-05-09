package by.training.bean.element;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

import by.training.bean.options.MetricType;

@XmlAccessorType(XmlAccessType.FIELD)
public class MetricTypeElement {

    @XmlAttribute(name = "type", required = true)
    private int     metricType;

    @XmlAttribute(name = "set", required = true)
    private boolean setTitle;

    public MetricTypeElement() {
    }

    public MetricTypeElement(final MetricType metricType, final boolean setTitle) {
        this.metricType = metricType.ordinal();
        this.setTitle = setTitle;
    }

    public MetricType getMetricType() {
        return MetricType.values()[metricType];
    }

    public void setMetricType(final MetricType metricType) {
        this.metricType = metricType.ordinal();
    }

    public boolean isSetTitle() {
        return setTitle;
    }

    public void setSetTitle(final boolean setTitle) {
        this.setTitle = setTitle;
    }

}
