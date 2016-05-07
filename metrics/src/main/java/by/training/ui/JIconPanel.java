package by.training.ui;

import static by.training.constants.AppDefaultConstants.DIMENSION;

import static by.training.exception.JIconPanelException.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragSource;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

import by.training.bean.element.OptionsElement;
import by.training.bean.metric.Metric;
import by.training.dragndrop.IconDragGestureListener;
import by.training.exception.JIconPanelException;
import by.training.exception.StorageException;
import by.training.listener.OptionWindowMouseListener;
import by.training.storage.Storage;
import by.training.window.ReminderWindow;

public class JIconPanel extends JPanel implements Runnable {

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
        super();
        this.options = options;

        init();

        storage = new Storage(options);
    }

    public int getIndex() {
        return index;
    }

    public OptionsElement getOptions() {
        return options;
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

    private void init() {
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

    public void runChart() throws JIconPanelException {
        setOptions();
        new Thread(storage).start();
        new Thread(this).start();
    }

    @Override
    public void run() {
        active = true;

        while (active) {
            try {
                switch (options.getPeriodElement().getPeriod()) {
                    case LAST_MINUTES_15:
                    case LAST_MINUTES_30:
                    case LAST_HOUR:
                        Metric metric;
                        metric = storage.getLast();
                        labelIconCurrentValue.setText(String.valueOf(metric.getValue()));

                        if (chart != null) {
                            chart.setText(String.valueOf(metric.getValue()));
                            chart.refresh(storage.getList());
                        }
                        break;

                    case CUSTOM:
                        if (!setCustom) {
                            setCustom = true;
                            labelIconCurrentValue.setText(NO_VALUE);

                            if (chart != null) {
                                chart.setText(NO_VALUE);
                                chart.refresh(storage.getList());
                            }
                        }
                        break;
                }
            } catch (StorageException e) {
                e.printStackTrace();
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

    public void setOptions() throws JIconPanelException {
        setCustom = false;
        labelIconTitle.setText(options.getTitle());

        try {
            storage.refresh();
        } catch (StorageException e) {
            throw new JIconPanelException(STORAGE_REFRESH_ERROR);
        }

        if (chart != null) {
            chart.setOptions(options.getTitle(),
                    options.getMetricTypeElement().getMetricType().getUnit());
        }

        synchronized (this) {
            notify();
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

}
