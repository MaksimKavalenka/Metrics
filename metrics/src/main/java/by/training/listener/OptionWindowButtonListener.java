package by.training.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import by.training.bean.ui.JIconPanel;
import by.training.window.OptionWindow;

public class OptionWindowButtonListener implements ActionListener {

    private JIconPanel iconPanel;

    public OptionWindowButtonListener(final JIconPanel iconPanel) {
        this.iconPanel = iconPanel;
    }

    public void setIconPanel(final JIconPanel iconPanel) {
        this.iconPanel = iconPanel;
    }

    @Override
    public void actionPerformed(final ActionEvent event) {
        OptionWindow.createDialog(iconPanel);
    }

}
