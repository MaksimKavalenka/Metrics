package by.training.constants;

import by.training.bean.element.ConfigElement;
import by.training.bean.element.IconElement;
import by.training.bean.element.MetricElement;
import by.training.bean.element.WidgetElement;
import by.training.bean.metric.Period;
import by.training.bean.metric.RefreshInterval;
import by.training.bean.metric.Transport;
import by.training.bean.metric.TypeMetric;

public class ConfigDefaultConstants {

    public static final int             MIN_ACTIVE       = 1;
    public static final int             AVG_ACTIVE       = 2;
    public static final int             MAX_ACTIVE       = 4;

    public static final String          TITLE            = "My icon";
    public static final TypeMetric      TYPE_METRIC      = TypeMetric.AVAILABLE_PROCESSORS;
    public static final Transport       TRANSPORT        = Transport.OPERATING_SYSTEM;
    public static final Period          PERIOD           = Period.REAL_TIME;
    public static final RefreshInterval REFRESH_INTERVAL = RefreshInterval.SECOND;

    public static final MetricElement   METRIC_1         = new MetricElement(
            TypeMetric.SYSTEM_CPU_LOAD.getTitle(), TypeMetric.SYSTEM_CPU_LOAD, TRANSPORT, PERIOD,
            REFRESH_INTERVAL);

    public static final MetricElement   METRIC_2         = new MetricElement(
            TypeMetric.BUSY_PHYSICAL_MEMORY_SIZE.getTitle(), TypeMetric.BUSY_PHYSICAL_MEMORY_SIZE,
            TRANSPORT, PERIOD, REFRESH_INTERVAL);

    public static final MetricElement   METRIC_3         = new MetricElement(
            TypeMetric.PROCESS_CPU_LOAD.getTitle(), TypeMetric.PROCESS_CPU_LOAD, TRANSPORT, PERIOD,
            REFRESH_INTERVAL);

    public static final MetricElement   METRIC_4         = new MetricElement(
            TypeMetric.PROCESS_CPU_TIME.getTitle(), TypeMetric.PROCESS_CPU_TIME, TRANSPORT, PERIOD,
            REFRESH_INTERVAL);

    public static final WidgetElement   WIDGET           = new WidgetElement(MAX_ACTIVE, 0, 1, 2,
            3);

    public static final IconElement     ICON             = new IconElement(METRIC_1, METRIC_2,
            METRIC_3, METRIC_4);

    public static final ConfigElement   CONFIG           = new ConfigElement(WIDGET, ICON);

    public static MetricElement createDefaultMetricElement() {
        return new MetricElement(TITLE, TYPE_METRIC, TRANSPORT, PERIOD, REFRESH_INTERVAL);
    }

}
