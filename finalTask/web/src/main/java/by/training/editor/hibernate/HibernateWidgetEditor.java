package by.training.editor.hibernate;

import java.util.Date;
import java.util.List;

import org.hibernate.Transaction;

import by.training.bean.Widget;
import by.training.dao.IWidgetDAO;
import by.training.database.structure.DatabaseTables;
import by.training.options.MetricType;
import by.training.options.Period;
import by.training.options.RefreshInterval;

public class HibernateWidgetEditor extends AbstractHibernateEditor implements IWidgetDAO {

    public HibernateWidgetEditor() {
        super();
    }

    @Override
    public void addWidget(final String name, final MetricType metricType,
            final RefreshInterval refreshInterval, final Period period, final Date fromDate,
            final Date toDate) {
        Widget widget = new Widget();
        widget.setName(name);
        widget.setMetricType(metricType);
        widget.setRefreshInterval(refreshInterval);
        widget.setPeriod(period);
        widget.setFromDate(fromDate);
        widget.setToDate(toDate);

        synchronized (HibernateWidgetEditor.class) {
            Transaction transaction = session.beginTransaction();
            session.save(widget);
            transaction.commit();
        }
    }

    @Override
    public void modifyWidget(final int id, final String name, final MetricType metricType,
            final RefreshInterval refreshInterval, final Period period, final Date fromDate,
            final Date toDate) {
        Widget widget = getWidget(id);
        widget.setName(name);
        widget.setMetricType(metricType);
        widget.setRefreshInterval(refreshInterval);
        widget.setPeriod(period);
        widget.setFromDate(fromDate);
        widget.setToDate(toDate);

        synchronized (HibernateWidgetEditor.class) {
            Transaction transaction = session.beginTransaction();
            session.update(widget);
            transaction.commit();
        }
    }

    @Override
    public Widget getWidget(final int id) {
        return (Widget) session.get(Widget.class, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Widget> getAllWidgets() {
        return session.createQuery("from " + DatabaseTables.WIDGET.toString()).list();
    }

    @Override
    public void deleteWidget(final int id) {
        Widget widget = getWidget(id);
        synchronized (HibernateWidgetEditor.class) {
            Transaction transaction = session.beginTransaction();
            session.delete(widget);
            transaction.commit();
        }
    }

    @Override
    public void close() {
        super.close();
    }

}
