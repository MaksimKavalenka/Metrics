package by.training.editor.hibernate;

import java.util.List;

import org.hibernate.Transaction;

import by.training.bean.Dashboard;
import by.training.bean.Widget;
import by.training.dao.IDashboardDAO;
import by.training.database.structure.DatabaseTables;

public class HibernateDashboardEditor extends AbstractHibernateEditor implements IDashboardDAO {

    public HibernateDashboardEditor() {
        super();
    }

    @Override
    public void addDashboard(final String name, final String description,
            final List<Widget> widgets) {
        Dashboard dashboard = new Dashboard();
        dashboard.setName(name);
        dashboard.setDescription(description);
        dashboard.setWidgets(widgets);

        synchronized (HibernateDashboardEditor.class) {
            Transaction transaction = session.beginTransaction();
            session.save(dashboard);
            transaction.commit();
        }
    }

    @Override
    public void modifyDashboard(final int id, final String name, final String description,
            final List<Widget> widgets) {
        Dashboard dashboard = getDashboard(id);
        dashboard.setName(name);
        dashboard.setDescription(description);
        dashboard.setWidgets(widgets);

        synchronized (HibernateDashboardEditor.class) {
            Transaction transaction = session.beginTransaction();
            session.update(dashboard);
            transaction.commit();
        }
    }

    @Override
    public Dashboard getDashboard(final int id) {
        return (Dashboard) session.get(Dashboard.class, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Dashboard> getAllDashboards() {
        return session.createQuery("from " + DatabaseTables.DASHBOARD.toString()).list();
    }

    @Override
    public void deleteDashboard(final int id) {
        Dashboard dashboard = getDashboard(id);
        synchronized (HibernateDashboardEditor.class) {
            Transaction transaction = session.beginTransaction();
            session.delete(dashboard);
            transaction.commit();
        }
    }

    @Override
    public void close() {
        super.close();
    }

}
