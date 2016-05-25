package by.training.constants;

public class JSPNameConstants {

    private static final String PATH = "WEB-INF/jsp/";
    private static final String EXT  = ".jsp";

    public static class Pages {

        private static final String LOCAL_PATH = "page/";
        private static final String FULL_PATH  = PATH + LOCAL_PATH;

        public static final String  CONFIG_JSP = FULL_PATH + "configPage" + EXT;
        public static final String  WIDGET_JSP = FULL_PATH + "widgetPage" + EXT;

    }

}
