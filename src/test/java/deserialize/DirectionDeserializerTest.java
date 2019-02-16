package deserialize;

import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import factory.GsonFactory;
import org.junit.Before;
import org.junit.Test;
import ptvobjects.v3.Direction;

import java.lang.reflect.Type;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class DirectionDeserializerTest {


    private DirectionDeserializer deserializer;

    @Before
    public void setUp() throws Exception {
        deserializer = new DirectionDeserializer(GsonFactory.gson());
    }

    @Test
    public void shouldObtainDirectionsObject() {
        final JsonElement element = deserializer.topLevelElement(DeserializeTestHelper.buildJsonArrayWithKey("directions"));

        DeserializeTestHelper.assertOnBuiltArray(element);
    }

    @Test
    public void shouldHaveCollectionDirectionAsType() {
        final Type type = deserializer.deserializeType();

        assertEquals(new TypeToken<List<Direction>>() {
        }.getType(), type);
    }

}
