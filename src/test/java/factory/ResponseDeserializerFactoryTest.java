package factory;

import core.url.v3.DeparturesRequest;
import deserialize.DepartureDeserializer;
import deserialize.ResponseDeserializer;
import org.junit.Test;
import ptvobjects.v3.Departure;

import java.util.Collection;

import static org.junit.Assert.assertEquals;

public class ResponseDeserializerFactoryTest {

    @Test
    public void shouldRegisterAndRetrieve() {
        ResponseDeserializerFactory.instance().registerDeserializer(DeparturesRequest.class, DepartureDeserializer.class);

        final ResponseDeserializer<Collection<Departure>> de = ResponseDeserializerFactory.instance().obtainDeserializer(DeparturesRequest.class);

        assertEquals(DepartureDeserializer.class, de.getClass());
    }
}
