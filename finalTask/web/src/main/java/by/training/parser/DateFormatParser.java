package by.training.parser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatParser {

    private static final SimpleDateFormat FORMATTER = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");

    public static Date stringToDate(final String date) throws ParseException {
        return FORMATTER.parse(date);
    }

    public static String dateToString(final Date date) {
        return FORMATTER.format(date);
    }

}
