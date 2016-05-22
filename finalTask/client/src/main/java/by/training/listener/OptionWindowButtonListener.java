package by.training.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import by.training.window.OptionsWindow;

public class OptionWindowButtonListener implements ActionListener {

    private OptionListener listener;

    public OptionWindowButtonListener(final OptionListener listener) {
        this.listener = listener;
    }

    public void setIconPanel(final OptionListener listener) {
        this.listener = listener;
    }

    @Override
    public void actionPerformed(final ActionEvent event) {
    	OptionsWindow.createDialog(listener);
    }

}
