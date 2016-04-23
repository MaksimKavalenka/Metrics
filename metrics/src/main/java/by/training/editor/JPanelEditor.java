package by.training.editor;

import static by.training.constants.ConfigDefaultConstants.MAX_ACTIVE;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragSource;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

import by.training.bean.element.MetricElement;
import by.training.bean.ui.JIconPanel;
import by.training.bean.ui.JWidgetPanel;
import by.training.constants.ConfigDefaultConstants;
import by.training.dragndrop.IconDragGestureListener;
import by.training.dragndrop.WidgetDropTargetAdapter;
import by.training.exception.ConfigEditorException;
import by.training.listener.ChartWidgetButtonListener;
import by.training.listener.OptionWindowMouseListener;
import by.training.window.OptionWindow;

public class JPanelEditor {

    private static final Dimension     DIMENSION        = new Dimension(100, 53);

    private static List<MetricElement> metrics          = ConfigEditor.config.getIcon()
            .getMetrics();

    private static List<JWidgetPanel>  listWidgetPanels = new LinkedList<>();
    private static List<JIconPanel>    listIconPanels   = new LinkedList<>();
    private static JButton             buttonAddIcon;
    private static JPanel              panelIcons;

    public static JIconPanel addIconPanel(final int number) {
        JIconPanel iconPanel = new JIconPanel(metrics.get(number));
        iconPanel.setBackground(Color.WHITE);
        iconPanel.setBorder(new MatteBorder(1, 1, 1, 1, new Color(100, 149, 237)));
        iconPanel.setPreferredSize(DIMENSION);
        iconPanel.setLayout(new BorderLayout(0, 0));
        panelIcons.add(iconPanel);

        JLabel labelIconTitle = new JLabel();
        labelIconTitle.setHorizontalAlignment(SwingConstants.CENTER);
        iconPanel.add(labelIconTitle, BorderLayout.NORTH);

        JLabel labelIconCurrentValue = new JLabel();
        labelIconCurrentValue.setHorizontalAlignment(SwingConstants.CENTER);
        iconPanel.add(labelIconCurrentValue, BorderLayout.CENTER);

        iconPanel.init();
        iconPanel.setOptions();

        iconPanel.addMouseListener(new OptionWindowMouseListener(iconPanel));
        new DragSource().createDefaultDragGestureRecognizer(iconPanel, DnDConstants.ACTION_COPY,
                new IconDragGestureListener());

        listIconPanels.add(iconPanel);
        return iconPanel;
    }

    public static void removeIconPanel(final JIconPanel iconPanel) throws ConfigEditorException {
        JIconPanel.reduceCountIcon();
        int number = iconPanel.getIndex();
        iconPanel.terminateChart();
        listIconPanels.remove(number);
        metrics.remove(number);
        panelIcons.remove(iconPanel);

        for (int i = number; i < listIconPanels.size(); i++) {
            JIconPanel iconPanel1 = listIconPanels.get(i);
            iconPanel1.reduceIndex();
        }

        for (JWidgetPanel widgetPanel : listWidgetPanels) {
            widgetPanel.setIndex();
        }

        ConfigEditor.updateConfig();
    }

    public static void generateIconPanel(final JPanel panelIcons, final JPanel parent) {
        JPanelEditor.panelIcons = panelIcons;

        for (int i = 0; i < metrics.size(); i++) {
            addIconPanel(i);
        }

        buttonAddIcon = new JButton("Add icon");
        buttonAddIcon.setPreferredSize(DIMENSION);
        parent.add(buttonAddIcon);
        buttonAddIcon.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                MetricElement metric = ConfigDefaultConstants.createDefaultMetricElement();
                metrics.add(metric);
                JIconPanel iconPanel = addIconPanel(metrics.size() - 1);
                iconPanel.runChart();
                OptionWindow.createDialog(iconPanel);
            }
        });
    }

    public static void addWidgetPanel(final JWidgetPanel panelWidget) {
        listWidgetPanels.add(panelWidget);
    }

    public static void generateWidgetPanel() throws ConfigEditorException {
        ChartWidgetButtonListener.setPanels(listWidgetPanels);

        for (int i = 0; i < MAX_ACTIVE; i++) {
            int number = ConfigEditor.config.getWidget().getIconMetrics().get(i);
            JIconPanel iconPanel = listIconPanels.get(number);
            JWidgetPanel widgetPanel = listWidgetPanels.get(i);
            widgetPanel.init();
            widgetPanel.setIconPanel(iconPanel);
            iconPanel.setOptions();

            new WidgetDropTargetAdapter(widgetPanel);
        }
    }

    public static void runChart() {
        for (JIconPanel iconPanel : listIconPanels) {
            iconPanel.runChart();
        }
    }

}
