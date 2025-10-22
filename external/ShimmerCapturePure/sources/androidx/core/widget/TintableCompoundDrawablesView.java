package androidx.core.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;

/* loaded from: classes.dex */
public interface TintableCompoundDrawablesView {
    ColorStateList getSupportCompoundDrawablesTintList();

    void setSupportCompoundDrawablesTintList(ColorStateList colorStateList);

    PorterDuff.Mode getSupportCompoundDrawablesTintMode();

    void setSupportCompoundDrawablesTintMode(PorterDuff.Mode mode);
}
