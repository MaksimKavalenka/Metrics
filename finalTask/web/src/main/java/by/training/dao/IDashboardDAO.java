package by.training.dao;

import java.util.List;

import by.training.bean.Dashboard;

public interface IDashboardDAO extends IDAO {

    void addDashboard(String name, String description, List<Integer> widgetIds);

    void modifyDashboard(int id, String name, String description, List<Integer> widgetIds);

    Dashboard getDashboard(int id);

    List<Dashboard> getDashboards();

    void deleteDashboard(int id);

}
