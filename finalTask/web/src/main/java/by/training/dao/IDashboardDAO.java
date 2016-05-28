package by.training.dao;

import java.util.List;

import by.training.bean.Dashboard;

public interface IDashboardDAO extends IDAO {

    public int addDashboard(String name, String description);

    public List<Dashboard> getDashboards();

    public void deleteDashboard(int id);

}
