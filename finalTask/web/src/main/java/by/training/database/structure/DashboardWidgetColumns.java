package by.training.database.structure;

public enum DashboardWidgetColumns {

    ID_DASHBOARD("IdDashboard"), ID_WIDGET("IdWidget");

    private String column;

    private DashboardWidgetColumns(final String column) {
        this.column = column;
    }

    @Override
    public String toString() {
        return column;
    }

}
