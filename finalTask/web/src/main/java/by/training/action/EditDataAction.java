package by.training.action;

import javax.servlet.http.HttpServletRequest;

import by.training.constants.ActionConstants;
import by.training.constants.PropertyConstants;
import by.training.dao.IDashboardDAO;
import by.training.dao.IWidgetDAO;
import by.training.database.structure.DashboardColumns;
import by.training.database.structure.WidgetColumns;
import by.training.factory.DashboardFactory;
import by.training.factory.WidgetFactory;
import by.training.options.MetricType;
import by.training.options.Period;
import by.training.options.RefreshInterval;

public class EditDataAction {

    public static void edit(final HttpServletRequest request) {
        String value = request.getParameter(PropertyConstants.ACTION);
        ActionConstants action = DataAction.searchEnum(ActionConstants.class, value);

        switch (action) {
            case ADD_DASHBOARD:
                addDashboard(request);
                break;
            case ADD_WIDGET:
                addWidget(request);
                break;
            default:
                break;
        }
    }

    private static void addDashboard(final HttpServletRequest request) {
        try (IDashboardDAO dashboardDAO = DashboardFactory.getEditor()) {
            String name = request.getParameter(DashboardColumns.NAME.name());
            String description = request.getParameter(DashboardColumns.DESCRIPTION.name());
            // int count = Integer.parseInt(request.getParameter(PropertyConstants.WIDGET_COUNT));

            dashboardDAO.addDashboard(name, description, null);
        }
    }

    private static void addWidget(final HttpServletRequest request) {
        try (IWidgetDAO widgetDAO = WidgetFactory.getEditor()) {
            String name = request.getParameter(WidgetColumns.NAME.name());
            int metricType = Integer
                    .valueOf(request.getParameter(WidgetColumns.METRIC_TYPE.name()));
            int period = Integer.valueOf(request.getParameter(WidgetColumns.PERIOD.name()));
            int refreshInterval = Integer
                    .valueOf(request.getParameter(WidgetColumns.REFRESH_INTERVAL.name()));

            widgetDAO.addWidget(name, MetricType.values()[metricType - 1],
                    Period.values()[period - 1], RefreshInterval.values()[refreshInterval - 1]);
        }
    }

}
