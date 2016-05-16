package by.training.editor;

import static by.training.constants.AppDefaultConstants.DIMENSION;
import static by.training.constants.ConfigDefaultConstants.MAX_ACTIVE;

import static by.training.exception.JPanelEditorException.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import by.training.bean.element.OptionsElement;
import by.training.constants.ConfigDefaultConstants;
import by.training.dragndrop.WidgetDropTargetAdapter;
import by.training.exception.ConfigEditorException;
import by.training.exception.JIconPanelException;
import by.training.exception.JPanelEditorException;
import by.training.exception.JWidgetPanelException;
import by.training.listener.ChartWidgetButtonListener;
import by.training.ui.JIconPanel;
import by.training.ui.JWidgetPanel;
import by.training.window.OptionWindow;

public class JPanelEditor {

    public static void generatePanels(final JPanel panelIcons, final JPanel parent)
            throws JPanelEditorException {
        JIconPanelEditor.generatePanels(panelIcons, parent);
        JWidgetPanelEditor.generatePanels();
    }

    public static class JIconPanelEditor {

        private static List<OptionsElement> options        = ConfigEditor.config.getIcon()
                .getOptions();
        private static JPanel               panelIcons;
        private static List<JIconPanel>     listIconPanels = new LinkedList<>();

        public static JIconPanel addPanel(final OptionsElement metric)
                throws JPanelEditorException {
            JIconPanel iconPanel = null;

            try {
                iconPanel = new JIconPanel(metric);
                listIconPanels.add(iconPanel);
                panelIcons.add(iconPanel);
            } catch (JIconPanelException e) {
                throw new JPanelEditorException(CREATE_PANEL_ERROR);
            }

            return iconPanel;
        }

        public static void removePanel(final JIconPanel iconPanel) throws ConfigEditorException {
            int number = iconPanel.getIndex();
            iconPanel.deactivate();
            listIconPanels.remove(number);
            options.remove(number);
            panelIcons.remove(iconPanel);

            for (int i = number; i < listIconPanels.size(); i++) {
                JIconPanel iconPanel1 = listIconPanels.get(i);
                iconPanel1.reduceIndex();
            }

            for (JWidgetPanel widgetPanel : JWidgetPanelEditor.listWidgetPanels) {
                widgetPanel.getDependency().setIndex();
            }

            ConfigEditor.updateConfig();
        }

        private static void generatePanels(final JPanel panelIcons, final JPanel parent)
                throws JPanelEditorException {
            JPanelEditor.JIconPanelEditor.panelIcons = panelIcons;

            for (OptionsElement option : options) {
                addPanel(option);
            }

            JButton buttonAddIcon = new JButton("Add icon");
            buttonAddIcon.setPreferredSize(DIMENSION);
            parent.add(buttonAddIcon);
            buttonAddIcon.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(final ActionEvent event) {
                    OptionsElement metric = ConfigDefaultConstants.createDefaultMetricElement();
                    options.add(metric);

                    try {
                        JIconPanel iconPanel = addPanel(metric);
                        OptionWindow.createDialog(iconPanel);
                        if (!OptionWindow.isSave()) {
                            try {
                                removePanel(iconPanel);
                            } catch (ConfigEditorException e) {
                                e.printStackTrace();
                            }
                        }
                    } catch (JPanelEditorException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

    }

    public static class JWidgetPanelEditor {

        private static List<JWidgetPanel> listWidgetPanels = new LinkedList<>();

        public static void addPanel(final JWidgetPanel widgetPanel) {
            listWidgetPanels.add(widgetPanel);
        }

        private static void generatePanels() throws JPanelEditorException {
            ChartWidgetButtonListener.setPanels(listWidgetPanels);

            for (int i = 0; i < MAX_ACTIVE; i++) {
                int number = ConfigEditor.config.getWidget().getIconOptions().get(i);
                JIconPanel iconPanel = JIconPanelEditor.listIconPanels.get(number);
                JWidgetPanel widgetPanel = listWidgetPanels.get(i);
                new WidgetDropTargetAdapter(widgetPanel);
                try {
                    widgetPanel.getDependency().setIconPanel(iconPanel);
                } catch (JWidgetPanelException e) {
                    throw new JPanelEditorException(SET_PANEL_ERROR);
                }
            }
        }

    }

}
