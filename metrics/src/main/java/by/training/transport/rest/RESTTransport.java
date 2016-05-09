package by.training.transport.rest;

import java.net.URI;
import java.util.Date;
import java.util.List;

import javax.ws.rs.NotAllowedException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.codehaus.jettison.json.JSONException;
import org.glassfish.jersey.client.ClientConfig;

import by.training.bean.metric.Metric;
import by.training.bean.options.MetricType;
import by.training.dao.TransportDAO;
import by.training.parser.JSONParser;

public class RESTTransport implements TransportDAO {

    private Client    client;
    private WebTarget service;

    public RESTTransport(final String address) {
        client = ClientBuilder.newClient(new ClientConfig());
        service = client.target(getBaseURI(address));
    }

    private URI getBaseURI(final String address) {
        return UriBuilder.fromUri(address).build();
    }

    @Override
    public Metric getLast(final MetricType typeMetric) throws JSONException {
        String str;
        synchronized (service) {
            str = service.path(typeMetric.name()).request().accept(MediaType.APPLICATION_JSON)
                    .get(String.class);
        }
        return JSONParser.parseLineJSON(str);
    }

    @Override
    public List<Metric> getList(final MetricType typeMetric, final Date from, final Date to)
            throws JSONException {
        String str = "";
        synchronized (service) {
            str = service.path(typeMetric.name() + "/" + from.getTime() + "_" + to.getTime())
                    .request().accept(MediaType.APPLICATION_JSON).get(String.class);
        }
        return JSONParser.parseArrayJSON(str);
    }

    @Override
    public boolean setAddress(final String address) {
        boolean connect = false;

        synchronized (service) {
            service = client.target(getBaseURI(address));
            try {
                service.path("").request().get(Object.class);
            } catch (NotAllowedException e) {
                connect = true;
            } catch (NotFoundException e) {
                connect = false;
            } /// add java.net.ConnectException
              /// javax.ws.rs.ServiceUnavailableException
        }

        return connect;
    }

    @Override
    public void close() {
        client.close();
    }

}
