package by.training.editor.memory;

import by.training.bean.DashboardWidget;
import by.training.dao.IDashboardWidgetDAO;
import by.training.memory.Memory;

public class MemoryDashboardWidgetEditor implements IDashboardWidgetDAO {

    @Override
    public void addDashboardWidget(final int idDashboard, final int idWidget) {
        DashboardWidget dashboardWidget = new DashboardWidget(idDashboard, idWidget);
        Memory.getDashboardWidgets().add(dashboardWidget);
    }

    @Override
    public void deleteDashboard(final int idDashboard) {
        for (DashboardWidget dashboardWidget : Memory.getDashboardWidgets()) {
            if (dashboardWidget.getIdDashboard() == idDashboard) {
                Memory.getDashboards().remove(dashboardWidget);
            }
        }
    }

    @Override
    public void deleteWidget(final int idWidget) {
        for (DashboardWidget dashboardWidget : Memory.getDashboardWidgets()) {
            if (dashboardWidget.getIdWidget() == idWidget) {
                Memory.getDashboards().remove(dashboardWidget);
            }
        }
    }

    @Override
    public void close() {
    }

}
