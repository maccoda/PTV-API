package factory;

public class DeserializerRegister {
    public static void loadDeserializers() {
        // Load the classes so the static block is called and registers
        try {
            Class.forName("deserialize.DepartureDeserializer");
            Class.forName("deserialize.DirectionDeserializer");
            Class.forName("deserialize.RouteDeserializer");
        } catch (final ClassNotFoundException any) {
            any.printStackTrace();
            throw new RuntimeException("Failed to load");
        }
    }
}
