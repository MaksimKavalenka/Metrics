package by.training.dao;

import java.util.Date;
import java.util.List;

import org.codehaus.jettison.json.JSONException;

import by.training.bean.element.ParametersElement;
import by.training.bean.metric.Metric;
import by.training.exception.HTTPException;
import by.training.options.MetricType;

public interface TransportDAO {

    Metric getLast(MetricType metricType) throws JSONException;

    List<Metric> getList(MetricType metricType, Date from, Date to) throws JSONException;

    void setParameters(ParametersElement parameters);

    HTTPException getStatus();

    void close();

}
