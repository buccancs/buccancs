package org.bouncycastle.crypto.engines;

/* loaded from: classes5.dex */
public class SEEDWrapEngine extends RFC3394WrapEngine {
    public SEEDWrapEngine() {
        super(new SEEDEngine());
    }
}
