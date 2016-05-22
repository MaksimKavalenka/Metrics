package by.training.transport.soap;

import java.util.Date;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import by.training.bean.metric.Metric;

@WebService
@SOAPBinding(style = Style.RPC)
public interface SOAPWebServiceInterface {

    @WebMethod
    Metric getLast(String metricType);

    @WebMethod
    Metric[] getList(String metricType, Date from, Date to);

}
