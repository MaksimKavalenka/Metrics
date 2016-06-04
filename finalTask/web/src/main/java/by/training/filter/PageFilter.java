package by.training.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import by.training.action.DataAction;
import by.training.constants.ActionConstants;
import by.training.constants.PropertyConstants;
import by.training.database.structure.DashboardColumns;
import by.training.database.structure.WidgetColumns;

@WebFilter(servletNames = "page")
public class PageFilter implements Filter {

    @Override
    public void init(final FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response,
            final FilterChain chain) throws IOException, ServletException {

        switch (checkAction(request)) {
            case MODIFY_DASHBOARD:
            case CHART:
                checkDashboardId(request);
                break;
            case MODIFY_WIDGET:
                checkWidgetId(request);
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
        String uri = ((HttpServletRequest) request).getRequestURI().toString();
        ActionConstants action = DataAction.getAction(uri);
        request.setAttribute(PropertyConstants.ACTION, action);
        return action;
    }

    private static void checkDashboardId(final ServletRequest request) {
        int id = Integer.valueOf(request.getParameter(DashboardColumns.ID.toString()));
        request.setAttribute(DashboardColumns.ID.toString(), id);
    }

    private static void checkWidgetId(final ServletRequest request) {
        int id = Integer.valueOf(request.getParameter(WidgetColumns.ID.toString()));
        request.setAttribute(WidgetColumns.ID.toString(), id);
    }

}
