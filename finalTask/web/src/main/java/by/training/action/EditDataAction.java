package by.training.action;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.training.constants.ActionConstants;
import by.training.constants.PropertyConstants;
import by.training.dao.IDashboardDAO;
import by.training.dao.IDashboardWidgetDAO;
import by.training.dao.IWidgetDAO;
import by.training.database.structure.DashboardColumns;
import by.training.database.structure.DashboardWidgetColumns;
import by.training.database.structure.WidgetColumns;
import by.training.exception.IllegalDataException;
import by.training.factory.DashboardFactory;
import by.training.factory.DashboardWidgetFactory;
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
                DashboardEditData.addDashboard(request);
                DashboardWidgetEditData.addDashboardWidget(request);
                break;
            case DELETE_DASHBOARD:
                DashboardEditData.deleteDashboard(request);
                DashboardWidgetEditData.deleteDashboard(request);
                break;
            case ADD_WIDGET:
                WidgetEditData.addWidget(request);
                break;
            case DELETE_WIDGET:
                WidgetEditData.deleteWidget(request);
                DashboardWidgetEditData.deleteWidget(request);
                break;
            default:
                break;
        }
    }

    private static class DashboardEditData {

        private static void addDashboard(final HttpServletRequest request) {
            try (IDashboardDAO dashboardDAO = DashboardFactory.getEditor()) {
                String name = (String) request.getAttribute(DashboardColumns.NAME.toString());
                String description = (String) request
                        .getAttribute(DashboardColumns.DESCRIPTION.toString());
                int id = dashboardDAO.addDashboard(name, description);
                request.setAttribute(DashboardColumns.ID.toString(), id);
            }
        }

        private static void deleteDashboard(final HttpServletRequest request) {
            try (IDashboardDAO dashboardDAO = DashboardFactory.getEditor()) {
                int id = (int) request.getAttribute(DashboardColumns.ID.toString());
                dashboardDAO.deleteDashboard(id);
            }
        }

    }

    private static class WidgetEditData {

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

        private static void deleteWidget(final HttpServletRequest request) {
            try (IWidgetDAO widgetDAO = WidgetFactory.getEditor()) {
                int id = (int) request.getAttribute(WidgetColumns.ID.toString());
                widgetDAO.deleteWidget(id);
            }
        }

    }

    private static class DashboardWidgetEditData {

        private static void addDashboardWidget(final HttpServletRequest request) {
            try (IDashboardWidgetDAO dashboardWidgetDAO = DashboardWidgetFactory.getEditor()) {
                int idDashboard = (int) request.getAttribute(DashboardColumns.ID.toString());
                @SuppressWarnings("unchecked")
                List<Integer> list = (List<Integer>) request
                        .getAttribute(DashboardWidgetColumns.ID_WIDGET.toString());
                for (int i = 0; i < list.size(); i++) {
                    dashboardWidgetDAO.addDashboardWidget(idDashboard, list.get(i));
                }
            }
        }

        private static void deleteDashboard(final HttpServletRequest request) {
            try (IDashboardWidgetDAO dashboardWidgetDAO = DashboardWidgetFactory.getEditor()) {
                int idDashboard = (int) request.getAttribute(DashboardColumns.ID.toString());
                dashboardWidgetDAO.deleteDashboard(idDashboard);
            }
        }

        private static void deleteWidget(final HttpServletRequest request) {
            try (IDashboardWidgetDAO dashboardWidgetDAO = DashboardWidgetFactory.getEditor()) {
                int idWidget = (int) request.getAttribute(WidgetColumns.ID.toString());
                dashboardWidgetDAO.deleteWidget(idWidget);
            }
        }

    }

}
