package ptvobjects.v3;

import ptvobjects.PtvRouteType;

import java.util.Objects;
import java.util.StringJoiner;

public class Route {
    private PtvRouteType routeType;
    private int routeId;
    private String routeName;
    private String routeNumber;
    private String routeGtfsId;

    public PtvRouteType getRouteType() {
        return routeType;
    }

    public int getRouteId() {
        return routeId;
    }

    public String getRouteName() {
        return routeName;
    }

    public String getRouteNumber() {
        return routeNumber;
    }

    public String getRouteGtfsId() {
        return routeGtfsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Route route = (Route) o;
        return routeType == route.routeType &&
                routeId == route.routeId &&
                Objects.equals(routeName, route.routeName) &&
                Objects.equals(routeNumber, route.routeNumber) &&
                Objects.equals(routeGtfsId, route.routeGtfsId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(routeType, routeId, routeName, routeNumber, routeGtfsId);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Route.class.getSimpleName() + "[", "]")
                .add("routeType=" + routeType)
                .add("routeId=" + routeId)
                .add("routeName='" + routeName + "'")
                .add("routeNumber='" + routeNumber + "'")
                .add("routeGtfsId='" + routeGtfsId + "'")
                .toString();
    }
}
