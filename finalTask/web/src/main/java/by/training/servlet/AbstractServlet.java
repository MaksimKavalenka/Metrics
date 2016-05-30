package by.training.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class AbstractServlet extends HttpServlet {

    private static final long serialVersionUID = -6140221492057838359L;

    @Override
    public void doGet(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        performTask(request, response);
    }

    @Override
    public void doPost(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        performTask(request, response);
    }

    protected abstract void performTask(final HttpServletRequest request,
            final HttpServletResponse response) throws ServletException, IOException;

}
