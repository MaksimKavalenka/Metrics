package by.training.editor.memory;

import java.util.Iterator;
import java.util.List;

import by.training.bean.Dashboard;
import by.training.bean.Widget;
import by.training.dao.IDashboardDAO;
import by.training.data.memory.Memory;

public class MemoryDashboardEditor implements IDashboardDAO {

    @Override
    public void addDashboard(final String name, final String description,
            final List<Widget> widgets) {
        synchronized (MemoryDashboardEditor.class) {
            Dashboard dashboard = new Dashboard(Memory.getDashboardLastId(), name, description,
                    widgets);
            Memory.getDashboards().add(dashboard);
            Memory.incDashboardLastId();
        }
    }

    @Override
    public void modifyDashboard(final int id, final String name, final String description,
            final List<Widget> widgets) {
        Dashboard dashboard;
        synchronized (MemoryDashboardEditor.class) {
            dashboard = getDashboard(id);
            dashboard.setName(name);
            dashboard.setDescription(description);
            dashboard.setWidgets(widgets);
        }
    }

    @Override
    public Dashboard getDashboard(final int id) {
        Iterator<Dashboard> iterator = Memory.getDashboards().iterator();
        while (iterator.hasNext()) {
            Dashboard dashboard = iterator.next();
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
        Dashboard dashboard;
        synchronized (MemoryDashboardEditor.class) {
            dashboard = getDashboard(id);
            Memory.getDashboards().remove(dashboard);
        }
    }

    @Override
    public void close() {
    }

}
