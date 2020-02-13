/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import org.apache.commons.math3.ml.clustering.Clusterable;



/**
 *
 * @author P05144
 */
public class Audit implements Clusterable{
    private String fileName;
    private String column;
    private double[] points;
    public Audit(String fileName, String column) {
        this.fileName = fileName;
        this.column = column;
        this.points = new double[] { Double.parseDouble(column),0 };
    }
    
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    @Override
    public String toString() {
        return "Audit{" + "fileName=" + fileName + ", column=" + column + '}'+"\n";
    }

    @Override
    public double[] getPoint() {
      return points;
    }

    
    
}
