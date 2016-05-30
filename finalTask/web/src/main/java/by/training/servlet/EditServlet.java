package by.training.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.training.action.DataAction;
import by.training.action.EditDataAction;
import by.training.action.LoadDataAction;
import by.training.exception.IllegalDataException;

@WebServlet(name = "edit", urlPatterns = "/edit")
public class EditServlet extends AbstractServlet {

    private static final long serialVersionUID = -6953306113192816923L;

    @Override
    public void doGet(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher(DataAction.getPath(request)).forward(request, response);
    }

    @Override
    protected void performTask(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        try {
            EditDataAction.edit(request);
            response.sendRedirect(DataAction.getLocation(request));
        } catch (IllegalDataException e) {
            LoadDataAction.load(request);
            request.getRequestDispatcher(DataAction.getPath(request)).forward(request, response);
        }
    }

}
