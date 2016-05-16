package by.training.options;

public enum RefreshInterval {

    SECOND("1 second", 1000), SECOND_5("5 seconds", 5000), SECOND_15("15 seconds", 15000);

    private String name;
    private int    value;

    private RefreshInterval(final String name, final int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

}
