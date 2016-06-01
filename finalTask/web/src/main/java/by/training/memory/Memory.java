package by.training.memory;

import java.util.LinkedList;
import java.util.List;

import by.training.bean.Dashboard;
import by.training.bean.DashboardWidget;
import by.training.bean.Widget;
import by.training.options.MetricType;
import by.training.options.Period;
import by.training.options.RefreshInterval;

public class Memory {

    private static List<Dashboard>       dashboards       = new LinkedList<>();
    private static List<Widget>          widgets          = new LinkedList<>();
    private static List<DashboardWidget> dashboardWidgets = new LinkedList<>();

    private static int                   dashboardLastId  = 1;
    private static int                   widgetLastId     = 1;

    static {
        widgets.add(new Widget(1, "Hello", MetricType.AVAILABLE_PROCESSORS, RefreshInterval.SECOND,
                Period.LAST_MINUTES_15, null, null));
        dashboards.add(new Dashboard(1, "Hey", "Yo"));
        dashboardWidgets.add(new DashboardWidget(1, 1));
        incDashboardLastId();
        incWidgetLastId();
    }

    public static List<Dashboard> getDashboards() {
        return dashboards;
    }

    public static void setDashboards(final List<Dashboard> dashboards) {
        Memory.dashboards = dashboards;
    }

    public static List<Widget> getWidgets() {
        return widgets;
    }

    public static void setWidgets(final List<Widget> widgets) {
        Memory.widgets = widgets;
    }

    public static List<DashboardWidget> getDashboardWidgets() {
        return dashboardWidgets;
    }

    public static void setDashboardWidget(final List<DashboardWidget> dashboardWidgets) {
        Memory.dashboardWidgets = dashboardWidgets;
    }

    public static int getDashboardLastId() {
        return dashboardLastId;
    }

    public static void incDashboardLastId() {
        ++dashboardLastId;
    }

    public static int getWidgetLastId() {
        return widgetLastId;
    }

    public static void incWidgetLastId() {
        ++widgetLastId;
    }

}
