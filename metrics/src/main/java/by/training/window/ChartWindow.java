package by.training.window;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import java.awt.Dimension;

import static by.training.constants.ConfigDefaultConstants.*;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

import by.training.bean.ui.JWidgetPanel;
import by.training.editor.ConfigEditor;
import by.training.editor.JPanelEditor;
import by.training.exception.ConfigEditorException;
import by.training.listener.ChartWidgetButtonListener;

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
                    frame.setMinimumSize(new Dimension(1400, 950));
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private ChartWindow() throws ConfigEditorException {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 350);
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
        JPanelEditor.addWidgetPanel(panelWidget1);

        GridBagLayout gbl_panelWidget1 = new GridBagLayout();
        gbl_panelWidget1.columnWidths = new int[] {0, 0};
        gbl_panelWidget1.rowHeights = new int[] {0, 0, 0};
        gbl_panelWidget1.columnWeights = new double[] {1.0, Double.MIN_VALUE};
        gbl_panelWidget1.rowWeights = new double[] {0.0, 1.0, Double.MIN_VALUE};
        panelWidget1.setLayout(gbl_panelWidget1);

        JPanel panelStatus1 = new JPanel();
        panelStatus1.setBackground(Color.GRAY);
        GridBagConstraints gbc_panelStatus1 = new GridBagConstraints();
        gbc_panelStatus1.insets = new Insets(0, 0, 5, 0);
        gbc_panelStatus1.fill = GridBagConstraints.BOTH;
        gbc_panelStatus1.gridx = 0;
        gbc_panelStatus1.gridy = 0;
        panelWidget1.add(panelStatus1, gbc_panelStatus1);
        panelStatus1.setLayout(new BorderLayout(0, 0));

        JLabel labelName1 = new JLabel("Current value: ");
        labelName1.setHorizontalAlignment(SwingConstants.RIGHT);
        labelName1.setForeground(Color.WHITE);
        panelStatus1.add(labelName1, BorderLayout.WEST);

        JLabel labelCurrentValue1 = new JLabel();
        labelCurrentValue1.setForeground(Color.WHITE);
        panelStatus1.add(labelCurrentValue1, BorderLayout.CENTER);

        JButton buttonOptions1 = new JButton("Options");
        panelStatus1.add(buttonOptions1, BorderLayout.EAST);

        JPanel panelChart1 = new JPanel();
        GridBagConstraints gbc_panelChart1 = new GridBagConstraints();
        gbc_panelChart1.fill = GridBagConstraints.BOTH;
        gbc_panelChart1.gridx = 0;
        gbc_panelChart1.gridy = 1;
        panelWidget1.add(panelChart1, gbc_panelChart1);
        panelChart1.setLayout(new BorderLayout(0, 0));

        JWidgetPanel panelWidget2 = new JWidgetPanel();
        panelWidget2.setBorder(new MatteBorder(5, 2, 2, 5, Color.DARK_GRAY));
        panelWidget2.setBackground(Color.WHITE);
        panelWidgetsTop.add(panelWidget2);
        JPanelEditor.addWidgetPanel(panelWidget2);

        GridBagLayout gbl_panelWidget2 = new GridBagLayout();
        gbl_panelWidget2.columnWidths = new int[] {0, 0};
        gbl_panelWidget2.rowHeights = new int[] {0, 0, 0};
        gbl_panelWidget2.columnWeights = new double[] {1.0, Double.MIN_VALUE};
        gbl_panelWidget2.rowWeights = new double[] {0.0, 1.0, Double.MIN_VALUE};
        panelWidget2.setLayout(gbl_panelWidget2);

        JPanel panelStatus2 = new JPanel();
        panelStatus2.setBackground(Color.GRAY);
        GridBagConstraints gbc_panelStatus2 = new GridBagConstraints();
        gbc_panelStatus2.insets = new Insets(0, 0, 5, 0);
        gbc_panelStatus2.fill = GridBagConstraints.BOTH;
        gbc_panelStatus2.gridx = 0;
        gbc_panelStatus2.gridy = 0;
        panelWidget2.add(panelStatus2, gbc_panelStatus2);
        panelStatus2.setLayout(new BorderLayout(0, 0));

        JLabel labelName2 = new JLabel("Current value: ");
        labelName2.setForeground(Color.WHITE);
        panelStatus2.add(labelName2, BorderLayout.WEST);

        JLabel labelCurrentValue2 = new JLabel();
        labelCurrentValue2.setForeground(Color.WHITE);
        panelStatus2.add(labelCurrentValue2, BorderLayout.CENTER);

        JButton buttonOptions2 = new JButton("Options");
        panelStatus2.add(buttonOptions2, BorderLayout.EAST);

        JPanel panelChart2 = new JPanel();
        GridBagConstraints gbc_panelChart2 = new GridBagConstraints();
        gbc_panelChart2.fill = GridBagConstraints.BOTH;
        gbc_panelChart2.gridx = 0;
        gbc_panelChart2.gridy = 1;
        panelWidget2.add(panelChart2, gbc_panelChart2);
        panelChart2.setLayout(new BorderLayout(0, 0));

        JPanel panelWidgetsBottom = new JPanel();
        panelWidgets.add(panelWidgetsBottom);
        panelWidgetsBottom.setLayout(new BoxLayout(panelWidgetsBottom, BoxLayout.X_AXIS));

        JWidgetPanel panelWidget3 = new JWidgetPanel();
        panelWidget3.setBorder(new MatteBorder(2, 5, 5, 2, Color.DARK_GRAY));
        panelWidget3.setBackground(Color.WHITE);
        panelWidgetsBottom.add(panelWidget3);
        JPanelEditor.addWidgetPanel(panelWidget3);

        GridBagLayout gbl_panelWidget3 = new GridBagLayout();
        gbl_panelWidget3.columnWidths = new int[] {0, 0};
        gbl_panelWidget3.rowHeights = new int[] {0, 0, 0};
        gbl_panelWidget3.columnWeights = new double[] {1.0, Double.MIN_VALUE};
        gbl_panelWidget3.rowWeights = new double[] {0.0, 1.0, Double.MIN_VALUE};
        panelWidget3.setLayout(gbl_panelWidget3);

        JPanel panelStatus3 = new JPanel();
        panelStatus3.setBackground(Color.GRAY);
        GridBagConstraints gbc_panelStatus3 = new GridBagConstraints();
        gbc_panelStatus3.insets = new Insets(0, 0, 5, 0);
        gbc_panelStatus3.fill = GridBagConstraints.BOTH;
        gbc_panelStatus3.gridx = 0;
        gbc_panelStatus3.gridy = 0;
        panelWidget3.add(panelStatus3, gbc_panelStatus3);
        panelStatus3.setLayout(new BorderLayout(0, 0));

        JLabel labelName3 = new JLabel("Current value: ");
        labelName3.setForeground(Color.WHITE);
        panelStatus3.add(labelName3, BorderLayout.WEST);

        JLabel labelCurrentValue3 = new JLabel();
        labelCurrentValue3.setForeground(Color.WHITE);
        panelStatus3.add(labelCurrentValue3, BorderLayout.CENTER);

        JButton buttonOptions3 = new JButton("Options");
        panelStatus3.add(buttonOptions3, BorderLayout.EAST);

        JPanel panelChart3 = new JPanel();
        GridBagConstraints gbc_panelChart3 = new GridBagConstraints();
        gbc_panelChart3.fill = GridBagConstraints.BOTH;
        gbc_panelChart3.gridx = 0;
        gbc_panelChart3.gridy = 1;
        panelWidget3.add(panelChart3, gbc_panelChart3);
        panelChart3.setLayout(new BorderLayout(0, 0));

        JWidgetPanel panelWidget4 = new JWidgetPanel();
        panelWidget4.setBorder(new MatteBorder(2, 2, 5, 5, Color.DARK_GRAY));
        panelWidget4.setBackground(Color.WHITE);
        panelWidgetsBottom.add(panelWidget4);
        JPanelEditor.addWidgetPanel(panelWidget4);

        GridBagLayout gbl_panelWidget4 = new GridBagLayout();
        gbl_panelWidget4.columnWidths = new int[] {0, 0};
        gbl_panelWidget4.rowHeights = new int[] {0, 0, 0};
        gbl_panelWidget4.columnWeights = new double[] {1.0, Double.MIN_VALUE};
        gbl_panelWidget4.rowWeights = new double[] {0.0, 1.0, Double.MIN_VALUE};
        panelWidget4.setLayout(gbl_panelWidget4);

        JPanel panelStatus4 = new JPanel();
        panelStatus4.setBackground(Color.GRAY);
        GridBagConstraints gbc_panelStatus4 = new GridBagConstraints();
        gbc_panelStatus4.insets = new Insets(0, 0, 5, 0);
        gbc_panelStatus4.fill = GridBagConstraints.BOTH;
        gbc_panelStatus4.gridx = 0;
        gbc_panelStatus4.gridy = 0;
        panelWidget4.add(panelStatus4, gbc_panelStatus4);
        panelStatus4.setLayout(new BorderLayout(0, 0));

        JLabel labelName4 = new JLabel("Current value: ");
        labelName4.setForeground(Color.WHITE);
        panelStatus4.add(labelName4, BorderLayout.WEST);

        JLabel labelCurrentValue4 = new JLabel();
        labelCurrentValue4.setForeground(Color.WHITE);
        panelStatus4.add(labelCurrentValue4, BorderLayout.CENTER);

        JButton buttonOptions4 = new JButton("Options");
        panelStatus4.add(buttonOptions4, BorderLayout.EAST);

        JPanel panelChart4 = new JPanel();
        GridBagConstraints gbc_panelChart4 = new GridBagConstraints();
        gbc_panelChart4.fill = GridBagConstraints.BOTH;
        gbc_panelChart4.gridx = 0;
        gbc_panelChart4.gridy = 1;
        panelWidget4.add(panelChart4, gbc_panelChart4);
        panelChart4.setLayout(new BorderLayout(0, 0));

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

        JPanelEditor.generateIconPanel(panelIcons, panels);
        JPanelEditor.generateWidgetPanel();
        JPanelEditor.runChart();
        new ChartWidgetButtonListener(ConfigEditor.config.getWidget().getActive())
                .setVisibleWindows();
    }

}
