/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.chart;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import model.Audit;
import org.apache.commons.math3.ml.clustering.CentroidCluster;
import org.apache.commons.math3.stat.Frequency;

import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.BitmapEncoder.BitmapFormat;
import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.CategorySeries;
import org.knowm.xchart.SwingWrapper;
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
        
        CategoryChart   chart = new CategoryChartBuilder().width(800).height(600)
                .title(fileOutName)
                .xAxisTitle(fileOutName)
                .yAxisTitle("Frecuencia")
                .theme(ChartTheme.Matlab)
                .build();
        
      
        //chart.getStyler().setAxisTicksMarksVisible(true);
        chart.getStyler().setAvailableSpaceFill(0.99);
        chart.getStyler().setOverlapped(true);
        chart.getStyler().setChartTitleVisible(false);
        chart.getStyler().setLegendPosition(LegendPosition.OutsideE);
        
        //chart.getStyler().setAxisTickMarkLength(10);
        chart.getStyler().setMarkerSize(15);

        List yData = new ArrayList();
        List xData =new ArrayList();
        List yDataBaseline = new ArrayList();
        List xDataBaseline =new ArrayList();
        
        

        dataSet.forEach((a) -> {
            xData.add(a.getPoints().get(0).getColumn());               
            yData.add(_frecuency.getCount(a.getPoints().get(0).getColumn()));
            
            a.getPoints().forEach((b) -> {
                
               if (b.getFileName().equals("BASELINE")) {
                   
                   xDataBaseline.add(a.getPoints().get(0).getColumn());
                   yDataBaseline.add(_frecuency.getCount(a.getPoints().get(0).getColumn())); 
               }
            });
        });
        //Collections.sort(xData); 
        //xData.sort(cmprtr);
        //xData.forEach(System.out::println);
        chart.addSeries(fileOutName, xData, yData);
        if (!xDataBaseline.isEmpty()) {

            CategorySeries series = chart.addSeries("BASELINE= "+_baseline,xDataBaseline,yDataBaseline);
            series.setChartCategorySeriesRenderStyle(CategorySeries.CategorySeriesRenderStyle.Stick);
            series.setMarkerColor(Color.MAGENTA);
            series.setMarker(SeriesMarkers.CROSS);
           
        }
        
        chart.getStyler().setHasAnnotations(true);
        
// Show it
        new SwingWrapper(chart).displayChart();

// Save it
        //BitmapEncoder.saveBitmap(chart, output, BitmapFormat.PNG);

// or save it in high-res
        BitmapEncoder.saveBitmapWithDPI(chart, output+File.separator+fileOutName+"-audit", BitmapFormat.PNG, 300);
        //VectorGraphicsEncoder.saveVectorGraphic(chart, output+File.separator+fileOutName+"-audit", VectorGraphicsFormat.SVG);
    }
}
