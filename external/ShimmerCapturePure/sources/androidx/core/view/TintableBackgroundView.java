package androidx.core.view;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;

/* loaded from: classes.dex */
public interface TintableBackgroundView {
    ColorStateList getSupportBackgroundTintList();

    void setSupportBackgroundTintList(ColorStateList colorStateList);

    PorterDuff.Mode getSupportBackgroundTintMode();

    void setSupportBackgroundTintMode(PorterDuff.Mode mode);
}
