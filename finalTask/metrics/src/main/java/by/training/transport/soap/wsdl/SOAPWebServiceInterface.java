package by.training.transport.soap.wsdl;

import java.util.Date;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import by.training.bean.metric.Metric;

@WebService(name = "SOAPWebServiceInterface", targetNamespace = "http://soap.transport.training.by/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface SOAPWebServiceInterface {

    @WebMethod
    @WebResult(partName = "return")
    public Metric getLast(@WebParam(name = "arg0", partName = "arg0") String arg0);

    @WebMethod
    @WebResult(partName = "return")
    public Metric[] getList(@WebParam(name = "arg0", partName = "arg0") String arg0,
            @WebParam(name = "arg1", partName = "arg1") Date arg1,
            @WebParam(name = "arg2", partName = "arg2") Date arg2);

}
