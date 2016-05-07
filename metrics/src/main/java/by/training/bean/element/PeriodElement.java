package by.training.bean.element;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

import by.training.bean.options.Period;

@XmlAccessorType(XmlAccessType.FIELD)
public class PeriodElement {

    @XmlAttribute(name = "type", required = true)
    private int  period;

    @XmlAttribute(required = false)
    private Date from;

    @XmlAttribute(required = false)
    private Date to;

    public PeriodElement() {
        super();
    }

    public PeriodElement(final Period period, final Date from, final Date to) {
        super();
        this.period = period.ordinal();
        this.from = from;
        this.to = to;
    }

    public Period getPeriod() {
        return Period.values()[period];
    }

    public void setPeriod(final Period period) {
        this.period = period.ordinal();

        if (period != Period.CUSTOM) {
            from = null;
            to = null;
        }
    }

    public Date getFrom() {
        return from;
    }

    public void setFrom(final Date from) {
        this.from = from;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(final Date to) {
        this.to = to;
    }

}
