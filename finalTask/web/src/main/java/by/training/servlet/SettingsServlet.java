package by.training.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.training.action.LoadDataAction;

@WebServlet(urlPatterns = "/settings", loadOnStartup = 1)
public class SettingsServlet extends AbstractServlet {

    private static final long serialVersionUID = 5306106036240520824L;

    @Override
    protected void performTask(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        LoadDataAction.load(request);
        jump(LoadDataAction.getJsp(request), request, response);
    }

}
