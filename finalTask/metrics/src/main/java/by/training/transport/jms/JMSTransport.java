package by.training.transport.jms;

import static by.training.constants.JMSKeyConstants.*;
import static by.training.constants.StubConstants.*;
import static by.training.exception.HTTPException.*;

import java.util.Date;
import java.util.List;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;

import by.training.bean.element.ParametersElement;
import by.training.bean.metric.Metric;
import by.training.dao.TransportDAO;
import by.training.exception.HTTPException;
import by.training.options.MetricType;

public class JMSTransport implements TransportDAO {

    private HTTPException         status = HTTP_404;

    private Connection            connection;
    private Session               session;
    private MessageProducer       producer;
    private ClientMessageListener listener;

    public JMSTransport(final ParametersElement parameters) {
        listener = new ClientMessageListener();
        setParameters(parameters);
    }

    @Override
    public Metric getLast(final MetricType metricType) {
        Metric metric = DEFAULT_VALUE;

        try {
            Message msg = session.createMessage();
            msg.setStringProperty(TYPE, metricType.name());

            synchronized (this) {
                producer.send(msg);
                while (!listener.isReceived());
                metric = listener.getList().get(0);
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }

        return metric;
    }

    @Override
    public List<Metric> getList(final MetricType metricType, final Date from, final Date to) {
        List<Metric> list = DEFAULT_LIST;

        try {
            Message msg = session.createMessage();
            msg.setStringProperty(TYPE, metricType.name());
            msg.setLongProperty(FROM, from.getTime());
            msg.setLongProperty(TO, to.getTime());

            synchronized (this) {
                producer.send(msg);
                while (!listener.isReceived());
                list = listener.getList();
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public void setParameters(final ParametersElement parameters) {
        try {
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
                    "tcp://" + parameters.getHost() + ":" + parameters.getPort());

            synchronized (this) {
                connection = connectionFactory.createConnection();
                session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

                Queue queue = session.createQueue("QueueForServer");
                producer = session.createProducer(queue);

                queue = session.createQueue("QueueForClient");
                session.createConsumer(queue).setMessageListener(listener);

                connection.start();
            }

            status = HTTP_200;
        } catch (JMSException e) {
            e.printStackTrace();
            // javax.jms.JMSException: Cannot send, channel has already failed: tcp://127.0.0.1:8083
        }
    }

    @Override
    public HTTPException getStatus() {
        return status;
    }

    @Override
    public void close() {
        try {
            session.close();
            if (connection != null) {
                connection.close();
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

}
