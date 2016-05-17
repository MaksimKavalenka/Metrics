package by.training.parser;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.management.openmbean.CompositeData;

import by.training.bean.metric.Metric;

public class CompositeDataParser {

    public static Metric parseSingleData(final CompositeData compositeData) {
        Date date = (Date) compositeData.get("date");
        double value = (double) compositeData.get("value");
        return new Metric(date, value);
    }

    public static List<Metric> parseArrayDatas(final CompositeData[] arr) {
        List<Metric> list = new LinkedList<>();

        for (CompositeData element : arr) {
            list.add(parseSingleData(element));
        }

        return list;
    }

}
