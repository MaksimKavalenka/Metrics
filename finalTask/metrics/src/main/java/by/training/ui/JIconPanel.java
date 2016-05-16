package by.training.ui;

import static by.training.constants.AppDefaultConstants.DIMENSION;
import static by.training.exception.HTTPException.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragSource;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

import by.training.bean.element.OptionsElement;
import by.training.bean.element.PeriodElement;
import by.training.bean.element.TransportElement;
import by.training.dragndrop.IconDragGestureListener;
import by.training.listener.OptionListener;
import by.training.listener.OptionWindowMouseListener;
import by.training.options.MetricType;
import by.training.storage.Storage;
import by.training.window.ReminderWindow;

public class JIconPanel extends JPanel implements OptionListener, Runnable {

    private static final long   serialVersionUID = 1104619866571656272L;

    private static final String NO_VALUE         = "no value";

    private static int          count            = 0;

    private int                 index;

    private OptionsElement      options;
    private Storage             storage;
    private JWidgetPanel.Chart  chart;

    private JLabel              labelIconTitle;
    private JLabel              labelIconCurrentValue;

    private boolean             active;
    private boolean             setCustom;

    public JIconPanel(final OptionsElement options) {
        create();
        this.options = options;
        storage = new Storage(options.getMetricTypeElement().getMetricType());
        storage.createTransport(options.getTransportElement().getTransport(),
                options.getTransportElement().getParameters());
        titleChanged();
        metricTypeChanged();
        new Thread(this).start();
    }

    public int getIndex() {
        return index;
    }

    public boolean setChart(final JWidgetPanel.Chart chart) {
        if (this.chart == null) {
            this.chart = chart;
            return true;
        } else {
            ReminderWindow.createDialog(ReminderWindow.DISPLAY);
            return false;
        }
    }

    private void create() {
        setBackground(Color.WHITE);
        setBorder(new MatteBorder(1, 1, 1, 1, new Color(100, 149, 237)));
        setPreferredSize(DIMENSION);
        setLayout(new BorderLayout(0, 0));

        labelIconTitle = new JLabel();
        labelIconTitle.setHorizontalAlignment(SwingConstants.CENTER);
        add(labelIconTitle, BorderLayout.NORTH);

        labelIconCurrentValue = new JLabel();
        labelIconCurrentValue.setHorizontalAlignment(SwingConstants.CENTER);
        add(labelIconCurrentValue, BorderLayout.CENTER);

        addMouseListener(new OptionWindowMouseListener(JIconPanel.this));
        new DragSource().createDefaultDragGestureRecognizer(this, DnDConstants.ACTION_COPY,
                new IconDragGestureListener());

        index = count++;
    }

    @Override
    public void run() {
        active = true;

        while (active) {
            switch (storage.getStatus()) {
                case HTTP_200:
                    callStorage(String.valueOf(storage.getLast().getValue()));
                    break;

                case HTTP_503:
                    callStorage(HTTP_503.getMessage());
                    break;

                case HTTP_404:
                    labelIconCurrentValue.setText(HTTP_404.getMessage());
                    if (chart != null) {
                        chart.setText(HTTP_404.getMessage());
                    }
                    break;
            }

            synchronized (this) {
                try {
                    wait(options.getRefreshInterval().getValue());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void callStorage(final String message) {
        PeriodElement periodElement = options.getPeriodElement();

        switch (periodElement.getPeriod()) {
            case LAST_MINUTES_15:
            case LAST_MINUTES_30:
            case LAST_HOUR:
                labelIconCurrentValue.setText(message);

                if (chart != null) {
                    chart.setText(message);
                    chart.refresh(storage.getList(periodElement.getPeriod().getDate()));
                }
                break;

            case CUSTOM:
                if (!setCustom) {
                    setCustom = true;
                    labelIconCurrentValue.setText(NO_VALUE);

                    if (chart != null) {
                        chart.setText(NO_VALUE);
                        chart.refresh(
                                storage.getList(periodElement.getFrom(), periodElement.getTo()));
                    }
                }
                break;
        }
    }

    public void deactivate() {
        --count;
        active = false;
        storage.deactivate();
    }

    public void reduceIndex() {
        --index;
    }

    public boolean isDependency() {
        if (chart != null) {
            return true;
        }
        return false;
    }

    public void removeDependency() {
        chart = null;
    }

    @Override
    public OptionsElement getOptions() {
        return options;
    }

    @Override
    public boolean isResourceExist() {
        if (storage.getStatus() == HTTP_404) {
            return false;
        }
        return true;
    }

    @Override
    public void titleChanged() {
        String title = options.getTitle();

        labelIconTitle.setText(title);
        if (chart != null) {
            chart.setTitle(title);
        }
    }

    @Override
    public void metricTypeChanged() {
        MetricType metricType = options.getMetricTypeElement().getMetricType();

        setCustom = false;
        storage.setMetricType(metricType);

        if (chart != null) {
            chart.setUnit(metricType.getUnit());
        }

        synchronized (this) {
            notify();
        }
    }

    @Override
    public void transportChanged() {
        TransportElement transportElement = options.getTransportElement();
        storage.setTransport(transportElement.getTransport(), transportElement.getParameters());

        synchronized (this) {
            notify();
        }
    }

    @Override
    public void parametersChanged(final boolean changed) {
        if (!changed || (storage.getStatus() == HTTP_404)) {
            storage.setParameters(options.getTransportElement().getParameters());

            synchronized (this) {
                notify();
            }
        }
    }

    @Override
    public void periodChanged() {
        synchronized (this) {
            notify();
        }
    }

    @Override
    public void customPeriodChanged() {
        setCustom = false;
        synchronized (this) {
            notify();
        }
    }

    @Override
    public void refreshIntervalChanged() {
        synchronized (this) {
            notify();
        }
    }

}
