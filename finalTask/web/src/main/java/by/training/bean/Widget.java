package by.training.bean;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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
public class Widget extends Model {

    private static final long serialVersionUID = -5140160665446123713L;

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

    @Column(name = "FromDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date              fromDate;

    @Column(name = "ToDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date              toDate;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.REFRESH, CascadeType.PERSIST}, targetEntity = Dashboard.class)
    @JoinTable(name = "DashboardWidget", inverseJoinColumns = @JoinColumn(name = "IdDashboard", nullable = false, updatable = false), joinColumns = @JoinColumn(name = "IdWidget", nullable = false, updatable = false))
    private List<Dashboard>   dashboards;

    public Widget() {
        super();
    }

    public Widget(final Integer id, final String name, final MetricType metricType,
            final RefreshInterval refreshInterval, final Period period, final Date fromDate,
            final Date toDate) {
        super(id);
        this.name = name;
        this.metricType = metricType;
        this.refreshInterval = refreshInterval;
        this.period = period;
        this.fromDate = fromDate;
        this.toDate = toDate;
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

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(final Date fromDate) {
        if ((this.fromDate == null) || (fromDate == null)) {
            this.fromDate = fromDate;
        } else {
            this.fromDate.setTime(fromDate.getTime());
        }
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(final Date toDate) {
        if ((this.toDate == null) || (toDate == null)) {
            this.toDate = toDate;
        } else {
            this.toDate.setTime(toDate.getTime());
        }
    }

    public List<Dashboard> getDashboards() {
        return dashboards;
    }

    public void setDashboards(final List<Dashboard> dashboards) {
        this.dashboards = dashboards;
    }

}
