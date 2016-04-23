package by.training.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import by.training.bean.ui.JWidgetPanel;
import by.training.editor.ConfigEditor;
import by.training.exception.ConfigEditorException;

public class ChartWidgetButtonListener implements ActionListener {

    private static List<JWidgetPanel> panels;

    private int                       active;

    public ChartWidgetButtonListener(final int active) {
        this.active = active;
    }

    public static void setPanels(final List<JWidgetPanel> panels) {
        ChartWidgetButtonListener.panels = panels;
    }

    public void setVisibleWindows() {
        for (int i = 1; i < panels.size(); i++) {
            panels.get(i).setVisible(false);
        }
        for (int i = 1; i < active; i++) {
            panels.get(i).setVisible(true);
        }
    }

    @Override
    public void actionPerformed(final ActionEvent event) {
        setVisibleWindows();
        ConfigEditor.config.getWidget().setActive(active);
        try {
            ConfigEditor.updateConfig();
        } catch (ConfigEditorException e) {
            e.printStackTrace();
        }
    }

}
