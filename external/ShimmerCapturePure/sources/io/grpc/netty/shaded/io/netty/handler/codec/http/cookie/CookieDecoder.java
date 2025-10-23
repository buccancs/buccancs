package io.grpc.netty.shaded.io.netty.handler.codec.http.cookie;

import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLoggerFactory;

import java.nio.CharBuffer;

/* loaded from: classes3.dex */
public abstract class CookieDecoder {
    private final InternalLogger logger = InternalLoggerFactory.getInstance(getClass());
    private final boolean strict;

    protected CookieDecoder(boolean z) {
        this.strict = z;
    }

    protected DefaultCookie initCookie(String str, int i, int i2, int i3, int i4) {
        int iFirstInvalidCookieValueOctet;
        int iFirstInvalidCookieNameOctet;
        if (i == -1 || i == i2) {
            this.logger.debug("Skipping cookie with null name");
            return null;
        }
        if (i3 == -1) {
            this.logger.debug("Skipping cookie with null value");
            return null;
        }
        CharBuffer charBufferWrap = CharBuffer.wrap(str, i3, i4);
        CharSequence charSequenceUnwrapValue = CookieUtil.unwrapValue(charBufferWrap);
        if (charSequenceUnwrapValue == null) {
            this.logger.debug("Skipping cookie because starting quotes are not properly balanced in '{}'", charBufferWrap);
            return null;
        }
        String strSubstring = str.substring(i, i2);
        if (this.strict && (iFirstInvalidCookieNameOctet = CookieUtil.firstInvalidCookieNameOctet(strSubstring)) >= 0) {
            if (this.logger.isDebugEnabled()) {
                this.logger.debug("Skipping cookie because name '{}' contains invalid char '{}'", strSubstring, Character.valueOf(strSubstring.charAt(iFirstInvalidCookieNameOctet)));
            }
            return null;
        }
        boolean z = charSequenceUnwrapValue.length() != i4 - i3;
        if (this.strict && (iFirstInvalidCookieValueOctet = CookieUtil.firstInvalidCookieValueOctet(charSequenceUnwrapValue)) >= 0) {
            if (this.logger.isDebugEnabled()) {
                this.logger.debug("Skipping cookie because value '{}' contains invalid char '{}'", charSequenceUnwrapValue, Character.valueOf(charSequenceUnwrapValue.charAt(iFirstInvalidCookieValueOctet)));
            }
            return null;
        }
        DefaultCookie defaultCookie = new DefaultCookie(strSubstring, charSequenceUnwrapValue.toString());
        defaultCookie.setWrap(z);
        return defaultCookie;
    }
}
