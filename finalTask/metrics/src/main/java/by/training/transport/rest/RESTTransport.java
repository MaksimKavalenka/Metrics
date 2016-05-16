package by.training.transport.rest;

import static by.training.constants.StubConstants.*;
import static by.training.exception.HTTPException.*;

import java.util.Date;
import java.util.List;

import javax.ws.rs.NotAllowedException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.ServiceUnavailableException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.ResponseProcessingException;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONException;
import org.glassfish.jersey.client.ClientConfig;

import by.training.bean.element.ParametersElement;
import by.training.bean.metric.Metric;
import by.training.dao.TransportDAO;
import by.training.editor.TransportEditor;
import by.training.exception.HTTPException;
import by.training.options.MetricType;
import by.training.parser.JSONParser;

public class RESTTransport implements TransportDAO {

    private HTTPException status = HTTP_404;

    private Client        client;
    private WebTarget     service;

    public RESTTransport(final ParametersElement parameters) {
        client = ClientBuilder.newClient(new ClientConfig());
        setParameters(parameters);
    }

    @Override
    public Metric getLast(final MetricType typeMetric) {
        Metric metric = DEFAULT_VALUE;
        String str;

        try {
            synchronized (this) {
                str = service.path(typeMetric.name()).request().accept(MediaType.APPLICATION_JSON)
                        .get(String.class);
            }
            try {
                metric = JSONParser.parseLineJSON(str);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            status = HTTP_200;
        } catch (ProcessingException | ServiceUnavailableException e) {
            status = HTTP_503;
        }

        return metric;
    }

    @Override
    public List<Metric> getList(final MetricType typeMetric, final Date from, final Date to) {
        String str = "";
        List<Metric> list = DEFAULT_LIST;

        try {
            synchronized (this) {
                str = service.path(typeMetric.name() + "/" + from.getTime() + "_" + to.getTime())
                        .request().accept(MediaType.APPLICATION_JSON).get(String.class);
            }
            try {
                list = JSONParser.parseArrayJSON(str);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            status = HTTP_200;
        } catch (ProcessingException | ServiceUnavailableException e) {
            status = HTTP_503;
        }

        return list;
    }

    @Override
    public void setParameters(final ParametersElement parameters) {
        synchronized (this) {
            service = client.target(TransportEditor.getBaseURI(parameters.getAddress()));
        }

        try {
            service.path("").request().get(Object.class);
        } catch (NotAllowedException e) {
            status = HTTP_200;
        } catch (ResponseProcessingException | NotFoundException e) {
            status = HTTP_404;
        }
    }

    @Override
    public HTTPException getStatus() {
        return status;
    }

    @Override
    public void close() {
        client.close();
    }

}
