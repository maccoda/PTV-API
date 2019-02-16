package ptvobjects.v3;

import ptvobjects.RouteType;

import java.util.Objects;
import java.util.StringJoiner;

public class Direction {
    private int directionId;
    private String directionName;
    private int routeId;
    private RouteType routeType;

    public int getDirectionId() {
        return directionId;
    }

    public String getDirectionName() {
        return directionName;
    }

    public int getRouteId() {
        return routeId;
    }

    public RouteType getRouteType() {
        return routeType;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Direction direction = (Direction) o;
        return directionId == direction.directionId &&
                routeId == direction.routeId &&
                Objects.equals(directionName, direction.directionName) &&
                routeType == direction.routeType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(directionId, directionName, routeId, routeType);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Direction.class.getSimpleName() + "[", "]")
                .add("directionId=" + directionId)
                .add("directionName='" + directionName + "'")
                .add("routeId=" + routeId)
                .add("routeType=" + routeType)
                .toString();
    }
}
