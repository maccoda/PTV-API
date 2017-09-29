package ptvobjects;

import com.google.gson.Gson;
import org.junit.Test;
import util.TestUtils;

import java.io.FileReader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PtvDirectionTest {


    @Test
    public void testPopulateFields() throws Exception {
        final String filePath = TestUtils.getResourcePath("testDirection.json");
        final Gson gson = new Gson();
        final PtvDirection dir = gson.fromJson(new FileReader(filePath), PtvDirection.class);

        assertEquals(0, dir.getLineDirId());
        assertEquals(6, dir.getDirectionId());
        assertTrue(dir.getDirectionName().equals("Frankston"));

        final PtvLine line = dir.getLine();
        assertTrue(line.getRouteType().equals(PtvRouteType.Train));
        assertEquals(6, line.getLineId());
        assertTrue(line.getLineName().equals("Frankston"));
        assertTrue(line.getLineNumber().equals("Frankston"));
        assertTrue(line.getLineNameShort().equals("Frankston"));
        assertTrue(line.getLineNumberLong().equals(""));

    }

}
