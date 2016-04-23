package by.training.bean.ui;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import by.training.editor.ConfigEditor;
import by.training.exception.ConfigEditorException;
import by.training.listener.OptionWindowButtonListener;

public class JWidgetPanel extends JPanel {

    private static final long             serialVersionUID = -3255394251222036087L;

    private static final SimpleDateFormat FORMAT           = new SimpleDateFormat("HH:mm:ss");

    private static int                    count            = 0;

    private int                           columns          = 10;
    private DefaultCategoryDataset        dataset          = new DefaultCategoryDataset();

    private CategoryPlot                  categoryPlot;
    private JFreeChart                    chart;

    private int                           index;
    private JIconPanel                    iconPanel;

    private JLabel                        labelCurrentValue;
    private JButton                       buttonOption;
    private JPanel                        panelChart;

    public JWidgetPanel() {
        index = count++;
    }

    public JIconPanel getIconPanel() {
        return iconPanel;
    }

    public void setIconPanel(final JIconPanel iconPanel) throws ConfigEditorException {
        if (iconPanel.setWidgetPanel(this)) {
            if (this.iconPanel != null) {
                this.iconPanel.removeDependency();
            }
            this.iconPanel = iconPanel;
            this.iconPanel.setOptions();

            ((OptionWindowButtonListener) buttonOption.getActionListeners()[0])
                    .setIconPanel(iconPanel);
            setIndex();

            ConfigEditor.updateConfig();
        }
    }

    public void setIndex() {
        ConfigEditor.config.getWidget().getIconMetrics().set(index, iconPanel.getIndex());
    }

    public void init() {
        labelCurrentValue = (JLabel) ((JPanel) getComponent(0)).getComponent(1);
        buttonOption = (JButton) ((JPanel) getComponent(0)).getComponent(2);
        panelChart = (JPanel) getComponent(1);

        buttonOption.addActionListener(new OptionWindowButtonListener(iconPanel));
        initChart();
    }

    private void initChart() {
        chart = ChartFactory.createLineChart("", "", "", dataset, PlotOrientation.VERTICAL, false,
                false, false);
        ChartPanel chartPanel = new ChartPanel(chart);

        categoryPlot = chart.getCategoryPlot();
        categoryPlot.setRangeGridlinePaint(Color.BLACK);
        categoryPlot.setBackgroundPaint(Color.WHITE);

        panelChart.removeAll();
        panelChart.add(chartPanel);
        panelChart.validate();
    }

    public void refresh(final double value) {
        if (columns == 0) {
            dataset.removeValue("", dataset.getColumnKeys().get(0).toString());
        } else {
            --columns;
        }
        dataset.setValue(value, "", FORMAT.format(new Date()));

        labelCurrentValue.setText(String.valueOf(value));
    }

    public void setOptions(final String title, final String unit) {
        chart.setTitle(title);
        categoryPlot.getRangeAxis().setLabel(unit);
    }

}
