package by.training.window;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

public class IncorrectDatesWindow extends JDialog {

    private static final long serialVersionUID = 333083131375570586L;

    private static boolean    swap;

    {
        swap = false;
    }

    public static void createDialog() {
        try {
        	IncorrectDatesWindow dialog = new IncorrectDatesWindow();
            dialog.setTitle("Warning");
            dialog.setMinimumSize(new Dimension(450, 100));
            dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            dialog.setLocationRelativeTo(null);
            dialog.setModal(true);
            dialog.setResizable(false);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private IncorrectDatesWindow() {
        JPanel contentPanel = new JPanel();
        setBounds(100, 100, 450, 100);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setLayout(new FlowLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        {
            JLabel lblNewLabel = new JLabel(
                    "The date 'From' is more than 'To'. Do you want to swap them?");
            contentPanel.add(lblNewLabel);
        }
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                JButton buttonYes = new JButton("Yes");
                buttonPane.add(buttonYes);
                getRootPane().setDefaultButton(buttonYes);
                buttonYes.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(final ActionEvent event) {
                        swap = true;
                        dispose();
                    }
                });
            }
            {
                JButton buttonNo = new JButton("No");
                buttonPane.add(buttonNo);
                buttonNo.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(final ActionEvent event) {
                        swap = false;
                        dispose();
                    }
                });
            }
        }
    }

    public static boolean isSwap() {
        return swap;
    }

}
