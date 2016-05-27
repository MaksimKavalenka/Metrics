package by.training.bean;

import java.io.Serializable;
import java.util.Date;

import by.training.options.MetricType;
import by.training.options.Period;
import by.training.options.RefreshInterval;

public class Widget implements Comparable<Widget>, Serializable {

    private static final long serialVersionUID = 8296154453314999388L;

    private int               id;
    private String            name;
    private MetricType        metricType;
    private RefreshInterval   refreshInterval;
    private Period            period;
    private Date              from;
    private Date              to;

    public Widget() {
    }

    public Widget(final Integer id, final String name, final MetricType metricType,
            final RefreshInterval refreshInterval, final Period period, final Date from,
            final Date to) {
        this.id = id;
        this.name = name;
        this.metricType = metricType;
        this.refreshInterval = refreshInterval;
        this.period = period;
        this.from = from;
        this.to = to;
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public MetricType getMetricType() {
        return metricType;
    }

    public void setMetricType(final MetricType metricType) {
        this.metricType = metricType;
    }

    public RefreshInterval getRefreshInterval() {
        return refreshInterval;
    }

    public void setRefreshInterval(final RefreshInterval refreshInterval) {
        this.refreshInterval = refreshInterval;
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(final Period period) {
        this.period = period;
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
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
        Widget other = (Widget) obj;
        if (id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(final Widget ob) {
        return id - ob.getId();
    }

}
