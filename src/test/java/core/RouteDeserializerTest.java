package core;

import deserialize.RouteDeserializer;
import factory.GsonFactory;
import org.junit.Before;
import org.junit.Test;
import ptvobjects.PtvRouteType;
import ptvobjects.v3.Route;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

public class RouteDeserializerTest {
    private RouteDeserializer deserializer;

    @Before
    public void setUp() throws Exception {
        deserializer = new RouteDeserializer(GsonFactory.gson());
    }

    @Test
    public void shouldDeserialize() throws Exception {
        Path path = Paths.get(ClassLoader.getSystemResource("v3/routes.json").toURI());
        String input = new String(Files.readAllBytes(path));

        Collection<Route> result = deserializer.deserialize(input);

        assertEquals(5, result.size());
        Route first = result.iterator().next();
        assertEquals(PtvRouteType.Train, first.getRouteType());
        assertEquals(1, first.getRouteId());
        assertEquals("Alamein", first.getRouteName());
        assertEquals("", first.getRouteNumber());
        assertEquals("2-ALM", first.getRouteGtfsId());
    }
}
