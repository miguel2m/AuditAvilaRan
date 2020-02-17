/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.csv;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;
import model.Audit;
import org.apache.commons.math3.ml.clustering.CentroidCluster;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author P05144
 */
public class WriteCustomCsv {
    
    public static void writeDataAudit(List<CentroidCluster<Audit>> dataSet,  
            String _audit,
            String outputFolder) throws IOException{
        String fileName = _audit+"-D3OUTPUT.json";
        try (FileOutputStream fos = new FileOutputStream(outputFolder+File.separator+fileName);
                OutputStreamWriter osw = new OutputStreamWriter(fos, 
                        StandardCharsets.UTF_8);
                BufferedWriter bw = new BufferedWriter(osw)) {
            //Creating a JSONObject object
            JSONObject mainObject = new JSONObject();
           JSONArray childrenMain =  new JSONArray();
            mainObject.put("name",_audit);
            dataSet.forEach((a) -> {
                    JSONObject jsonObject = new JSONObject();
                    //Inserting key-value pairs into the json object  
                    jsonObject.put("name",a.getPoints().get(0).getColumn());
                                   
                    JSONArray value =  new JSONArray();
                    a.getPoints().forEach((b) -> {
                        value.add(b.getFileName());
                    });
                    
                    jsonObject.put("children",value);
                    //jsonObject.put( a.getPoints().get(0).getColumn(),value);
                    childrenMain.add(jsonObject);
                
            });
            
            mainObject.put("children",childrenMain);
            //System.out.println(""+mainObject.toJSONString());
            bw.write(mainObject.toJSONString());
            bw.close();
            
        }
    }
}
