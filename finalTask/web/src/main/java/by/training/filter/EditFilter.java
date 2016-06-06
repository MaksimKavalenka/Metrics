package by.training.filter;

import static by.training.constants.MessageConstants.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import by.training.bean.Widget;
import by.training.constants.ActionConstants;
import by.training.constants.PropertyConstants;
import by.training.dao.IWidgetDAO;
import by.training.database.structure.DashboardColumns;
import by.training.database.structure.DashboardWidgetColumns;
import by.training.database.structure.WidgetColumns;
import by.training.exception.IllegalDataException;
import by.training.factory.WidgetFactory;
import by.training.options.MetricType;
import by.training.options.Period;
import by.training.options.RefreshInterval;
import by.training.parser.DateFormatParser;
import by.training.server.RESTTransport;

@WebFilter(servletNames = "edit")
public class EditFilter implements Filter {

    @Override
    public void init(final FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response,
            final FilterChain chain) throws IOException, ServletException {

        switch (checkAction(request)) {
            case MODIFY_DASHBOARD:
                DashboardEditFilter.checkDashboardId(request);
            case ADD_DASHBOARD:
                DashboardEditFilter.checkDashboardName(request);
                DashboardEditFilter.checkDashboardDescription(request);
                DashboardEditFilter.checkDashboardWidgets(request);
                break;
            case DELETE_DASHBOARD:
                DashboardEditFilter.checkDashboardId(request);
                break;
            case MODIFY_WIDGET:
                WidgetEditFilter.checkWidgetId(request);
            case ADD_WIDGET:
                WidgetEditFilter.checkWidgetName(request);
                WidgetEditFilter.checkWidgetMetricType(request);
                WidgetEditFilter.checkWidgetRefreshInterval(request);
                WidgetEditFilter.checkWidgetPeriod(request);
                break;
            case DELETE_WIDGET:
                WidgetEditFilter.checkWidgetId(request);
                break;
            case CHART:
                sendChart(request, response);
                break;
            default:
                break;
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }

    private static ActionConstants checkAction(final ServletRequest request) {
        String value = request.getParameter(PropertyConstants.ACTION);
        ActionConstants action = ActionConstants.valueOf(value.toUpperCase());
        request.setAttribute(PropertyConstants.ACTION, action);
        return action;
    }

    private static void sendChart(final ServletRequest request, final ServletResponse response) {
        try (IWidgetDAO widgetDAO = WidgetFactory.getEditor()) {
            int id = Integer.valueOf(request.getParameter(WidgetColumns.ID.toString()));
            Widget widget = widgetDAO.getWidget(id);
            try (PrintWriter out = response.getWriter()) {
                if (widget.getPeriod() != Period.CUSTOM) {
                    out.print(RESTTransport.getList(widget.getMetricType(),
                            widget.getPeriod().getDate(), new Date(0)));
                } else {
                    out.print(RESTTransport.getList(widget.getMetricType(), widget.getFromDate(),
                            widget.getToDate()));
                }
                out.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static class DashboardEditFilter {

        private static void checkDashboardId(final ServletRequest request) {
            int id = Integer.valueOf(request.getParameter(DashboardColumns.ID.toString()));
            request.setAttribute(DashboardColumns.ID.toString(), id);
        }

        private static void checkDashboardName(final ServletRequest request) {
            String name = request.getParameter(DashboardColumns.NAME.toString());

            if (name.trim().isEmpty()) {
                request.setAttribute(PropertyConstants.ERROR, EMPTY_NAME_MESSAGE);
            } else {
                request.setAttribute(DashboardColumns.NAME.toString(), name);
            }
        }

        private static void checkDashboardDescription(final ServletRequest request) {
            String description = request.getParameter(DashboardColumns.DESCRIPTION.toString());
            request.setAttribute(DashboardColumns.DESCRIPTION.toString(), description);
        }

        private static void checkDashboardWidgets(final ServletRequest request) {
            int number = Integer.valueOf(request.getParameter(PropertyConstants.WIDGET_NUMBER));
            List<Integer> list = new ArrayList<>(number);

            try {
                for (int i = 1; i <= number; i++) {
                    int id = Integer.valueOf(
                            request.getParameter(DashboardWidgetColumns.ID_WIDGET.toString() + i));
                    list.add(id);
                }
                request.setAttribute(DashboardWidgetColumns.ID_WIDGET.toString(), list);
            } catch (NumberFormatException e) {
                request.setAttribute(PropertyConstants.ERROR, SPECIFIED_WIDGET_MESSAGE);
            }
        }

    }

    private static class WidgetEditFilter {

        private static void checkWidgetId(final ServletRequest request) {
            int id = Integer.valueOf(request.getParameter(WidgetColumns.ID.toString()));
            request.setAttribute(WidgetColumns.ID.toString(), id);
        }

        private static void checkWidgetName(final ServletRequest request) {
            String name = request.getParameter(WidgetColumns.NAME.toString());

            if (name.trim().isEmpty()) {
                request.setAttribute(PropertyConstants.ERROR, EMPTY_NAME_MESSAGE);
            } else {
                request.setAttribute(WidgetColumns.NAME.toString(), name);
            }
        }

        private static void checkWidgetMetricType(final ServletRequest request) {
            int metricType = Integer
                    .valueOf(request.getParameter(WidgetColumns.METRIC_TYPE.toString()));
            request.setAttribute(WidgetColumns.METRIC_TYPE.toString(),
                    MetricType.values()[metricType - 1]);
        }

        private static void checkWidgetRefreshInterval(final ServletRequest request) {
            int refreshInterval = Integer
                    .valueOf(request.getParameter(WidgetColumns.REFRESH_INTERVAL.toString()));
            request.setAttribute(WidgetColumns.REFRESH_INTERVAL.toString(),
                    RefreshInterval.values()[refreshInterval - 1]);
        }

        private static void checkWidgetPeriod(final ServletRequest request) {
            boolean isCustom = Boolean.valueOf(request.getParameter(Period.CUSTOM.toString()));
            request.setAttribute(Period.CUSTOM.toString(), isCustom);

            if (isCustom) {
                String start = request.getParameter(WidgetColumns.FROM_DATE.toString());
                String end = request.getParameter(WidgetColumns.TO_DATE.toString());

                try {
                    request.setAttribute(WidgetColumns.FROM_DATE.toString(),
                            DateFormatParser.stringToDate(start));
                    end = request.getParameter(WidgetColumns.TO_DATE.toString());
                    request.setAttribute(WidgetColumns.TO_DATE.toString(),
                            DateFormatParser.stringToDate(end));
                    request.setAttribute(WidgetColumns.PERIOD.toString(), Period.CUSTOM);

                    if (start.compareTo(end) >= 0) {
                        throw new IllegalDataException(INCORRECT_DATES_MESSAGE);
                    }
                } catch (IllegalDataException e) {
                    e.printStackTrace();
                    request.setAttribute(WidgetColumns.FROM_DATE.toString(), start);
                    request.setAttribute(WidgetColumns.TO_DATE.toString(), end);
                    request.setAttribute(PropertyConstants.ERROR, e.getMessage());
                }
            } else {
                int period = Integer.valueOf(request.getParameter(WidgetColumns.PERIOD.toString()));
                request.setAttribute(WidgetColumns.PERIOD.toString(), Period.values()[period - 1]);
            }
        }

    }

}
