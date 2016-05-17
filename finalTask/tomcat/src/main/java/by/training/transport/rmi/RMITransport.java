package by.training.transport.rmi;

import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.util.List;

import by.training.bean.metric.Metric;
import by.training.options.MetricType;
import by.training.storage.StorageMXBean;

public class RMITransport implements RMIWebServiceInterface {

    public static void publish() throws RemoteException, AlreadyBoundException {
        final Registry registry = LocateRegistry.createRegistry(8082);
        final RMIWebServiceInterface service = new RMITransport();
        final Remote stub = UnicastRemoteObject.exportObject(service, 0);
        registry.bind("metrics/rmi", stub);
    }

    @Override
    public Metric getLast(final String metricType) {
        MetricType mType = MetricType.valueOf(metricType);
        return StorageMXBean.getMXBean(mType).getStorage().getLast();
    }

    @Override
    public List<Metric> getList(final String metricType, final Date from, final Date to) {
        MetricType mType = MetricType.valueOf(metricType);
        return StorageMXBean.getMXBean(mType).getStorage().getList(from, to);
    }

}
