package by.training.bean;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import by.training.bean.metric.Metric;
import by.training.options.MetricType;
import by.training.options.RefreshInterval;
import by.training.storage.Storage;
import by.training.storage.StorageMXBean;

public class MXBean implements Runnable {

    private final ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

    private ScheduledFuture<?>             future;
    private MetricType                     metricType;
    private Storage                        storage;

    public MXBean(final MetricType metricType) {
        this.metricType = metricType;
        storage = new Storage();
        init();

        future = executor.scheduleWithFixedDelay(this, 0, RefreshInterval.SECOND.getValue(),
                TimeUnit.MILLISECONDS);
    }

    public Storage getStorage() {
        return storage;
    }

    private void init() {
        Random random = new Random();
        long ms = System.currentTimeMillis();
        for (int i = 0; i < 3600; i++) {
            ms -= 1000;
            int value = random.nextInt(100);
            storage.add(new Metric(new Date(ms), value));
        }
    }

    public void deactivate() {
        future.cancel(false);
    }

    @Override
    public void run() {
        Metric metric = new Metric(new Date(), StorageMXBean.getValue(metricType));
        storage.add(metric);
    }

}
