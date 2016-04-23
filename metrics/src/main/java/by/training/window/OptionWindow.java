package by.training.window;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import by.training.bean.element.MetricElement;
import by.training.bean.metric.Period;
import by.training.bean.metric.RefreshInterval;
import by.training.bean.metric.Transport;
import by.training.bean.metric.TypeMetric;
import by.training.bean.ui.JIconPanel;
import by.training.constants.AppDefaultConstants;
import by.training.editor.ConfigEditor;
import by.training.exception.ConfigEditorException;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

public class OptionWindow extends JDialog {

    private static final long serialVersionUID = -29982346605979633L;

    private MetricElement     metric;
    private JTextField        textFieldTitle;
    private JComboBox<Object> comboBoxTypeMetric;
    private JComboBox<Object> comboBoxTransport;
    private JComboBox<Object> comboBoxPeriod;
    private JComboBox<Object> comboBoxRefreshInterval;

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
        metric = iconPanel.getMetric();

        JPanel contentPanel = new JPanel();
        setBounds(100, 100, 450, 300);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.NORTH);
        GridBagLayout gbl_contentPanel = new GridBagLayout();
        gbl_contentPanel.columnWidths = new int[] {0, 0, 0};
        gbl_contentPanel.rowHeights = new int[] {0, 0, 0, 0, 0, 0};
        gbl_contentPanel.columnWeights = new double[] {0.0, 1.0, Double.MIN_VALUE};
        gbl_contentPanel.rowWeights = new double[] {0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        contentPanel.setLayout(gbl_contentPanel);
        {
            JLabel labelTitle = new JLabel("Title");
            GridBagConstraints gbc_labelTitle = new GridBagConstraints();
            gbc_labelTitle.insets = new Insets(0, 0, 5, 5);
            gbc_labelTitle.gridx = 0;
            gbc_labelTitle.gridy = 0;
            contentPanel.add(labelTitle, gbc_labelTitle);
        }
        {
            textFieldTitle = new JTextField();
            GridBagConstraints gbc_textFieldTitle = new GridBagConstraints();
            gbc_textFieldTitle.insets = new Insets(0, 0, 5, 0);
            gbc_textFieldTitle.fill = GridBagConstraints.HORIZONTAL;
            gbc_textFieldTitle.gridx = 1;
            gbc_textFieldTitle.gridy = 0;
            contentPanel.add(textFieldTitle, gbc_textFieldTitle);
            textFieldTitle.setColumns(10);
        }
        {
            JLabel labelMetricType = new JLabel("Metric type");
            GridBagConstraints gbc_labelMetricType = new GridBagConstraints();
            gbc_labelMetricType.insets = new Insets(0, 0, 5, 5);
            gbc_labelMetricType.gridx = 0;
            gbc_labelMetricType.gridy = 1;
            contentPanel.add(labelMetricType, gbc_labelMetricType);
        }
        {
            comboBoxTypeMetric = new JComboBox<>(AppDefaultConstants.TYPE_METRIC_LIST.toArray());
            GridBagConstraints gbc_comboBoxTypeMetric = new GridBagConstraints();
            gbc_comboBoxTypeMetric.insets = new Insets(0, 0, 5, 0);
            gbc_comboBoxTypeMetric.fill = GridBagConstraints.HORIZONTAL;
            gbc_comboBoxTypeMetric.gridx = 1;
            gbc_comboBoxTypeMetric.gridy = 1;
            contentPanel.add(comboBoxTypeMetric, gbc_comboBoxTypeMetric);
        }
        {
            JLabel labelTransport = new JLabel("Transport");
            GridBagConstraints gbc_labelTransport = new GridBagConstraints();
            gbc_labelTransport.insets = new Insets(0, 0, 5, 5);
            gbc_labelTransport.gridx = 0;
            gbc_labelTransport.gridy = 2;
            contentPanel.add(labelTransport, gbc_labelTransport);
        }
        {
            comboBoxTransport = new JComboBox<>(AppDefaultConstants.TRANSPORT_LIST.toArray());
            GridBagConstraints gbc_comboBoxTransport = new GridBagConstraints();
            gbc_comboBoxTransport.insets = new Insets(0, 0, 5, 0);
            gbc_comboBoxTransport.fill = GridBagConstraints.HORIZONTAL;
            gbc_comboBoxTransport.gridx = 1;
            gbc_comboBoxTransport.gridy = 2;
            contentPanel.add(comboBoxTransport, gbc_comboBoxTransport);
        }
        {
            JLabel labelPeriod = new JLabel("Period");
            GridBagConstraints gbc_labelPeriod = new GridBagConstraints();
            gbc_labelPeriod.insets = new Insets(0, 0, 5, 5);
            gbc_labelPeriod.gridx = 0;
            gbc_labelPeriod.gridy = 3;
            contentPanel.add(labelPeriod, gbc_labelPeriod);
        }
        {
            comboBoxPeriod = new JComboBox<>(AppDefaultConstants.PERIOD_LIST.toArray());
            GridBagConstraints gbc_comboBoxPeriod = new GridBagConstraints();
            gbc_comboBoxPeriod.insets = new Insets(0, 0, 5, 0);
            gbc_comboBoxPeriod.fill = GridBagConstraints.HORIZONTAL;
            gbc_comboBoxPeriod.gridx = 1;
            gbc_comboBoxPeriod.gridy = 3;
            contentPanel.add(comboBoxPeriod, gbc_comboBoxPeriod);
        }
        {
            JLabel labelRefreshInterval = new JLabel("Refresh Interval");
            GridBagConstraints gbc_labelRefreshInterval = new GridBagConstraints();
            gbc_labelRefreshInterval.anchor = GridBagConstraints.EAST;
            gbc_labelRefreshInterval.insets = new Insets(0, 0, 0, 5);
            gbc_labelRefreshInterval.gridx = 0;
            gbc_labelRefreshInterval.gridy = 4;
            contentPanel.add(labelRefreshInterval, gbc_labelRefreshInterval);
        }
        {
            comboBoxRefreshInterval = new JComboBox<>(
                    AppDefaultConstants.REFRESH_INTERVAL_LIST.toArray());
            GridBagConstraints gbc_comboBoxRefreshInterval = new GridBagConstraints();
            gbc_comboBoxRefreshInterval.fill = GridBagConstraints.HORIZONTAL;
            gbc_comboBoxRefreshInterval.gridx = 1;
            gbc_comboBoxRefreshInterval.gridy = 4;
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
                        metric.setTitle(textFieldTitle.getText());
                        metric.setMetricType(
                                TypeMetric.values()[comboBoxTypeMetric.getSelectedIndex()]);
                        metric.setTransport(
                                Transport.values()[comboBoxTransport.getSelectedIndex()]);
                        metric.setPeriod(Period.values()[comboBoxPeriod.getSelectedIndex()]);
                        metric.setRefreshInterval(RefreshInterval.values()[comboBoxRefreshInterval
                                .getSelectedIndex()]);

                        try {
                            ConfigEditor.updateConfig();
                        } catch (ConfigEditorException e) {
                            e.printStackTrace();
                        }

                        iconPanel.setOptions();
                        dispose();
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
                        dispose();
                    }
                });
            }
        }
        {
            textFieldTitle.setText(metric.getTitle());
            comboBoxTypeMetric.setSelectedIndex(metric.getMetricType().ordinal());
            comboBoxTransport.setSelectedIndex(metric.getTransport().ordinal());
            comboBoxPeriod.setSelectedIndex(metric.getPeriod().ordinal());
            comboBoxRefreshInterval.setSelectedIndex(metric.getRefreshInterval().ordinal());
        }
    }

}
