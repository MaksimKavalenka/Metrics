package by.training.editor.memory;

import java.util.List;

import by.training.bean.Dashboard;
import by.training.dao.IDashboardDAO;
import by.training.dao.IDashboardWidgetDAO;
import by.training.factory.DashboardWidgetFactory;
import by.training.memory.Memory;

public class MemoryDashboardEditor implements IDashboardDAO {

    private final IDashboardWidgetDAO DASHBOARD_WIDGET_DAO;

    {
        DASHBOARD_WIDGET_DAO = DashboardWidgetFactory.getEditor();
    }

    @Override
    public void addDashboard(final String name, final String description,
            final List<Integer> widgetIds) {
        synchronized (MemoryDashboardEditor.class) {
            int idDashboard = Memory.getDashboardLastId();
            Dashboard dashboard = new Dashboard(Memory.getDashboardLastId(), name, description);
            Memory.getDashboards().add(dashboard);
            Memory.incDashboardLastId();
            for (Integer idWidget : widgetIds) {
                DASHBOARD_WIDGET_DAO.addDashboardWidget(idDashboard, idWidget);
            }
        }
    }

    @Override
    public void modifyDashboard(final int id, final String name, final String description,
            final List<Integer> widgetIds) {
        synchronized (MemoryDashboardEditor.class) {
            Dashboard dashboard = getDashboard(id);
            dashboard.setName(name);
            dashboard.setDescription(description);
            DASHBOARD_WIDGET_DAO.deleteDashboard(dashboard.getId());
            for (Integer idWidget : widgetIds) {
                DASHBOARD_WIDGET_DAO.addDashboardWidget(dashboard.getId(), idWidget);
            }
        }
    }

    @Override
    public Dashboard getDashboard(final int id) {
        for (Dashboard dashboard : Memory.getDashboards()) {
            if (dashboard.getId() == id) {
                return dashboard;
            }
        }
        return null;
    }

    @Override
    public List<Dashboard> getAllDashboards() {
        return Memory.getDashboards();
    }

    @Override
    public void deleteDashboard(final int id) {
        synchronized (MemoryDashboardEditor.class) {
            Dashboard dashboard = getDashboard(id);
            Memory.getDashboards().remove(dashboard);
            DASHBOARD_WIDGET_DAO.deleteDashboard(dashboard.getId());
        }
    }

    @Override
    public void close() {
    }

}
