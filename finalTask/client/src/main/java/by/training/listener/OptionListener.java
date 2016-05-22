package by.training.listener;

import by.training.bean.element.OptionsElement;

public interface OptionListener {

    OptionsElement getOptions();

    boolean isResourceExist();

    void titleChanged();

    void metricTypeChanged();

    void transportChanged();

    void parametersChanged(boolean changed);

    void periodChanged();

    void customPeriodChanged();

    void refreshIntervalChanged();

}
