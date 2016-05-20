package by.training.transport.jms;

import static by.training.constants.JMSKeyConstants.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;

import by.training.bean.metric.Metric;
import by.training.options.MetricType;
import by.training.storage.StorageMXBean;

public class ServerMessageListener implements MessageListener {

    private Session         session;
    private MessageProducer producer;

    public ServerMessageListener(final Session session) throws JMSException {
        this.session = session;
        Queue queue = session.createQueue("QueueForClient");
        producer = session.createProducer(queue);
    }

    @Override
    public void onMessage(final Message message) {
        try {
            String type = message.getStringProperty(TYPE);

            if (!message.propertyExists(FROM) || !message.propertyExists(TO)) {
                List<Metric> list = new ArrayList<>(1);
                list.add(getLast(type));

                Message msg = session.createObjectMessage((ArrayList<Metric>) list);
                producer.send(msg);
            } else {
                Date from = new Date(message.getLongProperty(FROM));
                Date to = new Date(message.getLongProperty(TO));

                Message msg = session
                        .createObjectMessage(getList(type, from, to));
                producer.send(msg);
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    private Metric getLast(final String metricType) {
        MetricType mType = MetricType.valueOf(metricType);
        return StorageMXBean.getMXBean(mType).getStorage().getLast();
    }

    public LinkedList<Metric> getList(final String metricType, final Date from, final Date to) {
        MetricType mType = MetricType.valueOf(metricType);
        return (LinkedList<Metric>) StorageMXBean.getMXBean(mType).getStorage().getList(from, to);
    }

}
