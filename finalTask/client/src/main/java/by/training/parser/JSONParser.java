package by.training.parser;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import by.training.bean.metric.Metric;

public abstract class JSONParser {

    public static Metric parseLineJSON(final String str) throws JSONException {
        JSONObject obj = new JSONObject(str);

        String ms = obj.getString("date");
        Date date = new Date(Long.valueOf(ms));
        double value = obj.getDouble("value");

        return new Metric(date, value);
    }

    public static List<Metric> parseArrayJSON(final String str) throws JSONException {
        List<Metric> list = new LinkedList<>();
        JSONArray arr = new JSONArray(str);

        for (int i = 0; i < arr.length(); i++) {
            Metric metric = parseLineJSON(arr.getString(i));
            list.add(metric);
        }

        return list;
    }

}
