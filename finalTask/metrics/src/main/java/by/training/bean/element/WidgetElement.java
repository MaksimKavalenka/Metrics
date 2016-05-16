package by.training.bean.element;

import java.util.Arrays;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class WidgetElement {

    @XmlAttribute(required = true)
    private int           active;

    @XmlElement(required = true)
    private List<Integer> iconOptions;

    public WidgetElement() {
    }

    public WidgetElement(final int active, final List<Integer> iconOptions) {
        this.active = active;
        this.iconOptions = iconOptions;
    }

    public WidgetElement(final int active, final Integer... iconOptions) {
        this.active = active;
        this.iconOptions = Arrays.asList(iconOptions);
    }

    public int getActive() {
        return active;
    }

    public void setActive(final int active) {
        this.active = active;
    }

    public List<Integer> getIconOptions() {
        return iconOptions;
    }

    public void setIconOptions(final List<Integer> iconOptions) {
        this.iconOptions = iconOptions;
    }

}
