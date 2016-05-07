package by.training.transport.rest;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Application;

import by.training.bean.options.MetricType;

@ApplicationPath("rest")
@Path("/metric")
public class RESTTransport extends Application {

    @Path("/{type}")
    public MetricType getTypeMetric(@PathParam("type") final String type) {
        return MetricType.valueOf(type);
    }

}
