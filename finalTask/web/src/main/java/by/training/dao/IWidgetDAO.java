package by.training.dao;

import java.util.Date;
import java.util.List;

import by.training.bean.Widget;
import by.training.options.MetricType;
import by.training.options.Period;
import by.training.options.RefreshInterval;

public interface IWidgetDAO extends IDAO {

    public void addWidget(String name, MetricType metricType, RefreshInterval refreshInterval,
            Period period, Date from, Date to);

    public Widget getWidget(int id);

    public List<Widget> getWidgets();

    public void deleteWidget(int id);

}
