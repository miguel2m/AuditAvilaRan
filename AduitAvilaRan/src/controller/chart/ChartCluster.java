/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.chart;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import model.Audit;
import org.apache.commons.math3.ml.clustering.CentroidCluster;
import org.apache.commons.math3.stat.Frequency;

import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.BitmapEncoder.BitmapFormat;
import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.XYSeries.XYSeriesRenderStyle;
import org.knowm.xchart.style.CategoryStyler;
import org.knowm.xchart.style.Styler;
import org.knowm.xchart.style.Styler.ChartTheme;
import org.knowm.xchart.style.Styler.LegendPosition;
import org.knowm.xchart.style.markers.SeriesMarkers;



/**
 *
 * @author P05144
 */
public class ChartCluster {
    public static void paintChart(List<CentroidCluster<Audit>> dataSet,
            String output, 
            String fileOutName,
            String _baseline,
            Frequency _frecuency) throws IOException {
        //double[] xData = new double[]{0.0, 1.0, 2.0};
        //double[] yData = new double[]{2.0, 1.0, 0.0};
        
        
        CategoryChart   chart = new CategoryChartBuilder().width(800).height(600)
                .title(fileOutName)
                .xAxisTitle(fileOutName)
                .yAxisTitle("Frecuencia")
                .theme(ChartTheme.Matlab)
                .build();
        
        
        
        /*chart.getStyler().setLegendPosition(Styler.LegendPosition.OutsideE);*/
        chart.getStyler().setAxisTicksMarksVisible(true);
        //chart.getStyler().setOverlapped(true);
        // Customize Chart
        //chart.getStyler().setDefaultSeriesRenderStyle(C);
        chart.getStyler().setChartTitleVisible(false);
        chart.getStyler().setLegendPosition(LegendPosition.OutsideE);
        //chart.getStyler().setAxisTickPadding(20);
        chart.getStyler().setAxisTickMarkLength(10);
        chart.getStyler().setMarkerSize(20);

        List yData = new ArrayList();
        List xData =new ArrayList();
        List yDataBaseline = new ArrayList();
        List xDataBaseline =new ArrayList();
        
        //Map<Double, Object> customYAxisTickLabelsMap = new HashMap<>();
        
        
        dataSet.forEach((a) -> {
            xData.add(a.getPoints().get(0).getColumn());               
            yData.add(a.getPoints().size());
            System.out.println(""+_frecuency.getCumFreq((double)a.getPoints().get(0).getColumn()));
            //customYAxisTickLabelsMap.put((double)a.getPoints().size(),a.getPoints().size());
            //System.out.println(""+a.getPoints().size());
            a.getPoints().forEach((b) -> {
               if (b.getFileName().equals("BASELINE")) {
                   //System.out.println(""+b.getColumn());
                   xDataBaseline.add(a.getPoints().get(0).getColumn());
                   yDataBaseline.add(a.getPoints().size());
               }
            });
        });
        
        
        chart.addSeries("VALOR", xData, yData);
        //chart.setYAxisLabelOverrideMap(customYAxisTickLabelsMap);
        /*if (!yDataBaseline.isEmpty()) {
            XYSeries series = chart.addSeries("BASELINE= "+_baseline,xDataBaseline,yDataBaseline);
            series.setXYSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Scatter);
            series.setMarker(SeriesMarkers.OVAL);
           
        }*/
        chart.getStyler().setHasAnnotations(true);
        
// Show it
        new SwingWrapper(chart).displayChart();

// Save it
        //BitmapEncoder.saveBitmap(chart, output, BitmapFormat.PNG);

// or save it in high-res
        //BitmapEncoder.saveBitmapWithDPI(chart, output+File.separator+fileOutName+"-audit", BitmapFormat.PNG, 300);
        //VectorGraphicsEncoder.saveVectorGraphic(chart, output+File.separator+fileOutName+"-audit", VectorGraphicsFormat.SVG);
    }
}
