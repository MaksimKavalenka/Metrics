package by.training.editor.memory;

import java.util.List;

import by.training.bean.Dashboard;
import by.training.dao.IDashboardDAO;
import by.training.memory.Memory;

public class MemoryDashboardEditor implements IDashboardDAO {

    @Override
    public void addDashboard(final String name, final String description,
            final List<Integer> widgetIds) {
        Dashboard dashboard = new Dashboard(Memory.getDashboardLastId(), name, description,
                widgetIds);
        Memory.getDashboards().add(dashboard);
        Memory.incDashboardLastId();
    }

    @Override
    public List<Dashboard> getAll() {
        return Memory.getDashboards();
    }

    @Override
    public void close() {
    }

}
