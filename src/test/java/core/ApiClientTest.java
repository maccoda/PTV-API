package core;

import auth.Authentication;
import auth.DeveloperId;
import auth.PrivateKey;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ApiClientTest {

    @Test
    public void shouldCallHttpClientWithGeneratedSignature() {
        final SenderMock http = new SenderMock();
        final ApiClient client = new ApiClient(new UrlSignatureDecorator(new Authentication(PrivateKey.from("key"), DeveloperId.from(1))), http);

        client.send("/call/with/this");

        assertTrue(http.isCalled);
        assertEquals("http://timetableapi.ptv.vic.gov.au/call/with/this?devid=1&signature=1B659E5FDA9C96501E3F7D958CBA5674EDF9F500", http.calledValue);
    }

    static class SenderMock implements Sender {
        boolean isCalled = false;
        String calledValue;
        String response;

        SenderMock() {
            response = "response";
        }

        SenderMock(final String response) {
            this.response = response;
        }

        @Override
        public String send(final String sendToUrl) {
            isCalled = true;
            calledValue = sendToUrl;
            return response;
        }
    }
}
