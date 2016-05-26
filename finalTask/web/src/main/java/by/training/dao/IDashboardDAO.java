package by.training.dao;

import java.util.List;

import by.training.bean.Dashboard;

public interface IDashboardDAO extends IDAO {

    public void addDashboard(String name, String description, List<Integer> widgetIds);

    public List<Dashboard> getAll();

}
