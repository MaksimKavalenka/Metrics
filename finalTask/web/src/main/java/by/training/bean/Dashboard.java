package by.training.bean;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Dashboard")
public class Dashboard implements Comparable<Dashboard>, Serializable {

    private static final long serialVersionUID = 1569833650274367256L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id", length = 5)
    private int               id;

    @Column(name = "Name", length = 30)
    @NotNull
    private String            name;

    @Column(name = "Description", length = 30)
    private String            description;

    @ManyToMany
    @JoinTable(name = "DashboardWidget", joinColumns = {
            @JoinColumn(name = "IdDashboard")}, inverseJoinColumns = {
                    @JoinColumn(name = "IdWidget")})
    private List<Widget>      widgets;

    public Dashboard() {
    }

    public Dashboard(final int id, final String name, final String description,
            final List<Widget> widgets) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.widgets = widgets;
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
        Dashboard other = (Dashboard) obj;
        if (id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(final Dashboard o) {
        return id - o.getId();
    }

}
