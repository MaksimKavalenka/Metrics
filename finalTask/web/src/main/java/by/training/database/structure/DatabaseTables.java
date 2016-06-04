package by.training.database.structure;

public enum DatabaseTables {

    DASHBOARD("Dashboard"), WIDGET("Widget"), DASHBOARD_WIDGET("DashboardWidget");

    private String table;

    private DatabaseTables(final String table) {
        this.table = table;
    }

    @Override
    public String toString() {
        return table;
    }

}
