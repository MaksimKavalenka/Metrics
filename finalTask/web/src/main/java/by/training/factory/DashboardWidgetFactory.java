package by.training.factory;

import by.training.dao.IDashboardWidgetDAO;
import by.training.editor.memory.MemoryDashboardWidgetEditor;

public class DashboardWidgetFactory {

    public static IDashboardWidgetDAO getEditor() {
        return new MemoryDashboardWidgetEditor();
    }

}
