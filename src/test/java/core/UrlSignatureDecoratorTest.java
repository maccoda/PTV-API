package core;

import auth.Authentication;
import auth.DeveloperId;
import auth.PrivateKey;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * UrlSignatureDecorator Tester.
 *
 * @author D. Maccora
 * @version 1.0
 * @since <pre>Apr 8, 2017</pre>
 */
public class UrlSignatureDecoratorTest {

    private final UrlSignatureDecorator tester = new UrlSignatureDecorator(new Authentication(PrivateKey.from("9c132d31-6a30-4cac-8d8b-8a1970834799"), DeveloperId.from(2)));

    @Test
    public void shouldAddSignature() {
        final String result = tester.generateCompleteURLWithSignature("/Add/to/me");

        assertEquals("http://timetableapi.ptv.vic.gov.au/Add/to/me?devid=2&signature=BEA65E95FA54DFF010E614C6165F9290C1837403", result);
    }
}


