package auth;

public class Authentication {
    private final PrivateKey privateKey;
    private final DeveloperId developerId;

    public Authentication(final PrivateKey privateKey, final DeveloperId developerId) {
        this.privateKey = privateKey;
        this.developerId = developerId;
    }

    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    public DeveloperId getDeveloperId() {
        return developerId;
    }
}
