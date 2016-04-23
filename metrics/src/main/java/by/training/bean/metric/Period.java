package by.training.bean.metric;

import java.util.Date;

public enum Period {

    REAL_TIME(0, "Real time"), LAST_MINUTES_15(900000, "Last 15 minutes"), LAST_MINUTES_30(1800000,
            "Last 30 minutes"), LAST_HOUR(3600000, "Last hour");

    private int    value;
    private String name;

    private Period(final Integer value, final String name) {
        this.value = value;
        this.name = name;
    }

    public Date getDate() {
        return new Date(new Date().getTime() - value);
    }

    public String getName() {
        return name;
    }

}
