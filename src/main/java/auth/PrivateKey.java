package auth;

public class PrivateKey {
    private final String inner;

    private PrivateKey(final String inner) {
        this.inner = inner;
    }

    public String asString() {
        return inner;
    }

    public static PrivateKey from(final String key) {
        return new PrivateKey(key);
    }
}
