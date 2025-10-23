package org.apache.commons.collections.functors;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

import org.apache.commons.collections.FunctorException;
import org.apache.commons.collections.Transformer;

/* loaded from: classes5.dex */
public class InvokerTransformer implements Transformer, Serializable {
    private static final long serialVersionUID = -8653385846894047688L;
    private final Object[] iArgs;
    private final String iMethodName;
    private final Class[] iParamTypes;

    private InvokerTransformer(String str) {
        this.iMethodName = str;
        this.iParamTypes = null;
        this.iArgs = null;
    }

    public InvokerTransformer(String str, Class[] clsArr, Object[] objArr) {
        this.iMethodName = str;
        this.iParamTypes = clsArr;
        this.iArgs = objArr;
    }

    public static Transformer getInstance(String str) {
        if (str == null) {
            throw new IllegalArgumentException("The method to invoke must not be null");
        }
        return new InvokerTransformer(str);
    }

    public static Transformer getInstance(String str, Class[] clsArr, Object[] objArr) {
        if (str == null) {
            throw new IllegalArgumentException("The method to invoke must not be null");
        }
        if ((clsArr == null && objArr != null) || ((clsArr != null && objArr == null) || (clsArr != null && objArr != null && clsArr.length != objArr.length))) {
            throw new IllegalArgumentException("The parameter types must match the arguments");
        }
        if (clsArr == null || clsArr.length == 0) {
            return new InvokerTransformer(str);
        }
        return new InvokerTransformer(str, (Class[]) clsArr.clone(), (Object[]) objArr.clone());
    }

    @Override // org.apache.commons.collections.Transformer
    public Object transform(Object obj) {
        if (obj == null) {
            return null;
        }
        try {
            return obj.getClass().getMethod(this.iMethodName, this.iParamTypes).invoke(obj, this.iArgs);
        } catch (IllegalAccessException unused) {
            throw new FunctorException(new StringBuffer("InvokerTransformer: The method '").append(this.iMethodName).append("' on '").append(obj.getClass()).append("' cannot be accessed").toString());
        } catch (NoSuchMethodException unused2) {
            throw new FunctorException(new StringBuffer("InvokerTransformer: The method '").append(this.iMethodName).append("' on '").append(obj.getClass()).append("' does not exist").toString());
        } catch (InvocationTargetException e) {
            throw new FunctorException(new StringBuffer("InvokerTransformer: The method '").append(this.iMethodName).append("' on '").append(obj.getClass()).append("' threw an exception").toString(), e);
        }
    }
}
