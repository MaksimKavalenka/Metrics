package by.training.bean.metric;

public enum RefreshInterval {

    SECOND(1000, "1 second"), SECOND_5(5000, "5 seconds"), SECOND_15(15000, "15 seconds");

    private int    value;
    private String name;

    private RefreshInterval(final int value, final String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

}
