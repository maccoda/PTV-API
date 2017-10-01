package ptvobjects.builders;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import org.junit.Test;
import ptvobjects.PtvResult;
import util.TestUtils;

import java.io.FileReader;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ResultListBuilderTest {

    @Test
    public void test() throws Exception {
        final String resourcePath = TestUtils.getResourcePath("testResultList.json");
        final JsonArray arr = new Gson().fromJson(new FileReader(resourcePath), JsonArray.class);

        final List<PtvResult> results = new ResultListBuilder().populateList(arr);

        final PtvResult result0 = results.get(0);
        assertEquals("line", result0.getType().toLowerCase());

        final PtvResult result1 = results.get(1);
        assertEquals("stop", result1.getType().toLowerCase());

        final PtvResult result2 = results.get(2);
        assertEquals("stop", result2.getType().toLowerCase());
    }
}
