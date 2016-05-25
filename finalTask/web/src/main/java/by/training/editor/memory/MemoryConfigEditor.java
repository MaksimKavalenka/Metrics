package by.training.editor.memory;

import java.util.List;

import by.training.bean.Config;
import by.training.dao.IConfigDAO;
import by.training.memory.Memory;

public class MemoryConfigEditor implements IConfigDAO {

    @Override
    public void addConfig(final String name, final String description,
            final List<Integer> widgetIds) {
        Config config = new Config(Memory.getConfigLastId(), name, description, widgetIds);
        Memory.getConfigs().add(config);
        Memory.incConfigLastId();
    }

    @Override
    public List<Config> getAll() {
        return Memory.getConfigs();
    }

    @Override
    public void close() {
    }

}
