package core;

public class ApiClient implements Sender {
    private final UrlSignatureDecorator urlSignatureDecorator;
    private final Sender httpClient;

    public ApiClient(final UrlSignatureDecorator urlSignatureDecorator, final Sender httpClient) {
        this.urlSignatureDecorator = urlSignatureDecorator;
        this.httpClient = httpClient;
    }

    @Override
    public String send(final String url) {
        final String urlWithSignature = urlSignatureDecorator.generateCompleteURLWithSignature(url);
        return httpClient.send(urlWithSignature);
    }
}
