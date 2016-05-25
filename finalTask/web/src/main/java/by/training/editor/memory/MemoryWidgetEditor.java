package by.training.editor.memory;

import java.util.List;

import by.training.bean.Widget;
import by.training.dao.IWidgetDAO;
import by.training.memory.Memory;

public class MemoryWidgetEditor implements IWidgetDAO {

    @Override
    public void addWidget(final String title, final int metricType, final int period,
            final int refreshInterval) {
        Widget widget = new Widget(Memory.getWidgetLastId(), title, metricType, period,
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
