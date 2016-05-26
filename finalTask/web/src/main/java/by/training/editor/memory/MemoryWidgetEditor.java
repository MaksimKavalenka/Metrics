package by.training.editor.memory;

import java.util.List;

import by.training.bean.Widget;
import by.training.dao.IWidgetDAO;
import by.training.memory.Memory;
import by.training.options.MetricType;
import by.training.options.Period;
import by.training.options.RefreshInterval;

public class MemoryWidgetEditor implements IWidgetDAO {

    @Override
    public void addWidget(final String name, final MetricType metricType, final Period period,
            final RefreshInterval refreshInterval) {
        Widget widget = new Widget(Memory.getWidgetLastId(), name, metricType, period,
                refreshInterval);
        Memory.getWidgets().add(widget);
        Memory.incWidgetLastId();

    }

    @Override
    public List<Widget> getAll() {
        return Memory.getWidgets();
    }

    @Override
    public void close() {
    }

}
