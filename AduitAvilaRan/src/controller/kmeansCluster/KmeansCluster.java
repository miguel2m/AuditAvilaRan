/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.kmeansCluster;

import java.util.List;
import model.Audit;
import org.apache.commons.math3.ml.clustering.CentroidCluster;
import org.apache.commons.math3.ml.clustering.KMeansPlusPlusClusterer;

/**
 *
 * @author P05144
 */
public class KmeansCluster {
    /**
     * METODO QUE CREA LOS GRUPOS DE DATASETS
     * @param nCluster
     * @param dataSet
     * @return 
     */
    public static List<CentroidCluster<Audit>> getCluster(int nCluster, List<Audit> dataSet){
        KMeansPlusPlusClusterer<Audit> clusterer 
                        = new KMeansPlusPlusClusterer<>(nCluster, 10000);//GRUPOS, ITERACIONES
                
        List<CentroidCluster<Audit>> clusterResults = clusterer.cluster(dataSet);
        
        return clusterResults;
    }
}
