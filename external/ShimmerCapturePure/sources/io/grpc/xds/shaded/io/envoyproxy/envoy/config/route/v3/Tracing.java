package io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.Parser;
import com.google.protobuf.RepeatedFieldBuilderV3;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;
import io.grpc.xds.shaded.io.envoyproxy.envoy.type.tracing.v3.CustomTag;
import io.grpc.xds.shaded.io.envoyproxy.envoy.type.tracing.v3.CustomTagOrBuilder;
import io.grpc.xds.shaded.io.envoyproxy.envoy.type.v3.FractionalPercent;
import io.grpc.xds.shaded.io.envoyproxy.envoy.type.v3.FractionalPercentOrBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes4.dex */
public final class Tracing extends GeneratedMessageV3 implements TracingOrBuilder {
    public static final int CLIENT_SAMPLING_FIELD_NUMBER = 1;
    public static final int CUSTOM_TAGS_FIELD_NUMBER = 4;
    public static final int OVERALL_SAMPLING_FIELD_NUMBER = 3;
    public static final int RANDOM_SAMPLING_FIELD_NUMBER = 2;
    private static final long serialVersionUID = 0;
    private static final Tracing DEFAULT_INSTANCE = new Tracing();
    private static final Parser<Tracing> PARSER = new AbstractParser<Tracing>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.Tracing.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public Tracing m29794parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new Tracing(codedInputStream, extensionRegistryLite);
        }
    };
    private FractionalPercent clientSampling_;
    private List<CustomTag> customTags_;
    private byte memoizedIsInitialized;
    private FractionalPercent overallSampling_;
    private FractionalPercent randomSampling_;

    private Tracing(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private Tracing() {
        this.memoizedIsInitialized = (byte) -1;
        this.customTags_ = Collections.emptyList();
    }

    private Tracing(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        FractionalPercent.Builder builderM35023toBuilder;
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
                            FractionalPercent fractionalPercent = this.clientSampling_;
                            builderM35023toBuilder = fractionalPercent != null ? fractionalPercent.m35023toBuilder() : null;
                            FractionalPercent fractionalPercent2 = (FractionalPercent) codedInputStream.readMessage(FractionalPercent.parser(), extensionRegistryLite);
                            this.clientSampling_ = fractionalPercent2;
                            if (builderM35023toBuilder != null) {
                                builderM35023toBuilder.mergeFrom(fractionalPercent2);
                                this.clientSampling_ = builderM35023toBuilder.m35030buildPartial();
                            }
                        } else if (tag == 18) {
                            FractionalPercent fractionalPercent3 = this.randomSampling_;
                            builderM35023toBuilder = fractionalPercent3 != null ? fractionalPercent3.m35023toBuilder() : null;
                            FractionalPercent fractionalPercent4 = (FractionalPercent) codedInputStream.readMessage(FractionalPercent.parser(), extensionRegistryLite);
                            this.randomSampling_ = fractionalPercent4;
                            if (builderM35023toBuilder != null) {
                                builderM35023toBuilder.mergeFrom(fractionalPercent4);
                                this.randomSampling_ = builderM35023toBuilder.m35030buildPartial();
                            }
                        } else if (tag == 26) {
                            FractionalPercent fractionalPercent5 = this.overallSampling_;
                            builderM35023toBuilder = fractionalPercent5 != null ? fractionalPercent5.m35023toBuilder() : null;
                            FractionalPercent fractionalPercent6 = (FractionalPercent) codedInputStream.readMessage(FractionalPercent.parser(), extensionRegistryLite);
                            this.overallSampling_ = fractionalPercent6;
                            if (builderM35023toBuilder != null) {
                                builderM35023toBuilder.mergeFrom(fractionalPercent6);
                                this.overallSampling_ = builderM35023toBuilder.m35030buildPartial();
                            }
                        } else if (tag == 34) {
                            if (!(z2 & true)) {
                                this.customTags_ = new ArrayList();
                                z2 |= true;
                            }
                            this.customTags_.add(codedInputStream.readMessage(CustomTag.parser(), extensionRegistryLite));
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
                    this.customTags_ = Collections.unmodifiableList(this.customTags_);
                }
                this.unknownFields = builderNewBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static Tracing getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Tracing> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return RouteComponentsProto.internal_static_envoy_config_route_v3_Tracing_descriptor;
    }

    public static Tracing parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (Tracing) PARSER.parseFrom(byteBuffer);
    }

    public static Tracing parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Tracing) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static Tracing parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (Tracing) PARSER.parseFrom(byteString);
    }

    public static Tracing parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Tracing) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static Tracing parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (Tracing) PARSER.parseFrom(bArr);
    }

    public static Tracing parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Tracing) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static Tracing parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static Tracing parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Tracing parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static Tracing parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static Tracing parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static Tracing parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m29792toBuilder();
    }

    public static Builder newBuilder(Tracing tracing) {
        return DEFAULT_INSTANCE.m29792toBuilder().mergeFrom(tracing);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.TracingOrBuilder
    public List<CustomTag> getCustomTagsList() {
        return this.customTags_;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.TracingOrBuilder
    public List<? extends CustomTagOrBuilder> getCustomTagsOrBuilderList() {
        return this.customTags_;
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Tracing m29787getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public Parser<Tracing> getParserForType() {
        return PARSER;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.TracingOrBuilder
    public boolean hasClientSampling() {
        return this.clientSampling_ != null;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.TracingOrBuilder
    public boolean hasOverallSampling() {
        return this.overallSampling_ != null;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.TracingOrBuilder
    public boolean hasRandomSampling() {
        return this.randomSampling_ != null;
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
        return new Tracing();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return RouteComponentsProto.internal_static_envoy_config_route_v3_Tracing_fieldAccessorTable.ensureFieldAccessorsInitialized(Tracing.class, Builder.class);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.TracingOrBuilder
    public FractionalPercent getClientSampling() {
        FractionalPercent fractionalPercent = this.clientSampling_;
        return fractionalPercent == null ? FractionalPercent.getDefaultInstance() : fractionalPercent;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.TracingOrBuilder
    public FractionalPercentOrBuilder getClientSamplingOrBuilder() {
        return getClientSampling();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.TracingOrBuilder
    public FractionalPercent getRandomSampling() {
        FractionalPercent fractionalPercent = this.randomSampling_;
        return fractionalPercent == null ? FractionalPercent.getDefaultInstance() : fractionalPercent;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.TracingOrBuilder
    public FractionalPercentOrBuilder getRandomSamplingOrBuilder() {
        return getRandomSampling();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.TracingOrBuilder
    public FractionalPercent getOverallSampling() {
        FractionalPercent fractionalPercent = this.overallSampling_;
        return fractionalPercent == null ? FractionalPercent.getDefaultInstance() : fractionalPercent;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.TracingOrBuilder
    public FractionalPercentOrBuilder getOverallSamplingOrBuilder() {
        return getOverallSampling();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.TracingOrBuilder
    public int getCustomTagsCount() {
        return this.customTags_.size();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.TracingOrBuilder
    public CustomTag getCustomTags(int i) {
        return this.customTags_.get(i);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.TracingOrBuilder
    public CustomTagOrBuilder getCustomTagsOrBuilder(int i) {
        return this.customTags_.get(i);
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (this.clientSampling_ != null) {
            codedOutputStream.writeMessage(1, getClientSampling());
        }
        if (this.randomSampling_ != null) {
            codedOutputStream.writeMessage(2, getRandomSampling());
        }
        if (this.overallSampling_ != null) {
            codedOutputStream.writeMessage(3, getOverallSampling());
        }
        for (int i = 0; i < this.customTags_.size(); i++) {
            codedOutputStream.writeMessage(4, this.customTags_.get(i));
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeMessageSize = this.clientSampling_ != null ? CodedOutputStream.computeMessageSize(1, getClientSampling()) : 0;
        if (this.randomSampling_ != null) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(2, getRandomSampling());
        }
        if (this.overallSampling_ != null) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(3, getOverallSampling());
        }
        for (int i2 = 0; i2 < this.customTags_.size(); i2++) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(4, this.customTags_.get(i2));
        }
        int serializedSize = iComputeMessageSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Tracing)) {
            return super.equals(obj);
        }
        Tracing tracing = (Tracing) obj;
        if (hasClientSampling() != tracing.hasClientSampling()) {
            return false;
        }
        if ((hasClientSampling() && !getClientSampling().equals(tracing.getClientSampling())) || hasRandomSampling() != tracing.hasRandomSampling()) {
            return false;
        }
        if ((!hasRandomSampling() || getRandomSampling().equals(tracing.getRandomSampling())) && hasOverallSampling() == tracing.hasOverallSampling()) {
            return (!hasOverallSampling() || getOverallSampling().equals(tracing.getOverallSampling())) && getCustomTagsList().equals(tracing.getCustomTagsList()) && this.unknownFields.equals(tracing.unknownFields);
        }
        return false;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = 779 + getDescriptor().hashCode();
        if (hasClientSampling()) {
            iHashCode = (((iHashCode * 37) + 1) * 53) + getClientSampling().hashCode();
        }
        if (hasRandomSampling()) {
            iHashCode = (((iHashCode * 37) + 2) * 53) + getRandomSampling().hashCode();
        }
        if (hasOverallSampling()) {
            iHashCode = (((iHashCode * 37) + 3) * 53) + getOverallSampling().hashCode();
        }
        if (getCustomTagsCount() > 0) {
            iHashCode = (((iHashCode * 37) + 4) * 53) + getCustomTagsList().hashCode();
        }
        int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode2;
        return iHashCode2;
    }

    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m29789newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m29792toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements TracingOrBuilder {
        private int bitField0_;
        private SingleFieldBuilderV3<FractionalPercent, FractionalPercent.Builder, FractionalPercentOrBuilder> clientSamplingBuilder_;
        private FractionalPercent clientSampling_;
        private RepeatedFieldBuilderV3<CustomTag, CustomTag.Builder, CustomTagOrBuilder> customTagsBuilder_;
        private List<CustomTag> customTags_;
        private SingleFieldBuilderV3<FractionalPercent, FractionalPercent.Builder, FractionalPercentOrBuilder> overallSamplingBuilder_;
        private FractionalPercent overallSampling_;
        private SingleFieldBuilderV3<FractionalPercent, FractionalPercent.Builder, FractionalPercentOrBuilder> randomSamplingBuilder_;
        private FractionalPercent randomSampling_;

        private Builder() {
            this.customTags_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.customTags_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return RouteComponentsProto.internal_static_envoy_config_route_v3_Tracing_descriptor;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.TracingOrBuilder
        public boolean hasClientSampling() {
            return (this.clientSamplingBuilder_ == null && this.clientSampling_ == null) ? false : true;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.TracingOrBuilder
        public boolean hasOverallSampling() {
            return (this.overallSamplingBuilder_ == null && this.overallSampling_ == null) ? false : true;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.TracingOrBuilder
        public boolean hasRandomSampling() {
            return (this.randomSamplingBuilder_ == null && this.randomSampling_ == null) ? false : true;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return RouteComponentsProto.internal_static_envoy_config_route_v3_Tracing_fieldAccessorTable.ensureFieldAccessorsInitialized(Tracing.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            if (Tracing.alwaysUseFieldBuilders) {
                getCustomTagsFieldBuilder();
            }
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m29803clear() {
            super.clear();
            if (this.clientSamplingBuilder_ == null) {
                this.clientSampling_ = null;
            } else {
                this.clientSampling_ = null;
                this.clientSamplingBuilder_ = null;
            }
            if (this.randomSamplingBuilder_ == null) {
                this.randomSampling_ = null;
            } else {
                this.randomSampling_ = null;
                this.randomSamplingBuilder_ = null;
            }
            if (this.overallSamplingBuilder_ == null) {
                this.overallSampling_ = null;
            } else {
                this.overallSampling_ = null;
                this.overallSamplingBuilder_ = null;
            }
            RepeatedFieldBuilderV3<CustomTag, CustomTag.Builder, CustomTagOrBuilder> repeatedFieldBuilderV3 = this.customTagsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.customTags_ = Collections.emptyList();
                this.bitField0_ &= -2;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return RouteComponentsProto.internal_static_envoy_config_route_v3_Tracing_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Tracing m29816getDefaultInstanceForType() {
            return Tracing.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Tracing m29797build() throws UninitializedMessageException {
            Tracing tracingM29799buildPartial = m29799buildPartial();
            if (tracingM29799buildPartial.isInitialized()) {
                return tracingM29799buildPartial;
            }
            throw newUninitializedMessageException(tracingM29799buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Tracing m29799buildPartial() {
            Tracing tracing = new Tracing(this);
            SingleFieldBuilderV3<FractionalPercent, FractionalPercent.Builder, FractionalPercentOrBuilder> singleFieldBuilderV3 = this.clientSamplingBuilder_;
            if (singleFieldBuilderV3 == null) {
                tracing.clientSampling_ = this.clientSampling_;
            } else {
                tracing.clientSampling_ = singleFieldBuilderV3.build();
            }
            SingleFieldBuilderV3<FractionalPercent, FractionalPercent.Builder, FractionalPercentOrBuilder> singleFieldBuilderV32 = this.randomSamplingBuilder_;
            if (singleFieldBuilderV32 == null) {
                tracing.randomSampling_ = this.randomSampling_;
            } else {
                tracing.randomSampling_ = singleFieldBuilderV32.build();
            }
            SingleFieldBuilderV3<FractionalPercent, FractionalPercent.Builder, FractionalPercentOrBuilder> singleFieldBuilderV33 = this.overallSamplingBuilder_;
            if (singleFieldBuilderV33 == null) {
                tracing.overallSampling_ = this.overallSampling_;
            } else {
                tracing.overallSampling_ = singleFieldBuilderV33.build();
            }
            RepeatedFieldBuilderV3<CustomTag, CustomTag.Builder, CustomTagOrBuilder> repeatedFieldBuilderV3 = this.customTagsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if ((this.bitField0_ & 1) != 0) {
                    this.customTags_ = Collections.unmodifiableList(this.customTags_);
                    this.bitField0_ &= -2;
                }
                tracing.customTags_ = this.customTags_;
            } else {
                tracing.customTags_ = repeatedFieldBuilderV3.build();
            }
            onBuilt();
            return tracing;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m29815clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m29827setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m29805clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m29808clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m29829setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m29795addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m29820mergeFrom(Message message) {
            if (message instanceof Tracing) {
                return mergeFrom((Tracing) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(Tracing tracing) {
            if (tracing == Tracing.getDefaultInstance()) {
                return this;
            }
            if (tracing.hasClientSampling()) {
                mergeClientSampling(tracing.getClientSampling());
            }
            if (tracing.hasRandomSampling()) {
                mergeRandomSampling(tracing.getRandomSampling());
            }
            if (tracing.hasOverallSampling()) {
                mergeOverallSampling(tracing.getOverallSampling());
            }
            if (this.customTagsBuilder_ == null) {
                if (!tracing.customTags_.isEmpty()) {
                    if (this.customTags_.isEmpty()) {
                        this.customTags_ = tracing.customTags_;
                        this.bitField0_ &= -2;
                    } else {
                        ensureCustomTagsIsMutable();
                        this.customTags_.addAll(tracing.customTags_);
                    }
                    onChanged();
                }
            } else if (!tracing.customTags_.isEmpty()) {
                if (!this.customTagsBuilder_.isEmpty()) {
                    this.customTagsBuilder_.addAllMessages(tracing.customTags_);
                } else {
                    this.customTagsBuilder_.dispose();
                    this.customTagsBuilder_ = null;
                    this.customTags_ = tracing.customTags_;
                    this.bitField0_ &= -2;
                    this.customTagsBuilder_ = Tracing.alwaysUseFieldBuilders ? getCustomTagsFieldBuilder() : null;
                }
            }
            m29825mergeUnknownFields(tracing.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.Tracing.Builder m29821mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.Tracing.access$1000()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.Tracing r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.Tracing) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.Tracing r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.Tracing) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.Tracing.Builder.m29821mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.Tracing$Builder");
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.TracingOrBuilder
        public FractionalPercent getClientSampling() {
            SingleFieldBuilderV3<FractionalPercent, FractionalPercent.Builder, FractionalPercentOrBuilder> singleFieldBuilderV3 = this.clientSamplingBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            FractionalPercent fractionalPercent = this.clientSampling_;
            return fractionalPercent == null ? FractionalPercent.getDefaultInstance() : fractionalPercent;
        }

        public Builder setClientSampling(FractionalPercent fractionalPercent) {
            SingleFieldBuilderV3<FractionalPercent, FractionalPercent.Builder, FractionalPercentOrBuilder> singleFieldBuilderV3 = this.clientSamplingBuilder_;
            if (singleFieldBuilderV3 == null) {
                fractionalPercent.getClass();
                this.clientSampling_ = fractionalPercent;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(fractionalPercent);
            }
            return this;
        }

        public Builder setClientSampling(FractionalPercent.Builder builder) {
            SingleFieldBuilderV3<FractionalPercent, FractionalPercent.Builder, FractionalPercentOrBuilder> singleFieldBuilderV3 = this.clientSamplingBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.clientSampling_ = builder.m35028build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m35028build());
            }
            return this;
        }

        public Builder mergeClientSampling(FractionalPercent fractionalPercent) {
            SingleFieldBuilderV3<FractionalPercent, FractionalPercent.Builder, FractionalPercentOrBuilder> singleFieldBuilderV3 = this.clientSamplingBuilder_;
            if (singleFieldBuilderV3 == null) {
                FractionalPercent fractionalPercent2 = this.clientSampling_;
                if (fractionalPercent2 != null) {
                    this.clientSampling_ = FractionalPercent.newBuilder(fractionalPercent2).mergeFrom(fractionalPercent).m35030buildPartial();
                } else {
                    this.clientSampling_ = fractionalPercent;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(fractionalPercent);
            }
            return this;
        }

        public Builder clearClientSampling() {
            if (this.clientSamplingBuilder_ == null) {
                this.clientSampling_ = null;
                onChanged();
            } else {
                this.clientSampling_ = null;
                this.clientSamplingBuilder_ = null;
            }
            return this;
        }

        public FractionalPercent.Builder getClientSamplingBuilder() {
            onChanged();
            return getClientSamplingFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.TracingOrBuilder
        public FractionalPercentOrBuilder getClientSamplingOrBuilder() {
            SingleFieldBuilderV3<FractionalPercent, FractionalPercent.Builder, FractionalPercentOrBuilder> singleFieldBuilderV3 = this.clientSamplingBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (FractionalPercentOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            FractionalPercent fractionalPercent = this.clientSampling_;
            return fractionalPercent == null ? FractionalPercent.getDefaultInstance() : fractionalPercent;
        }

        private SingleFieldBuilderV3<FractionalPercent, FractionalPercent.Builder, FractionalPercentOrBuilder> getClientSamplingFieldBuilder() {
            if (this.clientSamplingBuilder_ == null) {
                this.clientSamplingBuilder_ = new SingleFieldBuilderV3<>(getClientSampling(), getParentForChildren(), isClean());
                this.clientSampling_ = null;
            }
            return this.clientSamplingBuilder_;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.TracingOrBuilder
        public FractionalPercent getRandomSampling() {
            SingleFieldBuilderV3<FractionalPercent, FractionalPercent.Builder, FractionalPercentOrBuilder> singleFieldBuilderV3 = this.randomSamplingBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            FractionalPercent fractionalPercent = this.randomSampling_;
            return fractionalPercent == null ? FractionalPercent.getDefaultInstance() : fractionalPercent;
        }

        public Builder setRandomSampling(FractionalPercent fractionalPercent) {
            SingleFieldBuilderV3<FractionalPercent, FractionalPercent.Builder, FractionalPercentOrBuilder> singleFieldBuilderV3 = this.randomSamplingBuilder_;
            if (singleFieldBuilderV3 == null) {
                fractionalPercent.getClass();
                this.randomSampling_ = fractionalPercent;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(fractionalPercent);
            }
            return this;
        }

        public Builder setRandomSampling(FractionalPercent.Builder builder) {
            SingleFieldBuilderV3<FractionalPercent, FractionalPercent.Builder, FractionalPercentOrBuilder> singleFieldBuilderV3 = this.randomSamplingBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.randomSampling_ = builder.m35028build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m35028build());
            }
            return this;
        }

        public Builder mergeRandomSampling(FractionalPercent fractionalPercent) {
            SingleFieldBuilderV3<FractionalPercent, FractionalPercent.Builder, FractionalPercentOrBuilder> singleFieldBuilderV3 = this.randomSamplingBuilder_;
            if (singleFieldBuilderV3 == null) {
                FractionalPercent fractionalPercent2 = this.randomSampling_;
                if (fractionalPercent2 != null) {
                    this.randomSampling_ = FractionalPercent.newBuilder(fractionalPercent2).mergeFrom(fractionalPercent).m35030buildPartial();
                } else {
                    this.randomSampling_ = fractionalPercent;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(fractionalPercent);
            }
            return this;
        }

        public Builder clearRandomSampling() {
            if (this.randomSamplingBuilder_ == null) {
                this.randomSampling_ = null;
                onChanged();
            } else {
                this.randomSampling_ = null;
                this.randomSamplingBuilder_ = null;
            }
            return this;
        }

        public FractionalPercent.Builder getRandomSamplingBuilder() {
            onChanged();
            return getRandomSamplingFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.TracingOrBuilder
        public FractionalPercentOrBuilder getRandomSamplingOrBuilder() {
            SingleFieldBuilderV3<FractionalPercent, FractionalPercent.Builder, FractionalPercentOrBuilder> singleFieldBuilderV3 = this.randomSamplingBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (FractionalPercentOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            FractionalPercent fractionalPercent = this.randomSampling_;
            return fractionalPercent == null ? FractionalPercent.getDefaultInstance() : fractionalPercent;
        }

        private SingleFieldBuilderV3<FractionalPercent, FractionalPercent.Builder, FractionalPercentOrBuilder> getRandomSamplingFieldBuilder() {
            if (this.randomSamplingBuilder_ == null) {
                this.randomSamplingBuilder_ = new SingleFieldBuilderV3<>(getRandomSampling(), getParentForChildren(), isClean());
                this.randomSampling_ = null;
            }
            return this.randomSamplingBuilder_;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.TracingOrBuilder
        public FractionalPercent getOverallSampling() {
            SingleFieldBuilderV3<FractionalPercent, FractionalPercent.Builder, FractionalPercentOrBuilder> singleFieldBuilderV3 = this.overallSamplingBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            FractionalPercent fractionalPercent = this.overallSampling_;
            return fractionalPercent == null ? FractionalPercent.getDefaultInstance() : fractionalPercent;
        }

        public Builder setOverallSampling(FractionalPercent fractionalPercent) {
            SingleFieldBuilderV3<FractionalPercent, FractionalPercent.Builder, FractionalPercentOrBuilder> singleFieldBuilderV3 = this.overallSamplingBuilder_;
            if (singleFieldBuilderV3 == null) {
                fractionalPercent.getClass();
                this.overallSampling_ = fractionalPercent;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(fractionalPercent);
            }
            return this;
        }

        public Builder setOverallSampling(FractionalPercent.Builder builder) {
            SingleFieldBuilderV3<FractionalPercent, FractionalPercent.Builder, FractionalPercentOrBuilder> singleFieldBuilderV3 = this.overallSamplingBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.overallSampling_ = builder.m35028build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m35028build());
            }
            return this;
        }

        public Builder mergeOverallSampling(FractionalPercent fractionalPercent) {
            SingleFieldBuilderV3<FractionalPercent, FractionalPercent.Builder, FractionalPercentOrBuilder> singleFieldBuilderV3 = this.overallSamplingBuilder_;
            if (singleFieldBuilderV3 == null) {
                FractionalPercent fractionalPercent2 = this.overallSampling_;
                if (fractionalPercent2 != null) {
                    this.overallSampling_ = FractionalPercent.newBuilder(fractionalPercent2).mergeFrom(fractionalPercent).m35030buildPartial();
                } else {
                    this.overallSampling_ = fractionalPercent;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(fractionalPercent);
            }
            return this;
        }

        public Builder clearOverallSampling() {
            if (this.overallSamplingBuilder_ == null) {
                this.overallSampling_ = null;
                onChanged();
            } else {
                this.overallSampling_ = null;
                this.overallSamplingBuilder_ = null;
            }
            return this;
        }

        public FractionalPercent.Builder getOverallSamplingBuilder() {
            onChanged();
            return getOverallSamplingFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.TracingOrBuilder
        public FractionalPercentOrBuilder getOverallSamplingOrBuilder() {
            SingleFieldBuilderV3<FractionalPercent, FractionalPercent.Builder, FractionalPercentOrBuilder> singleFieldBuilderV3 = this.overallSamplingBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (FractionalPercentOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            FractionalPercent fractionalPercent = this.overallSampling_;
            return fractionalPercent == null ? FractionalPercent.getDefaultInstance() : fractionalPercent;
        }

        private SingleFieldBuilderV3<FractionalPercent, FractionalPercent.Builder, FractionalPercentOrBuilder> getOverallSamplingFieldBuilder() {
            if (this.overallSamplingBuilder_ == null) {
                this.overallSamplingBuilder_ = new SingleFieldBuilderV3<>(getOverallSampling(), getParentForChildren(), isClean());
                this.overallSampling_ = null;
            }
            return this.overallSamplingBuilder_;
        }

        private void ensureCustomTagsIsMutable() {
            if ((this.bitField0_ & 1) == 0) {
                this.customTags_ = new ArrayList(this.customTags_);
                this.bitField0_ |= 1;
            }
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.TracingOrBuilder
        public List<CustomTag> getCustomTagsList() {
            RepeatedFieldBuilderV3<CustomTag, CustomTag.Builder, CustomTagOrBuilder> repeatedFieldBuilderV3 = this.customTagsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.customTags_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.TracingOrBuilder
        public int getCustomTagsCount() {
            RepeatedFieldBuilderV3<CustomTag, CustomTag.Builder, CustomTagOrBuilder> repeatedFieldBuilderV3 = this.customTagsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.customTags_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.TracingOrBuilder
        public CustomTag getCustomTags(int i) {
            RepeatedFieldBuilderV3<CustomTag, CustomTag.Builder, CustomTagOrBuilder> repeatedFieldBuilderV3 = this.customTagsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.customTags_.get(i);
            }
            return repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setCustomTags(int i, CustomTag customTag) {
            RepeatedFieldBuilderV3<CustomTag, CustomTag.Builder, CustomTagOrBuilder> repeatedFieldBuilderV3 = this.customTagsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                customTag.getClass();
                ensureCustomTagsIsMutable();
                this.customTags_.set(i, customTag);
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, customTag);
            }
            return this;
        }

        public Builder setCustomTags(int i, CustomTag.Builder builder) {
            RepeatedFieldBuilderV3<CustomTag, CustomTag.Builder, CustomTagOrBuilder> repeatedFieldBuilderV3 = this.customTagsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureCustomTagsIsMutable();
                this.customTags_.set(i, builder.m34751build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.m34751build());
            }
            return this;
        }

        public Builder addCustomTags(CustomTag customTag) {
            RepeatedFieldBuilderV3<CustomTag, CustomTag.Builder, CustomTagOrBuilder> repeatedFieldBuilderV3 = this.customTagsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                customTag.getClass();
                ensureCustomTagsIsMutable();
                this.customTags_.add(customTag);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(customTag);
            }
            return this;
        }

        public Builder addCustomTags(int i, CustomTag customTag) {
            RepeatedFieldBuilderV3<CustomTag, CustomTag.Builder, CustomTagOrBuilder> repeatedFieldBuilderV3 = this.customTagsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                customTag.getClass();
                ensureCustomTagsIsMutable();
                this.customTags_.add(i, customTag);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, customTag);
            }
            return this;
        }

        public Builder addCustomTags(CustomTag.Builder builder) {
            RepeatedFieldBuilderV3<CustomTag, CustomTag.Builder, CustomTagOrBuilder> repeatedFieldBuilderV3 = this.customTagsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureCustomTagsIsMutable();
                this.customTags_.add(builder.m34751build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.m34751build());
            }
            return this;
        }

        public Builder addCustomTags(int i, CustomTag.Builder builder) {
            RepeatedFieldBuilderV3<CustomTag, CustomTag.Builder, CustomTagOrBuilder> repeatedFieldBuilderV3 = this.customTagsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureCustomTagsIsMutable();
                this.customTags_.add(i, builder.m34751build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.m34751build());
            }
            return this;
        }

        public Builder addAllCustomTags(Iterable<? extends CustomTag> iterable) {
            RepeatedFieldBuilderV3<CustomTag, CustomTag.Builder, CustomTagOrBuilder> repeatedFieldBuilderV3 = this.customTagsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureCustomTagsIsMutable();
                AbstractMessageLite.Builder.addAll(iterable, this.customTags_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearCustomTags() {
            RepeatedFieldBuilderV3<CustomTag, CustomTag.Builder, CustomTagOrBuilder> repeatedFieldBuilderV3 = this.customTagsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.customTags_ = Collections.emptyList();
                this.bitField0_ &= -2;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeCustomTags(int i) {
            RepeatedFieldBuilderV3<CustomTag, CustomTag.Builder, CustomTagOrBuilder> repeatedFieldBuilderV3 = this.customTagsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureCustomTagsIsMutable();
                this.customTags_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public CustomTag.Builder getCustomTagsBuilder(int i) {
            return getCustomTagsFieldBuilder().getBuilder(i);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.TracingOrBuilder
        public CustomTagOrBuilder getCustomTagsOrBuilder(int i) {
            RepeatedFieldBuilderV3<CustomTag, CustomTag.Builder, CustomTagOrBuilder> repeatedFieldBuilderV3 = this.customTagsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.customTags_.get(i);
            }
            return (CustomTagOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.route.v3.TracingOrBuilder
        public List<? extends CustomTagOrBuilder> getCustomTagsOrBuilderList() {
            RepeatedFieldBuilderV3<CustomTag, CustomTag.Builder, CustomTagOrBuilder> repeatedFieldBuilderV3 = this.customTagsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.customTags_);
        }

        public CustomTag.Builder addCustomTagsBuilder() {
            return getCustomTagsFieldBuilder().addBuilder(CustomTag.getDefaultInstance());
        }

        public CustomTag.Builder addCustomTagsBuilder(int i) {
            return getCustomTagsFieldBuilder().addBuilder(i, CustomTag.getDefaultInstance());
        }

        public List<CustomTag.Builder> getCustomTagsBuilderList() {
            return getCustomTagsFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<CustomTag, CustomTag.Builder, CustomTagOrBuilder> getCustomTagsFieldBuilder() {
            if (this.customTagsBuilder_ == null) {
                this.customTagsBuilder_ = new RepeatedFieldBuilderV3<>(this.customTags_, (this.bitField0_ & 1) != 0, getParentForChildren(), isClean());
                this.customTags_ = null;
            }
            return this.customTagsBuilder_;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m29831setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m29825mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }
}
