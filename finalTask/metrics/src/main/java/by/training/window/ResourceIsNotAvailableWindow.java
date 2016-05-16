package by.training.window;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.SwingConstants;

public class ResourceIsNotAvailableWindow extends JDialog {

    private static final long serialVersionUID = -3222979601104635484L;

    private static boolean    later;

    public static void createDialog() {
        try {
            ResourceIsNotAvailableWindow dialog = new ResourceIsNotAvailableWindow();
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

    public ResourceIsNotAvailableWindow() {
        JPanel contentPanel = new JPanel();
        setBounds(100, 100, 350, 125);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(new GridLayout(0, 1, 0, 0));
        {
            JLabel labelMessage1 = new JLabel("The requested resource is not available.");
            labelMessage1.setHorizontalAlignment(SwingConstants.CENTER);
            contentPanel.add(labelMessage1);
        }
        {
            JLabel labelMessage2 = new JLabel("You should change parameters");
            labelMessage2.setHorizontalAlignment(SwingConstants.CENTER);
            contentPanel.add(labelMessage2);
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
                        later = false;
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
                        later = true;
                        dispose();
                    }
                });
            }
        }
    }

    public static boolean isLater() {
        return later;
    }

}
