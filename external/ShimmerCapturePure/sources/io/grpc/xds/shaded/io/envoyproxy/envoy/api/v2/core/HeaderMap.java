package io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core;

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
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.HeaderValue;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes3.dex */
public final class HeaderMap extends GeneratedMessageV3 implements HeaderMapOrBuilder {
    public static final int HEADERS_FIELD_NUMBER = 1;
    private static final long serialVersionUID = 0;
    private static final HeaderMap DEFAULT_INSTANCE = new HeaderMap();
    private static final Parser<HeaderMap> PARSER = new AbstractParser<HeaderMap>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.HeaderMap.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public HeaderMap m15412parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new HeaderMap(codedInputStream, extensionRegistryLite);
        }
    };
    private List<HeaderValue> headers_;
    private byte memoizedIsInitialized;

    private HeaderMap(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private HeaderMap() {
        this.memoizedIsInitialized = (byte) -1;
        this.headers_ = Collections.emptyList();
    }

    private HeaderMap(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            if (!(z2 & true)) {
                                this.headers_ = new ArrayList();
                                z2 |= true;
                            }
                            this.headers_.add(codedInputStream.readMessage(HeaderValue.parser(), extensionRegistryLite));
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
                    this.headers_ = Collections.unmodifiableList(this.headers_);
                }
                this.unknownFields = builderNewBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static HeaderMap getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<HeaderMap> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return BaseProto.internal_static_envoy_api_v2_core_HeaderMap_descriptor;
    }

    public static HeaderMap parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (HeaderMap) PARSER.parseFrom(byteBuffer);
    }

    public static HeaderMap parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (HeaderMap) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static HeaderMap parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (HeaderMap) PARSER.parseFrom(byteString);
    }

    public static HeaderMap parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (HeaderMap) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static HeaderMap parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (HeaderMap) PARSER.parseFrom(bArr);
    }

    public static HeaderMap parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (HeaderMap) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static HeaderMap parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static HeaderMap parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static HeaderMap parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static HeaderMap parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static HeaderMap parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static HeaderMap parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m15410toBuilder();
    }

    public static Builder newBuilder(HeaderMap headerMap) {
        return DEFAULT_INSTANCE.m15410toBuilder().mergeFrom(headerMap);
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public HeaderMap m15405getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.HeaderMapOrBuilder
    public List<HeaderValue> getHeadersList() {
        return this.headers_;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.HeaderMapOrBuilder
    public List<? extends HeaderValueOrBuilder> getHeadersOrBuilderList() {
        return this.headers_;
    }

    public Parser<HeaderMap> getParserForType() {
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
        return new HeaderMap();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return BaseProto.internal_static_envoy_api_v2_core_HeaderMap_fieldAccessorTable.ensureFieldAccessorsInitialized(HeaderMap.class, Builder.class);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.HeaderMapOrBuilder
    public int getHeadersCount() {
        return this.headers_.size();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.HeaderMapOrBuilder
    public HeaderValue getHeaders(int i) {
        return this.headers_.get(i);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.HeaderMapOrBuilder
    public HeaderValueOrBuilder getHeadersOrBuilder(int i) {
        return this.headers_.get(i);
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        for (int i = 0; i < this.headers_.size(); i++) {
            codedOutputStream.writeMessage(1, this.headers_.get(i));
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeMessageSize = 0;
        for (int i2 = 0; i2 < this.headers_.size(); i2++) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(1, this.headers_.get(i2));
        }
        int serializedSize = iComputeMessageSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof HeaderMap)) {
            return super.equals(obj);
        }
        HeaderMap headerMap = (HeaderMap) obj;
        return getHeadersList().equals(headerMap.getHeadersList()) && this.unknownFields.equals(headerMap.unknownFields);
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = 779 + getDescriptor().hashCode();
        if (getHeadersCount() > 0) {
            iHashCode = (((iHashCode * 37) + 1) * 53) + getHeadersList().hashCode();
        }
        int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode2;
        return iHashCode2;
    }

    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m15407newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m15410toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements HeaderMapOrBuilder {
        private int bitField0_;
        private RepeatedFieldBuilderV3<HeaderValue, HeaderValue.Builder, HeaderValueOrBuilder> headersBuilder_;
        private List<HeaderValue> headers_;

        private Builder() {
            this.headers_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.headers_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return BaseProto.internal_static_envoy_api_v2_core_HeaderMap_descriptor;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return BaseProto.internal_static_envoy_api_v2_core_HeaderMap_fieldAccessorTable.ensureFieldAccessorsInitialized(HeaderMap.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            if (HeaderMap.alwaysUseFieldBuilders) {
                getHeadersFieldBuilder();
            }
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m15421clear() {
            super.clear();
            RepeatedFieldBuilderV3<HeaderValue, HeaderValue.Builder, HeaderValueOrBuilder> repeatedFieldBuilderV3 = this.headersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.headers_ = Collections.emptyList();
                this.bitField0_ &= -2;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return BaseProto.internal_static_envoy_api_v2_core_HeaderMap_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public HeaderMap m15434getDefaultInstanceForType() {
            return HeaderMap.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public HeaderMap m15415build() throws UninitializedMessageException {
            HeaderMap headerMapM15417buildPartial = m15417buildPartial();
            if (headerMapM15417buildPartial.isInitialized()) {
                return headerMapM15417buildPartial;
            }
            throw newUninitializedMessageException(headerMapM15417buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public HeaderMap m15417buildPartial() {
            HeaderMap headerMap = new HeaderMap(this);
            int i = this.bitField0_;
            RepeatedFieldBuilderV3<HeaderValue, HeaderValue.Builder, HeaderValueOrBuilder> repeatedFieldBuilderV3 = this.headersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if ((i & 1) != 0) {
                    this.headers_ = Collections.unmodifiableList(this.headers_);
                    this.bitField0_ &= -2;
                }
                headerMap.headers_ = this.headers_;
            } else {
                headerMap.headers_ = repeatedFieldBuilderV3.build();
            }
            onBuilt();
            return headerMap;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m15433clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m15445setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m15423clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m15426clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m15447setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m15413addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m15438mergeFrom(Message message) {
            if (message instanceof HeaderMap) {
                return mergeFrom((HeaderMap) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(HeaderMap headerMap) {
            if (headerMap == HeaderMap.getDefaultInstance()) {
                return this;
            }
            if (this.headersBuilder_ == null) {
                if (!headerMap.headers_.isEmpty()) {
                    if (this.headers_.isEmpty()) {
                        this.headers_ = headerMap.headers_;
                        this.bitField0_ &= -2;
                    } else {
                        ensureHeadersIsMutable();
                        this.headers_.addAll(headerMap.headers_);
                    }
                    onChanged();
                }
            } else if (!headerMap.headers_.isEmpty()) {
                if (!this.headersBuilder_.isEmpty()) {
                    this.headersBuilder_.addAllMessages(headerMap.headers_);
                } else {
                    this.headersBuilder_.dispose();
                    this.headersBuilder_ = null;
                    this.headers_ = headerMap.headers_;
                    this.bitField0_ &= -2;
                    this.headersBuilder_ = HeaderMap.alwaysUseFieldBuilders ? getHeadersFieldBuilder() : null;
                }
            }
            m15443mergeUnknownFields(headerMap.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.HeaderMap.Builder m15439mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.HeaderMap.access$700()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.HeaderMap r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.HeaderMap) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.HeaderMap r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.HeaderMap) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.HeaderMap.Builder.m15439mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.HeaderMap$Builder");
        }

        private void ensureHeadersIsMutable() {
            if ((this.bitField0_ & 1) == 0) {
                this.headers_ = new ArrayList(this.headers_);
                this.bitField0_ |= 1;
            }
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.HeaderMapOrBuilder
        public List<HeaderValue> getHeadersList() {
            RepeatedFieldBuilderV3<HeaderValue, HeaderValue.Builder, HeaderValueOrBuilder> repeatedFieldBuilderV3 = this.headersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.headers_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.HeaderMapOrBuilder
        public int getHeadersCount() {
            RepeatedFieldBuilderV3<HeaderValue, HeaderValue.Builder, HeaderValueOrBuilder> repeatedFieldBuilderV3 = this.headersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.headers_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.HeaderMapOrBuilder
        public HeaderValue getHeaders(int i) {
            RepeatedFieldBuilderV3<HeaderValue, HeaderValue.Builder, HeaderValueOrBuilder> repeatedFieldBuilderV3 = this.headersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.headers_.get(i);
            }
            return repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setHeaders(int i, HeaderValue headerValue) {
            RepeatedFieldBuilderV3<HeaderValue, HeaderValue.Builder, HeaderValueOrBuilder> repeatedFieldBuilderV3 = this.headersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                headerValue.getClass();
                ensureHeadersIsMutable();
                this.headers_.set(i, headerValue);
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, headerValue);
            }
            return this;
        }

        public Builder setHeaders(int i, HeaderValue.Builder builder) {
            RepeatedFieldBuilderV3<HeaderValue, HeaderValue.Builder, HeaderValueOrBuilder> repeatedFieldBuilderV3 = this.headersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureHeadersIsMutable();
                this.headers_.set(i, builder.m15461build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.m15461build());
            }
            return this;
        }

        public Builder addHeaders(HeaderValue headerValue) {
            RepeatedFieldBuilderV3<HeaderValue, HeaderValue.Builder, HeaderValueOrBuilder> repeatedFieldBuilderV3 = this.headersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                headerValue.getClass();
                ensureHeadersIsMutable();
                this.headers_.add(headerValue);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(headerValue);
            }
            return this;
        }

        public Builder addHeaders(int i, HeaderValue headerValue) {
            RepeatedFieldBuilderV3<HeaderValue, HeaderValue.Builder, HeaderValueOrBuilder> repeatedFieldBuilderV3 = this.headersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                headerValue.getClass();
                ensureHeadersIsMutable();
                this.headers_.add(i, headerValue);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, headerValue);
            }
            return this;
        }

        public Builder addHeaders(HeaderValue.Builder builder) {
            RepeatedFieldBuilderV3<HeaderValue, HeaderValue.Builder, HeaderValueOrBuilder> repeatedFieldBuilderV3 = this.headersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureHeadersIsMutable();
                this.headers_.add(builder.m15461build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.m15461build());
            }
            return this;
        }

        public Builder addHeaders(int i, HeaderValue.Builder builder) {
            RepeatedFieldBuilderV3<HeaderValue, HeaderValue.Builder, HeaderValueOrBuilder> repeatedFieldBuilderV3 = this.headersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureHeadersIsMutable();
                this.headers_.add(i, builder.m15461build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.m15461build());
            }
            return this;
        }

        public Builder addAllHeaders(Iterable<? extends HeaderValue> iterable) {
            RepeatedFieldBuilderV3<HeaderValue, HeaderValue.Builder, HeaderValueOrBuilder> repeatedFieldBuilderV3 = this.headersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureHeadersIsMutable();
                AbstractMessageLite.Builder.addAll(iterable, this.headers_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearHeaders() {
            RepeatedFieldBuilderV3<HeaderValue, HeaderValue.Builder, HeaderValueOrBuilder> repeatedFieldBuilderV3 = this.headersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.headers_ = Collections.emptyList();
                this.bitField0_ &= -2;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeHeaders(int i) {
            RepeatedFieldBuilderV3<HeaderValue, HeaderValue.Builder, HeaderValueOrBuilder> repeatedFieldBuilderV3 = this.headersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureHeadersIsMutable();
                this.headers_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public HeaderValue.Builder getHeadersBuilder(int i) {
            return getHeadersFieldBuilder().getBuilder(i);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.HeaderMapOrBuilder
        public HeaderValueOrBuilder getHeadersOrBuilder(int i) {
            RepeatedFieldBuilderV3<HeaderValue, HeaderValue.Builder, HeaderValueOrBuilder> repeatedFieldBuilderV3 = this.headersBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.headers_.get(i);
            }
            return (HeaderValueOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.HeaderMapOrBuilder
        public List<? extends HeaderValueOrBuilder> getHeadersOrBuilderList() {
            RepeatedFieldBuilderV3<HeaderValue, HeaderValue.Builder, HeaderValueOrBuilder> repeatedFieldBuilderV3 = this.headersBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.headers_);
        }

        public HeaderValue.Builder addHeadersBuilder() {
            return getHeadersFieldBuilder().addBuilder(HeaderValue.getDefaultInstance());
        }

        public HeaderValue.Builder addHeadersBuilder(int i) {
            return getHeadersFieldBuilder().addBuilder(i, HeaderValue.getDefaultInstance());
        }

        public List<HeaderValue.Builder> getHeadersBuilderList() {
            return getHeadersFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<HeaderValue, HeaderValue.Builder, HeaderValueOrBuilder> getHeadersFieldBuilder() {
            if (this.headersBuilder_ == null) {
                this.headersBuilder_ = new RepeatedFieldBuilderV3<>(this.headers_, (this.bitField0_ & 1) != 0, getParentForChildren(), isClean());
                this.headers_ = null;
            }
            return this.headersBuilder_;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m15449setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m15443mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }
}
