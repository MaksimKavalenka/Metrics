package by.training.options;

import java.util.Date;

public enum Period {

    LAST_MINUTES_15("Last 15 minutes", 900000), LAST_MINUTES_30("Last 30 minutes",
            1800000), LAST_HOUR("Last hour", 3600000), CUSTOM("Custom", Long.MAX_VALUE);

    private String name;
    private long   nano;

    private Period(final String name, final long nano) {
        this.name = name;
        this.nano = nano;
    }

    public long getNanoTime() {
        return nano;
    }

    public long getTime() {
        return nano / 1000;
    }

    public Date getDate() {
        return new Date(new Date().getTime() - nano);
    }

    @Override
    public String toString() {
        return name;
    }

}
