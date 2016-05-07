package by.training.bean.element;

import java.util.Arrays;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class IconElement {

    @XmlElement(required = true)
    private List<OptionsElement> options;

    public IconElement() {
        super();
    }

    public IconElement(final List<OptionsElement> options) {
        super();
        this.options = options;
    }

    public IconElement(final OptionsElement... options) {
        this.options = Arrays.asList(options);
    }

    public List<OptionsElement> getOptions() {
        return options;
    }

    public void setOptions(final List<OptionsElement> options) {
        this.options = options;
    }

}
