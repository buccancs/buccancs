package io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.RepeatedFieldBuilderV3;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;
import io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.ValueMatcher;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes4.dex */
public final class MetadataMatcher extends GeneratedMessageV3 implements MetadataMatcherOrBuilder {
    public static final int FILTER_FIELD_NUMBER = 1;
    public static final int PATH_FIELD_NUMBER = 2;
    public static final int VALUE_FIELD_NUMBER = 3;
    private static final long serialVersionUID = 0;
    private static final MetadataMatcher DEFAULT_INSTANCE = new MetadataMatcher();
    private static final Parser<MetadataMatcher> PARSER = new AbstractParser<MetadataMatcher>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.MetadataMatcher.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public MetadataMatcher m33230parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new MetadataMatcher(codedInputStream, extensionRegistryLite);
        }
    };
    private volatile Object filter_;
    private byte memoizedIsInitialized;
    private List<PathSegment> path_;
    private ValueMatcher value_;

    private MetadataMatcher(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private MetadataMatcher() {
        this.memoizedIsInitialized = (byte) -1;
        this.filter_ = "";
        this.path_ = Collections.emptyList();
    }

    private MetadataMatcher(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        this();
        extensionRegistryLite.getClass();
        UnknownFieldSet.Builder builderNewBuilder = UnknownFieldSet.newBuilder();
        boolean z = false;
        boolean z2 = false;
        while (!z) {
            try {
                try {
                    int tag = codedInputStream.readTag();
                    if (tag != 0) {
                        if (tag == 10) {
                            this.filter_ = codedInputStream.readStringRequireUtf8();
                        } else if (tag == 18) {
                            if (!(z2 & true)) {
                                this.path_ = new ArrayList();
                                z2 |= true;
                            }
                            this.path_.add(codedInputStream.readMessage(PathSegment.parser(), extensionRegistryLite));
                        } else if (tag == 26) {
                            ValueMatcher valueMatcher = this.value_;
                            ValueMatcher.Builder builderM33550toBuilder = valueMatcher != null ? valueMatcher.m33550toBuilder() : null;
                            ValueMatcher valueMatcher2 = (ValueMatcher) codedInputStream.readMessage(ValueMatcher.parser(), extensionRegistryLite);
                            this.value_ = valueMatcher2;
                            if (builderM33550toBuilder != null) {
                                builderM33550toBuilder.mergeFrom(valueMatcher2);
                                this.value_ = builderM33550toBuilder.m33557buildPartial();
                            }
                        } else if (!parseUnknownField(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                        }
                    }
                    z = true;
                } catch (IOException e) {
                    throw new InvalidProtocolBufferException(e).setUnfinishedMessage(this);
                } catch (InvalidProtocolBufferException e2) {
                    throw e2.setUnfinishedMessage(this);
                }
            } finally {
                if (z2 & true) {
                    this.path_ = Collections.unmodifiableList(this.path_);
                }
                this.unknownFields = builderNewBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static MetadataMatcher getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<MetadataMatcher> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return MetadataProto.internal_static_envoy_type_matcher_MetadataMatcher_descriptor;
    }

    public static MetadataMatcher parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (MetadataMatcher) PARSER.parseFrom(byteBuffer);
    }

    public static MetadataMatcher parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (MetadataMatcher) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static MetadataMatcher parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (MetadataMatcher) PARSER.parseFrom(byteString);
    }

    public static MetadataMatcher parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (MetadataMatcher) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static MetadataMatcher parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (MetadataMatcher) PARSER.parseFrom(bArr);
    }

    public static MetadataMatcher parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (MetadataMatcher) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static MetadataMatcher parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static MetadataMatcher parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static MetadataMatcher parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static MetadataMatcher parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static MetadataMatcher parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static MetadataMatcher parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m33228toBuilder();
    }

    public static Builder newBuilder(MetadataMatcher metadataMatcher) {
        return DEFAULT_INSTANCE.m33228toBuilder().mergeFrom(metadataMatcher);
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public MetadataMatcher m33223getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public Parser<MetadataMatcher> getParserForType() {
        return PARSER;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.MetadataMatcherOrBuilder
    public List<PathSegment> getPathList() {
        return this.path_;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.MetadataMatcherOrBuilder
    public List<? extends PathSegmentOrBuilder> getPathOrBuilderList() {
        return this.path_;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.MetadataMatcherOrBuilder
    public boolean hasValue() {
        return this.value_ != null;
    }

    public final boolean isInitialized() {
        byte b = this.memoizedIsInitialized;
        if (b == 1) {
            return true;
        }
        if (b == 0) {
            return false;
        }
        this.memoizedIsInitialized = (byte) 1;
        return true;
    }

    protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
        return new MetadataMatcher();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return MetadataProto.internal_static_envoy_type_matcher_MetadataMatcher_fieldAccessorTable.ensureFieldAccessorsInitialized(MetadataMatcher.class, Builder.class);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.MetadataMatcherOrBuilder
    public String getFilter() {
        Object obj = this.filter_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.filter_ = stringUtf8;
        return stringUtf8;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.MetadataMatcherOrBuilder
    public ByteString getFilterBytes() {
        Object obj = this.filter_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.filter_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.MetadataMatcherOrBuilder
    public int getPathCount() {
        return this.path_.size();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.MetadataMatcherOrBuilder
    public PathSegment getPath(int i) {
        return this.path_.get(i);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.MetadataMatcherOrBuilder
    public PathSegmentOrBuilder getPathOrBuilder(int i) {
        return this.path_.get(i);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.MetadataMatcherOrBuilder
    public ValueMatcher getValue() {
        ValueMatcher valueMatcher = this.value_;
        return valueMatcher == null ? ValueMatcher.getDefaultInstance() : valueMatcher;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.MetadataMatcherOrBuilder
    public ValueMatcherOrBuilder getValueOrBuilder() {
        return getValue();
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (!getFilterBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.filter_);
        }
        for (int i = 0; i < this.path_.size(); i++) {
            codedOutputStream.writeMessage(2, this.path_.get(i));
        }
        if (this.value_ != null) {
            codedOutputStream.writeMessage(3, getValue());
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeStringSize = !getFilterBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.filter_) : 0;
        for (int i2 = 0; i2 < this.path_.size(); i2++) {
            iComputeStringSize += CodedOutputStream.computeMessageSize(2, this.path_.get(i2));
        }
        if (this.value_ != null) {
            iComputeStringSize += CodedOutputStream.computeMessageSize(3, getValue());
        }
        int serializedSize = iComputeStringSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof MetadataMatcher)) {
            return super.equals(obj);
        }
        MetadataMatcher metadataMatcher = (MetadataMatcher) obj;
        if (getFilter().equals(metadataMatcher.getFilter()) && getPathList().equals(metadataMatcher.getPathList()) && hasValue() == metadataMatcher.hasValue()) {
            return (!hasValue() || getValue().equals(metadataMatcher.getValue())) && this.unknownFields.equals(metadataMatcher.unknownFields);
        }
        return false;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = ((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getFilter().hashCode();
        if (getPathCount() > 0) {
            iHashCode = (((iHashCode * 37) + 2) * 53) + getPathList().hashCode();
        }
        if (hasValue()) {
            iHashCode = (((iHashCode * 37) + 3) * 53) + getValue().hashCode();
        }
        int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode2;
        return iHashCode2;
    }

    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m33225newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m33228toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public interface PathSegmentOrBuilder extends MessageOrBuilder {
        String getKey();

        ByteString getKeyBytes();

        PathSegment.SegmentCase getSegmentCase();
    }

    public static final class PathSegment extends GeneratedMessageV3 implements PathSegmentOrBuilder {
        public static final int KEY_FIELD_NUMBER = 1;
        private static final long serialVersionUID = 0;
        private static final PathSegment DEFAULT_INSTANCE = new PathSegment();
        private static final Parser<PathSegment> PARSER = new AbstractParser<PathSegment>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.MetadataMatcher.PathSegment.1
            /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
            public PathSegment m33276parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new PathSegment(codedInputStream, extensionRegistryLite);
            }
        };
        private byte memoizedIsInitialized;
        private int segmentCase_;
        private Object segment_;

        private PathSegment(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.segmentCase_ = 0;
            this.memoizedIsInitialized = (byte) -1;
        }

        private PathSegment() {
            this.segmentCase_ = 0;
            this.memoizedIsInitialized = (byte) -1;
        }

        private PathSegment(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            this();
            extensionRegistryLite.getClass();
            UnknownFieldSet.Builder builderNewBuilder = UnknownFieldSet.newBuilder();
            boolean z = false;
            while (!z) {
                try {
                    try {
                        int tag = codedInputStream.readTag();
                        if (tag != 0) {
                            if (tag == 10) {
                                String stringRequireUtf8 = codedInputStream.readStringRequireUtf8();
                                this.segmentCase_ = 1;
                                this.segment_ = stringRequireUtf8;
                            } else if (!parseUnknownField(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                            }
                        }
                        z = true;
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (IOException e2) {
                        throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                    }
                } finally {
                    this.unknownFields = builderNewBuilder.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static PathSegment getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<PathSegment> parser() {
            return PARSER;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return MetadataProto.internal_static_envoy_type_matcher_MetadataMatcher_PathSegment_descriptor;
        }

        public static PathSegment parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (PathSegment) PARSER.parseFrom(byteBuffer);
        }

        public static PathSegment parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (PathSegment) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static PathSegment parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (PathSegment) PARSER.parseFrom(byteString);
        }

        public static PathSegment parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (PathSegment) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static PathSegment parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (PathSegment) PARSER.parseFrom(bArr);
        }

        public static PathSegment parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (PathSegment) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static PathSegment parseFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static PathSegment parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static PathSegment parseDelimitedFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static PathSegment parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static PathSegment parseFrom(CodedInputStream codedInputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static PathSegment parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.m33274toBuilder();
        }

        public static Builder newBuilder(PathSegment pathSegment) {
            return DEFAULT_INSTANCE.m33274toBuilder().mergeFrom(pathSegment);
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public PathSegment m33269getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        public Parser<PathSegment> getParserForType() {
            return PARSER;
        }

        public final boolean isInitialized() {
            byte b = this.memoizedIsInitialized;
            if (b == 1) {
                return true;
            }
            if (b == 0) {
                return false;
            }
            this.memoizedIsInitialized = (byte) 1;
            return true;
        }

        protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unusedPrivateParameter) {
            return new PathSegment();
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return MetadataProto.internal_static_envoy_type_matcher_MetadataMatcher_PathSegment_fieldAccessorTable.ensureFieldAccessorsInitialized(PathSegment.class, Builder.class);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.MetadataMatcher.PathSegmentOrBuilder
        public SegmentCase getSegmentCase() {
            return SegmentCase.forNumber(this.segmentCase_);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.MetadataMatcher.PathSegmentOrBuilder
        public String getKey() {
            String str = this.segmentCase_ == 1 ? this.segment_ : "";
            if (str instanceof String) {
                return (String) str;
            }
            String stringUtf8 = ((ByteString) str).toStringUtf8();
            if (this.segmentCase_ == 1) {
                this.segment_ = stringUtf8;
            }
            return stringUtf8;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.MetadataMatcher.PathSegmentOrBuilder
        public ByteString getKeyBytes() {
            String str = this.segmentCase_ == 1 ? this.segment_ : "";
            if (str instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) str);
                if (this.segmentCase_ == 1) {
                    this.segment_ = byteStringCopyFromUtf8;
                }
                return byteStringCopyFromUtf8;
            }
            return (ByteString) str;
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (this.segmentCase_ == 1) {
                GeneratedMessageV3.writeString(codedOutputStream, 1, this.segment_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int iComputeStringSize = (this.segmentCase_ == 1 ? GeneratedMessageV3.computeStringSize(1, this.segment_) : 0) + this.unknownFields.getSerializedSize();
            this.memoizedSize = iComputeStringSize;
            return iComputeStringSize;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof PathSegment)) {
                return super.equals(obj);
            }
            PathSegment pathSegment = (PathSegment) obj;
            if (getSegmentCase().equals(pathSegment.getSegmentCase())) {
                return (this.segmentCase_ != 1 || getKey().equals(pathSegment.getKey())) && this.unknownFields.equals(pathSegment.unknownFields);
            }
            return false;
        }

        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int iHashCode = 779 + getDescriptor().hashCode();
            if (this.segmentCase_ == 1) {
                iHashCode = (((iHashCode * 37) + 1) * 53) + getKey().hashCode();
            }
            int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = iHashCode2;
            return iHashCode2;
        }

        /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m33271newBuilderForType() {
            return newBuilder();
        }

        /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m33274toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public enum SegmentCase implements Internal.EnumLite, AbstractMessageLite.InternalOneOfEnum {
            KEY(1),
            SEGMENT_NOT_SET(0);

            private final int value;

            SegmentCase(int i) {
                this.value = i;
            }

            public static SegmentCase forNumber(int i) {
                if (i == 0) {
                    return SEGMENT_NOT_SET;
                }
                if (i != 1) {
                    return null;
                }
                return KEY;
            }

            @Deprecated
            public static SegmentCase valueOf(int i) {
                return forNumber(i);
            }

            public int getNumber() {
                return this.value;
            }
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements PathSegmentOrBuilder {
            private int segmentCase_;
            private Object segment_;

            private Builder() {
                this.segmentCase_ = 0;
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.segmentCase_ = 0;
                maybeForceBuilderInitialization();
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return MetadataProto.internal_static_envoy_type_matcher_MetadataMatcher_PathSegment_descriptor;
            }

            public final boolean isInitialized() {
                return true;
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return MetadataProto.internal_static_envoy_type_matcher_MetadataMatcher_PathSegment_fieldAccessorTable.ensureFieldAccessorsInitialized(PathSegment.class, Builder.class);
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = PathSegment.alwaysUseFieldBuilders;
            }

            /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m33285clear() {
                super.clear();
                this.segmentCase_ = 0;
                this.segment_ = null;
                return this;
            }

            public Descriptors.Descriptor getDescriptorForType() {
                return MetadataProto.internal_static_envoy_type_matcher_MetadataMatcher_PathSegment_descriptor;
            }

            /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public PathSegment m33298getDefaultInstanceForType() {
                return PathSegment.getDefaultInstance();
            }

            /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
            /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public PathSegment m33279build() throws UninitializedMessageException {
                PathSegment pathSegmentM33281buildPartial = m33281buildPartial();
                if (pathSegmentM33281buildPartial.isInitialized()) {
                    return pathSegmentM33281buildPartial;
                }
                throw newUninitializedMessageException(pathSegmentM33281buildPartial);
            }

            /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public PathSegment m33281buildPartial() {
                PathSegment pathSegment = new PathSegment(this);
                if (this.segmentCase_ == 1) {
                    pathSegment.segment_ = this.segment_;
                }
                pathSegment.segmentCase_ = this.segmentCase_;
                onBuilt();
                return pathSegment;
            }

            /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m33297clone() {
                return (Builder) super.clone();
            }

            /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m33309setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.setField(fieldDescriptor, obj);
            }

            /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m33287clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.clearField(fieldDescriptor);
            }

            /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m33290clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.clearOneof(oneofDescriptor);
            }

            /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m33311setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m33277addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.addRepeatedField(fieldDescriptor, obj);
            }

            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m33302mergeFrom(Message message) {
                if (message instanceof PathSegment) {
                    return mergeFrom((PathSegment) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(PathSegment pathSegment) {
                if (pathSegment == PathSegment.getDefaultInstance()) {
                    return this;
                }
                if (AnonymousClass2.$SwitchMap$io$envoyproxy$envoy$type$matcher$MetadataMatcher$PathSegment$SegmentCase[pathSegment.getSegmentCase().ordinal()] == 1) {
                    this.segmentCase_ = 1;
                    this.segment_ = pathSegment.segment_;
                    onChanged();
                }
                m33307mergeUnknownFields(pathSegment.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.MetadataMatcher.PathSegment.Builder m33303mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.MetadataMatcher.PathSegment.access$700()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.MetadataMatcher$PathSegment r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.MetadataMatcher.PathSegment) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    if (r3 == 0) goto L10
                    r2.mergeFrom(r3)
                L10:
                    return r2
                L11:
                    r3 = move-exception
                    goto L21
                L13:
                    r3 = move-exception
                    com.google.protobuf.MessageLite r4 = r3.getUnfinishedMessage()     // Catch: java.lang.Throwable -> L11
                    io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.MetadataMatcher$PathSegment r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.MetadataMatcher.PathSegment) r4     // Catch: java.lang.Throwable -> L11
                    java.io.IOException r3 = r3.unwrapIOException()     // Catch: java.lang.Throwable -> L1f
                    throw r3     // Catch: java.lang.Throwable -> L1f
                L1f:
                    r3 = move-exception
                    r0 = r4
                L21:
                    if (r0 == 0) goto L26
                    r2.mergeFrom(r0)
                L26:
                    throw r3
                */
                throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.MetadataMatcher.PathSegment.Builder.m33303mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.MetadataMatcher$PathSegment$Builder");
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.MetadataMatcher.PathSegmentOrBuilder
            public SegmentCase getSegmentCase() {
                return SegmentCase.forNumber(this.segmentCase_);
            }

            public Builder clearSegment() {
                this.segmentCase_ = 0;
                this.segment_ = null;
                onChanged();
                return this;
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.MetadataMatcher.PathSegmentOrBuilder
            public String getKey() {
                String str = this.segmentCase_ == 1 ? this.segment_ : "";
                if (!(str instanceof String)) {
                    String stringUtf8 = ((ByteString) str).toStringUtf8();
                    if (this.segmentCase_ == 1) {
                        this.segment_ = stringUtf8;
                    }
                    return stringUtf8;
                }
                return (String) str;
            }

            public Builder setKey(String str) {
                str.getClass();
                this.segmentCase_ = 1;
                this.segment_ = str;
                onChanged();
                return this;
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.MetadataMatcher.PathSegmentOrBuilder
            public ByteString getKeyBytes() {
                String str = this.segmentCase_ == 1 ? this.segment_ : "";
                if (str instanceof String) {
                    ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) str);
                    if (this.segmentCase_ == 1) {
                        this.segment_ = byteStringCopyFromUtf8;
                    }
                    return byteStringCopyFromUtf8;
                }
                return (ByteString) str;
            }

            public Builder setKeyBytes(ByteString byteString) {
                byteString.getClass();
                PathSegment.checkByteStringIsUtf8(byteString);
                this.segmentCase_ = 1;
                this.segment_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearKey() {
                if (this.segmentCase_ == 1) {
                    this.segmentCase_ = 0;
                    this.segment_ = null;
                    onChanged();
                }
                return this;
            }

            /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m33313setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFields(unknownFieldSet);
            }

            /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m33307mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }
    }

    /* renamed from: io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.MetadataMatcher$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$io$envoyproxy$envoy$type$matcher$MetadataMatcher$PathSegment$SegmentCase;

        static {
            int[] iArr = new int[PathSegment.SegmentCase.values().length];
            $SwitchMap$io$envoyproxy$envoy$type$matcher$MetadataMatcher$PathSegment$SegmentCase = iArr;
            try {
                iArr[PathSegment.SegmentCase.KEY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$type$matcher$MetadataMatcher$PathSegment$SegmentCase[PathSegment.SegmentCase.SEGMENT_NOT_SET.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements MetadataMatcherOrBuilder {
        private int bitField0_;
        private Object filter_;
        private RepeatedFieldBuilderV3<PathSegment, PathSegment.Builder, PathSegmentOrBuilder> pathBuilder_;
        private List<PathSegment> path_;
        private SingleFieldBuilderV3<ValueMatcher, ValueMatcher.Builder, ValueMatcherOrBuilder> valueBuilder_;
        private ValueMatcher value_;

        private Builder() {
            this.filter_ = "";
            this.path_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.filter_ = "";
            this.path_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return MetadataProto.internal_static_envoy_type_matcher_MetadataMatcher_descriptor;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.MetadataMatcherOrBuilder
        public boolean hasValue() {
            return (this.valueBuilder_ == null && this.value_ == null) ? false : true;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return MetadataProto.internal_static_envoy_type_matcher_MetadataMatcher_fieldAccessorTable.ensureFieldAccessorsInitialized(MetadataMatcher.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            if (MetadataMatcher.alwaysUseFieldBuilders) {
                getPathFieldBuilder();
            }
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m33239clear() {
            super.clear();
            this.filter_ = "";
            RepeatedFieldBuilderV3<PathSegment, PathSegment.Builder, PathSegmentOrBuilder> repeatedFieldBuilderV3 = this.pathBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.path_ = Collections.emptyList();
                this.bitField0_ &= -2;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            if (this.valueBuilder_ == null) {
                this.value_ = null;
            } else {
                this.value_ = null;
                this.valueBuilder_ = null;
            }
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return MetadataProto.internal_static_envoy_type_matcher_MetadataMatcher_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public MetadataMatcher m33252getDefaultInstanceForType() {
            return MetadataMatcher.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public MetadataMatcher m33233build() throws UninitializedMessageException {
            MetadataMatcher metadataMatcherM33235buildPartial = m33235buildPartial();
            if (metadataMatcherM33235buildPartial.isInitialized()) {
                return metadataMatcherM33235buildPartial;
            }
            throw newUninitializedMessageException(metadataMatcherM33235buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public MetadataMatcher m33235buildPartial() {
            MetadataMatcher metadataMatcher = new MetadataMatcher(this);
            metadataMatcher.filter_ = this.filter_;
            RepeatedFieldBuilderV3<PathSegment, PathSegment.Builder, PathSegmentOrBuilder> repeatedFieldBuilderV3 = this.pathBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if ((this.bitField0_ & 1) != 0) {
                    this.path_ = Collections.unmodifiableList(this.path_);
                    this.bitField0_ &= -2;
                }
                metadataMatcher.path_ = this.path_;
            } else {
                metadataMatcher.path_ = repeatedFieldBuilderV3.build();
            }
            SingleFieldBuilderV3<ValueMatcher, ValueMatcher.Builder, ValueMatcherOrBuilder> singleFieldBuilderV3 = this.valueBuilder_;
            if (singleFieldBuilderV3 == null) {
                metadataMatcher.value_ = this.value_;
            } else {
                metadataMatcher.value_ = singleFieldBuilderV3.build();
            }
            onBuilt();
            return metadataMatcher;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m33251clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m33263setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m33241clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m33244clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m33265setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m33231addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m33256mergeFrom(Message message) {
            if (message instanceof MetadataMatcher) {
                return mergeFrom((MetadataMatcher) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(MetadataMatcher metadataMatcher) {
            if (metadataMatcher == MetadataMatcher.getDefaultInstance()) {
                return this;
            }
            if (!metadataMatcher.getFilter().isEmpty()) {
                this.filter_ = metadataMatcher.filter_;
                onChanged();
            }
            if (this.pathBuilder_ == null) {
                if (!metadataMatcher.path_.isEmpty()) {
                    if (this.path_.isEmpty()) {
                        this.path_ = metadataMatcher.path_;
                        this.bitField0_ &= -2;
                    } else {
                        ensurePathIsMutable();
                        this.path_.addAll(metadataMatcher.path_);
                    }
                    onChanged();
                }
            } else if (!metadataMatcher.path_.isEmpty()) {
                if (!this.pathBuilder_.isEmpty()) {
                    this.pathBuilder_.addAllMessages(metadataMatcher.path_);
                } else {
                    this.pathBuilder_.dispose();
                    this.pathBuilder_ = null;
                    this.path_ = metadataMatcher.path_;
                    this.bitField0_ &= -2;
                    this.pathBuilder_ = MetadataMatcher.alwaysUseFieldBuilders ? getPathFieldBuilder() : null;
                }
            }
            if (metadataMatcher.hasValue()) {
                mergeValue(metadataMatcher.getValue());
            }
            m33261mergeUnknownFields(metadataMatcher.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.MetadataMatcher.Builder m33257mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.MetadataMatcher.access$1900()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.MetadataMatcher r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.MetadataMatcher) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                if (r3 == 0) goto L10
                r2.mergeFrom(r3)
            L10:
                return r2
            L11:
                r3 = move-exception
                goto L21
            L13:
                r3 = move-exception
                com.google.protobuf.MessageLite r4 = r3.getUnfinishedMessage()     // Catch: java.lang.Throwable -> L11
                io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.MetadataMatcher r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.MetadataMatcher) r4     // Catch: java.lang.Throwable -> L11
                java.io.IOException r3 = r3.unwrapIOException()     // Catch: java.lang.Throwable -> L1f
                throw r3     // Catch: java.lang.Throwable -> L1f
            L1f:
                r3 = move-exception
                r0 = r4
            L21:
                if (r0 == 0) goto L26
                r2.mergeFrom(r0)
            L26:
                throw r3
            */
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.MetadataMatcher.Builder.m33257mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.MetadataMatcher$Builder");
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.MetadataMatcherOrBuilder
        public String getFilter() {
            Object obj = this.filter_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.filter_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        public Builder setFilter(String str) {
            str.getClass();
            this.filter_ = str;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.MetadataMatcherOrBuilder
        public ByteString getFilterBytes() {
            Object obj = this.filter_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.filter_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setFilterBytes(ByteString byteString) {
            byteString.getClass();
            MetadataMatcher.checkByteStringIsUtf8(byteString);
            this.filter_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearFilter() {
            this.filter_ = MetadataMatcher.getDefaultInstance().getFilter();
            onChanged();
            return this;
        }

        private void ensurePathIsMutable() {
            if ((this.bitField0_ & 1) == 0) {
                this.path_ = new ArrayList(this.path_);
                this.bitField0_ |= 1;
            }
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.MetadataMatcherOrBuilder
        public List<PathSegment> getPathList() {
            RepeatedFieldBuilderV3<PathSegment, PathSegment.Builder, PathSegmentOrBuilder> repeatedFieldBuilderV3 = this.pathBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.path_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.MetadataMatcherOrBuilder
        public int getPathCount() {
            RepeatedFieldBuilderV3<PathSegment, PathSegment.Builder, PathSegmentOrBuilder> repeatedFieldBuilderV3 = this.pathBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.path_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.MetadataMatcherOrBuilder
        public PathSegment getPath(int i) {
            RepeatedFieldBuilderV3<PathSegment, PathSegment.Builder, PathSegmentOrBuilder> repeatedFieldBuilderV3 = this.pathBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.path_.get(i);
            }
            return repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setPath(int i, PathSegment pathSegment) {
            RepeatedFieldBuilderV3<PathSegment, PathSegment.Builder, PathSegmentOrBuilder> repeatedFieldBuilderV3 = this.pathBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                pathSegment.getClass();
                ensurePathIsMutable();
                this.path_.set(i, pathSegment);
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, pathSegment);
            }
            return this;
        }

        public Builder setPath(int i, PathSegment.Builder builder) {
            RepeatedFieldBuilderV3<PathSegment, PathSegment.Builder, PathSegmentOrBuilder> repeatedFieldBuilderV3 = this.pathBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensurePathIsMutable();
                this.path_.set(i, builder.m33279build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.m33279build());
            }
            return this;
        }

        public Builder addPath(PathSegment pathSegment) {
            RepeatedFieldBuilderV3<PathSegment, PathSegment.Builder, PathSegmentOrBuilder> repeatedFieldBuilderV3 = this.pathBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                pathSegment.getClass();
                ensurePathIsMutable();
                this.path_.add(pathSegment);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(pathSegment);
            }
            return this;
        }

        public Builder addPath(int i, PathSegment pathSegment) {
            RepeatedFieldBuilderV3<PathSegment, PathSegment.Builder, PathSegmentOrBuilder> repeatedFieldBuilderV3 = this.pathBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                pathSegment.getClass();
                ensurePathIsMutable();
                this.path_.add(i, pathSegment);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, pathSegment);
            }
            return this;
        }

        public Builder addPath(PathSegment.Builder builder) {
            RepeatedFieldBuilderV3<PathSegment, PathSegment.Builder, PathSegmentOrBuilder> repeatedFieldBuilderV3 = this.pathBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensurePathIsMutable();
                this.path_.add(builder.m33279build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.m33279build());
            }
            return this;
        }

        public Builder addPath(int i, PathSegment.Builder builder) {
            RepeatedFieldBuilderV3<PathSegment, PathSegment.Builder, PathSegmentOrBuilder> repeatedFieldBuilderV3 = this.pathBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensurePathIsMutable();
                this.path_.add(i, builder.m33279build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.m33279build());
            }
            return this;
        }

        public Builder addAllPath(Iterable<? extends PathSegment> iterable) {
            RepeatedFieldBuilderV3<PathSegment, PathSegment.Builder, PathSegmentOrBuilder> repeatedFieldBuilderV3 = this.pathBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensurePathIsMutable();
                AbstractMessageLite.Builder.addAll(iterable, this.path_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearPath() {
            RepeatedFieldBuilderV3<PathSegment, PathSegment.Builder, PathSegmentOrBuilder> repeatedFieldBuilderV3 = this.pathBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.path_ = Collections.emptyList();
                this.bitField0_ &= -2;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removePath(int i) {
            RepeatedFieldBuilderV3<PathSegment, PathSegment.Builder, PathSegmentOrBuilder> repeatedFieldBuilderV3 = this.pathBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensurePathIsMutable();
                this.path_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public PathSegment.Builder getPathBuilder(int i) {
            return getPathFieldBuilder().getBuilder(i);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.MetadataMatcherOrBuilder
        public PathSegmentOrBuilder getPathOrBuilder(int i) {
            RepeatedFieldBuilderV3<PathSegment, PathSegment.Builder, PathSegmentOrBuilder> repeatedFieldBuilderV3 = this.pathBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.path_.get(i);
            }
            return (PathSegmentOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.MetadataMatcherOrBuilder
        public List<? extends PathSegmentOrBuilder> getPathOrBuilderList() {
            RepeatedFieldBuilderV3<PathSegment, PathSegment.Builder, PathSegmentOrBuilder> repeatedFieldBuilderV3 = this.pathBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.path_);
        }

        public PathSegment.Builder addPathBuilder() {
            return getPathFieldBuilder().addBuilder(PathSegment.getDefaultInstance());
        }

        public PathSegment.Builder addPathBuilder(int i) {
            return getPathFieldBuilder().addBuilder(i, PathSegment.getDefaultInstance());
        }

        public List<PathSegment.Builder> getPathBuilderList() {
            return getPathFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<PathSegment, PathSegment.Builder, PathSegmentOrBuilder> getPathFieldBuilder() {
            if (this.pathBuilder_ == null) {
                this.pathBuilder_ = new RepeatedFieldBuilderV3<>(this.path_, (this.bitField0_ & 1) != 0, getParentForChildren(), isClean());
                this.path_ = null;
            }
            return this.pathBuilder_;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.MetadataMatcherOrBuilder
        public ValueMatcher getValue() {
            SingleFieldBuilderV3<ValueMatcher, ValueMatcher.Builder, ValueMatcherOrBuilder> singleFieldBuilderV3 = this.valueBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            ValueMatcher valueMatcher = this.value_;
            return valueMatcher == null ? ValueMatcher.getDefaultInstance() : valueMatcher;
        }

        public Builder setValue(ValueMatcher valueMatcher) {
            SingleFieldBuilderV3<ValueMatcher, ValueMatcher.Builder, ValueMatcherOrBuilder> singleFieldBuilderV3 = this.valueBuilder_;
            if (singleFieldBuilderV3 == null) {
                valueMatcher.getClass();
                this.value_ = valueMatcher;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(valueMatcher);
            }
            return this;
        }

        public Builder setValue(ValueMatcher.Builder builder) {
            SingleFieldBuilderV3<ValueMatcher, ValueMatcher.Builder, ValueMatcherOrBuilder> singleFieldBuilderV3 = this.valueBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.value_ = builder.m33555build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m33555build());
            }
            return this;
        }

        public Builder mergeValue(ValueMatcher valueMatcher) {
            SingleFieldBuilderV3<ValueMatcher, ValueMatcher.Builder, ValueMatcherOrBuilder> singleFieldBuilderV3 = this.valueBuilder_;
            if (singleFieldBuilderV3 == null) {
                ValueMatcher valueMatcher2 = this.value_;
                if (valueMatcher2 != null) {
                    this.value_ = ValueMatcher.newBuilder(valueMatcher2).mergeFrom(valueMatcher).m33557buildPartial();
                } else {
                    this.value_ = valueMatcher;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(valueMatcher);
            }
            return this;
        }

        public Builder clearValue() {
            if (this.valueBuilder_ == null) {
                this.value_ = null;
                onChanged();
            } else {
                this.value_ = null;
                this.valueBuilder_ = null;
            }
            return this;
        }

        public ValueMatcher.Builder getValueBuilder() {
            onChanged();
            return getValueFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.MetadataMatcherOrBuilder
        public ValueMatcherOrBuilder getValueOrBuilder() {
            SingleFieldBuilderV3<ValueMatcher, ValueMatcher.Builder, ValueMatcherOrBuilder> singleFieldBuilderV3 = this.valueBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (ValueMatcherOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            ValueMatcher valueMatcher = this.value_;
            return valueMatcher == null ? ValueMatcher.getDefaultInstance() : valueMatcher;
        }

        private SingleFieldBuilderV3<ValueMatcher, ValueMatcher.Builder, ValueMatcherOrBuilder> getValueFieldBuilder() {
            if (this.valueBuilder_ == null) {
                this.valueBuilder_ = new SingleFieldBuilderV3<>(getValue(), getParentForChildren(), isClean());
                this.value_ = null;
            }
            return this.valueBuilder_;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m33267setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m33261mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }
}
