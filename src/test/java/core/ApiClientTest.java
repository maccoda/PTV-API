package core;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ApiClientTest {

    @Test
    public void shouldCallHttpClientWithGeneratedSignature() {
        final SenderMock http = new SenderMock();
        // TODO: This constructor is a problem
        final ApiClient client = new ApiClient(new UrlSignatureDecorator(UrlSignatureDecorator.ApiVersion.V3, "key", 1), http);

        client.send("/call/with/this");

        assertTrue(http.isCalled);
        assertEquals("http://timetableapi.ptv.vic.gov.au/v3/call/with/this?devid=1&signature=299016E46F93B72DDDAEFAB50A51D5D3FCD394FE", http.calledValue);
    }

    static class SenderMock implements Sender {
        boolean isCalled = false;
        String calledValue;
        String response;

        SenderMock() {
            response = "response";
        }

        SenderMock(String resonse) {
            this.response = resonse;
        }

        @Override
        public String send(final String sendToUrl) {
            isCalled = true;
            calledValue = sendToUrl;
            return response;
        }
    }
}
