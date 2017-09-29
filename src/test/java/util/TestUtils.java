package util;

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
}
