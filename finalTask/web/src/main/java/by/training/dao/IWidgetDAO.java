package by.training.dao;

import java.util.Date;
import java.util.List;

import by.training.bean.Widget;
import by.training.options.MetricType;
import by.training.options.Period;
import by.training.options.RefreshInterval;

public interface IWidgetDAO extends IDAO {

    void addWidget(String name, MetricType metricType, RefreshInterval refreshInterval,
            Period period, Date fromDate, Date toDate);

    void modifyWidget(int id, String name, MetricType metricType, RefreshInterval refreshInterval,
            Period period, Date fromDate, Date toDate);

    Widget getWidget(int id);

    List<Widget> getAllWidgets();

    void deleteWidget(int id);

}
