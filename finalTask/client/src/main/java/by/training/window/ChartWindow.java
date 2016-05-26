package by.training.window;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import java.awt.Dimension;

import static by.training.constants.ConfigDefaultConstants.*;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.border.MatteBorder;

import by.training.editor.ConfigEditor;
import by.training.editor.JPanelEditor;
import by.training.exception.ConfigEditorException;
import by.training.listener.ChartWidgetButtonListener;
import by.training.ui.JWidgetPanel;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.FlowLayout;

public class ChartWindow extends JFrame {

    private static final long serialVersionUID = 556876083127358761L;

    public static void startApp() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    ChartWindow frame = new ChartWindow();
                    frame.setTitle("Metrics");
                    frame.setMinimumSize(new Dimension(900, 600));
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                    frame.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosing(final WindowEvent event) {
                            event.getWindow().dispose();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private ChartWindow() throws ConfigEditorException {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1200, 800);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        GridBagLayout gbl_contentPane = new GridBagLayout();
        gbl_contentPane.columnWidths = new int[] {424, 0};
        gbl_contentPane.rowHeights = new int[] {0, 71, 71, 0};
        gbl_contentPane.columnWeights = new double[] {1.0, Double.MIN_VALUE};
        gbl_contentPane.rowWeights = new double[] {0.0, 1.0, 0.0, Double.MIN_VALUE};
        contentPane.setLayout(gbl_contentPane);

        JPanel panelWindows = new JPanel();
        panelWindows.setBackground(Color.ORANGE);
        GridBagConstraints gbc_panelWindows = new GridBagConstraints();
        gbc_panelWindows.insets = new Insets(0, 0, 5, 0);
        gbc_panelWindows.fill = GridBagConstraints.BOTH;
        gbc_panelWindows.gridx = 0;
        gbc_panelWindows.gridy = 0;
        contentPane.add(panelWindows, gbc_panelWindows);

        JButton buttonOneWindow = new JButton("One widget");
        panelWindows.add(buttonOneWindow);
        buttonOneWindow.addActionListener(new ChartWidgetButtonListener(MIN_ACTIVE));

        JButton buttonTwoWindows = new JButton("Two widgets");
        panelWindows.add(buttonTwoWindows);
        buttonTwoWindows.addActionListener(new ChartWidgetButtonListener(AVG_ACTIVE));

        JButton buttonFourWindows = new JButton("Four widgets");
        panelWindows.add(buttonFourWindows);
        buttonFourWindows.addActionListener(new ChartWidgetButtonListener(MAX_ACTIVE));

        JPanel panelWidgets = new JPanel();
        panelWidgets.setBackground(Color.WHITE);
        GridBagConstraints gbc_panelWidgets = new GridBagConstraints();
        gbc_panelWidgets.insets = new Insets(0, 0, 5, 0);
        gbc_panelWidgets.fill = GridBagConstraints.BOTH;
        gbc_panelWidgets.gridx = 0;
        gbc_panelWidgets.gridy = 1;
        contentPane.add(panelWidgets, gbc_panelWidgets);
        panelWidgets.setLayout(new BoxLayout(panelWidgets, BoxLayout.Y_AXIS));

        JPanel panelWidgetsTop = new JPanel();
        panelWidgets.add(panelWidgetsTop);
        panelWidgetsTop.setLayout(new BoxLayout(panelWidgetsTop, BoxLayout.X_AXIS));

        JWidgetPanel panelWidget1 = new JWidgetPanel();
        panelWidget1.setBorder(new MatteBorder(5, 5, 2, 2, Color.DARK_GRAY));
        panelWidget1.setBackground(Color.WHITE);
        panelWidgetsTop.add(panelWidget1);
        JPanelEditor.JWidgetPanelEditor.addPanel(panelWidget1);

        JWidgetPanel panelWidget2 = new JWidgetPanel();
        panelWidget2.setBorder(new MatteBorder(5, 2, 2, 5, Color.DARK_GRAY));
        panelWidget2.setBackground(Color.WHITE);
        panelWidgetsTop.add(panelWidget2);
        JPanelEditor.JWidgetPanelEditor.addPanel(panelWidget2);

        JPanel panelWidgetsBottom = new JPanel();
        panelWidgets.add(panelWidgetsBottom);
        panelWidgetsBottom.setLayout(new BoxLayout(panelWidgetsBottom, BoxLayout.X_AXIS));

        JWidgetPanel panelWidget3 = new JWidgetPanel();
        panelWidget3.setBorder(new MatteBorder(2, 5, 5, 2, Color.DARK_GRAY));
        panelWidget3.setBackground(Color.WHITE);
        panelWidgetsBottom.add(panelWidget3);
        JPanelEditor.JWidgetPanelEditor.addPanel(panelWidget3);

        JWidgetPanel panelWidget4 = new JWidgetPanel();
        panelWidget4.setBorder(new MatteBorder(2, 2, 5, 5, Color.DARK_GRAY));
        panelWidget4.setBackground(Color.WHITE);
        panelWidgetsBottom.add(panelWidget4);
        JPanelEditor.JWidgetPanelEditor.addPanel(panelWidget4);

        JPanel panels = new JPanel();
        panels.setBackground(Color.LIGHT_GRAY);
        panels.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        JScrollPane scrollPane = new JScrollPane(panels);
        GridBagConstraints gbc_scrollPane = new GridBagConstraints();
        gbc_scrollPane.fill = GridBagConstraints.BOTH;
        gbc_scrollPane.gridx = 0;
        gbc_scrollPane.gridy = 2;
        contentPane.add(scrollPane, gbc_scrollPane);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

        JPanel panelIcons = new JPanel();
        panelIcons.setBackground(Color.LIGHT_GRAY);
        FlowLayout flowLayout = (FlowLayout) panelIcons.getLayout();
        flowLayout.setAlignment(FlowLayout.LEFT);
        flowLayout.setVgap(0);
        flowLayout.setHgap(0);
        panels.add(panelIcons);

        JPanelEditor.generatePanels(panelIcons, panels);
        new ChartWidgetButtonListener(ConfigEditor.config.getWidget().getActive())
                .setVisibleWindows();
    }

}
