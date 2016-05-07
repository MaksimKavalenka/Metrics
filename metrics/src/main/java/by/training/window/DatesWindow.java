package by.training.window;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import com.github.lgooddatepicker.datepicker.DatePickerSettings;
import com.github.lgooddatepicker.datetimepicker.DateTimePicker;
import com.github.lgooddatepicker.timepicker.TimePickerSettings;

import java.awt.GridBagLayout;

import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.SwingConstants;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import javax.swing.BoxLayout;

import java.awt.Color;
import javax.swing.border.MatteBorder;

public class DatesWindow extends JDialog {

    private static final long   serialVersionUID    = -4886662360142292516L;

    private static final String EMPTY_DATE_MESSAGE  = "The dates are not selected";
    private static final String EQUAL_DATES_MESSAGE = "Both dates are equal";
    private static final String MORE_DATE_MESSAGE   = "The date 'From' is more than 'To'";

    private static Date         from;
    private static Date         to;

    public static void createDialog() {
        try {
            DatesWindow dialog = new DatesWindow();
            dialog.setTitle("Dates");
            dialog.setMinimumSize(new Dimension(550, 350));
            dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            dialog.setLocationRelativeTo(null);
            dialog.setModal(true);
            dialog.setResizable(false);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private DatesWindow() {
        DateTimePicker dateTimePickerFrom;
        DateTimePicker dateTimePickerTo;

        JPanel contentPanel = new JPanel();
        setBounds(100, 100, 600, 350);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        GridBagLayout gbl_contentPanel = new GridBagLayout();
        gbl_contentPanel.columnWidths = new int[] {0, 0};
        gbl_contentPanel.rowHeights = new int[] {0, 0, 0, 0};
        gbl_contentPanel.columnWeights = new double[] {1.0, Double.MIN_VALUE};
        gbl_contentPanel.rowWeights = new double[] {0.0, 0.0, 1.0, Double.MIN_VALUE};
        contentPanel.setLayout(gbl_contentPanel);
        {
            JLabel lblNewLabel = new JLabel("Select the dates");
            lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
            GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
            gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
            gbc_lblNewLabel.fill = GridBagConstraints.HORIZONTAL;
            gbc_lblNewLabel.gridx = 0;
            gbc_lblNewLabel.gridy = 0;
            contentPanel.add(lblNewLabel, gbc_lblNewLabel);
        }

        JLabel lblMessage = new JLabel("");
        lblMessage.setForeground(Color.RED);
        lblMessage.setHorizontalAlignment(SwingConstants.CENTER);
        GridBagConstraints gbc_lblMessage = new GridBagConstraints();
        gbc_lblMessage.insets = new Insets(0, 0, 5, 0);
        gbc_lblMessage.gridx = 0;
        gbc_lblMessage.gridy = 1;
        contentPanel.add(lblMessage, gbc_lblMessage);
        {
            JPanel panel = new JPanel();
            GridBagConstraints gbc_panel = new GridBagConstraints();
            gbc_panel.fill = GridBagConstraints.BOTH;
            gbc_panel.gridx = 0;
            gbc_panel.gridy = 2;
            contentPanel.add(panel, gbc_panel);
            panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
            {
                JPanel panel_1 = new JPanel();
                panel_1.setBorder(new MatteBorder(3, 3, 3, 2, new Color(192, 192, 192)));
                panel.add(panel_1);
                GridBagLayout gbl_panel_1 = new GridBagLayout();
                gbl_panel_1.columnWidths = new int[] {0, 0};
                gbl_panel_1.rowHeights = new int[] {0, 0, 0};
                gbl_panel_1.columnWeights = new double[] {1.0, Double.MIN_VALUE};
                gbl_panel_1.rowWeights = new double[] {0.0, 0.0, Double.MIN_VALUE};
                panel_1.setLayout(gbl_panel_1);
                {
                    JLabel lblNewLabel_1 = new JLabel("From");
                    lblNewLabel_1.setBackground(Color.WHITE);
                    lblNewLabel_1.setForeground(Color.BLACK);
                    lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
                    GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
                    gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 0);
                    gbc_lblNewLabel_1.fill = GridBagConstraints.HORIZONTAL;
                    gbc_lblNewLabel_1.gridx = 0;
                    gbc_lblNewLabel_1.gridy = 0;
                    panel_1.add(lblNewLabel_1, gbc_lblNewLabel_1);
                }
                {
                    DatePickerSettings dateSettings = new DatePickerSettings();
                    dateSettings.setInitialDateToToday();

                    TimePickerSettings timeSettings = new TimePickerSettings();
                    timeSettings.setDisplaySpinnerButtons(true);
                    timeSettings.setInitialTimeToNow();

                    dateTimePickerFrom = new DateTimePicker(dateSettings, timeSettings);

                    GridBagConstraints gbc_datePicker = new GridBagConstraints();
                    gbc_datePicker.fill = GridBagConstraints.BOTH;
                    gbc_datePicker.gridx = 0;
                    gbc_datePicker.gridy = 1;
                    panel_1.add(dateTimePickerFrom, gbc_datePicker);

                    if (from != null) {
                        dateTimePickerFrom.setDateTime(dateToLocalDateTime(from));
                    }
                }
            }
            {
                JPanel panel_2 = new JPanel();
                panel_2.setBorder(new MatteBorder(3, 2, 3, 3, new Color(192, 192, 192)));
                panel.add(panel_2);
                GridBagLayout gbl_panel_2 = new GridBagLayout();
                gbl_panel_2.columnWidths = new int[] {0, 0};
                gbl_panel_2.rowHeights = new int[] {0, 0, 0};
                gbl_panel_2.columnWeights = new double[] {1.0, Double.MIN_VALUE};
                gbl_panel_2.rowWeights = new double[] {0.0, 0.0, Double.MIN_VALUE};
                panel_2.setLayout(gbl_panel_2);
                {
                    JLabel lblNewLabel_2 = new JLabel("To");
                    lblNewLabel_2.setBackground(Color.WHITE);
                    lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
                    GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
                    gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 0);
                    gbc_lblNewLabel_2.fill = GridBagConstraints.HORIZONTAL;
                    gbc_lblNewLabel_2.gridx = 0;
                    gbc_lblNewLabel_2.gridy = 0;
                    panel_2.add(lblNewLabel_2, gbc_lblNewLabel_2);
                }
                {
                    DatePickerSettings dateSettings = new DatePickerSettings();
                    dateSettings.setInitialDateToToday();

                    TimePickerSettings timeSettings = new TimePickerSettings();
                    timeSettings.setDisplaySpinnerButtons(true);
                    timeSettings.setInitialTimeToNow();

                    dateTimePickerTo = new DateTimePicker(dateSettings, timeSettings);

                    GridBagConstraints gbc_datePicker = new GridBagConstraints();
                    gbc_datePicker.fill = GridBagConstraints.BOTH;
                    gbc_datePicker.gridx = 0;
                    gbc_datePicker.gridy = 1;
                    panel_2.add(dateTimePickerTo, gbc_datePicker);

                    if (to != null) {
                        dateTimePickerTo.setDateTime(dateToLocalDateTime(to));
                    }
                }
            }
        }
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                JButton buttonOK = new JButton("OK");
                buttonPane.add(buttonOK);
                getRootPane().setDefaultButton(buttonOK);
                buttonOK.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(final ActionEvent event) {
                        Date fromDate = localDateTimeToDate(dateTimePickerFrom.getDateTime());
                        Date toDate = localDateTimeToDate(dateTimePickerTo.getDateTime());

                        if ((fromDate != null) && (toDate != null)) {

                            if (fromDate.getTime() == toDate.getTime()) {
                                lblMessage.setText(EQUAL_DATES_MESSAGE);
                            }

                            if (fromDate.getTime() > toDate.getTime()) {
                                lblMessage.setText(MORE_DATE_MESSAGE);

                                IncorrectInputDatesWindow.createDialog();
                                if (IncorrectInputDatesWindow.isSwap()) {
                                    to = fromDate;
                                    from = toDate;
                                    dispose();
                                }
                            }

                            if (fromDate.getTime() < toDate.getTime()) {
                                from = fromDate;
                                to = toDate;
                                dispose();
                            }

                        } else {
                            lblMessage.setText(EMPTY_DATE_MESSAGE);
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
                        dispose();
                    }
                });
            }
        }
    }

    public static Date getFrom() {
        return from;
    }

    public static void setFrom(final Date fromDate) {
        from = fromDate;
    }

    public static Date getTo() {
        return to;
    }

    public static void setTo(final Date toDate) {
        to = toDate;
    }

    private LocalDateTime dateToLocalDateTime(final Date date) {
        Instant instant = Instant.ofEpochMilli(date.getTime());
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }

    private Date localDateTimeToDate(final LocalDateTime localDateTime) {
        if (localDateTime != null) {
            Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
            return Date.from(instant);
        }

        return null;
    }

}
