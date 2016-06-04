package by.training.action;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.training.bean.Dashboard;
import by.training.bean.Widget;
import by.training.constants.ActionConstants;
import by.training.constants.PathConstants;
import by.training.constants.PropertyConstants;
import by.training.dao.IDashboardDAO;
import by.training.dao.IDashboardWidgetDAO;
import by.training.dao.IWidgetDAO;
import by.training.database.structure.DashboardColumns;
import by.training.database.structure.DashboardWidgetColumns;
import by.training.database.structure.DatabaseTables;
import by.training.database.structure.WidgetColumns;
import by.training.factory.DashboardFactory;
import by.training.factory.DashboardWidgetFactory;
import by.training.factory.WidgetFactory;
import by.training.options.MetricType;
import by.training.options.Period;
import by.training.options.RefreshInterval;
import by.training.parser.DateFormatParser;

public class LoadDataAction {

    public static String init(final HttpServletRequest request) {
        getAllDashboards(request);
        return PathConstants.Pages.DASHBOARD_PAGE_PATH;
    }

    public static void loadAfterError(final HttpServletRequest request) {
        ActionConstants action = (ActionConstants) request.getAttribute(PropertyConstants.ACTION);

        switch (action) {
            case MODIFY_DASHBOARD:
            case ADD_DASHBOARD:
                getAllWidgets(request);
                break;
            case MODIFY_WIDGET:
            case ADD_WIDGET:
                getDefaultParameters(request);
                break;
            default:
                break;
        }
    }

    public static void load(final HttpServletRequest request, final HttpServletResponse response) {
        ActionConstants action = (ActionConstants) request.getAttribute(PropertyConstants.ACTION);

        switch (action) {
            case SHOW_DASHBOARD:
                getAllDashboards(request);
                break;
            case MODIFY_DASHBOARD:
                getDashboard(request);
                getDashboardWidgets(request);
            case ADD_DASHBOARD:
            case SHOW_WIDGET:
                getAllWidgets(request);
                break;
            case MODIFY_WIDGET:
                getWidget(request);
            case ADD_WIDGET:
                getDefaultParameters(request);
                break;
            case CHART:
                getWidgets(request, response);
                break;
            default:
                break;
        }
    }

    private static void getDefaultParameters(final HttpServletRequest request) {
        final List<Period> periodList = new LinkedList<>();
        periodList.add(Period.LAST_MINUTES_15);
        periodList.add(Period.LAST_MINUTES_30);
        periodList.add(Period.LAST_HOUR);
        request.setAttribute(PropertyConstants.METRIC_TYPE_LIST, MetricType.values());
        request.setAttribute(PropertyConstants.REFRESH_INTERVAL_LIST, RefreshInterval.values());
        request.setAttribute(PropertyConstants.PERIOD_LIST, periodList);
    }

    private static void getDashboard(final HttpServletRequest request) {
        try (IDashboardDAO dashboardDAO = DashboardFactory.getEditor()) {
            int id = (int) request.getAttribute(DashboardColumns.ID.toString());
            final Dashboard dashboard = dashboardDAO.getDashboard(id);
            request.setAttribute(DashboardColumns.NAME.toString(), dashboard.getName());
            request.setAttribute(DashboardColumns.DESCRIPTION.toString(),
                    dashboard.getDescription());
        }
    }

    private static void getAllDashboards(final HttpServletRequest request) {
        try (IDashboardDAO dashboardDAO = DashboardFactory.getEditor()) {
            final List<Dashboard> dashboards = dashboardDAO.getAllDashboards();
            request.setAttribute(DatabaseTables.DASHBOARD.toString(), dashboards);
        }
    }

    private static void getWidget(final HttpServletRequest request) {
        try (IWidgetDAO widgetDAO = WidgetFactory.getEditor()) {
            int id = (int) request.getAttribute(WidgetColumns.ID.toString());
            final Widget widget = widgetDAO.getWidget(id);
            request.setAttribute(WidgetColumns.NAME.toString(), widget.getName());
            request.setAttribute(WidgetColumns.METRIC_TYPE.toString(), widget.getMetricType());
            request.setAttribute(WidgetColumns.REFRESH_INTERVAL.toString(),
                    widget.getRefreshInterval());
            request.setAttribute(WidgetColumns.PERIOD.toString(), widget.getPeriod());

            if (widget.getPeriod() == Period.CUSTOM) {
                request.setAttribute(Period.CUSTOM.toString(), true);
                request.setAttribute(WidgetColumns.FROM.toString(),
                        DateFormatParser.dateToString(widget.getFrom()));
                request.setAttribute(WidgetColumns.TO.toString(),
                        DateFormatParser.dateToString(widget.getTo()));
            } else {
                request.setAttribute(Period.CUSTOM.toString(), false);
            }
        }
    }

    private static void getWidgets(final HttpServletRequest request,
            final HttpServletResponse response) {
        getDashboardWidgets(request);
        try (IWidgetDAO widgetDAO = WidgetFactory.getEditor()) {
            @SuppressWarnings("unchecked")
            List<Integer> widgetIds = (List<Integer>) request
                    .getAttribute(DashboardWidgetColumns.ID_WIDGET.toString());
            final List<Widget> widgets = new ArrayList<>(widgetIds.size());
            for (Integer id : widgetIds) {
                widgets.add(widgetDAO.getWidget(id));
            }
            request.setAttribute(DatabaseTables.WIDGET.toString(), widgets);
        }
    }

    private static void getAllWidgets(final HttpServletRequest request) {
        try (IWidgetDAO widgetDAO = WidgetFactory.getEditor()) {
            final List<Widget> widgets = widgetDAO.getAllWidgets();
            request.setAttribute(DatabaseTables.WIDGET.toString(), widgets);
        }
    }

    private static void getDashboardWidgets(final HttpServletRequest request) {
        try (IDashboardWidgetDAO dashboardWidgetDAO = DashboardWidgetFactory.getEditor()) {
            int idDashboard = (int) request.getAttribute(DashboardColumns.ID.toString());
            List<Integer> widgetIds = dashboardWidgetDAO.getWidgetIds(idDashboard);
            request.setAttribute(DashboardWidgetColumns.ID_WIDGET.toString(), widgetIds);
        }

    }

}
