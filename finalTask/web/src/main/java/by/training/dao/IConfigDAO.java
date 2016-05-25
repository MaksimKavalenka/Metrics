package by.training.dao;

import java.util.List;

import by.training.bean.Config;

public interface IConfigDAO extends IDAO {

    public void addConfig(String name, String description, List<Integer> widgetIds);

    public List<Config> getAll();

}
