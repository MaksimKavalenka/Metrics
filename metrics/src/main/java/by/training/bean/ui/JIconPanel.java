package by.training.bean.ui;

import javax.swing.JLabel;
import javax.swing.JPanel;

import by.training.bean.element.MetricElement;
import by.training.bean.metric.TypeMetric;
import by.training.window.ReminderWindow;

public class JIconPanel extends JPanel implements Runnable {

    private static final long serialVersionUID = 3227064233448063007L;

    private static int        count            = 0;

    private MetricElement     metric;
    private int               refreshInterval;

    private boolean           active;
    private int               index;
    private JWidgetPanel      widgetPanel;

    private JLabel            labelIconTitle;
    private JLabel            labelIconCurrentValue;

    public JIconPanel(final MetricElement metric) {
        super();
        this.metric = metric;

        index = count++;
    }

    public static void reduceCountIcon() {
        --count;
    }

    public MetricElement getMetric() {
        return metric;
    }

    public void setMetric(final MetricElement metric) {
        this.metric = metric;
    }

    public boolean setWidgetPanel(final JWidgetPanel widgetPanel) {
        if (this.widgetPanel == null) {
            this.widgetPanel = widgetPanel;
            return true;
        } else {
            ReminderWindow.createDialog(ReminderWindow.DISPLAY);
            return false;
        }
    }

    public int getIndex() {
        return index;
    }

    public void reduceIndex() {
        --index;

    }

    public void init() {
        labelIconTitle = (JLabel) getComponent(0);
        labelIconCurrentValue = (JLabel) getComponent(1);
    }

    public void runChart() {
        active = true;
        new Thread(this).start();
    }

    public void terminateChart() {
        active = false;
    }

    @Override
    public void run() {
        while (active) {
            TypeMetric type = metric.getMetricType();
            double value = metric.getTransport().getTransporter().getValue(type);
            labelIconCurrentValue.setText(String.valueOf(value));

            if (widgetPanel != null) {
                widgetPanel.refresh(value);
            }

            try {
                Thread.sleep(refreshInterval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void setOptions() {
        String title = metric.getTitle();

        labelIconTitle.setText(title);
        refreshInterval = metric.getRefreshInterval().getValue();

        if (widgetPanel != null) {
            widgetPanel.setOptions(title, metric.getMetricType().getUnit());
        }
    }

    public boolean isDependency() {
        if (widgetPanel != null) {
            return true;
        }
        return false;
    }

    public void removeDependency() {
        widgetPanel = null;
    }

}
