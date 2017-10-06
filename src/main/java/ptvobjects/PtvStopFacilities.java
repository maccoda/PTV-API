package ptvobjects;

public class PtvStopFacilities implements PtvBasicObject {
    private int stop_id, stop_mode_id;
    private String stop_type, stop_type_description;
    private StopLocation location;
    private StopAmenity amenity;
    private StopAccessibility accessibility;

    public int getStopId() {
        return stop_id;
    }

    public int getStopModeId() {
        return stop_mode_id;
    }

    public String getStopType() {
        return stop_type;
    }

    public String getStopTypeDescription() {
        return stop_type_description;
    }

    public StopLocation getLocation() {
        return location;
    }

    public StopAmenity getAmenity() {
        return amenity;
    }

    public StopAccessibility getAccessibility() {
        return accessibility;
    }

    public class StopLocation {
        private String suburb;
        private LocationGps gps;
        private int postcode;
        private String municipality;
        private int municipality_id;
        private String primary_stop_name, road_type_primary, second_stop_name, road_type_second;
        private int bay_nbr;

        public String getSuburb() {
            return suburb;
        }

        public LocationGps getGps() {
            return gps;
        }

        public int getPostcode() {
            return postcode;
        }

        public String getMunicipality() {
            return municipality;
        }

        public int getMunicipalityId() {
            return municipality_id;
        }

        public String getPrimaryStopName() {
            return primary_stop_name;
        }

        public String getRoadTypePrimary() {
            return road_type_primary;
        }

        public String getSecondStopName() {
            return second_stop_name;
        }

        public String getRoadTypeSecond() {
            return road_type_second;
        }

        public int getBayNbr() {
            return bay_nbr;
        }
    }

    public class LocationGps {
        private float longitude, latitude;

        public float getLongitude() {
            return longitude;
        }

        public float getLatitude() {
            return latitude;
        }
    }

    public class StopAmenity {
        private boolean toilet, taxiRank, cctv;
        private String car_parking;

        public boolean isToilet() {
            return toilet;
        }

        public boolean isTaxiRank() {
            return taxiRank;
        }

        public boolean isCctv() {
            return cctv;
        }

        public String getCarParking() {
            return car_parking;
        }
    }

    public class StopAccessibility {
        private boolean lighting, stairs, escalator, lifts, hearing_loop, tactile_tiles;
        private StopWheelchairAccessibility wheelchair;

        public boolean isLighting() {
            return lighting;
        }

        public boolean isStairs() {
            return stairs;
        }

        public boolean isEscalator() {
            return escalator;
        }

        public boolean isLifts() {
            return lifts;
        }

        public boolean isHearingLoop() {
            return hearing_loop;
        }

        public boolean isTactileTiles() {
            return tactile_tiles;
        }

        public StopWheelchairAccessibility getWheelchair() {
            return wheelchair;
        }
    }

    public class StopWheelchairAccessibility {
        private boolean accessible_ramp, acessible_parking, accessible_phone, accessible_toilet;

        public boolean isAccessibleRamp() {
            return accessible_ramp;
        }

        public boolean isAcessibleParking() {
            return acessible_parking;
        }

        public boolean isAccessiblePhone() {
            return accessible_phone;
        }

        public boolean isAcessibleToilet() {
            return accessible_toilet;
        }
    }
}
