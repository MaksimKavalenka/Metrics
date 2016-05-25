package by.training.memory;

import java.util.LinkedList;
import java.util.List;

import by.training.bean.Config;
import by.training.bean.Widget;

public class Memory {

    private static List<Config> configs      = new LinkedList<>();
    private static List<Widget> widgets      = new LinkedList<>();

    private static int          configLastId = 1;
    private static int          widgetLastId = 1;

    public static List<Config> getConfigs() {
        return configs;
    }

    public static void setConfigs(final List<Config> configs) {
        Memory.configs = configs;
    }

    public static List<Widget> getWidgets() {
        return widgets;
    }

    public static void setWidgets(final List<Widget> widgets) {
        Memory.widgets = widgets;
    }

    public static int getConfigLastId() {
        return configLastId;
    }

    public static void incConfigLastId() {
        ++configLastId;
    }

    public static int getWidgetLastId() {
        return widgetLastId;
    }

    public static void incWidgetLastId() {
        ++widgetLastId;
    }

}
