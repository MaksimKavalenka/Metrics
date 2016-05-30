package by.training.bean;

public class DashboardWidget {

    private int idDashboard;
    private int idWidget;

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

}
