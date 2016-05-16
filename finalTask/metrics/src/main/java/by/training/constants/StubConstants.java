package by.training.constants;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import by.training.bean.metric.Metric;

public abstract class StubConstants {

    public static final Metric       DEFAULT_VALUE = new Metric(new Date(0), 0);
    public static final List<Metric> DEFAULT_LIST  = new ArrayList<>(0);

}
