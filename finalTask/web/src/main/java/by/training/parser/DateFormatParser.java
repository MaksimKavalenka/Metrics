package by.training.parser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatParser {

    private static final SimpleDateFormat FORMATTER = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");

    public static Date stringToDate(final String value) throws ParseException {
        return FORMATTER.parse(value);
    }

}
