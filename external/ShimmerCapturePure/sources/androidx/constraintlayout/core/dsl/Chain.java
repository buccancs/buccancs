package androidx.constraintlayout.core.dsl;

import androidx.constraintlayout.core.dsl.Constraint;
import androidx.constraintlayout.core.dsl.Helper;
import com.shimmerresearch.verisense.UtilVerisenseDriver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes.dex */
public abstract class Chain extends Helper {
    protected static final Map<Style, String> styleMap;

    static {
        HashMap map = new HashMap();
        styleMap = map;
        map.put(Style.SPREAD, "'spread'");
        map.put(Style.SPREAD_INSIDE, "'spread_inside'");
        map.put(Style.PACKED, "'packed'");
    }

    protected ArrayList<Ref> references;
    private Style mStyle;

    public Chain(String str) {
        super(str, new Helper.HelperType(""));
        this.mStyle = null;
        this.references = new ArrayList<>();
    }

    public Style getStyle() {
        return this.mStyle;
    }

    public void setStyle(Style style) {
        this.mStyle = style;
        this.configMap.put("style", styleMap.get(style));
    }

    public String referencesToString() {
        if (this.references.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder("[");
        Iterator<Ref> it2 = this.references.iterator();
        while (it2.hasNext()) {
            sb.append(it2.next().toString());
        }
        sb.append("]");
        return sb.toString();
    }

    public Chain addReference(Ref ref) {
        this.references.add(ref);
        this.configMap.put("contains", referencesToString());
        return this;
    }

    public Chain addReference(String str) {
        return addReference(Ref.parseStringToRef(str));
    }

    public enum Style {
        PACKED,
        SPREAD,
        SPREAD_INSIDE
    }

    public class Anchor {
        final Constraint.Side mSide;
        Constraint.Anchor mConnection = null;
        int mGoneMargin = Integer.MIN_VALUE;
        int mMargin;

        Anchor(Constraint.Side side) {
            this.mSide = side;
        }

        public String getId() {
            return Chain.this.name;
        }

        public void build(StringBuilder sb) {
            if (this.mConnection != null) {
                sb.append(this.mSide.toString().toLowerCase());
                sb.append(":");
                sb.append(this);
                sb.append(",\n");
            }
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("[");
            if (this.mConnection != null) {
                sb.append("'");
                sb.append(this.mConnection.getId());
                sb.append("','");
                sb.append(this.mConnection.mSide.toString().toLowerCase());
                sb.append("'");
            }
            if (this.mMargin != 0) {
                sb.append(UtilVerisenseDriver.CSV_DELIMITER);
                sb.append(this.mMargin);
            }
            if (this.mGoneMargin != Integer.MIN_VALUE) {
                if (this.mMargin == 0) {
                    sb.append(",0,");
                    sb.append(this.mGoneMargin);
                } else {
                    sb.append(UtilVerisenseDriver.CSV_DELIMITER);
                    sb.append(this.mGoneMargin);
                }
            }
            sb.append("]");
            return sb.toString();
        }
    }
}
