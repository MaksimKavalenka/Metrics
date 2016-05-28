package by.training.bean;

import java.io.Serializable;

public class DashboardWidget implements Comparable<DashboardWidget>, Serializable {

    private static final long serialVersionUID = -6693462536351341933L;

    private int               idDashboard;
    private int               idWidget;

    public DashboardWidget() {
    }

    public DashboardWidget(final int idDashboard, final int idWidget) {
        this.idDashboard = idDashboard;
        this.idWidget = idWidget;
    }

    public int getIdDashboard() {
        return idDashboard;
    }

    public void setIdDashboard(final int idDashboard) {
        this.idDashboard = idDashboard;
    }

    public int getIdWidget() {
        return idWidget;
    }

    public void setIdWidget(final int idWidget) {
        this.idWidget = idWidget;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + idDashboard;
        result = prime * result + idWidget;
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
        DashboardWidget other = (DashboardWidget) obj;
        if (idDashboard != other.idDashboard) {
            return false;
        }
        if (idWidget != other.idWidget) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(final DashboardWidget o) {
        int value = idDashboard - o.getIdDashboard();
        if (value == 0) {
            value = idWidget - o.getIdWidget();
        }
        return value;
    }

}
