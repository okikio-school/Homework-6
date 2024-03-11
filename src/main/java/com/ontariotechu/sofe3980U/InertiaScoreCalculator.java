package com.ontariotechu.sofe3980U;

import net.sf.javaml.core.Dataset;
import net.sf.javaml.core.Instance;
import net.sf.javaml.distance.EuclideanDistance;
import net.sf.javaml.distance.DistanceMeasure;

/**
 * Provides methods to calculate the inertia score for a set of clusters.
 * Inertia is a measure of how internally coherent clusters are, defined as the total sum
 * of squared distances between each point in a cluster and the cluster's centroid.
 * Lower inertia values indicate better clustering by minimizing variance within clusters.
 */
public class InertiaScoreCalculator {
    /**
     * Calculates the total inertia score for a set of clusters.
     * Inertia is defined as the sum of squared distances between each point in a cluster and the cluster's centroid.
     *
     * @param clusters An array of {@link Dataset} objects representing the clusters.
     * @return The total inertia score for all clusters.
     */
    public static double calculateTotalInertia(Dataset[] clusters) {
        DistanceMeasure distanceMeasure = new EuclideanDistance();
        double totalInertia = 0;

        for (Dataset cluster : clusters) {
            Instance centroid = calculateCentroid(cluster);
            for (Instance instance : cluster) {
                double distance = distanceMeasure.measure(instance, centroid);
                totalInertia += Math.pow(distance, 2);
            }
        }

        return totalInertia;
    }

    /**
     * Calculates the centroid of a given cluster.
     * The centroid is the average of all points in the cluster.
     *
     * @param cluster The cluster for which to calculate the centroid.
     * @return The centroid of the cluster.
     */
    public static Instance calculateCentroid(Dataset cluster) {
        if (cluster.size() == 0) {
            return null; // Return null or handle empty cluster appropriately.
        }
        Instance centroid = cluster.instance(0).copy();
        for (int i = 1; i < cluster.size(); i++) {
            centroid = centroid.add(cluster.instance(i));
        }
        centroid = centroid.divide(cluster.size());
        return centroid;
    }
}

