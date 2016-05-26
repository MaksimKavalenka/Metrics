package by.training.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.training.bean.Dashboard;
import by.training.bean.Widget;
import by.training.constants.ActionConstants;
import by.training.constants.AppDefaultConstants;
import by.training.constants.JSPNameConstants;
import by.training.constants.PropertyConstants;
import by.training.dao.IDashboardDAO;
import by.training.dao.IWidgetDAO;
import by.training.database.structure.DatabaseTables;
import by.training.factory.DashboardFactory;
import by.training.factory.WidgetFactory;

public class LoadDataAction {

    public static String init(final HttpServletRequest request) {
        loadDashboard(request);
        return JSPNameConstants.Pages.DASHBOARD_PAGE_JSP;
    }

    public static void load(final HttpServletRequest request) {
        String value = request.getParameter(PropertyConstants.ACTION);
        ActionConstants action = DataAction.searchEnum(ActionConstants.class, value);

        switch (action) {
            case SHOW_DASHBOARD:
                loadDashboard(request);
                break;
            case SHOW_WIDGET:
            case ADD_DASHBOARD:
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
        request.setAttribute(PropertyConstants.METRIC_TYPE_LIST,
                AppDefaultConstants.METRIC_TYPE_LIST);
        request.setAttribute(PropertyConstants.PERIOD_LIST, AppDefaultConstants.PERIOD_LIST);
        request.setAttribute(PropertyConstants.REFRESH_INTERVAL_LIST,
                AppDefaultConstants.REFRESH_INTERVAL_LIST);
    }

    private static void loadDashboard(final HttpServletRequest request) {
        try (IDashboardDAO dashboardDAO = DashboardFactory.getEditor()) {
            final List<Dashboard> dashboards = dashboardDAO.getAll();
            request.setAttribute(DatabaseTables.DASHBOARD.toString(), dashboards);
        }
    }

    private static void loadWidget(final HttpServletRequest request) {
        try (IWidgetDAO widgetDAO = WidgetFactory.getEditor()) {
            final List<Widget> widgets = widgetDAO.getAll();
            request.setAttribute(DatabaseTables.WIDGET.toString(), widgets);
        }
    }

}
