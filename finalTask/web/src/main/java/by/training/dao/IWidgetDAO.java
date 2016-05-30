package by.training.dao;

import java.util.Date;
import java.util.List;

import by.training.bean.Widget;
import by.training.options.MetricType;
import by.training.options.Period;
import by.training.options.RefreshInterval;

public interface IWidgetDAO extends IDAO {

    void addWidget(String name, MetricType metricType, RefreshInterval refreshInterval,
            Period period, Date from, Date to);

    Widget getWidget(int id);

    List<Widget> getWidgets();

    void deleteWidget(int id);

}
