package by.training.transport.jms;

import java.util.List;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import by.training.bean.metric.Metric;
import by.training.constants.StubConstants;

public class ClientMessageListener implements MessageListener {

    private boolean      received;
    private List<Metric> list;

    @SuppressWarnings("unchecked")
    @Override
    public synchronized void onMessage(final Message message) {
        list = StubConstants.DEFAULT_LIST;

        try {
            list = (List<Metric>) ((ObjectMessage) message).getObject();
            received = true;
            notify();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public synchronized List<Metric> getList() {
        if (!received) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        received = false;
        return list;
    }

}
