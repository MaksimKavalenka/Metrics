package by.training.transport.rmi;

import static by.training.constants.ResponseStatus.*;
import static by.training.constants.StubConstants.*;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Date;
import java.util.List;

import by.training.bean.element.ParametersElement;
import by.training.bean.metric.Metric;
import by.training.constants.ResponseStatus;
import by.training.dao.TransportDAO;
import by.training.options.MetricType;

public class RMITransport implements TransportDAO {

    private ResponseStatus         status = NOT_FOUND;

    private RMIWebServiceInterface service;

    public RMITransport(final ParametersElement parameters) {
        setParameters(parameters);
    }

    @Override
    public Metric getLast(final MetricType metricType) {
        Metric metric = DEFAULT_VALUE;

        try {
            synchronized (this) {
                metric = service.getLast(metricType.name());
            }
            status = OK;
        } catch (RemoteException e) {
            status = SERVICE_UNAVAILABLE;
        } catch (NullPointerException e) {
            status = NOT_FOUND;
        }

        return metric;
    }

    @Override
    public List<Metric> getList(final MetricType metricType, final Date from, final Date to) {
        List<Metric> list = DEFAULT_LIST;

        try {
            synchronized (this) {
                list = service.getList(metricType.name(), from, to);
            }
            status = OK;
        } catch (RemoteException e) {
            status = SERVICE_UNAVAILABLE;
        } catch (NullPointerException e) {
            status = NOT_FOUND;
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
            status = OK;
        } catch (RemoteException | NotBoundException | IllegalArgumentException e) {
            status = NOT_FOUND;
        }
    }

    @Override
    public ResponseStatus getStatus() {
        return status;
    }

    @Override
    public void close() {
    }

}
