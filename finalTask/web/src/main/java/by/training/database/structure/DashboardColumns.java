package by.training.database.structure;

public enum DashboardColumns {

    ID("IdDashboard"), NAME("Name"), DESCRIPTION("Description");

    private String column;

    private DashboardColumns(final String column) {
        this.column = column;
    }

    @Override
    public String toString() {
        return column;
    }

}
