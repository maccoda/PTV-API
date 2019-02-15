package ptvobjects.v3;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import org.junit.Test;
import ptvobjects.PtvRouteType;

import static org.junit.Assert.assertEquals;

public class RouteTypeDeserializerTest {

    @Test
    public void shouldMap0ToTrain() {
        RouteTypeDeserializer deserializer = new RouteTypeDeserializer();
        JsonElement element = new JsonPrimitive(0);
        PtvRouteType type = deserializer.deserialize(element, null, null);
        assertEquals(PtvRouteType.Train, type);
    }

    @Test
    public void shouldMap1ToTram() {
        RouteTypeDeserializer deserializer = new RouteTypeDeserializer();
        JsonElement element = new JsonPrimitive(1);
        PtvRouteType type = deserializer.deserialize(element, null, null);
        assertEquals(PtvRouteType.Tram, type);
    }

    @Test
    public void shouldMap2ToBus() {
        RouteTypeDeserializer deserializer = new RouteTypeDeserializer();
        JsonElement element = new JsonPrimitive(2);
        PtvRouteType type = deserializer.deserialize(element, null, null);
        assertEquals(PtvRouteType.Bus, type);
    }

    @Test
    public void shouldMap3ToVLine() {
        RouteTypeDeserializer deserializer = new RouteTypeDeserializer();
        JsonElement element = new JsonPrimitive(3);
        PtvRouteType type = deserializer.deserialize(element, null, null);
        assertEquals(PtvRouteType.VLine, type);
    }

    @Test
    public void shouldMap4ToNightBus() {
        RouteTypeDeserializer deserializer = new RouteTypeDeserializer();
        JsonElement element = new JsonPrimitive(4);
        PtvRouteType type = deserializer.deserialize(element, null, null);
        assertEquals(PtvRouteType.NightBus, type);
    }
}
