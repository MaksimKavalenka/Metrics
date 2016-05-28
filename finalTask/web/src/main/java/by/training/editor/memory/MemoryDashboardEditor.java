package by.training.editor.memory;

import java.util.List;

import by.training.bean.Dashboard;
import by.training.dao.IDashboardDAO;
import by.training.memory.Memory;

public class MemoryDashboardEditor implements IDashboardDAO {

    @Override
    public int addDashboard(final String name, final String description) {
        Dashboard dashboard = new Dashboard(Memory.getDashboardLastId(), name, description);
        Memory.getDashboards().add(dashboard);
        Memory.incDashboardLastId();
        return Memory.getDashboardLastId();
    }

    @Override
    public List<Dashboard> getDashboards() {
        return Memory.getDashboards();
    }

    @Override
    public void deleteDashboard(final int id) {
        for (Dashboard dashboard : Memory.getDashboards()) {
            if (dashboard.getId() == id) {
                Memory.getDashboards().remove(dashboard);
                return;
            }
        }
    }

    @Override
    public void close() {
    }

}
