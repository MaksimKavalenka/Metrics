package by.training.bean;

import java.io.Serializable;

public class Widget implements Comparable<Widget>, Serializable {

    private static final long serialVersionUID = 8296154453314999388L;

    private int               id;
    private String            title;
    private int               metricType;
    private int               period;
    private int               refreshInterval;

    public Widget() {
    }

    public Widget(final Integer id, final String title, final int metricType, final int period,
            final int refreshInterval) {
        this.id = id;
        this.title = title;
        this.metricType = metricType;
        this.period = period;
        this.refreshInterval = refreshInterval;
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public int getMetricType() {
        return metricType;
    }

    public void setMetricType(final int metricType) {
        this.metricType = metricType;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(final int period) {
        this.period = period;
    }

    public int getRefreshInterval() {
        return refreshInterval;
    }

    public void setRefreshInterval(final int refreshInterval) {
        this.refreshInterval = refreshInterval;
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
