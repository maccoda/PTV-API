package ptvobjects;

import com.google.gson.Gson;
import org.junit.Test;
import util.TestUtils;

import java.io.FileReader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PtvLineTest {


    @Test
    public void testPopulateFields() throws Exception {
        final Gson gson = new Gson();

        final String resourcePath = TestUtils.getResourcePath("testLine.json");
        final PtvLine line = gson.fromJson(new FileReader(resourcePath), PtvLine.class);

        assertTrue(line.getRouteType().equals(PtvRouteType.Train));
        assertEquals(6, line.getLineId());
        assertTrue(line.getLineName().equals("Frankston"));
        assertTrue(line.getLineNumber().equals("Frankston"));
        assertTrue(line.getLineNameShort().equals("Frankston"));
        assertTrue(line.getLineNumberLong().equals(""));

    }

}
