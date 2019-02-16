package factory;

import auth.Authentication;
import core.ApiClient;
import core.HttpClientImpl;
import core.UrlSignatureDecorator;

public class ApiClientFactory {
    public static ApiClient v3ApiClient(final Authentication auth) {
        final UrlSignatureDecorator decorator = new UrlSignatureDecorator(auth);
        final HttpClientImpl httpClient = new HttpClientImpl();
        return new ApiClient(decorator, httpClient);
    }
}
