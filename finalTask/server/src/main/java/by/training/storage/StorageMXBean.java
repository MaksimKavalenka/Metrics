package by.training.storage;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.RuntimeMXBean;
import java.lang.management.ThreadMXBean;
import java.util.ArrayList;
import java.util.List;

import com.sun.management.GarbageCollectorMXBean;
import com.sun.management.OperatingSystemMXBean;

import by.training.bean.MXBean;
import by.training.options.MetricType;

public abstract class StorageMXBean {

    private static final int                    PERCENT = 100;
    private static final int                    SEC     = 1000;
    private static final int                    MB      = 1048576;
    private static final int                    GB      = 1073741824;

    private static final GarbageCollectorMXBean GCMX    = (GarbageCollectorMXBean) ManagementFactory
            .getGarbageCollectorMXBeans().get(0);
    private static final MemoryMXBean           MMX     = ManagementFactory.getMemoryMXBean();
    private static final OperatingSystemMXBean  OSMX    = (OperatingSystemMXBean) ManagementFactory
            .getOperatingSystemMXBean();
    private static final RuntimeMXBean          RMX     = ManagementFactory.getRuntimeMXBean();
    private static final ThreadMXBean           TMX     = ManagementFactory.getThreadMXBean();

    private static List<MXBean>                 beans   = new ArrayList<>(
            MetricType.values().length);

    public static MXBean getMXBean(final MetricType metricType) {
        return beans.get(metricType.ordinal());
    }

    public static double getValue(final MetricType metricType) {
        switch (metricType) {
            case AVAILABLE_PROCESSORS:
                return OSMX.getAvailableProcessors();

            case COLLECTION_COUNT:
                return GCMX.getCollectionCount();

            case COLLECTION_TIME:
                return GCMX.getCollectionTime();

            case DAEMON_THREAD_COUNT:
                return TMX.getDaemonThreadCount();

            case FREE_PHYSICAL_MEMORY_SIZE:
                return round((double) (OSMX.getFreePhysicalMemorySize()) / GB);

            case HEAP_INIT:
                return round((double) (MMX.getHeapMemoryUsage().getInit()) / MB);

            case HEAP_MAX:
                return round((double) (MMX.getHeapMemoryUsage().getMax()) / MB);

            case HEAP_USED:
                return round((double) (MMX.getHeapMemoryUsage().getUsed()) / MB);

            case PEAK_THREAD_COUNT:
                return TMX.getPeakThreadCount();

            case SYSTEM_LOAD_AVERAGE:
                return round(OSMX.getSystemLoadAverage() / PERCENT);

            case THREAD_COUNT:
                return TMX.getThreadCount();

            case TOTAL_PHYSICAL_MEMORY_SIZE:
                return round((double) (OSMX.getTotalPhysicalMemorySize()) / GB);

            case UPTIME:
                return round((double) (RMX.getUptime()) / SEC);
        }
        return 0;
    }

    private static double round(final double value) {
        return Math.rint(value * 100) / 100;
    }

    public static void init() {
        for (MetricType metricType : MetricType.values()) {
            beans.add(new MXBean(metricType));
        }
    }

    public static void deactivate() {
        for (MXBean bean : beans) {
            bean.deactivate();
        }
    }

}
