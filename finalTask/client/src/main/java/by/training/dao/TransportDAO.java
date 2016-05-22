package by.training.dao;

import java.util.Date;
import java.util.List;

import by.training.bean.element.ParametersElement;
import by.training.bean.metric.Metric;
import by.training.exception.HTTPException;
import by.training.options.MetricType;

public interface TransportDAO {

    Metric getLast(MetricType metricType);

    List<Metric> getList(MetricType metricType, Date from, Date to);

    void setParameters(ParametersElement parameters);

    HTTPException getStatus();

    void close();

}
