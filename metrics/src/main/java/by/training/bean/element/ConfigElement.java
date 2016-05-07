package by.training.bean.element;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "config")
@XmlAccessorType(XmlAccessType.FIELD)
public class ConfigElement {

    @XmlElement(required = true)
    private WidgetElement widget;

    @XmlElement(required = true)
    private IconElement   icon;

    public ConfigElement() {
        super();
    }

    public ConfigElement(final WidgetElement widget, final IconElement icon) {
        super();
        this.widget = widget;
        this.icon = icon;
    }

    public WidgetElement getWidget() {
        return widget;
    }

    public void setWidget(final WidgetElement widget) {
        this.widget = widget;
    }

    public IconElement getIcon() {
        return icon;
    }

    public void setIcon(final IconElement icon) {
        this.icon = icon;
    }

}
