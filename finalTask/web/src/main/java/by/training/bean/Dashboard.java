package by.training.bean;

import java.io.Serializable;

public class Dashboard implements Comparable<Dashboard>, Serializable {

    private static final long serialVersionUID = 1569833650274367256L;

    private int               id;
    private String            name;
    private String            description;

    public Dashboard() {
    }

    public Dashboard(final int id, final String name, final String description) {
        this.id = id;
        this.name = name;
        this.description = description;
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
