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
import by.training.constants.PropertyConstants;

@WebFilter(servletNames = "page")
public class PageFilter implements Filter {

    @Override
    public void init(final FilterConfig fConfig) throws ServletException {
    }

    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response,
            final FilterChain chain) throws IOException, ServletException {

        String uri = ((HttpServletRequest) request).getRequestURI().toString();
        request.setAttribute(PropertyConstants.ACTION, DataAction.getAction(uri));

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }

}
