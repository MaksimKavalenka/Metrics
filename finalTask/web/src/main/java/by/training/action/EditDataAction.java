package by.training.action;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import by.training.constants.ActionConstants;
import by.training.constants.PropertyConstants;
import by.training.dao.IDashboardDAO;
import by.training.dao.IWidgetDAO;
import by.training.database.structure.DashboardColumns;
import by.training.database.structure.WidgetColumns;
import by.training.exception.IllegalDataException;
import by.training.factory.DashboardFactory;
import by.training.factory.WidgetFactory;
import by.training.options.MetricType;
import by.training.options.Period;
import by.training.options.RefreshInterval;

public class EditDataAction {

    public static void edit(final HttpServletRequest request) throws IllegalDataException {
        DataAction.checkError(request);
        ActionConstants action = (ActionConstants) request.getAttribute(PropertyConstants.ACTION);

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
            String name = (String) request.getAttribute(DashboardColumns.NAME.toString());
            String description = (String) request
                    .getAttribute(DashboardColumns.DESCRIPTION.toString());
            // int count = Integer.parseInt(request.getParameter(PropertyConstants.WIDGET_COUNT));

            dashboardDAO.addDashboard(name, description, null);
        }
    }

    private static void addWidget(final HttpServletRequest request) {
        try (IWidgetDAO widgetDAO = WidgetFactory.getEditor()) {
            String name = (String) request.getAttribute(WidgetColumns.NAME.toString());
            MetricType metricType = (MetricType) request
                    .getAttribute(WidgetColumns.METRIC_TYPE.toString());
            RefreshInterval refreshInterval = (RefreshInterval) request
                    .getAttribute(WidgetColumns.REFRESH_INTERVAL.toString());
            Period period = (Period) request.getAttribute(WidgetColumns.PERIOD.toString());

            boolean isCustom = (boolean) request.getAttribute(Period.CUSTOM.toString());
            if (!isCustom) {
                widgetDAO.addWidget(name, metricType, refreshInterval, period, null, null);
            } else {
                Date from = (Date) request.getAttribute(WidgetColumns.FROM.toString());
                Date to = (Date) request.getAttribute(WidgetColumns.TO.toString());
                widgetDAO.addWidget(name, metricType, refreshInterval, period, from, to);
            }
        }
    }

}
