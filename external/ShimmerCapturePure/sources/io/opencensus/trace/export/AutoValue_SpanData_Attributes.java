package io.opencensus.trace.export;

import io.opencensus.trace.AttributeValue;
import io.opencensus.trace.export.SpanData;

import java.util.Map;

import org.apache.commons.math3.geometry.VectorFormat;

/* loaded from: classes4.dex */
final class AutoValue_SpanData_Attributes extends SpanData.Attributes {
    private final Map<String, AttributeValue> attributeMap;
    private final int droppedAttributesCount;

    AutoValue_SpanData_Attributes(Map<String, AttributeValue> map, int i) {
        if (map == null) {
            throw new NullPointerException("Null attributeMap");
        }
        this.attributeMap = map;
        this.droppedAttributesCount = i;
    }

    @Override // io.opencensus.trace.export.SpanData.Attributes
    public Map<String, AttributeValue> getAttributeMap() {
        return this.attributeMap;
    }

    @Override // io.opencensus.trace.export.SpanData.Attributes
    public int getDroppedAttributesCount() {
        return this.droppedAttributesCount;
    }

    public String toString() {
        return "Attributes{attributeMap=" + this.attributeMap + ", droppedAttributesCount=" + this.droppedAttributesCount + VectorFormat.DEFAULT_SUFFIX;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SpanData.Attributes)) {
            return false;
        }
        SpanData.Attributes attributes = (SpanData.Attributes) obj;
        return this.attributeMap.equals(attributes.getAttributeMap()) && this.droppedAttributesCount == attributes.getDroppedAttributesCount();
    }

    public int hashCode() {
        return ((this.attributeMap.hashCode() ^ 1000003) * 1000003) ^ this.droppedAttributesCount;
    }
}
