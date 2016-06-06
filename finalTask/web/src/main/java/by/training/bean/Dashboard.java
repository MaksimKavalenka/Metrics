package by.training.bean;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Dashboard")
public class Dashboard extends Model {

    private static final long serialVersionUID = 1569833650274367256L;

    @Column(name = "Name", length = 30)
    @NotNull
    private String            name;

    @Column(name = "Description", length = 30)
    private String            description;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.REFRESH, CascadeType.PERSIST}, targetEntity = Widget.class)
    @JoinTable(name = "DashboardWidget", inverseJoinColumns = @JoinColumn(name = "IdWidget", nullable = false, updatable = false), joinColumns = @JoinColumn(name = "IdDashboard", nullable = false, updatable = false))
    private List<Widget>      widgets;

    public Dashboard() {
        super();
    }

    public Dashboard(final int id, final String name, final String description,
            final List<Widget> widgets) {
        super(id);
        this.name = name;
        this.description = description;
        this.widgets = widgets;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public List<Widget> getWidgets() {
        return widgets;
    }

    public void setWidgets(final List<Widget> widgets) {
        this.widgets = widgets;
    }

}
