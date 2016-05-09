package by.training.window;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import by.training.bean.element.OptionsElement;
import by.training.bean.options.MetricType;
import by.training.bean.options.Period;
import by.training.bean.options.RefreshInterval;
import by.training.bean.options.Transport;
import by.training.constants.AppDefaultConstants;
import by.training.editor.ConfigEditor;
import by.training.exception.ConfigEditorException;
import by.training.exception.JIconPanelException;
import by.training.other.Moonwalker;
import by.training.ui.JIconPanel;

import java.awt.GridBagLayout;

import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;

public class OptionWindow extends JDialog {

    private static final long serialVersionUID = -29982346605979633L;

    private static boolean    save;

    private OptionsElement    options;
    private JButton           buttonCalendar;

    private JTextField        textFieldTitle;
    private JTextField        textFieldAddress;
    private JCheckBox         checkBoxSetTitle;
    private JComboBox<Object> comboBoxTypeMetric;
    private JComboBox<Object> comboBoxTransport;
    private JComboBox<Object> comboBoxPeriod;
    private JComboBox<Object> comboBoxRefreshInterval;

    {
        save = false;
    }

    public static void createDialog(final JIconPanel iconPanel) {
        try {
            OptionWindow dialog = new OptionWindow(iconPanel);
            dialog.setTitle("Options");
            dialog.setMinimumSize(new Dimension(450, 300));
            dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            dialog.setLocationRelativeTo(null);
            dialog.setModal(true);
            dialog.setResizable(false);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private OptionWindow(final JIconPanel iconPanel) {
        options = iconPanel.getOptions();
        DatesWindow.setFrom(options.getPeriodElement().getFrom());
        DatesWindow.setTo(options.getPeriodElement().getTo());

        JPanel contentPanel = new JPanel();
        setBounds(100, 100, 450, 300);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.NORTH);
        GridBagLayout gbl_contentPanel = new GridBagLayout();
        gbl_contentPanel.columnWidths = new int[] {0, 0, 0, 0};
        gbl_contentPanel.rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0, 0};
        gbl_contentPanel.columnWeights = new double[] {0.0, 1.0, 0.0, Double.MIN_VALUE};
        gbl_contentPanel.rowWeights = new double[] {0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0,
                Double.MIN_VALUE};
        contentPanel.setLayout(gbl_contentPanel);
        {
            JLabel labelTitle = new JLabel("Title");
            GridBagConstraints gbc_labelTitle = new GridBagConstraints();
            gbc_labelTitle.anchor = GridBagConstraints.EAST;
            gbc_labelTitle.insets = new Insets(0, 0, 5, 5);
            gbc_labelTitle.gridx = 0;
            gbc_labelTitle.gridy = 0;
            contentPanel.add(labelTitle, gbc_labelTitle);
        }
        {
            textFieldTitle = new JTextField();
            GridBagConstraints gbc_textFieldTitle = new GridBagConstraints();
            gbc_textFieldTitle.insets = new Insets(0, 0, 5, 5);
            gbc_textFieldTitle.fill = GridBagConstraints.HORIZONTAL;
            gbc_textFieldTitle.gridx = 1;
            gbc_textFieldTitle.gridy = 0;
            contentPanel.add(textFieldTitle, gbc_textFieldTitle);
            textFieldTitle.setColumns(10);
        }
        {
            JLabel labelMetricType = new JLabel("Metric type");
            GridBagConstraints gbc_labelMetricType = new GridBagConstraints();
            gbc_labelMetricType.anchor = GridBagConstraints.EAST;
            gbc_labelMetricType.insets = new Insets(0, 0, 5, 5);
            gbc_labelMetricType.gridx = 0;
            gbc_labelMetricType.gridy = 1;
            contentPanel.add(labelMetricType, gbc_labelMetricType);
        }
        {
            comboBoxTypeMetric = new JComboBox<>(AppDefaultConstants.TYPE_METRIC_LIST.toArray());
            GridBagConstraints gbc_comboBoxTypeMetric = new GridBagConstraints();
            gbc_comboBoxTypeMetric.insets = new Insets(0, 0, 5, 5);
            gbc_comboBoxTypeMetric.fill = GridBagConstraints.HORIZONTAL;
            gbc_comboBoxTypeMetric.gridx = 1;
            gbc_comboBoxTypeMetric.gridy = 1;
            contentPanel.add(comboBoxTypeMetric, gbc_comboBoxTypeMetric);
        }
        {
            checkBoxSetTitle = new JCheckBox("Set the name of metric type to the title");
            checkBoxSetTitle.setHorizontalAlignment(SwingConstants.LEFT);
            GridBagConstraints gbc_checkBoxSetTitle = new GridBagConstraints();
            gbc_checkBoxSetTitle.anchor = GridBagConstraints.WEST;
            gbc_checkBoxSetTitle.insets = new Insets(0, 0, 5, 5);
            gbc_checkBoxSetTitle.gridx = 1;
            gbc_checkBoxSetTitle.gridy = 2;
            contentPanel.add(checkBoxSetTitle, gbc_checkBoxSetTitle);
        }
        {
            JLabel labelTransport = new JLabel("Transport");
            GridBagConstraints gbc_labelTransport = new GridBagConstraints();
            gbc_labelTransport.anchor = GridBagConstraints.EAST;
            gbc_labelTransport.insets = new Insets(0, 0, 5, 5);
            gbc_labelTransport.gridx = 0;
            gbc_labelTransport.gridy = 3;
            contentPanel.add(labelTransport, gbc_labelTransport);
        }
        {
            comboBoxTransport = new JComboBox<>(AppDefaultConstants.TRANSPORT_LIST.toArray());
            GridBagConstraints gbc_comboBoxTransport = new GridBagConstraints();
            gbc_comboBoxTransport.insets = new Insets(0, 0, 5, 5);
            gbc_comboBoxTransport.fill = GridBagConstraints.HORIZONTAL;
            gbc_comboBoxTransport.gridx = 1;
            gbc_comboBoxTransport.gridy = 3;
            contentPanel.add(comboBoxTransport, gbc_comboBoxTransport);
        }
        {
            JLabel labelAddress = new JLabel("Address");
            GridBagConstraints gbc_labelAddress = new GridBagConstraints();
            gbc_labelAddress.insets = new Insets(0, 0, 5, 5);
            gbc_labelAddress.anchor = GridBagConstraints.EAST;
            gbc_labelAddress.gridx = 0;
            gbc_labelAddress.gridy = 4;
            contentPanel.add(labelAddress, gbc_labelAddress);
        }
        {
            textFieldAddress = new JTextField();
            GridBagConstraints gbc_textFieldAddress = new GridBagConstraints();
            gbc_textFieldAddress.insets = new Insets(0, 0, 5, 5);
            gbc_textFieldAddress.fill = GridBagConstraints.HORIZONTAL;
            gbc_textFieldAddress.gridx = 1;
            gbc_textFieldAddress.gridy = 4;
            contentPanel.add(textFieldAddress, gbc_textFieldAddress);
            textFieldAddress.setColumns(10);
        }
        {
            JLabel labelPeriod = new JLabel("Period");
            GridBagConstraints gbc_labelPeriod = new GridBagConstraints();
            gbc_labelPeriod.anchor = GridBagConstraints.EAST;
            gbc_labelPeriod.insets = new Insets(0, 0, 5, 5);
            gbc_labelPeriod.gridx = 0;
            gbc_labelPeriod.gridy = 5;
            contentPanel.add(labelPeriod, gbc_labelPeriod);
        }
        {
            comboBoxPeriod = new JComboBox<>(AppDefaultConstants.PERIOD_LIST.toArray());
            GridBagConstraints gbc_comboBoxPeriod = new GridBagConstraints();
            gbc_comboBoxPeriod.insets = new Insets(0, 0, 5, 5);
            gbc_comboBoxPeriod.fill = GridBagConstraints.HORIZONTAL;
            gbc_comboBoxPeriod.gridx = 1;
            gbc_comboBoxPeriod.gridy = 5;
            contentPanel.add(comboBoxPeriod, gbc_comboBoxPeriod);
            comboBoxPeriod.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(final ItemEvent event) {
                    if (event.getStateChange() == ItemEvent.SELECTED) {
                        buttonCalendar.setEnabled(true);
                        checkPeriod();
                    }
                }
            });
        }
        {
            buttonCalendar = new JButton(
                    new ImageIcon(((new ImageIcon("src/main/resources/icon/date.png").getImage()
                            .getScaledInstance(16, 16, java.awt.Image.SCALE_SMOOTH)))));
            GridBagConstraints gbc_buttonCalendar = new GridBagConstraints();
            gbc_buttonCalendar.fill = GridBagConstraints.BOTH;
            gbc_buttonCalendar.insets = new Insets(0, 0, 5, 0);
            gbc_buttonCalendar.gridx = 2;
            gbc_buttonCalendar.gridy = 5;
            contentPanel.add(buttonCalendar, gbc_buttonCalendar);
            buttonCalendar.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(final ActionEvent event) {
                    DatesWindow.createDialog();
                }
            });
        }
        {
            JLabel labelRefreshInterval = new JLabel("Refresh Interval");
            GridBagConstraints gbc_labelRefreshInterval = new GridBagConstraints();
            gbc_labelRefreshInterval.anchor = GridBagConstraints.EAST;
            gbc_labelRefreshInterval.insets = new Insets(0, 0, 0, 5);
            gbc_labelRefreshInterval.gridx = 0;
            gbc_labelRefreshInterval.gridy = 6;
            contentPanel.add(labelRefreshInterval, gbc_labelRefreshInterval);
        }
        {
            comboBoxRefreshInterval = new JComboBox<>(
                    AppDefaultConstants.REFRESH_INTERVAL_LIST.toArray());
            GridBagConstraints gbc_comboBoxRefreshInterval = new GridBagConstraints();
            gbc_comboBoxRefreshInterval.insets = new Insets(0, 0, 0, 5);
            gbc_comboBoxRefreshInterval.fill = GridBagConstraints.HORIZONTAL;
            gbc_comboBoxRefreshInterval.gridx = 1;
            gbc_comboBoxRefreshInterval.gridy = 6;
            contentPanel.add(comboBoxRefreshInterval, gbc_comboBoxRefreshInterval);
        }
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                JButton buttonSave = new JButton("Save");
                buttonSave.setActionCommand("Save");
                buttonPane.add(buttonSave);
                getRootPane().setDefaultButton(buttonSave);
                buttonSave.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(final ActionEvent event) {
                        if (checkPeriod()) {
                            MetricType metricType = MetricType.values()[comboBoxTypeMetric
                                    .getSelectedIndex()];
                            Transport transport = Transport.values()[comboBoxTransport
                                    .getSelectedIndex()];
                            Period period = Period.values()[comboBoxPeriod.getSelectedIndex()];
                            RefreshInterval refreshInterval = RefreshInterval
                                    .values()[comboBoxRefreshInterval.getSelectedIndex()];

                            if (checkBoxSetTitle.isSelected()) {
                                options.setTitle(metricType.getTitle());
                            } else {
                                options.setTitle(textFieldTitle.getText());
                            }

                            options.getMetricTypeElement().setMetricType(metricType);
                            options.getMetricTypeElement()
                                    .setSetTitle(checkBoxSetTitle.isSelected());

                            options.getTransportElement().setTransport(transport);
                            options.getTransportElement().setAddress(textFieldAddress.getText());

                            options.getPeriodElement().setPeriod(period);
                            options.getPeriodElement().setFrom(DatesWindow.getFrom());
                            options.getPeriodElement().setTo(DatesWindow.getTo());

                            options.setRefreshInterval(refreshInterval);

                            dispose();

                            try {
                                ConfigEditor.updateConfig();
                                iconPanel.setOptions();
                            } catch (ConfigEditorException | JIconPanelException e) {
                                e.printStackTrace();
                            }

                            save = true;
                            Moonwalker.orNot(options.getTitle());
                        }
                    }
                });
            }
            {
                JButton buttonCancel = new JButton("Cancel");
                buttonCancel.setActionCommand("Cancel");
                buttonPane.add(buttonCancel);
                buttonCancel.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(final ActionEvent event) {
                        save = false;
                        dispose();
                    }
                });
            }
        }
        {
            setEnabledButton(Period.values()[comboBoxPeriod.getSelectedIndex()]);
            checkBoxSetTitle.setSelected(options.getMetricTypeElement().isSetTitle());

            textFieldTitle.setText(options.getTitle());
            textFieldAddress.setText(options.getTransportElement().getAddress());

            comboBoxTypeMetric
                    .setSelectedIndex(options.getMetricTypeElement().getMetricType().ordinal());
            comboBoxTransport
                    .setSelectedIndex(options.getTransportElement().getTransport().ordinal());
            comboBoxPeriod.setSelectedIndex(options.getPeriodElement().getPeriod().ordinal());
            comboBoxRefreshInterval.setSelectedIndex(options.getRefreshInterval().ordinal());
        }
    }

    private boolean checkPeriod() {
        setEnabledButton(Period.values()[comboBoxPeriod.getSelectedIndex()]);

        if (Period.values()[comboBoxPeriod.getSelectedIndex()] == Period.CUSTOM) {
            if (DatesWindow.getFrom() == null || DatesWindow.getTo() == null) {
                DatesWindow.createDialog();
                return false;
            }
        }
        return true;
    }

    private void setEnabledButton(final Period period) {
        if (Period.values()[comboBoxPeriod.getSelectedIndex()] == Period.CUSTOM) {
            buttonCalendar.setEnabled(true);
        } else {
            buttonCalendar.setEnabled(false);
        }
    }

    public static boolean isSave() {
        return save;
    }

}
