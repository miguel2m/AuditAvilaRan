/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.chart;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    /**
     * Intento para pintar cluster con baseline
     * @param dataSet
     * @param output
     * @param fileOutName
     * @param _baseline
     * @param _frecuency
     * @throws IOException 
     */
    public static void paintChart(List<CentroidCluster<Audit>> dataSet,
            String output, 
            String fileOutName,
            String _baseline,
            Frequency _frecuency) throws IOException {      
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int)screenSize.getWidth();
        int height = (int)screenSize.getHeight()-200;
        CategoryChart   chart = new CategoryChartBuilder().width(width).height(height)
                .title(fileOutName)
                .xAxisTitle(fileOutName+" (BASELINE= "+_baseline+")")
                .yAxisTitle("Recurrente")
                .theme(ChartTheme.Matlab)
                .build();
        //chart.getStyler().setDefaultSeriesRenderStyle(XYSeriesRenderStyle.Scatter);
        chart.getStyler().setAvailableSpaceFill(0.75);
        chart.getStyler().setOverlapped(true);
        chart.getStyler().setChartTitleVisible(false);
        chart.getStyler().setLegendPosition(LegendPosition.OutsideS);
        chart.getStyler().setHasAnnotations(true);
        //chart.getStyler().setShowWithinAreaPoint(true);
        //chart.getStyler().setMarkerSize(15);
        chart.getStyler().setYAxisDecimalPattern("#,###.##");
        chart.getStyler().setXAxisDecimalPattern("#,###");
        
        //chart.getStyler().setPlotTicksMarksVisible(true);
        /*
        chart.getStyler().setToolTipsEnabled(true);
        chart.getStyler().setToolTipsAlwaysVisible(true);
         */
        List yData = new ArrayList();
        List xData = new ArrayList();
        //List yDataBaseline = new ArrayList();
        //List xDataBaseline =new ArrayList();
        
        Map<Object, Object> customXAxisTickLabelsMap = new HashMap<>();
        //Map<Object, Object> customYAxisTickLabelsMap = new HashMap<>();

        dataSet.forEach((a) -> {
            //EMTCMODEARLCPARAGROUPID
            xData.add(a.getPoints().get(0).getColumn());               
            yData.add(_frecuency.getCount(a.getPoints().get(0).getColumn()));
            customXAxisTickLabelsMap.put(a.getPoints().get(0).getColumn(),
                           (int)a.getPoints().get(0).getColumn());
            if (a.getPoints().get(a.getPoints().size()-1).getFileName().equals("BASELINE")) {
                   
                   customXAxisTickLabelsMap.replace(a.getPoints().get(0).getColumn(),
                           (int)a.getPoints().get(0).getColumn()+" ~ (BASELINE)" );

            }
           /* a.getPoints().forEach((b) -> {
                
               if (b.getFileName().equals("BASELINE")) {
                   
                   customXAxisTickLabelsMap.replace(a.getPoints().get(0).getColumn(),
                           (int)a.getPoints().get(0).getColumn()+" ~ (BASELINE)" );
                  /*xDataBaseline.add(a.getPoints().get(0).getColumn());
                  yDataBaseline.add(_frecuency.getCount(a.getPoints().get(0).getColumn())); */
              /* }
            });*/
        });
       
        chart.setCustomXAxisTickLabelsMap(customXAxisTickLabelsMap);
        //System.out.println("xData");
        //xData.forEach(System.out::println);
        //System.out.println("yData");
        //yData.forEach(System.out::println);
        //System.out.println(""+xData.size());
        //if(xData.size() > 10)
            chart.getStyler().setXAxisLabelRotation(45);
        chart.addSeries(fileOutName, xData, yData);
        
        
        
        /*if (!xDataBaseline.isEmpty()) {
            //chart.addSeries("BASELINE = "+_baseline, xDataBaseline, yDataBaseline);
            //System.out.println("xDataBaseline");
            //xDataBaseline.forEach(System.out::println);
            //System.out.println("yDataBaseline");
            //yDataBaseline.forEach(System.out::println);
            CategorySeries series = chart.addSeries("BASELINE = "+_baseline,xData,yData);
            //XYSeries series = chart.addSeries("BASELINE = "+_baseline,xDataBaseline,yDataBaseline);
            series.setChartCategorySeriesRenderStyle(CategorySeries.CategorySeriesRenderStyle.Scatter);
            //series.setXYSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Scatter);
            series.setMarkerColor(Color.MAGENTA);
            series.setMarker(SeriesMarkers.CROSS);       
        //}*/

        new SwingWrapper(chart).displayChart();

// or save it in high-res
        BitmapEncoder.saveBitmapWithDPI(chart, output+File.separator+fileOutName+"-audit", BitmapFormat.PNG, 300);
        //VectorGraphicsEncoder.saveVectorGraphic(chart, output+File.separator+fileOutName+"-audit", VectorGraphicsFormat.SVG);
    }
}
