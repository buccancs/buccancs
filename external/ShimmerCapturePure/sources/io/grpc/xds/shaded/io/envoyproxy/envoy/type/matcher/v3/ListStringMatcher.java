package io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.v3;

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
import io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.v3.StringMatcher;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes4.dex */
public final class ListStringMatcher extends GeneratedMessageV3 implements ListStringMatcherOrBuilder {
    public static final int PATTERNS_FIELD_NUMBER = 1;
    private static final ListStringMatcher DEFAULT_INSTANCE = new ListStringMatcher();
    private static final Parser<ListStringMatcher> PARSER = new AbstractParser<ListStringMatcher>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.v3.ListStringMatcher.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public ListStringMatcher m33644parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new ListStringMatcher(codedInputStream, extensionRegistryLite);
        }
    };
    private static final long serialVersionUID = 0;
    private byte memoizedIsInitialized;
    private List<StringMatcher> patterns_;

    private ListStringMatcher(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private ListStringMatcher() {
        this.memoizedIsInitialized = (byte) -1;
        this.patterns_ = Collections.emptyList();
    }

    private ListStringMatcher(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                this.patterns_ = new ArrayList();
                                z2 |= true;
                            }
                            this.patterns_.add(codedInputStream.readMessage(StringMatcher.parser(), extensionRegistryLite));
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
                    this.patterns_ = Collections.unmodifiableList(this.patterns_);
                }
                this.unknownFields = builderNewBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static ListStringMatcher getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ListStringMatcher> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return StringProto.internal_static_envoy_type_matcher_v3_ListStringMatcher_descriptor;
    }

    public static ListStringMatcher parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (ListStringMatcher) PARSER.parseFrom(byteBuffer);
    }

    public static ListStringMatcher parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ListStringMatcher) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static ListStringMatcher parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (ListStringMatcher) PARSER.parseFrom(byteString);
    }

    public static ListStringMatcher parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ListStringMatcher) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static ListStringMatcher parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (ListStringMatcher) PARSER.parseFrom(bArr);
    }

    public static ListStringMatcher parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ListStringMatcher) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static ListStringMatcher parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static ListStringMatcher parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ListStringMatcher parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static ListStringMatcher parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ListStringMatcher parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static ListStringMatcher parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m33642toBuilder();
    }

    public static Builder newBuilder(ListStringMatcher listStringMatcher) {
        return DEFAULT_INSTANCE.m33642toBuilder().mergeFrom(listStringMatcher);
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public ListStringMatcher m33637getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public Parser<ListStringMatcher> getParserForType() {
        return PARSER;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.v3.ListStringMatcherOrBuilder
    public List<StringMatcher> getPatternsList() {
        return this.patterns_;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.v3.ListStringMatcherOrBuilder
    public List<? extends StringMatcherOrBuilder> getPatternsOrBuilderList() {
        return this.patterns_;
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
        return new ListStringMatcher();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return StringProto.internal_static_envoy_type_matcher_v3_ListStringMatcher_fieldAccessorTable.ensureFieldAccessorsInitialized(ListStringMatcher.class, Builder.class);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.v3.ListStringMatcherOrBuilder
    public int getPatternsCount() {
        return this.patterns_.size();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.v3.ListStringMatcherOrBuilder
    public StringMatcher getPatterns(int i) {
        return this.patterns_.get(i);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.v3.ListStringMatcherOrBuilder
    public StringMatcherOrBuilder getPatternsOrBuilder(int i) {
        return this.patterns_.get(i);
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        for (int i = 0; i < this.patterns_.size(); i++) {
            codedOutputStream.writeMessage(1, this.patterns_.get(i));
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeMessageSize = 0;
        for (int i2 = 0; i2 < this.patterns_.size(); i2++) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(1, this.patterns_.get(i2));
        }
        int serializedSize = iComputeMessageSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ListStringMatcher)) {
            return super.equals(obj);
        }
        ListStringMatcher listStringMatcher = (ListStringMatcher) obj;
        return getPatternsList().equals(listStringMatcher.getPatternsList()) && this.unknownFields.equals(listStringMatcher.unknownFields);
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = 779 + getDescriptor().hashCode();
        if (getPatternsCount() > 0) {
            iHashCode = (((iHashCode * 37) + 1) * 53) + getPatternsList().hashCode();
        }
        int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode2;
        return iHashCode2;
    }

    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m33639newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m33642toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ListStringMatcherOrBuilder {
        private int bitField0_;
        private RepeatedFieldBuilderV3<StringMatcher, StringMatcher.Builder, StringMatcherOrBuilder> patternsBuilder_;
        private List<StringMatcher> patterns_;

        private Builder() {
            this.patterns_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.patterns_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return StringProto.internal_static_envoy_type_matcher_v3_ListStringMatcher_descriptor;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return StringProto.internal_static_envoy_type_matcher_v3_ListStringMatcher_fieldAccessorTable.ensureFieldAccessorsInitialized(ListStringMatcher.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            if (ListStringMatcher.alwaysUseFieldBuilders) {
                getPatternsFieldBuilder();
            }
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m33653clear() {
            super.clear();
            RepeatedFieldBuilderV3<StringMatcher, StringMatcher.Builder, StringMatcherOrBuilder> repeatedFieldBuilderV3 = this.patternsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.patterns_ = Collections.emptyList();
                this.bitField0_ &= -2;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return StringProto.internal_static_envoy_type_matcher_v3_ListStringMatcher_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public ListStringMatcher m33666getDefaultInstanceForType() {
            return ListStringMatcher.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public ListStringMatcher m33647build() throws UninitializedMessageException {
            ListStringMatcher listStringMatcherM33649buildPartial = m33649buildPartial();
            if (listStringMatcherM33649buildPartial.isInitialized()) {
                return listStringMatcherM33649buildPartial;
            }
            throw newUninitializedMessageException(listStringMatcherM33649buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public ListStringMatcher m33649buildPartial() {
            ListStringMatcher listStringMatcher = new ListStringMatcher(this);
            int i = this.bitField0_;
            RepeatedFieldBuilderV3<StringMatcher, StringMatcher.Builder, StringMatcherOrBuilder> repeatedFieldBuilderV3 = this.patternsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if ((i & 1) != 0) {
                    this.patterns_ = Collections.unmodifiableList(this.patterns_);
                    this.bitField0_ &= -2;
                }
                listStringMatcher.patterns_ = this.patterns_;
            } else {
                listStringMatcher.patterns_ = repeatedFieldBuilderV3.build();
            }
            onBuilt();
            return listStringMatcher;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m33665clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m33677setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m33655clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m33658clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m33679setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m33645addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m33670mergeFrom(Message message) {
            if (message instanceof ListStringMatcher) {
                return mergeFrom((ListStringMatcher) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(ListStringMatcher listStringMatcher) {
            if (listStringMatcher == ListStringMatcher.getDefaultInstance()) {
                return this;
            }
            if (this.patternsBuilder_ == null) {
                if (!listStringMatcher.patterns_.isEmpty()) {
                    if (this.patterns_.isEmpty()) {
                        this.patterns_ = listStringMatcher.patterns_;
                        this.bitField0_ &= -2;
                    } else {
                        ensurePatternsIsMutable();
                        this.patterns_.addAll(listStringMatcher.patterns_);
                    }
                    onChanged();
                }
            } else if (!listStringMatcher.patterns_.isEmpty()) {
                if (!this.patternsBuilder_.isEmpty()) {
                    this.patternsBuilder_.addAllMessages(listStringMatcher.patterns_);
                } else {
                    this.patternsBuilder_.dispose();
                    this.patternsBuilder_ = null;
                    this.patterns_ = listStringMatcher.patterns_;
                    this.bitField0_ &= -2;
                    this.patternsBuilder_ = ListStringMatcher.alwaysUseFieldBuilders ? getPatternsFieldBuilder() : null;
                }
            }
            m33675mergeUnknownFields(listStringMatcher.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.v3.ListStringMatcher.Builder m33671mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.v3.ListStringMatcher.access$700()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.v3.ListStringMatcher r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.v3.ListStringMatcher) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.v3.ListStringMatcher r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.v3.ListStringMatcher) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.v3.ListStringMatcher.Builder.m33671mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.v3.ListStringMatcher$Builder");
        }

        private void ensurePatternsIsMutable() {
            if ((this.bitField0_ & 1) == 0) {
                this.patterns_ = new ArrayList(this.patterns_);
                this.bitField0_ |= 1;
            }
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.v3.ListStringMatcherOrBuilder
        public List<StringMatcher> getPatternsList() {
            RepeatedFieldBuilderV3<StringMatcher, StringMatcher.Builder, StringMatcherOrBuilder> repeatedFieldBuilderV3 = this.patternsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.patterns_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.v3.ListStringMatcherOrBuilder
        public int getPatternsCount() {
            RepeatedFieldBuilderV3<StringMatcher, StringMatcher.Builder, StringMatcherOrBuilder> repeatedFieldBuilderV3 = this.patternsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.patterns_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.v3.ListStringMatcherOrBuilder
        public StringMatcher getPatterns(int i) {
            RepeatedFieldBuilderV3<StringMatcher, StringMatcher.Builder, StringMatcherOrBuilder> repeatedFieldBuilderV3 = this.patternsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.patterns_.get(i);
            }
            return repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setPatterns(int i, StringMatcher stringMatcher) {
            RepeatedFieldBuilderV3<StringMatcher, StringMatcher.Builder, StringMatcherOrBuilder> repeatedFieldBuilderV3 = this.patternsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                stringMatcher.getClass();
                ensurePatternsIsMutable();
                this.patterns_.set(i, stringMatcher);
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, stringMatcher);
            }
            return this;
        }

        public Builder setPatterns(int i, StringMatcher.Builder builder) {
            RepeatedFieldBuilderV3<StringMatcher, StringMatcher.Builder, StringMatcherOrBuilder> repeatedFieldBuilderV3 = this.patternsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensurePatternsIsMutable();
                this.patterns_.set(i, builder.m33831build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.m33831build());
            }
            return this;
        }

        public Builder addPatterns(StringMatcher stringMatcher) {
            RepeatedFieldBuilderV3<StringMatcher, StringMatcher.Builder, StringMatcherOrBuilder> repeatedFieldBuilderV3 = this.patternsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                stringMatcher.getClass();
                ensurePatternsIsMutable();
                this.patterns_.add(stringMatcher);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(stringMatcher);
            }
            return this;
        }

        public Builder addPatterns(int i, StringMatcher stringMatcher) {
            RepeatedFieldBuilderV3<StringMatcher, StringMatcher.Builder, StringMatcherOrBuilder> repeatedFieldBuilderV3 = this.patternsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                stringMatcher.getClass();
                ensurePatternsIsMutable();
                this.patterns_.add(i, stringMatcher);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, stringMatcher);
            }
            return this;
        }

        public Builder addPatterns(StringMatcher.Builder builder) {
            RepeatedFieldBuilderV3<StringMatcher, StringMatcher.Builder, StringMatcherOrBuilder> repeatedFieldBuilderV3 = this.patternsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensurePatternsIsMutable();
                this.patterns_.add(builder.m33831build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.m33831build());
            }
            return this;
        }

        public Builder addPatterns(int i, StringMatcher.Builder builder) {
            RepeatedFieldBuilderV3<StringMatcher, StringMatcher.Builder, StringMatcherOrBuilder> repeatedFieldBuilderV3 = this.patternsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensurePatternsIsMutable();
                this.patterns_.add(i, builder.m33831build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.m33831build());
            }
            return this;
        }

        public Builder addAllPatterns(Iterable<? extends StringMatcher> iterable) {
            RepeatedFieldBuilderV3<StringMatcher, StringMatcher.Builder, StringMatcherOrBuilder> repeatedFieldBuilderV3 = this.patternsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensurePatternsIsMutable();
                AbstractMessageLite.Builder.addAll(iterable, this.patterns_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearPatterns() {
            RepeatedFieldBuilderV3<StringMatcher, StringMatcher.Builder, StringMatcherOrBuilder> repeatedFieldBuilderV3 = this.patternsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.patterns_ = Collections.emptyList();
                this.bitField0_ &= -2;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removePatterns(int i) {
            RepeatedFieldBuilderV3<StringMatcher, StringMatcher.Builder, StringMatcherOrBuilder> repeatedFieldBuilderV3 = this.patternsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensurePatternsIsMutable();
                this.patterns_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public StringMatcher.Builder getPatternsBuilder(int i) {
            return getPatternsFieldBuilder().getBuilder(i);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.v3.ListStringMatcherOrBuilder
        public StringMatcherOrBuilder getPatternsOrBuilder(int i) {
            RepeatedFieldBuilderV3<StringMatcher, StringMatcher.Builder, StringMatcherOrBuilder> repeatedFieldBuilderV3 = this.patternsBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.patterns_.get(i);
            }
            return (StringMatcherOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.type.matcher.v3.ListStringMatcherOrBuilder
        public List<? extends StringMatcherOrBuilder> getPatternsOrBuilderList() {
            RepeatedFieldBuilderV3<StringMatcher, StringMatcher.Builder, StringMatcherOrBuilder> repeatedFieldBuilderV3 = this.patternsBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.patterns_);
        }

        public StringMatcher.Builder addPatternsBuilder() {
            return getPatternsFieldBuilder().addBuilder(StringMatcher.getDefaultInstance());
        }

        public StringMatcher.Builder addPatternsBuilder(int i) {
            return getPatternsFieldBuilder().addBuilder(i, StringMatcher.getDefaultInstance());
        }

        public List<StringMatcher.Builder> getPatternsBuilderList() {
            return getPatternsFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<StringMatcher, StringMatcher.Builder, StringMatcherOrBuilder> getPatternsFieldBuilder() {
            if (this.patternsBuilder_ == null) {
                this.patternsBuilder_ = new RepeatedFieldBuilderV3<>(this.patterns_, (this.bitField0_ & 1) != 0, getParentForChildren(), isClean());
                this.patterns_ = null;
            }
            return this.patternsBuilder_;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m33681setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m33675mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }
}
