package by.training.window;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import by.training.bean.element.MetricTypeElement;
import by.training.bean.element.OptionsElement;
import by.training.bean.element.ParametersElement;
import by.training.bean.element.PeriodElement;
import by.training.bean.element.TransportElement;
import by.training.constants.AppDefaultConstants;
import by.training.editor.ConfigEditor;
import by.training.exception.ConfigEditorException;
import by.training.listener.OptionListener;
import by.training.options.MetricType;
import by.training.options.Period;
import by.training.options.RefreshInterval;
import by.training.options.Transport;

import java.awt.GridBagLayout;

import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Date;

import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;
import java.awt.Color;

public class OptionsWindow extends JDialog {

    private static final long serialVersionUID = -29982346605979633L;

    private static boolean    save;

    private OptionListener    listener;
    private JButton           buttonCalendar;

    private JTextField        textFieldTitle;
    private JCheckBox         checkBoxSetTitle;
    private JComboBox<Object> comboBoxTypeMetric;
    private JComboBox<Object> comboBoxTransport;
    private JComboBox<Object> comboBoxPeriod;
    private JComboBox<Object> comboBoxRefreshInterval;
    private JLabel            labelAddress;
    private JTextField        textFieldAddress;
    private JLabel            labelHost;
    private JTextField        textFieldHost;
    private JLabel            labelPort;
    private JTextField        textFieldPort;

    {
        save = false;
    }

