package core.url.v3;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UrlPathBuilderTest {
    private UrlPathBuilder builder;

    @Before
    public void setUp() throws Exception {
        builder = new UrlPathBuilder();
    }

    @Test
    public void shouldAppendPathSegment() {
        builder.appendPathSegment("hello");

        assertEquals("/hello", builder.build());
    }

    @Test
    public void shouldAppendPathAndQuery() {
        builder.appendPathSegment("hello");
        builder.appendQueryParam("key", "value");

        assertEquals("/hello?key=value", builder.build());
    }

    @Test
    public void shouldAppendMultiplePathSegments() {
        builder.appendPathSegment("hello");
        builder.appendPathSegment("there");

        assertEquals("/hello/there", builder.build());
    }

    @Test
    public void shouldAppendPathAndMultipleQueryParameters() {
        builder.appendPathSegment("path");
        builder.appendQueryParam("key", "value");
        builder.appendQueryParam("another", "one");

        assertEquals("/path?key=value&another=one", builder.build());
    }
}
