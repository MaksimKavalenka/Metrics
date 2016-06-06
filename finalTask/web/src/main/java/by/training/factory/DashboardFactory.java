package by.training.factory;

import by.training.dao.IDashboardDAO;
import by.training.editor.hibernate.HibernateDashboardEditor;

public abstract class DashboardFactory {

    public static IDashboardDAO getEditor() {
        return new HibernateDashboardEditor();
    }

}
