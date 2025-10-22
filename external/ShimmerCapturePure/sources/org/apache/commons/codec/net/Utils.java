package org.apache.commons.codec.net;

import org.apache.commons.codec.DecoderException;

/* loaded from: classes5.dex */
class Utils {
    Utils() {
    }

    static int digit16(byte b) throws DecoderException {
        int iDigit = Character.digit((char) b, 16);
        if (iDigit != -1) {
            return iDigit;
        }
        throw new DecoderException(new StringBuffer("Invalid URL encoding: not a valid digit (radix 16): ").append((int) b).toString());
    }
}
