package by.training.constants;

public abstract class PathConstants {

    private static final String PATH = "/WEB-INF/jsp";
    private static final String EXT  = ".jsp";

    public static class Pages {

        private static final String LOCAL_PATH          = "/page";
        private static final String FULL_PATH           = PATH + LOCAL_PATH;

        public static final String  DASHBOARD_PAGE_PATH = FULL_PATH + "/dashboardPage" + EXT;
        public static final String  WIDGET_PAGE_PATH    = FULL_PATH + "/widgetPage" + EXT;
        public static final String  CHART_PAGE_PATH     = FULL_PATH + "/chartPage" + EXT;

    }

    public static class Forms {

        private static final String LOCAL_PATH          = "/form";
        private static final String FULL_PATH           = PATH + LOCAL_PATH;

        public static final String  DASHBOARD_FORM_PATH = FULL_PATH + "/dashboardForm" + EXT;
        public static final String  WIDGET_FORM_PATH    = FULL_PATH + "/widgetForm" + EXT;

    }

}
