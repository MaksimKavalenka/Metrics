package by.training.transport.rest;

import java.util.Date;
import java.util.List;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

import by.training.bean.metric.Metric;
import by.training.bean.options.MetricType;

@ApplicationPath("rest")
@Path("/metric")
public class RESTTransport extends Application {

    @GET
    @Path("/{type}")
    @Produces(MediaType.APPLICATION_JSON)
    public Metric getLast(@PathParam("type") final String metricType) {
        return MetricType.valueOf(metricType).getStorageEditor().getLast();
    }

    @GET
    @Path("/{type}/{from}_{to}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Metric> getList(@PathParam("type") final String metricType,
            @PathParam("from") final long from, @PathParam("to") final long to) {
        return MetricType.valueOf(metricType).getStorageEditor().getList(new Date(from),
                new Date(to));
    }

}
