package by.training.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

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
import by.training.listener.OptionWindowButtonListener;

public class JWidgetPanel extends JPanel {

    private static final long serialVersionUID = 8939398023502921495L;

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

        private Date       lastDate;

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
            series.setMaximumItemAge(Long.MAX_VALUE);
            return new TimeSeriesCollection(series);
        }

        public void setText(final String text) {
            labelCurrentValue.setText(text);
        }

        public void setTitle(final String title) {
            chart.setTitle(title);
        }

        public void setUnit(final String unit) {
            plot.getRangeAxis().setLabel(unit);
        }

        public void setPeriod(final long period) {
            series.setMaximumItemAge(period);
        }

        public void clear() {
            lastDate = null;
            series.clear();
        }

        public Date getLastDate() {
            return lastDate;
        }

        public synchronized void refresh(final Set<Metric> set) {
            Metric metric = null;

            Iterator<Metric> iterator = set.iterator();
            while (iterator.hasNext()) {
                metric = iterator.next();
                series.addOrUpdate(new Second(metric.getDate()), metric.getValue());
            }

            if (metric != null) {
                lastDate = metric.getDate();
            }
        }

    }

    public class IconDependency {

        private JIconPanel iconPanel;

        public JIconPanel getIconPanel() {
            return iconPanel;
        }

        public void setIconPanel(final JIconPanel iconPanel) throws ConfigEditorException {
            if (iconPanel.setChart(chart)) {
                if (this.iconPanel != null) {
                    this.iconPanel.removeDependency();
                }

                this.iconPanel = iconPanel;
                this.iconPanel.titleChanged();
                this.iconPanel.metricTypeChanged();
                this.iconPanel.periodChanged();

                ((OptionWindowButtonListener) buttonOptions.getActionListeners()[0])
                        .setIconPanel(iconPanel);
                setIndex();
                ConfigEditor.updateConfig();
            }
        }

        public void setIndex() {
            ConfigEditor.config.getWidget().getIconOptions().set(index, iconPanel.getIndex());
        }

    }

}
