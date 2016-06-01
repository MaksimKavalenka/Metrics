package by.training.parser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatParser {

    private static final String           DATE_DELIMITER   = "T";
    private static final String           STRING_DELIMITER = " ";

    private static final SimpleDateFormat FORMATTER        = new SimpleDateFormat(
            "yyyy-MM-dd hh:mm:ss");

    public static Date stringToDate(final String date) throws ParseException {
        String[] lines = date.split(DATE_DELIMITER);
        return FORMATTER.parse(lines[0] + STRING_DELIMITER + lines[1]);
    }

    public static String dateToString(final Date date) {
        String[] lines = FORMATTER.format(date).split(STRING_DELIMITER);
        return lines[0] + DATE_DELIMITER + lines[1];
    }

}
