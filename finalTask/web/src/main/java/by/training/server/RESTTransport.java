package by.training.server;

import java.net.URI;
import java.util.Date;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;

import by.training.options.MetricType;

public class RESTTransport {

    private static Client    client;
    private static WebTarget service;

    static {
        client = ClientBuilder.newClient(new ClientConfig());
        URI uri = UriBuilder.fromUri("http://localhost:8080/metrics/rest/metric").build();
        service = client.target(uri);
    }

    public static String getList(final MetricType metricType, final Date from, final Date to) {
        synchronized (RESTTransport.class) {
            return service.path(metricType.name() + "/" + from.getTime() + "_" + to.getTime())
                    .request().accept(MediaType.APPLICATION_JSON).get(String.class);
        }
    }

    public static void close() {
        client.close();
    }

}
