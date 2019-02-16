package core;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

class SignatureGenerator {
    String generateSignatureFromInput(final String input, final String privateKey) {
        final String encoding = "UTF-8";
        final String HMAC_SHA1_ALGORITHM = "HmacSHA1";
        final String signature;
        try {
            final byte[] keyBytes = privateKey.getBytes(encoding);
            final byte[] uriBytes = input.getBytes(encoding);
            final Key signingKey = new SecretKeySpec(keyBytes, HMAC_SHA1_ALGORITHM);
            final Mac mac = Mac.getInstance(HMAC_SHA1_ALGORITHM);
            mac.init(signingKey);
            final byte[] signatureBytes = mac.doFinal(uriBytes);
            final StringBuffer buf = new StringBuffer(signatureBytes.length * 2);
            for (final byte signatureByte : signatureBytes) {
                final int intVal = signatureByte & 0xff;
                if (intVal < 0x10) {
                    buf.append("0");
                }
                buf.append(Integer.toHexString(intVal));
            }
            signature = buf.toString();
        } catch (final UnsupportedEncodingException | NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
        return signature.toUpperCase();
    }
}
