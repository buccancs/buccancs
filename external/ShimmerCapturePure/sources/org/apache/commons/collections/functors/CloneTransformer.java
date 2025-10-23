package org.apache.commons.collections.functors;

import java.io.Serializable;

import org.apache.commons.collections.Transformer;

/* loaded from: classes5.dex */
public class CloneTransformer implements Transformer, Serializable {
    public static final Transformer INSTANCE = new CloneTransformer();
    private static final long serialVersionUID = -8188742709499652567L;

    private CloneTransformer() {
    }

    public static Transformer getInstance() {
        return INSTANCE;
    }

    @Override // org.apache.commons.collections.Transformer
    public Object transform(Object obj) {
        if (obj == null) {
            return null;
        }
        return PrototypeFactory.getInstance(obj).create();
    }
}
