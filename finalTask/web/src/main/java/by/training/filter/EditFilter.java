package by.training.filter;

import java.io.IOException;
import java.text.ParseException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import by.training.constants.ActionConstants;
import by.training.constants.MessageConstants;
import by.training.constants.PropertyConstants;
import by.training.database.structure.DashboardColumns;
import by.training.database.structure.WidgetColumns;
import by.training.options.MetricType;
import by.training.options.Period;
import by.training.options.RefreshInterval;
import by.training.parser.DateFormatParser;

@WebFilter(servletNames = "edit")
public class EditFilter implements Filter {

    @Override
    public void init(final FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response,
            final FilterChain chain) throws IOException, ServletException {

        switch (checkAction(request)) {
            case ADD_DASHBOARD:
                DashboardEdit.checkName(request);
                DashboardEdit.checkDescription(request);
                break;
            case ADD_WIDGET:
                WidgetEdit.checkName(request);
                WidgetEdit.checkMetricType(request);
                WidgetEdit.checkRefreshInterval(request);
                WidgetEdit.checkPeriod(request);
                break;
            default:
                break;
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }

    private ActionConstants checkAction(final ServletRequest request) {
        String value = request.getParameter(PropertyConstants.ACTION);
        ActionConstants action = ActionConstants.valueOf(value.toUpperCase());
        request.setAttribute(PropertyConstants.ACTION, action);
        return action;
    }

    private static class DashboardEdit {

        private static void checkName(final ServletRequest request) {
            String name = request.getParameter(DashboardColumns.NAME.toString());

            if (name.trim().isEmpty()) {
                request.setAttribute(PropertyConstants.ERROR, MessageConstants.EMPTY_NAME_MESSAGE);
            } else {
                request.setAttribute(DashboardColumns.NAME.toString(), name);
            }
        }

        private static void checkDescription(final ServletRequest request) {
            String description = request.getParameter(DashboardColumns.DESCRIPTION.toString());
            request.setAttribute(DashboardColumns.DESCRIPTION.toString(), description);
        }

    }

    private static class WidgetEdit {

        private static void checkName(final ServletRequest request) {
            String name = request.getParameter(WidgetColumns.NAME.toString());

            if (name.trim().isEmpty()) {
                request.setAttribute(PropertyConstants.ERROR, MessageConstants.EMPTY_NAME_MESSAGE);
            } else {
                request.setAttribute(WidgetColumns.NAME.toString(), name);
            }
        }

        private static void checkMetricType(final ServletRequest request) {
            int metricType = Integer
                    .valueOf(request.getParameter(WidgetColumns.METRIC_TYPE.toString()));
            request.setAttribute(WidgetColumns.METRIC_TYPE.toString(),
                    MetricType.values()[metricType - 1]);
        }

        private static void checkRefreshInterval(final ServletRequest request) {
            int refreshInterval = Integer
                    .valueOf(request.getParameter(WidgetColumns.REFRESH_INTERVAL.toString()));
            request.setAttribute(WidgetColumns.REFRESH_INTERVAL.toString(),
                    RefreshInterval.values()[refreshInterval - 1]);
        }

        private static void checkPeriod(final ServletRequest request) {
            boolean isCustom = Boolean.valueOf(request.getParameter(Period.CUSTOM.toString()));
            request.setAttribute(Period.CUSTOM.toString(), isCustom);

            if (isCustom) {
                try {
                    String from = request.getParameter(WidgetColumns.FROM.toString());
                    request.setAttribute(WidgetColumns.FROM.toString(),
                            DateFormatParser.stringToDate(from));

                    String to = request.getParameter(WidgetColumns.TO.toString());
                    request.setAttribute(WidgetColumns.TO.toString(),
                            DateFormatParser.stringToDate(to));

                    request.setAttribute(WidgetColumns.PERIOD.toString(), Period.CUSTOM);
                } catch (ParseException e) {
                    request.setAttribute(PropertyConstants.ERROR,
                            MessageConstants.SPECIFIED_DATE_ERROR);
                }
            } else {
                int period = Integer.valueOf(request.getParameter(WidgetColumns.PERIOD.toString()));
                request.setAttribute(WidgetColumns.PERIOD.toString(), Period.values()[period - 1]);
            }
        }

    }

}
