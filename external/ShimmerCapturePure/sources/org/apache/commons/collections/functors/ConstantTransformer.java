package org.apache.commons.collections.functors;

import java.io.Serializable;

import org.apache.commons.collections.Transformer;

/* loaded from: classes5.dex */
public class ConstantTransformer implements Transformer, Serializable {
    public static final Transformer NULL_INSTANCE = new ConstantTransformer(null);
    private static final long serialVersionUID = 6374440726369055124L;
    private final Object iConstant;

    public ConstantTransformer(Object obj) {
        this.iConstant = obj;
    }

    public static Transformer getInstance(Object obj) {
        return obj == null ? NULL_INSTANCE : new ConstantTransformer(obj);
    }

    public Object getConstant() {
        return this.iConstant;
    }

    @Override // org.apache.commons.collections.Transformer
    public Object transform(Object obj) {
        return this.iConstant;
    }
}
