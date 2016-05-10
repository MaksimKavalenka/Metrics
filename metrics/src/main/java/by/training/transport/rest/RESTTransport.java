package by.training.transport.rest;

import static by.training.exception.HTTPException.*;

import java.util.ArrayList;
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

import by.training.bean.metric.Metric;
import by.training.bean.options.MetricType;
import by.training.dao.TransportDAO;
import by.training.editor.TransportEditor;
import by.training.exception.HTTPException;
import by.training.parser.JSONParser;

public class RESTTransport implements TransportDAO {

    private static final List<Metric> EMPTY = new ArrayList<>(0);

    private Object                    block;
    private HTTPException             status;
    private Client                    client;
    private WebTarget                 service;

    public RESTTransport(final String address) {
        block = new Object();
        client = ClientBuilder.newClient(new ClientConfig());
        setAddress(address);
    }

    @Override
    public Metric getLast(final MetricType typeMetric) throws JSONException {
        String str;
        synchronized (block) {
            str = service.path(typeMetric.name()).request().accept(MediaType.APPLICATION_JSON)
                    .get(String.class);
        }
        return JSONParser.parseLineJSON(str);
    }

    @Override
    public List<Metric> getList(final MetricType typeMetric, final Date from, final Date to)
            throws JSONException {
        List<Metric> list = EMPTY;

        synchronized (block) {
            try {
                String str = service
                        .path(typeMetric.name() + "/" + from.getTime() + "_" + to.getTime())
                        .request().accept(MediaType.APPLICATION_JSON).get(String.class);
                list = JSONParser.parseArrayJSON(str);
            } catch (ProcessingException | ServiceUnavailableException e) {
                status = HTTP_503;
            }
        }

        return list;
    }

    @Override
    public void setAddress(final String address) {
        synchronized (block) {
            service = client.target(TransportEditor.getBaseURI(address));
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
