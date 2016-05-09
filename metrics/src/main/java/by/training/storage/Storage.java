package by.training.storage;

import static by.training.exception.StorageException.*;

import java.util.Date;
import java.util.List;
import java.util.NavigableSet;

import org.codehaus.jettison.json.JSONException;

import by.training.bean.element.OptionsElement;
import by.training.bean.element.PeriodElement;
import by.training.bean.element.TransportElement;
import by.training.bean.metric.Metric;
import by.training.bean.options.MetricType;
import by.training.bean.set.BoundedTreeSet;
import by.training.dao.TransportDAO;
import by.training.editor.ConfigEditor;
import by.training.exception.ConfigEditorException;
import by.training.exception.StorageException;
import by.training.window.ResourceIsNotAvailableWindow;

public class Storage implements Runnable {

    private static final int     MAX_COUNT = 604800;

    private boolean              active;

    private OptionsElement       options;
    private TransportElement     transportElement;
    private MetricType           metricType;
    private TransportDAO         dao;

    private NavigableSet<Metric> storage;

    public Storage(final OptionsElement options) throws StorageException {
        this.options = options;
        transportElement = new TransportElement(options.getTransportElement().getTransport(),
                options.getTransportElement().getAddress());
        metricType = options.getMetricTypeElement().getMetricType();
        dao = options.getTransportElement().createDAO();
        storage = new BoundedTreeSet<>(MAX_COUNT);
        getList();
    }

    public Metric getLast() throws StorageException {
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
                synchronized (storage) {
                    set.addAll(storage.tailSet(new Metric(periodElement.getPeriod().getDate(), 0),
                            true));
                }
                break;
            case CUSTOM:
                checkAndLoad(periodElement.getFrom());
                synchronized (storage) {
                    set.addAll(storage.subSet(new Metric(periodElement.getFrom(), 0), true,
                            new Metric(periodElement.getTo(), 0), true));
                }
                break;
        }

        return set;
    }

    public void refresh() throws StorageException {
        boolean change = false;
        MetricType metricType = options.getMetricTypeElement().getMetricType();
        TransportElement transportElement = options.getTransportElement();

        if (this.transportElement.getTransport() != transportElement.getTransport()) {
            synchronized (dao) {
                dao.close();
                dao = transportElement.createDAO();
            }
            this.transportElement.setTransport(transportElement.getTransport());
            change = true;

            synchronized (this) {
                notify();
            }
        }

        if (!change && !this.transportElement.getAddress().equals(transportElement.getAddress())) {
            synchronized (dao) {
                while (!dao.setAddress(transportElement.getAddress())) {
                    ResourceIsNotAvailableWindow.createDialog(transportElement.getAddress(),
                            this.transportElement.getAddress());
                    transportElement.setAddress(ResourceIsNotAvailableWindow.getAddress());
                }
            }
            this.transportElement.setAddress(transportElement.getAddress());

            try {
                ConfigEditor.updateConfig();
            } catch (ConfigEditorException e) {
                throw new StorageException(e.getMessage());
            }

            synchronized (this) {
                notify();
            }
        }

        if (this.metricType != metricType) {
            synchronized (storage) {
                storage.clear();
            }
            this.metricType = metricType;
            loadAfter(getLast().getDate());

            synchronized (this) {
                notify();
            }
        }
    }

    private void checkAndLoad(final Date from) throws StorageException {
        if (storage.isEmpty()) {
            loadAfter(from);
        } else if (storage.first().getDate().compareTo(from) > 0) {
            loadBefore(from);
        } else {
            loadAfter(from);
        }
    }

    private void loadBefore(final Date from) throws StorageException {
        List<Metric> list;
        try {
            synchronized (dao) {
                list = dao.getList(metricType, from, storage.first().getDate());
            }
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
            synchronized (dao) {
                list = dao.getList(metricType, from, new Date(0));
            }
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
                checkAndLoad(getLast().getDate());
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
