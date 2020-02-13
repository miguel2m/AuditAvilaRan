/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.readcsv;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import model.Audit;


/**
 *
 * @author Miguelangel
 */
public class ReadCustomCsv {
    /**
     * METODO QUE AUDITA UN .CSV DINAMICAMENTE
     * @param _input
     * @param _audit
     * @return 
     * @throws IOException 
     */
    public static List<Audit> getDataAudit(String _input, String _audit)throws IOException, CsvException{
        
        Path myPath = Paths.get(_input);
        List<Audit> audit = new ArrayList<>();
        CSVParser parser = new CSVParserBuilder().withSeparator(',').build();
        try (BufferedReader br = Files.newBufferedReader(myPath,
                StandardCharsets.UTF_8);
                CSVReader reader = new CSVReaderBuilder(br).withCSVParser(parser)
                        .build()) {
            List<String[]> rows = reader.readAll();
            rows.forEach((t) -> {
                if (rows.get(0) != t) {
                    for (int i = 0; i < t.length; i++) {
                        if (rows.get(0)[i].equals(_audit)) {
                            Audit _auditTemp = new Audit(t[0].split("_")[1],t[i]);
                            audit.add(_auditTemp);
                        }
                    }
                }
            });          
        }
       return audit;
        
    }
    /**
     * METODO RETORNA LAS COLUNAS DEL CSV
     * @param _input
     * @return 
     * @throws IOException 
     */
    public static List<String> getColumnsCSV(String _input)throws IOException, CsvException{
        
        Path myPath = Paths.get(_input);
        List<String> colums = new ArrayList<>();
        CSVParser parser = new CSVParserBuilder().withSeparator(',').build();
        try (BufferedReader br = Files.newBufferedReader(myPath,
                StandardCharsets.UTF_8);
                CSVReader reader = new CSVReaderBuilder(br).withCSVParser(parser)
                        .build()) {
            List<String[]> rows = reader.readAll();
            rows.forEach((t) -> {
                if (rows.get(0) == t){
                    for (int i = 0; i < t.length; i++) {
                        colums.add(t[i]);
                    }
                } 
                    
            });        
        }
       return colums;
        
    }
}
