package by.training.transport.soap;

import java.util.LinkedList;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import by.training.bean.metric.Metric;
import by.training.bean.options.MetricType;

@WebService
@SOAPBinding(style = Style.RPC)
public interface WebServiceInterface {

    @WebMethod
    Metric getLast(MetricType metricType);

    @WebMethod
    LinkedList<Metric> getList(MetricType metricType, String from, String to);

}
