package by.training.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.SwingUtilities;

import by.training.ui.JIconPanel;
import by.training.ui.JMousePopupMenu;
import by.training.window.OptionWindow;

public class OptionWindowMouseListener implements MouseListener {

    private JIconPanel iconPanel;

    public OptionWindowMouseListener(final JIconPanel iconPanel) {
        this.iconPanel = iconPanel;
    }

    @Override
    public void mouseClicked(final MouseEvent event) {
        if (SwingUtilities.isLeftMouseButton(event)) {
            OptionWindow.createDialog(iconPanel);
        } else if (SwingUtilities.isRightMouseButton(event)) {
            JMousePopupMenu menu = new JMousePopupMenu(iconPanel);
            menu.show(event.getComponent(), event.getX(), event.getY());
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
