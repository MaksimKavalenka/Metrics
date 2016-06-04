package by.training.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.training.action.LoadDataAction;

@WebServlet(urlPatterns = "/init", loadOnStartup = 1)
public class InitServlet extends AbstractServlet {

    private static final long serialVersionUID = -8299906747769789139L;

    @Override
    protected void performTask(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher(LoadDataAction.init(request)).forward(request, response);
    }

}
