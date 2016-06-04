package by.training.factory;

import by.training.dao.IDashboardDAO;
import by.training.editor.memory.MemoryDashboardEditor;

public class DashboardFactory {

    public static IDashboardDAO getEditor() {
        return new MemoryDashboardEditor();
    }

}
