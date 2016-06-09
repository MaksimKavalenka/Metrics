package by.training.transport.jms;

import static by.training.constants.ResponseStatus.*;
import static by.training.constants.JMSKeyConstants.*;
import static by.training.constants.StubConstants.*;

import java.util.Date;
import java.util.List;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;

import by.training.bean.element.ParametersElement;
import by.training.bean.metric.Metric;
import by.training.constants.ResponseStatus;
import by.training.dao.TransportDAO;
import by.training.editor.TransportEditor;
import by.training.options.MetricType;

public class JMSTransport implements TransportDAO {

    private ResponseStatus        status = NOT_FOUND;

    private Connection            connection;
    private Session               session;
    private MessageProducer       producer;
    private ClientMessageListener listener;
    private Destination           destination;

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
            msg.setJMSReplyTo(destination);
            msg.setJMSCorrelationID(TransportEditor.getRandomString());

            synchronized (JMSTransport.class) {
                producer.send(msg);
                metric = listener.getList().get(0);
            }
        } catch (JMSException | IllegalStateException e) {
            status = SERVICE_UNAVAILABLE;
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
            msg.setJMSReplyTo(destination);
            msg.setJMSCorrelationID(TransportEditor.getRandomString());

            synchronized (JMSTransport.class) {
                producer.send(msg);
                list = listener.getList();
            }
        } catch (JMSException | IllegalStateException e) {
            status = SERVICE_UNAVAILABLE;
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

                Queue queue = session.createQueue(SERVER_QUEUE);
                producer = session.createProducer(queue);

                destination = session.createTemporaryQueue();
                session.createConsumer(destination).setMessageListener(listener);

                connection.start();
            }

            status = OK;
        } catch (JMSException | IllegalArgumentException e) {
            status = NOT_FOUND;
        }
    }

    @Override
    public ResponseStatus getStatus() {
        return status;
    }

    @Override
    public void close() {
        try {
            if (producer != null) {
                producer.close();
            }
            if (session != null) {
                session.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

}
