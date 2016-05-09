package by.training.storage;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.NavigableSet;

import by.training.bean.metric.Metric;
import by.training.bean.set.BoundedTreeSet;

public class Storage {

    private static final int     MAX_COUNT = 604800;

    private NavigableSet<Metric> storage;

    public Storage() {
        storage = new BoundedTreeSet<>(MAX_COUNT);
    }

    public void add(final Metric metric) {
        storage.add(metric);
    }

    public Metric getLast() {
        if (!storage.isEmpty()) {
            return storage.last();
        }
        return new Metric(new Date(), 0);
    }

    public List<Metric> getList(final Date from, final Date to) {
        List<Metric> list = new LinkedList<>();
        if (to.getTime() == 0) {
            list.addAll(storage.tailSet(new Metric(from, 0), true));
        } else {
            list.addAll(storage.subSet(new Metric(from, 0), true, new Metric(to, 0), true));
        }

        return list;
    }

}
