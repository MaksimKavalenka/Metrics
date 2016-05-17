package by.training.transport.rmi;

import static by.training.constants.StubConstants.*;
import static by.training.exception.HTTPException.*;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Date;
import java.util.List;

import by.training.bean.element.ParametersElement;
import by.training.bean.metric.Metric;
import by.training.dao.TransportDAO;
import by.training.exception.HTTPException;
import by.training.options.MetricType;

public class RMITransport implements TransportDAO {

    private HTTPException          status = HTTP_404;

    private RMIWebServiceInterface service;

    public RMITransport(final ParametersElement parameters) {
        setParameters(parameters);
    }

    @Override
    public Metric getLast(final MetricType metricType) {
        Metric metric = DEFAULT_VALUE;

        try {
            synchronized (this) {
                try {
                    metric = service.getLast(metricType.name());
                } catch (NullPointerException e) {
                    status = HTTP_404;
                }
            }
            status = HTTP_200;
        } catch (RemoteException e) {
            status = HTTP_503;
        }

        return metric;
    }

    @Override
    public List<Metric> getList(final MetricType metricType, final Date from, final Date to) {
        List<Metric> list = DEFAULT_LIST;

        try {
            synchronized (this) {
                try {
                    list = service.getList(metricType.name(), from, to);
                } catch (NullPointerException e) {
                    status = HTTP_404;
                }
            }
            status = HTTP_200;
        } catch (RemoteException e) {
            status = HTTP_503;
        }

        return list;
    }

    @Override
    public void setParameters(final ParametersElement parameters) {
        try {
            Registry registry = LocateRegistry.getRegistry(parameters.getHost(),
                    Integer.parseInt(parameters.getPort()));
            synchronized (this) {
                service = (RMIWebServiceInterface) registry.lookup("metrics/rmi");
            }
            status = HTTP_200;
        } catch (RemoteException | NotBoundException | IllegalArgumentException e) {
            status = HTTP_404;
        }
    }

    @Override
    public HTTPException getStatus() {
        return status;
    }

    @Override
    public void close() {
    }

}
