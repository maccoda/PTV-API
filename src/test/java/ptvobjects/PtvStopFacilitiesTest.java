package ptvobjects;

import org.junit.Test;
import util.TestUtils;

import static org.junit.Assert.assertEquals;

public class PtvStopFacilitiesTest {

    @Test
    public void testPopulate() throws Exception {
        final PtvStopFacilities result = TestUtils.getFromJson("testStopFacilities.json", PtvStopFacilities.class);
        assertEquals(1162, result.getStopId());
        assertEquals(2, result.getStopModeId());
        assertEquals("Premium Station", result.getStopType());
        assertEquals("The customer service centre is staffed from first to last train, 7 days a week. Protective Services Officers are generally present from 6pm to last train Sunday to Thursday and overnight on Fridays and Saturdays.", result.getStopTypeDescription());

        final PtvStopFacilities.StopLocation location = result.getLocation();
        assertEquals("Richmond", location.getSuburb());
        assertEquals(144.990158, location.getGps().getLongitude(), 0.01);
        assertEquals(-37.8240738, location.getGps().getLatitude(), 0.01);
        assertEquals(3121, location.getPostcode());
        assertEquals("Yarra", location.getMunicipality());
        assertEquals(53, location.getMunicipalityId());
        assertEquals("Punt", location.getPrimaryStopName());
        assertEquals("Rd", location.getRoadTypePrimary());
        assertEquals("Swan", location.getSecondStopName());
        assertEquals("St", location.getRoadTypeSecond());
        assertEquals(0, location.getBayNbr());

        final PtvStopFacilities.StopAmenity amenity = result.getAmenity();
        assertEquals(true, amenity.isToilet());
        assertEquals(false, amenity.isTaxiRank());
        assertEquals("0", amenity.getCarParking());
        assertEquals(true, amenity.isCctv());

        final PtvStopFacilities.StopAccessibility accessibility = result.getAccessibility();
        assertEquals(true, accessibility.isLighting());
        assertEquals(true, accessibility.isStairs());
        assertEquals(false, accessibility.isEscalator());
        assertEquals(false, accessibility.isLifts());
        assertEquals(false, accessibility.isHearingLoop());
        assertEquals(true, accessibility.isTactileTiles());
        assertEquals(false, accessibility.getWheelchair().isAccessibleRamp());
        assertEquals(false, accessibility.getWheelchair().isAcessibleParking());
        assertEquals(true, accessibility.getWheelchair().isAccessiblePhone());
        assertEquals(true, accessibility.getWheelchair().isAcessibleToilet());


    }
}
