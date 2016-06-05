package by.training.dao;

import java.util.List;

import by.training.bean.Dashboard;
import by.training.bean.Widget;

public interface IDashboardDAO extends IDAO {

    void addDashboard(String name, String description, List<Widget> widgets);

    void modifyDashboard(int id, String name, String description, List<Widget> widgets);

    Dashboard getDashboard(int id);

    List<Dashboard> getAllDashboards();

    void deleteDashboard(int id);

}
