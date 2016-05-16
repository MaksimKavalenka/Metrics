package by.training.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.NavigableSet;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;

import by.training.bean.metric.Metric;
import by.training.editor.ConfigEditor;
import by.training.exception.ConfigEditorException;
import by.training.exception.JWidgetPanelException;
import by.training.listener.OptionWindowButtonListener;

public class JWidgetPanel extends JPanel {

    private static final long serialVersionUID = 8939398023502921495L;

    private static final int  MAX_COLUMNS      = 10;

    private static int        count            = 0;

    private int               index;

    private Chart             chart;
    private IconDependency    dependency;

    private JLabel            labelCurrentValue;
    private JButton           buttonOptions;
    private JPanel            panelChart;

    public JWidgetPanel() {
        init();
        chart = new Chart();
        dependency = new IconDependency();
    }

    public Chart getChart() {
        return chart;
    }

    public IconDependency getDependency() {
        return dependency;
    }

    private void init() {
        GridBagLayout gbl_panelWidget = new GridBagLayout();
        gbl_panelWidget.columnWidths = new int[] {0, 0};
        gbl_panelWidget.rowHeights = new int[] {0, 0, 0};
        gbl_panelWidget.columnWeights = new double[] {1.0, Double.MIN_VALUE};
        gbl_panelWidget.rowWeights = new double[] {0.0, 1.0, Double.MIN_VALUE};
        setLayout(gbl_panelWidget);

        JPanel panelStatus = new JPanel();
        panelStatus.setBackground(Color.GRAY);
        GridBagConstraints gbc_panelStatus = new GridBagConstraints();
        gbc_panelStatus.insets = new Insets(0, 0, 5, 0);
        gbc_panelStatus.fill = GridBagConstraints.BOTH;
        gbc_panelStatus.gridx = 0;
        gbc_panelStatus.gridy = 0;
        add(panelStatus, gbc_panelStatus);
        panelStatus.setLayout(new BorderLayout(0, 0));

        JLabel labelName = new JLabel("Current value: ");
        labelName.setHorizontalAlignment(SwingConstants.RIGHT);
        labelName.setForeground(Color.WHITE);
        panelStatus.add(labelName, BorderLayout.WEST);

        labelCurrentValue = new JLabel();
        labelCurrentValue.setForeground(Color.WHITE);
        panelStatus.add(labelCurrentValue, BorderLayout.CENTER);

        buttonOptions = new JButton("Options");
        buttonOptions.addActionListener(new OptionWindowButtonListener(null));
        panelStatus.add(buttonOptions, BorderLayout.EAST);

        panelChart = new JPanel();
        GridBagConstraints gbc_panelChart = new GridBagConstraints();
        gbc_panelChart.fill = GridBagConstraints.BOTH;
        gbc_panelChart.gridx = 0;
        gbc_panelChart.gridy = 1;
        add(panelChart, gbc_panelChart);
        panelChart.setLayout(new BorderLayout(0, 0));

        index = count++;
    }

    public class Chart {

        private TimeSeries series;
        private XYPlot     plot;
        private JFreeChart chart;

        public Chart() {
            init();
        }

        private void init() {
            chart = ChartFactory.createTimeSeriesChart("", "", "", createSeries(), false, false,
                    false);
            ChartPanel chartPanel = new ChartPanel(chart);

            plot = chart.getXYPlot();
            plot.setRangeGridlinePaint(Color.BLACK);
            plot.setBackgroundPaint(Color.WHITE);
            ((DateAxis) plot.getDomainAxis())
                    .setDateFormatOverride(new SimpleDateFormat("HH:mm:ss"));

            panelChart.removeAll();
            panelChart.add(chartPanel);
            panelChart.validate();
        }

        private XYDataset createSeries() {
            series = new TimeSeries("");
            series.setMaximumItemCount(MAX_COLUMNS);
            return new TimeSeriesCollection(series);
        }

        public void setTitle(final String title) {
            chart.setTitle(title);
        }

        public void setUnit(final String unit) {
            plot.getRangeAxis().setLabel(unit);
        }

        public void setText(final String text) {
            labelCurrentValue.setText(text);
        }

        public synchronized void refresh(final NavigableSet<Metric> set) {
            series.clear();

            Iterator<Metric> iterator = set.iterator();
            if (set.size() > MAX_COLUMNS) {
                int part = (int) Math.ceil((double) set.size() / MAX_COLUMNS);

                if ((set.size() % MAX_COLUMNS) != 0) {
                    calcAverage(iterator, 0, set.size() % MAX_COLUMNS, part);
                    --part;
                }
                calcAverage(iterator, set.size() % MAX_COLUMNS, MAX_COLUMNS, part);
            } else {
                for (int i = 0; i < set.size(); i++) {
                    Metric metric = iterator.next();
                    series.addOrUpdate(new Second(metric.getDate()), metric.getValue());
                }
            }
        }

        private void calcAverage(final Iterator<Metric> iterator, final int startIndex,
                final int endIndex, final int part) {
            long avgDate = 0;
            double avgValue = 0;

            for (int i = startIndex; i < endIndex; i++) {
                avgDate = 0;
                avgValue = 0;

                for (int j = 0; j < part; j++) {
                    Metric store = iterator.next();
                    avgDate += store.getDate().getTime();
                    avgValue += store.getValue();
                }
                avgDate /= part;
                avgValue /= part;

                series.addOrUpdate(new Second(new Date(avgDate)), avgValue);
            }
        }

    }

    public class IconDependency {

        private JIconPanel iconPanel;

        public JIconPanel getIconPanel() {
            return iconPanel;
        }

        public void setIconPanel(final JIconPanel iconPanel) throws JWidgetPanelException {
            if (iconPanel.setChart(chart)) {
                if (this.iconPanel != null) {
                    this.iconPanel.removeDependency();
                }

                this.iconPanel = iconPanel;
                this.iconPanel.titleChanged();
                this.iconPanel.metricTypeChanged();

                ((OptionWindowButtonListener) buttonOptions.getActionListeners()[0])
                        .setIconPanel(iconPanel);
                setIndex();

                try {
                    ConfigEditor.updateConfig();
                } catch (ConfigEditorException e) {
                    throw new JWidgetPanelException(e.getMessage());
                }
            }
        }

        public void setIndex() {
            ConfigEditor.config.getWidget().getIconOptions().set(index, iconPanel.getIndex());
        }

    }

}
