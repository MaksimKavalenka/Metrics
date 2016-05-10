package by.training.window;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

public class ResourceIsNotAvailableWindow extends JDialog {

    private static final long serialVersionUID = -3222979601104635484L;

    private static String     resourceAddress;

    private JTextField        textField;

    {
        resourceAddress = "";
    }

    public static void createDialog(final String address, final String successfulAddress) {
        try {
            ResourceIsNotAvailableWindow dialog = new ResourceIsNotAvailableWindow(address,
                    successfulAddress);
            dialog.setTitle("Resource is not available");
            dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            dialog.setLocationRelativeTo(null);
            dialog.setModal(true);
            dialog.setResizable(false);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ResourceIsNotAvailableWindow(final String address, final String successfulAddress) {
        JPanel contentPanel = new JPanel();
        setBounds(100, 100, 500, 200);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        GridBagLayout gbl_contentPanel = new GridBagLayout();
        gbl_contentPanel.columnWidths = new int[] {0, 0};
        gbl_contentPanel.rowHeights = new int[] {0, 0, 0, 0, 0};
        gbl_contentPanel.columnWeights = new double[] {1.0, Double.MIN_VALUE};
        gbl_contentPanel.rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        contentPanel.setLayout(gbl_contentPanel);
        {
            JLabel labelMessage1 = new JLabel("The requested resource is not available.");
            GridBagConstraints gbc_labelMessage1 = new GridBagConstraints();
            gbc_labelMessage1.insets = new Insets(0, 0, 5, 0);
            gbc_labelMessage1.gridx = 0;
            gbc_labelMessage1.gridy = 0;
            contentPanel.add(labelMessage1, gbc_labelMessage1);
        }
        {
            JLabel labelMessage2 = new JLabel("Change the address or set the last successful");
            GridBagConstraints gbc_labelMessage2 = new GridBagConstraints();
            gbc_labelMessage2.insets = new Insets(0, 0, 5, 0);
            gbc_labelMessage2.gridx = 0;
            gbc_labelMessage2.gridy = 1;
            contentPanel.add(labelMessage2, gbc_labelMessage2);
        }
        {
            textField = new JTextField(address);
            GridBagConstraints gbc_textField = new GridBagConstraints();
            gbc_textField.insets = new Insets(0, 0, 5, 0);
            gbc_textField.fill = GridBagConstraints.HORIZONTAL;
            gbc_textField.gridx = 0;
            gbc_textField.gridy = 2;
            contentPanel.add(textField, gbc_textField);
            textField.setColumns(10);
        }
        {
            JLabel labelLastSuccessful = new JLabel(
                    "The last successful address " + successfulAddress);
            GridBagConstraints gbc_labelLastSuccessful = new GridBagConstraints();
            gbc_labelLastSuccessful.anchor = GridBagConstraints.WEST;
            gbc_labelLastSuccessful.gridx = 0;
            gbc_labelLastSuccessful.gridy = 3;
            contentPanel.add(labelLastSuccessful, gbc_labelLastSuccessful);
        }
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                JButton buttonTryAgain = new JButton("Try again");
                buttonTryAgain.setActionCommand("Try again");
                buttonPane.add(buttonTryAgain);
                getRootPane().setDefaultButton(buttonTryAgain);
                buttonTryAgain.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(final ActionEvent event) {
                        resourceAddress = textField.getText();
                        dispose();
                    }
                });
            }
            {
                JButton buttonTryLater = new JButton("Try later");
                buttonPane.add(buttonTryLater);
                buttonTryLater.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(final ActionEvent event) {
                        resourceAddress = "";
                        dispose();
                    }
                });
            }
            {
                JButton buttonLastSuccessful = new JButton("Set the last successful");
                buttonLastSuccessful.setActionCommand("Set the last successful");
                buttonPane.add(buttonLastSuccessful);
                buttonLastSuccessful.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(final ActionEvent event) {
                        resourceAddress = successfulAddress;
                        dispose();
                    }
                });

                if ("".equals(successfulAddress)) {
                    buttonLastSuccessful.setEnabled(false);
                }
            }
        }
    }

    public static String getAddress() {
        return resourceAddress;
    }

}
