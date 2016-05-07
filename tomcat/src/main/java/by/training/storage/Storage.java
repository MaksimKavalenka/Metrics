package by.training.storage;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.NavigableSet;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import by.training.bean.metric.Metric;
import by.training.bean.set.BoundedTreeSet;

public class Storage {

    private static final int     MAX_COUNT = 604800;

    private NavigableSet<Metric> storage;

    public Storage() {
        super();
        storage = new BoundedTreeSet<>(MAX_COUNT);
    }

    public void add(final Metric metric) {
        storage.add(metric);
    }

    @GET
    @Path("/last")
    @Produces(MediaType.APPLICATION_JSON)
    public Metric getLast() {
        if (!storage.isEmpty()) {
            return storage.last();
        }
        return new Metric(new Date(), 0);
    }

    @GET
    @Path("/{from}_{to}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Metric> getList(@PathParam("from") final String from,
            @PathParam("to") final String to) {
        List<Metric> list = new LinkedList<>();
        Date fromDate = new Date(Long.valueOf(from));
        Date toDate = new Date(Long.valueOf(to));

        if (toDate.getTime() == 0) {
            list.addAll(storage.tailSet(new Metric(fromDate, 0), true));
        } else {
            list.addAll(storage.subSet(new Metric(fromDate, 0), true, new Metric(toDate, 0), true));
        }

        return list;
    }

}
