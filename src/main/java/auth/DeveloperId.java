package auth;

public class DeveloperId {
    private final int inner;

    private DeveloperId(final int inner) {
        this.inner = inner;
    }

    public int asInteger() {
        return inner;
    }

    public static DeveloperId from(final int id) {
        return new DeveloperId(id);
    }
}
