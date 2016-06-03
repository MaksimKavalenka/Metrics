package by.training.options;

public enum RefreshInterval {

    SECOND("1 second", 1000), SECOND_5("5 seconds", 5000), SECOND_15("15 seconds", 15000);

    private String name;
    private long   nanoTime;

    private RefreshInterval(final String name, final long nanoTime) {
        this.name = name;
        this.nanoTime = nanoTime;
    }

    public long getNanoTime() {
        return nanoTime;
    }

    public long getTime() {
        return nanoTime / 1000;
    }

    @Override
    public String toString() {
        return name;
    }

}
