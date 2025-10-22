package io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2;

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

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes3.dex */
public final class ScopedRouteConfiguration extends GeneratedMessageV3 implements ScopedRouteConfigurationOrBuilder {
    public static final int KEY_FIELD_NUMBER = 3;
    public static final int NAME_FIELD_NUMBER = 1;
    public static final int ROUTE_CONFIGURATION_NAME_FIELD_NUMBER = 2;
    private static final long serialVersionUID = 0;
    private static final ScopedRouteConfiguration DEFAULT_INSTANCE = new ScopedRouteConfiguration();
    private static final Parser<ScopedRouteConfiguration> PARSER = new AbstractParser<ScopedRouteConfiguration>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ScopedRouteConfiguration.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public ScopedRouteConfiguration m13147parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new ScopedRouteConfiguration(codedInputStream, extensionRegistryLite);
        }
    };
    private Key key_;
    private byte memoizedIsInitialized;
    private volatile Object name_;
    private volatile Object routeConfigurationName_;

    private ScopedRouteConfiguration(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private ScopedRouteConfiguration() {
        this.memoizedIsInitialized = (byte) -1;
        this.name_ = "";
        this.routeConfigurationName_ = "";
    }

    private ScopedRouteConfiguration(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            this.name_ = codedInputStream.readStringRequireUtf8();
                        } else if (tag == 18) {
                            this.routeConfigurationName_ = codedInputStream.readStringRequireUtf8();
                        } else if (tag == 26) {
                            Key key = this.key_;
                            Key.Builder builderM13191toBuilder = key != null ? key.m13191toBuilder() : null;
                            Key key2 = (Key) codedInputStream.readMessage(Key.parser(), extensionRegistryLite);
                            this.key_ = key2;
                            if (builderM13191toBuilder != null) {
                                builderM13191toBuilder.mergeFrom(key2);
                                this.key_ = builderM13191toBuilder.m13198buildPartial();
                            }
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

    public static ScopedRouteConfiguration getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ScopedRouteConfiguration> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return ScopedRouteProto.internal_static_envoy_api_v2_ScopedRouteConfiguration_descriptor;
    }

    public static ScopedRouteConfiguration parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (ScopedRouteConfiguration) PARSER.parseFrom(byteBuffer);
    }

    public static ScopedRouteConfiguration parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ScopedRouteConfiguration) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static ScopedRouteConfiguration parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (ScopedRouteConfiguration) PARSER.parseFrom(byteString);
    }

    public static ScopedRouteConfiguration parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ScopedRouteConfiguration) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static ScopedRouteConfiguration parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (ScopedRouteConfiguration) PARSER.parseFrom(bArr);
    }

    public static ScopedRouteConfiguration parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ScopedRouteConfiguration) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static ScopedRouteConfiguration parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static ScopedRouteConfiguration parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ScopedRouteConfiguration parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static ScopedRouteConfiguration parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static ScopedRouteConfiguration parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static ScopedRouteConfiguration parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m13145toBuilder();
    }

    public static Builder newBuilder(ScopedRouteConfiguration scopedRouteConfiguration) {
        return DEFAULT_INSTANCE.m13145toBuilder().mergeFrom(scopedRouteConfiguration);
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public ScopedRouteConfiguration m13140getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public Parser<ScopedRouteConfiguration> getParserForType() {
        return PARSER;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ScopedRouteConfigurationOrBuilder
    public boolean hasKey() {
        return this.key_ != null;
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
        return new ScopedRouteConfiguration();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return ScopedRouteProto.internal_static_envoy_api_v2_ScopedRouteConfiguration_fieldAccessorTable.ensureFieldAccessorsInitialized(ScopedRouteConfiguration.class, Builder.class);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ScopedRouteConfigurationOrBuilder
    public String getName() {
        Object obj = this.name_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.name_ = stringUtf8;
        return stringUtf8;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ScopedRouteConfigurationOrBuilder
    public ByteString getNameBytes() {
        Object obj = this.name_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.name_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ScopedRouteConfigurationOrBuilder
    public String getRouteConfigurationName() {
        Object obj = this.routeConfigurationName_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.routeConfigurationName_ = stringUtf8;
        return stringUtf8;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ScopedRouteConfigurationOrBuilder
    public ByteString getRouteConfigurationNameBytes() {
        Object obj = this.routeConfigurationName_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.routeConfigurationName_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ScopedRouteConfigurationOrBuilder
    public Key getKey() {
        Key key = this.key_;
        return key == null ? Key.getDefaultInstance() : key;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ScopedRouteConfigurationOrBuilder
    public KeyOrBuilder getKeyOrBuilder() {
        return getKey();
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (!getNameBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.name_);
        }
        if (!getRouteConfigurationNameBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 2, this.routeConfigurationName_);
        }
        if (this.key_ != null) {
            codedOutputStream.writeMessage(3, getKey());
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeStringSize = !getNameBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.name_) : 0;
        if (!getRouteConfigurationNameBytes().isEmpty()) {
            iComputeStringSize += GeneratedMessageV3.computeStringSize(2, this.routeConfigurationName_);
        }
        if (this.key_ != null) {
            iComputeStringSize += CodedOutputStream.computeMessageSize(3, getKey());
        }
        int serializedSize = iComputeStringSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ScopedRouteConfiguration)) {
            return super.equals(obj);
        }
        ScopedRouteConfiguration scopedRouteConfiguration = (ScopedRouteConfiguration) obj;
        if (getName().equals(scopedRouteConfiguration.getName()) && getRouteConfigurationName().equals(scopedRouteConfiguration.getRouteConfigurationName()) && hasKey() == scopedRouteConfiguration.hasKey()) {
            return (!hasKey() || getKey().equals(scopedRouteConfiguration.getKey())) && this.unknownFields.equals(scopedRouteConfiguration.unknownFields);
        }
        return false;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = ((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getName().hashCode()) * 37) + 2) * 53) + getRouteConfigurationName().hashCode();
        if (hasKey()) {
            iHashCode = (((iHashCode * 37) + 3) * 53) + getKey().hashCode();
        }
        int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode2;
        return iHashCode2;
    }

    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m13142newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m13145toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public interface KeyOrBuilder extends MessageOrBuilder {
        Key.Fragment getFragments(int i);

        int getFragmentsCount();

        List<Key.Fragment> getFragmentsList();

        Key.FragmentOrBuilder getFragmentsOrBuilder(int i);

        List<? extends Key.FragmentOrBuilder> getFragmentsOrBuilderList();
    }

    public static final class Key extends GeneratedMessageV3 implements KeyOrBuilder {
        public static final int FRAGMENTS_FIELD_NUMBER = 1;
        private static final long serialVersionUID = 0;
        private static final Key DEFAULT_INSTANCE = new Key();
        private static final Parser<Key> PARSER = new AbstractParser<Key>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ScopedRouteConfiguration.Key.1
            /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
            public Key m13193parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new Key(codedInputStream, extensionRegistryLite);
            }
        };
        private List<Fragment> fragments_;
        private byte memoizedIsInitialized;

        private Key(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private Key() {
            this.memoizedIsInitialized = (byte) -1;
            this.fragments_ = Collections.emptyList();
        }

        private Key(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                    this.fragments_ = new ArrayList();
                                    z2 |= true;
                                }
                                this.fragments_.add(codedInputStream.readMessage(Fragment.parser(), extensionRegistryLite));
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
                        this.fragments_ = Collections.unmodifiableList(this.fragments_);
                    }
                    this.unknownFields = builderNewBuilder.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static Key getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Key> parser() {
            return PARSER;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ScopedRouteProto.internal_static_envoy_api_v2_ScopedRouteConfiguration_Key_descriptor;
        }

        public static Key parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (Key) PARSER.parseFrom(byteBuffer);
        }

        public static Key parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Key) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static Key parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (Key) PARSER.parseFrom(byteString);
        }

        public static Key parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Key) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static Key parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (Key) PARSER.parseFrom(bArr);
        }

        public static Key parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Key) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static Key parseFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static Key parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static Key parseDelimitedFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static Key parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static Key parseFrom(CodedInputStream codedInputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static Key parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.m13191toBuilder();
        }

        public static Builder newBuilder(Key key) {
            return DEFAULT_INSTANCE.m13191toBuilder().mergeFrom(key);
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Key m13186getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ScopedRouteConfiguration.KeyOrBuilder
        public List<Fragment> getFragmentsList() {
            return this.fragments_;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ScopedRouteConfiguration.KeyOrBuilder
        public List<? extends FragmentOrBuilder> getFragmentsOrBuilderList() {
            return this.fragments_;
        }

        public Parser<Key> getParserForType() {
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
            return new Key();
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ScopedRouteProto.internal_static_envoy_api_v2_ScopedRouteConfiguration_Key_fieldAccessorTable.ensureFieldAccessorsInitialized(Key.class, Builder.class);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ScopedRouteConfiguration.KeyOrBuilder
        public int getFragmentsCount() {
            return this.fragments_.size();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ScopedRouteConfiguration.KeyOrBuilder
        public Fragment getFragments(int i) {
            return this.fragments_.get(i);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ScopedRouteConfiguration.KeyOrBuilder
        public FragmentOrBuilder getFragmentsOrBuilder(int i) {
            return this.fragments_.get(i);
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            for (int i = 0; i < this.fragments_.size(); i++) {
                codedOutputStream.writeMessage(1, this.fragments_.get(i));
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int iComputeMessageSize = 0;
            for (int i2 = 0; i2 < this.fragments_.size(); i2++) {
                iComputeMessageSize += CodedOutputStream.computeMessageSize(1, this.fragments_.get(i2));
            }
            int serializedSize = iComputeMessageSize + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Key)) {
                return super.equals(obj);
            }
            Key key = (Key) obj;
            return getFragmentsList().equals(key.getFragmentsList()) && this.unknownFields.equals(key.unknownFields);
        }

        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int iHashCode = 779 + getDescriptor().hashCode();
            if (getFragmentsCount() > 0) {
                iHashCode = (((iHashCode * 37) + 1) * 53) + getFragmentsList().hashCode();
            }
            int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = iHashCode2;
            return iHashCode2;
        }

        /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m13188newBuilderForType() {
            return newBuilder();
        }

        /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m13191toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public interface FragmentOrBuilder extends MessageOrBuilder {
            String getStringKey();

            ByteString getStringKeyBytes();

            Fragment.TypeCase getTypeCase();
        }

        public static final class Fragment extends GeneratedMessageV3 implements FragmentOrBuilder {
            public static final int STRING_KEY_FIELD_NUMBER = 1;
            private static final Fragment DEFAULT_INSTANCE = new Fragment();
            private static final Parser<Fragment> PARSER = new AbstractParser<Fragment>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ScopedRouteConfiguration.Key.Fragment.1
                /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
                public Fragment m13239parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                    return new Fragment(codedInputStream, extensionRegistryLite);
                }
            };
            private static final long serialVersionUID = 0;
            private byte memoizedIsInitialized;
            private int typeCase_;
            private Object type_;

            private Fragment(GeneratedMessageV3.Builder<?> builder) {
                super(builder);
                this.typeCase_ = 0;
                this.memoizedIsInitialized = (byte) -1;
            }

            private Fragment() {
                this.typeCase_ = 0;
                this.memoizedIsInitialized = (byte) -1;
            }

            private Fragment(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                    this.typeCase_ = 1;
                                    this.type_ = stringRequireUtf8;
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

            public static Fragment getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<Fragment> parser() {
                return PARSER;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return ScopedRouteProto.internal_static_envoy_api_v2_ScopedRouteConfiguration_Key_Fragment_descriptor;
            }

            public static Fragment parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
                return (Fragment) PARSER.parseFrom(byteBuffer);
            }

            public static Fragment parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return (Fragment) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
            }

            public static Fragment parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
                return (Fragment) PARSER.parseFrom(byteString);
            }

            public static Fragment parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return (Fragment) PARSER.parseFrom(byteString, extensionRegistryLite);
            }

            public static Fragment parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
                return (Fragment) PARSER.parseFrom(bArr);
            }

            public static Fragment parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return (Fragment) PARSER.parseFrom(bArr, extensionRegistryLite);
            }

            public static Fragment parseFrom(InputStream inputStream) throws IOException {
                return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
            }

            public static Fragment parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
            }

            public static Fragment parseDelimitedFrom(InputStream inputStream) throws IOException {
                return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
            }

            public static Fragment parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
            }

            public static Fragment parseFrom(CodedInputStream codedInputStream) throws IOException {
                return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
            }

            public static Fragment parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
            }

            public static Builder newBuilder() {
                return DEFAULT_INSTANCE.m13237toBuilder();
            }

            public static Builder newBuilder(Fragment fragment) {
                return DEFAULT_INSTANCE.m13237toBuilder().mergeFrom(fragment);
            }

            /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Fragment m13232getDefaultInstanceForType() {
                return DEFAULT_INSTANCE;
            }

            public Parser<Fragment> getParserForType() {
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
                return new Fragment();
            }

            public final UnknownFieldSet getUnknownFields() {
                return this.unknownFields;
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return ScopedRouteProto.internal_static_envoy_api_v2_ScopedRouteConfiguration_Key_Fragment_fieldAccessorTable.ensureFieldAccessorsInitialized(Fragment.class, Builder.class);
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ScopedRouteConfiguration.Key.FragmentOrBuilder
            public TypeCase getTypeCase() {
                return TypeCase.forNumber(this.typeCase_);
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ScopedRouteConfiguration.Key.FragmentOrBuilder
            public String getStringKey() {
                String str = this.typeCase_ == 1 ? this.type_ : "";
                if (str instanceof String) {
                    return (String) str;
                }
                String stringUtf8 = ((ByteString) str).toStringUtf8();
                if (this.typeCase_ == 1) {
                    this.type_ = stringUtf8;
                }
                return stringUtf8;
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ScopedRouteConfiguration.Key.FragmentOrBuilder
            public ByteString getStringKeyBytes() {
                String str = this.typeCase_ == 1 ? this.type_ : "";
                if (str instanceof String) {
                    ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) str);
                    if (this.typeCase_ == 1) {
                        this.type_ = byteStringCopyFromUtf8;
                    }
                    return byteStringCopyFromUtf8;
                }
                return (ByteString) str;
            }

            public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
                if (this.typeCase_ == 1) {
                    GeneratedMessageV3.writeString(codedOutputStream, 1, this.type_);
                }
                this.unknownFields.writeTo(codedOutputStream);
            }

            public int getSerializedSize() {
                int i = this.memoizedSize;
                if (i != -1) {
                    return i;
                }
                int iComputeStringSize = (this.typeCase_ == 1 ? GeneratedMessageV3.computeStringSize(1, this.type_) : 0) + this.unknownFields.getSerializedSize();
                this.memoizedSize = iComputeStringSize;
                return iComputeStringSize;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof Fragment)) {
                    return super.equals(obj);
                }
                Fragment fragment = (Fragment) obj;
                if (getTypeCase().equals(fragment.getTypeCase())) {
                    return (this.typeCase_ != 1 || getStringKey().equals(fragment.getStringKey())) && this.unknownFields.equals(fragment.unknownFields);
                }
                return false;
            }

            public int hashCode() {
                if (this.memoizedHashCode != 0) {
                    return this.memoizedHashCode;
                }
                int iHashCode = 779 + getDescriptor().hashCode();
                if (this.typeCase_ == 1) {
                    iHashCode = (((iHashCode * 37) + 1) * 53) + getStringKey().hashCode();
                }
                int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
                this.memoizedHashCode = iHashCode2;
                return iHashCode2;
            }

            /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m13234newBuilderForType() {
                return newBuilder();
            }

            /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m13237toBuilder() {
                return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
                return new Builder(builderParent);
            }

            public enum TypeCase implements Internal.EnumLite, AbstractMessageLite.InternalOneOfEnum {
                STRING_KEY(1),
                TYPE_NOT_SET(0);

                private final int value;

                TypeCase(int i) {
                    this.value = i;
                }

                public static TypeCase forNumber(int i) {
                    if (i == 0) {
                        return TYPE_NOT_SET;
                    }
                    if (i != 1) {
                        return null;
                    }
                    return STRING_KEY;
                }

                @Deprecated
                public static TypeCase valueOf(int i) {
                    return forNumber(i);
                }

                public int getNumber() {
                    return this.value;
                }
            }

            public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements FragmentOrBuilder {
                private int typeCase_;
                private Object type_;

                private Builder() {
                    this.typeCase_ = 0;
                    maybeForceBuilderInitialization();
                }

                private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                    super(builderParent);
                    this.typeCase_ = 0;
                    maybeForceBuilderInitialization();
                }

                public static final Descriptors.Descriptor getDescriptor() {
                    return ScopedRouteProto.internal_static_envoy_api_v2_ScopedRouteConfiguration_Key_Fragment_descriptor;
                }

                public final boolean isInitialized() {
                    return true;
                }

                protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                    return ScopedRouteProto.internal_static_envoy_api_v2_ScopedRouteConfiguration_Key_Fragment_fieldAccessorTable.ensureFieldAccessorsInitialized(Fragment.class, Builder.class);
                }

                private void maybeForceBuilderInitialization() {
                    boolean unused = Fragment.alwaysUseFieldBuilders;
                }

                /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m13248clear() {
                    super.clear();
                    this.typeCase_ = 0;
                    this.type_ = null;
                    return this;
                }

                public Descriptors.Descriptor getDescriptorForType() {
                    return ScopedRouteProto.internal_static_envoy_api_v2_ScopedRouteConfiguration_Key_Fragment_descriptor;
                }

                /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Fragment m13261getDefaultInstanceForType() {
                    return Fragment.getDefaultInstance();
                }

                /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
                /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Fragment m13242build() throws UninitializedMessageException {
                    Fragment fragmentM13244buildPartial = m13244buildPartial();
                    if (fragmentM13244buildPartial.isInitialized()) {
                        return fragmentM13244buildPartial;
                    }
                    throw newUninitializedMessageException(fragmentM13244buildPartial);
                }

                /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Fragment m13244buildPartial() {
                    Fragment fragment = new Fragment(this);
                    if (this.typeCase_ == 1) {
                        fragment.type_ = this.type_;
                    }
                    fragment.typeCase_ = this.typeCase_;
                    onBuilt();
                    return fragment;
                }

                /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m13260clone() {
                    return (Builder) super.clone();
                }

                /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m13272setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                    return (Builder) super.setField(fieldDescriptor, obj);
                }

                /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m13250clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                    return (Builder) super.clearField(fieldDescriptor);
                }

                /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m13253clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                    return (Builder) super.clearOneof(oneofDescriptor);
                }

                /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m13274setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                    return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
                }

                /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m13240addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                    return (Builder) super.addRepeatedField(fieldDescriptor, obj);
                }

                /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m13265mergeFrom(Message message) {
                    if (message instanceof Fragment) {
                        return mergeFrom((Fragment) message);
                    }
                    super.mergeFrom(message);
                    return this;
                }

                public Builder mergeFrom(Fragment fragment) {
                    if (fragment == Fragment.getDefaultInstance()) {
                        return this;
                    }
                    if (AnonymousClass2.$SwitchMap$io$envoyproxy$envoy$api$v2$ScopedRouteConfiguration$Key$Fragment$TypeCase[fragment.getTypeCase().ordinal()] == 1) {
                        this.typeCase_ = 1;
                        this.type_ = fragment.type_;
                        onChanged();
                    }
                    m13270mergeUnknownFields(fragment.unknownFields);
                    onChanged();
                    return this;
                }

                /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
                /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ScopedRouteConfiguration.Key.Fragment.Builder m13266mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
                    /*
                        r2 = this;
                        r0 = 0
                        com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ScopedRouteConfiguration.Key.Fragment.access$700()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                        java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                        io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ScopedRouteConfiguration$Key$Fragment r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ScopedRouteConfiguration.Key.Fragment) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                        io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ScopedRouteConfiguration$Key$Fragment r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ScopedRouteConfiguration.Key.Fragment) r4     // Catch: java.lang.Throwable -> L11
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
                    throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ScopedRouteConfiguration.Key.Fragment.Builder.m13266mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ScopedRouteConfiguration$Key$Fragment$Builder");
                }

                @Override
                // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ScopedRouteConfiguration.Key.FragmentOrBuilder
                public TypeCase getTypeCase() {
                    return TypeCase.forNumber(this.typeCase_);
                }

                public Builder clearType() {
                    this.typeCase_ = 0;
                    this.type_ = null;
                    onChanged();
                    return this;
                }

                @Override
                // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ScopedRouteConfiguration.Key.FragmentOrBuilder
                public String getStringKey() {
                    String str = this.typeCase_ == 1 ? this.type_ : "";
                    if (!(str instanceof String)) {
                        String stringUtf8 = ((ByteString) str).toStringUtf8();
                        if (this.typeCase_ == 1) {
                            this.type_ = stringUtf8;
                        }
                        return stringUtf8;
                    }
                    return (String) str;
                }

                public Builder setStringKey(String str) {
                    str.getClass();
                    this.typeCase_ = 1;
                    this.type_ = str;
                    onChanged();
                    return this;
                }

                @Override
                // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ScopedRouteConfiguration.Key.FragmentOrBuilder
                public ByteString getStringKeyBytes() {
                    String str = this.typeCase_ == 1 ? this.type_ : "";
                    if (str instanceof String) {
                        ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) str);
                        if (this.typeCase_ == 1) {
                            this.type_ = byteStringCopyFromUtf8;
                        }
                        return byteStringCopyFromUtf8;
                    }
                    return (ByteString) str;
                }

                public Builder setStringKeyBytes(ByteString byteString) {
                    byteString.getClass();
                    Fragment.checkByteStringIsUtf8(byteString);
                    this.typeCase_ = 1;
                    this.type_ = byteString;
                    onChanged();
                    return this;
                }

                public Builder clearStringKey() {
                    if (this.typeCase_ == 1) {
                        this.typeCase_ = 0;
                        this.type_ = null;
                        onChanged();
                    }
                    return this;
                }

                /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public final Builder m13276setUnknownFields(UnknownFieldSet unknownFieldSet) {
                    return (Builder) super.setUnknownFields(unknownFieldSet);
                }

                /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public final Builder m13270mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                    return (Builder) super.mergeUnknownFields(unknownFieldSet);
                }
            }
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements KeyOrBuilder {
            private int bitField0_;
            private RepeatedFieldBuilderV3<Fragment, Fragment.Builder, FragmentOrBuilder> fragmentsBuilder_;
            private List<Fragment> fragments_;

            private Builder() {
                this.fragments_ = Collections.emptyList();
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.fragments_ = Collections.emptyList();
                maybeForceBuilderInitialization();
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return ScopedRouteProto.internal_static_envoy_api_v2_ScopedRouteConfiguration_Key_descriptor;
            }

            public final boolean isInitialized() {
                return true;
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return ScopedRouteProto.internal_static_envoy_api_v2_ScopedRouteConfiguration_Key_fieldAccessorTable.ensureFieldAccessorsInitialized(Key.class, Builder.class);
            }

            private void maybeForceBuilderInitialization() {
                if (Key.alwaysUseFieldBuilders) {
                    getFragmentsFieldBuilder();
                }
            }

            /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m13202clear() {
                super.clear();
                RepeatedFieldBuilderV3<Fragment, Fragment.Builder, FragmentOrBuilder> repeatedFieldBuilderV3 = this.fragmentsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    this.fragments_ = Collections.emptyList();
                    this.bitField0_ &= -2;
                } else {
                    repeatedFieldBuilderV3.clear();
                }
                return this;
            }

            public Descriptors.Descriptor getDescriptorForType() {
                return ScopedRouteProto.internal_static_envoy_api_v2_ScopedRouteConfiguration_Key_descriptor;
            }

            /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Key m13215getDefaultInstanceForType() {
                return Key.getDefaultInstance();
            }

            /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
            /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Key m13196build() throws UninitializedMessageException {
                Key keyM13198buildPartial = m13198buildPartial();
                if (keyM13198buildPartial.isInitialized()) {
                    return keyM13198buildPartial;
                }
                throw newUninitializedMessageException(keyM13198buildPartial);
            }

            /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Key m13198buildPartial() {
                Key key = new Key(this);
                int i = this.bitField0_;
                RepeatedFieldBuilderV3<Fragment, Fragment.Builder, FragmentOrBuilder> repeatedFieldBuilderV3 = this.fragmentsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    if ((i & 1) != 0) {
                        this.fragments_ = Collections.unmodifiableList(this.fragments_);
                        this.bitField0_ &= -2;
                    }
                    key.fragments_ = this.fragments_;
                } else {
                    key.fragments_ = repeatedFieldBuilderV3.build();
                }
                onBuilt();
                return key;
            }

            /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m13214clone() {
                return (Builder) super.clone();
            }

            /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m13226setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.setField(fieldDescriptor, obj);
            }

            /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m13204clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.clearField(fieldDescriptor);
            }

            /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m13207clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.clearOneof(oneofDescriptor);
            }

            /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m13228setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m13194addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.addRepeatedField(fieldDescriptor, obj);
            }

            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m13219mergeFrom(Message message) {
                if (message instanceof Key) {
                    return mergeFrom((Key) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(Key key) {
                if (key == Key.getDefaultInstance()) {
                    return this;
                }
                if (this.fragmentsBuilder_ == null) {
                    if (!key.fragments_.isEmpty()) {
                        if (this.fragments_.isEmpty()) {
                            this.fragments_ = key.fragments_;
                            this.bitField0_ &= -2;
                        } else {
                            ensureFragmentsIsMutable();
                            this.fragments_.addAll(key.fragments_);
                        }
                        onChanged();
                    }
                } else if (!key.fragments_.isEmpty()) {
                    if (!this.fragmentsBuilder_.isEmpty()) {
                        this.fragmentsBuilder_.addAllMessages(key.fragments_);
                    } else {
                        this.fragmentsBuilder_.dispose();
                        this.fragmentsBuilder_ = null;
                        this.fragments_ = key.fragments_;
                        this.bitField0_ &= -2;
                        this.fragmentsBuilder_ = Key.alwaysUseFieldBuilders ? getFragmentsFieldBuilder() : null;
                    }
                }
                m13224mergeUnknownFields(key.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ScopedRouteConfiguration.Key.Builder m13220mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ScopedRouteConfiguration.Key.access$1700()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ScopedRouteConfiguration$Key r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ScopedRouteConfiguration.Key) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                    io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ScopedRouteConfiguration$Key r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ScopedRouteConfiguration.Key) r4     // Catch: java.lang.Throwable -> L11
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
                throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ScopedRouteConfiguration.Key.Builder.m13220mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ScopedRouteConfiguration$Key$Builder");
            }

            private void ensureFragmentsIsMutable() {
                if ((this.bitField0_ & 1) == 0) {
                    this.fragments_ = new ArrayList(this.fragments_);
                    this.bitField0_ |= 1;
                }
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ScopedRouteConfiguration.KeyOrBuilder
            public List<Fragment> getFragmentsList() {
                RepeatedFieldBuilderV3<Fragment, Fragment.Builder, FragmentOrBuilder> repeatedFieldBuilderV3 = this.fragmentsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return Collections.unmodifiableList(this.fragments_);
                }
                return repeatedFieldBuilderV3.getMessageList();
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ScopedRouteConfiguration.KeyOrBuilder
            public int getFragmentsCount() {
                RepeatedFieldBuilderV3<Fragment, Fragment.Builder, FragmentOrBuilder> repeatedFieldBuilderV3 = this.fragmentsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.fragments_.size();
                }
                return repeatedFieldBuilderV3.getCount();
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ScopedRouteConfiguration.KeyOrBuilder
            public Fragment getFragments(int i) {
                RepeatedFieldBuilderV3<Fragment, Fragment.Builder, FragmentOrBuilder> repeatedFieldBuilderV3 = this.fragmentsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.fragments_.get(i);
                }
                return repeatedFieldBuilderV3.getMessage(i);
            }

            public Builder setFragments(int i, Fragment fragment) {
                RepeatedFieldBuilderV3<Fragment, Fragment.Builder, FragmentOrBuilder> repeatedFieldBuilderV3 = this.fragmentsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    fragment.getClass();
                    ensureFragmentsIsMutable();
                    this.fragments_.set(i, fragment);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.setMessage(i, fragment);
                }
                return this;
            }

            public Builder setFragments(int i, Fragment.Builder builder) {
                RepeatedFieldBuilderV3<Fragment, Fragment.Builder, FragmentOrBuilder> repeatedFieldBuilderV3 = this.fragmentsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureFragmentsIsMutable();
                    this.fragments_.set(i, builder.m13242build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.setMessage(i, builder.m13242build());
                }
                return this;
            }

            public Builder addFragments(Fragment fragment) {
                RepeatedFieldBuilderV3<Fragment, Fragment.Builder, FragmentOrBuilder> repeatedFieldBuilderV3 = this.fragmentsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    fragment.getClass();
                    ensureFragmentsIsMutable();
                    this.fragments_.add(fragment);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(fragment);
                }
                return this;
            }

            public Builder addFragments(int i, Fragment fragment) {
                RepeatedFieldBuilderV3<Fragment, Fragment.Builder, FragmentOrBuilder> repeatedFieldBuilderV3 = this.fragmentsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    fragment.getClass();
                    ensureFragmentsIsMutable();
                    this.fragments_.add(i, fragment);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(i, fragment);
                }
                return this;
            }

            public Builder addFragments(Fragment.Builder builder) {
                RepeatedFieldBuilderV3<Fragment, Fragment.Builder, FragmentOrBuilder> repeatedFieldBuilderV3 = this.fragmentsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureFragmentsIsMutable();
                    this.fragments_.add(builder.m13242build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(builder.m13242build());
                }
                return this;
            }

            public Builder addFragments(int i, Fragment.Builder builder) {
                RepeatedFieldBuilderV3<Fragment, Fragment.Builder, FragmentOrBuilder> repeatedFieldBuilderV3 = this.fragmentsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureFragmentsIsMutable();
                    this.fragments_.add(i, builder.m13242build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(i, builder.m13242build());
                }
                return this;
            }

            public Builder addAllFragments(Iterable<? extends Fragment> iterable) {
                RepeatedFieldBuilderV3<Fragment, Fragment.Builder, FragmentOrBuilder> repeatedFieldBuilderV3 = this.fragmentsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureFragmentsIsMutable();
                    AbstractMessageLite.Builder.addAll(iterable, this.fragments_);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addAllMessages(iterable);
                }
                return this;
            }

            public Builder clearFragments() {
                RepeatedFieldBuilderV3<Fragment, Fragment.Builder, FragmentOrBuilder> repeatedFieldBuilderV3 = this.fragmentsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    this.fragments_ = Collections.emptyList();
                    this.bitField0_ &= -2;
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.clear();
                }
                return this;
            }

            public Builder removeFragments(int i) {
                RepeatedFieldBuilderV3<Fragment, Fragment.Builder, FragmentOrBuilder> repeatedFieldBuilderV3 = this.fragmentsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureFragmentsIsMutable();
                    this.fragments_.remove(i);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.remove(i);
                }
                return this;
            }

            public Fragment.Builder getFragmentsBuilder(int i) {
                return getFragmentsFieldBuilder().getBuilder(i);
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ScopedRouteConfiguration.KeyOrBuilder
            public FragmentOrBuilder getFragmentsOrBuilder(int i) {
                RepeatedFieldBuilderV3<Fragment, Fragment.Builder, FragmentOrBuilder> repeatedFieldBuilderV3 = this.fragmentsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.fragments_.get(i);
                }
                return (FragmentOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(i);
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ScopedRouteConfiguration.KeyOrBuilder
            public List<? extends FragmentOrBuilder> getFragmentsOrBuilderList() {
                RepeatedFieldBuilderV3<Fragment, Fragment.Builder, FragmentOrBuilder> repeatedFieldBuilderV3 = this.fragmentsBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    return repeatedFieldBuilderV3.getMessageOrBuilderList();
                }
                return Collections.unmodifiableList(this.fragments_);
            }

            public Fragment.Builder addFragmentsBuilder() {
                return getFragmentsFieldBuilder().addBuilder(Fragment.getDefaultInstance());
            }

            public Fragment.Builder addFragmentsBuilder(int i) {
                return getFragmentsFieldBuilder().addBuilder(i, Fragment.getDefaultInstance());
            }

            public List<Fragment.Builder> getFragmentsBuilderList() {
                return getFragmentsFieldBuilder().getBuilderList();
            }

            private RepeatedFieldBuilderV3<Fragment, Fragment.Builder, FragmentOrBuilder> getFragmentsFieldBuilder() {
                if (this.fragmentsBuilder_ == null) {
                    this.fragmentsBuilder_ = new RepeatedFieldBuilderV3<>(this.fragments_, (this.bitField0_ & 1) != 0, getParentForChildren(), isClean());
                    this.fragments_ = null;
                }
                return this.fragmentsBuilder_;
            }

            /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m13230setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFields(unknownFieldSet);
            }

            /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m13224mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }
    }

    /* renamed from: io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ScopedRouteConfiguration$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$io$envoyproxy$envoy$api$v2$ScopedRouteConfiguration$Key$Fragment$TypeCase;

        static {
            int[] iArr = new int[Key.Fragment.TypeCase.values().length];
            $SwitchMap$io$envoyproxy$envoy$api$v2$ScopedRouteConfiguration$Key$Fragment$TypeCase = iArr;
            try {
                iArr[Key.Fragment.TypeCase.STRING_KEY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$api$v2$ScopedRouteConfiguration$Key$Fragment$TypeCase[Key.Fragment.TypeCase.TYPE_NOT_SET.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ScopedRouteConfigurationOrBuilder {
        private SingleFieldBuilderV3<Key, Key.Builder, KeyOrBuilder> keyBuilder_;
        private Key key_;
        private Object name_;
        private Object routeConfigurationName_;

        private Builder() {
            this.name_ = "";
            this.routeConfigurationName_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.name_ = "";
            this.routeConfigurationName_ = "";
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return ScopedRouteProto.internal_static_envoy_api_v2_ScopedRouteConfiguration_descriptor;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ScopedRouteConfigurationOrBuilder
        public boolean hasKey() {
            return (this.keyBuilder_ == null && this.key_ == null) ? false : true;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ScopedRouteProto.internal_static_envoy_api_v2_ScopedRouteConfiguration_fieldAccessorTable.ensureFieldAccessorsInitialized(ScopedRouteConfiguration.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = ScopedRouteConfiguration.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m13156clear() {
            super.clear();
            this.name_ = "";
            this.routeConfigurationName_ = "";
            if (this.keyBuilder_ == null) {
                this.key_ = null;
            } else {
                this.key_ = null;
                this.keyBuilder_ = null;
            }
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return ScopedRouteProto.internal_static_envoy_api_v2_ScopedRouteConfiguration_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public ScopedRouteConfiguration m13169getDefaultInstanceForType() {
            return ScopedRouteConfiguration.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public ScopedRouteConfiguration m13150build() throws UninitializedMessageException {
            ScopedRouteConfiguration scopedRouteConfigurationM13152buildPartial = m13152buildPartial();
            if (scopedRouteConfigurationM13152buildPartial.isInitialized()) {
                return scopedRouteConfigurationM13152buildPartial;
            }
            throw newUninitializedMessageException(scopedRouteConfigurationM13152buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public ScopedRouteConfiguration m13152buildPartial() {
            ScopedRouteConfiguration scopedRouteConfiguration = new ScopedRouteConfiguration(this);
            scopedRouteConfiguration.name_ = this.name_;
            scopedRouteConfiguration.routeConfigurationName_ = this.routeConfigurationName_;
            SingleFieldBuilderV3<Key, Key.Builder, KeyOrBuilder> singleFieldBuilderV3 = this.keyBuilder_;
            if (singleFieldBuilderV3 == null) {
                scopedRouteConfiguration.key_ = this.key_;
            } else {
                scopedRouteConfiguration.key_ = singleFieldBuilderV3.build();
            }
            onBuilt();
            return scopedRouteConfiguration;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m13168clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m13180setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m13158clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m13161clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m13182setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m13148addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m13173mergeFrom(Message message) {
            if (message instanceof ScopedRouteConfiguration) {
                return mergeFrom((ScopedRouteConfiguration) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(ScopedRouteConfiguration scopedRouteConfiguration) {
            if (scopedRouteConfiguration == ScopedRouteConfiguration.getDefaultInstance()) {
                return this;
            }
            if (!scopedRouteConfiguration.getName().isEmpty()) {
                this.name_ = scopedRouteConfiguration.name_;
                onChanged();
            }
            if (!scopedRouteConfiguration.getRouteConfigurationName().isEmpty()) {
                this.routeConfigurationName_ = scopedRouteConfiguration.routeConfigurationName_;
                onChanged();
            }
            if (scopedRouteConfiguration.hasKey()) {
                mergeKey(scopedRouteConfiguration.getKey());
            }
            m13178mergeUnknownFields(scopedRouteConfiguration.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ScopedRouteConfiguration.Builder m13174mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ScopedRouteConfiguration.access$2700()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ScopedRouteConfiguration r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ScopedRouteConfiguration) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ScopedRouteConfiguration r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ScopedRouteConfiguration) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ScopedRouteConfiguration.Builder.m13174mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ScopedRouteConfiguration$Builder");
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ScopedRouteConfigurationOrBuilder
        public String getName() {
            Object obj = this.name_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.name_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        public Builder setName(String str) {
            str.getClass();
            this.name_ = str;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ScopedRouteConfigurationOrBuilder
        public ByteString getNameBytes() {
            Object obj = this.name_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.name_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setNameBytes(ByteString byteString) {
            byteString.getClass();
            ScopedRouteConfiguration.checkByteStringIsUtf8(byteString);
            this.name_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearName() {
            this.name_ = ScopedRouteConfiguration.getDefaultInstance().getName();
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ScopedRouteConfigurationOrBuilder
        public String getRouteConfigurationName() {
            Object obj = this.routeConfigurationName_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.routeConfigurationName_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        public Builder setRouteConfigurationName(String str) {
            str.getClass();
            this.routeConfigurationName_ = str;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ScopedRouteConfigurationOrBuilder
        public ByteString getRouteConfigurationNameBytes() {
            Object obj = this.routeConfigurationName_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.routeConfigurationName_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setRouteConfigurationNameBytes(ByteString byteString) {
            byteString.getClass();
            ScopedRouteConfiguration.checkByteStringIsUtf8(byteString);
            this.routeConfigurationName_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearRouteConfigurationName() {
            this.routeConfigurationName_ = ScopedRouteConfiguration.getDefaultInstance().getRouteConfigurationName();
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ScopedRouteConfigurationOrBuilder
        public Key getKey() {
            SingleFieldBuilderV3<Key, Key.Builder, KeyOrBuilder> singleFieldBuilderV3 = this.keyBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            Key key = this.key_;
            return key == null ? Key.getDefaultInstance() : key;
        }

        public Builder setKey(Key key) {
            SingleFieldBuilderV3<Key, Key.Builder, KeyOrBuilder> singleFieldBuilderV3 = this.keyBuilder_;
            if (singleFieldBuilderV3 == null) {
                key.getClass();
                this.key_ = key;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(key);
            }
            return this;
        }

        public Builder setKey(Key.Builder builder) {
            SingleFieldBuilderV3<Key, Key.Builder, KeyOrBuilder> singleFieldBuilderV3 = this.keyBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.key_ = builder.m13196build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m13196build());
            }
            return this;
        }

        public Builder mergeKey(Key key) {
            SingleFieldBuilderV3<Key, Key.Builder, KeyOrBuilder> singleFieldBuilderV3 = this.keyBuilder_;
            if (singleFieldBuilderV3 == null) {
                Key key2 = this.key_;
                if (key2 != null) {
                    this.key_ = Key.newBuilder(key2).mergeFrom(key).m13198buildPartial();
                } else {
                    this.key_ = key;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(key);
            }
            return this;
        }

        public Builder clearKey() {
            if (this.keyBuilder_ == null) {
                this.key_ = null;
                onChanged();
            } else {
                this.key_ = null;
                this.keyBuilder_ = null;
            }
            return this;
        }

        public Key.Builder getKeyBuilder() {
            onChanged();
            return getKeyFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.ScopedRouteConfigurationOrBuilder
        public KeyOrBuilder getKeyOrBuilder() {
            SingleFieldBuilderV3<Key, Key.Builder, KeyOrBuilder> singleFieldBuilderV3 = this.keyBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (KeyOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            Key key = this.key_;
            return key == null ? Key.getDefaultInstance() : key;
        }

        private SingleFieldBuilderV3<Key, Key.Builder, KeyOrBuilder> getKeyFieldBuilder() {
            if (this.keyBuilder_ == null) {
                this.keyBuilder_ = new SingleFieldBuilderV3<>(getKey(), getParentForChildren(), isClean());
                this.key_ = null;
            }
            return this.keyBuilder_;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m13184setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m13178mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }
}
