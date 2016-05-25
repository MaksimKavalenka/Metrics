package by.training.dao;

import java.util.List;

import by.training.bean.Widget;

public interface IWidgetDAO extends IDAO {

    public void addWidget(String title, int metricType, int period, int refreshInterval);

    public List<Widget> getAll();

}
