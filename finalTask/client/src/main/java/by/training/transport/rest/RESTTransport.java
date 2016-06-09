package by.training.transport.rest;

import static by.training.constants.ResponseStatus.*;
import static by.training.constants.StubConstants.*;

import java.util.Date;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONException;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse.Status;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;

import by.training.bean.element.ParametersElement;
import by.training.bean.metric.Metric;
import by.training.constants.ResponseStatus;
import by.training.dao.TransportDAO;
import by.training.editor.TransportEditor;
import by.training.options.MetricType;
import by.training.parser.JSONParser;

public class RESTTransport implements TransportDAO {

    private ResponseStatus status = NOT_FOUND;

    private Client         client;
    private WebResource    resource;

    public RESTTransport(final ParametersElement parameters) {
        client = Client.create();
        setParameters(parameters);
    }

    @Override
    public Metric getLast(final MetricType metricType) {
        Metric metric = DEFAULT_VALUE;
        String str = "";

        try {
            synchronized (this) {
                str = resource.path(metricType.name()).accept(MediaType.APPLICATION_JSON)
                        .get(String.class);
            }
            status = OK;
            try {
                metric = JSONParser.parseLineJSON(str);
            } catch (JSONException e) {
                status = SERVICE_UNAVAILABLE;
            }
        } catch (UniformInterfaceException | ClientHandlerException e) {
            status = NOT_FOUND;
        }

        return metric;
    }

    @Override
    public List<Metric> getList(final MetricType metricType, final Date from, final Date to) {
        List<Metric> list = DEFAULT_LIST;
        String str = "";

        try {
            synchronized (this) {
                str = resource.path(metricType.name() + "/" + from.getTime() + "_" + to.getTime())
                        .accept(MediaType.APPLICATION_JSON).get(String.class);
            }
            status = OK;
            try {
                list = JSONParser.parseArrayJSON(str);
            } catch (JSONException e) {
                status = SERVICE_UNAVAILABLE;
            }
        } catch (UniformInterfaceException | ClientHandlerException e) {
            status = NOT_FOUND;
        }

        return list;
    }

    @Override
    public void setParameters(final ParametersElement parameters) {
        try {
            synchronized (this) {
                resource = client.resource(TransportEditor.getBaseUri(parameters.getAddress()));
            }
            status = OK;
            if (resource.head().getStatus() == Status.NOT_FOUND.getStatusCode()) {
                status = NOT_FOUND;
            }
        } catch (ClientHandlerException e) {
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
