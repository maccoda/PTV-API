package util;

import com.google.gson.Gson;

import java.io.FileReader;
import java.io.IOException;

/**
 * Utility functions for tests
 */
public class TestUtils {

    /**
     * Empty constructor
     */
    private TestUtils() {
        // empty
    }

    /**
     * Load specified resource from the classpath.
     *
     * @param resource
     *         - name of resource
     * @return Resolved path to resource
     */
    public static String getResourcePath(final String resource) {
        return TestUtils.class.getClassLoader().getResource(resource).getPath();
    }

    /**
     * Loads the specified JSON resource and parses to the provided type
     *
     * @param resourceName
     *         - JSON resource name
     * @param clazz
     *         - Type to convert to
     * @param <T>
     *         - Type to convert to
     * @return Parsed JSON type
     *
     * @throws IOException
     */
    public static <T> T getFromJson(final String resourceName, final Class<T> clazz) throws IOException {
        return new Gson().fromJson(new FileReader(getResourcePath(resourceName)), clazz);
    }
}
