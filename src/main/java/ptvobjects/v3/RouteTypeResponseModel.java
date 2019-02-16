package ptvobjects.v3;

import ptvobjects.RouteType;

import java.util.StringJoiner;

public class RouteTypeResponseModel {
    private final String routeTypeName;
    private final int routeType;

    RouteTypeResponseModel(final String routeTypeName, final int routeType) {
        this.routeTypeName = routeTypeName;
        this.routeType = routeType;
    }

    public String getRouteTypeName() {
        return routeTypeName;
    }

    public int getRouteType() {
        return routeType;
    }

    public RouteType toRouteType() {
        return RouteType.valueOfInt(routeType);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", RouteTypeResponseModel.class.getSimpleName() + "[", "]")
                .add("routeTypeName='" + routeTypeName + "'")
                .add("routeType=" + routeType)
                .toString();
    }
}
