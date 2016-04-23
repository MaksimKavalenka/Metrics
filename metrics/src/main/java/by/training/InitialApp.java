package by.training;

import by.training.editor.ConfigEditor;
import by.training.exception.ConfigEditorException;
import by.training.window.ChartWindow;

public class InitialApp {

    public static void main(final String[] args) throws ConfigEditorException {
        ConfigEditor.init();
        ChartWindow.startApp();
    }

}
