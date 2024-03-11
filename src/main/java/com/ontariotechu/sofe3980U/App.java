package com.ontariotechu.sofe3980U;

import java.io.*;
import java.util.ArrayList;

import org.joda.time.DateTime;
import org.joda.time.Duration;

import net.sf.javaml.clustering.*;
import net.sf.javaml.core.Dataset;
import net.sf.javaml.core.Instance;
import net.sf.javaml.tools.data.FileHandler;

public class App {

    /**
     * Tests the k-means algorithm with default parameter settings.
     */
    public static void main(String[] args) throws Exception {
        /* Load a dataset */
        Dataset data = FileHandler.loadDataset(new File("src/main/resources/iris.data"), 4, ",");
        System.out.println("Iris dataset loaded successfully - " + data.size() + " instances");
        System.out.println("");

        Dataset[] clusters = {};

        ArrayList<String> centroids = new ArrayList<String>();
        ArrayList<Integer> clusterSizes = new ArrayList<Integer>();

        SilhouetteScoreCalculator ssc = new SilhouetteScoreCalculator();

        DateTime start = new DateTime();
        DateTime end = new DateTime();
        Duration duration = new Duration(start, end);

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

        centroids.clear();
        clusterSizes.clear();

        for (Dataset cluster : clusters) {
            Instance centroid = InertiaScoreCalculator.calculateCentroid(cluster);
            centroids.add(centroid.toString());
            clusterSizes.add(cluster.size());
        }

        System.out.println("Cluster Centers: " + centroids);
        System.out.println("Number of Points in Each Cluster: " + clusterSizes);
        System.out.println("Inertia: " + InertiaScoreCalculator.calculateTotalInertia(clusters));
        System.out.println("Silhouette Score: " + ssc.calculateSilhouetteScore(clusters));
        System.out.println("");


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

        centroids.clear();
        clusterSizes.clear();
        
        for (Dataset cluster : clusters) {
            Instance centroid = InertiaScoreCalculator.calculateCentroid(cluster);
            centroids.add(centroid.toString());
            clusterSizes.add(cluster.size());
        }

        System.out.println("Cluster Centers: " + centroids);
        System.out.println("Number of Points in Each Cluster: " + clusterSizes);
        System.out.println("Inertia: " + InertiaScoreCalculator.calculateTotalInertia(clusters));
        System.out.println("Silhouette Score: " + ssc.calculateSilhouetteScore(clusters));
        System.out.println("");
		
        /*
         * Cluster the data, use the Cobweb Clustering Algorithm, 
         * it will be returned as an array of data sets, with
         * each dataset representing a cluster
         */
        start = new DateTime();
        clusters = cw.cluster(data);
        end = new DateTime();

        // Output the duration in milliseconds
        duration = new Duration(start, end);
        System.out.println("(Cobweb) Cluster count: " + clusters.length + ", Task duration: " + duration.getMillis() + " milliseconds");
        System.out.println("");
    }

}