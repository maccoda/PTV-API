package ptvobjects;

import java.util.List;

public class PtvLocationCluster implements PtvBasicObject {
    private double minLat, minLong, maxLat, maxLong, weightedLat, weightedLong;
    private int totalLocations;
    // NOTE This is not entirely correct as can be an Outlet type but has no discriminant :(
    private List<PtvStop> locations;
    private List<PtvCluster> clusters;
    private int MaxLocations;
    private boolean LimitByDistance;

    public double getMinLat() {
        return minLat;
    }

    public double getMinLong() {
        return minLong;
    }

    public double getMaxLat() {
        return maxLat;
    }

    public double getMaxLong() {
        return maxLong;
    }

    public double getWeightedLat() {
        return weightedLat;
    }

    public double getWeightedLong() {
        return weightedLong;
    }

    public int getTotalLocations() {
        return totalLocations;
    }

    public List<PtvStop> getLocations() {
        return locations;
    }

    public List<PtvCluster> getClusters() {
        return clusters;
    }

    public int getMaxLocations() {
        return MaxLocations;
    }

    public boolean isLimitByDistance() {
        return LimitByDistance;
    }
}
