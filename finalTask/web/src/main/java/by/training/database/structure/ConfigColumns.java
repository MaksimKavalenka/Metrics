package by.training.database.structure;

public enum ConfigColumns {

    ID("IdConfig"), NAME("Name"), DESCRIPTION("Description");

    private String column;

    private ConfigColumns(final String column) {
        this.column = column;
    }

    @Override
    public String toString() {
        return column;
    }

}
