package javax.vecmath;

import java.io.Serializable;

/* loaded from: classes4.dex */
public class TexCoord3f extends Tuple3f implements Serializable {
    static final long serialVersionUID = -3517736544731446513L;

    public TexCoord3f(float f, float f2, float f3) {
        super(f, f2, f3);
    }

    public TexCoord3f(float[] fArr) {
        super(fArr);
    }

    public TexCoord3f(TexCoord3f texCoord3f) {
        super(texCoord3f);
    }

    public TexCoord3f(Tuple3f tuple3f) {
        super(tuple3f);
    }

    public TexCoord3f(Tuple3d tuple3d) {
        super(tuple3d);
    }

    public TexCoord3f() {
    }
}
