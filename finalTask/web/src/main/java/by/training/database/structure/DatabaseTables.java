package by.training.database.structure;

public enum DatabaseTables {

    CONFIG("Config"), WIDGET("Widget"), CONFIG_WIDGET("ConfigWidget");

    private String table;

    private DatabaseTables(final String table) {
        this.table = table;
    }

    @Override
    public String toString() {
        return table;
    }

}
