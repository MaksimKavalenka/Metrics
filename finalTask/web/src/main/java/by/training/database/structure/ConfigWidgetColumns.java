package by.training.database.structure;

public enum ConfigWidgetColumns {

    ID_CONFIG("ConfigId"), ID_WIDGET("WidgetId");

    private String column;

    private ConfigWidgetColumns(final String column) {
        this.column = column;
    }

    @Override
    public String toString() {
        return column;
    }

}
