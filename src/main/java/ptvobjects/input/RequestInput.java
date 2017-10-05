package ptvobjects.input;

/**
 * Data type that is used as part of a PTV request URI.
 */
public interface RequestInput {

    /**
     * Convert the data type into its URI representation for the request.
     *
     * @return URI representation
     */
    String toUriRepresentation();
}
