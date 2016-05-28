package by.training.dao;

public interface IDashboardWidgetDAO extends IDAO {

    void addDashboardWidget(int idDashboard, int idWidget);

    void deleteDashboard(int idDashboard);

    void deleteWidget(int idWidget);

}
