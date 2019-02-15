package core;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ApiClientTest {
    private ApiClient client;

    @Before
    public void setUp()  {
        client = new ApiClient();
    }

    @Test
    public void sendQueryTest() {
        String path = ClassLoader.getSystemResource("testResponse.json").getPath();
        final String fileLocation = "file://" + path;

        final String result = client.send(fileLocation);

        final String input = "{  \"securityTokenOK\": false,  \"clientClockOK\": false,  \"memcacheOK\": true,  \"databaseOK\": true,}";
        assertEquals(input, result);
    }

}
