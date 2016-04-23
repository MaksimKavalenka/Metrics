package by.training.bean.element;

import java.util.Arrays;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class IconElement {

    @XmlElement(name = "metric", required = true)
    private List<MetricElement> metrics;

    public IconElement() {
        super();
    }

    public IconElement(final List<MetricElement> metrics) {
        super();
        this.metrics = metrics;
    }

    public IconElement(final MetricElement... metrics) {
        this.metrics = Arrays.asList(metrics);
    }

    public List<MetricElement> getMetrics() {
        return metrics;
    }

    public void setMetrics(final List<MetricElement> metrics) {
        this.metrics = metrics;
    }

}
