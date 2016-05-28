package by.training.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.training.bean.Dashboard;
import by.training.bean.Widget;
import by.training.constants.ActionConstants;
import by.training.constants.AppDefaultConstants;
import by.training.constants.PathConstants;
import by.training.constants.PropertyConstants;
import by.training.dao.IDashboardDAO;
import by.training.dao.IWidgetDAO;
import by.training.database.structure.DatabaseTables;
import by.training.database.structure.WidgetColumns;
import by.training.factory.DashboardFactory;
import by.training.factory.WidgetFactory;

public class LoadDataAction {

    public static String init(final HttpServletRequest request) {
        loadDashboard(request);
        return PathConstants.Pages.DASHBOARD_PAGE_PATH;
    }

    public static void load(final HttpServletRequest request) {
        ActionConstants action = (ActionConstants) request.getAttribute(PropertyConstants.ACTION);

        switch (action) {
            case SHOW_DASHBOARD:
                loadDashboard(request);
                break;
            case ADD_DASHBOARD:
            case SHOW_WIDGET:
                loadWidget(request);
                break;
            case ADD_WIDGET:
                loadDefaultParameters(request);
                break;
            default:
                break;
        }
    }

    private static void loadDefaultParameters(final HttpServletRequest request) {
        request.setAttribute(WidgetColumns.METRIC_TYPE.toString(),
                AppDefaultConstants.METRIC_TYPE_LIST);
        request.setAttribute(WidgetColumns.PERIOD.toString(), AppDefaultConstants.PERIOD_LIST);
        request.setAttribute(WidgetColumns.REFRESH_INTERVAL.toString(),
                AppDefaultConstants.REFRESH_INTERVAL_LIST);
    }

    private static void loadDashboard(final HttpServletRequest request) {
        try (IDashboardDAO dashboardDAO = DashboardFactory.getEditor()) {
            final List<Dashboard> dashboards = dashboardDAO.getDashboards();
            request.setAttribute(DatabaseTables.DASHBOARD.toString(), dashboards);
        }
    }

    private static void loadWidget(final HttpServletRequest request) {
        try (IWidgetDAO widgetDAO = WidgetFactory.getEditor()) {
            final List<Widget> widgets = widgetDAO.getWidgets();
            request.setAttribute(DatabaseTables.WIDGET.toString(), widgets);
        }
    }

}
