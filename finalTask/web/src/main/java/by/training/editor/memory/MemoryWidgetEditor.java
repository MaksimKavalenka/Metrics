package by.training.editor.memory;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import by.training.bean.Widget;
import by.training.dao.IWidgetDAO;
import by.training.data.memory.Memory;
import by.training.options.MetricType;
import by.training.options.Period;
import by.training.options.RefreshInterval;

public class MemoryWidgetEditor implements IWidgetDAO {

    @Override
    public void addWidget(final String name, final MetricType metricType,
            final RefreshInterval refreshInterval, final Period period, final Date fromDate,
            final Date toDate) {
        synchronized (MemoryWidgetEditor.class) {
            Widget widget = new Widget(Memory.getWidgetLastId(), name, metricType, refreshInterval,
                    period, fromDate, toDate);
            Memory.getWidgets().add(widget);
            Memory.incWidgetLastId();
        }
    }

    @Override
    public void modifyWidget(final int id, final String name, final MetricType metricType,
            final RefreshInterval refreshInterval, final Period period, final Date fromDate,
            final Date toDate) {
        synchronized (MemoryWidgetEditor.class) {
            Widget widget = getWidget(id);
            widget.setName(name);
            widget.setMetricType(metricType);
            widget.setRefreshInterval(refreshInterval);
            widget.setPeriod(period);
            widget.setFromDate(fromDate);
            widget.setToDate(toDate);
        }
    }

    @Override
    public Widget getWidget(final int id) {
        Iterator<Widget> iterator = Memory.getWidgets().iterator();
        while (iterator.hasNext()) {
            Widget widget = iterator.next();
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
        Widget widget;
        synchronized (MemoryWidgetEditor.class) {
            widget = getWidget(id);
            Memory.getWidgets().remove(widget);
        }
    }

    @Override
    public void close() {
    }

}
