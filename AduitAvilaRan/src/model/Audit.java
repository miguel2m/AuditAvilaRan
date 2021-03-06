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
public class Audit implements Clusterable,Comparable{
    private String fileName;
    private Double column;
    private double[] points;
    public Audit(String fileName, double column) {
        this.fileName = fileName;
        this.column = column;
        this.points = new double[] { column,0 };
    }
    
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public double getColumn() {
        return column;
    }

    public void setColumn(double column) {
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

    @Override
    public int compareTo(Object t) {
        int columnA=(int)((Audit)t).getColumn();
        /* For Ascending order*/
        return (int)this.getColumn()-columnA;

        /* For Descending order do like this */
        //return compareage-this.studentage;
    }

    
    
}
