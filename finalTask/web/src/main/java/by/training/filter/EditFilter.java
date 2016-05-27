package by.training.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import by.training.action.DataAction;
import by.training.constants.ActionConstants;
import by.training.constants.MessageConstants;
import by.training.constants.PropertyConstants;
import by.training.database.structure.WidgetColumns;

@WebFilter(servletNames = "edit")
public class EditFilter implements Filter {

    @Override
    public void init(final FilterConfig fConfig) throws ServletException {
    }

    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response,
            final FilterChain chain) throws IOException, ServletException {

        String value = request.getParameter(PropertyConstants.ACTION);
        ActionConstants action = DataAction.searchEnum(ActionConstants.class, value);
        request.setAttribute(PropertyConstants.ACTION, action);

        if (request.getParameter(WidgetColumns.NAME.toString()).trim().isEmpty()) {
            request.setAttribute(PropertyConstants.ERROR, MessageConstants.EMPTY_NAME_MESSAGE);
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }

}
