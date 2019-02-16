package deserialize;

import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import factory.GsonFactory;
import org.junit.Before;
import org.junit.Test;
import ptvobjects.RouteType;
import ptvobjects.v3.Route;

import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

public class RouteDeserializerTest {
    private RouteDeserializer deserializer;

    @Before
    public void setUp() {
        deserializer = new RouteDeserializer(GsonFactory.gson());
    }

    @Test
    public void shouldDeserialize() throws Exception {
        final Path path = Paths.get(ClassLoader.getSystemResource("v3/routes.json").toURI());
        final String input = new String(Files.readAllBytes(path));

        final Collection<Route> result = deserializer.deserialize(input);

        assertEquals(5, result.size());
        final Route first = result.iterator().next();
        assertEquals(RouteType.Train, first.getRouteType());
        assertEquals(1, first.getRouteId());
        assertEquals("Alamein", first.getRouteName());
        assertEquals("", first.getRouteNumber());
        assertEquals("2-ALM", first.getRouteGtfsId());
    }

    @Test
    public void shouldObtainRoutesObject() {
        final JsonElement element = deserializer.topLevelElement(DeserializeTestHelper.buildJsonArrayWithKey("routes"));

        DeserializeTestHelper.assertOnBuiltArray(element);
    }

    @Test
    public void shouldHaveCollectionRouteAsType() {
        final Type type = deserializer.deserializeType();

        assertEquals(new TypeToken<Collection<Route>>() {
        }.getType(), type);
    }
}
