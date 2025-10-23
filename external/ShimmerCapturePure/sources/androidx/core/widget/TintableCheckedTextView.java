package androidx.core.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;

/* loaded from: classes.dex */
public interface TintableCheckedTextView {
    ColorStateList getSupportCheckMarkTintList();

    void setSupportCheckMarkTintList(ColorStateList colorStateList);

    PorterDuff.Mode getSupportCheckMarkTintMode();

    void setSupportCheckMarkTintMode(PorterDuff.Mode mode);
}
