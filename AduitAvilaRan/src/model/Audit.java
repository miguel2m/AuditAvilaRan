/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author P05144
 */
public class Audit {
    private String fileName;
    private String column;

    public Audit(String fileName, String column) {
        this.fileName = fileName;
        this.column = column;
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
        return "Audit{" + "fileName=" + fileName + ", column=" + column + '}';
    }

    
    
}
