package ptvobjects;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PtvPoiTest {

    @Test
    public void testOne() {
        final PtvPoi poi = new PtvPoi();
        poi.setPoi(PtvPoi.PtvPoiTypes.BUS);
        assertEquals("2", poi.toUriRepresentation());
    }

    @Test
    public void testAll() {
        final PtvPoi poi = new PtvPoi();
        for (final PtvPoi.PtvPoiTypes type : PtvPoi.PtvPoiTypes.values()) {
            poi.setPoi(type);
        }
        assertEquals("0,1,2,3,4,100", poi.toUriRepresentation());
    }

    @Test
    public void testSome() {
        final PtvPoi poi = new PtvPoi();
        poi.setPoi(PtvPoi.PtvPoiTypes.BUS);
        poi.setPoi(PtvPoi.PtvPoiTypes.NIGHT_BUS);
        assertEquals("2,4", poi.toUriRepresentation());
    }
}
