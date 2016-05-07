package by.training.dao;

import java.util.Date;
import java.util.List;

import org.codehaus.jettison.json.JSONException;

import by.training.bean.metric.Metric;
import by.training.bean.options.MetricType;

public interface TransportDAO {

    Metric getLast(MetricType typeMetric) throws JSONException;

    List<Metric> getList(MetricType typeMetric, Date from, Date to) throws JSONException;

    void close();

}
