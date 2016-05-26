package by.training.memory;

import java.util.LinkedList;
import java.util.List;

import by.training.bean.Dashboard;
import by.training.bean.Widget;

public class Memory {

    private static List<Dashboard> dashboards      = new LinkedList<>();
    private static List<Widget>    widgets         = new LinkedList<>();

    private static int             dashboardLastId = 1;
    private static int             widgetLastId    = 1;

    static {
        dashboards.add(new Dashboard(1, "hello", "yo!", null));
        dashboards.add(new Dashboard(2, "hey", "yo!", null));
    }

    public static List<Dashboard> getDashboards() {
        return dashboards;
    }

    public static void setConfigs(final List<Dashboard> dashboards) {
        Memory.dashboards = dashboards;
    }

    public static List<Widget> getWidgets() {
        return widgets;
    }

    public static void setWidgets(final List<Widget> widgets) {
        Memory.widgets = widgets;
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
