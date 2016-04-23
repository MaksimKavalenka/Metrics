package by.training.bean.storage;

import java.util.Date;

public class Storage implements Comparable<Storage> {

    private Date   date;
    private double value;

    public Storage(final Date date, final double value) {
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
    public int compareTo(final Storage o) {
        return date.compareTo(o.getDate());
    }

}
