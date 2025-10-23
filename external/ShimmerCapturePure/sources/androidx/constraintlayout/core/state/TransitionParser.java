package androidx.constraintlayout.core.state;

import androidx.constraintlayout.core.motion.CustomVariable;
import androidx.constraintlayout.core.motion.utils.TypedBundle;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.core.parser.CLArray;
import androidx.constraintlayout.core.parser.CLContainer;
import androidx.constraintlayout.core.parser.CLElement;
import androidx.constraintlayout.core.parser.CLKey;
import androidx.constraintlayout.core.parser.CLNumber;
import androidx.constraintlayout.core.parser.CLObject;
import androidx.constraintlayout.core.parser.CLParsingException;
import androidx.constraintlayout.core.state.Transition;
import io.grpc.netty.shaded.io.netty.handler.codec.rtsp.RtspHeaders;

import java.lang.reflect.Array;

/* loaded from: classes.dex */
public class TransitionParser {
    @Deprecated
    public static void parse(CLObject cLObject, Transition transition, CorePixelDp corePixelDp) throws CLParsingException {
        parse(cLObject, transition);
    }

    /* JADX WARN: Removed duplicated region for block: B:52:0x00af  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void parse(androidx.constraintlayout.core.parser.CLObject r10, androidx.constraintlayout.core.state.Transition r11) throws androidx.constraintlayout.core.parser.CLParsingException {
        /*
            r11.resetProperties()
            java.lang.String r0 = "pathMotionArc"
            java.lang.String r0 = r10.getStringOrNull(r0)
            androidx.constraintlayout.core.motion.utils.TypedBundle r1 = new androidx.constraintlayout.core.motion.utils.TypedBundle
            r1.<init>()
            r2 = 1
            r3 = 0
            if (r0 == 0) goto L81
            r0.hashCode()
            int r4 = r0.hashCode()
            r5 = 5
            r6 = 4
            r7 = 3
            r8 = 2
            r9 = -1
            switch(r4) {
                case -1857024520: goto L59;
                case -1007052250: goto L4e;
                case 3145837: goto L43;
                case 3387192: goto L38;
                case 92611485: goto L2d;
                case 93621297: goto L22;
                default: goto L21;
            }
        L21:
            goto L63
        L22:
            java.lang.String r4 = "below"
            boolean r0 = r0.equals(r4)
            if (r0 != 0) goto L2b
            goto L63
        L2b:
            r9 = 5
            goto L63
        L2d:
            java.lang.String r4 = "above"
            boolean r0 = r0.equals(r4)
            if (r0 != 0) goto L36
            goto L63
        L36:
            r9 = 4
            goto L63
        L38:
            java.lang.String r4 = "none"
            boolean r0 = r0.equals(r4)
            if (r0 != 0) goto L41
            goto L63
        L41:
            r9 = 3
            goto L63
        L43:
            java.lang.String r4 = "flip"
            boolean r0 = r0.equals(r4)
            if (r0 != 0) goto L4c
            goto L63
        L4c:
            r9 = 2
            goto L63
        L4e:
            java.lang.String r4 = "startHorizontal"
            boolean r0 = r0.equals(r4)
            if (r0 != 0) goto L57
            goto L63
        L57:
            r9 = 1
            goto L63
        L59:
            java.lang.String r4 = "startVertical"
            boolean r0 = r0.equals(r4)
            if (r0 != 0) goto L62
            goto L63
        L62:
            r9 = 0
        L63:
            r0 = 509(0x1fd, float:7.13E-43)
            switch(r9) {
                case 0: goto L7d;
                case 1: goto L79;
                case 2: goto L75;
                case 3: goto L71;
                case 4: goto L6d;
                case 5: goto L69;
                default: goto L68;
            }
        L68:
            goto L80
        L69:
            r1.add(r0, r6)
            goto L80
        L6d:
            r1.add(r0, r5)
            goto L80
        L71:
            r1.add(r0, r3)
            goto L80
        L75:
            r1.add(r0, r7)
            goto L80
        L79:
            r1.add(r0, r8)
            goto L80
        L7d:
            r1.add(r0, r2)
        L80:
            r3 = 1
        L81:
            java.lang.String r0 = "interpolator"
            java.lang.String r0 = r10.getStringOrNull(r0)
            if (r0 == 0) goto L8f
            r3 = 705(0x2c1, float:9.88E-43)
            r1.add(r3, r0)
            goto L90
        L8f:
            r2 = r3
        L90:
            java.lang.String r0 = "staggered"
            float r0 = r10.getFloatOrNaN(r0)
            boolean r3 = java.lang.Float.isNaN(r0)
            if (r3 != 0) goto La2
            r2 = 706(0x2c2, float:9.9E-43)
            r1.add(r2, r0)
            goto La4
        La2:
            if (r2 == 0) goto La7
        La4:
            r11.setTransitionProperties(r1)
        La7:
            java.lang.String r0 = "onSwipe"
            androidx.constraintlayout.core.parser.CLObject r0 = r10.getObjectOrNull(r0)
            if (r0 == 0) goto Lb2
            parseOnSwipe(r0, r11)
        Lb2:
            parseKeyFrames(r10, r11)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.state.TransitionParser.parse(androidx.constraintlayout.core.parser.CLObject, androidx.constraintlayout.core.state.Transition):void");
    }

    private static void parseOnSwipe(CLContainer cLContainer, Transition transition) {
        String stringOrNull = cLContainer.getStringOrNull("anchor");
        int map = map(cLContainer.getStringOrNull("side"), Transition.OnSwipe.SIDES);
        int map2 = map(cLContainer.getStringOrNull("direction"), Transition.OnSwipe.DIRECTIONS);
        float floatOrNaN = cLContainer.getFloatOrNaN("scale");
        float floatOrNaN2 = cLContainer.getFloatOrNaN("threshold");
        float floatOrNaN3 = cLContainer.getFloatOrNaN("maxVelocity");
        float floatOrNaN4 = cLContainer.getFloatOrNaN("maxAccel");
        String stringOrNull2 = cLContainer.getStringOrNull("limitBounds");
        int map3 = map(cLContainer.getStringOrNull(RtspHeaders.Values.MODE), Transition.OnSwipe.MODE);
        int map4 = map(cLContainer.getStringOrNull("touchUp"), Transition.OnSwipe.TOUCH_UP);
        float floatOrNaN5 = cLContainer.getFloatOrNaN("springMass");
        float floatOrNaN6 = cLContainer.getFloatOrNaN("springStiffness");
        float floatOrNaN7 = cLContainer.getFloatOrNaN("springDamping");
        float floatOrNaN8 = cLContainer.getFloatOrNaN("stopThreshold");
        int map5 = map(cLContainer.getStringOrNull("springBoundary"), Transition.OnSwipe.BOUNDARY);
        String stringOrNull3 = cLContainer.getStringOrNull("around");
        Transition.OnSwipe onSwipeCreateOnSwipe = transition.createOnSwipe();
        onSwipeCreateOnSwipe.setAnchorId(stringOrNull);
        onSwipeCreateOnSwipe.setAnchorSide(map);
        onSwipeCreateOnSwipe.setDragDirection(map2);
        onSwipeCreateOnSwipe.setDragScale(floatOrNaN);
        onSwipeCreateOnSwipe.setDragThreshold(floatOrNaN2);
        onSwipeCreateOnSwipe.setMaxVelocity(floatOrNaN3);
        onSwipeCreateOnSwipe.setMaxAcceleration(floatOrNaN4);
        onSwipeCreateOnSwipe.setLimitBoundsTo(stringOrNull2);
        onSwipeCreateOnSwipe.setAutoCompleteMode(map3);
        onSwipeCreateOnSwipe.setOnTouchUp(map4);
        onSwipeCreateOnSwipe.setSpringMass(floatOrNaN5);
        onSwipeCreateOnSwipe.setSpringStiffness(floatOrNaN6);
        onSwipeCreateOnSwipe.setSpringDamping(floatOrNaN7);
        onSwipeCreateOnSwipe.setSpringStopThreshold(floatOrNaN8);
        onSwipeCreateOnSwipe.setSpringBoundary(map5);
        onSwipeCreateOnSwipe.setRotationCenterId(stringOrNull3);
    }

    private static int map(String str, String... strArr) {
        for (int i = 0; i < strArr.length; i++) {
            if (strArr[i].equals(str)) {
                return i;
            }
        }
        return 0;
    }

    private static void map(TypedBundle typedBundle, int i, String str, String... strArr) {
        for (int i2 = 0; i2 < strArr.length; i2++) {
            if (strArr[i2].equals(str)) {
                typedBundle.add(i, i2);
            }
        }
    }

    public static void parseKeyFrames(CLObject cLObject, Transition transition) throws CLParsingException {
        CLObject objectOrNull = cLObject.getObjectOrNull("KeyFrames");
        if (objectOrNull == null) {
            return;
        }
        CLArray arrayOrNull = objectOrNull.getArrayOrNull("KeyPositions");
        if (arrayOrNull != null) {
            for (int i = 0; i < arrayOrNull.size(); i++) {
                CLElement cLElement = arrayOrNull.get(i);
                if (cLElement instanceof CLObject) {
                    parseKeyPosition((CLObject) cLElement, transition);
                }
            }
        }
        CLArray arrayOrNull2 = objectOrNull.getArrayOrNull("KeyAttributes");
        if (arrayOrNull2 != null) {
            for (int i2 = 0; i2 < arrayOrNull2.size(); i2++) {
                CLElement cLElement2 = arrayOrNull2.get(i2);
                if (cLElement2 instanceof CLObject) {
                    parseKeyAttribute((CLObject) cLElement2, transition);
                }
            }
        }
        CLArray arrayOrNull3 = objectOrNull.getArrayOrNull("KeyCycles");
        if (arrayOrNull3 != null) {
            for (int i3 = 0; i3 < arrayOrNull3.size(); i3++) {
                CLElement cLElement3 = arrayOrNull3.get(i3);
                if (cLElement3 instanceof CLObject) {
                    parseKeyCycle((CLObject) cLElement3, transition);
                }
            }
        }
    }

    private static void parseKeyPosition(CLObject cLObject, Transition transition) throws CLParsingException {
        TypedBundle typedBundle = new TypedBundle();
        CLArray array = cLObject.getArray(TypedValues.AttributesType.S_TARGET);
        CLArray array2 = cLObject.getArray("frames");
        CLArray arrayOrNull = cLObject.getArrayOrNull("percentX");
        CLArray arrayOrNull2 = cLObject.getArrayOrNull("percentY");
        CLArray arrayOrNull3 = cLObject.getArrayOrNull("percentWidth");
        CLArray arrayOrNull4 = cLObject.getArrayOrNull("percentHeight");
        String stringOrNull = cLObject.getStringOrNull(TypedValues.TransitionType.S_PATH_MOTION_ARC);
        String stringOrNull2 = cLObject.getStringOrNull("transitionEasing");
        String stringOrNull3 = cLObject.getStringOrNull("curveFit");
        String stringOrNull4 = cLObject.getStringOrNull("type");
        if (stringOrNull4 == null) {
            stringOrNull4 = "parentRelative";
        }
        if (arrayOrNull == null || array2.size() == arrayOrNull.size()) {
            if (arrayOrNull2 == null || array2.size() == arrayOrNull2.size()) {
                int i = 0;
                while (i < array.size()) {
                    String string = array.getString(i);
                    int map = map(stringOrNull4, "deltaRelative", "pathRelative", "parentRelative");
                    typedBundle.clear();
                    typedBundle.add(510, map);
                    if (stringOrNull3 != null) {
                        map(typedBundle, TypedValues.PositionType.TYPE_CURVE_FIT, stringOrNull3, "spline", "linear");
                    }
                    typedBundle.addIfNotNull(501, stringOrNull2);
                    if (stringOrNull != null) {
                        map(typedBundle, 509, stringOrNull, "none", "startVertical", "startHorizontal", "flip", "below", "above");
                    }
                    int i2 = 0;
                    while (i2 < array2.size()) {
                        typedBundle.add(100, array2.getInt(i2));
                        set(typedBundle, TypedValues.PositionType.TYPE_PERCENT_X, arrayOrNull, i2);
                        set(typedBundle, 507, arrayOrNull2, i2);
                        set(typedBundle, 503, arrayOrNull3, i2);
                        set(typedBundle, 504, arrayOrNull4, i2);
                        transition.addKeyPosition(string, typedBundle);
                        i2++;
                        stringOrNull4 = stringOrNull4;
                    }
                    i++;
                    stringOrNull4 = stringOrNull4;
                }
            }
        }
    }

    private static void set(TypedBundle typedBundle, int i, CLArray cLArray, int i2) throws CLParsingException {
        if (cLArray != null) {
            typedBundle.add(i, cLArray.getFloat(i2));
        }
    }

    private static void parseKeyAttribute(CLObject cLObject, Transition transition) throws CLParsingException {
        CLArray arrayOrNull;
        CustomVariable[][] customVariableArr;
        CLObject cLObject2;
        int i;
        int i2;
        String[] strArr;
        CLArray arrayOrNull2 = cLObject.getArrayOrNull(TypedValues.AttributesType.S_TARGET);
        if (arrayOrNull2 == null || (arrayOrNull = cLObject.getArrayOrNull("frames")) == null) {
            return;
        }
        String stringOrNull = cLObject.getStringOrNull("transitionEasing");
        String[] strArr2 = {"scaleX", "scaleY", "translationX", "translationY", "translationZ", "rotationX", "rotationY", "rotationZ", "alpha"};
        int[] iArr = {311, 312, 304, 305, 306, 308, 309, 310, 303};
        boolean[] zArr = {false, false, true, true, true, false, false, false, false};
        int size = arrayOrNull.size();
        TypedBundle[] typedBundleArr = new TypedBundle[size];
        for (int i3 = 0; i3 < arrayOrNull.size(); i3++) {
            typedBundleArr[i3] = new TypedBundle();
        }
        int i4 = 0;
        for (int i5 = 9; i4 < i5; i5 = 9) {
            String str = strArr2[i4];
            int i6 = iArr[i4];
            boolean z = zArr[i4];
            CLArray arrayOrNull3 = cLObject.getArrayOrNull(str);
            if (arrayOrNull3 != null && arrayOrNull3.size() != size) {
                throw new CLParsingException("incorrect size for " + str + " array, not matching targets array!", cLObject);
            }
            if (arrayOrNull3 != null) {
                int i7 = 0;
                while (i7 < size) {
                    float pixels = arrayOrNull3.getFloat(i7);
                    String[] strArr3 = strArr2;
                    if (z) {
                        pixels = transition.mToPixel.toPixels(pixels);
                    }
                    typedBundleArr[i7].add(i6, pixels);
                    i7++;
                    strArr2 = strArr3;
                }
                strArr = strArr2;
            } else {
                strArr = strArr2;
                float floatOrNaN = cLObject.getFloatOrNaN(str);
                if (!Float.isNaN(floatOrNaN)) {
                    if (z) {
                        floatOrNaN = transition.mToPixel.toPixels(floatOrNaN);
                    }
                    for (int i8 = 0; i8 < size; i8++) {
                        typedBundleArr[i8].add(i6, floatOrNaN);
                    }
                }
            }
            i4++;
            strArr2 = strArr;
        }
        CLElement orNull = cLObject.getOrNull("custom");
        if (orNull == null || !(orNull instanceof CLObject)) {
            customVariableArr = null;
        } else {
            CLObject cLObject3 = (CLObject) orNull;
            int size2 = cLObject3.size();
            customVariableArr = (CustomVariable[][]) Array.newInstance((Class<?>) CustomVariable.class, arrayOrNull.size(), size2);
            int i9 = 0;
            while (i9 < size2) {
                CLKey cLKey = (CLKey) cLObject3.get(i9);
                String strContent = cLKey.content();
                if (cLKey.getValue() instanceof CLArray) {
                    CLArray cLArray = (CLArray) cLKey.getValue();
                    int size3 = cLArray.size();
                    if (size3 != size || size3 <= 0) {
                        cLObject2 = cLObject3;
                        i = size2;
                    } else if (cLArray.get(0) instanceof CLNumber) {
                        int i10 = 0;
                        while (i10 < size) {
                            customVariableArr[i10][i9] = new CustomVariable(strContent, 901, cLArray.get(i10).getFloat());
                            i10++;
                            cLObject3 = cLObject3;
                        }
                        cLObject2 = cLObject3;
                        i = size2;
                    } else {
                        cLObject2 = cLObject3;
                        int i11 = 0;
                        while (i11 < size) {
                            long colorString = ConstraintSetParser.parseColorString(cLArray.get(i11).content());
                            if (colorString != -1) {
                                i2 = size2;
                                customVariableArr[i11][i9] = new CustomVariable(strContent, TypedValues.Custom.TYPE_COLOR, (int) colorString);
                            } else {
                                i2 = size2;
                            }
                            i11++;
                            size2 = i2;
                        }
                        i = size2;
                    }
                } else {
                    cLObject2 = cLObject3;
                    i = size2;
                    CLElement value = cLKey.getValue();
                    if (value instanceof CLNumber) {
                        float f = value.getFloat();
                        for (int i12 = 0; i12 < size; i12++) {
                            customVariableArr[i12][i9] = new CustomVariable(strContent, 901, f);
                        }
                    } else {
                        long colorString2 = ConstraintSetParser.parseColorString(value.content());
                        if (colorString2 != -1) {
                            int i13 = 0;
                            while (i13 < size) {
                                customVariableArr[i13][i9] = new CustomVariable(strContent, TypedValues.Custom.TYPE_COLOR, (int) colorString2);
                                i13++;
                                colorString2 = colorString2;
                            }
                        }
                    }
                }
                i9++;
                cLObject3 = cLObject2;
                size2 = i;
            }
        }
        String stringOrNull2 = cLObject.getStringOrNull("curveFit");
        for (int i14 = 0; i14 < arrayOrNull2.size(); i14++) {
            for (int i15 = 0; i15 < size; i15++) {
                String string = arrayOrNull2.getString(i14);
                TypedBundle typedBundle = typedBundleArr[i15];
                if (stringOrNull2 != null) {
                    typedBundle.add(TypedValues.PositionType.TYPE_CURVE_FIT, map(stringOrNull2, "spline", "linear"));
                }
                typedBundle.addIfNotNull(501, stringOrNull);
                typedBundle.add(100, arrayOrNull.getInt(i15));
                transition.addKeyAttribute(string, typedBundle, customVariableArr != null ? customVariableArr[i15] : null);
            }
        }
    }

    private static void parseKeyCycle(CLObject cLObject, Transition transition) throws CLParsingException {
        int[] iArr;
        CLArray array = cLObject.getArray(TypedValues.AttributesType.S_TARGET);
        CLArray array2 = cLObject.getArray("frames");
        String stringOrNull = cLObject.getStringOrNull("transitionEasing");
        String[] strArr = {"scaleX", "scaleY", "translationX", "translationY", "translationZ", "rotationX", "rotationY", "rotationZ", "alpha", TypedValues.CycleType.S_WAVE_PERIOD, TypedValues.CycleType.S_WAVE_OFFSET, TypedValues.CycleType.S_WAVE_PHASE};
        int[] iArr2 = {311, 312, 304, 305, 306, 308, 309, 310, 403, 423, 424, TypedValues.CycleType.TYPE_WAVE_PHASE};
        int[] iArr3 = {0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 2, 0};
        int size = array2.size();
        TypedBundle[] typedBundleArr = new TypedBundle[size];
        for (int i = 0; i < size; i++) {
            typedBundleArr[i] = new TypedBundle();
        }
        boolean z = false;
        for (int i2 = 0; i2 < 12; i2++) {
            if (cLObject.has(strArr[i2]) && iArr3[i2] == 1) {
                z = true;
            }
        }
        int i3 = 0;
        for (int i4 = 12; i3 < i4; i4 = 12) {
            String str = strArr[i3];
            int i5 = iArr2[i3];
            int i6 = iArr3[i3];
            CLArray arrayOrNull = cLObject.getArrayOrNull(str);
            String[] strArr2 = strArr;
            if (arrayOrNull != null && arrayOrNull.size() != size) {
                throw new CLParsingException("incorrect size for $attrName array, not matching targets array!", cLObject);
            }
            if (arrayOrNull != null) {
                int i7 = 0;
                while (i7 < size) {
                    float pixels = arrayOrNull.getFloat(i7);
                    int[] iArr4 = iArr2;
                    if (i6 == 1) {
                        pixels = transition.mToPixel.toPixels(pixels);
                    } else if (i6 == 2 && z) {
                        pixels = transition.mToPixel.toPixels(pixels);
                    }
                    typedBundleArr[i7].add(i5, pixels);
                    i7++;
                    iArr2 = iArr4;
                }
                iArr = iArr2;
            } else {
                iArr = iArr2;
                float floatOrNaN = cLObject.getFloatOrNaN(str);
                if (!Float.isNaN(floatOrNaN)) {
                    if (i6 == 1) {
                        floatOrNaN = transition.mToPixel.toPixels(floatOrNaN);
                    } else if (i6 == 2 && z) {
                        floatOrNaN = transition.mToPixel.toPixels(floatOrNaN);
                    }
                    for (int i8 = 0; i8 < size; i8++) {
                        typedBundleArr[i8].add(i5, floatOrNaN);
                    }
                }
            }
            i3++;
            strArr = strArr2;
            iArr2 = iArr;
        }
        String stringOrNull2 = cLObject.getStringOrNull("curveFit");
        String stringOrNull3 = cLObject.getStringOrNull("easing");
        String stringOrNull4 = cLObject.getStringOrNull("waveShape");
        String stringOrNull5 = cLObject.getStringOrNull(TypedValues.CycleType.S_CUSTOM_WAVE_SHAPE);
        for (int i9 = 0; i9 < array.size(); i9++) {
            for (int i10 = 0; i10 < size; i10++) {
                String string = array.getString(i9);
                TypedBundle typedBundle = typedBundleArr[i10];
                if (stringOrNull2 != null) {
                    stringOrNull2.hashCode();
                    if (stringOrNull2.equals("linear")) {
                        typedBundle.add(401, 1);
                    } else if (stringOrNull2.equals("spline")) {
                        typedBundle.add(401, 0);
                    }
                }
                typedBundle.addIfNotNull(501, stringOrNull);
                if (stringOrNull3 != null) {
                    typedBundle.add(420, stringOrNull3);
                }
                if (stringOrNull4 != null) {
                    typedBundle.add(TypedValues.CycleType.TYPE_WAVE_SHAPE, stringOrNull4);
                }
                if (stringOrNull5 != null) {
                    typedBundle.add(422, stringOrNull5);
                }
                typedBundle.add(100, array2.getInt(i10));
                transition.addKeyCycle(string, typedBundle);
            }
        }
    }
}
