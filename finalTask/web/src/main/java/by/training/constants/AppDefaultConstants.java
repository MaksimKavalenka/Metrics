package by.training.constants;

import java.util.LinkedList;
import java.util.List;

import by.training.options.MetricType;
import by.training.options.Period;
import by.training.options.RefreshInterval;

public abstract class AppDefaultConstants {

    public static final List<String> METRIC_TYPE_LIST      = new LinkedList<>();
    public static final List<String> PERIOD_LIST           = new LinkedList<>();
    public static final List<String> REFRESH_INTERVAL_LIST = new LinkedList<>();

    static {
        for (MetricType metricType : MetricType.values()) {
            METRIC_TYPE_LIST.add(metricType.getTitle());
        }

        for (RefreshInterval refreshInterval : RefreshInterval.values()) {
            REFRESH_INTERVAL_LIST.add(refreshInterval.getName());
        }

        PERIOD_LIST.add(Period.LAST_MINUTES_15.getName());
        PERIOD_LIST.add(Period.LAST_MINUTES_30.getName());
        PERIOD_LIST.add(Period.LAST_HOUR.getName());
    }

}
