package by.training.factory;

import by.training.dao.IWidgetDAO;
import by.training.editor.hibernate.HibernateWidgetEditor;

public abstract class WidgetFactory {

    public static IWidgetDAO getEditor() {
        return new HibernateWidgetEditor();
    }

}
