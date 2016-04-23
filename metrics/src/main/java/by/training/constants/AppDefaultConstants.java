package by.training.constants;

import java.awt.datatransfer.DataFlavor;
import java.util.ArrayList;
import java.util.List;

import by.training.bean.element.MetricElement;
import by.training.bean.metric.Period;
import by.training.bean.metric.RefreshInterval;
import by.training.bean.metric.Transport;
import by.training.bean.metric.TypeMetric;

public class AppDefaultConstants {

    public static final DataFlavor   DATA_FLAVOR           = new DataFlavor(MetricElement.class,
            MetricElement.class.getSimpleName());

    public static final List<String> TYPE_METRIC_LIST      = new ArrayList<>();
    public static final List<String> TRANSPORT_LIST        = new ArrayList<>();
    public static final List<String> PERIOD_LIST           = new ArrayList<>();
    public static final List<String> REFRESH_INTERVAL_LIST = new ArrayList<>();

    static {
        for (TypeMetric typeMetric : TypeMetric.values()) {
            TYPE_METRIC_LIST.add(typeMetric.getTitle());
        }

        for (Transport transport : Transport.values()) {
            TRANSPORT_LIST.add(transport.getName());
        }

        for (Period period : Period.values()) {
            PERIOD_LIST.add(period.getName());
        }

        for (RefreshInterval refreshInterval : RefreshInterval.values()) {
            REFRESH_INTERVAL_LIST.add(refreshInterval.getName());
        }
    }

}
