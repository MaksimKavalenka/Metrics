package by.training.ui;

import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

public class JNumberTextField extends JTextField {

    private static final long serialVersionUID = -3844500739494876383L;

    public JNumberTextField(final int defval, final int size) {
        super("" + defval, size);
    }

    @Override
    protected Document createDefaultModel() {
        return new NumberTextField();
    }

    @Override
    public boolean isValid() {
        try {
            Integer.parseInt(getText());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public int getValue() {
        try {
            return Integer.parseInt(getText());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    class NumberTextField extends PlainDocument {

        private static final long serialVersionUID = -6412616139729164891L;

        @Override
        public void insertString(final int offs, final String str, final AttributeSet a)
                throws BadLocationException {
            if (str == null) {
                return;
            }
            String oldString = getText(0, getLength());
            String newString = oldString.substring(0, offs) + str + oldString.substring(offs);
            try {
                Integer.parseInt(newString + "0");
                super.insertString(offs, str, a);
            } catch (NumberFormatException e) {
            }
        }
    }

}
