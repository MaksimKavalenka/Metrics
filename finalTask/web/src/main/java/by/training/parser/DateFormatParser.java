package by.training.parser;

import static by.training.constants.MessageConstants.SPECIFIED_DATE_MESSAGE;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import by.training.exception.IllegalDataException;

public abstract class DateFormatParser {

    private static final String           DATE_DELIMITER   = "T";
    private static final String           STRING_DELIMITER = " ";

    private static final SimpleDateFormat FORMATTER        = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss");

    public static Date stringToDate(final String date) throws IllegalDataException {
        try {
            String[] lines = date.split(DATE_DELIMITER);
            return FORMATTER.parse(lines[0] + STRING_DELIMITER + lines[1]);
        } catch (ArrayIndexOutOfBoundsException | ParseException e) {
            throw new IllegalDataException(SPECIFIED_DATE_MESSAGE);
        }
    }

    public static String dateToString(final Date date) {
        String[] lines = FORMATTER.format(date).split(STRING_DELIMITER);
        return lines[0] + DATE_DELIMITER + lines[1];
    }

}
