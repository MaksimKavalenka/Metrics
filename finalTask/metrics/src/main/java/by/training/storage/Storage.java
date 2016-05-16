package by.training.storage;

import static by.training.exception.StorageException.*;
import static by.training.exception.HTTPException.*;

import java.util.Date;
import java.util.List;
import java.util.NavigableSet;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.codehaus.jettison.json.JSONException;

import by.training.bean.concurrent.ConcurrentBoundedSkipListSet;
import by.training.bean.element.ParametersElement;
import by.training.bean.metric.Metric;
import by.training.dao.TransportDAO;
import by.training.exception.HTTPException;
import by.training.exception.StorageException;
import by.training.options.MetricType;
import by.training.options.RefreshInterval;
import by.training.options.Transport;

public class Storage implements Runnable {

    private static final int                     MAX_COUNT = 604800;

    private final ScheduledExecutorService       executor  = Executors.newScheduledThreadPool(1);

    private ScheduledFuture<?>                   future;
    private MetricType                           metricType;
    private TransportDAO                         dao;

    private ConcurrentBoundedSkipListSet<Metric> storage;

    public Storage(final MetricType metricType) {
        this.metricType = metricType;
        storage = new ConcurrentBoundedSkipListSet<>(MAX_COUNT);
        future = executor.scheduleWithFixedDelay(this, 0, RefreshInterval.SECOND_5.getValue(),
                TimeUnit.MILLISECONDS);
    }

    public Metric getLast() throws StorageException {
        if (storage.isEmpty()) {
            try {
                synchronized (dao) {
                    storage.add(dao.getLast(metricType));
                }
            } catch (JSONException e) {
                throw new StorageException(LOAD_LAST_ERROR);
            }
        }
        return storage.last();
    }

    public NavigableSet<Metric> getList(final Date from) throws StorageException {
        checkAndLoad(from);
        return storage.tailSet(new Metric(from, 0), true);
    }

    public NavigableSet<Metric> getList(final Date from, final Date to) throws StorageException {
        checkAndLoad(from, to);
        return storage.subSet(new Metric(from, 0), true, new Metric(to, 0), true);
    }

    public void createTransport(final Transport transport, final ParametersElement parameters) {
        dao = transport.createDAO(parameters);
    }

    public void setTransport(final Transport transport, final ParametersElement parameters) {
        synchronized (dao) {
            dao.close();
            dao = transport.createDAO(parameters);
        }
    }

    public void setParameters(final ParametersElement parameters) {
        synchronized (dao) {
            dao.setParameters(parameters);
        }
    }

    public void setMetricType(final MetricType metricType) {
        synchronized (dao) {
            storage.clear();
            this.metricType = metricType;
        }
    }

    private void checkAndLoad(final Date from) throws StorageException {
        if (dao.getStatus() != HTTP_404) {
            if (storage.isEmpty()) {
                storage.addAll(loadAfter());
            } else if (storage.first().getDate().compareTo(from) > 0) {
                storage.addAll(loadBefore(from));
            } else {
                storage.addAll(loadAfter());
            }
        }
    }

    private void checkAndLoad(final Date from, final Date to) throws StorageException {
        if (dao.getStatus() != HTTP_404) {
            if (storage.isEmpty()) {
                storage.addAll(loadBetween(from, to));
            } else if (storage.first().getDate().compareTo(from) > 0) {
                if (storage.first().getDate().compareTo(to) <= 0) {
                    storage.addAll(loadBefore(from));
                } else {
                    storage.clear();
                    storage.addAll(loadBetween(from, to));
                }
            }
            if (storage.last().getDate().compareTo(to) < 0) {
                if (storage.last().getDate().compareTo(from) >= 0) {
                    storage.addAll(loadAfter());
                } else {
                    storage.clear();
                    storage.addAll(loadBetween(from, to));
                }
            }
        }
    }

    private List<Metric> loadBefore(final Date from) throws StorageException {
        try {
            synchronized (dao) {
                return dao.getList(metricType, from, storage.first().getDate());
            }
        } catch (JSONException e) {
            throw new StorageException(LOAD_BEFORE_ERROR);
        }
    }

    private List<Metric> loadAfter() throws StorageException {
        try {
            synchronized (dao) {
                return dao.getList(metricType, storage.last().getDate(), new Date(0));
            }
        } catch (JSONException e) {
            throw new StorageException(LOAD_AFTER_ERROR);
        }
    }

    private List<Metric> loadBetween(final Date from, final Date to) throws StorageException {
        try {
            synchronized (dao) {
                return dao.getList(metricType, from, to);
            }
        } catch (JSONException e) {
            throw new StorageException(LOAD_BETWEEN_ERROR);
        }
    }

    public HTTPException getStatus() {
        return dao.getStatus();
    }

    public void deactivate() {
        future.cancel(false);
        dao.close();
    }

    @Override
    public void run() {
        try {
            checkAndLoad(getLast().getDate());
        } catch (StorageException e) {
            e.printStackTrace();
        }
    }

}
