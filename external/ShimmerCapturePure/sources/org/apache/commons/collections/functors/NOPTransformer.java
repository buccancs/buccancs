package org.apache.commons.collections.functors;

import java.io.Serializable;

import org.apache.commons.collections.Transformer;

/* loaded from: classes5.dex */
public class NOPTransformer implements Transformer, Serializable {
    public static final Transformer INSTANCE = new NOPTransformer();
    private static final long serialVersionUID = 2133891748318574490L;

    private NOPTransformer() {
    }

    public static Transformer getInstance() {
        return INSTANCE;
    }

    @Override // org.apache.commons.collections.Transformer
    public Object transform(Object obj) {
        return obj;
    }
}
