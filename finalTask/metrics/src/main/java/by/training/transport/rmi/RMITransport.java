package by.training.transport.rmi;

import static by.training.exception.HTTPException.*;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Date;
import java.util.List;

import org.codehaus.jettison.json.JSONException;

import by.training.bean.element.ParametersElement;
import by.training.bean.metric.Metric;
import by.training.dao.TransportDAO;
import by.training.exception.HTTPException;
import by.training.options.MetricType;

public class RMITransport implements TransportDAO {

    private RMIWebServiceInterface service;

    public RMITransport(final ParametersElement parameters) {
        setParameters(parameters);
    }

    @Override
    public Metric getLast(final MetricType metricType) throws JSONException {
        Metric metric = null;
        try {
            metric = service.getLast(metricType.name());
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return metric;
    }

    @Override
    public List<Metric> getList(final MetricType metricType, final Date from, final Date to)
            throws JSONException {
        List<Metric> list = null;
        try {
            list = service.getList(metricType.name(), from, to);
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public void setParameters(final ParametersElement parameters) {
        try {
            Registry registry = LocateRegistry.getRegistry(parameters.getHost(),
                    Integer.parseInt(parameters.getPort()));
            service = (RMIWebServiceInterface) registry.lookup(parameters.getName());
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public HTTPException getStatus() {
        return HTTP_200;
    }

    @Override
    public void close() {
        // TODO Auto-generated method stub

    }

}
