package by.training.transport.rest;

import java.net.URI;
import java.util.Date;
import java.util.List;

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
        ClientConfig clientConfig = new ClientConfig();
        client = ClientBuilder.newClient(clientConfig);
        service = client.target(getBaseURI(address));
    }

    private URI getBaseURI(final String address) {
        return UriBuilder.fromUri(address).build();
    }

    @Override
    public Metric getLast(final MetricType typeMetric) throws JSONException {
        String str = service.path("rest/metric/" + typeMetric.name() + "/storage/last").request()
                .accept(MediaType.APPLICATION_JSON).get(String.class);
        return JSONParser.parseLineJSON(str);
        /// add javax.ws.rs.NotFoundException
    }

    @Override
    public synchronized List<Metric> getList(final MetricType typeMetric, final Date from,
            final Date to) throws JSONException {
        String str = service
                .path("rest/metric/" + typeMetric.name() + "/storage/" + from.getTime() + "_"
                        + to.getTime())
                .request().accept(MediaType.APPLICATION_JSON).get(String.class);
        return JSONParser.parseArrayJSON(str);
        /// add javax.ws.rs.NotFoundException
    }

    @Override
    public void close() {
        client.close();
    }

}
