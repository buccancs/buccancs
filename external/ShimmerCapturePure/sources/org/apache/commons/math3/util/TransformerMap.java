package org.apache.commons.math3.util;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.commons.math3.exception.MathIllegalArgumentException;

/* loaded from: classes5.dex */
public class TransformerMap implements NumberTransformer, Serializable {
    private static final long serialVersionUID = 4605318041528645258L;
    private NumberTransformer defaultTransformer;
    private Map<Class<?>, NumberTransformer> map;

    public TransformerMap() {
        this.defaultTransformer = null;
        this.map = null;
        this.map = new HashMap();
        this.defaultTransformer = new DefaultTransformer();
    }

    public boolean containsClass(Class<?> cls) {
        return this.map.containsKey(cls);
    }

    public boolean containsTransformer(NumberTransformer numberTransformer) {
        return this.map.containsValue(numberTransformer);
    }

    public NumberTransformer getTransformer(Class<?> cls) {
        return this.map.get(cls);
    }

    public NumberTransformer putTransformer(Class<?> cls, NumberTransformer numberTransformer) {
        return this.map.put(cls, numberTransformer);
    }

    public NumberTransformer removeTransformer(Class<?> cls) {
        return this.map.remove(cls);
    }

    public void clear() {
        this.map.clear();
    }

    public Set<Class<?>> classes() {
        return this.map.keySet();
    }

    public Collection<NumberTransformer> transformers() {
        return this.map.values();
    }

    @Override // org.apache.commons.math3.util.NumberTransformer
    public double transform(Object obj) throws MathIllegalArgumentException {
        if ((obj instanceof Number) || (obj instanceof String)) {
            return this.defaultTransformer.transform(obj);
        }
        NumberTransformer transformer = getTransformer(obj.getClass());
        if (transformer != null) {
            return transformer.transform(obj);
        }
        return Double.NaN;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TransformerMap)) {
            return false;
        }
        TransformerMap transformerMap = (TransformerMap) obj;
        if (!this.defaultTransformer.equals(transformerMap.defaultTransformer) || this.map.size() != transformerMap.map.size()) {
            return false;
        }
        for (Map.Entry<Class<?>, NumberTransformer> entry : this.map.entrySet()) {
            if (!entry.getValue().equals(transformerMap.map.get(entry.getKey()))) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        int iHashCode = this.defaultTransformer.hashCode();
        Iterator<NumberTransformer> it2 = this.map.values().iterator();
        while (it2.hasNext()) {
            iHashCode = (iHashCode * 31) + it2.next().hashCode();
        }
        return iHashCode;
    }
}
