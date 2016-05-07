package by.training.constants;

import by.training.bean.element.ConfigElement;
import by.training.bean.element.IconElement;
import by.training.bean.element.OptionsElement;
import by.training.bean.element.MetricTypeElement;
import by.training.bean.element.PeriodElement;
import by.training.bean.element.TransportElement;
import by.training.bean.element.WidgetElement;
import by.training.bean.options.MetricType;
import by.training.bean.options.Period;
import by.training.bean.options.RefreshInterval;
import by.training.bean.options.Transport;

public class ConfigDefaultConstants {

    public static final String          ADDRESS          = "http://localhost:8080/metrics";

    public static final int             MIN_ACTIVE       = 1;
    public static final int             AVG_ACTIVE       = 2;
    public static final int             MAX_ACTIVE       = 4;

    public static final String          TITLE            = "My icon";
    public static final MetricType      METRIC_TYPE      = MetricType.AVAILABLE_PROCESSORS;
    public static final Transport       TRANSPORT        = Transport.REST;
    public static final Period          PERIOD           = Period.LAST_MINUTES_15;
    public static final RefreshInterval REFRESH_INTERVAL = RefreshInterval.SECOND;

    public static final OptionsElement  OPTIONS_1        = new OptionsElement(
            MetricType.FREE_PHYSICAL_MEMORY_SIZE.getTitle(),
            new MetricTypeElement(MetricType.FREE_PHYSICAL_MEMORY_SIZE, true),
            new TransportElement(TRANSPORT, ADDRESS), new PeriodElement(PERIOD, null, null),
            REFRESH_INTERVAL);

    public static final OptionsElement  OPTIONS_2        = new OptionsElement(
            MetricType.HEAP_USED.getTitle(), new MetricTypeElement(MetricType.HEAP_USED, true),
            new TransportElement(TRANSPORT, ADDRESS), new PeriodElement(PERIOD, null, null),
            REFRESH_INTERVAL);

    public static final OptionsElement  OPTIONS_3        = new OptionsElement(
            MetricType.DAEMON_THREAD_COUNT.getTitle(),
            new MetricTypeElement(MetricType.DAEMON_THREAD_COUNT, true),
            new TransportElement(TRANSPORT, ADDRESS), new PeriodElement(PERIOD, null, null),
            REFRESH_INTERVAL);

    public static final OptionsElement  OPTIONS_4        = new OptionsElement(
            MetricType.UPTIME.getTitle(), new MetricTypeElement(MetricType.UPTIME, true),
            new TransportElement(TRANSPORT, ADDRESS), new PeriodElement(PERIOD, null, null),
            REFRESH_INTERVAL);

    public static final WidgetElement   WIDGET           = new WidgetElement(MAX_ACTIVE, 0, 1, 2,
            3);

    public static final IconElement     ICON             = new IconElement(OPTIONS_1, OPTIONS_2,
            OPTIONS_3, OPTIONS_4);

    public static final ConfigElement   CONFIG           = new ConfigElement(WIDGET, ICON);

    public static OptionsElement createDefaultMetricElement() {
        return new OptionsElement(TITLE, new MetricTypeElement(METRIC_TYPE, true),
                new TransportElement(TRANSPORT, ADDRESS), new PeriodElement(PERIOD, null, null),
                REFRESH_INTERVAL);
    }

}
