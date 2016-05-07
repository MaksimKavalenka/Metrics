package by.training.bean.options;

public enum MetricType {

    AVAILABLE_PROCESSORS("Available processors", "count"),

    COLLECTION_COUNT("Collection count", "number"),

    COLLECTION_TIME("Collection time", "milliseconds"),

    DAEMON_THREAD_COUNT("Daemon thread count", "number"),

    FREE_PHYSICAL_MEMORY_SIZE("Free physical memory size", "GB"),

    HEAP_INIT("Heap init", "MB"),

    HEAP_MAX("Heap max", "MB"),

    HEAP_USED("Heap used", "MB"),

    PEAK_THREAD_COUNT("Peak thread count", "number"),

    SYSTEM_LOAD_AVERAGE("System load average", "%"),

    THREAD_COUNT("Thread count", "number"),

    TOTAL_PHYSICAL_MEMORY_SIZE("Total physical memory size", "GB"),

    UPTIME("Uptime", "seconds");

    private final String title;
    private final String unit;

    private MetricType(final String title, final String unit) {
        this.title = title;
        this.unit = unit;
    }

    public String getTitle() {
        return title;
    }

    public String getUnit() {
        return unit;
    }

}
