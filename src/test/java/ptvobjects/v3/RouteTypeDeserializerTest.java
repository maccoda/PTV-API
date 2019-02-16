package ptvobjects.v3;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import org.junit.Test;
import ptvobjects.RouteType;

import static org.junit.Assert.assertEquals;

public class RouteTypeDeserializerTest {

    @Test
    public void shouldMap0ToTrain() {
        final RouteTypeDeserializer deserializer = new RouteTypeDeserializer();
        final JsonElement element = new JsonPrimitive(0);
        final RouteType type = deserializer.deserialize(element, null, null);
        assertEquals(RouteType.Train, type);
    }

    @Test
    public void shouldMap1ToTram() {
        final RouteTypeDeserializer deserializer = new RouteTypeDeserializer();
        final JsonElement element = new JsonPrimitive(1);
        final RouteType type = deserializer.deserialize(element, null, null);
        assertEquals(RouteType.Tram, type);
    }

    @Test
    public void shouldMap2ToBus() {
        final RouteTypeDeserializer deserializer = new RouteTypeDeserializer();
        final JsonElement element = new JsonPrimitive(2);
        final RouteType type = deserializer.deserialize(element, null, null);
        assertEquals(RouteType.Bus, type);
    }

    @Test
    public void shouldMap3ToVLine() {
        final RouteTypeDeserializer deserializer = new RouteTypeDeserializer();
        final JsonElement element = new JsonPrimitive(3);
        final RouteType type = deserializer.deserialize(element, null, null);
        assertEquals(RouteType.VLine, type);
    }

    @Test
    public void shouldMap4ToNightBus() {
        final RouteTypeDeserializer deserializer = new RouteTypeDeserializer();
        final JsonElement element = new JsonPrimitive(4);
        final RouteType type = deserializer.deserialize(element, null, null);
        assertEquals(RouteType.NightBus, type);
    }
}
