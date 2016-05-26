package by.training.dao;

import java.util.List;

import by.training.bean.Widget;
import by.training.options.MetricType;
import by.training.options.Period;
import by.training.options.RefreshInterval;

public interface IWidgetDAO extends IDAO {

    public void addWidget(String name, MetricType metricType, Period period,
            RefreshInterval refreshInterval);

    public List<Widget> getAll();

}
