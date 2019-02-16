package deserialize;

import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import factory.GsonFactory;
import org.junit.Before;
import org.junit.Test;
import ptvobjects.v3.Departure;

import java.lang.reflect.Type;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class DepartureDeserializerTest {

    private DepartureDeserializer deserializer;

    @Before
    public void setUp() {
        deserializer = new DepartureDeserializer(GsonFactory.gson());
    }

    @Test
    public void shouldObtainDeparturesObject() {
        final JsonElement element = deserializer.topLevelElement(DeserializeTestHelper.buildJsonArrayWithKey("departures"));

        DeserializeTestHelper.assertOnBuiltArray(element);
    }

    @Test
    public void shouldHaveCollectionDepartureAsType() {
        final Type type = deserializer.deserializeType();

        assertEquals(new TypeToken<List<Departure>>() {
        }.getType(), type);
    }
}
