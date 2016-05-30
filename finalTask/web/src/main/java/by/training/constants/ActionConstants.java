package by.training.constants;

public enum ActionConstants {

    SHOW_DASHBOARD(UriConstants.Pages.DASHBOARD_SHOW_PAGE_URI, PathConstants.Pages.DASHBOARD_PAGE_PATH, UriConstants.Pages.DASHBOARD_SHOW_PAGE_URI),
    ADD_DASHBOARD(UriConstants.Forms.DASHBOARD_ADD_FORM_URI, PathConstants.Forms.DASHBOARD_FORM_PATH, UriConstants.Forms.DASHBOARD_ADD_FORM_URI),
    MODIFY_DASHBOARD(UriConstants.Forms.DASHBOARD_MODIFY_FORM_URI, PathConstants.Forms.DASHBOARD_FORM_PATH, UriConstants.Pages.DASHBOARD_SHOW_PAGE_URI),
    DELETE_DASHBOARD(UriConstants.Pages.DASHBOARD_SHOW_PAGE_URI, PathConstants.Pages.DASHBOARD_PAGE_PATH, UriConstants.Pages.DASHBOARD_SHOW_PAGE_URI),
    SHOW_WIDGET(UriConstants.Pages.WIDGET_SHOW_PAGE_URI, PathConstants.Pages.WIDGET_PAGE_PATH, UriConstants.Pages.WIDGET_SHOW_PAGE_URI),
    ADD_WIDGET(UriConstants.Forms.WIDGET_ADD_FORM_URI, PathConstants.Forms.WIDGET_FORM_PATH, UriConstants.Forms.WIDGET_ADD_FORM_URI),
    MODIFY_WIDGET(UriConstants.Forms.WIDGET_MODIFY_FORM_URI, PathConstants.Forms.WIDGET_FORM_PATH, UriConstants.Pages.WIDGET_SHOW_PAGE_URI),
    DELETE_WIDGET(UriConstants.Pages.WIDGET_SHOW_PAGE_URI, PathConstants.Pages.WIDGET_PAGE_PATH, UriConstants.Pages.WIDGET_SHOW_PAGE_URI);

    private String uri;
    private String path;
    private String location;

    private ActionConstants(final String uri, final String path, final String location) {
        this.uri = uri;
        this.path = path;
        this.location = location;
    }

    public String getUri() {
        return uri;
    }

    public String getPath() {
        return path;
    }
    
    public String getLocation() {
        return location;
    }

}
