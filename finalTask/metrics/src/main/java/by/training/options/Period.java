package by.training.options;

import java.util.Date;

public enum Period {

    LAST_MINUTES_15("Last 15 minutes", 900000), LAST_MINUTES_30("Last 30 minutes",
            1800000), LAST_HOUR("Last hour", 3600000), CUSTOM("Custom", 0);

    private String name;
    private int    value;

    private Period(final String name, final Integer value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Date getDate() {
        return new Date(new Date().getTime() - value);
    }

}
