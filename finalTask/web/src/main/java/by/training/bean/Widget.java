package by.training.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import by.training.options.MetricType;
import by.training.options.Period;
import by.training.options.RefreshInterval;

@Entity
@Table(name = "Widget")
public class Widget implements Comparable<Widget>, Serializable {

    private static final long serialVersionUID = 8296154453314999388L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id", length = 5)
    private int               id;

    @Column(name = "Name", length = 30)
    @NotNull
    private String            name;

    @Column(name = "MetricType", length = 2)
    @Enumerated(EnumType.ORDINAL)
    @NotNull
    private MetricType        metricType;

    @Column(name = "RefreshInterval", length = 1)
    @Enumerated(EnumType.ORDINAL)
    @NotNull
    private RefreshInterval   refreshInterval;

    @Column(name = "Period", length = 1)
    @Enumerated(EnumType.ORDINAL)
    @NotNull
    private Period            period;

    @Column(name = "Start")
    @Temporal(TemporalType.TIME)
    private Date              start;

    @Column(name = "End")
    @Temporal(TemporalType.TIME)
    private Date              end;

    @ManyToMany(mappedBy = "widgets")
    private List<Dashboard>   dashboards;

    public Widget() {
    }

    public Widget(final Integer id, final String name, final MetricType metricType,
            final RefreshInterval refreshInterval, final Period period, final Date start,
            final Date end) {
        this.id = id;
        this.name = name;
        this.metricType = metricType;
        this.refreshInterval = refreshInterval;
        this.period = period;
        this.start = start;
        this.end = end;
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

    public Date getStart() {
        return start;
    }

    public void setStart(final Date start) {
        if ((this.start == null) || (start == null)) {
            this.start = start;
        } else {
            this.start.setTime(start.getTime());
        }
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(final Date end) {
        if ((this.end == null) || (end == null)) {
            this.end = end;
        } else {
            this.end.setTime(end.getTime());
        }
    }

    public List<Dashboard> getDashboards() {
        return dashboards;
    }

    public void setDashboards(final List<Dashboard> dashboards) {
        this.dashboards = dashboards;
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
    public int compareTo(final Widget o) {
        return id - o.getId();
    }

}