    public static void createDialog(final OptionListener listener) {
        try {
            OptionsWindow dialog = new OptionsWindow(listener);
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

    private OptionsWindow(final OptionListener listener) {
        this.listener = listener;
        DatesWindow.setFrom(listener.getOptions().getPeriodElement().getFrom());
        DatesWindow.setTo(listener.getOptions().getPeriodElement().getTo());

        JPanel contentPanel = new JPanel();
        setBounds(100, 100, 450, 400);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.NORTH);
        GridBagLayout gbl_contentPanel = new GridBagLayout();
        gbl_contentPanel.columnWidths = new int[] {0, 0, 0, 0};
        gbl_contentPanel.rowHeights = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        gbl_contentPanel.columnWeights = new double[] {0.0, 1.0, 0.0, Double.MIN_VALUE};
        gbl_contentPanel.rowWeights = new double[] {0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
                Double.MIN_VALUE};
        contentPanel.setLayout(gbl_contentPanel);
        {
            JLabel labelTitle = new JLabel("Title");
            GridBagConstraints gbc_labelTitle = new GridBagConstraints();
            gbc_labelTitle.anchor = GridBagConstraints.EAST;
            gbc_labelTitle.fill = GridBagConstraints.VERTICAL;
            gbc_labelTitle.insets = new Insets(0, 0, 5, 5);
            gbc_labelTitle.gridx = 0;
            gbc_labelTitle.gridy = 0;
            contentPanel.add(labelTitle, gbc_labelTitle);
        }
        {
            textFieldTitle = new JTextField();
            GridBagConstraints gbc_textFieldTitle = new GridBagConstraints();
            gbc_textFieldTitle.insets = new Insets(0, 0, 5, 5);
            gbc_textFieldTitle.fill = GridBagConstraints.BOTH;
            gbc_textFieldTitle.gridx = 1;
            gbc_textFieldTitle.gridy = 0;
            contentPanel.add(textFieldTitle, gbc_textFieldTitle);
            textFieldTitle.setColumns(10);
        }
        {
            JLabel labelMetricType = new JLabel("Metric type");
            GridBagConstraints gbc_labelMetricType = new GridBagConstraints();
            gbc_labelMetricType.fill = GridBagConstraints.VERTICAL;
            gbc_labelMetricType.anchor = GridBagConstraints.EAST;
            gbc_labelMetricType.insets = new Insets(0, 0, 5, 5);
            gbc_labelMetricType.gridx = 0;
            gbc_labelMetricType.gridy = 1;
            contentPanel.add(labelMetricType, gbc_labelMetricType);
        }
        {
            comboBoxTypeMetric = new JComboBox<>(AppDefaultConstants.METRIC_TYPE_LIST.toArray());
            GridBagConstraints gbc_comboBoxMetricType = new GridBagConstraints();
            gbc_comboBoxMetricType.insets = new Insets(0, 0, 5, 5);
            gbc_comboBoxMetricType.fill = GridBagConstraints.BOTH;
            gbc_comboBoxMetricType.gridx = 1;
            gbc_comboBoxMetricType.gridy = 1;
            contentPanel.add(comboBoxTypeMetric, gbc_comboBoxMetricType);
        }
        {
            checkBoxSetTitle = new JCheckBox("Set the name of metric type to the title");
            checkBoxSetTitle.setBackground(Color.LIGHT_GRAY);
            checkBoxSetTitle.setHorizontalAlignment(SwingConstants.LEFT);
            GridBagConstraints gbc_checkBoxSetTitle = new GridBagConstraints();
            gbc_checkBoxSetTitle.fill = GridBagConstraints.VERTICAL;
            gbc_checkBoxSetTitle.anchor = GridBagConstraints.WEST;
            gbc_checkBoxSetTitle.insets = new Insets(0, 0, 5, 5);
            gbc_checkBoxSetTitle.gridx = 1;
            gbc_checkBoxSetTitle.gridy = 2;
            contentPanel.add(checkBoxSetTitle, gbc_checkBoxSetTitle);
        }
        {
            JLabel labelTransport = new JLabel("Transport");
            GridBagConstraints gbc_labelTransport = new GridBagConstraints();
            gbc_labelTransport.fill = GridBagConstraints.VERTICAL;
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
            gbc_comboBoxTransport.fill = GridBagConstraints.BOTH;
            gbc_comboBoxTransport.gridx = 1;
            gbc_comboBoxTransport.gridy = 3;
            contentPanel.add(comboBoxTransport, gbc_comboBoxTransport);
            comboBoxTransport.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(final ItemEvent event) {
                    if (event.getStateChange() == ItemEvent.SELECTED) {
                        initParameterFields(
                                listener.getOptions().getTransportElement().getParameters());
                        setVisibilityToFields();
                    }
                }
            });
        }
        {
            labelAddress = new JLabel("Address");
            GridBagConstraints gbc_labelAddress = new GridBagConstraints();
            gbc_labelAddress.fill = GridBagConstraints.VERTICAL;
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
            gbc_textFieldAddress.fill = GridBagConstraints.BOTH;
            gbc_textFieldAddress.gridx = 1;
            gbc_textFieldAddress.gridy = 4;
            contentPanel.add(textFieldAddress, gbc_textFieldAddress);
            textFieldAddress.setColumns(10);
        }
        {
            JButton buttonSetDefaultAddress = new JButton("Default");
            GridBagConstraints gbc_buttonSetDefaultAddress = new GridBagConstraints();
            gbc_buttonSetDefaultAddress.fill = GridBagConstraints.HORIZONTAL;
            gbc_buttonSetDefaultAddress.gridheight = 3;
            gbc_buttonSetDefaultAddress.insets = new Insets(0, 0, 5, 0);
            gbc_buttonSetDefaultAddress.gridx = 2;
            gbc_buttonSetDefaultAddress.gridy = 4;
            contentPanel.add(buttonSetDefaultAddress, gbc_buttonSetDefaultAddress);
            buttonSetDefaultAddress.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(final ActionEvent event) {
                    initParameterFields(Transport.values()[comboBoxTransport.getSelectedIndex()]
                            .getDefaultParameters());
                }
            });
        }
        {
            labelHost = new JLabel("Host");
            GridBagConstraints gbc_labelHost = new GridBagConstraints();
            gbc_labelHost.fill = GridBagConstraints.VERTICAL;
            gbc_labelHost.insets = new Insets(0, 0, 5, 5);
            gbc_labelHost.anchor = GridBagConstraints.EAST;
            gbc_labelHost.gridx = 0;
            gbc_labelHost.gridy = 5;
            contentPanel.add(labelHost, gbc_labelHost);
        }
        {
            textFieldHost = new JTextField();
            GridBagConstraints gbc_textFieldHost = new GridBagConstraints();
            gbc_textFieldHost.insets = new Insets(0, 0, 5, 5);
            gbc_textFieldHost.fill = GridBagConstraints.BOTH;
            gbc_textFieldHost.gridx = 1;
            gbc_textFieldHost.gridy = 5;
            contentPanel.add(textFieldHost, gbc_textFieldHost);
            textFieldHost.setColumns(10);
        }
        {
            labelPort = new JLabel("Port");
            GridBagConstraints gbc_labelPort = new GridBagConstraints();
            gbc_labelPort.fill = GridBagConstraints.VERTICAL;
            gbc_labelPort.insets = new Insets(0, 0, 5, 5);
            gbc_labelPort.anchor = GridBagConstraints.EAST;
            gbc_labelPort.gridx = 0;
            gbc_labelPort.gridy = 6;
            contentPanel.add(labelPort, gbc_labelPort);
        }
        {
            textFieldPort = new JTextField();
            GridBagConstraints gbc_textFieldPort = new GridBagConstraints();
            gbc_textFieldPort.insets = new Insets(0, 0, 5, 5);
            gbc_textFieldPort.fill = GridBagConstraints.BOTH;
            gbc_textFieldPort.gridx = 1;
            gbc_textFieldPort.gridy = 6;
            contentPanel.add(textFieldPort, gbc_textFieldPort);
            textFieldPort.setColumns(10);
            textFieldPort.addKeyListener(new KeyAdapter() {
                @Override
                public void keyTyped(final KeyEvent event) {
                    char ch = event.getKeyChar();
                    if (!Character.isDigit(ch) || (ch == KeyEvent.VK_BACK_SPACE)
                            || (ch == KeyEvent.VK_DELETE)) {
                        event.consume();
                    }
                };
            });
        }
        {
            JLabel labelPeriod = new JLabel("Period");
            GridBagConstraints gbc_labelPeriod = new GridBagConstraints();
            gbc_labelPeriod.fill = GridBagConstraints.VERTICAL;
            gbc_labelPeriod.anchor = GridBagConstraints.EAST;
            gbc_labelPeriod.insets = new Insets(0, 0, 5, 5);
            gbc_labelPeriod.gridx = 0;
            gbc_labelPeriod.gridy = 7;
            contentPanel.add(labelPeriod, gbc_labelPeriod);
        }
        {
            comboBoxPeriod = new JComboBox<>(AppDefaultConstants.PERIOD_LIST.toArray());
            GridBagConstraints gbc_comboBoxPeriod = new GridBagConstraints();
            gbc_comboBoxPeriod.insets = new Insets(0, 0, 5, 5);
            gbc_comboBoxPeriod.fill = GridBagConstraints.BOTH;
            gbc_comboBoxPeriod.gridx = 1;
            gbc_comboBoxPeriod.gridy = 7;
            contentPanel.add(comboBoxPeriod, gbc_comboBoxPeriod);
            comboBoxPeriod.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(final ItemEvent event) {
                    if (event.getStateChange() == ItemEvent.SELECTED) {
                        buttonCalendar.setEnabled(true);
                        isPeriodEstablish();
                    }
                }
            });
        }
        {
            buttonCalendar = new JButton(
                    new ImageIcon(((new ImageIcon("src/main/resources/icon/date.png").getImage()
                            .getScaledInstance(16, 16, java.awt.Image.SCALE_SMOOTH)))));
            GridBagConstraints gbc_buttonCalendar = new GridBagConstraints();
            gbc_buttonCalendar.fill = GridBagConstraints.VERTICAL;
            gbc_buttonCalendar.insets = new Insets(0, 0, 5, 0);
            gbc_buttonCalendar.gridx = 2;
            gbc_buttonCalendar.gridy = 7;
            contentPanel.add(buttonCalendar, gbc_buttonCalendar);
            buttonCalendar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(final ActionEvent event) {
                    DatesWindow.createDialog();
                }
            });
        }
        {
            JLabel labelRefreshInterval = new JLabel("Refresh interval");
            GridBagConstraints gbc_labelRefreshInterval = new GridBagConstraints();
            gbc_labelRefreshInterval.fill = GridBagConstraints.VERTICAL;
            gbc_labelRefreshInterval.anchor = GridBagConstraints.EAST;
            gbc_labelRefreshInterval.insets = new Insets(0, 0, 0, 5);
            gbc_labelRefreshInterval.gridx = 0;
            gbc_labelRefreshInterval.gridy = 8;
            contentPanel.add(labelRefreshInterval, gbc_labelRefreshInterval);
        }
        {
            comboBoxRefreshInterval = new JComboBox<>(
                    AppDefaultConstants.REFRESH_INTERVAL_LIST.toArray());
            GridBagConstraints gbc_comboBoxRefreshInterval = new GridBagConstraints();
            gbc_comboBoxRefreshInterval.insets = new Insets(0, 0, 0, 5);
            gbc_comboBoxRefreshInterval.fill = GridBagConstraints.BOTH;
            gbc_comboBoxRefreshInterval.gridx = 1;
            gbc_comboBoxRefreshInterval.gridy = 8;
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
                        if (isPeriodEstablish()) {
                            editTitle();
                            editMetricType();
                            editTransport();
                            editPeriod();
                            editRefreshInterval();

                            if (!listener.isResourceExist()) {
                                ResourceFailedWindow.createDialog();
                                if (ResourceFailedWindow.isLater()) {
                                    dispose();
                                }
                            } else {
                                dispose();
                            }

                            try {
                                ConfigEditor.updateConfig();
                            } catch (ConfigEditorException e) {
                                e.printStackTrace();
                            }

                            save = true;
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
            OptionsElement options = listener.getOptions();

            checkBoxSetTitle.setSelected(options.getMetricTypeElement().isSetToTitle());
            textFieldTitle.setText(options.getTitle());

            comboBoxTypeMetric
                    .setSelectedIndex(options.getMetricTypeElement().getMetricType().ordinal());
            comboBoxTransport
                    .setSelectedIndex(options.getTransportElement().getTransport().ordinal());
            comboBoxPeriod.setSelectedIndex(options.getPeriodElement().getPeriod().ordinal());
            comboBoxRefreshInterval.setSelectedIndex(options.getRefreshInterval().ordinal());

            setVisibilityToFields();
            initParameterFields(options.getTransportElement().getParameters());
            setEnabledButton(Period.values()[comboBoxPeriod.getSelectedIndex()]);
        }
    }

    private void setVisibilityToFields() {
        Transport transport = Transport.values()[comboBoxTransport.getSelectedIndex()];
        switch (transport) {
            case REST:
            case SOAP:
                setVisibilities(true, false, false, false);
                break;
            case RMI:
            case JMX:
            case JMS:
                setVisibilities(false, true, true, true);
                break;
        }
    }

    private void setVisibilities(final boolean addressField, final boolean hostField,
            final boolean portField, final boolean nameField) {
        labelAddress.setVisible(addressField);
        textFieldAddress.setVisible(addressField);
        labelHost.setVisible(hostField);
        textFieldHost.setVisible(hostField);
        labelPort.setVisible(portField);
        textFieldPort.setVisible(portField);
    }

    private void initParameterFields(final ParametersElement parameters) {
        Transport transport = Transport.values()[comboBoxTransport.getSelectedIndex()];
        switch (transport) {
            case REST:
            case SOAP:
                if (parameters.getAddress() != null) {
                    textFieldAddress.setText(parameters.getAddress());
                } else {
                    textFieldAddress.setText(transport.getDefaultParameters().getAddress());
                }
                break;

            case RMI:
            case JMX:
            case JMS:
                if (parameters.getHost() != null) {
                    textFieldHost.setText(parameters.getHost());
                } else {
                    textFieldHost.setText(transport.getDefaultParameters().getHost());
                }

                if (parameters.getPort() != null) {
                    textFieldPort.setText(parameters.getPort());
                } else {
                    textFieldPort.setText(transport.getDefaultParameters().getPort());
                }
                break;
        }
    }

    private void editTitle() {
        String title = listener.getOptions().getTitle();
        String newTitle;

        if (checkBoxSetTitle.isSelected()) {
            MetricType metricType = MetricType.values()[comboBoxTypeMetric.getSelectedIndex()];
            newTitle = metricType.getTitle();
        } else {
            newTitle = textFieldTitle.getText();
        }

        if (!title.equals(newTitle)) {
            listener.getOptions().setTitle(newTitle);
            listener.titleChanged();
        }
    }

    private void editMetricType() {
        MetricTypeElement metricTypeElement = listener.getOptions().getMetricTypeElement();
        MetricType metricType = MetricType.values()[comboBoxTypeMetric.getSelectedIndex()];

        if (metricTypeElement.getMetricType() != metricType) {
            metricTypeElement.setMetricType(metricType);
            listener.metricTypeChanged();
        }

        metricTypeElement.setToTitle(checkBoxSetTitle.isSelected());
    }

    private void editTransport() {
        TransportElement transportElement = listener.getOptions().getTransportElement();
        Transport transport = Transport.values()[comboBoxTransport.getSelectedIndex()];

        boolean changed = false;
        boolean tChanged = false;

        switch (transport) {
            case REST:
            case SOAP:
                String address = textFieldAddress.getText();

                if (!address.equals(transportElement.getParameters().getAddress())) {
                    listener.getOptions().getTransportElement().getParameters()
                            .setParameters(address, null, null);
                    changed = true;
                }

                break;

            case RMI:
            case JMX:
            case JMS:
                String host = textFieldHost.getText();
                String port = textFieldPort.getText();

                if (!host.equals(transportElement.getParameters().getHost())
                        || !port.equals(transportElement.getParameters().getPort())) {
                    listener.getOptions().getTransportElement().getParameters().setParameters(null,
                            host, port);
                    changed = true;
                }
                break;
        }

        if (transportElement.getTransport() != transport) {
            listener.getOptions().getTransportElement().setTransport(transport);
            listener.transportChanged();
            tChanged = true;
        }

        if (!tChanged) {
            listener.parametersChanged(changed);
        }
    }

    private void editPeriod() {
        PeriodElement periodElement = listener.getOptions().getPeriodElement();
        Period period = Period.values()[comboBoxPeriod.getSelectedIndex()];

        if (periodElement.getPeriod() != period) {
            listener.getOptions().getPeriodElement().setPeriod(period);

            if ((period == Period.LAST_MINUTES_15) || (period == Period.LAST_MINUTES_30)
                    || (period == Period.LAST_HOUR)) {
                listener.periodChanged();
            }
        }

        if (periodElement.getPeriod() == Period.CUSTOM) {
            Date from = DatesWindow.getFrom();
            Date to = DatesWindow.getTo();

            if ((periodElement.getFrom() != from) || (periodElement.getTo() != to)) {
                listener.getOptions().getPeriodElement().setFrom(DatesWindow.getFrom());
                listener.getOptions().getPeriodElement().setTo(DatesWindow.getTo());
                listener.customPeriodChanged();
            }
        }
    }

    private void editRefreshInterval() {
        RefreshInterval interval = listener.getOptions().getRefreshInterval();
        RefreshInterval refreshInterval = RefreshInterval.values()[comboBoxRefreshInterval
                .getSelectedIndex()];

        if (interval != refreshInterval) {
            listener.getOptions().setRefreshInterval(refreshInterval);
            listener.refreshIntervalChanged();
        }
    }

    private boolean isPeriodEstablish() {
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
