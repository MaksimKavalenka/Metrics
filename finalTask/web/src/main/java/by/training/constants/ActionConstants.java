package by.training.constants;

public enum ActionConstants {

    SHOW_DASHBOARD(UriConstants.Pages.DASHBOARD_PAGE_URI, PathConstants.Pages.DASHBOARD_PAGE_PATH),
    ADD_DASHBOARD(UriConstants.Forms.DASHBOARD_FORM_URI, PathConstants.Forms.DASHBOARD_FORM_PATH),
    SHOW_WIDGET(UriConstants.Pages.WIDGET_PAGE_URI, PathConstants.Pages.WIDGET_PAGE_PATH),
    ADD_WIDGET(UriConstants.Forms.WIDGET_FORM_URI, PathConstants.Forms.WIDGET_FORM_PATH);

    private String uri;
    private String path;

    private ActionConstants(final String uri, final String path) {
        this.uri = uri;
        this.path = path;
    }

    public String getUri() {
        return uri;
    }

    public String getPath() {
        return path;
    }

}
