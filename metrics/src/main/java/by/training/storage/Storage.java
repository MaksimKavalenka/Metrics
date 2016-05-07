package by.training.storage;

import static by.training.exception.StorageException.*;

import java.util.Date;
import java.util.List;
import java.util.NavigableSet;

import org.codehaus.jettison.json.JSONException;

import by.training.bean.element.OptionsElement;
import by.training.bean.element.PeriodElement;
import by.training.bean.metric.Metric;
import by.training.bean.options.MetricType;
import by.training.bean.set.BoundedTreeSet;
import by.training.dao.TransportDAO;
import by.training.exception.StorageException;

public class Storage implements Runnable {

    private static final int     MAX_COUNT = 604800;

    private boolean              active;

    private OptionsElement       options;
    private MetricType           metricType;
    private TransportDAO         dao;

    private NavigableSet<Metric> storage;

    public Storage(final OptionsElement options) {
        super();
        this.options = options;
        metricType = options.getMetricTypeElement().getMetricType();
        dao = options.getTransportElement().createDAO();
        storage = new BoundedTreeSet<>(MAX_COUNT);
    }

    public Metric getLast() throws StorageException {
        if (storage.isEmpty()) {
            getList();
        }
        return storage.last();
    }

    public NavigableSet<Metric> getList() throws StorageException {
        NavigableSet<Metric> set = new BoundedTreeSet<>();

        PeriodElement periodElement = options.getPeriodElement();
        switch (periodElement.getPeriod()) {
            case LAST_MINUTES_15:
            case LAST_MINUTES_30:
            case LAST_HOUR:
                checkAndLoad(periodElement.getPeriod().getDate());
                set.addAll(
                        storage.tailSet(new Metric(periodElement.getPeriod().getDate(), 0), true));
                break;
            case CUSTOM:
                checkAndLoad(periodElement.getFrom());
                set.addAll(storage.subSet(new Metric(periodElement.getFrom(), 0), true,
                        new Metric(periodElement.getTo(), 0), true));
                break;
        }

        return set;
    }

    public void refresh() throws StorageException {
        if (metricType != options.getMetricTypeElement().getMetricType()) {
            synchronized (storage) {
                storage.clear();
                metricType = options.getMetricTypeElement().getMetricType();
                loadAfter(getLast().getDate());
            }
        }
    }

    private void checkAndLoad(final Date from) throws StorageException {
        if (!storage.isEmpty()) {
            Metric check = new Metric(from, 0);
            if (storage.first().compareTo(check) > 0) {
                loadBefore(from);
            }
        } else {
            loadAfter(from);
        }
    }

    private void loadBefore(final Date from) throws StorageException {
        List<Metric> list;
        try {
            list = dao.getList(metricType, from, storage.first().getDate());
        } catch (JSONException e) {
            throw new StorageException(LOAD_BEFORE_ERROR);
        }

        synchronized (storage) {
            storage.addAll(list);
        }
    }

    private void loadAfter(final Date from) throws StorageException {
        List<Metric> list;
        try {
            list = dao.getList(metricType, from, new Date(0));
        } catch (JSONException e) {
            throw new StorageException(LOAD_AFTER_ERROR);
        }

        synchronized (storage) {
            storage.addAll(list);
        }
    }

    public void deactivate() {
        active = false;
        dao.close();
    }

    @Override
    public void run() {
        active = true;

        while (active) {
            try {
                loadAfter(getLast().getDate());
            } catch (StorageException e) {
                e.printStackTrace();
            }

            synchronized (this) {
                try {
                    wait(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

}
