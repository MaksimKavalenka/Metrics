package by.training.editor.memory;

import java.util.LinkedList;
import java.util.List;

import by.training.bean.DashboardWidget;
import by.training.dao.IDashboardWidgetDAO;
import by.training.memory.Memory;

public class MemoryDashboardWidgetEditor implements IDashboardWidgetDAO {

    @Override
    public void addDashboardWidget(final int idDashboard, final int idWidget) {
        DashboardWidget dashboardWidget = new DashboardWidget(idDashboard, idWidget);
        synchronized (MemoryDashboardWidgetEditor.class) {
            Memory.getDashboardWidgets().add(dashboardWidget);
        }
    }

    @Override
    public List<Integer> getWidgetIds(final int idDashboard) {
        List<Integer> list = new LinkedList<>();
        synchronized (MemoryDashboardWidgetEditor.class) {
            for (DashboardWidget dashboardWidget : Memory.getDashboardWidgets()) {
                if (dashboardWidget.getIdDashboard() == idDashboard) {
                    list.add(dashboardWidget.getIdWidget());
                }
            }
        }
        return list;
    }

    @Override
    public void deleteDashboard(final int idDashboard) {
        synchronized (MemoryDashboardWidgetEditor.class) {
            for (DashboardWidget dashboardWidget : Memory.getDashboardWidgets()) {
                if (dashboardWidget.getIdDashboard() == idDashboard) {
                    Memory.getDashboards().remove(dashboardWidget);
                    return;
                }
            }
        }
    }

    @Override
    public void deleteWidget(final int idWidget) {
        synchronized (MemoryDashboardWidgetEditor.class) {
            for (DashboardWidget dashboardWidget : Memory.getDashboardWidgets()) {
                if (dashboardWidget.getIdWidget() == idWidget) {
                    Memory.getDashboards().remove(dashboardWidget);
                }
            }
        }
    }

    @Override
    public void close() {
    }

}
