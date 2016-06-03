package by.training.transport.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

import by.training.bean.metric.Metric;

public interface RMIWebServiceInterface extends Remote {

    Metric getLast(String metricType) throws RemoteException;

    List<Metric> getList(String metricType, Date from, Date to) throws RemoteException;

}
