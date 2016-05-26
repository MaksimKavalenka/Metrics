package by.training.action;

import javax.servlet.http.HttpServletRequest;

import by.training.constants.ActionConstants;
import by.training.constants.JSPNameConstants;
import by.training.constants.PropertyConstants;

public class DataAction {

    public static String getJsp(final HttpServletRequest request) {
        String value = request.getParameter(PropertyConstants.ACTION);
        ActionConstants action = searchEnum(ActionConstants.class, value);

        switch (action) {
            case SHOW_DASHBOARD:
                return JSPNameConstants.Pages.DASHBOARD_PAGE_JSP;
            case SHOW_WIDGET:
                return JSPNameConstants.Pages.WIDGET_PAGE_JSP;
            case ADD_DASHBOARD:
                return JSPNameConstants.Forms.DASHBOARD_FORM_JSP;
            case ADD_WIDGET:
                return JSPNameConstants.Forms.WIDGET_FORM_JSP;
            default:
                return JSPNameConstants.Pages.DASHBOARD_PAGE_JSP;
        }
    }

    public static <T extends Enum<?>> T searchEnum(final Class<T> enumeration, final String value) {
        for (T each : enumeration.getEnumConstants()) {
            if (each.name().compareToIgnoreCase(value) == 0) {
                return each;
            }
        }
        return null;
    }

}
