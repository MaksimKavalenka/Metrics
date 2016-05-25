package by.training.factory;

import by.training.dao.IConfigDAO;
import by.training.editor.memory.MemoryConfigEditor;

public class ConfigFactory {

    public static IConfigDAO getEditor() {
        return new MemoryConfigEditor();
    }

}
