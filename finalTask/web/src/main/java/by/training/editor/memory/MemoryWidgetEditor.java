package by.training.editor.memory;

import java.util.Date;
import java.util.List;

import by.training.bean.Widget;
import by.training.dao.IDashboardWidgetDAO;
import by.training.dao.IWidgetDAO;
import by.training.factory.DashboardWidgetFactory;
import by.training.memory.Memory;
import by.training.options.MetricType;
import by.training.options.Period;
import by.training.options.RefreshInterval;

public class MemoryWidgetEditor implements IWidgetDAO {

    private final IDashboardWidgetDAO DASHBOARD_WIDGET_DAO;

    {
        DASHBOARD_WIDGET_DAO = DashboardWidgetFactory.getEditor();
    }

    @Override
    public void addWidget(final String name, final MetricType metricType,
            final RefreshInterval refreshInterval, final Period period, final Date from,
            final Date to) {
        synchronized (MemoryWidgetEditor.class) {
            Widget widget = new Widget(Memory.getWidgetLastId(), name, metricType, refreshInterval,
                    period, from, to);
            Memory.getWidgets().add(widget);
            Memory.incWidgetLastId();
        }
    }

    @Override
    public void modifyWidget(final int id, final String name, final MetricType metricType,
            final RefreshInterval refreshInterval, final Period period, final Date from,
            final Date to) {
        synchronized (MemoryWidgetEditor.class) {
            Widget widget = getWidget(id);
            widget.setName(name);
            widget.setMetricType(metricType);
            widget.setRefreshInterval(refreshInterval);
            widget.setPeriod(period);
            widget.setFrom(from);
            widget.setTo(to);
        }
    }

    @Override
    public Widget getWidget(final int id) {
        for (Widget widget : Memory.getWidgets()) {
            if (widget.getId() == id) {
                return widget;
            }
        }
        return null;
    }

    @Override
    public List<Widget> getAllWidgets() {
        return Memory.getWidgets();
    }

    @Override
    public void deleteWidget(final int id) {
        synchronized (MemoryWidgetEditor.class) {
            Widget widget = getWidget(id);
            DASHBOARD_WIDGET_DAO.deleteWidget(widget.getId());
            Memory.getWidgets().remove(widget);
        }
    }

    @Override
    public void close() {
    }

}
