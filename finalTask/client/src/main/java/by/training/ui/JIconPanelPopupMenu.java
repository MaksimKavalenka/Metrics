package by.training.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import by.training.editor.JPanelEditor;
import by.training.exception.ConfigEditorException;
import by.training.window.ReminderWindow;

public class JIconPanelPopupMenu extends JPopupMenu {

    private static final long serialVersionUID = -6229551194885294154L;

    private JMenuItem         itemDelete;

    public JIconPanelPopupMenu(final JIconPanel iconPanel) {
        itemDelete = new JMenuItem("Detele the icon");
        add(itemDelete);
        itemDelete.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent event) {
                if (iconPanel.isDependency()) {
                    ReminderWindow.createDialog(ReminderWindow.DELETE);
                } else {
                    try {
                        JPanelEditor.JIconPanelEditor.removePanel(iconPanel);
                    } catch (ConfigEditorException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

}
