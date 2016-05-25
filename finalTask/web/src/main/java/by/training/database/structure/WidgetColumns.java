package by.training.database.structure;

public enum WidgetColumns {

    ID("IdWidget"), TITLE("Title"), METRIC_TYPE("MetricType"), PERIOD("Period"), REFRESH_INTERVAL(
            "RefreshInterval");

    private String column;

    private WidgetColumns(final String column) {
        this.column = column;
    }

    @Override
    public String toString() {
        return column;
    }

}
