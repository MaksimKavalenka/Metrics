package by.training.editor;

import static by.training.constants.AppDefaultConstants.DIMENSION;
import static by.training.constants.ConfigDefaultConstants.MAX_ACTIVE;

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
import by.training.listener.ChartWidgetButtonListener;
import by.training.ui.JIconPanel;
import by.training.ui.JWidgetPanel;
import by.training.window.OptionWindow;

public class JPanelEditor {

    public static void generatePanels(final JPanel panelIcons, final JPanel parent)
            throws ConfigEditorException {
        JIconPanelEditor.generatePanels(panelIcons, parent);
        JWidgetPanelEditor.generatePanels();
    }

    public static class JIconPanelEditor {

        private static List<OptionsElement> options        = ConfigEditor.config.getIcon()
                .getOptions();
        private static JPanel               panelIcons;
        private static List<JIconPanel>     listIconPanels = new LinkedList<>();

        public static JIconPanel addPanel(final OptionsElement metric) {
            JIconPanel iconPanel = new JIconPanel(metric);
            listIconPanels.add(iconPanel);
            panelIcons.add(iconPanel);
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

        private static void generatePanels(final JPanel panelIcons, final JPanel parent) {
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

                    JIconPanel iconPanel = addPanel(metric);
                    OptionWindow.createDialog(iconPanel);
                    if (!OptionWindow.isSave()) {
                        try {
                            removePanel(iconPanel);
                        } catch (ConfigEditorException e) {
                            e.printStackTrace();
                        }
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

        private static void generatePanels() throws ConfigEditorException {
            ChartWidgetButtonListener.setPanels(listWidgetPanels);

            for (int i = 0; i < MAX_ACTIVE; i++) {
                int number = ConfigEditor.config.getWidget().getIconOptions().get(i);
                JIconPanel iconPanel = JIconPanelEditor.listIconPanels.get(number);
                JWidgetPanel widgetPanel = listWidgetPanels.get(i);
                new WidgetDropTargetAdapter(widgetPanel);
                widgetPanel.getDependency().setIconPanel(iconPanel);
            }
        }

    }

}
