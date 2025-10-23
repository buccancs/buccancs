package org.junit.runners.model;

import java.lang.reflect.Modifier;
import java.util.Iterator;
import java.util.List;

import org.junit.runners.model.FrameworkMember;

/* loaded from: classes2.dex */
public abstract class FrameworkMember<T extends FrameworkMember<T>> implements Annotatable {
    public abstract Class<?> getDeclaringClass();

    protected abstract int getModifiers();

    public abstract String getName();

    public abstract Class<?> getType();

    abstract boolean isShadowedBy(T t);

    boolean isShadowedBy(List<T> list) {
        Iterator<T> it2 = list.iterator();
        while (it2.hasNext()) {
            if (isShadowedBy((FrameworkMember<T>) it2.next())) {
                return true;
            }
        }
        return false;
    }

    public boolean isStatic() {
        return Modifier.isStatic(getModifiers());
    }

    public boolean isPublic() {
        return Modifier.isPublic(getModifiers());
    }
}
