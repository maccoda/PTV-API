package ptvobjects;

import com.google.gson.Gson;
import org.junit.Test;
import util.TestUtils;

import java.io.FileReader;

import static org.junit.Assert.assertEquals;

public class PtvLocationCluserTest {

    @Test
    public void testPopulate() throws Exception {
        final PtvLocationCluster obj = new Gson().fromJson(new FileReader(TestUtils.getResourcePath("testLocationCluster.json")), PtvLocationCluster.class);

        assertEquals(-37.81959, obj.getMinLat(), 0.1);
        assertEquals(144.979126, obj.getMinLong(), 0.1);
        assertEquals(-37.8157463, obj.getMaxLat(), 0.1);
        assertEquals(144.984818, obj.getMaxLong(), 0.1);
        assertEquals(-37.81705, obj.getWeightedLat(), 0.1);
        assertEquals(144.981964, obj.getWeightedLong(), 0.1);
        assertEquals(4, obj.getTotalLocations());
        assertEquals(4, obj.getLocations().size());
        assertEquals(0, obj.getClusters().size());
        assertEquals(1000, obj.getMaxLocations());
        assertEquals(true, obj.isLimitByDistance());
    }
}
