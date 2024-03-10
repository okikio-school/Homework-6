package com.ontariotechu.sofe3980U;
import java.io.*;
import java.sql.Date;

import org.joda.time.DateTime;
import org.joda.time.Duration;

import net.sf.javaml.clustering.*;
import net.sf.javaml.core.Dataset;
import net.sf.javaml.tools.data.FileHandler;

/**
 * This tutorial shows how to use a clustering algorithm to cluster a data set.
 * 
 * 
 * @author Thomas Abeel
 * 
 */
public class KMeansApp {

    /**
     * Tests the k-means algorithm with default parameter settings.
     */
    public static void main(String[] args) throws Exception {
        /* Load a dataset */
        Dataset data = FileHandler.loadDataset(new File("src/main/resources/iris.data"), 4, ",");
        System.out.println("Iris dataset loaded successfully - " + data.size() + " instances");

        DateTime start = new DateTime();
        DateTime end = new DateTime();
        Duration duration = new Duration(start, end);
        Dataset[] clusters = {};

        Clusterer km = new KMeans();
        Clusterer ff = new FarthestFirst();
        Clusterer cw = new Cobweb();

        /*
         * Cluster the data, use the KMeans Clustering Algorithm,
         * it will be returned as an array of data sets, with
         * each dataset representing a cluster
         */

        start = new DateTime();
        clusters = km.cluster(data);
        end = new DateTime();

        // Output the duration in milliseconds
        duration = new Duration(start, end);
        System.out.println("(KMeans) Cluster count: " + clusters.length + ", Task duration: " + duration.getMillis() + " milliseconds");

        for (Dataset cluster : clusters) {
            System.out.println("- (KMeans) 1st item in cluster = " + cluster.get(0).toString());
        }
        

        /*
         * Cluster the data, use the Farthest First Clustering Algorithm, 
         * it will be returned as an array of data sets, with
         * each dataset representing a cluster
         */

        start = new DateTime();
        clusters = ff.cluster(data);
        end = new DateTime();

        // Output the duration in milliseconds
        duration = new Duration(start, end);
        System.out.println("(Farthest First) Cluster count: " + clusters.length + ", Task duration: " + duration.getMillis() + " milliseconds");

        for (Dataset cluster : clusters) {
            System.out.println("- (Farthest First) 1st item in cluster = " + cluster.get(0).toString());
        }

        
        /*
         * Cluster the data, use the Cobweb Clustering Algorithm, 
         * it will be returned as an array of data sets, with
         * each dataset representing a cluster
         */

        start = new DateTime();
        clusters = ff.cluster(data);
        end = new DateTime();

        // Output the duration in milliseconds
        duration = new Duration(start, end);
        System.out.println("(Cobweb) Cluster count: " + clusters.length + ", Task duration: " + duration.getMillis() + " milliseconds");
        
        for (Dataset cluster : clusters) {
            System.out.println("- (Cobweb) 1st item in cluster = " + cluster.get(0).toString());
        }
    }

}