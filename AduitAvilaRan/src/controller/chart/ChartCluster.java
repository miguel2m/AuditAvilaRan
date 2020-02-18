/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.chart;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.Audit;
import org.apache.commons.math3.ml.clustering.CentroidCluster;

import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.BitmapEncoder.BitmapFormat;
import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.style.Styler;



/**
 *
 * @author P05144
 */
public class ChartCluster {
    public static void paintChart(List<CentroidCluster<Audit>> dataSet,
            String output, 
            String fileOutName) throws IOException {
        //double[] xData = new double[]{0.0, 1.0, 2.0};
        //double[] yData = new double[]{2.0, 1.0, 0.0};
        
        
        CategoryChart chart = new CategoryChartBuilder().width(800).height(600)
                .title(fileOutName)
                .xAxisTitle(fileOutName)
                .yAxisTitle("Frequency")
                .build();
        
        
        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNW);
        chart.getStyler().setAvailableSpaceFill(0.99);
        chart.getStyler().setOverlapped(true);
        List yData = new ArrayList();
        List xData =new ArrayList();
        List yDataBaseline = new ArrayList();
        List xDataBaseline =new ArrayList();
        dataSet.forEach((a) -> {
            xData.add(a.getPoints().get(0).getColumn());
            
            yData.add(a.getPoints().size());
            a.getPoints().forEach((b) -> {
               if (b.getFileName().equals("BASELINE")) {
                   System.out.println(""+b.getColumn());
                   xDataBaseline.add(b.getColumn());
                   yDataBaseline.add(a.getPoints().size());
               }
            });
            //a.getPoints().contains("BASELINE");
        });
        chart.addSeries("VALOR", xData, yData);
        if(!yDataBaseline.isEmpty())chart.addSeries("BASELINE", xDataBaseline, yDataBaseline);
// Create Chart
        //XYChart chart = QuickChart.getChart(fileOutName+" Aduit", "X", "Y", "y(x)", xData, yData);

// Show it
        new SwingWrapper(chart).displayChart();

// Save it
        //BitmapEncoder.saveBitmap(chart, output, BitmapFormat.PNG);

// or save it in high-res
        BitmapEncoder.saveBitmapWithDPI(chart, output+File.separator+fileOutName+"-audit", BitmapFormat.PNG, 300);
        //VectorGraphicsEncoder.saveVectorGraphic(chart, output+File.separator+fileOutName+"-audit", VectorGraphicsFormat.SVG);
    }
}
