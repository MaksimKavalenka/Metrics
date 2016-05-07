package by.training.bean.metric;

import java.util.Date;

public class Metric implements Comparable<Metric> {

    private Date   date;
    private double value;

    public Metric() {
        super();
    }

    public Metric(final Date date, final double value) {
        super();
        this.date = date;
        this.value = value;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(final Date date) {
        this.date = date;
    }

    public double getValue() {
        return value;
    }

    public void setValue(final double value) {
        this.value = value;
    }

    @Override
    public int compareTo(final Metric o) {
        return date.compareTo(o.getDate());
    }

}
