package by.training.dao;

import java.util.List;

public interface IDashboardWidgetDAO extends IDAO {

    void addDashboardWidget(int idDashboard, int idWidget);

    List<Integer> getWidgetIds(int idDashboard);

    void deleteDashboard(int idDashboard);

    void deleteWidget(int idWidget);

}
