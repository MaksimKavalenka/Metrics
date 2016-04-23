package by.training.bean.metric.transport;

import static by.training.bean.metric.TypeMetric.round;

import java.lang.management.ManagementFactory;

import com.sun.management.OperatingSystemMXBean;

import by.training.bean.metric.TypeMetric;

public class OperatingSystemTransport implements Transporter {

    private static final OperatingSystemMXBean OSMX_BEAN = (OperatingSystemMXBean) ManagementFactory
            .getOperatingSystemMXBean();

    private static final int                   PERCENT   = 100;
    private static final int                   MB        = 1048576;
    private static final int                   GB        = 1073741824;
    private static final int                   SEC       = 1000000000;

    @Override
    public double getValue(final TypeMetric type) {
        switch (type) {
            case AVAILABLE_PROCESSORS:
                return round(OSMX_BEAN.getAvailableProcessors());
            case BUSY_PHYSICAL_MEMORY_SIZE:
                return round((double) (OSMX_BEAN.getTotalPhysicalMemorySize()
                        - OSMX_BEAN.getFreePhysicalMemorySize()) / GB);
            case COMMITTED_VIRTUAL_MEMORY_SIZE:
                return round((double) OSMX_BEAN.getCommittedVirtualMemorySize() / MB);
            case FREE_PHYSICAL_MEMORY_SIZE:
                return round((double) (OSMX_BEAN.getFreePhysicalMemorySize()) / GB);
            case FREE_SWAP_SPACE_SIZE:
                return round((double) (OSMX_BEAN.getFreeSwapSpaceSize()) / GB);
            case PROCESS_CPU_LOAD:
                return round(OSMX_BEAN.getProcessCpuLoad() * PERCENT);
            case PROCESS_CPU_TIME:
                return round((double) (OSMX_BEAN.getProcessCpuTime()) / SEC);
            case SYSTEM_CPU_LOAD:
                return round(OSMX_BEAN.getSystemCpuLoad() * PERCENT);
            case SYSTEM_LOAD_AVERAGE:
                return round(OSMX_BEAN.getSystemLoadAverage() / PERCENT);
            case TOTAL_PHYSICAL_MEMORY_SIZE:
                return round(OSMX_BEAN.getTotalPhysicalMemorySize() / GB);
            case TOTAL_SWAP_SPACE_SIZE:
                return round(OSMX_BEAN.getTotalSwapSpaceSize() / GB);
            default:
                return 0;
        }
    }

}
