package by.training.factory;

import by.training.dao.IWidgetDAO;
import by.training.editor.memory.MemoryWidgetEditor;

public class WidgetFactory {

    public static IWidgetDAO getEditor() {
        return new MemoryWidgetEditor();
    }

}
