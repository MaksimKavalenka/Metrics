package by.training.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.SwingUtilities;

import by.training.ui.JIconPanel;
import by.training.ui.JIconPanelPopupMenu;
import by.training.window.OptionsWindow;

public class OptionWindowMouseListener implements MouseListener {

    private OptionListener listener;

    public OptionWindowMouseListener(final OptionListener listener) {
        this.listener = listener;
    }

    @Override
    public void mouseClicked(final MouseEvent event) {
        if (SwingUtilities.isLeftMouseButton(event)) {
            OptionsWindow.createDialog(listener);
        } else if (SwingUtilities.isRightMouseButton(event)) {
            if (JIconPanel.class.isInstance(listener)) {
                JIconPanelPopupMenu menu = new JIconPanelPopupMenu((JIconPanel) listener);
                menu.show(event.getComponent(), event.getX(), event.getY());
            }
        }
    }

    @Override
    public void mouseEntered(final MouseEvent event) {
        return;
    }

    @Override
    public void mouseExited(final MouseEvent event) {
        return;
    }

    @Override
    public void mousePressed(final MouseEvent event) {
        return;
    }

    @Override
    public void mouseReleased(final MouseEvent event) {
        return;
    }

}
