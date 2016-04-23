package by.training.bean.element;

import java.util.Arrays;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class WidgetElement {

    private int           active;

    @XmlElement(name = "iconMetric", required = true)
    private List<Integer> iconMetrics;

    public WidgetElement() {
        super();
    }

    public WidgetElement(final int active, final List<Integer> iconMetrics) {
        super();
        this.active = active;
        this.iconMetrics = iconMetrics;
    }

    public WidgetElement(final int active, final Integer... iconMetrics) {
        this.active = active;
        this.iconMetrics = Arrays.asList(iconMetrics);
    }

    public int getActive() {
        return active;
    }

    public void setActive(final int active) {
        this.active = active;
    }

    public List<Integer> getIconMetrics() {
        return iconMetrics;
    }

    public void setIconMetric(final List<Integer> iconMetrics) {
        this.iconMetrics = iconMetrics;
    }

}
