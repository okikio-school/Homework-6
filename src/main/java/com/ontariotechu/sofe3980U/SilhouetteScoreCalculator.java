package com.ontariotechu.sofe3980U;

import net.sf.javaml.core.Dataset;
import net.sf.javaml.core.Instance;
import net.sf.javaml.distance.DistanceMeasure;
import net.sf.javaml.distance.EuclideanDistance;

/**
 * A class to calculate the silhouette score for a set of clusters.
 * The silhouette score is a measure of how similar an object is to its own cluster compared to other clusters.
 * The score ranges from -1 (incorrect clustering) to 1 (highly dense clustering), where high values indicate that the object is well matched to its own cluster and poorly matched to neighboring clusters.
 */
public class SilhouetteScoreCalculator {

    private DistanceMeasure dm;

    /**
     * Constructs a SilhouetteScoreCalculator with a default distance measure.
     * This constructor uses the Euclidean distance as the default measure.
     */
    public SilhouetteScoreCalculator() {
        this.dm = new EuclideanDistance(); // Using Euclidean distance as an example
    }

    /**
     * Calculates the average silhouette score for a set of clusters.
     *
     * @param clusters An array of {@link Dataset} objects, each representing a cluster.
     * @return The average silhouette score for all instances across all clusters.
     */
    public double calculateSilhouetteScore(Dataset[] clusters) {
        double totalSilhouetteScore = 0;
        int totalInstances = 0;

        for (int i = 0; i < clusters.length; i++) {
            for (Instance instance : clusters[i]) {
                double a = averageIntraClusterDistance(instance, clusters[i]);
                double b = minimumInterClusterDistance(instance, clusters, i);
                double s = (b - a) / Math.max(a, b);

                totalSilhouetteScore += s;
                totalInstances++;
            }
        }

        return totalInstances == 0 ? 0 : totalSilhouetteScore / totalInstances;
    }

    /**
     * Calculates the average distance from a given instance to all other instances in the same cluster.
     *
     * @param instance The instance to calculate the distance for.
     * @param cluster The cluster containing the instance and other instances to compare against.
     * @return The average intra-cluster distance.
     */
    private double averageIntraClusterDistance(Instance instance, Dataset cluster) {
        double totalDistance = 0;
        for (Instance otherInstance : cluster) {
            if (!instance.equals(otherInstance)) {
                totalDistance += dm.measure(instance, otherInstance);
            }
        }
        return cluster.size() <= 1 ? 0 : totalDistance / (cluster.size() - 1);
    }

    /**
     * Finds the minimum average distance from a given instance to all instances in other clusters.
     *
     * @param instance The instance to calculate the distance for.
     * @param clusters An array of all clusters.
     * @param ownClusterIndex The index of the cluster in the array that contains the given instance.
     * @return The minimum average inter-cluster distance.
     */
    private double minimumInterClusterDistance(Instance instance, Dataset[] clusters, int ownClusterIndex) {
        double minAverageDistance = Double.MAX_VALUE;

        for (int i = 0; i < clusters.length; i++) {
            if (i == ownClusterIndex) continue; // Skip the cluster containing the instance

            double totalDistance = 0;
            for (Instance otherInstance : clusters[i]) {
                totalDistance += dm.measure(instance, otherInstance);
            }

            double averageDistance = clusters[i].size() == 0 ? Double.MAX_VALUE : totalDistance / clusters[i].size();
            if (averageDistance < minAverageDistance) {
                minAverageDistance = averageDistance;
            }
        }

        return minAverageDistance;
    }
}
