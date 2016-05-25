package by.training.bean.metric;

import java.io.Serializable;
import java.util.Date;

public class Metric implements Comparable<Metric>, Serializable {

    private static final long serialVersionUID = -4340321060528443103L;

    private Date              date;
    private double            value;

    public Metric() {
    }

    public Metric(final Date date, final double value) {
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
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((date == null) ? 0 : date.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Metric other = (Metric) obj;
        if (date == null) {
            if (other.date != null) {
                return false;
            }
        } else if (!date.equals(other.date)) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(final Metric o) {
        return date.compareTo(o.getDate());
    }

}
