package androidx.core.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;

/* loaded from: classes.dex */
public interface TintableImageSourceView {
    ColorStateList getSupportImageTintList();

    void setSupportImageTintList(ColorStateList colorStateList);

    PorterDuff.Mode getSupportImageTintMode();

    void setSupportImageTintMode(PorterDuff.Mode mode);
}
