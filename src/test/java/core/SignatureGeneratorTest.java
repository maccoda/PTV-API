package core;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SignatureGeneratorTest {
    @Test
    public void shouldGenerateConsistentSignature() {
        final String result = new SignatureGenerator().generateSignatureFromInput("/v2/Add/to/me?devid=2", "9c132d31-6a30-4cac-8d8b-8a1970834799");
        assertEquals("A719D44E60D504F26F1D60EE942F5C386D5AF4D5", result);

    }
}
