package by.training.factory;

import by.training.dao.IDashboardDAO;
import by.training.editor.hibernate.HibernateDashboardEditor;

public class DashboardFactory {

    public static IDashboardDAO getEditor() {
        return new HibernateDashboardEditor();
    }

}
