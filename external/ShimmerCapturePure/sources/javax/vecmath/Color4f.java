package javax.vecmath;

import java.awt.Color;
import java.io.Serializable;

/* loaded from: classes4.dex */
public class Color4f extends Tuple4f implements Serializable {
    static final long serialVersionUID = 8577680141580006740L;

    public Color4f(float f, float f2, float f3, float f4) {
        super(f, f2, f3, f4);
    }

    public Color4f(float[] fArr) {
        super(fArr);
    }

    public Color4f(Color4f color4f) {
        super(color4f);
    }

    public Color4f(Tuple4f tuple4f) {
        super(tuple4f);
    }

    public Color4f(Tuple4d tuple4d) {
        super(tuple4d);
    }

    public Color4f(Color color) {
        super(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, color.getAlpha() / 255.0f);
    }

    public Color4f() {
    }

    public final void set(Color color) {
        this.x = color.getRed() / 255.0f;
        this.y = color.getGreen() / 255.0f;
        this.z = color.getBlue() / 255.0f;
        this.w = color.getAlpha() / 255.0f;
    }

    public final Color get() {
        return new Color(Math.round(this.x * 255.0f), Math.round(this.y * 255.0f), Math.round(this.z * 255.0f), Math.round(this.w * 255.0f));
    }
}
