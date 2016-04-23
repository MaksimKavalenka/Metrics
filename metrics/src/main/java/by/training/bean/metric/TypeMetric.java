package by.training.bean.metric;

public enum TypeMetric {

    AVAILABLE_PROCESSORS("Available processors", "count"),

    BUSY_PHYSICAL_MEMORY_SIZE("Busy physical memory size", "GB"),

    COMMITTED_VIRTUAL_MEMORY_SIZE("Committed virtual memory size", "MB"),

    FREE_PHYSICAL_MEMORY_SIZE("Free physical memory size", "GB"),

    FREE_SWAP_SPACE_SIZE("Free swap space size", "GB"),

    PROCESS_CPU_LOAD("Process CPU load", "%"),

    PROCESS_CPU_TIME("Process CPU time", "seconds"),

    SYSTEM_CPU_LOAD("System CPU load", "%"),

    SYSTEM_LOAD_AVERAGE("System load average", "%"),

    TOTAL_PHYSICAL_MEMORY_SIZE("Total physical memory size", "GB"),

    TOTAL_SWAP_SPACE_SIZE("Total swap space size", "GB");

    private final String title;
    private final String unit;

    // private NavigableSet<Storage> storage;

    private TypeMetric(final String title, final String unit) {
        this.title = title;
        this.unit = unit;
        // storage = new TreeSet<>();
    }

    public String getTitle() {
        return title;
    }

    public String getUnit() {
        return unit;
    }

    // FOR THE FUTURE
    /*
     * public SortedSet<Storage> getStorage(final Date date) { return storage.headSet(new
     * Storage(date, 0)); }
     */

    // FOR THE FUTURE
    /*
     * public void addToStorage(final Storage storage) { this.storage.add(storage); }
     */

    public static double round(final double value) {
        return Math.rint(value * 100) / 100;
    }

}
