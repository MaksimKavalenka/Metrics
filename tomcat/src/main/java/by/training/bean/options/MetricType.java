package by.training.bean.options;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.RuntimeMXBean;
import java.lang.management.ThreadMXBean;
import java.util.Date;
import java.util.Random;

import javax.ws.rs.Path;

import com.sun.management.GarbageCollectorMXBean;
import com.sun.management.OperatingSystemMXBean;

import by.training.bean.metric.Metric;
import by.training.storage.Storage;

public enum MetricType implements Runnable {

    AVAILABLE_PROCESSORS {
        @Override
        protected double getValue() {
            return OSMX.getAvailableProcessors();
        }
    },

    COLLECTION_COUNT {
        @Override
        protected double getValue() {
            return GCMX.getCollectionCount();
        }
    },

    COLLECTION_TIME {
        @Override
        protected double getValue() {
            return GCMX.getCollectionTime();
        }
    },

    DAEMON_THREAD_COUNT {
        @Override
        protected double getValue() {
            return TMX.getDaemonThreadCount();
        }
    },

    FREE_PHYSICAL_MEMORY_SIZE {
        @Override
        protected double getValue() {
            return round((double) (OSMX.getFreePhysicalMemorySize()) / GB);
        }
    },

    HEAP_INIT {
        @Override
        protected double getValue() {
            return round((double) (MMX.getHeapMemoryUsage().getInit()) / MB);
        }
    },

    HEAP_MAX {
        @Override
        protected double getValue() {
            return round((double) (MMX.getHeapMemoryUsage().getMax()) / MB);
        }
    },

    HEAP_USED {
        @Override
        protected double getValue() {
            return round((double) (MMX.getHeapMemoryUsage().getUsed()) / MB);
        }
    },

    PEAK_THREAD_COUNT {
        @Override
        protected double getValue() {
            return TMX.getPeakThreadCount();
        }
    },

    SYSTEM_LOAD_AVERAGE {
        @Override
        protected double getValue() {
            return round(OSMX.getSystemLoadAverage() / PERCENT);
        }
    },

    THREAD_COUNT {
        @Override
        protected double getValue() {
            return TMX.getThreadCount();
        }
    },

    TOTAL_PHYSICAL_MEMORY_SIZE {
        @Override
        protected double getValue() {
            return round((double) (OSMX.getTotalPhysicalMemorySize()) / GB);
        }
    },

    UPTIME {
        @Override
        protected double getValue() {
            return round((double) (RMX.getUptime()) / SEC);
        }
    };

    private static final GarbageCollectorMXBean GCMX    = (GarbageCollectorMXBean) ManagementFactory
            .getGarbageCollectorMXBeans().get(0);
    private static final MemoryMXBean           MMX     = ManagementFactory.getMemoryMXBean();
    private static final OperatingSystemMXBean  OSMX    = (OperatingSystemMXBean) ManagementFactory
            .getOperatingSystemMXBean();
    private static final RuntimeMXBean          RMX     = ManagementFactory.getRuntimeMXBean();
    private static final ThreadMXBean           TMX     = ManagementFactory.getThreadMXBean();

    private static final int                    PERCENT = 100;
    private static final int                    SEC     = 1000;
    private static final int                    MB      = 1048576;
    private static final int                    GB      = 1073741824;

    private final Storage                       storage;
    private boolean                             active;

    private MetricType() {
        storage = new Storage();
        active = true;
    }

    @Path("/storage")
    public Storage getStorageEditor() {
        return storage;
    }

    protected abstract double getValue();

    public static double round(final double value) {
        return Math.rint(value * 100) / 100;
    }

    public void deactivate() {
        active = false;
    }

    @Override
    public void run() {
        init();
        while (active) {
            Metric metric = new Metric(new Date(), getValue());
            storage.add(metric);

            synchronized (this) {
                try {
                    wait(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void init() {
        Random random = new Random();
        long ms = System.currentTimeMillis();
        for (int i = 0; i < 3600; i++) {
            ms -= 1000;
            int value = random.nextInt(100);
            storage.add(new Metric(new Date(ms), value));
        }
    }

}
