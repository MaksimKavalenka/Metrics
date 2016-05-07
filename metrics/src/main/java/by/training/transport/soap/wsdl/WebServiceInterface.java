package by.training.transport.soap.wsdl;

import java.util.LinkedList;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import by.training.bean.metric.Metric;
import by.training.bean.options.MetricType;

@WebService(name = "WebServiceInterface", targetNamespace = "http://soap.transport.training.by/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface WebServiceInterface {

    @WebMethod
    @WebResult(partName = "return")
    public Metric getLast(@WebParam(name = "arg0", partName = "arg0") MetricType arg0);

    @WebMethod
    @WebResult(partName = "return")
    public LinkedList<Metric> getList(@WebParam(name = "arg0", partName = "arg0") MetricType arg0,
            @WebParam(name = "arg1", partName = "arg1") String arg1,
            @WebParam(name = "arg2", partName = "arg2") String arg2);

}
