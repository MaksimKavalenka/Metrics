package by.training.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import by.training.bean.Config;
import by.training.bean.Widget;
import by.training.constants.ActionConstants;
import by.training.constants.JSPNameConstants;
import by.training.constants.PropertyConstants;
import by.training.dao.IConfigDAO;
import by.training.dao.IWidgetDAO;
import by.training.database.structure.DatabaseTables;
import by.training.factory.ConfigFactory;
import by.training.factory.WidgetFactory;

public class LoadDataAction {

    public static String init(final HttpServletRequest request) {
        loadConfig(request);
        return JSPNameConstants.Pages.CONFIG_JSP;
    }

    public static String getJsp(final HttpServletRequest request) {
        String value = request.getParameter(PropertyConstants.ACTION).toUpperCase();
        ActionConstants action = ActionConstants.valueOf(value);

        switch (action) {
            case CONFIG:
                return JSPNameConstants.Pages.CONFIG_JSP;
            case WIDGET:
                return JSPNameConstants.Pages.WIDGET_JSP;
            default:
                return JSPNameConstants.Pages.CONFIG_JSP;
        }
    }

    public static void load(final HttpServletRequest request) {
        String value = request.getParameter(PropertyConstants.ACTION).toUpperCase();
        ActionConstants action = ActionConstants.valueOf(value);

        switch (action) {
            case CONFIG:
                loadConfig(request);
                break;
            case WIDGET:
                loadWidget(request);
                break;
        }
    }

    private static void loadConfig(final HttpServletRequest request) {
        try (IConfigDAO configDAO = ConfigFactory.getEditor()) {
            final List<Config> configs = configDAO.getAll();
            request.setAttribute(DatabaseTables.CONFIG.toString(), configs);
        }
    }

    private static void loadWidget(final HttpServletRequest request) {
        try (IWidgetDAO widgetDAO = WidgetFactory.getEditor()) {
            final List<Widget> widgets = widgetDAO.getAll();
            request.setAttribute(DatabaseTables.WIDGET.toString(), widgets);
        }
    }

}
