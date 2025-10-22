package io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.Any;
import com.google.protobuf.AnyOrBuilder;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.Duration;
import com.google.protobuf.DurationOrBuilder;
import com.google.protobuf.Empty;
import com.google.protobuf.EmptyOrBuilder;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.MessageLite;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.RepeatedFieldBuilderV3;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.Struct;
import com.google.protobuf.StructOrBuilder;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.DataSource;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.HeaderValue;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes3.dex */
public final class GrpcService extends GeneratedMessageV3 implements GrpcServiceOrBuilder {
    public static final int ENVOY_GRPC_FIELD_NUMBER = 1;
    public static final int GOOGLE_GRPC_FIELD_NUMBER = 2;
    public static final int INITIAL_METADATA_FIELD_NUMBER = 5;
    public static final int TIMEOUT_FIELD_NUMBER = 3;
    private static final long serialVersionUID = 0;
    private static final GrpcService DEFAULT_INSTANCE = new GrpcService();
    private static final Parser<GrpcService> PARSER = new AbstractParser<GrpcService>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public GrpcService m14906parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new GrpcService(codedInputStream, extensionRegistryLite);
        }
    };
    private List<HeaderValue> initialMetadata_;
    private byte memoizedIsInitialized;
    private int targetSpecifierCase_;
    private Object targetSpecifier_;
    private Duration timeout_;

    private GrpcService(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.targetSpecifierCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
    }

    private GrpcService() {
        this.targetSpecifierCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
        this.initialMetadata_ = Collections.emptyList();
    }

    private GrpcService(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        Duration.Builder builder;
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
                            builder = this.targetSpecifierCase_ == 1 ? ((EnvoyGrpc) this.targetSpecifier_).m14950toBuilder() : null;
                            MessageLite message = codedInputStream.readMessage(EnvoyGrpc.parser(), extensionRegistryLite);
                            this.targetSpecifier_ = message;
                            if (builder != null) {
                                builder.mergeFrom((EnvoyGrpc) message);
                                this.targetSpecifier_ = builder.m14957buildPartial();
                            }
                            this.targetSpecifierCase_ = 1;
                        } else if (tag == 18) {
                            builder = this.targetSpecifierCase_ == 2 ? ((GoogleGrpc) this.targetSpecifier_).m14996toBuilder() : null;
                            MessageLite message2 = codedInputStream.readMessage(GoogleGrpc.parser(), extensionRegistryLite);
                            this.targetSpecifier_ = message2;
                            if (builder != null) {
                                builder.mergeFrom((GoogleGrpc) message2);
                                this.targetSpecifier_ = builder.m15003buildPartial();
                            }
                            this.targetSpecifierCase_ = 2;
                        } else if (tag == 26) {
                            Duration duration = this.timeout_;
                            builder = duration != null ? duration.toBuilder() : null;
                            Duration message3 = codedInputStream.readMessage(Duration.parser(), extensionRegistryLite);
                            this.timeout_ = message3;
                            if (builder != null) {
                                builder.mergeFrom(message3);
                                this.timeout_ = builder.buildPartial();
                            }
                        } else if (tag == 42) {
                            if (!(z2 & true)) {
                                this.initialMetadata_ = new ArrayList();
                                z2 |= true;
                            }
                            this.initialMetadata_.add(codedInputStream.readMessage(HeaderValue.parser(), extensionRegistryLite));
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
                if (z2 & true) {
                    this.initialMetadata_ = Collections.unmodifiableList(this.initialMetadata_);
                }
                this.unknownFields = builderNewBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static GrpcService getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<GrpcService> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return GrpcServiceProto.internal_static_envoy_api_v2_core_GrpcService_descriptor;
    }

    public static GrpcService parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (GrpcService) PARSER.parseFrom(byteBuffer);
    }

    public static GrpcService parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (GrpcService) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static GrpcService parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (GrpcService) PARSER.parseFrom(byteString);
    }

    public static GrpcService parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (GrpcService) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static GrpcService parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (GrpcService) PARSER.parseFrom(bArr);
    }

    public static GrpcService parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (GrpcService) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static GrpcService parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static GrpcService parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static GrpcService parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static GrpcService parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static GrpcService parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static GrpcService parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m14904toBuilder();
    }

    public static Builder newBuilder(GrpcService grpcService) {
        return DEFAULT_INSTANCE.m14904toBuilder().mergeFrom(grpcService);
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public GrpcService m14899getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcServiceOrBuilder
    public List<HeaderValue> getInitialMetadataList() {
        return this.initialMetadata_;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcServiceOrBuilder
    public List<? extends HeaderValueOrBuilder> getInitialMetadataOrBuilderList() {
        return this.initialMetadata_;
    }

    public Parser<GrpcService> getParserForType() {
        return PARSER;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcServiceOrBuilder
    public boolean hasEnvoyGrpc() {
        return this.targetSpecifierCase_ == 1;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcServiceOrBuilder
    public boolean hasGoogleGrpc() {
        return this.targetSpecifierCase_ == 2;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcServiceOrBuilder
    public boolean hasTimeout() {
        return this.timeout_ != null;
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
        return new GrpcService();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return GrpcServiceProto.internal_static_envoy_api_v2_core_GrpcService_fieldAccessorTable.ensureFieldAccessorsInitialized(GrpcService.class, Builder.class);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcServiceOrBuilder
    public TargetSpecifierCase getTargetSpecifierCase() {
        return TargetSpecifierCase.forNumber(this.targetSpecifierCase_);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcServiceOrBuilder
    public EnvoyGrpc getEnvoyGrpc() {
        if (this.targetSpecifierCase_ == 1) {
            return (EnvoyGrpc) this.targetSpecifier_;
        }
        return EnvoyGrpc.getDefaultInstance();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcServiceOrBuilder
    public EnvoyGrpcOrBuilder getEnvoyGrpcOrBuilder() {
        if (this.targetSpecifierCase_ == 1) {
            return (EnvoyGrpc) this.targetSpecifier_;
        }
        return EnvoyGrpc.getDefaultInstance();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcServiceOrBuilder
    public GoogleGrpc getGoogleGrpc() {
        if (this.targetSpecifierCase_ == 2) {
            return (GoogleGrpc) this.targetSpecifier_;
        }
        return GoogleGrpc.getDefaultInstance();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcServiceOrBuilder
    public GoogleGrpcOrBuilder getGoogleGrpcOrBuilder() {
        if (this.targetSpecifierCase_ == 2) {
            return (GoogleGrpc) this.targetSpecifier_;
        }
        return GoogleGrpc.getDefaultInstance();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcServiceOrBuilder
    public Duration getTimeout() {
        Duration duration = this.timeout_;
        return duration == null ? Duration.getDefaultInstance() : duration;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcServiceOrBuilder
    public DurationOrBuilder getTimeoutOrBuilder() {
        return getTimeout();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcServiceOrBuilder
    public int getInitialMetadataCount() {
        return this.initialMetadata_.size();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcServiceOrBuilder
    public HeaderValue getInitialMetadata(int i) {
        return this.initialMetadata_.get(i);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcServiceOrBuilder
    public HeaderValueOrBuilder getInitialMetadataOrBuilder(int i) {
        return this.initialMetadata_.get(i);
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (this.targetSpecifierCase_ == 1) {
            codedOutputStream.writeMessage(1, (EnvoyGrpc) this.targetSpecifier_);
        }
        if (this.targetSpecifierCase_ == 2) {
            codedOutputStream.writeMessage(2, (GoogleGrpc) this.targetSpecifier_);
        }
        if (this.timeout_ != null) {
            codedOutputStream.writeMessage(3, getTimeout());
        }
        for (int i = 0; i < this.initialMetadata_.size(); i++) {
            codedOutputStream.writeMessage(5, this.initialMetadata_.get(i));
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeMessageSize = this.targetSpecifierCase_ == 1 ? CodedOutputStream.computeMessageSize(1, (EnvoyGrpc) this.targetSpecifier_) : 0;
        if (this.targetSpecifierCase_ == 2) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(2, (GoogleGrpc) this.targetSpecifier_);
        }
        if (this.timeout_ != null) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(3, getTimeout());
        }
        for (int i2 = 0; i2 < this.initialMetadata_.size(); i2++) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(5, this.initialMetadata_.get(i2));
        }
        int serializedSize = iComputeMessageSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GrpcService)) {
            return super.equals(obj);
        }
        GrpcService grpcService = (GrpcService) obj;
        if (hasTimeout() != grpcService.hasTimeout()) {
            return false;
        }
        if ((hasTimeout() && !getTimeout().equals(grpcService.getTimeout())) || !getInitialMetadataList().equals(grpcService.getInitialMetadataList()) || !getTargetSpecifierCase().equals(grpcService.getTargetSpecifierCase())) {
            return false;
        }
        int i = this.targetSpecifierCase_;
        if (i == 1) {
            if (!getEnvoyGrpc().equals(grpcService.getEnvoyGrpc())) {
                return false;
            }
        } else if (i == 2 && !getGoogleGrpc().equals(grpcService.getGoogleGrpc())) {
            return false;
        }
        return this.unknownFields.equals(grpcService.unknownFields);
    }

    public int hashCode() {
        int i;
        int iHashCode;
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode2 = 779 + getDescriptor().hashCode();
        if (hasTimeout()) {
            iHashCode2 = (((iHashCode2 * 37) + 3) * 53) + getTimeout().hashCode();
        }
        if (getInitialMetadataCount() > 0) {
            iHashCode2 = (((iHashCode2 * 37) + 5) * 53) + getInitialMetadataList().hashCode();
        }
        int i2 = this.targetSpecifierCase_;
        if (i2 == 1) {
            i = ((iHashCode2 * 37) + 1) * 53;
            iHashCode = getEnvoyGrpc().hashCode();
        } else {
            if (i2 == 2) {
                i = ((iHashCode2 * 37) + 2) * 53;
                iHashCode = getGoogleGrpc().hashCode();
            }
            int iHashCode3 = (iHashCode2 * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = iHashCode3;
            return iHashCode3;
        }
        iHashCode2 = i + iHashCode;
        int iHashCode32 = (iHashCode2 * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode32;
        return iHashCode32;
    }

    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m14901newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m14904toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public enum TargetSpecifierCase implements Internal.EnumLite, AbstractMessageLite.InternalOneOfEnum {
        ENVOY_GRPC(1),
        GOOGLE_GRPC(2),
        TARGETSPECIFIER_NOT_SET(0);

        private final int value;

        TargetSpecifierCase(int i) {
            this.value = i;
        }

        public static TargetSpecifierCase forNumber(int i) {
            if (i == 0) {
                return TARGETSPECIFIER_NOT_SET;
            }
            if (i == 1) {
                return ENVOY_GRPC;
            }
            if (i != 2) {
                return null;
            }
            return GOOGLE_GRPC;
        }

        @Deprecated
        public static TargetSpecifierCase valueOf(int i) {
            return forNumber(i);
        }

        public int getNumber() {
            return this.value;
        }
    }

    public interface EnvoyGrpcOrBuilder extends MessageOrBuilder {
        String getClusterName();

        ByteString getClusterNameBytes();
    }

    public interface GoogleGrpcOrBuilder extends MessageOrBuilder {
        GoogleGrpc.CallCredentials getCallCredentials(int i);

        int getCallCredentialsCount();

        List<GoogleGrpc.CallCredentials> getCallCredentialsList();

        GoogleGrpc.CallCredentialsOrBuilder getCallCredentialsOrBuilder(int i);

        List<? extends GoogleGrpc.CallCredentialsOrBuilder> getCallCredentialsOrBuilderList();

        GoogleGrpc.ChannelCredentials getChannelCredentials();

        GoogleGrpc.ChannelCredentialsOrBuilder getChannelCredentialsOrBuilder();

        Struct getConfig();

        StructOrBuilder getConfigOrBuilder();

        String getCredentialsFactoryName();

        ByteString getCredentialsFactoryNameBytes();

        String getStatPrefix();

        ByteString getStatPrefixBytes();

        String getTargetUri();

        ByteString getTargetUriBytes();

        boolean hasChannelCredentials();

        boolean hasConfig();
    }

    public static final class EnvoyGrpc extends GeneratedMessageV3 implements EnvoyGrpcOrBuilder {
        public static final int CLUSTER_NAME_FIELD_NUMBER = 1;
        private static final EnvoyGrpc DEFAULT_INSTANCE = new EnvoyGrpc();
        private static final Parser<EnvoyGrpc> PARSER = new AbstractParser<EnvoyGrpc>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.EnvoyGrpc.1
            /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
            public EnvoyGrpc m14952parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new EnvoyGrpc(codedInputStream, extensionRegistryLite);
            }
        };
        private static final long serialVersionUID = 0;
        private volatile Object clusterName_;
        private byte memoizedIsInitialized;

        private EnvoyGrpc(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private EnvoyGrpc() {
            this.memoizedIsInitialized = (byte) -1;
            this.clusterName_ = "";
        }

        private EnvoyGrpc(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                                this.clusterName_ = codedInputStream.readStringRequireUtf8();
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

        public static EnvoyGrpc getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<EnvoyGrpc> parser() {
            return PARSER;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return GrpcServiceProto.internal_static_envoy_api_v2_core_GrpcService_EnvoyGrpc_descriptor;
        }

        public static EnvoyGrpc parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (EnvoyGrpc) PARSER.parseFrom(byteBuffer);
        }

        public static EnvoyGrpc parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (EnvoyGrpc) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static EnvoyGrpc parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (EnvoyGrpc) PARSER.parseFrom(byteString);
        }

        public static EnvoyGrpc parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (EnvoyGrpc) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static EnvoyGrpc parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (EnvoyGrpc) PARSER.parseFrom(bArr);
        }

        public static EnvoyGrpc parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (EnvoyGrpc) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static EnvoyGrpc parseFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static EnvoyGrpc parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static EnvoyGrpc parseDelimitedFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static EnvoyGrpc parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static EnvoyGrpc parseFrom(CodedInputStream codedInputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static EnvoyGrpc parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.m14950toBuilder();
        }

        public static Builder newBuilder(EnvoyGrpc envoyGrpc) {
            return DEFAULT_INSTANCE.m14950toBuilder().mergeFrom(envoyGrpc);
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public EnvoyGrpc m14945getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        public Parser<EnvoyGrpc> getParserForType() {
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
            return new EnvoyGrpc();
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return GrpcServiceProto.internal_static_envoy_api_v2_core_GrpcService_EnvoyGrpc_fieldAccessorTable.ensureFieldAccessorsInitialized(EnvoyGrpc.class, Builder.class);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.EnvoyGrpcOrBuilder
        public String getClusterName() {
            Object obj = this.clusterName_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.clusterName_ = stringUtf8;
            return stringUtf8;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.EnvoyGrpcOrBuilder
        public ByteString getClusterNameBytes() {
            Object obj = this.clusterName_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.clusterName_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!getClusterNameBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 1, this.clusterName_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int iComputeStringSize = (!getClusterNameBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.clusterName_) : 0) + this.unknownFields.getSerializedSize();
            this.memoizedSize = iComputeStringSize;
            return iComputeStringSize;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof EnvoyGrpc)) {
                return super.equals(obj);
            }
            EnvoyGrpc envoyGrpc = (EnvoyGrpc) obj;
            return getClusterName().equals(envoyGrpc.getClusterName()) && this.unknownFields.equals(envoyGrpc.unknownFields);
        }

        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int iHashCode = ((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getClusterName().hashCode()) * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = iHashCode;
            return iHashCode;
        }

        /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m14947newBuilderForType() {
            return newBuilder();
        }

        /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m14950toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements EnvoyGrpcOrBuilder {
            private Object clusterName_;

            private Builder() {
                this.clusterName_ = "";
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.clusterName_ = "";
                maybeForceBuilderInitialization();
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return GrpcServiceProto.internal_static_envoy_api_v2_core_GrpcService_EnvoyGrpc_descriptor;
            }

            public final boolean isInitialized() {
                return true;
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return GrpcServiceProto.internal_static_envoy_api_v2_core_GrpcService_EnvoyGrpc_fieldAccessorTable.ensureFieldAccessorsInitialized(EnvoyGrpc.class, Builder.class);
            }

            private void maybeForceBuilderInitialization() {
                boolean unused = EnvoyGrpc.alwaysUseFieldBuilders;
            }

            /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m14961clear() {
                super.clear();
                this.clusterName_ = "";
                return this;
            }

            public Descriptors.Descriptor getDescriptorForType() {
                return GrpcServiceProto.internal_static_envoy_api_v2_core_GrpcService_EnvoyGrpc_descriptor;
            }

            /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public EnvoyGrpc m14974getDefaultInstanceForType() {
                return EnvoyGrpc.getDefaultInstance();
            }

            /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
            /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public EnvoyGrpc m14955build() throws UninitializedMessageException {
                EnvoyGrpc envoyGrpcM14957buildPartial = m14957buildPartial();
                if (envoyGrpcM14957buildPartial.isInitialized()) {
                    return envoyGrpcM14957buildPartial;
                }
                throw newUninitializedMessageException(envoyGrpcM14957buildPartial);
            }

            /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public EnvoyGrpc m14957buildPartial() {
                EnvoyGrpc envoyGrpc = new EnvoyGrpc(this);
                envoyGrpc.clusterName_ = this.clusterName_;
                onBuilt();
                return envoyGrpc;
            }

            /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m14973clone() {
                return (Builder) super.clone();
            }

            /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m14985setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.setField(fieldDescriptor, obj);
            }

            /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m14963clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.clearField(fieldDescriptor);
            }

            /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m14966clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.clearOneof(oneofDescriptor);
            }

            /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m14987setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m14953addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.addRepeatedField(fieldDescriptor, obj);
            }

            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m14978mergeFrom(Message message) {
                if (message instanceof EnvoyGrpc) {
                    return mergeFrom((EnvoyGrpc) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(EnvoyGrpc envoyGrpc) {
                if (envoyGrpc == EnvoyGrpc.getDefaultInstance()) {
                    return this;
                }
                if (!envoyGrpc.getClusterName().isEmpty()) {
                    this.clusterName_ = envoyGrpc.clusterName_;
                    onChanged();
                }
                m14983mergeUnknownFields(envoyGrpc.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.EnvoyGrpc.Builder m14979mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.EnvoyGrpc.access$600()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService$EnvoyGrpc r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.EnvoyGrpc) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                    io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService$EnvoyGrpc r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.EnvoyGrpc) r4     // Catch: java.lang.Throwable -> L11
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
                throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.EnvoyGrpc.Builder.m14979mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService$EnvoyGrpc$Builder");
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.EnvoyGrpcOrBuilder
            public String getClusterName() {
                Object obj = this.clusterName_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.clusterName_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            public Builder setClusterName(String str) {
                str.getClass();
                this.clusterName_ = str;
                onChanged();
                return this;
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.EnvoyGrpcOrBuilder
            public ByteString getClusterNameBytes() {
                Object obj = this.clusterName_;
                if (obj instanceof String) {
                    ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.clusterName_ = byteStringCopyFromUtf8;
                    return byteStringCopyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setClusterNameBytes(ByteString byteString) {
                byteString.getClass();
                EnvoyGrpc.checkByteStringIsUtf8(byteString);
                this.clusterName_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearClusterName() {
                this.clusterName_ = EnvoyGrpc.getDefaultInstance().getClusterName();
                onChanged();
                return this;
            }

            /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m14989setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFields(unknownFieldSet);
            }

            /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m14983mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }
    }

    public static final class GoogleGrpc extends GeneratedMessageV3 implements GoogleGrpcOrBuilder {
        public static final int CALL_CREDENTIALS_FIELD_NUMBER = 3;
        public static final int CHANNEL_CREDENTIALS_FIELD_NUMBER = 2;
        public static final int CONFIG_FIELD_NUMBER = 6;
        public static final int CREDENTIALS_FACTORY_NAME_FIELD_NUMBER = 5;
        public static final int STAT_PREFIX_FIELD_NUMBER = 4;
        public static final int TARGET_URI_FIELD_NUMBER = 1;
        private static final GoogleGrpc DEFAULT_INSTANCE = new GoogleGrpc();
        private static final Parser<GoogleGrpc> PARSER = new AbstractParser<GoogleGrpc>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.1
            /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
            public GoogleGrpc m14998parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return new GoogleGrpc(codedInputStream, extensionRegistryLite);
            }
        };
        private static final long serialVersionUID = 0;
        private List<CallCredentials> callCredentials_;
        private ChannelCredentials channelCredentials_;
        private Struct config_;
        private volatile Object credentialsFactoryName_;
        private byte memoizedIsInitialized;
        private volatile Object statPrefix_;
        private volatile Object targetUri_;

        private GoogleGrpc(GeneratedMessageV3.Builder<?> builder) {
            super(builder);
            this.memoizedIsInitialized = (byte) -1;
        }

        private GoogleGrpc() {
            this.memoizedIsInitialized = (byte) -1;
            this.targetUri_ = "";
            this.callCredentials_ = Collections.emptyList();
            this.statPrefix_ = "";
            this.credentialsFactoryName_ = "";
        }

        private GoogleGrpc(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            ChannelCredentials.Builder builder;
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
                            if (tag != 10) {
                                if (tag == 18) {
                                    ChannelCredentials channelCredentials = this.channelCredentials_;
                                    builder = channelCredentials != null ? channelCredentials.m15272toBuilder() : null;
                                    ChannelCredentials channelCredentials2 = (ChannelCredentials) codedInputStream.readMessage(ChannelCredentials.parser(), extensionRegistryLite);
                                    this.channelCredentials_ = channelCredentials2;
                                    if (builder != null) {
                                        builder.mergeFrom(channelCredentials2);
                                        this.channelCredentials_ = builder.m15279buildPartial();
                                    }
                                } else if (tag == 26) {
                                    if (!(z2 & true)) {
                                        this.callCredentials_ = new ArrayList();
                                        z2 |= true;
                                    }
                                    this.callCredentials_.add(codedInputStream.readMessage(CallCredentials.parser(), extensionRegistryLite));
                                } else if (tag == 34) {
                                    this.statPrefix_ = codedInputStream.readStringRequireUtf8();
                                } else if (tag == 42) {
                                    this.credentialsFactoryName_ = codedInputStream.readStringRequireUtf8();
                                } else if (tag == 50) {
                                    Struct struct = this.config_;
                                    builder = struct != null ? struct.toBuilder() : null;
                                    Struct message = codedInputStream.readMessage(Struct.parser(), extensionRegistryLite);
                                    this.config_ = message;
                                    if (builder != null) {
                                        builder.mergeFrom(message);
                                        this.config_ = builder.buildPartial();
                                    }
                                } else if (!parseUnknownField(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                                }
                            } else {
                                this.targetUri_ = codedInputStream.readStringRequireUtf8();
                            }
                        }
                        z = true;
                    } catch (InvalidProtocolBufferException e) {
                        throw e.setUnfinishedMessage(this);
                    } catch (IOException e2) {
                        throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                    }
                } finally {
                    if (z2 & true) {
                        this.callCredentials_ = Collections.unmodifiableList(this.callCredentials_);
                    }
                    this.unknownFields = builderNewBuilder.build();
                    makeExtensionsImmutable();
                }
            }
        }

        public static GoogleGrpc getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<GoogleGrpc> parser() {
            return PARSER;
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return GrpcServiceProto.internal_static_envoy_api_v2_core_GrpcService_GoogleGrpc_descriptor;
        }

        public static GoogleGrpc parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (GoogleGrpc) PARSER.parseFrom(byteBuffer);
        }

        public static GoogleGrpc parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (GoogleGrpc) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
        }

        public static GoogleGrpc parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (GoogleGrpc) PARSER.parseFrom(byteString);
        }

        public static GoogleGrpc parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (GoogleGrpc) PARSER.parseFrom(byteString, extensionRegistryLite);
        }

        public static GoogleGrpc parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (GoogleGrpc) PARSER.parseFrom(bArr);
        }

        public static GoogleGrpc parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (GoogleGrpc) PARSER.parseFrom(bArr, extensionRegistryLite);
        }

        public static GoogleGrpc parseFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
        }

        public static GoogleGrpc parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static GoogleGrpc parseDelimitedFrom(InputStream inputStream) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
        }

        public static GoogleGrpc parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
        }

        public static GoogleGrpc parseFrom(CodedInputStream codedInputStream) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
        }

        public static GoogleGrpc parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.m14996toBuilder();
        }

        public static Builder newBuilder(GoogleGrpc googleGrpc) {
            return DEFAULT_INSTANCE.m14996toBuilder().mergeFrom(googleGrpc);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpcOrBuilder
        public List<CallCredentials> getCallCredentialsList() {
            return this.callCredentials_;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpcOrBuilder
        public List<? extends CallCredentialsOrBuilder> getCallCredentialsOrBuilderList() {
            return this.callCredentials_;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public GoogleGrpc m14991getDefaultInstanceForType() {
            return DEFAULT_INSTANCE;
        }

        public Parser<GoogleGrpc> getParserForType() {
            return PARSER;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpcOrBuilder
        public boolean hasChannelCredentials() {
            return this.channelCredentials_ != null;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpcOrBuilder
        public boolean hasConfig() {
            return this.config_ != null;
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
            return new GoogleGrpc();
        }

        public final UnknownFieldSet getUnknownFields() {
            return this.unknownFields;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return GrpcServiceProto.internal_static_envoy_api_v2_core_GrpcService_GoogleGrpc_fieldAccessorTable.ensureFieldAccessorsInitialized(GoogleGrpc.class, Builder.class);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpcOrBuilder
        public String getTargetUri() {
            Object obj = this.targetUri_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.targetUri_ = stringUtf8;
            return stringUtf8;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpcOrBuilder
        public ByteString getTargetUriBytes() {
            Object obj = this.targetUri_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.targetUri_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpcOrBuilder
        public ChannelCredentials getChannelCredentials() {
            ChannelCredentials channelCredentials = this.channelCredentials_;
            return channelCredentials == null ? ChannelCredentials.getDefaultInstance() : channelCredentials;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpcOrBuilder
        public ChannelCredentialsOrBuilder getChannelCredentialsOrBuilder() {
            return getChannelCredentials();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpcOrBuilder
        public int getCallCredentialsCount() {
            return this.callCredentials_.size();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpcOrBuilder
        public CallCredentials getCallCredentials(int i) {
            return this.callCredentials_.get(i);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpcOrBuilder
        public CallCredentialsOrBuilder getCallCredentialsOrBuilder(int i) {
            return this.callCredentials_.get(i);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpcOrBuilder
        public String getStatPrefix() {
            Object obj = this.statPrefix_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.statPrefix_ = stringUtf8;
            return stringUtf8;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpcOrBuilder
        public ByteString getStatPrefixBytes() {
            Object obj = this.statPrefix_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.statPrefix_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpcOrBuilder
        public String getCredentialsFactoryName() {
            Object obj = this.credentialsFactoryName_;
            if (obj instanceof String) {
                return (String) obj;
            }
            String stringUtf8 = ((ByteString) obj).toStringUtf8();
            this.credentialsFactoryName_ = stringUtf8;
            return stringUtf8;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpcOrBuilder
        public ByteString getCredentialsFactoryNameBytes() {
            Object obj = this.credentialsFactoryName_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.credentialsFactoryName_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpcOrBuilder
        public Struct getConfig() {
            Struct struct = this.config_;
            return struct == null ? Struct.getDefaultInstance() : struct;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpcOrBuilder
        public StructOrBuilder getConfigOrBuilder() {
            return getConfig();
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!getTargetUriBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 1, this.targetUri_);
            }
            if (this.channelCredentials_ != null) {
                codedOutputStream.writeMessage(2, getChannelCredentials());
            }
            for (int i = 0; i < this.callCredentials_.size(); i++) {
                codedOutputStream.writeMessage(3, this.callCredentials_.get(i));
            }
            if (!getStatPrefixBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 4, this.statPrefix_);
            }
            if (!getCredentialsFactoryNameBytes().isEmpty()) {
                GeneratedMessageV3.writeString(codedOutputStream, 5, this.credentialsFactoryName_);
            }
            if (this.config_ != null) {
                codedOutputStream.writeMessage(6, getConfig());
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public int getSerializedSize() {
            int i = this.memoizedSize;
            if (i != -1) {
                return i;
            }
            int iComputeStringSize = !getTargetUriBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.targetUri_) : 0;
            if (this.channelCredentials_ != null) {
                iComputeStringSize += CodedOutputStream.computeMessageSize(2, getChannelCredentials());
            }
            for (int i2 = 0; i2 < this.callCredentials_.size(); i2++) {
                iComputeStringSize += CodedOutputStream.computeMessageSize(3, this.callCredentials_.get(i2));
            }
            if (!getStatPrefixBytes().isEmpty()) {
                iComputeStringSize += GeneratedMessageV3.computeStringSize(4, this.statPrefix_);
            }
            if (!getCredentialsFactoryNameBytes().isEmpty()) {
                iComputeStringSize += GeneratedMessageV3.computeStringSize(5, this.credentialsFactoryName_);
            }
            if (this.config_ != null) {
                iComputeStringSize += CodedOutputStream.computeMessageSize(6, getConfig());
            }
            int serializedSize = iComputeStringSize + this.unknownFields.getSerializedSize();
            this.memoizedSize = serializedSize;
            return serializedSize;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof GoogleGrpc)) {
                return super.equals(obj);
            }
            GoogleGrpc googleGrpc = (GoogleGrpc) obj;
            if (!getTargetUri().equals(googleGrpc.getTargetUri()) || hasChannelCredentials() != googleGrpc.hasChannelCredentials()) {
                return false;
            }
            if ((!hasChannelCredentials() || getChannelCredentials().equals(googleGrpc.getChannelCredentials())) && getCallCredentialsList().equals(googleGrpc.getCallCredentialsList()) && getStatPrefix().equals(googleGrpc.getStatPrefix()) && getCredentialsFactoryName().equals(googleGrpc.getCredentialsFactoryName()) && hasConfig() == googleGrpc.hasConfig()) {
                return (!hasConfig() || getConfig().equals(googleGrpc.getConfig())) && this.unknownFields.equals(googleGrpc.unknownFields);
            }
            return false;
        }

        public int hashCode() {
            if (this.memoizedHashCode != 0) {
                return this.memoizedHashCode;
            }
            int iHashCode = ((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getTargetUri().hashCode();
            if (hasChannelCredentials()) {
                iHashCode = (((iHashCode * 37) + 2) * 53) + getChannelCredentials().hashCode();
            }
            if (getCallCredentialsCount() > 0) {
                iHashCode = (((iHashCode * 37) + 3) * 53) + getCallCredentialsList().hashCode();
            }
            int iHashCode2 = (((((((iHashCode * 37) + 4) * 53) + getStatPrefix().hashCode()) * 37) + 5) * 53) + getCredentialsFactoryName().hashCode();
            if (hasConfig()) {
                iHashCode2 = (((iHashCode2 * 37) + 6) * 53) + getConfig().hashCode();
            }
            int iHashCode3 = (iHashCode2 * 29) + this.unknownFields.hashCode();
            this.memoizedHashCode = iHashCode3;
            return iHashCode3;
        }

        /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m14993newBuilderForType() {
            return newBuilder();
        }

        /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m14996toBuilder() {
            return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
            return new Builder(builderParent);
        }

        public interface CallCredentialsOrBuilder extends MessageOrBuilder {
            String getAccessToken();

            ByteString getAccessTokenBytes();

            CallCredentials.CredentialSpecifierCase getCredentialSpecifierCase();

            CallCredentials.MetadataCredentialsFromPlugin getFromPlugin();

            CallCredentials.MetadataCredentialsFromPluginOrBuilder getFromPluginOrBuilder();

            Empty getGoogleComputeEngine();

            EmptyOrBuilder getGoogleComputeEngineOrBuilder();

            CallCredentials.GoogleIAMCredentials getGoogleIam();

            CallCredentials.GoogleIAMCredentialsOrBuilder getGoogleIamOrBuilder();

            String getGoogleRefreshToken();

            ByteString getGoogleRefreshTokenBytes();

            CallCredentials.ServiceAccountJWTAccessCredentials getServiceAccountJwtAccess();

            CallCredentials.ServiceAccountJWTAccessCredentialsOrBuilder getServiceAccountJwtAccessOrBuilder();

            CallCredentials.StsService getStsService();

            CallCredentials.StsServiceOrBuilder getStsServiceOrBuilder();

            boolean hasFromPlugin();

            boolean hasGoogleComputeEngine();

            boolean hasGoogleIam();

            boolean hasServiceAccountJwtAccess();

            boolean hasStsService();
        }

        public interface ChannelCredentialsOrBuilder extends MessageOrBuilder {
            ChannelCredentials.CredentialSpecifierCase getCredentialSpecifierCase();

            Empty getGoogleDefault();

            EmptyOrBuilder getGoogleDefaultOrBuilder();

            GoogleLocalCredentials getLocalCredentials();

            GoogleLocalCredentialsOrBuilder getLocalCredentialsOrBuilder();

            SslCredentials getSslCredentials();

            SslCredentialsOrBuilder getSslCredentialsOrBuilder();

            boolean hasGoogleDefault();

            boolean hasLocalCredentials();

            boolean hasSslCredentials();
        }

        public interface GoogleLocalCredentialsOrBuilder extends MessageOrBuilder {
        }

        public interface SslCredentialsOrBuilder extends MessageOrBuilder {
            DataSource getCertChain();

            DataSourceOrBuilder getCertChainOrBuilder();

            DataSource getPrivateKey();

            DataSourceOrBuilder getPrivateKeyOrBuilder();

            DataSource getRootCerts();

            DataSourceOrBuilder getRootCertsOrBuilder();

            boolean hasCertChain();

            boolean hasPrivateKey();

            boolean hasRootCerts();
        }

        public static final class SslCredentials extends GeneratedMessageV3 implements SslCredentialsOrBuilder {
            public static final int CERT_CHAIN_FIELD_NUMBER = 3;
            public static final int PRIVATE_KEY_FIELD_NUMBER = 2;
            public static final int ROOT_CERTS_FIELD_NUMBER = 1;
            private static final SslCredentials DEFAULT_INSTANCE = new SslCredentials();
            private static final Parser<SslCredentials> PARSER = new AbstractParser<SslCredentials>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.SslCredentials.1
                /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
                public SslCredentials m15366parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                    return new SslCredentials(codedInputStream, extensionRegistryLite);
                }
            };
            private static final long serialVersionUID = 0;
            private DataSource certChain_;
            private byte memoizedIsInitialized;
            private DataSource privateKey_;
            private DataSource rootCerts_;

            private SslCredentials(GeneratedMessageV3.Builder<?> builder) {
                super(builder);
                this.memoizedIsInitialized = (byte) -1;
            }

            private SslCredentials() {
                this.memoizedIsInitialized = (byte) -1;
            }

            private SslCredentials(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                DataSource.Builder builderM14720toBuilder;
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
                                    DataSource dataSource = this.rootCerts_;
                                    builderM14720toBuilder = dataSource != null ? dataSource.m14720toBuilder() : null;
                                    DataSource dataSource2 = (DataSource) codedInputStream.readMessage(DataSource.parser(), extensionRegistryLite);
                                    this.rootCerts_ = dataSource2;
                                    if (builderM14720toBuilder != null) {
                                        builderM14720toBuilder.mergeFrom(dataSource2);
                                        this.rootCerts_ = builderM14720toBuilder.m14727buildPartial();
                                    }
                                } else if (tag == 18) {
                                    DataSource dataSource3 = this.privateKey_;
                                    builderM14720toBuilder = dataSource3 != null ? dataSource3.m14720toBuilder() : null;
                                    DataSource dataSource4 = (DataSource) codedInputStream.readMessage(DataSource.parser(), extensionRegistryLite);
                                    this.privateKey_ = dataSource4;
                                    if (builderM14720toBuilder != null) {
                                        builderM14720toBuilder.mergeFrom(dataSource4);
                                        this.privateKey_ = builderM14720toBuilder.m14727buildPartial();
                                    }
                                } else if (tag == 26) {
                                    DataSource dataSource5 = this.certChain_;
                                    builderM14720toBuilder = dataSource5 != null ? dataSource5.m14720toBuilder() : null;
                                    DataSource dataSource6 = (DataSource) codedInputStream.readMessage(DataSource.parser(), extensionRegistryLite);
                                    this.certChain_ = dataSource6;
                                    if (builderM14720toBuilder != null) {
                                        builderM14720toBuilder.mergeFrom(dataSource6);
                                        this.certChain_ = builderM14720toBuilder.m14727buildPartial();
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

            public static SslCredentials getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<SslCredentials> parser() {
                return PARSER;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return GrpcServiceProto.internal_static_envoy_api_v2_core_GrpcService_GoogleGrpc_SslCredentials_descriptor;
            }

            public static SslCredentials parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
                return (SslCredentials) PARSER.parseFrom(byteBuffer);
            }

            public static SslCredentials parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return (SslCredentials) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
            }

            public static SslCredentials parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
                return (SslCredentials) PARSER.parseFrom(byteString);
            }

            public static SslCredentials parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return (SslCredentials) PARSER.parseFrom(byteString, extensionRegistryLite);
            }

            public static SslCredentials parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
                return (SslCredentials) PARSER.parseFrom(bArr);
            }

            public static SslCredentials parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return (SslCredentials) PARSER.parseFrom(bArr, extensionRegistryLite);
            }

            public static SslCredentials parseFrom(InputStream inputStream) throws IOException {
                return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
            }

            public static SslCredentials parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
            }

            public static SslCredentials parseDelimitedFrom(InputStream inputStream) throws IOException {
                return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
            }

            public static SslCredentials parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
            }

            public static SslCredentials parseFrom(CodedInputStream codedInputStream) throws IOException {
                return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
            }

            public static SslCredentials parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
            }

            public static Builder newBuilder() {
                return DEFAULT_INSTANCE.m15364toBuilder();
            }

            public static Builder newBuilder(SslCredentials sslCredentials) {
                return DEFAULT_INSTANCE.m15364toBuilder().mergeFrom(sslCredentials);
            }

            /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public SslCredentials m15359getDefaultInstanceForType() {
                return DEFAULT_INSTANCE;
            }

            public Parser<SslCredentials> getParserForType() {
                return PARSER;
            }

            @Override
            // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.SslCredentialsOrBuilder
            public boolean hasCertChain() {
                return this.certChain_ != null;
            }

            @Override
            // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.SslCredentialsOrBuilder
            public boolean hasPrivateKey() {
                return this.privateKey_ != null;
            }

            @Override
            // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.SslCredentialsOrBuilder
            public boolean hasRootCerts() {
                return this.rootCerts_ != null;
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
                return new SslCredentials();
            }

            public final UnknownFieldSet getUnknownFields() {
                return this.unknownFields;
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return GrpcServiceProto.internal_static_envoy_api_v2_core_GrpcService_GoogleGrpc_SslCredentials_fieldAccessorTable.ensureFieldAccessorsInitialized(SslCredentials.class, Builder.class);
            }

            @Override
            // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.SslCredentialsOrBuilder
            public DataSource getRootCerts() {
                DataSource dataSource = this.rootCerts_;
                return dataSource == null ? DataSource.getDefaultInstance() : dataSource;
            }

            @Override
            // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.SslCredentialsOrBuilder
            public DataSourceOrBuilder getRootCertsOrBuilder() {
                return getRootCerts();
            }

            @Override
            // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.SslCredentialsOrBuilder
            public DataSource getPrivateKey() {
                DataSource dataSource = this.privateKey_;
                return dataSource == null ? DataSource.getDefaultInstance() : dataSource;
            }

            @Override
            // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.SslCredentialsOrBuilder
            public DataSourceOrBuilder getPrivateKeyOrBuilder() {
                return getPrivateKey();
            }

            @Override
            // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.SslCredentialsOrBuilder
            public DataSource getCertChain() {
                DataSource dataSource = this.certChain_;
                return dataSource == null ? DataSource.getDefaultInstance() : dataSource;
            }

            @Override
            // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.SslCredentialsOrBuilder
            public DataSourceOrBuilder getCertChainOrBuilder() {
                return getCertChain();
            }

            public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
                if (this.rootCerts_ != null) {
                    codedOutputStream.writeMessage(1, getRootCerts());
                }
                if (this.privateKey_ != null) {
                    codedOutputStream.writeMessage(2, getPrivateKey());
                }
                if (this.certChain_ != null) {
                    codedOutputStream.writeMessage(3, getCertChain());
                }
                this.unknownFields.writeTo(codedOutputStream);
            }

            public int getSerializedSize() {
                int i = this.memoizedSize;
                if (i != -1) {
                    return i;
                }
                int iComputeMessageSize = this.rootCerts_ != null ? CodedOutputStream.computeMessageSize(1, getRootCerts()) : 0;
                if (this.privateKey_ != null) {
                    iComputeMessageSize += CodedOutputStream.computeMessageSize(2, getPrivateKey());
                }
                if (this.certChain_ != null) {
                    iComputeMessageSize += CodedOutputStream.computeMessageSize(3, getCertChain());
                }
                int serializedSize = iComputeMessageSize + this.unknownFields.getSerializedSize();
                this.memoizedSize = serializedSize;
                return serializedSize;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof SslCredentials)) {
                    return super.equals(obj);
                }
                SslCredentials sslCredentials = (SslCredentials) obj;
                if (hasRootCerts() != sslCredentials.hasRootCerts()) {
                    return false;
                }
                if ((hasRootCerts() && !getRootCerts().equals(sslCredentials.getRootCerts())) || hasPrivateKey() != sslCredentials.hasPrivateKey()) {
                    return false;
                }
                if ((!hasPrivateKey() || getPrivateKey().equals(sslCredentials.getPrivateKey())) && hasCertChain() == sslCredentials.hasCertChain()) {
                    return (!hasCertChain() || getCertChain().equals(sslCredentials.getCertChain())) && this.unknownFields.equals(sslCredentials.unknownFields);
                }
                return false;
            }

            public int hashCode() {
                if (this.memoizedHashCode != 0) {
                    return this.memoizedHashCode;
                }
                int iHashCode = 779 + getDescriptor().hashCode();
                if (hasRootCerts()) {
                    iHashCode = (((iHashCode * 37) + 1) * 53) + getRootCerts().hashCode();
                }
                if (hasPrivateKey()) {
                    iHashCode = (((iHashCode * 37) + 2) * 53) + getPrivateKey().hashCode();
                }
                if (hasCertChain()) {
                    iHashCode = (((iHashCode * 37) + 3) * 53) + getCertChain().hashCode();
                }
                int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
                this.memoizedHashCode = iHashCode2;
                return iHashCode2;
            }

            /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m15361newBuilderForType() {
                return newBuilder();
            }

            /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m15364toBuilder() {
                return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
                return new Builder(builderParent);
            }

            public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements SslCredentialsOrBuilder {
                private SingleFieldBuilderV3<DataSource, DataSource.Builder, DataSourceOrBuilder> certChainBuilder_;
                private DataSource certChain_;
                private SingleFieldBuilderV3<DataSource, DataSource.Builder, DataSourceOrBuilder> privateKeyBuilder_;
                private DataSource privateKey_;
                private SingleFieldBuilderV3<DataSource, DataSource.Builder, DataSourceOrBuilder> rootCertsBuilder_;
                private DataSource rootCerts_;

                private Builder() {
                    maybeForceBuilderInitialization();
                }

                private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                    super(builderParent);
                    maybeForceBuilderInitialization();
                }

                public static final Descriptors.Descriptor getDescriptor() {
                    return GrpcServiceProto.internal_static_envoy_api_v2_core_GrpcService_GoogleGrpc_SslCredentials_descriptor;
                }

                @Override
                // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.SslCredentialsOrBuilder
                public boolean hasCertChain() {
                    return (this.certChainBuilder_ == null && this.certChain_ == null) ? false : true;
                }

                @Override
                // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.SslCredentialsOrBuilder
                public boolean hasPrivateKey() {
                    return (this.privateKeyBuilder_ == null && this.privateKey_ == null) ? false : true;
                }

                @Override
                // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.SslCredentialsOrBuilder
                public boolean hasRootCerts() {
                    return (this.rootCertsBuilder_ == null && this.rootCerts_ == null) ? false : true;
                }

                public final boolean isInitialized() {
                    return true;
                }

                protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                    return GrpcServiceProto.internal_static_envoy_api_v2_core_GrpcService_GoogleGrpc_SslCredentials_fieldAccessorTable.ensureFieldAccessorsInitialized(SslCredentials.class, Builder.class);
                }

                private void maybeForceBuilderInitialization() {
                    boolean unused = SslCredentials.alwaysUseFieldBuilders;
                }

                /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m15375clear() {
                    super.clear();
                    if (this.rootCertsBuilder_ == null) {
                        this.rootCerts_ = null;
                    } else {
                        this.rootCerts_ = null;
                        this.rootCertsBuilder_ = null;
                    }
                    if (this.privateKeyBuilder_ == null) {
                        this.privateKey_ = null;
                    } else {
                        this.privateKey_ = null;
                        this.privateKeyBuilder_ = null;
                    }
                    if (this.certChainBuilder_ == null) {
                        this.certChain_ = null;
                    } else {
                        this.certChain_ = null;
                        this.certChainBuilder_ = null;
                    }
                    return this;
                }

                public Descriptors.Descriptor getDescriptorForType() {
                    return GrpcServiceProto.internal_static_envoy_api_v2_core_GrpcService_GoogleGrpc_SslCredentials_descriptor;
                }

                /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public SslCredentials m15388getDefaultInstanceForType() {
                    return SslCredentials.getDefaultInstance();
                }

                /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
                /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public SslCredentials m15369build() throws UninitializedMessageException {
                    SslCredentials sslCredentialsM15371buildPartial = m15371buildPartial();
                    if (sslCredentialsM15371buildPartial.isInitialized()) {
                        return sslCredentialsM15371buildPartial;
                    }
                    throw newUninitializedMessageException(sslCredentialsM15371buildPartial);
                }

                /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public SslCredentials m15371buildPartial() {
                    SslCredentials sslCredentials = new SslCredentials(this);
                    SingleFieldBuilderV3<DataSource, DataSource.Builder, DataSourceOrBuilder> singleFieldBuilderV3 = this.rootCertsBuilder_;
                    if (singleFieldBuilderV3 == null) {
                        sslCredentials.rootCerts_ = this.rootCerts_;
                    } else {
                        sslCredentials.rootCerts_ = singleFieldBuilderV3.build();
                    }
                    SingleFieldBuilderV3<DataSource, DataSource.Builder, DataSourceOrBuilder> singleFieldBuilderV32 = this.privateKeyBuilder_;
                    if (singleFieldBuilderV32 == null) {
                        sslCredentials.privateKey_ = this.privateKey_;
                    } else {
                        sslCredentials.privateKey_ = singleFieldBuilderV32.build();
                    }
                    SingleFieldBuilderV3<DataSource, DataSource.Builder, DataSourceOrBuilder> singleFieldBuilderV33 = this.certChainBuilder_;
                    if (singleFieldBuilderV33 == null) {
                        sslCredentials.certChain_ = this.certChain_;
                    } else {
                        sslCredentials.certChain_ = singleFieldBuilderV33.build();
                    }
                    onBuilt();
                    return sslCredentials;
                }

                /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m15387clone() {
                    return (Builder) super.clone();
                }

                /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m15399setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                    return (Builder) super.setField(fieldDescriptor, obj);
                }

                /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m15377clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                    return (Builder) super.clearField(fieldDescriptor);
                }

                /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m15380clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                    return (Builder) super.clearOneof(oneofDescriptor);
                }

                /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m15401setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                    return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
                }

                /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m15367addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                    return (Builder) super.addRepeatedField(fieldDescriptor, obj);
                }

                /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m15392mergeFrom(Message message) {
                    if (message instanceof SslCredentials) {
                        return mergeFrom((SslCredentials) message);
                    }
                    super.mergeFrom(message);
                    return this;
                }

                public Builder mergeFrom(SslCredentials sslCredentials) {
                    if (sslCredentials == SslCredentials.getDefaultInstance()) {
                        return this;
                    }
                    if (sslCredentials.hasRootCerts()) {
                        mergeRootCerts(sslCredentials.getRootCerts());
                    }
                    if (sslCredentials.hasPrivateKey()) {
                        mergePrivateKey(sslCredentials.getPrivateKey());
                    }
                    if (sslCredentials.hasCertChain()) {
                        mergeCertChain(sslCredentials.getCertChain());
                    }
                    m15397mergeUnknownFields(sslCredentials.unknownFields);
                    onChanged();
                    return this;
                }

                /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
                /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.SslCredentials.Builder m15393mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
                    /*
                        r2 = this;
                        r0 = 0
                        com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.SslCredentials.access$1700()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                        java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                        io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService$GoogleGrpc$SslCredentials r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.SslCredentials) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                        io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService$GoogleGrpc$SslCredentials r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.SslCredentials) r4     // Catch: java.lang.Throwable -> L11
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
                    throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.SslCredentials.Builder.m15393mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService$GoogleGrpc$SslCredentials$Builder");
                }

                @Override
                // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.SslCredentialsOrBuilder
                public DataSource getRootCerts() {
                    SingleFieldBuilderV3<DataSource, DataSource.Builder, DataSourceOrBuilder> singleFieldBuilderV3 = this.rootCertsBuilder_;
                    if (singleFieldBuilderV3 != null) {
                        return singleFieldBuilderV3.getMessage();
                    }
                    DataSource dataSource = this.rootCerts_;
                    return dataSource == null ? DataSource.getDefaultInstance() : dataSource;
                }

                public Builder setRootCerts(DataSource dataSource) {
                    SingleFieldBuilderV3<DataSource, DataSource.Builder, DataSourceOrBuilder> singleFieldBuilderV3 = this.rootCertsBuilder_;
                    if (singleFieldBuilderV3 == null) {
                        dataSource.getClass();
                        this.rootCerts_ = dataSource;
                        onChanged();
                    } else {
                        singleFieldBuilderV3.setMessage(dataSource);
                    }
                    return this;
                }

                public Builder setRootCerts(DataSource.Builder builder) {
                    SingleFieldBuilderV3<DataSource, DataSource.Builder, DataSourceOrBuilder> singleFieldBuilderV3 = this.rootCertsBuilder_;
                    if (singleFieldBuilderV3 == null) {
                        this.rootCerts_ = builder.m14725build();
                        onChanged();
                    } else {
                        singleFieldBuilderV3.setMessage(builder.m14725build());
                    }
                    return this;
                }

                public Builder mergeRootCerts(DataSource dataSource) {
                    SingleFieldBuilderV3<DataSource, DataSource.Builder, DataSourceOrBuilder> singleFieldBuilderV3 = this.rootCertsBuilder_;
                    if (singleFieldBuilderV3 == null) {
                        DataSource dataSource2 = this.rootCerts_;
                        if (dataSource2 != null) {
                            this.rootCerts_ = DataSource.newBuilder(dataSource2).mergeFrom(dataSource).m14727buildPartial();
                        } else {
                            this.rootCerts_ = dataSource;
                        }
                        onChanged();
                    } else {
                        singleFieldBuilderV3.mergeFrom(dataSource);
                    }
                    return this;
                }

                public Builder clearRootCerts() {
                    if (this.rootCertsBuilder_ == null) {
                        this.rootCerts_ = null;
                        onChanged();
                    } else {
                        this.rootCerts_ = null;
                        this.rootCertsBuilder_ = null;
                    }
                    return this;
                }

                public DataSource.Builder getRootCertsBuilder() {
                    onChanged();
                    return getRootCertsFieldBuilder().getBuilder();
                }

                @Override
                // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.SslCredentialsOrBuilder
                public DataSourceOrBuilder getRootCertsOrBuilder() {
                    SingleFieldBuilderV3<DataSource, DataSource.Builder, DataSourceOrBuilder> singleFieldBuilderV3 = this.rootCertsBuilder_;
                    if (singleFieldBuilderV3 != null) {
                        return (DataSourceOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
                    }
                    DataSource dataSource = this.rootCerts_;
                    return dataSource == null ? DataSource.getDefaultInstance() : dataSource;
                }

                private SingleFieldBuilderV3<DataSource, DataSource.Builder, DataSourceOrBuilder> getRootCertsFieldBuilder() {
                    if (this.rootCertsBuilder_ == null) {
                        this.rootCertsBuilder_ = new SingleFieldBuilderV3<>(getRootCerts(), getParentForChildren(), isClean());
                        this.rootCerts_ = null;
                    }
                    return this.rootCertsBuilder_;
                }

                @Override
                // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.SslCredentialsOrBuilder
                public DataSource getPrivateKey() {
                    SingleFieldBuilderV3<DataSource, DataSource.Builder, DataSourceOrBuilder> singleFieldBuilderV3 = this.privateKeyBuilder_;
                    if (singleFieldBuilderV3 != null) {
                        return singleFieldBuilderV3.getMessage();
                    }
                    DataSource dataSource = this.privateKey_;
                    return dataSource == null ? DataSource.getDefaultInstance() : dataSource;
                }

                public Builder setPrivateKey(DataSource dataSource) {
                    SingleFieldBuilderV3<DataSource, DataSource.Builder, DataSourceOrBuilder> singleFieldBuilderV3 = this.privateKeyBuilder_;
                    if (singleFieldBuilderV3 == null) {
                        dataSource.getClass();
                        this.privateKey_ = dataSource;
                        onChanged();
                    } else {
                        singleFieldBuilderV3.setMessage(dataSource);
                    }
                    return this;
                }

                public Builder setPrivateKey(DataSource.Builder builder) {
                    SingleFieldBuilderV3<DataSource, DataSource.Builder, DataSourceOrBuilder> singleFieldBuilderV3 = this.privateKeyBuilder_;
                    if (singleFieldBuilderV3 == null) {
                        this.privateKey_ = builder.m14725build();
                        onChanged();
                    } else {
                        singleFieldBuilderV3.setMessage(builder.m14725build());
                    }
                    return this;
                }

                public Builder mergePrivateKey(DataSource dataSource) {
                    SingleFieldBuilderV3<DataSource, DataSource.Builder, DataSourceOrBuilder> singleFieldBuilderV3 = this.privateKeyBuilder_;
                    if (singleFieldBuilderV3 == null) {
                        DataSource dataSource2 = this.privateKey_;
                        if (dataSource2 != null) {
                            this.privateKey_ = DataSource.newBuilder(dataSource2).mergeFrom(dataSource).m14727buildPartial();
                        } else {
                            this.privateKey_ = dataSource;
                        }
                        onChanged();
                    } else {
                        singleFieldBuilderV3.mergeFrom(dataSource);
                    }
                    return this;
                }

                public Builder clearPrivateKey() {
                    if (this.privateKeyBuilder_ == null) {
                        this.privateKey_ = null;
                        onChanged();
                    } else {
                        this.privateKey_ = null;
                        this.privateKeyBuilder_ = null;
                    }
                    return this;
                }

                public DataSource.Builder getPrivateKeyBuilder() {
                    onChanged();
                    return getPrivateKeyFieldBuilder().getBuilder();
                }

                @Override
                // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.SslCredentialsOrBuilder
                public DataSourceOrBuilder getPrivateKeyOrBuilder() {
                    SingleFieldBuilderV3<DataSource, DataSource.Builder, DataSourceOrBuilder> singleFieldBuilderV3 = this.privateKeyBuilder_;
                    if (singleFieldBuilderV3 != null) {
                        return (DataSourceOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
                    }
                    DataSource dataSource = this.privateKey_;
                    return dataSource == null ? DataSource.getDefaultInstance() : dataSource;
                }

                private SingleFieldBuilderV3<DataSource, DataSource.Builder, DataSourceOrBuilder> getPrivateKeyFieldBuilder() {
                    if (this.privateKeyBuilder_ == null) {
                        this.privateKeyBuilder_ = new SingleFieldBuilderV3<>(getPrivateKey(), getParentForChildren(), isClean());
                        this.privateKey_ = null;
                    }
                    return this.privateKeyBuilder_;
                }

                @Override
                // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.SslCredentialsOrBuilder
                public DataSource getCertChain() {
                    SingleFieldBuilderV3<DataSource, DataSource.Builder, DataSourceOrBuilder> singleFieldBuilderV3 = this.certChainBuilder_;
                    if (singleFieldBuilderV3 != null) {
                        return singleFieldBuilderV3.getMessage();
                    }
                    DataSource dataSource = this.certChain_;
                    return dataSource == null ? DataSource.getDefaultInstance() : dataSource;
                }

                public Builder setCertChain(DataSource dataSource) {
                    SingleFieldBuilderV3<DataSource, DataSource.Builder, DataSourceOrBuilder> singleFieldBuilderV3 = this.certChainBuilder_;
                    if (singleFieldBuilderV3 == null) {
                        dataSource.getClass();
                        this.certChain_ = dataSource;
                        onChanged();
                    } else {
                        singleFieldBuilderV3.setMessage(dataSource);
                    }
                    return this;
                }

                public Builder setCertChain(DataSource.Builder builder) {
                    SingleFieldBuilderV3<DataSource, DataSource.Builder, DataSourceOrBuilder> singleFieldBuilderV3 = this.certChainBuilder_;
                    if (singleFieldBuilderV3 == null) {
                        this.certChain_ = builder.m14725build();
                        onChanged();
                    } else {
                        singleFieldBuilderV3.setMessage(builder.m14725build());
                    }
                    return this;
                }

                public Builder mergeCertChain(DataSource dataSource) {
                    SingleFieldBuilderV3<DataSource, DataSource.Builder, DataSourceOrBuilder> singleFieldBuilderV3 = this.certChainBuilder_;
                    if (singleFieldBuilderV3 == null) {
                        DataSource dataSource2 = this.certChain_;
                        if (dataSource2 != null) {
                            this.certChain_ = DataSource.newBuilder(dataSource2).mergeFrom(dataSource).m14727buildPartial();
                        } else {
                            this.certChain_ = dataSource;
                        }
                        onChanged();
                    } else {
                        singleFieldBuilderV3.mergeFrom(dataSource);
                    }
                    return this;
                }

                public Builder clearCertChain() {
                    if (this.certChainBuilder_ == null) {
                        this.certChain_ = null;
                        onChanged();
                    } else {
                        this.certChain_ = null;
                        this.certChainBuilder_ = null;
                    }
                    return this;
                }

                public DataSource.Builder getCertChainBuilder() {
                    onChanged();
                    return getCertChainFieldBuilder().getBuilder();
                }

                @Override
                // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.SslCredentialsOrBuilder
                public DataSourceOrBuilder getCertChainOrBuilder() {
                    SingleFieldBuilderV3<DataSource, DataSource.Builder, DataSourceOrBuilder> singleFieldBuilderV3 = this.certChainBuilder_;
                    if (singleFieldBuilderV3 != null) {
                        return (DataSourceOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
                    }
                    DataSource dataSource = this.certChain_;
                    return dataSource == null ? DataSource.getDefaultInstance() : dataSource;
                }

                private SingleFieldBuilderV3<DataSource, DataSource.Builder, DataSourceOrBuilder> getCertChainFieldBuilder() {
                    if (this.certChainBuilder_ == null) {
                        this.certChainBuilder_ = new SingleFieldBuilderV3<>(getCertChain(), getParentForChildren(), isClean());
                        this.certChain_ = null;
                    }
                    return this.certChainBuilder_;
                }

                /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public final Builder m15403setUnknownFields(UnknownFieldSet unknownFieldSet) {
                    return (Builder) super.setUnknownFields(unknownFieldSet);
                }

                /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public final Builder m15397mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                    return (Builder) super.mergeUnknownFields(unknownFieldSet);
                }
            }
        }

        public static final class GoogleLocalCredentials extends GeneratedMessageV3 implements GoogleLocalCredentialsOrBuilder {
            private static final GoogleLocalCredentials DEFAULT_INSTANCE = new GoogleLocalCredentials();
            private static final Parser<GoogleLocalCredentials> PARSER = new AbstractParser<GoogleLocalCredentials>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.GoogleLocalCredentials.1
                /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
                public GoogleLocalCredentials m15320parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                    return new GoogleLocalCredentials(codedInputStream, extensionRegistryLite);
                }
            };
            private static final long serialVersionUID = 0;
            private byte memoizedIsInitialized;

            private GoogleLocalCredentials(GeneratedMessageV3.Builder<?> builder) {
                super(builder);
                this.memoizedIsInitialized = (byte) -1;
            }

            private GoogleLocalCredentials() {
                this.memoizedIsInitialized = (byte) -1;
            }

            private GoogleLocalCredentials(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                this();
                extensionRegistryLite.getClass();
                UnknownFieldSet.Builder builderNewBuilder = UnknownFieldSet.newBuilder();
                boolean z = false;
                while (!z) {
                    try {
                        try {
                            try {
                                int tag = codedInputStream.readTag();
                                if (tag == 0 || !parseUnknownField(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                                    z = true;
                                }
                            } catch (InvalidProtocolBufferException e) {
                                throw e.setUnfinishedMessage(this);
                            }
                        } catch (IOException e2) {
                            throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                        }
                    } finally {
                        this.unknownFields = builderNewBuilder.build();
                        makeExtensionsImmutable();
                    }
                }
            }

            public static GoogleLocalCredentials getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<GoogleLocalCredentials> parser() {
                return PARSER;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return GrpcServiceProto.internal_static_envoy_api_v2_core_GrpcService_GoogleGrpc_GoogleLocalCredentials_descriptor;
            }

            public static GoogleLocalCredentials parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
                return (GoogleLocalCredentials) PARSER.parseFrom(byteBuffer);
            }

            public static GoogleLocalCredentials parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return (GoogleLocalCredentials) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
            }

            public static GoogleLocalCredentials parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
                return (GoogleLocalCredentials) PARSER.parseFrom(byteString);
            }

            public static GoogleLocalCredentials parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return (GoogleLocalCredentials) PARSER.parseFrom(byteString, extensionRegistryLite);
            }

            public static GoogleLocalCredentials parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
                return (GoogleLocalCredentials) PARSER.parseFrom(bArr);
            }

            public static GoogleLocalCredentials parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return (GoogleLocalCredentials) PARSER.parseFrom(bArr, extensionRegistryLite);
            }

            public static GoogleLocalCredentials parseFrom(InputStream inputStream) throws IOException {
                return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
            }

            public static GoogleLocalCredentials parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
            }

            public static GoogleLocalCredentials parseDelimitedFrom(InputStream inputStream) throws IOException {
                return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
            }

            public static GoogleLocalCredentials parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
            }

            public static GoogleLocalCredentials parseFrom(CodedInputStream codedInputStream) throws IOException {
                return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
            }

            public static GoogleLocalCredentials parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
            }

            public static Builder newBuilder() {
                return DEFAULT_INSTANCE.m15318toBuilder();
            }

            public static Builder newBuilder(GoogleLocalCredentials googleLocalCredentials) {
                return DEFAULT_INSTANCE.m15318toBuilder().mergeFrom(googleLocalCredentials);
            }

            /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public GoogleLocalCredentials m15313getDefaultInstanceForType() {
                return DEFAULT_INSTANCE;
            }

            public Parser<GoogleLocalCredentials> getParserForType() {
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
                return new GoogleLocalCredentials();
            }

            public final UnknownFieldSet getUnknownFields() {
                return this.unknownFields;
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return GrpcServiceProto.internal_static_envoy_api_v2_core_GrpcService_GoogleGrpc_GoogleLocalCredentials_fieldAccessorTable.ensureFieldAccessorsInitialized(GoogleLocalCredentials.class, Builder.class);
            }

            public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
                this.unknownFields.writeTo(codedOutputStream);
            }

            public int getSerializedSize() {
                int i = this.memoizedSize;
                if (i != -1) {
                    return i;
                }
                int serializedSize = this.unknownFields.getSerializedSize();
                this.memoizedSize = serializedSize;
                return serializedSize;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (obj instanceof GoogleLocalCredentials) {
                    return this.unknownFields.equals(((GoogleLocalCredentials) obj).unknownFields);
                }
                return super.equals(obj);
            }

            public int hashCode() {
                if (this.memoizedHashCode != 0) {
                    return this.memoizedHashCode;
                }
                int iHashCode = ((779 + getDescriptor().hashCode()) * 29) + this.unknownFields.hashCode();
                this.memoizedHashCode = iHashCode;
                return iHashCode;
            }

            /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m15315newBuilderForType() {
                return newBuilder();
            }

            /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m15318toBuilder() {
                return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
                return new Builder(builderParent);
            }

            public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements GoogleLocalCredentialsOrBuilder {
                private Builder() {
                    maybeForceBuilderInitialization();
                }

                private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                    super(builderParent);
                    maybeForceBuilderInitialization();
                }

                public static final Descriptors.Descriptor getDescriptor() {
                    return GrpcServiceProto.internal_static_envoy_api_v2_core_GrpcService_GoogleGrpc_GoogleLocalCredentials_descriptor;
                }

                public final boolean isInitialized() {
                    return true;
                }

                protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                    return GrpcServiceProto.internal_static_envoy_api_v2_core_GrpcService_GoogleGrpc_GoogleLocalCredentials_fieldAccessorTable.ensureFieldAccessorsInitialized(GoogleLocalCredentials.class, Builder.class);
                }

                private void maybeForceBuilderInitialization() {
                    boolean unused = GoogleLocalCredentials.alwaysUseFieldBuilders;
                }

                /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m15329clear() {
                    super.clear();
                    return this;
                }

                public Descriptors.Descriptor getDescriptorForType() {
                    return GrpcServiceProto.internal_static_envoy_api_v2_core_GrpcService_GoogleGrpc_GoogleLocalCredentials_descriptor;
                }

                /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public GoogleLocalCredentials m15342getDefaultInstanceForType() {
                    return GoogleLocalCredentials.getDefaultInstance();
                }

                /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
                /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public GoogleLocalCredentials m15323build() throws UninitializedMessageException {
                    GoogleLocalCredentials googleLocalCredentialsM15325buildPartial = m15325buildPartial();
                    if (googleLocalCredentialsM15325buildPartial.isInitialized()) {
                        return googleLocalCredentialsM15325buildPartial;
                    }
                    throw newUninitializedMessageException(googleLocalCredentialsM15325buildPartial);
                }

                /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public GoogleLocalCredentials m15325buildPartial() {
                    GoogleLocalCredentials googleLocalCredentials = new GoogleLocalCredentials(this);
                    onBuilt();
                    return googleLocalCredentials;
                }

                /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m15341clone() {
                    return (Builder) super.clone();
                }

                /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m15353setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                    return (Builder) super.setField(fieldDescriptor, obj);
                }

                /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m15331clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                    return (Builder) super.clearField(fieldDescriptor);
                }

                /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m15334clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                    return (Builder) super.clearOneof(oneofDescriptor);
                }

                /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m15355setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                    return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
                }

                /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m15321addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                    return (Builder) super.addRepeatedField(fieldDescriptor, obj);
                }

                /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m15346mergeFrom(Message message) {
                    if (message instanceof GoogleLocalCredentials) {
                        return mergeFrom((GoogleLocalCredentials) message);
                    }
                    super.mergeFrom(message);
                    return this;
                }

                public Builder mergeFrom(GoogleLocalCredentials googleLocalCredentials) {
                    if (googleLocalCredentials == GoogleLocalCredentials.getDefaultInstance()) {
                        return this;
                    }
                    m15351mergeUnknownFields(googleLocalCredentials.unknownFields);
                    onChanged();
                    return this;
                }

                /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
                /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.GoogleLocalCredentials.Builder m15347mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
                    /*
                        r2 = this;
                        r0 = 0
                        com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.GoogleLocalCredentials.access$2400()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                        java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                        io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService$GoogleGrpc$GoogleLocalCredentials r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.GoogleLocalCredentials) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                        io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService$GoogleGrpc$GoogleLocalCredentials r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.GoogleLocalCredentials) r4     // Catch: java.lang.Throwable -> L11
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
                    throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.GoogleLocalCredentials.Builder.m15347mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService$GoogleGrpc$GoogleLocalCredentials$Builder");
                }

                /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public final Builder m15357setUnknownFields(UnknownFieldSet unknownFieldSet) {
                    return (Builder) super.setUnknownFields(unknownFieldSet);
                }

                /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public final Builder m15351mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                    return (Builder) super.mergeUnknownFields(unknownFieldSet);
                }
            }
        }

        public static final class ChannelCredentials extends GeneratedMessageV3 implements ChannelCredentialsOrBuilder {
            public static final int GOOGLE_DEFAULT_FIELD_NUMBER = 2;
            public static final int LOCAL_CREDENTIALS_FIELD_NUMBER = 3;
            public static final int SSL_CREDENTIALS_FIELD_NUMBER = 1;
            private static final long serialVersionUID = 0;
            private static final ChannelCredentials DEFAULT_INSTANCE = new ChannelCredentials();
            private static final Parser<ChannelCredentials> PARSER = new AbstractParser<ChannelCredentials>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.ChannelCredentials.1
                /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
                public ChannelCredentials m15274parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                    return new ChannelCredentials(codedInputStream, extensionRegistryLite);
                }
            };
            private int credentialSpecifierCase_;
            private Object credentialSpecifier_;
            private byte memoizedIsInitialized;

            private ChannelCredentials(GeneratedMessageV3.Builder<?> builder) {
                super(builder);
                this.credentialSpecifierCase_ = 0;
                this.memoizedIsInitialized = (byte) -1;
            }

            private ChannelCredentials() {
                this.credentialSpecifierCase_ = 0;
                this.memoizedIsInitialized = (byte) -1;
            }

            private ChannelCredentials(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                Empty.Builder builderM15318toBuilder;
                this();
                extensionRegistryLite.getClass();
                UnknownFieldSet.Builder builderNewBuilder = UnknownFieldSet.newBuilder();
                boolean z = false;
                while (!z) {
                    try {
                        try {
                            try {
                                int tag = codedInputStream.readTag();
                                if (tag != 0) {
                                    if (tag == 10) {
                                        builderM15318toBuilder = this.credentialSpecifierCase_ == 1 ? ((SslCredentials) this.credentialSpecifier_).m15364toBuilder() : null;
                                        MessageLite message = codedInputStream.readMessage(SslCredentials.parser(), extensionRegistryLite);
                                        this.credentialSpecifier_ = message;
                                        if (builderM15318toBuilder != null) {
                                            builderM15318toBuilder.mergeFrom((SslCredentials) message);
                                            this.credentialSpecifier_ = builderM15318toBuilder.m15371buildPartial();
                                        }
                                        this.credentialSpecifierCase_ = 1;
                                    } else if (tag == 18) {
                                        builderM15318toBuilder = this.credentialSpecifierCase_ == 2 ? ((Empty) this.credentialSpecifier_).toBuilder() : null;
                                        Empty message2 = codedInputStream.readMessage(Empty.parser(), extensionRegistryLite);
                                        this.credentialSpecifier_ = message2;
                                        if (builderM15318toBuilder != null) {
                                            builderM15318toBuilder.mergeFrom(message2);
                                            this.credentialSpecifier_ = builderM15318toBuilder.buildPartial();
                                        }
                                        this.credentialSpecifierCase_ = 2;
                                    } else if (tag == 26) {
                                        builderM15318toBuilder = this.credentialSpecifierCase_ == 3 ? ((GoogleLocalCredentials) this.credentialSpecifier_).m15318toBuilder() : null;
                                        GoogleLocalCredentials message3 = codedInputStream.readMessage(GoogleLocalCredentials.parser(), extensionRegistryLite);
                                        this.credentialSpecifier_ = message3;
                                        if (builderM15318toBuilder != null) {
                                            builderM15318toBuilder.mergeFrom(message3);
                                            this.credentialSpecifier_ = builderM15318toBuilder.m15325buildPartial();
                                        }
                                        this.credentialSpecifierCase_ = 3;
                                    } else if (!parseUnknownField(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                                    }
                                }
                                z = true;
                            } catch (IOException e) {
                                throw new InvalidProtocolBufferException(e).setUnfinishedMessage(this);
                            }
                        } catch (InvalidProtocolBufferException e2) {
                            throw e2.setUnfinishedMessage(this);
                        }
                    } finally {
                        this.unknownFields = builderNewBuilder.build();
                        makeExtensionsImmutable();
                    }
                }
            }

            public static ChannelCredentials getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<ChannelCredentials> parser() {
                return PARSER;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return GrpcServiceProto.internal_static_envoy_api_v2_core_GrpcService_GoogleGrpc_ChannelCredentials_descriptor;
            }

            public static ChannelCredentials parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
                return (ChannelCredentials) PARSER.parseFrom(byteBuffer);
            }

            public static ChannelCredentials parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return (ChannelCredentials) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
            }

            public static ChannelCredentials parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
                return (ChannelCredentials) PARSER.parseFrom(byteString);
            }

            public static ChannelCredentials parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return (ChannelCredentials) PARSER.parseFrom(byteString, extensionRegistryLite);
            }

            public static ChannelCredentials parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
                return (ChannelCredentials) PARSER.parseFrom(bArr);
            }

            public static ChannelCredentials parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return (ChannelCredentials) PARSER.parseFrom(bArr, extensionRegistryLite);
            }

            public static ChannelCredentials parseFrom(InputStream inputStream) throws IOException {
                return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
            }

            public static ChannelCredentials parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
            }

            public static ChannelCredentials parseDelimitedFrom(InputStream inputStream) throws IOException {
                return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
            }

            public static ChannelCredentials parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
            }

            public static ChannelCredentials parseFrom(CodedInputStream codedInputStream) throws IOException {
                return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
            }

            public static ChannelCredentials parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
            }

            public static Builder newBuilder() {
                return DEFAULT_INSTANCE.m15272toBuilder();
            }

            public static Builder newBuilder(ChannelCredentials channelCredentials) {
                return DEFAULT_INSTANCE.m15272toBuilder().mergeFrom(channelCredentials);
            }

            /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public ChannelCredentials m15267getDefaultInstanceForType() {
                return DEFAULT_INSTANCE;
            }

            public Parser<ChannelCredentials> getParserForType() {
                return PARSER;
            }

            @Override
            // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.ChannelCredentialsOrBuilder
            public boolean hasGoogleDefault() {
                return this.credentialSpecifierCase_ == 2;
            }

            @Override
            // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.ChannelCredentialsOrBuilder
            public boolean hasLocalCredentials() {
                return this.credentialSpecifierCase_ == 3;
            }

            @Override
            // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.ChannelCredentialsOrBuilder
            public boolean hasSslCredentials() {
                return this.credentialSpecifierCase_ == 1;
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
                return new ChannelCredentials();
            }

            public final UnknownFieldSet getUnknownFields() {
                return this.unknownFields;
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return GrpcServiceProto.internal_static_envoy_api_v2_core_GrpcService_GoogleGrpc_ChannelCredentials_fieldAccessorTable.ensureFieldAccessorsInitialized(ChannelCredentials.class, Builder.class);
            }

            @Override
            // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.ChannelCredentialsOrBuilder
            public CredentialSpecifierCase getCredentialSpecifierCase() {
                return CredentialSpecifierCase.forNumber(this.credentialSpecifierCase_);
            }

            @Override
            // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.ChannelCredentialsOrBuilder
            public SslCredentials getSslCredentials() {
                if (this.credentialSpecifierCase_ == 1) {
                    return (SslCredentials) this.credentialSpecifier_;
                }
                return SslCredentials.getDefaultInstance();
            }

            @Override
            // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.ChannelCredentialsOrBuilder
            public SslCredentialsOrBuilder getSslCredentialsOrBuilder() {
                if (this.credentialSpecifierCase_ == 1) {
                    return (SslCredentials) this.credentialSpecifier_;
                }
                return SslCredentials.getDefaultInstance();
            }

            @Override
            // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.ChannelCredentialsOrBuilder
            public Empty getGoogleDefault() {
                if (this.credentialSpecifierCase_ == 2) {
                    return (Empty) this.credentialSpecifier_;
                }
                return Empty.getDefaultInstance();
            }

            @Override
            // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.ChannelCredentialsOrBuilder
            public EmptyOrBuilder getGoogleDefaultOrBuilder() {
                if (this.credentialSpecifierCase_ == 2) {
                    return (Empty) this.credentialSpecifier_;
                }
                return Empty.getDefaultInstance();
            }

            @Override
            // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.ChannelCredentialsOrBuilder
            public GoogleLocalCredentials getLocalCredentials() {
                if (this.credentialSpecifierCase_ == 3) {
                    return (GoogleLocalCredentials) this.credentialSpecifier_;
                }
                return GoogleLocalCredentials.getDefaultInstance();
            }

            @Override
            // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.ChannelCredentialsOrBuilder
            public GoogleLocalCredentialsOrBuilder getLocalCredentialsOrBuilder() {
                if (this.credentialSpecifierCase_ == 3) {
                    return (GoogleLocalCredentials) this.credentialSpecifier_;
                }
                return GoogleLocalCredentials.getDefaultInstance();
            }

            public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
                if (this.credentialSpecifierCase_ == 1) {
                    codedOutputStream.writeMessage(1, (SslCredentials) this.credentialSpecifier_);
                }
                if (this.credentialSpecifierCase_ == 2) {
                    codedOutputStream.writeMessage(2, (Empty) this.credentialSpecifier_);
                }
                if (this.credentialSpecifierCase_ == 3) {
                    codedOutputStream.writeMessage(3, (GoogleLocalCredentials) this.credentialSpecifier_);
                }
                this.unknownFields.writeTo(codedOutputStream);
            }

            public int getSerializedSize() {
                int i = this.memoizedSize;
                if (i != -1) {
                    return i;
                }
                int iComputeMessageSize = this.credentialSpecifierCase_ == 1 ? CodedOutputStream.computeMessageSize(1, (SslCredentials) this.credentialSpecifier_) : 0;
                if (this.credentialSpecifierCase_ == 2) {
                    iComputeMessageSize += CodedOutputStream.computeMessageSize(2, (Empty) this.credentialSpecifier_);
                }
                if (this.credentialSpecifierCase_ == 3) {
                    iComputeMessageSize += CodedOutputStream.computeMessageSize(3, (GoogleLocalCredentials) this.credentialSpecifier_);
                }
                int serializedSize = iComputeMessageSize + this.unknownFields.getSerializedSize();
                this.memoizedSize = serializedSize;
                return serializedSize;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof ChannelCredentials)) {
                    return super.equals(obj);
                }
                ChannelCredentials channelCredentials = (ChannelCredentials) obj;
                if (!getCredentialSpecifierCase().equals(channelCredentials.getCredentialSpecifierCase())) {
                    return false;
                }
                int i = this.credentialSpecifierCase_;
                if (i != 1) {
                    if (i == 2) {
                        if (!getGoogleDefault().equals(channelCredentials.getGoogleDefault())) {
                            return false;
                        }
                    } else if (i == 3 && !getLocalCredentials().equals(channelCredentials.getLocalCredentials())) {
                        return false;
                    }
                } else if (!getSslCredentials().equals(channelCredentials.getSslCredentials())) {
                    return false;
                }
                return this.unknownFields.equals(channelCredentials.unknownFields);
            }

            public int hashCode() {
                int i;
                int iHashCode;
                if (this.memoizedHashCode != 0) {
                    return this.memoizedHashCode;
                }
                int iHashCode2 = 779 + getDescriptor().hashCode();
                int i2 = this.credentialSpecifierCase_;
                if (i2 == 1) {
                    i = ((iHashCode2 * 37) + 1) * 53;
                    iHashCode = getSslCredentials().hashCode();
                } else if (i2 == 2) {
                    i = ((iHashCode2 * 37) + 2) * 53;
                    iHashCode = getGoogleDefault().hashCode();
                } else {
                    if (i2 == 3) {
                        i = ((iHashCode2 * 37) + 3) * 53;
                        iHashCode = getLocalCredentials().hashCode();
                    }
                    int iHashCode3 = (iHashCode2 * 29) + this.unknownFields.hashCode();
                    this.memoizedHashCode = iHashCode3;
                    return iHashCode3;
                }
                iHashCode2 = i + iHashCode;
                int iHashCode32 = (iHashCode2 * 29) + this.unknownFields.hashCode();
                this.memoizedHashCode = iHashCode32;
                return iHashCode32;
            }

            /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m15269newBuilderForType() {
                return newBuilder();
            }

            /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m15272toBuilder() {
                return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
                return new Builder(builderParent);
            }

            public enum CredentialSpecifierCase implements Internal.EnumLite, AbstractMessageLite.InternalOneOfEnum {
                SSL_CREDENTIALS(1),
                GOOGLE_DEFAULT(2),
                LOCAL_CREDENTIALS(3),
                CREDENTIALSPECIFIER_NOT_SET(0);

                private final int value;

                CredentialSpecifierCase(int i) {
                    this.value = i;
                }

                public static CredentialSpecifierCase forNumber(int i) {
                    if (i == 0) {
                        return CREDENTIALSPECIFIER_NOT_SET;
                    }
                    if (i == 1) {
                        return SSL_CREDENTIALS;
                    }
                    if (i == 2) {
                        return GOOGLE_DEFAULT;
                    }
                    if (i != 3) {
                        return null;
                    }
                    return LOCAL_CREDENTIALS;
                }

                @Deprecated
                public static CredentialSpecifierCase valueOf(int i) {
                    return forNumber(i);
                }

                public int getNumber() {
                    return this.value;
                }
            }

            public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ChannelCredentialsOrBuilder {
                private int credentialSpecifierCase_;
                private Object credentialSpecifier_;
                private SingleFieldBuilderV3<Empty, Empty.Builder, EmptyOrBuilder> googleDefaultBuilder_;
                private SingleFieldBuilderV3<GoogleLocalCredentials, GoogleLocalCredentials.Builder, GoogleLocalCredentialsOrBuilder> localCredentialsBuilder_;
                private SingleFieldBuilderV3<SslCredentials, SslCredentials.Builder, SslCredentialsOrBuilder> sslCredentialsBuilder_;

                private Builder() {
                    this.credentialSpecifierCase_ = 0;
                    maybeForceBuilderInitialization();
                }

                private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                    super(builderParent);
                    this.credentialSpecifierCase_ = 0;
                    maybeForceBuilderInitialization();
                }

                public static final Descriptors.Descriptor getDescriptor() {
                    return GrpcServiceProto.internal_static_envoy_api_v2_core_GrpcService_GoogleGrpc_ChannelCredentials_descriptor;
                }

                @Override
                // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.ChannelCredentialsOrBuilder
                public boolean hasGoogleDefault() {
                    return this.credentialSpecifierCase_ == 2;
                }

                @Override
                // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.ChannelCredentialsOrBuilder
                public boolean hasLocalCredentials() {
                    return this.credentialSpecifierCase_ == 3;
                }

                @Override
                // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.ChannelCredentialsOrBuilder
                public boolean hasSslCredentials() {
                    return this.credentialSpecifierCase_ == 1;
                }

                public final boolean isInitialized() {
                    return true;
                }

                protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                    return GrpcServiceProto.internal_static_envoy_api_v2_core_GrpcService_GoogleGrpc_ChannelCredentials_fieldAccessorTable.ensureFieldAccessorsInitialized(ChannelCredentials.class, Builder.class);
                }

                private void maybeForceBuilderInitialization() {
                    boolean unused = ChannelCredentials.alwaysUseFieldBuilders;
                }

                /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m15283clear() {
                    super.clear();
                    this.credentialSpecifierCase_ = 0;
                    this.credentialSpecifier_ = null;
                    return this;
                }

                public Descriptors.Descriptor getDescriptorForType() {
                    return GrpcServiceProto.internal_static_envoy_api_v2_core_GrpcService_GoogleGrpc_ChannelCredentials_descriptor;
                }

                /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public ChannelCredentials m15296getDefaultInstanceForType() {
                    return ChannelCredentials.getDefaultInstance();
                }

                /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
                /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public ChannelCredentials m15277build() throws UninitializedMessageException {
                    ChannelCredentials channelCredentialsM15279buildPartial = m15279buildPartial();
                    if (channelCredentialsM15279buildPartial.isInitialized()) {
                        return channelCredentialsM15279buildPartial;
                    }
                    throw newUninitializedMessageException(channelCredentialsM15279buildPartial);
                }

                /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public ChannelCredentials m15279buildPartial() {
                    ChannelCredentials channelCredentials = new ChannelCredentials(this);
                    if (this.credentialSpecifierCase_ == 1) {
                        SingleFieldBuilderV3<SslCredentials, SslCredentials.Builder, SslCredentialsOrBuilder> singleFieldBuilderV3 = this.sslCredentialsBuilder_;
                        if (singleFieldBuilderV3 == null) {
                            channelCredentials.credentialSpecifier_ = this.credentialSpecifier_;
                        } else {
                            channelCredentials.credentialSpecifier_ = singleFieldBuilderV3.build();
                        }
                    }
                    if (this.credentialSpecifierCase_ == 2) {
                        SingleFieldBuilderV3<Empty, Empty.Builder, EmptyOrBuilder> singleFieldBuilderV32 = this.googleDefaultBuilder_;
                        if (singleFieldBuilderV32 == null) {
                            channelCredentials.credentialSpecifier_ = this.credentialSpecifier_;
                        } else {
                            channelCredentials.credentialSpecifier_ = singleFieldBuilderV32.build();
                        }
                    }
                    if (this.credentialSpecifierCase_ == 3) {
                        SingleFieldBuilderV3<GoogleLocalCredentials, GoogleLocalCredentials.Builder, GoogleLocalCredentialsOrBuilder> singleFieldBuilderV33 = this.localCredentialsBuilder_;
                        if (singleFieldBuilderV33 == null) {
                            channelCredentials.credentialSpecifier_ = this.credentialSpecifier_;
                        } else {
                            channelCredentials.credentialSpecifier_ = singleFieldBuilderV33.build();
                        }
                    }
                    channelCredentials.credentialSpecifierCase_ = this.credentialSpecifierCase_;
                    onBuilt();
                    return channelCredentials;
                }

                /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m15295clone() {
                    return (Builder) super.clone();
                }

                /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m15307setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                    return (Builder) super.setField(fieldDescriptor, obj);
                }

                /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m15285clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                    return (Builder) super.clearField(fieldDescriptor);
                }

                /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m15288clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                    return (Builder) super.clearOneof(oneofDescriptor);
                }

                /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m15309setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                    return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
                }

                /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m15275addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                    return (Builder) super.addRepeatedField(fieldDescriptor, obj);
                }

                /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m15300mergeFrom(Message message) {
                    if (message instanceof ChannelCredentials) {
                        return mergeFrom((ChannelCredentials) message);
                    }
                    super.mergeFrom(message);
                    return this;
                }

                public Builder mergeFrom(ChannelCredentials channelCredentials) {
                    if (channelCredentials == ChannelCredentials.getDefaultInstance()) {
                        return this;
                    }
                    int i = AnonymousClass2.$SwitchMap$io$envoyproxy$envoy$api$v2$core$GrpcService$GoogleGrpc$ChannelCredentials$CredentialSpecifierCase[channelCredentials.getCredentialSpecifierCase().ordinal()];
                    if (i == 1) {
                        mergeSslCredentials(channelCredentials.getSslCredentials());
                    } else if (i == 2) {
                        mergeGoogleDefault(channelCredentials.getGoogleDefault());
                    } else if (i == 3) {
                        mergeLocalCredentials(channelCredentials.getLocalCredentials());
                    }
                    m15305mergeUnknownFields(channelCredentials.unknownFields);
                    onChanged();
                    return this;
                }

                /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
                /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.ChannelCredentials.Builder m15301mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
                    /*
                        r2 = this;
                        r0 = 0
                        com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.ChannelCredentials.access$3300()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                        java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                        io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService$GoogleGrpc$ChannelCredentials r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.ChannelCredentials) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                        io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService$GoogleGrpc$ChannelCredentials r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.ChannelCredentials) r4     // Catch: java.lang.Throwable -> L11
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
                    throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.ChannelCredentials.Builder.m15301mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService$GoogleGrpc$ChannelCredentials$Builder");
                }

                @Override
                // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.ChannelCredentialsOrBuilder
                public CredentialSpecifierCase getCredentialSpecifierCase() {
                    return CredentialSpecifierCase.forNumber(this.credentialSpecifierCase_);
                }

                public Builder clearCredentialSpecifier() {
                    this.credentialSpecifierCase_ = 0;
                    this.credentialSpecifier_ = null;
                    onChanged();
                    return this;
                }

                @Override
                // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.ChannelCredentialsOrBuilder
                public SslCredentials getSslCredentials() {
                    SingleFieldBuilderV3<SslCredentials, SslCredentials.Builder, SslCredentialsOrBuilder> singleFieldBuilderV3 = this.sslCredentialsBuilder_;
                    if (singleFieldBuilderV3 == null) {
                        if (this.credentialSpecifierCase_ == 1) {
                            return (SslCredentials) this.credentialSpecifier_;
                        }
                        return SslCredentials.getDefaultInstance();
                    }
                    if (this.credentialSpecifierCase_ == 1) {
                        return singleFieldBuilderV3.getMessage();
                    }
                    return SslCredentials.getDefaultInstance();
                }

                public Builder setSslCredentials(SslCredentials sslCredentials) {
                    SingleFieldBuilderV3<SslCredentials, SslCredentials.Builder, SslCredentialsOrBuilder> singleFieldBuilderV3 = this.sslCredentialsBuilder_;
                    if (singleFieldBuilderV3 == null) {
                        sslCredentials.getClass();
                        this.credentialSpecifier_ = sslCredentials;
                        onChanged();
                    } else {
                        singleFieldBuilderV3.setMessage(sslCredentials);
                    }
                    this.credentialSpecifierCase_ = 1;
                    return this;
                }

                public Builder setSslCredentials(SslCredentials.Builder builder) {
                    SingleFieldBuilderV3<SslCredentials, SslCredentials.Builder, SslCredentialsOrBuilder> singleFieldBuilderV3 = this.sslCredentialsBuilder_;
                    if (singleFieldBuilderV3 == null) {
                        this.credentialSpecifier_ = builder.m15369build();
                        onChanged();
                    } else {
                        singleFieldBuilderV3.setMessage(builder.m15369build());
                    }
                    this.credentialSpecifierCase_ = 1;
                    return this;
                }

                public Builder mergeSslCredentials(SslCredentials sslCredentials) {
                    SingleFieldBuilderV3<SslCredentials, SslCredentials.Builder, SslCredentialsOrBuilder> singleFieldBuilderV3 = this.sslCredentialsBuilder_;
                    if (singleFieldBuilderV3 == null) {
                        if (this.credentialSpecifierCase_ != 1 || this.credentialSpecifier_ == SslCredentials.getDefaultInstance()) {
                            this.credentialSpecifier_ = sslCredentials;
                        } else {
                            this.credentialSpecifier_ = SslCredentials.newBuilder((SslCredentials) this.credentialSpecifier_).mergeFrom(sslCredentials).m15371buildPartial();
                        }
                        onChanged();
                    } else {
                        if (this.credentialSpecifierCase_ == 1) {
                            singleFieldBuilderV3.mergeFrom(sslCredentials);
                        }
                        this.sslCredentialsBuilder_.setMessage(sslCredentials);
                    }
                    this.credentialSpecifierCase_ = 1;
                    return this;
                }

                public Builder clearSslCredentials() {
                    SingleFieldBuilderV3<SslCredentials, SslCredentials.Builder, SslCredentialsOrBuilder> singleFieldBuilderV3 = this.sslCredentialsBuilder_;
                    if (singleFieldBuilderV3 != null) {
                        if (this.credentialSpecifierCase_ == 1) {
                            this.credentialSpecifierCase_ = 0;
                            this.credentialSpecifier_ = null;
                        }
                        singleFieldBuilderV3.clear();
                    } else if (this.credentialSpecifierCase_ == 1) {
                        this.credentialSpecifierCase_ = 0;
                        this.credentialSpecifier_ = null;
                        onChanged();
                    }
                    return this;
                }

                public SslCredentials.Builder getSslCredentialsBuilder() {
                    return getSslCredentialsFieldBuilder().getBuilder();
                }

                @Override
                // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.ChannelCredentialsOrBuilder
                public SslCredentialsOrBuilder getSslCredentialsOrBuilder() {
                    SingleFieldBuilderV3<SslCredentials, SslCredentials.Builder, SslCredentialsOrBuilder> singleFieldBuilderV3;
                    int i = this.credentialSpecifierCase_;
                    if (i == 1 && (singleFieldBuilderV3 = this.sslCredentialsBuilder_) != null) {
                        return (SslCredentialsOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
                    }
                    if (i == 1) {
                        return (SslCredentials) this.credentialSpecifier_;
                    }
                    return SslCredentials.getDefaultInstance();
                }

                private SingleFieldBuilderV3<SslCredentials, SslCredentials.Builder, SslCredentialsOrBuilder> getSslCredentialsFieldBuilder() {
                    if (this.sslCredentialsBuilder_ == null) {
                        if (this.credentialSpecifierCase_ != 1) {
                            this.credentialSpecifier_ = SslCredentials.getDefaultInstance();
                        }
                        this.sslCredentialsBuilder_ = new SingleFieldBuilderV3<>((SslCredentials) this.credentialSpecifier_, getParentForChildren(), isClean());
                        this.credentialSpecifier_ = null;
                    }
                    this.credentialSpecifierCase_ = 1;
                    onChanged();
                    return this.sslCredentialsBuilder_;
                }

                @Override
                // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.ChannelCredentialsOrBuilder
                public Empty getGoogleDefault() {
                    SingleFieldBuilderV3<Empty, Empty.Builder, EmptyOrBuilder> singleFieldBuilderV3 = this.googleDefaultBuilder_;
                    if (singleFieldBuilderV3 == null) {
                        if (this.credentialSpecifierCase_ == 2) {
                            return (Empty) this.credentialSpecifier_;
                        }
                        return Empty.getDefaultInstance();
                    }
                    if (this.credentialSpecifierCase_ == 2) {
                        return singleFieldBuilderV3.getMessage();
                    }
                    return Empty.getDefaultInstance();
                }

                public Builder setGoogleDefault(Empty empty) {
                    SingleFieldBuilderV3<Empty, Empty.Builder, EmptyOrBuilder> singleFieldBuilderV3 = this.googleDefaultBuilder_;
                    if (singleFieldBuilderV3 == null) {
                        empty.getClass();
                        this.credentialSpecifier_ = empty;
                        onChanged();
                    } else {
                        singleFieldBuilderV3.setMessage(empty);
                    }
                    this.credentialSpecifierCase_ = 2;
                    return this;
                }

                public Builder setGoogleDefault(Empty.Builder builder) {
                    SingleFieldBuilderV3<Empty, Empty.Builder, EmptyOrBuilder> singleFieldBuilderV3 = this.googleDefaultBuilder_;
                    if (singleFieldBuilderV3 == null) {
                        this.credentialSpecifier_ = builder.build();
                        onChanged();
                    } else {
                        singleFieldBuilderV3.setMessage(builder.build());
                    }
                    this.credentialSpecifierCase_ = 2;
                    return this;
                }

                public Builder mergeGoogleDefault(Empty empty) {
                    SingleFieldBuilderV3<Empty, Empty.Builder, EmptyOrBuilder> singleFieldBuilderV3 = this.googleDefaultBuilder_;
                    if (singleFieldBuilderV3 == null) {
                        if (this.credentialSpecifierCase_ != 2 || this.credentialSpecifier_ == Empty.getDefaultInstance()) {
                            this.credentialSpecifier_ = empty;
                        } else {
                            this.credentialSpecifier_ = Empty.newBuilder((Empty) this.credentialSpecifier_).mergeFrom(empty).buildPartial();
                        }
                        onChanged();
                    } else {
                        if (this.credentialSpecifierCase_ == 2) {
                            singleFieldBuilderV3.mergeFrom(empty);
                        }
                        this.googleDefaultBuilder_.setMessage(empty);
                    }
                    this.credentialSpecifierCase_ = 2;
                    return this;
                }

                public Builder clearGoogleDefault() {
                    SingleFieldBuilderV3<Empty, Empty.Builder, EmptyOrBuilder> singleFieldBuilderV3 = this.googleDefaultBuilder_;
                    if (singleFieldBuilderV3 != null) {
                        if (this.credentialSpecifierCase_ == 2) {
                            this.credentialSpecifierCase_ = 0;
                            this.credentialSpecifier_ = null;
                        }
                        singleFieldBuilderV3.clear();
                    } else if (this.credentialSpecifierCase_ == 2) {
                        this.credentialSpecifierCase_ = 0;
                        this.credentialSpecifier_ = null;
                        onChanged();
                    }
                    return this;
                }

                public Empty.Builder getGoogleDefaultBuilder() {
                    return getGoogleDefaultFieldBuilder().getBuilder();
                }

                @Override
                // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.ChannelCredentialsOrBuilder
                public EmptyOrBuilder getGoogleDefaultOrBuilder() {
                    SingleFieldBuilderV3<Empty, Empty.Builder, EmptyOrBuilder> singleFieldBuilderV3;
                    int i = this.credentialSpecifierCase_;
                    if (i == 2 && (singleFieldBuilderV3 = this.googleDefaultBuilder_) != null) {
                        return singleFieldBuilderV3.getMessageOrBuilder();
                    }
                    if (i == 2) {
                        return (Empty) this.credentialSpecifier_;
                    }
                    return Empty.getDefaultInstance();
                }

                private SingleFieldBuilderV3<Empty, Empty.Builder, EmptyOrBuilder> getGoogleDefaultFieldBuilder() {
                    if (this.googleDefaultBuilder_ == null) {
                        if (this.credentialSpecifierCase_ != 2) {
                            this.credentialSpecifier_ = Empty.getDefaultInstance();
                        }
                        this.googleDefaultBuilder_ = new SingleFieldBuilderV3<>((Empty) this.credentialSpecifier_, getParentForChildren(), isClean());
                        this.credentialSpecifier_ = null;
                    }
                    this.credentialSpecifierCase_ = 2;
                    onChanged();
                    return this.googleDefaultBuilder_;
                }

                @Override
                // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.ChannelCredentialsOrBuilder
                public GoogleLocalCredentials getLocalCredentials() {
                    SingleFieldBuilderV3<GoogleLocalCredentials, GoogleLocalCredentials.Builder, GoogleLocalCredentialsOrBuilder> singleFieldBuilderV3 = this.localCredentialsBuilder_;
                    if (singleFieldBuilderV3 == null) {
                        if (this.credentialSpecifierCase_ == 3) {
                            return (GoogleLocalCredentials) this.credentialSpecifier_;
                        }
                        return GoogleLocalCredentials.getDefaultInstance();
                    }
                    if (this.credentialSpecifierCase_ == 3) {
                        return singleFieldBuilderV3.getMessage();
                    }
                    return GoogleLocalCredentials.getDefaultInstance();
                }

                public Builder setLocalCredentials(GoogleLocalCredentials googleLocalCredentials) {
                    SingleFieldBuilderV3<GoogleLocalCredentials, GoogleLocalCredentials.Builder, GoogleLocalCredentialsOrBuilder> singleFieldBuilderV3 = this.localCredentialsBuilder_;
                    if (singleFieldBuilderV3 == null) {
                        googleLocalCredentials.getClass();
                        this.credentialSpecifier_ = googleLocalCredentials;
                        onChanged();
                    } else {
                        singleFieldBuilderV3.setMessage(googleLocalCredentials);
                    }
                    this.credentialSpecifierCase_ = 3;
                    return this;
                }

                public Builder setLocalCredentials(GoogleLocalCredentials.Builder builder) {
                    SingleFieldBuilderV3<GoogleLocalCredentials, GoogleLocalCredentials.Builder, GoogleLocalCredentialsOrBuilder> singleFieldBuilderV3 = this.localCredentialsBuilder_;
                    if (singleFieldBuilderV3 == null) {
                        this.credentialSpecifier_ = builder.m15323build();
                        onChanged();
                    } else {
                        singleFieldBuilderV3.setMessage(builder.m15323build());
                    }
                    this.credentialSpecifierCase_ = 3;
                    return this;
                }

                public Builder mergeLocalCredentials(GoogleLocalCredentials googleLocalCredentials) {
                    SingleFieldBuilderV3<GoogleLocalCredentials, GoogleLocalCredentials.Builder, GoogleLocalCredentialsOrBuilder> singleFieldBuilderV3 = this.localCredentialsBuilder_;
                    if (singleFieldBuilderV3 == null) {
                        if (this.credentialSpecifierCase_ != 3 || this.credentialSpecifier_ == GoogleLocalCredentials.getDefaultInstance()) {
                            this.credentialSpecifier_ = googleLocalCredentials;
                        } else {
                            this.credentialSpecifier_ = GoogleLocalCredentials.newBuilder((GoogleLocalCredentials) this.credentialSpecifier_).mergeFrom(googleLocalCredentials).m15325buildPartial();
                        }
                        onChanged();
                    } else {
                        if (this.credentialSpecifierCase_ == 3) {
                            singleFieldBuilderV3.mergeFrom(googleLocalCredentials);
                        }
                        this.localCredentialsBuilder_.setMessage(googleLocalCredentials);
                    }
                    this.credentialSpecifierCase_ = 3;
                    return this;
                }

                public Builder clearLocalCredentials() {
                    SingleFieldBuilderV3<GoogleLocalCredentials, GoogleLocalCredentials.Builder, GoogleLocalCredentialsOrBuilder> singleFieldBuilderV3 = this.localCredentialsBuilder_;
                    if (singleFieldBuilderV3 != null) {
                        if (this.credentialSpecifierCase_ == 3) {
                            this.credentialSpecifierCase_ = 0;
                            this.credentialSpecifier_ = null;
                        }
                        singleFieldBuilderV3.clear();
                    } else if (this.credentialSpecifierCase_ == 3) {
                        this.credentialSpecifierCase_ = 0;
                        this.credentialSpecifier_ = null;
                        onChanged();
                    }
                    return this;
                }

                public GoogleLocalCredentials.Builder getLocalCredentialsBuilder() {
                    return getLocalCredentialsFieldBuilder().getBuilder();
                }

                @Override
                // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.ChannelCredentialsOrBuilder
                public GoogleLocalCredentialsOrBuilder getLocalCredentialsOrBuilder() {
                    SingleFieldBuilderV3<GoogleLocalCredentials, GoogleLocalCredentials.Builder, GoogleLocalCredentialsOrBuilder> singleFieldBuilderV3;
                    int i = this.credentialSpecifierCase_;
                    if (i == 3 && (singleFieldBuilderV3 = this.localCredentialsBuilder_) != null) {
                        return (GoogleLocalCredentialsOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
                    }
                    if (i == 3) {
                        return (GoogleLocalCredentials) this.credentialSpecifier_;
                    }
                    return GoogleLocalCredentials.getDefaultInstance();
                }

                private SingleFieldBuilderV3<GoogleLocalCredentials, GoogleLocalCredentials.Builder, GoogleLocalCredentialsOrBuilder> getLocalCredentialsFieldBuilder() {
                    if (this.localCredentialsBuilder_ == null) {
                        if (this.credentialSpecifierCase_ != 3) {
                            this.credentialSpecifier_ = GoogleLocalCredentials.getDefaultInstance();
                        }
                        this.localCredentialsBuilder_ = new SingleFieldBuilderV3<>((GoogleLocalCredentials) this.credentialSpecifier_, getParentForChildren(), isClean());
                        this.credentialSpecifier_ = null;
                    }
                    this.credentialSpecifierCase_ = 3;
                    onChanged();
                    return this.localCredentialsBuilder_;
                }

                /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public final Builder m15311setUnknownFields(UnknownFieldSet unknownFieldSet) {
                    return (Builder) super.setUnknownFields(unknownFieldSet);
                }

                /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public final Builder m15305mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                    return (Builder) super.mergeUnknownFields(unknownFieldSet);
                }
            }
        }

        public static final class CallCredentials extends GeneratedMessageV3 implements CallCredentialsOrBuilder {
            public static final int ACCESS_TOKEN_FIELD_NUMBER = 1;
            public static final int FROM_PLUGIN_FIELD_NUMBER = 6;
            public static final int GOOGLE_COMPUTE_ENGINE_FIELD_NUMBER = 2;
            public static final int GOOGLE_IAM_FIELD_NUMBER = 5;
            public static final int GOOGLE_REFRESH_TOKEN_FIELD_NUMBER = 3;
            public static final int SERVICE_ACCOUNT_JWT_ACCESS_FIELD_NUMBER = 4;
            public static final int STS_SERVICE_FIELD_NUMBER = 7;
            private static final long serialVersionUID = 0;
            private static final CallCredentials DEFAULT_INSTANCE = new CallCredentials();
            private static final Parser<CallCredentials> PARSER = new AbstractParser<CallCredentials>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentials.1
                /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
                public CallCredentials m15044parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                    return new CallCredentials(codedInputStream, extensionRegistryLite);
                }
            };
            private int credentialSpecifierCase_;
            private Object credentialSpecifier_;
            private byte memoizedIsInitialized;

            private CallCredentials(GeneratedMessageV3.Builder<?> builder) {
                super(builder);
                this.credentialSpecifierCase_ = 0;
                this.memoizedIsInitialized = (byte) -1;
            }

            private CallCredentials() {
                this.credentialSpecifierCase_ = 0;
                this.memoizedIsInitialized = (byte) -1;
            }

            private CallCredentials(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                Empty.Builder builderM15226toBuilder;
                this();
                extensionRegistryLite.getClass();
                UnknownFieldSet.Builder builderNewBuilder = UnknownFieldSet.newBuilder();
                boolean z = false;
                while (!z) {
                    try {
                        try {
                            int tag = codedInputStream.readTag();
                            if (tag != 0) {
                                if (tag != 10) {
                                    if (tag == 18) {
                                        builderM15226toBuilder = this.credentialSpecifierCase_ == 2 ? ((Empty) this.credentialSpecifier_).toBuilder() : null;
                                        Empty message = codedInputStream.readMessage(Empty.parser(), extensionRegistryLite);
                                        this.credentialSpecifier_ = message;
                                        if (builderM15226toBuilder != null) {
                                            builderM15226toBuilder.mergeFrom(message);
                                            this.credentialSpecifier_ = builderM15226toBuilder.buildPartial();
                                        }
                                        this.credentialSpecifierCase_ = 2;
                                    } else if (tag == 26) {
                                        String stringRequireUtf8 = codedInputStream.readStringRequireUtf8();
                                        this.credentialSpecifierCase_ = 3;
                                        this.credentialSpecifier_ = stringRequireUtf8;
                                    } else if (tag == 34) {
                                        builderM15226toBuilder = this.credentialSpecifierCase_ == 4 ? ((ServiceAccountJWTAccessCredentials) this.credentialSpecifier_).m15180toBuilder() : null;
                                        ServiceAccountJWTAccessCredentials message2 = codedInputStream.readMessage(ServiceAccountJWTAccessCredentials.parser(), extensionRegistryLite);
                                        this.credentialSpecifier_ = message2;
                                        if (builderM15226toBuilder != null) {
                                            builderM15226toBuilder.mergeFrom(message2);
                                            this.credentialSpecifier_ = builderM15226toBuilder.m15187buildPartial();
                                        }
                                        this.credentialSpecifierCase_ = 4;
                                    } else if (tag == 42) {
                                        builderM15226toBuilder = this.credentialSpecifierCase_ == 5 ? ((GoogleIAMCredentials) this.credentialSpecifier_).m15088toBuilder() : null;
                                        GoogleIAMCredentials message3 = codedInputStream.readMessage(GoogleIAMCredentials.parser(), extensionRegistryLite);
                                        this.credentialSpecifier_ = message3;
                                        if (builderM15226toBuilder != null) {
                                            builderM15226toBuilder.mergeFrom(message3);
                                            this.credentialSpecifier_ = builderM15226toBuilder.m15095buildPartial();
                                        }
                                        this.credentialSpecifierCase_ = 5;
                                    } else if (tag == 50) {
                                        builderM15226toBuilder = this.credentialSpecifierCase_ == 6 ? ((MetadataCredentialsFromPlugin) this.credentialSpecifier_).m15134toBuilder() : null;
                                        MetadataCredentialsFromPlugin message4 = codedInputStream.readMessage(MetadataCredentialsFromPlugin.parser(), extensionRegistryLite);
                                        this.credentialSpecifier_ = message4;
                                        if (builderM15226toBuilder != null) {
                                            builderM15226toBuilder.mergeFrom(message4);
                                            this.credentialSpecifier_ = builderM15226toBuilder.m15141buildPartial();
                                        }
                                        this.credentialSpecifierCase_ = 6;
                                    } else if (tag == 58) {
                                        builderM15226toBuilder = this.credentialSpecifierCase_ == 7 ? ((StsService) this.credentialSpecifier_).m15226toBuilder() : null;
                                        StsService message5 = codedInputStream.readMessage(StsService.parser(), extensionRegistryLite);
                                        this.credentialSpecifier_ = message5;
                                        if (builderM15226toBuilder != null) {
                                            builderM15226toBuilder.mergeFrom(message5);
                                            this.credentialSpecifier_ = builderM15226toBuilder.m15233buildPartial();
                                        }
                                        this.credentialSpecifierCase_ = 7;
                                    } else if (!parseUnknownField(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                                    }
                                } else {
                                    String stringRequireUtf82 = codedInputStream.readStringRequireUtf8();
                                    this.credentialSpecifierCase_ = 1;
                                    this.credentialSpecifier_ = stringRequireUtf82;
                                }
                            }
                            z = true;
                        } catch (IOException e) {
                            throw new InvalidProtocolBufferException(e).setUnfinishedMessage(this);
                        } catch (InvalidProtocolBufferException e2) {
                            throw e2.setUnfinishedMessage(this);
                        }
                    } finally {
                        this.unknownFields = builderNewBuilder.build();
                        makeExtensionsImmutable();
                    }
                }
            }

            public static CallCredentials getDefaultInstance() {
                return DEFAULT_INSTANCE;
            }

            public static Parser<CallCredentials> parser() {
                return PARSER;
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return GrpcServiceProto.internal_static_envoy_api_v2_core_GrpcService_GoogleGrpc_CallCredentials_descriptor;
            }

            public static CallCredentials parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
                return (CallCredentials) PARSER.parseFrom(byteBuffer);
            }

            public static CallCredentials parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return (CallCredentials) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
            }

            public static CallCredentials parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
                return (CallCredentials) PARSER.parseFrom(byteString);
            }

            public static CallCredentials parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return (CallCredentials) PARSER.parseFrom(byteString, extensionRegistryLite);
            }

            public static CallCredentials parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
                return (CallCredentials) PARSER.parseFrom(bArr);
            }

            public static CallCredentials parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                return (CallCredentials) PARSER.parseFrom(bArr, extensionRegistryLite);
            }

            public static CallCredentials parseFrom(InputStream inputStream) throws IOException {
                return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
            }

            public static CallCredentials parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
            }

            public static CallCredentials parseDelimitedFrom(InputStream inputStream) throws IOException {
                return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
            }

            public static CallCredentials parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
            }

            public static CallCredentials parseFrom(CodedInputStream codedInputStream) throws IOException {
                return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
            }

            public static CallCredentials parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
            }

            public static Builder newBuilder() {
                return DEFAULT_INSTANCE.m15042toBuilder();
            }

            public static Builder newBuilder(CallCredentials callCredentials) {
                return DEFAULT_INSTANCE.m15042toBuilder().mergeFrom(callCredentials);
            }

            /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public CallCredentials m15037getDefaultInstanceForType() {
                return DEFAULT_INSTANCE;
            }

            public Parser<CallCredentials> getParserForType() {
                return PARSER;
            }

            @Override
            // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentialsOrBuilder
            public boolean hasFromPlugin() {
                return this.credentialSpecifierCase_ == 6;
            }

            @Override
            // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentialsOrBuilder
            public boolean hasGoogleComputeEngine() {
                return this.credentialSpecifierCase_ == 2;
            }

            @Override
            // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentialsOrBuilder
            public boolean hasGoogleIam() {
                return this.credentialSpecifierCase_ == 5;
            }

            @Override
            // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentialsOrBuilder
            public boolean hasServiceAccountJwtAccess() {
                return this.credentialSpecifierCase_ == 4;
            }

            @Override
            // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentialsOrBuilder
            public boolean hasStsService() {
                return this.credentialSpecifierCase_ == 7;
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
                return new CallCredentials();
            }

            public final UnknownFieldSet getUnknownFields() {
                return this.unknownFields;
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return GrpcServiceProto.internal_static_envoy_api_v2_core_GrpcService_GoogleGrpc_CallCredentials_fieldAccessorTable.ensureFieldAccessorsInitialized(CallCredentials.class, Builder.class);
            }

            @Override
            // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentialsOrBuilder
            public CredentialSpecifierCase getCredentialSpecifierCase() {
                return CredentialSpecifierCase.forNumber(this.credentialSpecifierCase_);
            }

            @Override
            // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentialsOrBuilder
            public String getAccessToken() {
                String str = this.credentialSpecifierCase_ == 1 ? this.credentialSpecifier_ : "";
                if (str instanceof String) {
                    return (String) str;
                }
                String stringUtf8 = ((ByteString) str).toStringUtf8();
                if (this.credentialSpecifierCase_ == 1) {
                    this.credentialSpecifier_ = stringUtf8;
                }
                return stringUtf8;
            }

            @Override
            // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentialsOrBuilder
            public ByteString getAccessTokenBytes() {
                String str = this.credentialSpecifierCase_ == 1 ? this.credentialSpecifier_ : "";
                if (str instanceof String) {
                    ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) str);
                    if (this.credentialSpecifierCase_ == 1) {
                        this.credentialSpecifier_ = byteStringCopyFromUtf8;
                    }
                    return byteStringCopyFromUtf8;
                }
                return (ByteString) str;
            }

            @Override
            // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentialsOrBuilder
            public Empty getGoogleComputeEngine() {
                if (this.credentialSpecifierCase_ == 2) {
                    return (Empty) this.credentialSpecifier_;
                }
                return Empty.getDefaultInstance();
            }

            @Override
            // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentialsOrBuilder
            public EmptyOrBuilder getGoogleComputeEngineOrBuilder() {
                if (this.credentialSpecifierCase_ == 2) {
                    return (Empty) this.credentialSpecifier_;
                }
                return Empty.getDefaultInstance();
            }

            @Override
            // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentialsOrBuilder
            public String getGoogleRefreshToken() {
                String str = this.credentialSpecifierCase_ == 3 ? this.credentialSpecifier_ : "";
                if (str instanceof String) {
                    return (String) str;
                }
                String stringUtf8 = ((ByteString) str).toStringUtf8();
                if (this.credentialSpecifierCase_ == 3) {
                    this.credentialSpecifier_ = stringUtf8;
                }
                return stringUtf8;
            }

            @Override
            // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentialsOrBuilder
            public ByteString getGoogleRefreshTokenBytes() {
                String str = this.credentialSpecifierCase_ == 3 ? this.credentialSpecifier_ : "";
                if (str instanceof String) {
                    ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) str);
                    if (this.credentialSpecifierCase_ == 3) {
                        this.credentialSpecifier_ = byteStringCopyFromUtf8;
                    }
                    return byteStringCopyFromUtf8;
                }
                return (ByteString) str;
            }

            @Override
            // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentialsOrBuilder
            public ServiceAccountJWTAccessCredentials getServiceAccountJwtAccess() {
                if (this.credentialSpecifierCase_ == 4) {
                    return (ServiceAccountJWTAccessCredentials) this.credentialSpecifier_;
                }
                return ServiceAccountJWTAccessCredentials.getDefaultInstance();
            }

            @Override
            // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentialsOrBuilder
            public ServiceAccountJWTAccessCredentialsOrBuilder getServiceAccountJwtAccessOrBuilder() {
                if (this.credentialSpecifierCase_ == 4) {
                    return (ServiceAccountJWTAccessCredentials) this.credentialSpecifier_;
                }
                return ServiceAccountJWTAccessCredentials.getDefaultInstance();
            }

            @Override
            // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentialsOrBuilder
            public GoogleIAMCredentials getGoogleIam() {
                if (this.credentialSpecifierCase_ == 5) {
                    return (GoogleIAMCredentials) this.credentialSpecifier_;
                }
                return GoogleIAMCredentials.getDefaultInstance();
            }

            @Override
            // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentialsOrBuilder
            public GoogleIAMCredentialsOrBuilder getGoogleIamOrBuilder() {
                if (this.credentialSpecifierCase_ == 5) {
                    return (GoogleIAMCredentials) this.credentialSpecifier_;
                }
                return GoogleIAMCredentials.getDefaultInstance();
            }

            @Override
            // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentialsOrBuilder
            public MetadataCredentialsFromPlugin getFromPlugin() {
                if (this.credentialSpecifierCase_ == 6) {
                    return (MetadataCredentialsFromPlugin) this.credentialSpecifier_;
                }
                return MetadataCredentialsFromPlugin.getDefaultInstance();
            }

            @Override
            // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentialsOrBuilder
            public MetadataCredentialsFromPluginOrBuilder getFromPluginOrBuilder() {
                if (this.credentialSpecifierCase_ == 6) {
                    return (MetadataCredentialsFromPlugin) this.credentialSpecifier_;
                }
                return MetadataCredentialsFromPlugin.getDefaultInstance();
            }

            @Override
            // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentialsOrBuilder
            public StsService getStsService() {
                if (this.credentialSpecifierCase_ == 7) {
                    return (StsService) this.credentialSpecifier_;
                }
                return StsService.getDefaultInstance();
            }

            @Override
            // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentialsOrBuilder
            public StsServiceOrBuilder getStsServiceOrBuilder() {
                if (this.credentialSpecifierCase_ == 7) {
                    return (StsService) this.credentialSpecifier_;
                }
                return StsService.getDefaultInstance();
            }

            public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
                if (this.credentialSpecifierCase_ == 1) {
                    GeneratedMessageV3.writeString(codedOutputStream, 1, this.credentialSpecifier_);
                }
                if (this.credentialSpecifierCase_ == 2) {
                    codedOutputStream.writeMessage(2, (Empty) this.credentialSpecifier_);
                }
                if (this.credentialSpecifierCase_ == 3) {
                    GeneratedMessageV3.writeString(codedOutputStream, 3, this.credentialSpecifier_);
                }
                if (this.credentialSpecifierCase_ == 4) {
                    codedOutputStream.writeMessage(4, (ServiceAccountJWTAccessCredentials) this.credentialSpecifier_);
                }
                if (this.credentialSpecifierCase_ == 5) {
                    codedOutputStream.writeMessage(5, (GoogleIAMCredentials) this.credentialSpecifier_);
                }
                if (this.credentialSpecifierCase_ == 6) {
                    codedOutputStream.writeMessage(6, (MetadataCredentialsFromPlugin) this.credentialSpecifier_);
                }
                if (this.credentialSpecifierCase_ == 7) {
                    codedOutputStream.writeMessage(7, (StsService) this.credentialSpecifier_);
                }
                this.unknownFields.writeTo(codedOutputStream);
            }

            public int getSerializedSize() {
                int i = this.memoizedSize;
                if (i != -1) {
                    return i;
                }
                int iComputeStringSize = this.credentialSpecifierCase_ == 1 ? GeneratedMessageV3.computeStringSize(1, this.credentialSpecifier_) : 0;
                if (this.credentialSpecifierCase_ == 2) {
                    iComputeStringSize += CodedOutputStream.computeMessageSize(2, (Empty) this.credentialSpecifier_);
                }
                if (this.credentialSpecifierCase_ == 3) {
                    iComputeStringSize += GeneratedMessageV3.computeStringSize(3, this.credentialSpecifier_);
                }
                if (this.credentialSpecifierCase_ == 4) {
                    iComputeStringSize += CodedOutputStream.computeMessageSize(4, (ServiceAccountJWTAccessCredentials) this.credentialSpecifier_);
                }
                if (this.credentialSpecifierCase_ == 5) {
                    iComputeStringSize += CodedOutputStream.computeMessageSize(5, (GoogleIAMCredentials) this.credentialSpecifier_);
                }
                if (this.credentialSpecifierCase_ == 6) {
                    iComputeStringSize += CodedOutputStream.computeMessageSize(6, (MetadataCredentialsFromPlugin) this.credentialSpecifier_);
                }
                if (this.credentialSpecifierCase_ == 7) {
                    iComputeStringSize += CodedOutputStream.computeMessageSize(7, (StsService) this.credentialSpecifier_);
                }
                int serializedSize = iComputeStringSize + this.unknownFields.getSerializedSize();
                this.memoizedSize = serializedSize;
                return serializedSize;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof CallCredentials)) {
                    return super.equals(obj);
                }
                CallCredentials callCredentials = (CallCredentials) obj;
                if (!getCredentialSpecifierCase().equals(callCredentials.getCredentialSpecifierCase())) {
                    return false;
                }
                switch (this.credentialSpecifierCase_) {
                    case 1:
                        if (!getAccessToken().equals(callCredentials.getAccessToken())) {
                            return false;
                        }
                        break;
                    case 2:
                        if (!getGoogleComputeEngine().equals(callCredentials.getGoogleComputeEngine())) {
                            return false;
                        }
                        break;
                    case 3:
                        if (!getGoogleRefreshToken().equals(callCredentials.getGoogleRefreshToken())) {
                            return false;
                        }
                        break;
                    case 4:
                        if (!getServiceAccountJwtAccess().equals(callCredentials.getServiceAccountJwtAccess())) {
                            return false;
                        }
                        break;
                    case 5:
                        if (!getGoogleIam().equals(callCredentials.getGoogleIam())) {
                            return false;
                        }
                        break;
                    case 6:
                        if (!getFromPlugin().equals(callCredentials.getFromPlugin())) {
                            return false;
                        }
                        break;
                    case 7:
                        if (!getStsService().equals(callCredentials.getStsService())) {
                            return false;
                        }
                        break;
                }
                return this.unknownFields.equals(callCredentials.unknownFields);
            }

            public int hashCode() {
                int i;
                int iHashCode;
                if (this.memoizedHashCode != 0) {
                    return this.memoizedHashCode;
                }
                int iHashCode2 = 779 + getDescriptor().hashCode();
                switch (this.credentialSpecifierCase_) {
                    case 1:
                        i = ((iHashCode2 * 37) + 1) * 53;
                        iHashCode = getAccessToken().hashCode();
                        break;
                    case 2:
                        i = ((iHashCode2 * 37) + 2) * 53;
                        iHashCode = getGoogleComputeEngine().hashCode();
                        break;
                    case 3:
                        i = ((iHashCode2 * 37) + 3) * 53;
                        iHashCode = getGoogleRefreshToken().hashCode();
                        break;
                    case 4:
                        i = ((iHashCode2 * 37) + 4) * 53;
                        iHashCode = getServiceAccountJwtAccess().hashCode();
                        break;
                    case 5:
                        i = ((iHashCode2 * 37) + 5) * 53;
                        iHashCode = getGoogleIam().hashCode();
                        break;
                    case 6:
                        i = ((iHashCode2 * 37) + 6) * 53;
                        iHashCode = getFromPlugin().hashCode();
                        break;
                    case 7:
                        i = ((iHashCode2 * 37) + 7) * 53;
                        iHashCode = getStsService().hashCode();
                        break;
                    default:
                        int iHashCode3 = (iHashCode2 * 29) + this.unknownFields.hashCode();
                        this.memoizedHashCode = iHashCode3;
                        return iHashCode3;
                }
                iHashCode2 = i + iHashCode;
                int iHashCode32 = (iHashCode2 * 29) + this.unknownFields.hashCode();
                this.memoizedHashCode = iHashCode32;
                return iHashCode32;
            }

            /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m15039newBuilderForType() {
                return newBuilder();
            }

            /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m15042toBuilder() {
                return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
                return new Builder(builderParent);
            }

            public enum CredentialSpecifierCase implements Internal.EnumLite, AbstractMessageLite.InternalOneOfEnum {
                ACCESS_TOKEN(1),
                GOOGLE_COMPUTE_ENGINE(2),
                GOOGLE_REFRESH_TOKEN(3),
                SERVICE_ACCOUNT_JWT_ACCESS(4),
                GOOGLE_IAM(5),
                FROM_PLUGIN(6),
                STS_SERVICE(7),
                CREDENTIALSPECIFIER_NOT_SET(0);

                private final int value;

                CredentialSpecifierCase(int i) {
                    this.value = i;
                }

                public static CredentialSpecifierCase forNumber(int i) {
                    switch (i) {
                        case 0:
                            return CREDENTIALSPECIFIER_NOT_SET;
                        case 1:
                            return ACCESS_TOKEN;
                        case 2:
                            return GOOGLE_COMPUTE_ENGINE;
                        case 3:
                            return GOOGLE_REFRESH_TOKEN;
                        case 4:
                            return SERVICE_ACCOUNT_JWT_ACCESS;
                        case 5:
                            return GOOGLE_IAM;
                        case 6:
                            return FROM_PLUGIN;
                        case 7:
                            return STS_SERVICE;
                        default:
                            return null;
                    }
                }

                @Deprecated
                public static CredentialSpecifierCase valueOf(int i) {
                    return forNumber(i);
                }

                public int getNumber() {
                    return this.value;
                }
            }

            public interface GoogleIAMCredentialsOrBuilder extends MessageOrBuilder {
                String getAuthoritySelector();

                ByteString getAuthoritySelectorBytes();

                String getAuthorizationToken();

                ByteString getAuthorizationTokenBytes();
            }

            public interface MetadataCredentialsFromPluginOrBuilder extends MessageOrBuilder {
                @Deprecated
                Struct getConfig();

                @Deprecated
                StructOrBuilder getConfigOrBuilder();

                MetadataCredentialsFromPlugin.ConfigTypeCase getConfigTypeCase();

                String getName();

                ByteString getNameBytes();

                Any getTypedConfig();

                AnyOrBuilder getTypedConfigOrBuilder();

                @Deprecated
                boolean hasConfig();

                boolean hasTypedConfig();
            }

            public interface ServiceAccountJWTAccessCredentialsOrBuilder extends MessageOrBuilder {
                String getJsonKey();

                ByteString getJsonKeyBytes();

                long getTokenLifetimeSeconds();
            }

            public interface StsServiceOrBuilder extends MessageOrBuilder {
                String getActorTokenPath();

                ByteString getActorTokenPathBytes();

                String getActorTokenType();

                ByteString getActorTokenTypeBytes();

                String getAudience();

                ByteString getAudienceBytes();

                String getRequestedTokenType();

                ByteString getRequestedTokenTypeBytes();

                String getResource();

                ByteString getResourceBytes();

                String getScope();

                ByteString getScopeBytes();

                String getSubjectTokenPath();

                ByteString getSubjectTokenPathBytes();

                String getSubjectTokenType();

                ByteString getSubjectTokenTypeBytes();

                String getTokenExchangeServiceUri();

                ByteString getTokenExchangeServiceUriBytes();
            }

            public static final class ServiceAccountJWTAccessCredentials extends GeneratedMessageV3 implements ServiceAccountJWTAccessCredentialsOrBuilder {
                public static final int JSON_KEY_FIELD_NUMBER = 1;
                public static final int TOKEN_LIFETIME_SECONDS_FIELD_NUMBER = 2;
                private static final long serialVersionUID = 0;
                private static final ServiceAccountJWTAccessCredentials DEFAULT_INSTANCE = new ServiceAccountJWTAccessCredentials();
                private static final Parser<ServiceAccountJWTAccessCredentials> PARSER = new AbstractParser<ServiceAccountJWTAccessCredentials>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentials.ServiceAccountJWTAccessCredentials.1
                    /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
                    public ServiceAccountJWTAccessCredentials m15182parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                        return new ServiceAccountJWTAccessCredentials(codedInputStream, extensionRegistryLite);
                    }
                };
                private volatile Object jsonKey_;
                private byte memoizedIsInitialized;
                private long tokenLifetimeSeconds_;

                private ServiceAccountJWTAccessCredentials(GeneratedMessageV3.Builder<?> builder) {
                    super(builder);
                    this.memoizedIsInitialized = (byte) -1;
                }

                private ServiceAccountJWTAccessCredentials() {
                    this.memoizedIsInitialized = (byte) -1;
                    this.jsonKey_ = "";
                }

                private ServiceAccountJWTAccessCredentials(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                    this();
                    extensionRegistryLite.getClass();
                    UnknownFieldSet.Builder builderNewBuilder = UnknownFieldSet.newBuilder();
                    boolean z = false;
                    while (!z) {
                        try {
                            try {
                                try {
                                    int tag = codedInputStream.readTag();
                                    if (tag != 0) {
                                        if (tag == 10) {
                                            this.jsonKey_ = codedInputStream.readStringRequireUtf8();
                                        } else if (tag == 16) {
                                            this.tokenLifetimeSeconds_ = codedInputStream.readUInt64();
                                        } else if (!parseUnknownField(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                                        }
                                    }
                                    z = true;
                                } catch (IOException e) {
                                    throw new InvalidProtocolBufferException(e).setUnfinishedMessage(this);
                                }
                            } catch (InvalidProtocolBufferException e2) {
                                throw e2.setUnfinishedMessage(this);
                            }
                        } finally {
                            this.unknownFields = builderNewBuilder.build();
                            makeExtensionsImmutable();
                        }
                    }
                }

                public static ServiceAccountJWTAccessCredentials getDefaultInstance() {
                    return DEFAULT_INSTANCE;
                }

                public static Parser<ServiceAccountJWTAccessCredentials> parser() {
                    return PARSER;
                }

                public static final Descriptors.Descriptor getDescriptor() {
                    return GrpcServiceProto.internal_static_envoy_api_v2_core_GrpcService_GoogleGrpc_CallCredentials_ServiceAccountJWTAccessCredentials_descriptor;
                }

                public static ServiceAccountJWTAccessCredentials parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
                    return (ServiceAccountJWTAccessCredentials) PARSER.parseFrom(byteBuffer);
                }

                public static ServiceAccountJWTAccessCredentials parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                    return (ServiceAccountJWTAccessCredentials) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
                }

                public static ServiceAccountJWTAccessCredentials parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
                    return (ServiceAccountJWTAccessCredentials) PARSER.parseFrom(byteString);
                }

                public static ServiceAccountJWTAccessCredentials parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                    return (ServiceAccountJWTAccessCredentials) PARSER.parseFrom(byteString, extensionRegistryLite);
                }

                public static ServiceAccountJWTAccessCredentials parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
                    return (ServiceAccountJWTAccessCredentials) PARSER.parseFrom(bArr);
                }

                public static ServiceAccountJWTAccessCredentials parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                    return (ServiceAccountJWTAccessCredentials) PARSER.parseFrom(bArr, extensionRegistryLite);
                }

                public static ServiceAccountJWTAccessCredentials parseFrom(InputStream inputStream) throws IOException {
                    return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
                }

                public static ServiceAccountJWTAccessCredentials parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                    return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
                }

                public static ServiceAccountJWTAccessCredentials parseDelimitedFrom(InputStream inputStream) throws IOException {
                    return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
                }

                public static ServiceAccountJWTAccessCredentials parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                    return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
                }

                public static ServiceAccountJWTAccessCredentials parseFrom(CodedInputStream codedInputStream) throws IOException {
                    return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
                }

                public static ServiceAccountJWTAccessCredentials parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                    return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
                }

                public static Builder newBuilder() {
                    return DEFAULT_INSTANCE.m15180toBuilder();
                }

                public static Builder newBuilder(ServiceAccountJWTAccessCredentials serviceAccountJWTAccessCredentials) {
                    return DEFAULT_INSTANCE.m15180toBuilder().mergeFrom(serviceAccountJWTAccessCredentials);
                }

                /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public ServiceAccountJWTAccessCredentials m15175getDefaultInstanceForType() {
                    return DEFAULT_INSTANCE;
                }

                public Parser<ServiceAccountJWTAccessCredentials> getParserForType() {
                    return PARSER;
                }

                @Override
                // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentials.ServiceAccountJWTAccessCredentialsOrBuilder
                public long getTokenLifetimeSeconds() {
                    return this.tokenLifetimeSeconds_;
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
                    return new ServiceAccountJWTAccessCredentials();
                }

                public final UnknownFieldSet getUnknownFields() {
                    return this.unknownFields;
                }

                protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                    return GrpcServiceProto.internal_static_envoy_api_v2_core_GrpcService_GoogleGrpc_CallCredentials_ServiceAccountJWTAccessCredentials_fieldAccessorTable.ensureFieldAccessorsInitialized(ServiceAccountJWTAccessCredentials.class, Builder.class);
                }

                @Override
                // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentials.ServiceAccountJWTAccessCredentialsOrBuilder
                public String getJsonKey() {
                    Object obj = this.jsonKey_;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.jsonKey_ = stringUtf8;
                    return stringUtf8;
                }

                @Override
                // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentials.ServiceAccountJWTAccessCredentialsOrBuilder
                public ByteString getJsonKeyBytes() {
                    Object obj = this.jsonKey_;
                    if (obj instanceof String) {
                        ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                        this.jsonKey_ = byteStringCopyFromUtf8;
                        return byteStringCopyFromUtf8;
                    }
                    return (ByteString) obj;
                }

                public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
                    if (!getJsonKeyBytes().isEmpty()) {
                        GeneratedMessageV3.writeString(codedOutputStream, 1, this.jsonKey_);
                    }
                    long j = this.tokenLifetimeSeconds_;
                    if (j != 0) {
                        codedOutputStream.writeUInt64(2, j);
                    }
                    this.unknownFields.writeTo(codedOutputStream);
                }

                public int getSerializedSize() {
                    int i = this.memoizedSize;
                    if (i != -1) {
                        return i;
                    }
                    int iComputeStringSize = !getJsonKeyBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.jsonKey_) : 0;
                    long j = this.tokenLifetimeSeconds_;
                    if (j != 0) {
                        iComputeStringSize += CodedOutputStream.computeUInt64Size(2, j);
                    }
                    int serializedSize = iComputeStringSize + this.unknownFields.getSerializedSize();
                    this.memoizedSize = serializedSize;
                    return serializedSize;
                }

                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof ServiceAccountJWTAccessCredentials)) {
                        return super.equals(obj);
                    }
                    ServiceAccountJWTAccessCredentials serviceAccountJWTAccessCredentials = (ServiceAccountJWTAccessCredentials) obj;
                    return getJsonKey().equals(serviceAccountJWTAccessCredentials.getJsonKey()) && getTokenLifetimeSeconds() == serviceAccountJWTAccessCredentials.getTokenLifetimeSeconds() && this.unknownFields.equals(serviceAccountJWTAccessCredentials.unknownFields);
                }

                public int hashCode() {
                    if (this.memoizedHashCode != 0) {
                        return this.memoizedHashCode;
                    }
                    int iHashCode = ((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getJsonKey().hashCode()) * 37) + 2) * 53) + Internal.hashLong(getTokenLifetimeSeconds())) * 29) + this.unknownFields.hashCode();
                    this.memoizedHashCode = iHashCode;
                    return iHashCode;
                }

                /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m15177newBuilderForType() {
                    return newBuilder();
                }

                /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m15180toBuilder() {
                    return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
                }

                /* JADX INFO: Access modifiers changed from: protected */
                public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
                    return new Builder(builderParent);
                }

                public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ServiceAccountJWTAccessCredentialsOrBuilder {
                    private Object jsonKey_;
                    private long tokenLifetimeSeconds_;

                    private Builder() {
                        this.jsonKey_ = "";
                        maybeForceBuilderInitialization();
                    }

                    private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                        super(builderParent);
                        this.jsonKey_ = "";
                        maybeForceBuilderInitialization();
                    }

                    public static final Descriptors.Descriptor getDescriptor() {
                        return GrpcServiceProto.internal_static_envoy_api_v2_core_GrpcService_GoogleGrpc_CallCredentials_ServiceAccountJWTAccessCredentials_descriptor;
                    }

                    @Override
                    // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentials.ServiceAccountJWTAccessCredentialsOrBuilder
                    public long getTokenLifetimeSeconds() {
                        return this.tokenLifetimeSeconds_;
                    }

                    public Builder setTokenLifetimeSeconds(long j) {
                        this.tokenLifetimeSeconds_ = j;
                        onChanged();
                        return this;
                    }

                    public final boolean isInitialized() {
                        return true;
                    }

                    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                        return GrpcServiceProto.internal_static_envoy_api_v2_core_GrpcService_GoogleGrpc_CallCredentials_ServiceAccountJWTAccessCredentials_fieldAccessorTable.ensureFieldAccessorsInitialized(ServiceAccountJWTAccessCredentials.class, Builder.class);
                    }

                    private void maybeForceBuilderInitialization() {
                        boolean unused = ServiceAccountJWTAccessCredentials.alwaysUseFieldBuilders;
                    }

                    /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                    public Builder m15191clear() {
                        super.clear();
                        this.jsonKey_ = "";
                        this.tokenLifetimeSeconds_ = 0L;
                        return this;
                    }

                    public Descriptors.Descriptor getDescriptorForType() {
                        return GrpcServiceProto.internal_static_envoy_api_v2_core_GrpcService_GoogleGrpc_CallCredentials_ServiceAccountJWTAccessCredentials_descriptor;
                    }

                    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                    public ServiceAccountJWTAccessCredentials m15204getDefaultInstanceForType() {
                        return ServiceAccountJWTAccessCredentials.getDefaultInstance();
                    }

                    /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
                    /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                    public ServiceAccountJWTAccessCredentials m15185build() throws UninitializedMessageException {
                        ServiceAccountJWTAccessCredentials serviceAccountJWTAccessCredentialsM15187buildPartial = m15187buildPartial();
                        if (serviceAccountJWTAccessCredentialsM15187buildPartial.isInitialized()) {
                            return serviceAccountJWTAccessCredentialsM15187buildPartial;
                        }
                        throw newUninitializedMessageException(serviceAccountJWTAccessCredentialsM15187buildPartial);
                    }

                    /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                    public ServiceAccountJWTAccessCredentials m15187buildPartial() {
                        ServiceAccountJWTAccessCredentials serviceAccountJWTAccessCredentials = new ServiceAccountJWTAccessCredentials(this);
                        serviceAccountJWTAccessCredentials.jsonKey_ = this.jsonKey_;
                        serviceAccountJWTAccessCredentials.tokenLifetimeSeconds_ = this.tokenLifetimeSeconds_;
                        onBuilt();
                        return serviceAccountJWTAccessCredentials;
                    }

                    /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                    public Builder m15203clone() {
                        return (Builder) super.clone();
                    }

                    /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                    public Builder m15215setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                        return (Builder) super.setField(fieldDescriptor, obj);
                    }

                    /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                    public Builder m15193clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                        return (Builder) super.clearField(fieldDescriptor);
                    }

                    /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                    public Builder m15196clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                        return (Builder) super.clearOneof(oneofDescriptor);
                    }

                    /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                    public Builder m15217setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                        return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
                    }

                    /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                    public Builder m15183addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                        return (Builder) super.addRepeatedField(fieldDescriptor, obj);
                    }

                    /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                    public Builder m15208mergeFrom(Message message) {
                        if (message instanceof ServiceAccountJWTAccessCredentials) {
                            return mergeFrom((ServiceAccountJWTAccessCredentials) message);
                        }
                        super.mergeFrom(message);
                        return this;
                    }

                    public Builder mergeFrom(ServiceAccountJWTAccessCredentials serviceAccountJWTAccessCredentials) {
                        if (serviceAccountJWTAccessCredentials == ServiceAccountJWTAccessCredentials.getDefaultInstance()) {
                            return this;
                        }
                        if (!serviceAccountJWTAccessCredentials.getJsonKey().isEmpty()) {
                            this.jsonKey_ = serviceAccountJWTAccessCredentials.jsonKey_;
                            onChanged();
                        }
                        if (serviceAccountJWTAccessCredentials.getTokenLifetimeSeconds() != 0) {
                            setTokenLifetimeSeconds(serviceAccountJWTAccessCredentials.getTokenLifetimeSeconds());
                        }
                        m15213mergeUnknownFields(serviceAccountJWTAccessCredentials.unknownFields);
                        onChanged();
                        return this;
                    }

                    /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
                    /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                    /*
                        Code decompiled incorrectly, please refer to instructions dump.
                        To view partially-correct add '--show-bad-code' argument
                    */
                    public io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentials.ServiceAccountJWTAccessCredentials.Builder m15209mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
                        /*
                            r2 = this;
                            r0 = 0
                            com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentials.ServiceAccountJWTAccessCredentials.access$4200()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                            java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                            io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService$GoogleGrpc$CallCredentials$ServiceAccountJWTAccessCredentials r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentials.ServiceAccountJWTAccessCredentials) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                            io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService$GoogleGrpc$CallCredentials$ServiceAccountJWTAccessCredentials r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentials.ServiceAccountJWTAccessCredentials) r4     // Catch: java.lang.Throwable -> L11
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
                        throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentials.ServiceAccountJWTAccessCredentials.Builder.m15209mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService$GoogleGrpc$CallCredentials$ServiceAccountJWTAccessCredentials$Builder");
                    }

                    @Override
                    // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentials.ServiceAccountJWTAccessCredentialsOrBuilder
                    public String getJsonKey() {
                        Object obj = this.jsonKey_;
                        if (!(obj instanceof String)) {
                            String stringUtf8 = ((ByteString) obj).toStringUtf8();
                            this.jsonKey_ = stringUtf8;
                            return stringUtf8;
                        }
                        return (String) obj;
                    }

                    public Builder setJsonKey(String str) {
                        str.getClass();
                        this.jsonKey_ = str;
                        onChanged();
                        return this;
                    }

                    @Override
                    // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentials.ServiceAccountJWTAccessCredentialsOrBuilder
                    public ByteString getJsonKeyBytes() {
                        Object obj = this.jsonKey_;
                        if (obj instanceof String) {
                            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                            this.jsonKey_ = byteStringCopyFromUtf8;
                            return byteStringCopyFromUtf8;
                        }
                        return (ByteString) obj;
                    }

                    public Builder setJsonKeyBytes(ByteString byteString) {
                        byteString.getClass();
                        ServiceAccountJWTAccessCredentials.checkByteStringIsUtf8(byteString);
                        this.jsonKey_ = byteString;
                        onChanged();
                        return this;
                    }

                    public Builder clearJsonKey() {
                        this.jsonKey_ = ServiceAccountJWTAccessCredentials.getDefaultInstance().getJsonKey();
                        onChanged();
                        return this;
                    }

                    public Builder clearTokenLifetimeSeconds() {
                        this.tokenLifetimeSeconds_ = 0L;
                        onChanged();
                        return this;
                    }

                    /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                    public final Builder m15219setUnknownFields(UnknownFieldSet unknownFieldSet) {
                        return (Builder) super.setUnknownFields(unknownFieldSet);
                    }

                    /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                    public final Builder m15213mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                        return (Builder) super.mergeUnknownFields(unknownFieldSet);
                    }
                }
            }

            public static final class GoogleIAMCredentials extends GeneratedMessageV3 implements GoogleIAMCredentialsOrBuilder {
                public static final int AUTHORITY_SELECTOR_FIELD_NUMBER = 2;
                public static final int AUTHORIZATION_TOKEN_FIELD_NUMBER = 1;
                private static final GoogleIAMCredentials DEFAULT_INSTANCE = new GoogleIAMCredentials();
                private static final Parser<GoogleIAMCredentials> PARSER = new AbstractParser<GoogleIAMCredentials>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentials.GoogleIAMCredentials.1
                    /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
                    public GoogleIAMCredentials m15090parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                        return new GoogleIAMCredentials(codedInputStream, extensionRegistryLite);
                    }
                };
                private static final long serialVersionUID = 0;
                private volatile Object authoritySelector_;
                private volatile Object authorizationToken_;
                private byte memoizedIsInitialized;

                private GoogleIAMCredentials(GeneratedMessageV3.Builder<?> builder) {
                    super(builder);
                    this.memoizedIsInitialized = (byte) -1;
                }

                private GoogleIAMCredentials() {
                    this.memoizedIsInitialized = (byte) -1;
                    this.authorizationToken_ = "";
                    this.authoritySelector_ = "";
                }

                private GoogleIAMCredentials(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                    this();
                    extensionRegistryLite.getClass();
                    UnknownFieldSet.Builder builderNewBuilder = UnknownFieldSet.newBuilder();
                    boolean z = false;
                    while (!z) {
                        try {
                            try {
                                try {
                                    int tag = codedInputStream.readTag();
                                    if (tag != 0) {
                                        if (tag == 10) {
                                            this.authorizationToken_ = codedInputStream.readStringRequireUtf8();
                                        } else if (tag == 18) {
                                            this.authoritySelector_ = codedInputStream.readStringRequireUtf8();
                                        } else if (!parseUnknownField(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                                        }
                                    }
                                    z = true;
                                } catch (IOException e) {
                                    throw new InvalidProtocolBufferException(e).setUnfinishedMessage(this);
                                }
                            } catch (InvalidProtocolBufferException e2) {
                                throw e2.setUnfinishedMessage(this);
                            }
                        } finally {
                            this.unknownFields = builderNewBuilder.build();
                            makeExtensionsImmutable();
                        }
                    }
                }

                public static GoogleIAMCredentials getDefaultInstance() {
                    return DEFAULT_INSTANCE;
                }

                public static Parser<GoogleIAMCredentials> parser() {
                    return PARSER;
                }

                public static final Descriptors.Descriptor getDescriptor() {
                    return GrpcServiceProto.internal_static_envoy_api_v2_core_GrpcService_GoogleGrpc_CallCredentials_GoogleIAMCredentials_descriptor;
                }

                public static GoogleIAMCredentials parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
                    return (GoogleIAMCredentials) PARSER.parseFrom(byteBuffer);
                }

                public static GoogleIAMCredentials parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                    return (GoogleIAMCredentials) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
                }

                public static GoogleIAMCredentials parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
                    return (GoogleIAMCredentials) PARSER.parseFrom(byteString);
                }

                public static GoogleIAMCredentials parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                    return (GoogleIAMCredentials) PARSER.parseFrom(byteString, extensionRegistryLite);
                }

                public static GoogleIAMCredentials parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
                    return (GoogleIAMCredentials) PARSER.parseFrom(bArr);
                }

                public static GoogleIAMCredentials parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                    return (GoogleIAMCredentials) PARSER.parseFrom(bArr, extensionRegistryLite);
                }

                public static GoogleIAMCredentials parseFrom(InputStream inputStream) throws IOException {
                    return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
                }

                public static GoogleIAMCredentials parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                    return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
                }

                public static GoogleIAMCredentials parseDelimitedFrom(InputStream inputStream) throws IOException {
                    return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
                }

                public static GoogleIAMCredentials parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                    return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
                }

                public static GoogleIAMCredentials parseFrom(CodedInputStream codedInputStream) throws IOException {
                    return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
                }

                public static GoogleIAMCredentials parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                    return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
                }

                public static Builder newBuilder() {
                    return DEFAULT_INSTANCE.m15088toBuilder();
                }

                public static Builder newBuilder(GoogleIAMCredentials googleIAMCredentials) {
                    return DEFAULT_INSTANCE.m15088toBuilder().mergeFrom(googleIAMCredentials);
                }

                /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public GoogleIAMCredentials m15083getDefaultInstanceForType() {
                    return DEFAULT_INSTANCE;
                }

                public Parser<GoogleIAMCredentials> getParserForType() {
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
                    return new GoogleIAMCredentials();
                }

                public final UnknownFieldSet getUnknownFields() {
                    return this.unknownFields;
                }

                protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                    return GrpcServiceProto.internal_static_envoy_api_v2_core_GrpcService_GoogleGrpc_CallCredentials_GoogleIAMCredentials_fieldAccessorTable.ensureFieldAccessorsInitialized(GoogleIAMCredentials.class, Builder.class);
                }

                @Override
                // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentials.GoogleIAMCredentialsOrBuilder
                public String getAuthorizationToken() {
                    Object obj = this.authorizationToken_;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.authorizationToken_ = stringUtf8;
                    return stringUtf8;
                }

                @Override
                // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentials.GoogleIAMCredentialsOrBuilder
                public ByteString getAuthorizationTokenBytes() {
                    Object obj = this.authorizationToken_;
                    if (obj instanceof String) {
                        ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                        this.authorizationToken_ = byteStringCopyFromUtf8;
                        return byteStringCopyFromUtf8;
                    }
                    return (ByteString) obj;
                }

                @Override
                // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentials.GoogleIAMCredentialsOrBuilder
                public String getAuthoritySelector() {
                    Object obj = this.authoritySelector_;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.authoritySelector_ = stringUtf8;
                    return stringUtf8;
                }

                @Override
                // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentials.GoogleIAMCredentialsOrBuilder
                public ByteString getAuthoritySelectorBytes() {
                    Object obj = this.authoritySelector_;
                    if (obj instanceof String) {
                        ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                        this.authoritySelector_ = byteStringCopyFromUtf8;
                        return byteStringCopyFromUtf8;
                    }
                    return (ByteString) obj;
                }

                public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
                    if (!getAuthorizationTokenBytes().isEmpty()) {
                        GeneratedMessageV3.writeString(codedOutputStream, 1, this.authorizationToken_);
                    }
                    if (!getAuthoritySelectorBytes().isEmpty()) {
                        GeneratedMessageV3.writeString(codedOutputStream, 2, this.authoritySelector_);
                    }
                    this.unknownFields.writeTo(codedOutputStream);
                }

                public int getSerializedSize() {
                    int i = this.memoizedSize;
                    if (i != -1) {
                        return i;
                    }
                    int iComputeStringSize = !getAuthorizationTokenBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.authorizationToken_) : 0;
                    if (!getAuthoritySelectorBytes().isEmpty()) {
                        iComputeStringSize += GeneratedMessageV3.computeStringSize(2, this.authoritySelector_);
                    }
                    int serializedSize = iComputeStringSize + this.unknownFields.getSerializedSize();
                    this.memoizedSize = serializedSize;
                    return serializedSize;
                }

                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof GoogleIAMCredentials)) {
                        return super.equals(obj);
                    }
                    GoogleIAMCredentials googleIAMCredentials = (GoogleIAMCredentials) obj;
                    return getAuthorizationToken().equals(googleIAMCredentials.getAuthorizationToken()) && getAuthoritySelector().equals(googleIAMCredentials.getAuthoritySelector()) && this.unknownFields.equals(googleIAMCredentials.unknownFields);
                }

                public int hashCode() {
                    if (this.memoizedHashCode != 0) {
                        return this.memoizedHashCode;
                    }
                    int iHashCode = ((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getAuthorizationToken().hashCode()) * 37) + 2) * 53) + getAuthoritySelector().hashCode()) * 29) + this.unknownFields.hashCode();
                    this.memoizedHashCode = iHashCode;
                    return iHashCode;
                }

                /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m15085newBuilderForType() {
                    return newBuilder();
                }

                /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m15088toBuilder() {
                    return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
                }

                /* JADX INFO: Access modifiers changed from: protected */
                public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
                    return new Builder(builderParent);
                }

                public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements GoogleIAMCredentialsOrBuilder {
                    private Object authoritySelector_;
                    private Object authorizationToken_;

                    private Builder() {
                        this.authorizationToken_ = "";
                        this.authoritySelector_ = "";
                        maybeForceBuilderInitialization();
                    }

                    private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                        super(builderParent);
                        this.authorizationToken_ = "";
                        this.authoritySelector_ = "";
                        maybeForceBuilderInitialization();
                    }

                    public static final Descriptors.Descriptor getDescriptor() {
                        return GrpcServiceProto.internal_static_envoy_api_v2_core_GrpcService_GoogleGrpc_CallCredentials_GoogleIAMCredentials_descriptor;
                    }

                    public final boolean isInitialized() {
                        return true;
                    }

                    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                        return GrpcServiceProto.internal_static_envoy_api_v2_core_GrpcService_GoogleGrpc_CallCredentials_GoogleIAMCredentials_fieldAccessorTable.ensureFieldAccessorsInitialized(GoogleIAMCredentials.class, Builder.class);
                    }

                    private void maybeForceBuilderInitialization() {
                        boolean unused = GoogleIAMCredentials.alwaysUseFieldBuilders;
                    }

                    /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                    public Builder m15099clear() {
                        super.clear();
                        this.authorizationToken_ = "";
                        this.authoritySelector_ = "";
                        return this;
                    }

                    public Descriptors.Descriptor getDescriptorForType() {
                        return GrpcServiceProto.internal_static_envoy_api_v2_core_GrpcService_GoogleGrpc_CallCredentials_GoogleIAMCredentials_descriptor;
                    }

                    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                    public GoogleIAMCredentials m15112getDefaultInstanceForType() {
                        return GoogleIAMCredentials.getDefaultInstance();
                    }

                    /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
                    /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                    public GoogleIAMCredentials m15093build() throws UninitializedMessageException {
                        GoogleIAMCredentials googleIAMCredentialsM15095buildPartial = m15095buildPartial();
                        if (googleIAMCredentialsM15095buildPartial.isInitialized()) {
                            return googleIAMCredentialsM15095buildPartial;
                        }
                        throw newUninitializedMessageException(googleIAMCredentialsM15095buildPartial);
                    }

                    /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                    public GoogleIAMCredentials m15095buildPartial() {
                        GoogleIAMCredentials googleIAMCredentials = new GoogleIAMCredentials(this);
                        googleIAMCredentials.authorizationToken_ = this.authorizationToken_;
                        googleIAMCredentials.authoritySelector_ = this.authoritySelector_;
                        onBuilt();
                        return googleIAMCredentials;
                    }

                    /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                    public Builder m15111clone() {
                        return (Builder) super.clone();
                    }

                    /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                    public Builder m15123setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                        return (Builder) super.setField(fieldDescriptor, obj);
                    }

                    /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                    public Builder m15101clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                        return (Builder) super.clearField(fieldDescriptor);
                    }

                    /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                    public Builder m15104clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                        return (Builder) super.clearOneof(oneofDescriptor);
                    }

                    /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                    public Builder m15125setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                        return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
                    }

                    /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                    public Builder m15091addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                        return (Builder) super.addRepeatedField(fieldDescriptor, obj);
                    }

                    /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                    public Builder m15116mergeFrom(Message message) {
                        if (message instanceof GoogleIAMCredentials) {
                            return mergeFrom((GoogleIAMCredentials) message);
                        }
                        super.mergeFrom(message);
                        return this;
                    }

                    public Builder mergeFrom(GoogleIAMCredentials googleIAMCredentials) {
                        if (googleIAMCredentials == GoogleIAMCredentials.getDefaultInstance()) {
                            return this;
                        }
                        if (!googleIAMCredentials.getAuthorizationToken().isEmpty()) {
                            this.authorizationToken_ = googleIAMCredentials.authorizationToken_;
                            onChanged();
                        }
                        if (!googleIAMCredentials.getAuthoritySelector().isEmpty()) {
                            this.authoritySelector_ = googleIAMCredentials.authoritySelector_;
                            onChanged();
                        }
                        m15121mergeUnknownFields(googleIAMCredentials.unknownFields);
                        onChanged();
                        return this;
                    }

                    /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
                    /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                    /*
                        Code decompiled incorrectly, please refer to instructions dump.
                        To view partially-correct add '--show-bad-code' argument
                    */
                    public io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentials.GoogleIAMCredentials.Builder m15117mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
                        /*
                            r2 = this;
                            r0 = 0
                            com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentials.GoogleIAMCredentials.access$5200()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                            java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                            io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService$GoogleGrpc$CallCredentials$GoogleIAMCredentials r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentials.GoogleIAMCredentials) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                            io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService$GoogleGrpc$CallCredentials$GoogleIAMCredentials r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentials.GoogleIAMCredentials) r4     // Catch: java.lang.Throwable -> L11
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
                        throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentials.GoogleIAMCredentials.Builder.m15117mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService$GoogleGrpc$CallCredentials$GoogleIAMCredentials$Builder");
                    }

                    @Override
                    // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentials.GoogleIAMCredentialsOrBuilder
                    public String getAuthorizationToken() {
                        Object obj = this.authorizationToken_;
                        if (!(obj instanceof String)) {
                            String stringUtf8 = ((ByteString) obj).toStringUtf8();
                            this.authorizationToken_ = stringUtf8;
                            return stringUtf8;
                        }
                        return (String) obj;
                    }

                    public Builder setAuthorizationToken(String str) {
                        str.getClass();
                        this.authorizationToken_ = str;
                        onChanged();
                        return this;
                    }

                    @Override
                    // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentials.GoogleIAMCredentialsOrBuilder
                    public ByteString getAuthorizationTokenBytes() {
                        Object obj = this.authorizationToken_;
                        if (obj instanceof String) {
                            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                            this.authorizationToken_ = byteStringCopyFromUtf8;
                            return byteStringCopyFromUtf8;
                        }
                        return (ByteString) obj;
                    }

                    public Builder setAuthorizationTokenBytes(ByteString byteString) {
                        byteString.getClass();
                        GoogleIAMCredentials.checkByteStringIsUtf8(byteString);
                        this.authorizationToken_ = byteString;
                        onChanged();
                        return this;
                    }

                    public Builder clearAuthorizationToken() {
                        this.authorizationToken_ = GoogleIAMCredentials.getDefaultInstance().getAuthorizationToken();
                        onChanged();
                        return this;
                    }

                    @Override
                    // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentials.GoogleIAMCredentialsOrBuilder
                    public String getAuthoritySelector() {
                        Object obj = this.authoritySelector_;
                        if (!(obj instanceof String)) {
                            String stringUtf8 = ((ByteString) obj).toStringUtf8();
                            this.authoritySelector_ = stringUtf8;
                            return stringUtf8;
                        }
                        return (String) obj;
                    }

                    public Builder setAuthoritySelector(String str) {
                        str.getClass();
                        this.authoritySelector_ = str;
                        onChanged();
                        return this;
                    }

                    @Override
                    // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentials.GoogleIAMCredentialsOrBuilder
                    public ByteString getAuthoritySelectorBytes() {
                        Object obj = this.authoritySelector_;
                        if (obj instanceof String) {
                            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                            this.authoritySelector_ = byteStringCopyFromUtf8;
                            return byteStringCopyFromUtf8;
                        }
                        return (ByteString) obj;
                    }

                    public Builder setAuthoritySelectorBytes(ByteString byteString) {
                        byteString.getClass();
                        GoogleIAMCredentials.checkByteStringIsUtf8(byteString);
                        this.authoritySelector_ = byteString;
                        onChanged();
                        return this;
                    }

                    public Builder clearAuthoritySelector() {
                        this.authoritySelector_ = GoogleIAMCredentials.getDefaultInstance().getAuthoritySelector();
                        onChanged();
                        return this;
                    }

                    /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                    public final Builder m15127setUnknownFields(UnknownFieldSet unknownFieldSet) {
                        return (Builder) super.setUnknownFields(unknownFieldSet);
                    }

                    /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                    public final Builder m15121mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                        return (Builder) super.mergeUnknownFields(unknownFieldSet);
                    }
                }
            }

            public static final class MetadataCredentialsFromPlugin extends GeneratedMessageV3 implements MetadataCredentialsFromPluginOrBuilder {
                public static final int CONFIG_FIELD_NUMBER = 2;
                public static final int NAME_FIELD_NUMBER = 1;
                public static final int TYPED_CONFIG_FIELD_NUMBER = 3;
                private static final long serialVersionUID = 0;
                private static final MetadataCredentialsFromPlugin DEFAULT_INSTANCE = new MetadataCredentialsFromPlugin();
                private static final Parser<MetadataCredentialsFromPlugin> PARSER = new AbstractParser<MetadataCredentialsFromPlugin>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentials.MetadataCredentialsFromPlugin.1
                    /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
                    public MetadataCredentialsFromPlugin m15136parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                        return new MetadataCredentialsFromPlugin(codedInputStream, extensionRegistryLite);
                    }
                };
                private int configTypeCase_;
                private Object configType_;
                private byte memoizedIsInitialized;
                private volatile Object name_;

                private MetadataCredentialsFromPlugin(GeneratedMessageV3.Builder<?> builder) {
                    super(builder);
                    this.configTypeCase_ = 0;
                    this.memoizedIsInitialized = (byte) -1;
                }

                private MetadataCredentialsFromPlugin() {
                    this.configTypeCase_ = 0;
                    this.memoizedIsInitialized = (byte) -1;
                    this.name_ = "";
                }

                private MetadataCredentialsFromPlugin(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                    Struct.Builder builder;
                    this();
                    extensionRegistryLite.getClass();
                    UnknownFieldSet.Builder builderNewBuilder = UnknownFieldSet.newBuilder();
                    boolean z = false;
                    while (!z) {
                        try {
                            try {
                                int tag = codedInputStream.readTag();
                                if (tag != 0) {
                                    if (tag != 10) {
                                        if (tag == 18) {
                                            builder = this.configTypeCase_ == 2 ? ((Struct) this.configType_).toBuilder() : null;
                                            Struct message = codedInputStream.readMessage(Struct.parser(), extensionRegistryLite);
                                            this.configType_ = message;
                                            if (builder != null) {
                                                builder.mergeFrom(message);
                                                this.configType_ = builder.buildPartial();
                                            }
                                            this.configTypeCase_ = 2;
                                        } else if (tag == 26) {
                                            builder = this.configTypeCase_ == 3 ? ((Any) this.configType_).toBuilder() : null;
                                            Any message2 = codedInputStream.readMessage(Any.parser(), extensionRegistryLite);
                                            this.configType_ = message2;
                                            if (builder != null) {
                                                builder.mergeFrom(message2);
                                                this.configType_ = builder.buildPartial();
                                            }
                                            this.configTypeCase_ = 3;
                                        } else if (!parseUnknownField(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                                        }
                                    } else {
                                        this.name_ = codedInputStream.readStringRequireUtf8();
                                    }
                                }
                                z = true;
                            } catch (IOException e) {
                                throw new InvalidProtocolBufferException(e).setUnfinishedMessage(this);
                            } catch (InvalidProtocolBufferException e2) {
                                throw e2.setUnfinishedMessage(this);
                            }
                        } finally {
                            this.unknownFields = builderNewBuilder.build();
                            makeExtensionsImmutable();
                        }
                    }
                }

                public static MetadataCredentialsFromPlugin getDefaultInstance() {
                    return DEFAULT_INSTANCE;
                }

                public static Parser<MetadataCredentialsFromPlugin> parser() {
                    return PARSER;
                }

                public static final Descriptors.Descriptor getDescriptor() {
                    return GrpcServiceProto.internal_static_envoy_api_v2_core_GrpcService_GoogleGrpc_CallCredentials_MetadataCredentialsFromPlugin_descriptor;
                }

                public static MetadataCredentialsFromPlugin parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
                    return (MetadataCredentialsFromPlugin) PARSER.parseFrom(byteBuffer);
                }

                public static MetadataCredentialsFromPlugin parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                    return (MetadataCredentialsFromPlugin) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
                }

                public static MetadataCredentialsFromPlugin parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
                    return (MetadataCredentialsFromPlugin) PARSER.parseFrom(byteString);
                }

                public static MetadataCredentialsFromPlugin parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                    return (MetadataCredentialsFromPlugin) PARSER.parseFrom(byteString, extensionRegistryLite);
                }

                public static MetadataCredentialsFromPlugin parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
                    return (MetadataCredentialsFromPlugin) PARSER.parseFrom(bArr);
                }

                public static MetadataCredentialsFromPlugin parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                    return (MetadataCredentialsFromPlugin) PARSER.parseFrom(bArr, extensionRegistryLite);
                }

                public static MetadataCredentialsFromPlugin parseFrom(InputStream inputStream) throws IOException {
                    return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
                }

                public static MetadataCredentialsFromPlugin parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                    return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
                }

                public static MetadataCredentialsFromPlugin parseDelimitedFrom(InputStream inputStream) throws IOException {
                    return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
                }

                public static MetadataCredentialsFromPlugin parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                    return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
                }

                public static MetadataCredentialsFromPlugin parseFrom(CodedInputStream codedInputStream) throws IOException {
                    return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
                }

                public static MetadataCredentialsFromPlugin parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                    return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
                }

                public static Builder newBuilder() {
                    return DEFAULT_INSTANCE.m15134toBuilder();
                }

                public static Builder newBuilder(MetadataCredentialsFromPlugin metadataCredentialsFromPlugin) {
                    return DEFAULT_INSTANCE.m15134toBuilder().mergeFrom(metadataCredentialsFromPlugin);
                }

                /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public MetadataCredentialsFromPlugin m15129getDefaultInstanceForType() {
                    return DEFAULT_INSTANCE;
                }

                public Parser<MetadataCredentialsFromPlugin> getParserForType() {
                    return PARSER;
                }

                @Override
                // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentials.MetadataCredentialsFromPluginOrBuilder
                @Deprecated
                public boolean hasConfig() {
                    return this.configTypeCase_ == 2;
                }

                @Override
                // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentials.MetadataCredentialsFromPluginOrBuilder
                public boolean hasTypedConfig() {
                    return this.configTypeCase_ == 3;
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
                    return new MetadataCredentialsFromPlugin();
                }

                public final UnknownFieldSet getUnknownFields() {
                    return this.unknownFields;
                }

                protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                    return GrpcServiceProto.internal_static_envoy_api_v2_core_GrpcService_GoogleGrpc_CallCredentials_MetadataCredentialsFromPlugin_fieldAccessorTable.ensureFieldAccessorsInitialized(MetadataCredentialsFromPlugin.class, Builder.class);
                }

                @Override
                // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentials.MetadataCredentialsFromPluginOrBuilder
                public ConfigTypeCase getConfigTypeCase() {
                    return ConfigTypeCase.forNumber(this.configTypeCase_);
                }

                @Override
                // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentials.MetadataCredentialsFromPluginOrBuilder
                public String getName() {
                    Object obj = this.name_;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.name_ = stringUtf8;
                    return stringUtf8;
                }

                @Override
                // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentials.MetadataCredentialsFromPluginOrBuilder
                public ByteString getNameBytes() {
                    Object obj = this.name_;
                    if (obj instanceof String) {
                        ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                        this.name_ = byteStringCopyFromUtf8;
                        return byteStringCopyFromUtf8;
                    }
                    return (ByteString) obj;
                }

                @Override
                // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentials.MetadataCredentialsFromPluginOrBuilder
                @Deprecated
                public Struct getConfig() {
                    if (this.configTypeCase_ == 2) {
                        return (Struct) this.configType_;
                    }
                    return Struct.getDefaultInstance();
                }

                @Override
                // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentials.MetadataCredentialsFromPluginOrBuilder
                @Deprecated
                public StructOrBuilder getConfigOrBuilder() {
                    if (this.configTypeCase_ == 2) {
                        return (Struct) this.configType_;
                    }
                    return Struct.getDefaultInstance();
                }

                @Override
                // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentials.MetadataCredentialsFromPluginOrBuilder
                public Any getTypedConfig() {
                    if (this.configTypeCase_ == 3) {
                        return (Any) this.configType_;
                    }
                    return Any.getDefaultInstance();
                }

                @Override
                // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentials.MetadataCredentialsFromPluginOrBuilder
                public AnyOrBuilder getTypedConfigOrBuilder() {
                    if (this.configTypeCase_ == 3) {
                        return (Any) this.configType_;
                    }
                    return Any.getDefaultInstance();
                }

                public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
                    if (!getNameBytes().isEmpty()) {
                        GeneratedMessageV3.writeString(codedOutputStream, 1, this.name_);
                    }
                    if (this.configTypeCase_ == 2) {
                        codedOutputStream.writeMessage(2, (Struct) this.configType_);
                    }
                    if (this.configTypeCase_ == 3) {
                        codedOutputStream.writeMessage(3, (Any) this.configType_);
                    }
                    this.unknownFields.writeTo(codedOutputStream);
                }

                public int getSerializedSize() {
                    int i = this.memoizedSize;
                    if (i != -1) {
                        return i;
                    }
                    int iComputeStringSize = !getNameBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.name_) : 0;
                    if (this.configTypeCase_ == 2) {
                        iComputeStringSize += CodedOutputStream.computeMessageSize(2, (Struct) this.configType_);
                    }
                    if (this.configTypeCase_ == 3) {
                        iComputeStringSize += CodedOutputStream.computeMessageSize(3, (Any) this.configType_);
                    }
                    int serializedSize = iComputeStringSize + this.unknownFields.getSerializedSize();
                    this.memoizedSize = serializedSize;
                    return serializedSize;
                }

                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof MetadataCredentialsFromPlugin)) {
                        return super.equals(obj);
                    }
                    MetadataCredentialsFromPlugin metadataCredentialsFromPlugin = (MetadataCredentialsFromPlugin) obj;
                    if (!getName().equals(metadataCredentialsFromPlugin.getName()) || !getConfigTypeCase().equals(metadataCredentialsFromPlugin.getConfigTypeCase())) {
                        return false;
                    }
                    int i = this.configTypeCase_;
                    if (i == 2) {
                        if (!getConfig().equals(metadataCredentialsFromPlugin.getConfig())) {
                            return false;
                        }
                    } else if (i == 3 && !getTypedConfig().equals(metadataCredentialsFromPlugin.getTypedConfig())) {
                        return false;
                    }
                    return this.unknownFields.equals(metadataCredentialsFromPlugin.unknownFields);
                }

                public int hashCode() {
                    int i;
                    int iHashCode;
                    if (this.memoizedHashCode != 0) {
                        return this.memoizedHashCode;
                    }
                    int iHashCode2 = ((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getName().hashCode();
                    int i2 = this.configTypeCase_;
                    if (i2 == 2) {
                        i = ((iHashCode2 * 37) + 2) * 53;
                        iHashCode = getConfig().hashCode();
                    } else {
                        if (i2 == 3) {
                            i = ((iHashCode2 * 37) + 3) * 53;
                            iHashCode = getTypedConfig().hashCode();
                        }
                        int iHashCode3 = (iHashCode2 * 29) + this.unknownFields.hashCode();
                        this.memoizedHashCode = iHashCode3;
                        return iHashCode3;
                    }
                    iHashCode2 = i + iHashCode;
                    int iHashCode32 = (iHashCode2 * 29) + this.unknownFields.hashCode();
                    this.memoizedHashCode = iHashCode32;
                    return iHashCode32;
                }

                /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m15131newBuilderForType() {
                    return newBuilder();
                }

                /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m15134toBuilder() {
                    return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
                }

                /* JADX INFO: Access modifiers changed from: protected */
                public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
                    return new Builder(builderParent);
                }

                public enum ConfigTypeCase implements Internal.EnumLite, AbstractMessageLite.InternalOneOfEnum {
                    CONFIG(2),
                    TYPED_CONFIG(3),
                    CONFIGTYPE_NOT_SET(0);

                    private final int value;

                    ConfigTypeCase(int i) {
                        this.value = i;
                    }

                    public static ConfigTypeCase forNumber(int i) {
                        if (i == 0) {
                            return CONFIGTYPE_NOT_SET;
                        }
                        if (i == 2) {
                            return CONFIG;
                        }
                        if (i != 3) {
                            return null;
                        }
                        return TYPED_CONFIG;
                    }

                    @Deprecated
                    public static ConfigTypeCase valueOf(int i) {
                        return forNumber(i);
                    }

                    public int getNumber() {
                        return this.value;
                    }
                }

                public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements MetadataCredentialsFromPluginOrBuilder {
                    private SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> configBuilder_;
                    private int configTypeCase_;
                    private Object configType_;
                    private Object name_;
                    private SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> typedConfigBuilder_;

                    private Builder() {
                        this.configTypeCase_ = 0;
                        this.name_ = "";
                        maybeForceBuilderInitialization();
                    }

                    private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                        super(builderParent);
                        this.configTypeCase_ = 0;
                        this.name_ = "";
                        maybeForceBuilderInitialization();
                    }

                    public static final Descriptors.Descriptor getDescriptor() {
                        return GrpcServiceProto.internal_static_envoy_api_v2_core_GrpcService_GoogleGrpc_CallCredentials_MetadataCredentialsFromPlugin_descriptor;
                    }

                    @Override
                    // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentials.MetadataCredentialsFromPluginOrBuilder
                    @Deprecated
                    public boolean hasConfig() {
                        return this.configTypeCase_ == 2;
                    }

                    @Override
                    // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentials.MetadataCredentialsFromPluginOrBuilder
                    public boolean hasTypedConfig() {
                        return this.configTypeCase_ == 3;
                    }

                    public final boolean isInitialized() {
                        return true;
                    }

                    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                        return GrpcServiceProto.internal_static_envoy_api_v2_core_GrpcService_GoogleGrpc_CallCredentials_MetadataCredentialsFromPlugin_fieldAccessorTable.ensureFieldAccessorsInitialized(MetadataCredentialsFromPlugin.class, Builder.class);
                    }

                    private void maybeForceBuilderInitialization() {
                        boolean unused = MetadataCredentialsFromPlugin.alwaysUseFieldBuilders;
                    }

                    /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                    public Builder m15145clear() {
                        super.clear();
                        this.name_ = "";
                        this.configTypeCase_ = 0;
                        this.configType_ = null;
                        return this;
                    }

                    public Descriptors.Descriptor getDescriptorForType() {
                        return GrpcServiceProto.internal_static_envoy_api_v2_core_GrpcService_GoogleGrpc_CallCredentials_MetadataCredentialsFromPlugin_descriptor;
                    }

                    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                    public MetadataCredentialsFromPlugin m15158getDefaultInstanceForType() {
                        return MetadataCredentialsFromPlugin.getDefaultInstance();
                    }

                    /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
                    /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                    public MetadataCredentialsFromPlugin m15139build() throws UninitializedMessageException {
                        MetadataCredentialsFromPlugin metadataCredentialsFromPluginM15141buildPartial = m15141buildPartial();
                        if (metadataCredentialsFromPluginM15141buildPartial.isInitialized()) {
                            return metadataCredentialsFromPluginM15141buildPartial;
                        }
                        throw newUninitializedMessageException(metadataCredentialsFromPluginM15141buildPartial);
                    }

                    /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                    public MetadataCredentialsFromPlugin m15141buildPartial() {
                        MetadataCredentialsFromPlugin metadataCredentialsFromPlugin = new MetadataCredentialsFromPlugin(this);
                        metadataCredentialsFromPlugin.name_ = this.name_;
                        if (this.configTypeCase_ == 2) {
                            SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.configBuilder_;
                            if (singleFieldBuilderV3 == null) {
                                metadataCredentialsFromPlugin.configType_ = this.configType_;
                            } else {
                                metadataCredentialsFromPlugin.configType_ = singleFieldBuilderV3.build();
                            }
                        }
                        if (this.configTypeCase_ == 3) {
                            SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> singleFieldBuilderV32 = this.typedConfigBuilder_;
                            if (singleFieldBuilderV32 == null) {
                                metadataCredentialsFromPlugin.configType_ = this.configType_;
                            } else {
                                metadataCredentialsFromPlugin.configType_ = singleFieldBuilderV32.build();
                            }
                        }
                        metadataCredentialsFromPlugin.configTypeCase_ = this.configTypeCase_;
                        onBuilt();
                        return metadataCredentialsFromPlugin;
                    }

                    /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                    public Builder m15157clone() {
                        return (Builder) super.clone();
                    }

                    /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                    public Builder m15169setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                        return (Builder) super.setField(fieldDescriptor, obj);
                    }

                    /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                    public Builder m15147clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                        return (Builder) super.clearField(fieldDescriptor);
                    }

                    /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                    public Builder m15150clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                        return (Builder) super.clearOneof(oneofDescriptor);
                    }

                    /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                    public Builder m15171setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                        return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
                    }

                    /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                    public Builder m15137addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                        return (Builder) super.addRepeatedField(fieldDescriptor, obj);
                    }

                    /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                    public Builder m15162mergeFrom(Message message) {
                        if (message instanceof MetadataCredentialsFromPlugin) {
                            return mergeFrom((MetadataCredentialsFromPlugin) message);
                        }
                        super.mergeFrom(message);
                        return this;
                    }

                    public Builder mergeFrom(MetadataCredentialsFromPlugin metadataCredentialsFromPlugin) {
                        if (metadataCredentialsFromPlugin == MetadataCredentialsFromPlugin.getDefaultInstance()) {
                            return this;
                        }
                        if (!metadataCredentialsFromPlugin.getName().isEmpty()) {
                            this.name_ = metadataCredentialsFromPlugin.name_;
                            onChanged();
                        }
                        int i = AnonymousClass2.$SwitchMap$io$envoyproxy$envoy$api$v2$core$GrpcService$GoogleGrpc$CallCredentials$MetadataCredentialsFromPlugin$ConfigTypeCase[metadataCredentialsFromPlugin.getConfigTypeCase().ordinal()];
                        if (i == 1) {
                            mergeConfig(metadataCredentialsFromPlugin.getConfig());
                        } else if (i == 2) {
                            mergeTypedConfig(metadataCredentialsFromPlugin.getTypedConfig());
                        }
                        m15167mergeUnknownFields(metadataCredentialsFromPlugin.unknownFields);
                        onChanged();
                        return this;
                    }

                    /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
                    /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                    /*
                        Code decompiled incorrectly, please refer to instructions dump.
                        To view partially-correct add '--show-bad-code' argument
                    */
                    public io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentials.MetadataCredentialsFromPlugin.Builder m15163mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
                        /*
                            r2 = this;
                            r0 = 0
                            com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentials.MetadataCredentialsFromPlugin.access$6400()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                            java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                            io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService$GoogleGrpc$CallCredentials$MetadataCredentialsFromPlugin r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentials.MetadataCredentialsFromPlugin) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                            io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService$GoogleGrpc$CallCredentials$MetadataCredentialsFromPlugin r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentials.MetadataCredentialsFromPlugin) r4     // Catch: java.lang.Throwable -> L11
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
                        throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentials.MetadataCredentialsFromPlugin.Builder.m15163mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService$GoogleGrpc$CallCredentials$MetadataCredentialsFromPlugin$Builder");
                    }

                    @Override
                    // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentials.MetadataCredentialsFromPluginOrBuilder
                    public ConfigTypeCase getConfigTypeCase() {
                        return ConfigTypeCase.forNumber(this.configTypeCase_);
                    }

                    public Builder clearConfigType() {
                        this.configTypeCase_ = 0;
                        this.configType_ = null;
                        onChanged();
                        return this;
                    }

                    @Override
                    // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentials.MetadataCredentialsFromPluginOrBuilder
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

                    @Override
                    // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentials.MetadataCredentialsFromPluginOrBuilder
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
                        MetadataCredentialsFromPlugin.checkByteStringIsUtf8(byteString);
                        this.name_ = byteString;
                        onChanged();
                        return this;
                    }

                    public Builder clearName() {
                        this.name_ = MetadataCredentialsFromPlugin.getDefaultInstance().getName();
                        onChanged();
                        return this;
                    }

                    @Override
                    // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentials.MetadataCredentialsFromPluginOrBuilder
                    @Deprecated
                    public Struct getConfig() {
                        SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.configBuilder_;
                        if (singleFieldBuilderV3 == null) {
                            if (this.configTypeCase_ == 2) {
                                return (Struct) this.configType_;
                            }
                            return Struct.getDefaultInstance();
                        }
                        if (this.configTypeCase_ == 2) {
                            return singleFieldBuilderV3.getMessage();
                        }
                        return Struct.getDefaultInstance();
                    }

                    @Deprecated
                    public Builder setConfig(Struct struct) {
                        SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.configBuilder_;
                        if (singleFieldBuilderV3 == null) {
                            struct.getClass();
                            this.configType_ = struct;
                            onChanged();
                        } else {
                            singleFieldBuilderV3.setMessage(struct);
                        }
                        this.configTypeCase_ = 2;
                        return this;
                    }

                    @Deprecated
                    public Builder setConfig(Struct.Builder builder) {
                        SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.configBuilder_;
                        if (singleFieldBuilderV3 == null) {
                            this.configType_ = builder.build();
                            onChanged();
                        } else {
                            singleFieldBuilderV3.setMessage(builder.build());
                        }
                        this.configTypeCase_ = 2;
                        return this;
                    }

                    @Deprecated
                    public Builder mergeConfig(Struct struct) {
                        SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.configBuilder_;
                        if (singleFieldBuilderV3 == null) {
                            if (this.configTypeCase_ != 2 || this.configType_ == Struct.getDefaultInstance()) {
                                this.configType_ = struct;
                            } else {
                                this.configType_ = Struct.newBuilder((Struct) this.configType_).mergeFrom(struct).buildPartial();
                            }
                            onChanged();
                        } else {
                            if (this.configTypeCase_ == 2) {
                                singleFieldBuilderV3.mergeFrom(struct);
                            }
                            this.configBuilder_.setMessage(struct);
                        }
                        this.configTypeCase_ = 2;
                        return this;
                    }

                    @Deprecated
                    public Builder clearConfig() {
                        SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.configBuilder_;
                        if (singleFieldBuilderV3 != null) {
                            if (this.configTypeCase_ == 2) {
                                this.configTypeCase_ = 0;
                                this.configType_ = null;
                            }
                            singleFieldBuilderV3.clear();
                        } else if (this.configTypeCase_ == 2) {
                            this.configTypeCase_ = 0;
                            this.configType_ = null;
                            onChanged();
                        }
                        return this;
                    }

                    @Deprecated
                    public Struct.Builder getConfigBuilder() {
                        return getConfigFieldBuilder().getBuilder();
                    }

                    @Override
                    // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentials.MetadataCredentialsFromPluginOrBuilder
                    @Deprecated
                    public StructOrBuilder getConfigOrBuilder() {
                        SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3;
                        int i = this.configTypeCase_;
                        if (i == 2 && (singleFieldBuilderV3 = this.configBuilder_) != null) {
                            return singleFieldBuilderV3.getMessageOrBuilder();
                        }
                        if (i == 2) {
                            return (Struct) this.configType_;
                        }
                        return Struct.getDefaultInstance();
                    }

                    private SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> getConfigFieldBuilder() {
                        if (this.configBuilder_ == null) {
                            if (this.configTypeCase_ != 2) {
                                this.configType_ = Struct.getDefaultInstance();
                            }
                            this.configBuilder_ = new SingleFieldBuilderV3<>((Struct) this.configType_, getParentForChildren(), isClean());
                            this.configType_ = null;
                        }
                        this.configTypeCase_ = 2;
                        onChanged();
                        return this.configBuilder_;
                    }

                    @Override
                    // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentials.MetadataCredentialsFromPluginOrBuilder
                    public Any getTypedConfig() {
                        SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> singleFieldBuilderV3 = this.typedConfigBuilder_;
                        if (singleFieldBuilderV3 == null) {
                            if (this.configTypeCase_ == 3) {
                                return (Any) this.configType_;
                            }
                            return Any.getDefaultInstance();
                        }
                        if (this.configTypeCase_ == 3) {
                            return singleFieldBuilderV3.getMessage();
                        }
                        return Any.getDefaultInstance();
                    }

                    public Builder setTypedConfig(Any any) {
                        SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> singleFieldBuilderV3 = this.typedConfigBuilder_;
                        if (singleFieldBuilderV3 == null) {
                            any.getClass();
                            this.configType_ = any;
                            onChanged();
                        } else {
                            singleFieldBuilderV3.setMessage(any);
                        }
                        this.configTypeCase_ = 3;
                        return this;
                    }

                    public Builder setTypedConfig(Any.Builder builder) {
                        SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> singleFieldBuilderV3 = this.typedConfigBuilder_;
                        if (singleFieldBuilderV3 == null) {
                            this.configType_ = builder.build();
                            onChanged();
                        } else {
                            singleFieldBuilderV3.setMessage(builder.build());
                        }
                        this.configTypeCase_ = 3;
                        return this;
                    }

                    public Builder mergeTypedConfig(Any any) {
                        SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> singleFieldBuilderV3 = this.typedConfigBuilder_;
                        if (singleFieldBuilderV3 == null) {
                            if (this.configTypeCase_ != 3 || this.configType_ == Any.getDefaultInstance()) {
                                this.configType_ = any;
                            } else {
                                this.configType_ = Any.newBuilder((Any) this.configType_).mergeFrom(any).buildPartial();
                            }
                            onChanged();
                        } else {
                            if (this.configTypeCase_ == 3) {
                                singleFieldBuilderV3.mergeFrom(any);
                            }
                            this.typedConfigBuilder_.setMessage(any);
                        }
                        this.configTypeCase_ = 3;
                        return this;
                    }

                    public Builder clearTypedConfig() {
                        SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> singleFieldBuilderV3 = this.typedConfigBuilder_;
                        if (singleFieldBuilderV3 != null) {
                            if (this.configTypeCase_ == 3) {
                                this.configTypeCase_ = 0;
                                this.configType_ = null;
                            }
                            singleFieldBuilderV3.clear();
                        } else if (this.configTypeCase_ == 3) {
                            this.configTypeCase_ = 0;
                            this.configType_ = null;
                            onChanged();
                        }
                        return this;
                    }

                    public Any.Builder getTypedConfigBuilder() {
                        return getTypedConfigFieldBuilder().getBuilder();
                    }

                    @Override
                    // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentials.MetadataCredentialsFromPluginOrBuilder
                    public AnyOrBuilder getTypedConfigOrBuilder() {
                        SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> singleFieldBuilderV3;
                        int i = this.configTypeCase_;
                        if (i == 3 && (singleFieldBuilderV3 = this.typedConfigBuilder_) != null) {
                            return singleFieldBuilderV3.getMessageOrBuilder();
                        }
                        if (i == 3) {
                            return (Any) this.configType_;
                        }
                        return Any.getDefaultInstance();
                    }

                    private SingleFieldBuilderV3<Any, Any.Builder, AnyOrBuilder> getTypedConfigFieldBuilder() {
                        if (this.typedConfigBuilder_ == null) {
                            if (this.configTypeCase_ != 3) {
                                this.configType_ = Any.getDefaultInstance();
                            }
                            this.typedConfigBuilder_ = new SingleFieldBuilderV3<>((Any) this.configType_, getParentForChildren(), isClean());
                            this.configType_ = null;
                        }
                        this.configTypeCase_ = 3;
                        onChanged();
                        return this.typedConfigBuilder_;
                    }

                    /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                    public final Builder m15173setUnknownFields(UnknownFieldSet unknownFieldSet) {
                        return (Builder) super.setUnknownFields(unknownFieldSet);
                    }

                    /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                    public final Builder m15167mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                        return (Builder) super.mergeUnknownFields(unknownFieldSet);
                    }
                }
            }

            public static final class StsService extends GeneratedMessageV3 implements StsServiceOrBuilder {
                public static final int ACTOR_TOKEN_PATH_FIELD_NUMBER = 8;
                public static final int ACTOR_TOKEN_TYPE_FIELD_NUMBER = 9;
                public static final int AUDIENCE_FIELD_NUMBER = 3;
                public static final int REQUESTED_TOKEN_TYPE_FIELD_NUMBER = 5;
                public static final int RESOURCE_FIELD_NUMBER = 2;
                public static final int SCOPE_FIELD_NUMBER = 4;
                public static final int SUBJECT_TOKEN_PATH_FIELD_NUMBER = 6;
                public static final int SUBJECT_TOKEN_TYPE_FIELD_NUMBER = 7;
                public static final int TOKEN_EXCHANGE_SERVICE_URI_FIELD_NUMBER = 1;
                private static final StsService DEFAULT_INSTANCE = new StsService();
                private static final Parser<StsService> PARSER = new AbstractParser<StsService>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentials.StsService.1
                    /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
                    public StsService m15228parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                        return new StsService(codedInputStream, extensionRegistryLite);
                    }
                };
                private static final long serialVersionUID = 0;
                private volatile Object actorTokenPath_;
                private volatile Object actorTokenType_;
                private volatile Object audience_;
                private byte memoizedIsInitialized;
                private volatile Object requestedTokenType_;
                private volatile Object resource_;
                private volatile Object scope_;
                private volatile Object subjectTokenPath_;
                private volatile Object subjectTokenType_;
                private volatile Object tokenExchangeServiceUri_;

                private StsService(GeneratedMessageV3.Builder<?> builder) {
                    super(builder);
                    this.memoizedIsInitialized = (byte) -1;
                }

                private StsService() {
                    this.memoizedIsInitialized = (byte) -1;
                    this.tokenExchangeServiceUri_ = "";
                    this.resource_ = "";
                    this.audience_ = "";
                    this.scope_ = "";
                    this.requestedTokenType_ = "";
                    this.subjectTokenPath_ = "";
                    this.subjectTokenType_ = "";
                    this.actorTokenPath_ = "";
                    this.actorTokenType_ = "";
                }

                private StsService(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                    this();
                    extensionRegistryLite.getClass();
                    UnknownFieldSet.Builder builderNewBuilder = UnknownFieldSet.newBuilder();
                    boolean z = false;
                    while (!z) {
                        try {
                            try {
                                try {
                                    int tag = codedInputStream.readTag();
                                    if (tag != 0) {
                                        if (tag == 10) {
                                            this.tokenExchangeServiceUri_ = codedInputStream.readStringRequireUtf8();
                                        } else if (tag == 18) {
                                            this.resource_ = codedInputStream.readStringRequireUtf8();
                                        } else if (tag == 26) {
                                            this.audience_ = codedInputStream.readStringRequireUtf8();
                                        } else if (tag == 34) {
                                            this.scope_ = codedInputStream.readStringRequireUtf8();
                                        } else if (tag == 42) {
                                            this.requestedTokenType_ = codedInputStream.readStringRequireUtf8();
                                        } else if (tag == 50) {
                                            this.subjectTokenPath_ = codedInputStream.readStringRequireUtf8();
                                        } else if (tag == 58) {
                                            this.subjectTokenType_ = codedInputStream.readStringRequireUtf8();
                                        } else if (tag == 66) {
                                            this.actorTokenPath_ = codedInputStream.readStringRequireUtf8();
                                        } else if (tag == 74) {
                                            this.actorTokenType_ = codedInputStream.readStringRequireUtf8();
                                        } else if (!parseUnknownField(codedInputStream, builderNewBuilder, extensionRegistryLite, tag)) {
                                        }
                                    }
                                    z = true;
                                } catch (InvalidProtocolBufferException e) {
                                    throw e.setUnfinishedMessage(this);
                                }
                            } catch (IOException e2) {
                                throw new InvalidProtocolBufferException(e2).setUnfinishedMessage(this);
                            }
                        } finally {
                            this.unknownFields = builderNewBuilder.build();
                            makeExtensionsImmutable();
                        }
                    }
                }

                public static StsService getDefaultInstance() {
                    return DEFAULT_INSTANCE;
                }

                public static Parser<StsService> parser() {
                    return PARSER;
                }

                public static final Descriptors.Descriptor getDescriptor() {
                    return GrpcServiceProto.internal_static_envoy_api_v2_core_GrpcService_GoogleGrpc_CallCredentials_StsService_descriptor;
                }

                public static StsService parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
                    return (StsService) PARSER.parseFrom(byteBuffer);
                }

                public static StsService parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                    return (StsService) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
                }

                public static StsService parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
                    return (StsService) PARSER.parseFrom(byteString);
                }

                public static StsService parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                    return (StsService) PARSER.parseFrom(byteString, extensionRegistryLite);
                }

                public static StsService parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
                    return (StsService) PARSER.parseFrom(bArr);
                }

                public static StsService parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
                    return (StsService) PARSER.parseFrom(bArr, extensionRegistryLite);
                }

                public static StsService parseFrom(InputStream inputStream) throws IOException {
                    return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
                }

                public static StsService parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                    return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
                }

                public static StsService parseDelimitedFrom(InputStream inputStream) throws IOException {
                    return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
                }

                public static StsService parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                    return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
                }

                public static StsService parseFrom(CodedInputStream codedInputStream) throws IOException {
                    return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
                }

                public static StsService parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
                    return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
                }

                public static Builder newBuilder() {
                    return DEFAULT_INSTANCE.m15226toBuilder();
                }

                public static Builder newBuilder(StsService stsService) {
                    return DEFAULT_INSTANCE.m15226toBuilder().mergeFrom(stsService);
                }

                /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public StsService m15221getDefaultInstanceForType() {
                    return DEFAULT_INSTANCE;
                }

                public Parser<StsService> getParserForType() {
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
                    return new StsService();
                }

                public final UnknownFieldSet getUnknownFields() {
                    return this.unknownFields;
                }

                protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                    return GrpcServiceProto.internal_static_envoy_api_v2_core_GrpcService_GoogleGrpc_CallCredentials_StsService_fieldAccessorTable.ensureFieldAccessorsInitialized(StsService.class, Builder.class);
                }

                @Override
                // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentials.StsServiceOrBuilder
                public String getTokenExchangeServiceUri() {
                    Object obj = this.tokenExchangeServiceUri_;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.tokenExchangeServiceUri_ = stringUtf8;
                    return stringUtf8;
                }

                @Override
                // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentials.StsServiceOrBuilder
                public ByteString getTokenExchangeServiceUriBytes() {
                    Object obj = this.tokenExchangeServiceUri_;
                    if (obj instanceof String) {
                        ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                        this.tokenExchangeServiceUri_ = byteStringCopyFromUtf8;
                        return byteStringCopyFromUtf8;
                    }
                    return (ByteString) obj;
                }

                @Override
                // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentials.StsServiceOrBuilder
                public String getResource() {
                    Object obj = this.resource_;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.resource_ = stringUtf8;
                    return stringUtf8;
                }

                @Override
                // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentials.StsServiceOrBuilder
                public ByteString getResourceBytes() {
                    Object obj = this.resource_;
                    if (obj instanceof String) {
                        ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                        this.resource_ = byteStringCopyFromUtf8;
                        return byteStringCopyFromUtf8;
                    }
                    return (ByteString) obj;
                }

                @Override
                // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentials.StsServiceOrBuilder
                public String getAudience() {
                    Object obj = this.audience_;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.audience_ = stringUtf8;
                    return stringUtf8;
                }

                @Override
                // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentials.StsServiceOrBuilder
                public ByteString getAudienceBytes() {
                    Object obj = this.audience_;
                    if (obj instanceof String) {
                        ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                        this.audience_ = byteStringCopyFromUtf8;
                        return byteStringCopyFromUtf8;
                    }
                    return (ByteString) obj;
                }

                @Override
                // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentials.StsServiceOrBuilder
                public String getScope() {
                    Object obj = this.scope_;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.scope_ = stringUtf8;
                    return stringUtf8;
                }

                @Override
                // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentials.StsServiceOrBuilder
                public ByteString getScopeBytes() {
                    Object obj = this.scope_;
                    if (obj instanceof String) {
                        ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                        this.scope_ = byteStringCopyFromUtf8;
                        return byteStringCopyFromUtf8;
                    }
                    return (ByteString) obj;
                }

                @Override
                // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentials.StsServiceOrBuilder
                public String getRequestedTokenType() {
                    Object obj = this.requestedTokenType_;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.requestedTokenType_ = stringUtf8;
                    return stringUtf8;
                }

                @Override
                // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentials.StsServiceOrBuilder
                public ByteString getRequestedTokenTypeBytes() {
                    Object obj = this.requestedTokenType_;
                    if (obj instanceof String) {
                        ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                        this.requestedTokenType_ = byteStringCopyFromUtf8;
                        return byteStringCopyFromUtf8;
                    }
                    return (ByteString) obj;
                }

                @Override
                // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentials.StsServiceOrBuilder
                public String getSubjectTokenPath() {
                    Object obj = this.subjectTokenPath_;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.subjectTokenPath_ = stringUtf8;
                    return stringUtf8;
                }

                @Override
                // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentials.StsServiceOrBuilder
                public ByteString getSubjectTokenPathBytes() {
                    Object obj = this.subjectTokenPath_;
                    if (obj instanceof String) {
                        ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                        this.subjectTokenPath_ = byteStringCopyFromUtf8;
                        return byteStringCopyFromUtf8;
                    }
                    return (ByteString) obj;
                }

                @Override
                // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentials.StsServiceOrBuilder
                public String getSubjectTokenType() {
                    Object obj = this.subjectTokenType_;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.subjectTokenType_ = stringUtf8;
                    return stringUtf8;
                }

                @Override
                // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentials.StsServiceOrBuilder
                public ByteString getSubjectTokenTypeBytes() {
                    Object obj = this.subjectTokenType_;
                    if (obj instanceof String) {
                        ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                        this.subjectTokenType_ = byteStringCopyFromUtf8;
                        return byteStringCopyFromUtf8;
                    }
                    return (ByteString) obj;
                }

                @Override
                // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentials.StsServiceOrBuilder
                public String getActorTokenPath() {
                    Object obj = this.actorTokenPath_;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.actorTokenPath_ = stringUtf8;
                    return stringUtf8;
                }

                @Override
                // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentials.StsServiceOrBuilder
                public ByteString getActorTokenPathBytes() {
                    Object obj = this.actorTokenPath_;
                    if (obj instanceof String) {
                        ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                        this.actorTokenPath_ = byteStringCopyFromUtf8;
                        return byteStringCopyFromUtf8;
                    }
                    return (ByteString) obj;
                }

                @Override
                // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentials.StsServiceOrBuilder
                public String getActorTokenType() {
                    Object obj = this.actorTokenType_;
                    if (obj instanceof String) {
                        return (String) obj;
                    }
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.actorTokenType_ = stringUtf8;
                    return stringUtf8;
                }

                @Override
                // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentials.StsServiceOrBuilder
                public ByteString getActorTokenTypeBytes() {
                    Object obj = this.actorTokenType_;
                    if (obj instanceof String) {
                        ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                        this.actorTokenType_ = byteStringCopyFromUtf8;
                        return byteStringCopyFromUtf8;
                    }
                    return (ByteString) obj;
                }

                public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
                    if (!getTokenExchangeServiceUriBytes().isEmpty()) {
                        GeneratedMessageV3.writeString(codedOutputStream, 1, this.tokenExchangeServiceUri_);
                    }
                    if (!getResourceBytes().isEmpty()) {
                        GeneratedMessageV3.writeString(codedOutputStream, 2, this.resource_);
                    }
                    if (!getAudienceBytes().isEmpty()) {
                        GeneratedMessageV3.writeString(codedOutputStream, 3, this.audience_);
                    }
                    if (!getScopeBytes().isEmpty()) {
                        GeneratedMessageV3.writeString(codedOutputStream, 4, this.scope_);
                    }
                    if (!getRequestedTokenTypeBytes().isEmpty()) {
                        GeneratedMessageV3.writeString(codedOutputStream, 5, this.requestedTokenType_);
                    }
                    if (!getSubjectTokenPathBytes().isEmpty()) {
                        GeneratedMessageV3.writeString(codedOutputStream, 6, this.subjectTokenPath_);
                    }
                    if (!getSubjectTokenTypeBytes().isEmpty()) {
                        GeneratedMessageV3.writeString(codedOutputStream, 7, this.subjectTokenType_);
                    }
                    if (!getActorTokenPathBytes().isEmpty()) {
                        GeneratedMessageV3.writeString(codedOutputStream, 8, this.actorTokenPath_);
                    }
                    if (!getActorTokenTypeBytes().isEmpty()) {
                        GeneratedMessageV3.writeString(codedOutputStream, 9, this.actorTokenType_);
                    }
                    this.unknownFields.writeTo(codedOutputStream);
                }

                public int getSerializedSize() {
                    int i = this.memoizedSize;
                    if (i != -1) {
                        return i;
                    }
                    int iComputeStringSize = !getTokenExchangeServiceUriBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.tokenExchangeServiceUri_) : 0;
                    if (!getResourceBytes().isEmpty()) {
                        iComputeStringSize += GeneratedMessageV3.computeStringSize(2, this.resource_);
                    }
                    if (!getAudienceBytes().isEmpty()) {
                        iComputeStringSize += GeneratedMessageV3.computeStringSize(3, this.audience_);
                    }
                    if (!getScopeBytes().isEmpty()) {
                        iComputeStringSize += GeneratedMessageV3.computeStringSize(4, this.scope_);
                    }
                    if (!getRequestedTokenTypeBytes().isEmpty()) {
                        iComputeStringSize += GeneratedMessageV3.computeStringSize(5, this.requestedTokenType_);
                    }
                    if (!getSubjectTokenPathBytes().isEmpty()) {
                        iComputeStringSize += GeneratedMessageV3.computeStringSize(6, this.subjectTokenPath_);
                    }
                    if (!getSubjectTokenTypeBytes().isEmpty()) {
                        iComputeStringSize += GeneratedMessageV3.computeStringSize(7, this.subjectTokenType_);
                    }
                    if (!getActorTokenPathBytes().isEmpty()) {
                        iComputeStringSize += GeneratedMessageV3.computeStringSize(8, this.actorTokenPath_);
                    }
                    if (!getActorTokenTypeBytes().isEmpty()) {
                        iComputeStringSize += GeneratedMessageV3.computeStringSize(9, this.actorTokenType_);
                    }
                    int serializedSize = iComputeStringSize + this.unknownFields.getSerializedSize();
                    this.memoizedSize = serializedSize;
                    return serializedSize;
                }

                public boolean equals(Object obj) {
                    if (obj == this) {
                        return true;
                    }
                    if (!(obj instanceof StsService)) {
                        return super.equals(obj);
                    }
                    StsService stsService = (StsService) obj;
                    return getTokenExchangeServiceUri().equals(stsService.getTokenExchangeServiceUri()) && getResource().equals(stsService.getResource()) && getAudience().equals(stsService.getAudience()) && getScope().equals(stsService.getScope()) && getRequestedTokenType().equals(stsService.getRequestedTokenType()) && getSubjectTokenPath().equals(stsService.getSubjectTokenPath()) && getSubjectTokenType().equals(stsService.getSubjectTokenType()) && getActorTokenPath().equals(stsService.getActorTokenPath()) && getActorTokenType().equals(stsService.getActorTokenType()) && this.unknownFields.equals(stsService.unknownFields);
                }

                public int hashCode() {
                    if (this.memoizedHashCode != 0) {
                        return this.memoizedHashCode;
                    }
                    int iHashCode = ((((((((((((((((((((((((((((((((((((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + getTokenExchangeServiceUri().hashCode()) * 37) + 2) * 53) + getResource().hashCode()) * 37) + 3) * 53) + getAudience().hashCode()) * 37) + 4) * 53) + getScope().hashCode()) * 37) + 5) * 53) + getRequestedTokenType().hashCode()) * 37) + 6) * 53) + getSubjectTokenPath().hashCode()) * 37) + 7) * 53) + getSubjectTokenType().hashCode()) * 37) + 8) * 53) + getActorTokenPath().hashCode()) * 37) + 9) * 53) + getActorTokenType().hashCode()) * 29) + this.unknownFields.hashCode();
                    this.memoizedHashCode = iHashCode;
                    return iHashCode;
                }

                /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m15223newBuilderForType() {
                    return newBuilder();
                }

                /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m15226toBuilder() {
                    return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
                }

                /* JADX INFO: Access modifiers changed from: protected */
                public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
                    return new Builder(builderParent);
                }

                public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements StsServiceOrBuilder {
                    private Object actorTokenPath_;
                    private Object actorTokenType_;
                    private Object audience_;
                    private Object requestedTokenType_;
                    private Object resource_;
                    private Object scope_;
                    private Object subjectTokenPath_;
                    private Object subjectTokenType_;
                    private Object tokenExchangeServiceUri_;

                    private Builder() {
                        this.tokenExchangeServiceUri_ = "";
                        this.resource_ = "";
                        this.audience_ = "";
                        this.scope_ = "";
                        this.requestedTokenType_ = "";
                        this.subjectTokenPath_ = "";
                        this.subjectTokenType_ = "";
                        this.actorTokenPath_ = "";
                        this.actorTokenType_ = "";
                        maybeForceBuilderInitialization();
                    }

                    private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                        super(builderParent);
                        this.tokenExchangeServiceUri_ = "";
                        this.resource_ = "";
                        this.audience_ = "";
                        this.scope_ = "";
                        this.requestedTokenType_ = "";
                        this.subjectTokenPath_ = "";
                        this.subjectTokenType_ = "";
                        this.actorTokenPath_ = "";
                        this.actorTokenType_ = "";
                        maybeForceBuilderInitialization();
                    }

                    public static final Descriptors.Descriptor getDescriptor() {
                        return GrpcServiceProto.internal_static_envoy_api_v2_core_GrpcService_GoogleGrpc_CallCredentials_StsService_descriptor;
                    }

                    public final boolean isInitialized() {
                        return true;
                    }

                    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                        return GrpcServiceProto.internal_static_envoy_api_v2_core_GrpcService_GoogleGrpc_CallCredentials_StsService_fieldAccessorTable.ensureFieldAccessorsInitialized(StsService.class, Builder.class);
                    }

                    private void maybeForceBuilderInitialization() {
                        boolean unused = StsService.alwaysUseFieldBuilders;
                    }

                    /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                    public Builder m15237clear() {
                        super.clear();
                        this.tokenExchangeServiceUri_ = "";
                        this.resource_ = "";
                        this.audience_ = "";
                        this.scope_ = "";
                        this.requestedTokenType_ = "";
                        this.subjectTokenPath_ = "";
                        this.subjectTokenType_ = "";
                        this.actorTokenPath_ = "";
                        this.actorTokenType_ = "";
                        return this;
                    }

                    public Descriptors.Descriptor getDescriptorForType() {
                        return GrpcServiceProto.internal_static_envoy_api_v2_core_GrpcService_GoogleGrpc_CallCredentials_StsService_descriptor;
                    }

                    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                    public StsService m15250getDefaultInstanceForType() {
                        return StsService.getDefaultInstance();
                    }

                    /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
                    /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                    public StsService m15231build() throws UninitializedMessageException {
                        StsService stsServiceM15233buildPartial = m15233buildPartial();
                        if (stsServiceM15233buildPartial.isInitialized()) {
                            return stsServiceM15233buildPartial;
                        }
                        throw newUninitializedMessageException(stsServiceM15233buildPartial);
                    }

                    /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                    public StsService m15233buildPartial() {
                        StsService stsService = new StsService(this);
                        stsService.tokenExchangeServiceUri_ = this.tokenExchangeServiceUri_;
                        stsService.resource_ = this.resource_;
                        stsService.audience_ = this.audience_;
                        stsService.scope_ = this.scope_;
                        stsService.requestedTokenType_ = this.requestedTokenType_;
                        stsService.subjectTokenPath_ = this.subjectTokenPath_;
                        stsService.subjectTokenType_ = this.subjectTokenType_;
                        stsService.actorTokenPath_ = this.actorTokenPath_;
                        stsService.actorTokenType_ = this.actorTokenType_;
                        onBuilt();
                        return stsService;
                    }

                    /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                    public Builder m15249clone() {
                        return (Builder) super.clone();
                    }

                    /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                    public Builder m15261setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                        return (Builder) super.setField(fieldDescriptor, obj);
                    }

                    /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                    public Builder m15239clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                        return (Builder) super.clearField(fieldDescriptor);
                    }

                    /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                    public Builder m15242clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                        return (Builder) super.clearOneof(oneofDescriptor);
                    }

                    /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                    public Builder m15263setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                        return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
                    }

                    /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                    public Builder m15229addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                        return (Builder) super.addRepeatedField(fieldDescriptor, obj);
                    }

                    /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                    public Builder m15254mergeFrom(Message message) {
                        if (message instanceof StsService) {
                            return mergeFrom((StsService) message);
                        }
                        super.mergeFrom(message);
                        return this;
                    }

                    public Builder mergeFrom(StsService stsService) {
                        if (stsService == StsService.getDefaultInstance()) {
                            return this;
                        }
                        if (!stsService.getTokenExchangeServiceUri().isEmpty()) {
                            this.tokenExchangeServiceUri_ = stsService.tokenExchangeServiceUri_;
                            onChanged();
                        }
                        if (!stsService.getResource().isEmpty()) {
                            this.resource_ = stsService.resource_;
                            onChanged();
                        }
                        if (!stsService.getAudience().isEmpty()) {
                            this.audience_ = stsService.audience_;
                            onChanged();
                        }
                        if (!stsService.getScope().isEmpty()) {
                            this.scope_ = stsService.scope_;
                            onChanged();
                        }
                        if (!stsService.getRequestedTokenType().isEmpty()) {
                            this.requestedTokenType_ = stsService.requestedTokenType_;
                            onChanged();
                        }
                        if (!stsService.getSubjectTokenPath().isEmpty()) {
                            this.subjectTokenPath_ = stsService.subjectTokenPath_;
                            onChanged();
                        }
                        if (!stsService.getSubjectTokenType().isEmpty()) {
                            this.subjectTokenType_ = stsService.subjectTokenType_;
                            onChanged();
                        }
                        if (!stsService.getActorTokenPath().isEmpty()) {
                            this.actorTokenPath_ = stsService.actorTokenPath_;
                            onChanged();
                        }
                        if (!stsService.getActorTokenType().isEmpty()) {
                            this.actorTokenType_ = stsService.actorTokenType_;
                            onChanged();
                        }
                        m15259mergeUnknownFields(stsService.unknownFields);
                        onChanged();
                        return this;
                    }

                    /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
                    /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                    /*
                        Code decompiled incorrectly, please refer to instructions dump.
                        To view partially-correct add '--show-bad-code' argument
                    */
                    public io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentials.StsService.Builder m15255mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
                        /*
                            r2 = this;
                            r0 = 0
                            com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentials.StsService.access$8100()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                            java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                            io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService$GoogleGrpc$CallCredentials$StsService r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentials.StsService) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                            io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService$GoogleGrpc$CallCredentials$StsService r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentials.StsService) r4     // Catch: java.lang.Throwable -> L11
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
                        throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentials.StsService.Builder.m15255mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService$GoogleGrpc$CallCredentials$StsService$Builder");
                    }

                    @Override
                    // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentials.StsServiceOrBuilder
                    public String getTokenExchangeServiceUri() {
                        Object obj = this.tokenExchangeServiceUri_;
                        if (!(obj instanceof String)) {
                            String stringUtf8 = ((ByteString) obj).toStringUtf8();
                            this.tokenExchangeServiceUri_ = stringUtf8;
                            return stringUtf8;
                        }
                        return (String) obj;
                    }

                    public Builder setTokenExchangeServiceUri(String str) {
                        str.getClass();
                        this.tokenExchangeServiceUri_ = str;
                        onChanged();
                        return this;
                    }

                    @Override
                    // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentials.StsServiceOrBuilder
                    public ByteString getTokenExchangeServiceUriBytes() {
                        Object obj = this.tokenExchangeServiceUri_;
                        if (obj instanceof String) {
                            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                            this.tokenExchangeServiceUri_ = byteStringCopyFromUtf8;
                            return byteStringCopyFromUtf8;
                        }
                        return (ByteString) obj;
                    }

                    public Builder setTokenExchangeServiceUriBytes(ByteString byteString) {
                        byteString.getClass();
                        StsService.checkByteStringIsUtf8(byteString);
                        this.tokenExchangeServiceUri_ = byteString;
                        onChanged();
                        return this;
                    }

                    public Builder clearTokenExchangeServiceUri() {
                        this.tokenExchangeServiceUri_ = StsService.getDefaultInstance().getTokenExchangeServiceUri();
                        onChanged();
                        return this;
                    }

                    @Override
                    // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentials.StsServiceOrBuilder
                    public String getResource() {
                        Object obj = this.resource_;
                        if (!(obj instanceof String)) {
                            String stringUtf8 = ((ByteString) obj).toStringUtf8();
                            this.resource_ = stringUtf8;
                            return stringUtf8;
                        }
                        return (String) obj;
                    }

                    public Builder setResource(String str) {
                        str.getClass();
                        this.resource_ = str;
                        onChanged();
                        return this;
                    }

                    @Override
                    // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentials.StsServiceOrBuilder
                    public ByteString getResourceBytes() {
                        Object obj = this.resource_;
                        if (obj instanceof String) {
                            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                            this.resource_ = byteStringCopyFromUtf8;
                            return byteStringCopyFromUtf8;
                        }
                        return (ByteString) obj;
                    }

                    public Builder setResourceBytes(ByteString byteString) {
                        byteString.getClass();
                        StsService.checkByteStringIsUtf8(byteString);
                        this.resource_ = byteString;
                        onChanged();
                        return this;
                    }

                    public Builder clearResource() {
                        this.resource_ = StsService.getDefaultInstance().getResource();
                        onChanged();
                        return this;
                    }

                    @Override
                    // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentials.StsServiceOrBuilder
                    public String getAudience() {
                        Object obj = this.audience_;
                        if (!(obj instanceof String)) {
                            String stringUtf8 = ((ByteString) obj).toStringUtf8();
                            this.audience_ = stringUtf8;
                            return stringUtf8;
                        }
                        return (String) obj;
                    }

                    public Builder setAudience(String str) {
                        str.getClass();
                        this.audience_ = str;
                        onChanged();
                        return this;
                    }

                    @Override
                    // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentials.StsServiceOrBuilder
                    public ByteString getAudienceBytes() {
                        Object obj = this.audience_;
                        if (obj instanceof String) {
                            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                            this.audience_ = byteStringCopyFromUtf8;
                            return byteStringCopyFromUtf8;
                        }
                        return (ByteString) obj;
                    }

                    public Builder setAudienceBytes(ByteString byteString) {
                        byteString.getClass();
                        StsService.checkByteStringIsUtf8(byteString);
                        this.audience_ = byteString;
                        onChanged();
                        return this;
                    }

                    public Builder clearAudience() {
                        this.audience_ = StsService.getDefaultInstance().getAudience();
                        onChanged();
                        return this;
                    }

                    @Override
                    // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentials.StsServiceOrBuilder
                    public String getScope() {
                        Object obj = this.scope_;
                        if (!(obj instanceof String)) {
                            String stringUtf8 = ((ByteString) obj).toStringUtf8();
                            this.scope_ = stringUtf8;
                            return stringUtf8;
                        }
                        return (String) obj;
                    }

                    public Builder setScope(String str) {
                        str.getClass();
                        this.scope_ = str;
                        onChanged();
                        return this;
                    }

                    @Override
                    // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentials.StsServiceOrBuilder
                    public ByteString getScopeBytes() {
                        Object obj = this.scope_;
                        if (obj instanceof String) {
                            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                            this.scope_ = byteStringCopyFromUtf8;
                            return byteStringCopyFromUtf8;
                        }
                        return (ByteString) obj;
                    }

                    public Builder setScopeBytes(ByteString byteString) {
                        byteString.getClass();
                        StsService.checkByteStringIsUtf8(byteString);
                        this.scope_ = byteString;
                        onChanged();
                        return this;
                    }

                    public Builder clearScope() {
                        this.scope_ = StsService.getDefaultInstance().getScope();
                        onChanged();
                        return this;
                    }

                    @Override
                    // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentials.StsServiceOrBuilder
                    public String getRequestedTokenType() {
                        Object obj = this.requestedTokenType_;
                        if (!(obj instanceof String)) {
                            String stringUtf8 = ((ByteString) obj).toStringUtf8();
                            this.requestedTokenType_ = stringUtf8;
                            return stringUtf8;
                        }
                        return (String) obj;
                    }

                    public Builder setRequestedTokenType(String str) {
                        str.getClass();
                        this.requestedTokenType_ = str;
                        onChanged();
                        return this;
                    }

                    @Override
                    // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentials.StsServiceOrBuilder
                    public ByteString getRequestedTokenTypeBytes() {
                        Object obj = this.requestedTokenType_;
                        if (obj instanceof String) {
                            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                            this.requestedTokenType_ = byteStringCopyFromUtf8;
                            return byteStringCopyFromUtf8;
                        }
                        return (ByteString) obj;
                    }

                    public Builder setRequestedTokenTypeBytes(ByteString byteString) {
                        byteString.getClass();
                        StsService.checkByteStringIsUtf8(byteString);
                        this.requestedTokenType_ = byteString;
                        onChanged();
                        return this;
                    }

                    public Builder clearRequestedTokenType() {
                        this.requestedTokenType_ = StsService.getDefaultInstance().getRequestedTokenType();
                        onChanged();
                        return this;
                    }

                    @Override
                    // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentials.StsServiceOrBuilder
                    public String getSubjectTokenPath() {
                        Object obj = this.subjectTokenPath_;
                        if (!(obj instanceof String)) {
                            String stringUtf8 = ((ByteString) obj).toStringUtf8();
                            this.subjectTokenPath_ = stringUtf8;
                            return stringUtf8;
                        }
                        return (String) obj;
                    }

                    public Builder setSubjectTokenPath(String str) {
                        str.getClass();
                        this.subjectTokenPath_ = str;
                        onChanged();
                        return this;
                    }

                    @Override
                    // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentials.StsServiceOrBuilder
                    public ByteString getSubjectTokenPathBytes() {
                        Object obj = this.subjectTokenPath_;
                        if (obj instanceof String) {
                            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                            this.subjectTokenPath_ = byteStringCopyFromUtf8;
                            return byteStringCopyFromUtf8;
                        }
                        return (ByteString) obj;
                    }

                    public Builder setSubjectTokenPathBytes(ByteString byteString) {
                        byteString.getClass();
                        StsService.checkByteStringIsUtf8(byteString);
                        this.subjectTokenPath_ = byteString;
                        onChanged();
                        return this;
                    }

                    public Builder clearSubjectTokenPath() {
                        this.subjectTokenPath_ = StsService.getDefaultInstance().getSubjectTokenPath();
                        onChanged();
                        return this;
                    }

                    @Override
                    // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentials.StsServiceOrBuilder
                    public String getSubjectTokenType() {
                        Object obj = this.subjectTokenType_;
                        if (!(obj instanceof String)) {
                            String stringUtf8 = ((ByteString) obj).toStringUtf8();
                            this.subjectTokenType_ = stringUtf8;
                            return stringUtf8;
                        }
                        return (String) obj;
                    }

                    public Builder setSubjectTokenType(String str) {
                        str.getClass();
                        this.subjectTokenType_ = str;
                        onChanged();
                        return this;
                    }

                    @Override
                    // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentials.StsServiceOrBuilder
                    public ByteString getSubjectTokenTypeBytes() {
                        Object obj = this.subjectTokenType_;
                        if (obj instanceof String) {
                            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                            this.subjectTokenType_ = byteStringCopyFromUtf8;
                            return byteStringCopyFromUtf8;
                        }
                        return (ByteString) obj;
                    }

                    public Builder setSubjectTokenTypeBytes(ByteString byteString) {
                        byteString.getClass();
                        StsService.checkByteStringIsUtf8(byteString);
                        this.subjectTokenType_ = byteString;
                        onChanged();
                        return this;
                    }

                    public Builder clearSubjectTokenType() {
                        this.subjectTokenType_ = StsService.getDefaultInstance().getSubjectTokenType();
                        onChanged();
                        return this;
                    }

                    @Override
                    // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentials.StsServiceOrBuilder
                    public String getActorTokenPath() {
                        Object obj = this.actorTokenPath_;
                        if (!(obj instanceof String)) {
                            String stringUtf8 = ((ByteString) obj).toStringUtf8();
                            this.actorTokenPath_ = stringUtf8;
                            return stringUtf8;
                        }
                        return (String) obj;
                    }

                    public Builder setActorTokenPath(String str) {
                        str.getClass();
                        this.actorTokenPath_ = str;
                        onChanged();
                        return this;
                    }

                    @Override
                    // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentials.StsServiceOrBuilder
                    public ByteString getActorTokenPathBytes() {
                        Object obj = this.actorTokenPath_;
                        if (obj instanceof String) {
                            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                            this.actorTokenPath_ = byteStringCopyFromUtf8;
                            return byteStringCopyFromUtf8;
                        }
                        return (ByteString) obj;
                    }

                    public Builder setActorTokenPathBytes(ByteString byteString) {
                        byteString.getClass();
                        StsService.checkByteStringIsUtf8(byteString);
                        this.actorTokenPath_ = byteString;
                        onChanged();
                        return this;
                    }

                    public Builder clearActorTokenPath() {
                        this.actorTokenPath_ = StsService.getDefaultInstance().getActorTokenPath();
                        onChanged();
                        return this;
                    }

                    @Override
                    // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentials.StsServiceOrBuilder
                    public String getActorTokenType() {
                        Object obj = this.actorTokenType_;
                        if (!(obj instanceof String)) {
                            String stringUtf8 = ((ByteString) obj).toStringUtf8();
                            this.actorTokenType_ = stringUtf8;
                            return stringUtf8;
                        }
                        return (String) obj;
                    }

                    public Builder setActorTokenType(String str) {
                        str.getClass();
                        this.actorTokenType_ = str;
                        onChanged();
                        return this;
                    }

                    @Override
                    // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentials.StsServiceOrBuilder
                    public ByteString getActorTokenTypeBytes() {
                        Object obj = this.actorTokenType_;
                        if (obj instanceof String) {
                            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                            this.actorTokenType_ = byteStringCopyFromUtf8;
                            return byteStringCopyFromUtf8;
                        }
                        return (ByteString) obj;
                    }

                    public Builder setActorTokenTypeBytes(ByteString byteString) {
                        byteString.getClass();
                        StsService.checkByteStringIsUtf8(byteString);
                        this.actorTokenType_ = byteString;
                        onChanged();
                        return this;
                    }

                    public Builder clearActorTokenType() {
                        this.actorTokenType_ = StsService.getDefaultInstance().getActorTokenType();
                        onChanged();
                        return this;
                    }

                    /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                    public final Builder m15265setUnknownFields(UnknownFieldSet unknownFieldSet) {
                        return (Builder) super.setUnknownFields(unknownFieldSet);
                    }

                    /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                    public final Builder m15259mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                        return (Builder) super.mergeUnknownFields(unknownFieldSet);
                    }
                }
            }

            public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements CallCredentialsOrBuilder {
                private int credentialSpecifierCase_;
                private Object credentialSpecifier_;
                private SingleFieldBuilderV3<MetadataCredentialsFromPlugin, MetadataCredentialsFromPlugin.Builder, MetadataCredentialsFromPluginOrBuilder> fromPluginBuilder_;
                private SingleFieldBuilderV3<Empty, Empty.Builder, EmptyOrBuilder> googleComputeEngineBuilder_;
                private SingleFieldBuilderV3<GoogleIAMCredentials, GoogleIAMCredentials.Builder, GoogleIAMCredentialsOrBuilder> googleIamBuilder_;
                private SingleFieldBuilderV3<ServiceAccountJWTAccessCredentials, ServiceAccountJWTAccessCredentials.Builder, ServiceAccountJWTAccessCredentialsOrBuilder> serviceAccountJwtAccessBuilder_;
                private SingleFieldBuilderV3<StsService, StsService.Builder, StsServiceOrBuilder> stsServiceBuilder_;

                private Builder() {
                    this.credentialSpecifierCase_ = 0;
                    maybeForceBuilderInitialization();
                }

                private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                    super(builderParent);
                    this.credentialSpecifierCase_ = 0;
                    maybeForceBuilderInitialization();
                }

                public static final Descriptors.Descriptor getDescriptor() {
                    return GrpcServiceProto.internal_static_envoy_api_v2_core_GrpcService_GoogleGrpc_CallCredentials_descriptor;
                }

                @Override
                // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentialsOrBuilder
                public boolean hasFromPlugin() {
                    return this.credentialSpecifierCase_ == 6;
                }

                @Override
                // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentialsOrBuilder
                public boolean hasGoogleComputeEngine() {
                    return this.credentialSpecifierCase_ == 2;
                }

                @Override
                // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentialsOrBuilder
                public boolean hasGoogleIam() {
                    return this.credentialSpecifierCase_ == 5;
                }

                @Override
                // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentialsOrBuilder
                public boolean hasServiceAccountJwtAccess() {
                    return this.credentialSpecifierCase_ == 4;
                }

                @Override
                // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentialsOrBuilder
                public boolean hasStsService() {
                    return this.credentialSpecifierCase_ == 7;
                }

                public final boolean isInitialized() {
                    return true;
                }

                protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                    return GrpcServiceProto.internal_static_envoy_api_v2_core_GrpcService_GoogleGrpc_CallCredentials_fieldAccessorTable.ensureFieldAccessorsInitialized(CallCredentials.class, Builder.class);
                }

                private void maybeForceBuilderInitialization() {
                    boolean unused = CallCredentials.alwaysUseFieldBuilders;
                }

                /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m15053clear() {
                    super.clear();
                    this.credentialSpecifierCase_ = 0;
                    this.credentialSpecifier_ = null;
                    return this;
                }

                public Descriptors.Descriptor getDescriptorForType() {
                    return GrpcServiceProto.internal_static_envoy_api_v2_core_GrpcService_GoogleGrpc_CallCredentials_descriptor;
                }

                /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public CallCredentials m15066getDefaultInstanceForType() {
                    return CallCredentials.getDefaultInstance();
                }

                /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
                /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public CallCredentials m15047build() throws UninitializedMessageException {
                    CallCredentials callCredentialsM15049buildPartial = m15049buildPartial();
                    if (callCredentialsM15049buildPartial.isInitialized()) {
                        return callCredentialsM15049buildPartial;
                    }
                    throw newUninitializedMessageException(callCredentialsM15049buildPartial);
                }

                /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public CallCredentials m15049buildPartial() {
                    CallCredentials callCredentials = new CallCredentials(this);
                    if (this.credentialSpecifierCase_ == 1) {
                        callCredentials.credentialSpecifier_ = this.credentialSpecifier_;
                    }
                    if (this.credentialSpecifierCase_ == 2) {
                        SingleFieldBuilderV3<Empty, Empty.Builder, EmptyOrBuilder> singleFieldBuilderV3 = this.googleComputeEngineBuilder_;
                        if (singleFieldBuilderV3 == null) {
                            callCredentials.credentialSpecifier_ = this.credentialSpecifier_;
                        } else {
                            callCredentials.credentialSpecifier_ = singleFieldBuilderV3.build();
                        }
                    }
                    if (this.credentialSpecifierCase_ == 3) {
                        callCredentials.credentialSpecifier_ = this.credentialSpecifier_;
                    }
                    if (this.credentialSpecifierCase_ == 4) {
                        SingleFieldBuilderV3<ServiceAccountJWTAccessCredentials, ServiceAccountJWTAccessCredentials.Builder, ServiceAccountJWTAccessCredentialsOrBuilder> singleFieldBuilderV32 = this.serviceAccountJwtAccessBuilder_;
                        if (singleFieldBuilderV32 == null) {
                            callCredentials.credentialSpecifier_ = this.credentialSpecifier_;
                        } else {
                            callCredentials.credentialSpecifier_ = singleFieldBuilderV32.build();
                        }
                    }
                    if (this.credentialSpecifierCase_ == 5) {
                        SingleFieldBuilderV3<GoogleIAMCredentials, GoogleIAMCredentials.Builder, GoogleIAMCredentialsOrBuilder> singleFieldBuilderV33 = this.googleIamBuilder_;
                        if (singleFieldBuilderV33 == null) {
                            callCredentials.credentialSpecifier_ = this.credentialSpecifier_;
                        } else {
                            callCredentials.credentialSpecifier_ = singleFieldBuilderV33.build();
                        }
                    }
                    if (this.credentialSpecifierCase_ == 6) {
                        SingleFieldBuilderV3<MetadataCredentialsFromPlugin, MetadataCredentialsFromPlugin.Builder, MetadataCredentialsFromPluginOrBuilder> singleFieldBuilderV34 = this.fromPluginBuilder_;
                        if (singleFieldBuilderV34 == null) {
                            callCredentials.credentialSpecifier_ = this.credentialSpecifier_;
                        } else {
                            callCredentials.credentialSpecifier_ = singleFieldBuilderV34.build();
                        }
                    }
                    if (this.credentialSpecifierCase_ == 7) {
                        SingleFieldBuilderV3<StsService, StsService.Builder, StsServiceOrBuilder> singleFieldBuilderV35 = this.stsServiceBuilder_;
                        if (singleFieldBuilderV35 == null) {
                            callCredentials.credentialSpecifier_ = this.credentialSpecifier_;
                        } else {
                            callCredentials.credentialSpecifier_ = singleFieldBuilderV35.build();
                        }
                    }
                    callCredentials.credentialSpecifierCase_ = this.credentialSpecifierCase_;
                    onBuilt();
                    return callCredentials;
                }

                /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m15065clone() {
                    return (Builder) super.clone();
                }

                /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m15077setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                    return (Builder) super.setField(fieldDescriptor, obj);
                }

                /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m15055clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                    return (Builder) super.clearField(fieldDescriptor);
                }

                /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m15058clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                    return (Builder) super.clearOneof(oneofDescriptor);
                }

                /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m15079setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                    return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
                }

                /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m15045addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                    return (Builder) super.addRepeatedField(fieldDescriptor, obj);
                }

                /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public Builder m15070mergeFrom(Message message) {
                    if (message instanceof CallCredentials) {
                        return mergeFrom((CallCredentials) message);
                    }
                    super.mergeFrom(message);
                    return this;
                }

                public Builder mergeFrom(CallCredentials callCredentials) {
                    if (callCredentials == CallCredentials.getDefaultInstance()) {
                        return this;
                    }
                    switch (AnonymousClass2.$SwitchMap$io$envoyproxy$envoy$api$v2$core$GrpcService$GoogleGrpc$CallCredentials$CredentialSpecifierCase[callCredentials.getCredentialSpecifierCase().ordinal()]) {
                        case 1:
                            this.credentialSpecifierCase_ = 1;
                            this.credentialSpecifier_ = callCredentials.credentialSpecifier_;
                            onChanged();
                            break;
                        case 2:
                            mergeGoogleComputeEngine(callCredentials.getGoogleComputeEngine());
                            break;
                        case 3:
                            this.credentialSpecifierCase_ = 3;
                            this.credentialSpecifier_ = callCredentials.credentialSpecifier_;
                            onChanged();
                            break;
                        case 4:
                            mergeServiceAccountJwtAccess(callCredentials.getServiceAccountJwtAccess());
                            break;
                        case 5:
                            mergeGoogleIam(callCredentials.getGoogleIam());
                            break;
                        case 6:
                            mergeFromPlugin(callCredentials.getFromPlugin());
                            break;
                        case 7:
                            mergeStsService(callCredentials.getStsService());
                            break;
                    }
                    m15075mergeUnknownFields(callCredentials.unknownFields);
                    onChanged();
                    return this;
                }

                /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
                /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentials.Builder m15071mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
                    /*
                        r2 = this;
                        r0 = 0
                        com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentials.access$9900()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                        java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                        io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService$GoogleGrpc$CallCredentials r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentials) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                        io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService$GoogleGrpc$CallCredentials r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentials) r4     // Catch: java.lang.Throwable -> L11
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
                    throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentials.Builder.m15071mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService$GoogleGrpc$CallCredentials$Builder");
                }

                @Override
                // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentialsOrBuilder
                public CredentialSpecifierCase getCredentialSpecifierCase() {
                    return CredentialSpecifierCase.forNumber(this.credentialSpecifierCase_);
                }

                public Builder clearCredentialSpecifier() {
                    this.credentialSpecifierCase_ = 0;
                    this.credentialSpecifier_ = null;
                    onChanged();
                    return this;
                }

                @Override
                // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentialsOrBuilder
                public String getAccessToken() {
                    String str = this.credentialSpecifierCase_ == 1 ? this.credentialSpecifier_ : "";
                    if (!(str instanceof String)) {
                        String stringUtf8 = ((ByteString) str).toStringUtf8();
                        if (this.credentialSpecifierCase_ == 1) {
                            this.credentialSpecifier_ = stringUtf8;
                        }
                        return stringUtf8;
                    }
                    return (String) str;
                }

                public Builder setAccessToken(String str) {
                    str.getClass();
                    this.credentialSpecifierCase_ = 1;
                    this.credentialSpecifier_ = str;
                    onChanged();
                    return this;
                }

                @Override
                // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentialsOrBuilder
                public ByteString getAccessTokenBytes() {
                    String str = this.credentialSpecifierCase_ == 1 ? this.credentialSpecifier_ : "";
                    if (str instanceof String) {
                        ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) str);
                        if (this.credentialSpecifierCase_ == 1) {
                            this.credentialSpecifier_ = byteStringCopyFromUtf8;
                        }
                        return byteStringCopyFromUtf8;
                    }
                    return (ByteString) str;
                }

                public Builder setAccessTokenBytes(ByteString byteString) {
                    byteString.getClass();
                    CallCredentials.checkByteStringIsUtf8(byteString);
                    this.credentialSpecifierCase_ = 1;
                    this.credentialSpecifier_ = byteString;
                    onChanged();
                    return this;
                }

                public Builder clearAccessToken() {
                    if (this.credentialSpecifierCase_ == 1) {
                        this.credentialSpecifierCase_ = 0;
                        this.credentialSpecifier_ = null;
                        onChanged();
                    }
                    return this;
                }

                @Override
                // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentialsOrBuilder
                public Empty getGoogleComputeEngine() {
                    SingleFieldBuilderV3<Empty, Empty.Builder, EmptyOrBuilder> singleFieldBuilderV3 = this.googleComputeEngineBuilder_;
                    if (singleFieldBuilderV3 == null) {
                        if (this.credentialSpecifierCase_ == 2) {
                            return (Empty) this.credentialSpecifier_;
                        }
                        return Empty.getDefaultInstance();
                    }
                    if (this.credentialSpecifierCase_ == 2) {
                        return singleFieldBuilderV3.getMessage();
                    }
                    return Empty.getDefaultInstance();
                }

                public Builder setGoogleComputeEngine(Empty empty) {
                    SingleFieldBuilderV3<Empty, Empty.Builder, EmptyOrBuilder> singleFieldBuilderV3 = this.googleComputeEngineBuilder_;
                    if (singleFieldBuilderV3 == null) {
                        empty.getClass();
                        this.credentialSpecifier_ = empty;
                        onChanged();
                    } else {
                        singleFieldBuilderV3.setMessage(empty);
                    }
                    this.credentialSpecifierCase_ = 2;
                    return this;
                }

                public Builder setGoogleComputeEngine(Empty.Builder builder) {
                    SingleFieldBuilderV3<Empty, Empty.Builder, EmptyOrBuilder> singleFieldBuilderV3 = this.googleComputeEngineBuilder_;
                    if (singleFieldBuilderV3 == null) {
                        this.credentialSpecifier_ = builder.build();
                        onChanged();
                    } else {
                        singleFieldBuilderV3.setMessage(builder.build());
                    }
                    this.credentialSpecifierCase_ = 2;
                    return this;
                }

                public Builder mergeGoogleComputeEngine(Empty empty) {
                    SingleFieldBuilderV3<Empty, Empty.Builder, EmptyOrBuilder> singleFieldBuilderV3 = this.googleComputeEngineBuilder_;
                    if (singleFieldBuilderV3 == null) {
                        if (this.credentialSpecifierCase_ != 2 || this.credentialSpecifier_ == Empty.getDefaultInstance()) {
                            this.credentialSpecifier_ = empty;
                        } else {
                            this.credentialSpecifier_ = Empty.newBuilder((Empty) this.credentialSpecifier_).mergeFrom(empty).buildPartial();
                        }
                        onChanged();
                    } else {
                        if (this.credentialSpecifierCase_ == 2) {
                            singleFieldBuilderV3.mergeFrom(empty);
                        }
                        this.googleComputeEngineBuilder_.setMessage(empty);
                    }
                    this.credentialSpecifierCase_ = 2;
                    return this;
                }

                public Builder clearGoogleComputeEngine() {
                    SingleFieldBuilderV3<Empty, Empty.Builder, EmptyOrBuilder> singleFieldBuilderV3 = this.googleComputeEngineBuilder_;
                    if (singleFieldBuilderV3 != null) {
                        if (this.credentialSpecifierCase_ == 2) {
                            this.credentialSpecifierCase_ = 0;
                            this.credentialSpecifier_ = null;
                        }
                        singleFieldBuilderV3.clear();
                    } else if (this.credentialSpecifierCase_ == 2) {
                        this.credentialSpecifierCase_ = 0;
                        this.credentialSpecifier_ = null;
                        onChanged();
                    }
                    return this;
                }

                public Empty.Builder getGoogleComputeEngineBuilder() {
                    return getGoogleComputeEngineFieldBuilder().getBuilder();
                }

                @Override
                // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentialsOrBuilder
                public EmptyOrBuilder getGoogleComputeEngineOrBuilder() {
                    SingleFieldBuilderV3<Empty, Empty.Builder, EmptyOrBuilder> singleFieldBuilderV3;
                    int i = this.credentialSpecifierCase_;
                    if (i == 2 && (singleFieldBuilderV3 = this.googleComputeEngineBuilder_) != null) {
                        return singleFieldBuilderV3.getMessageOrBuilder();
                    }
                    if (i == 2) {
                        return (Empty) this.credentialSpecifier_;
                    }
                    return Empty.getDefaultInstance();
                }

                private SingleFieldBuilderV3<Empty, Empty.Builder, EmptyOrBuilder> getGoogleComputeEngineFieldBuilder() {
                    if (this.googleComputeEngineBuilder_ == null) {
                        if (this.credentialSpecifierCase_ != 2) {
                            this.credentialSpecifier_ = Empty.getDefaultInstance();
                        }
                        this.googleComputeEngineBuilder_ = new SingleFieldBuilderV3<>((Empty) this.credentialSpecifier_, getParentForChildren(), isClean());
                        this.credentialSpecifier_ = null;
                    }
                    this.credentialSpecifierCase_ = 2;
                    onChanged();
                    return this.googleComputeEngineBuilder_;
                }

                @Override
                // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentialsOrBuilder
                public String getGoogleRefreshToken() {
                    String str = this.credentialSpecifierCase_ == 3 ? this.credentialSpecifier_ : "";
                    if (!(str instanceof String)) {
                        String stringUtf8 = ((ByteString) str).toStringUtf8();
                        if (this.credentialSpecifierCase_ == 3) {
                            this.credentialSpecifier_ = stringUtf8;
                        }
                        return stringUtf8;
                    }
                    return (String) str;
                }

                public Builder setGoogleRefreshToken(String str) {
                    str.getClass();
                    this.credentialSpecifierCase_ = 3;
                    this.credentialSpecifier_ = str;
                    onChanged();
                    return this;
                }

                @Override
                // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentialsOrBuilder
                public ByteString getGoogleRefreshTokenBytes() {
                    String str = this.credentialSpecifierCase_ == 3 ? this.credentialSpecifier_ : "";
                    if (str instanceof String) {
                        ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) str);
                        if (this.credentialSpecifierCase_ == 3) {
                            this.credentialSpecifier_ = byteStringCopyFromUtf8;
                        }
                        return byteStringCopyFromUtf8;
                    }
                    return (ByteString) str;
                }

                public Builder setGoogleRefreshTokenBytes(ByteString byteString) {
                    byteString.getClass();
                    CallCredentials.checkByteStringIsUtf8(byteString);
                    this.credentialSpecifierCase_ = 3;
                    this.credentialSpecifier_ = byteString;
                    onChanged();
                    return this;
                }

                public Builder clearGoogleRefreshToken() {
                    if (this.credentialSpecifierCase_ == 3) {
                        this.credentialSpecifierCase_ = 0;
                        this.credentialSpecifier_ = null;
                        onChanged();
                    }
                    return this;
                }

                @Override
                // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentialsOrBuilder
                public ServiceAccountJWTAccessCredentials getServiceAccountJwtAccess() {
                    SingleFieldBuilderV3<ServiceAccountJWTAccessCredentials, ServiceAccountJWTAccessCredentials.Builder, ServiceAccountJWTAccessCredentialsOrBuilder> singleFieldBuilderV3 = this.serviceAccountJwtAccessBuilder_;
                    if (singleFieldBuilderV3 == null) {
                        if (this.credentialSpecifierCase_ == 4) {
                            return (ServiceAccountJWTAccessCredentials) this.credentialSpecifier_;
                        }
                        return ServiceAccountJWTAccessCredentials.getDefaultInstance();
                    }
                    if (this.credentialSpecifierCase_ == 4) {
                        return singleFieldBuilderV3.getMessage();
                    }
                    return ServiceAccountJWTAccessCredentials.getDefaultInstance();
                }

                public Builder setServiceAccountJwtAccess(ServiceAccountJWTAccessCredentials serviceAccountJWTAccessCredentials) {
                    SingleFieldBuilderV3<ServiceAccountJWTAccessCredentials, ServiceAccountJWTAccessCredentials.Builder, ServiceAccountJWTAccessCredentialsOrBuilder> singleFieldBuilderV3 = this.serviceAccountJwtAccessBuilder_;
                    if (singleFieldBuilderV3 == null) {
                        serviceAccountJWTAccessCredentials.getClass();
                        this.credentialSpecifier_ = serviceAccountJWTAccessCredentials;
                        onChanged();
                    } else {
                        singleFieldBuilderV3.setMessage(serviceAccountJWTAccessCredentials);
                    }
                    this.credentialSpecifierCase_ = 4;
                    return this;
                }

                public Builder setServiceAccountJwtAccess(ServiceAccountJWTAccessCredentials.Builder builder) {
                    SingleFieldBuilderV3<ServiceAccountJWTAccessCredentials, ServiceAccountJWTAccessCredentials.Builder, ServiceAccountJWTAccessCredentialsOrBuilder> singleFieldBuilderV3 = this.serviceAccountJwtAccessBuilder_;
                    if (singleFieldBuilderV3 == null) {
                        this.credentialSpecifier_ = builder.m15185build();
                        onChanged();
                    } else {
                        singleFieldBuilderV3.setMessage(builder.m15185build());
                    }
                    this.credentialSpecifierCase_ = 4;
                    return this;
                }

                public Builder mergeServiceAccountJwtAccess(ServiceAccountJWTAccessCredentials serviceAccountJWTAccessCredentials) {
                    SingleFieldBuilderV3<ServiceAccountJWTAccessCredentials, ServiceAccountJWTAccessCredentials.Builder, ServiceAccountJWTAccessCredentialsOrBuilder> singleFieldBuilderV3 = this.serviceAccountJwtAccessBuilder_;
                    if (singleFieldBuilderV3 == null) {
                        if (this.credentialSpecifierCase_ != 4 || this.credentialSpecifier_ == ServiceAccountJWTAccessCredentials.getDefaultInstance()) {
                            this.credentialSpecifier_ = serviceAccountJWTAccessCredentials;
                        } else {
                            this.credentialSpecifier_ = ServiceAccountJWTAccessCredentials.newBuilder((ServiceAccountJWTAccessCredentials) this.credentialSpecifier_).mergeFrom(serviceAccountJWTAccessCredentials).m15187buildPartial();
                        }
                        onChanged();
                    } else {
                        if (this.credentialSpecifierCase_ == 4) {
                            singleFieldBuilderV3.mergeFrom(serviceAccountJWTAccessCredentials);
                        }
                        this.serviceAccountJwtAccessBuilder_.setMessage(serviceAccountJWTAccessCredentials);
                    }
                    this.credentialSpecifierCase_ = 4;
                    return this;
                }

                public Builder clearServiceAccountJwtAccess() {
                    SingleFieldBuilderV3<ServiceAccountJWTAccessCredentials, ServiceAccountJWTAccessCredentials.Builder, ServiceAccountJWTAccessCredentialsOrBuilder> singleFieldBuilderV3 = this.serviceAccountJwtAccessBuilder_;
                    if (singleFieldBuilderV3 != null) {
                        if (this.credentialSpecifierCase_ == 4) {
                            this.credentialSpecifierCase_ = 0;
                            this.credentialSpecifier_ = null;
                        }
                        singleFieldBuilderV3.clear();
                    } else if (this.credentialSpecifierCase_ == 4) {
                        this.credentialSpecifierCase_ = 0;
                        this.credentialSpecifier_ = null;
                        onChanged();
                    }
                    return this;
                }

                public ServiceAccountJWTAccessCredentials.Builder getServiceAccountJwtAccessBuilder() {
                    return getServiceAccountJwtAccessFieldBuilder().getBuilder();
                }

                @Override
                // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentialsOrBuilder
                public ServiceAccountJWTAccessCredentialsOrBuilder getServiceAccountJwtAccessOrBuilder() {
                    SingleFieldBuilderV3<ServiceAccountJWTAccessCredentials, ServiceAccountJWTAccessCredentials.Builder, ServiceAccountJWTAccessCredentialsOrBuilder> singleFieldBuilderV3;
                    int i = this.credentialSpecifierCase_;
                    if (i == 4 && (singleFieldBuilderV3 = this.serviceAccountJwtAccessBuilder_) != null) {
                        return (ServiceAccountJWTAccessCredentialsOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
                    }
                    if (i == 4) {
                        return (ServiceAccountJWTAccessCredentials) this.credentialSpecifier_;
                    }
                    return ServiceAccountJWTAccessCredentials.getDefaultInstance();
                }

                private SingleFieldBuilderV3<ServiceAccountJWTAccessCredentials, ServiceAccountJWTAccessCredentials.Builder, ServiceAccountJWTAccessCredentialsOrBuilder> getServiceAccountJwtAccessFieldBuilder() {
                    if (this.serviceAccountJwtAccessBuilder_ == null) {
                        if (this.credentialSpecifierCase_ != 4) {
                            this.credentialSpecifier_ = ServiceAccountJWTAccessCredentials.getDefaultInstance();
                        }
                        this.serviceAccountJwtAccessBuilder_ = new SingleFieldBuilderV3<>((ServiceAccountJWTAccessCredentials) this.credentialSpecifier_, getParentForChildren(), isClean());
                        this.credentialSpecifier_ = null;
                    }
                    this.credentialSpecifierCase_ = 4;
                    onChanged();
                    return this.serviceAccountJwtAccessBuilder_;
                }

                @Override
                // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentialsOrBuilder
                public GoogleIAMCredentials getGoogleIam() {
                    SingleFieldBuilderV3<GoogleIAMCredentials, GoogleIAMCredentials.Builder, GoogleIAMCredentialsOrBuilder> singleFieldBuilderV3 = this.googleIamBuilder_;
                    if (singleFieldBuilderV3 == null) {
                        if (this.credentialSpecifierCase_ == 5) {
                            return (GoogleIAMCredentials) this.credentialSpecifier_;
                        }
                        return GoogleIAMCredentials.getDefaultInstance();
                    }
                    if (this.credentialSpecifierCase_ == 5) {
                        return singleFieldBuilderV3.getMessage();
                    }
                    return GoogleIAMCredentials.getDefaultInstance();
                }

                public Builder setGoogleIam(GoogleIAMCredentials googleIAMCredentials) {
                    SingleFieldBuilderV3<GoogleIAMCredentials, GoogleIAMCredentials.Builder, GoogleIAMCredentialsOrBuilder> singleFieldBuilderV3 = this.googleIamBuilder_;
                    if (singleFieldBuilderV3 == null) {
                        googleIAMCredentials.getClass();
                        this.credentialSpecifier_ = googleIAMCredentials;
                        onChanged();
                    } else {
                        singleFieldBuilderV3.setMessage(googleIAMCredentials);
                    }
                    this.credentialSpecifierCase_ = 5;
                    return this;
                }

                public Builder setGoogleIam(GoogleIAMCredentials.Builder builder) {
                    SingleFieldBuilderV3<GoogleIAMCredentials, GoogleIAMCredentials.Builder, GoogleIAMCredentialsOrBuilder> singleFieldBuilderV3 = this.googleIamBuilder_;
                    if (singleFieldBuilderV3 == null) {
                        this.credentialSpecifier_ = builder.m15093build();
                        onChanged();
                    } else {
                        singleFieldBuilderV3.setMessage(builder.m15093build());
                    }
                    this.credentialSpecifierCase_ = 5;
                    return this;
                }

                public Builder mergeGoogleIam(GoogleIAMCredentials googleIAMCredentials) {
                    SingleFieldBuilderV3<GoogleIAMCredentials, GoogleIAMCredentials.Builder, GoogleIAMCredentialsOrBuilder> singleFieldBuilderV3 = this.googleIamBuilder_;
                    if (singleFieldBuilderV3 == null) {
                        if (this.credentialSpecifierCase_ != 5 || this.credentialSpecifier_ == GoogleIAMCredentials.getDefaultInstance()) {
                            this.credentialSpecifier_ = googleIAMCredentials;
                        } else {
                            this.credentialSpecifier_ = GoogleIAMCredentials.newBuilder((GoogleIAMCredentials) this.credentialSpecifier_).mergeFrom(googleIAMCredentials).m15095buildPartial();
                        }
                        onChanged();
                    } else {
                        if (this.credentialSpecifierCase_ == 5) {
                            singleFieldBuilderV3.mergeFrom(googleIAMCredentials);
                        }
                        this.googleIamBuilder_.setMessage(googleIAMCredentials);
                    }
                    this.credentialSpecifierCase_ = 5;
                    return this;
                }

                public Builder clearGoogleIam() {
                    SingleFieldBuilderV3<GoogleIAMCredentials, GoogleIAMCredentials.Builder, GoogleIAMCredentialsOrBuilder> singleFieldBuilderV3 = this.googleIamBuilder_;
                    if (singleFieldBuilderV3 != null) {
                        if (this.credentialSpecifierCase_ == 5) {
                            this.credentialSpecifierCase_ = 0;
                            this.credentialSpecifier_ = null;
                        }
                        singleFieldBuilderV3.clear();
                    } else if (this.credentialSpecifierCase_ == 5) {
                        this.credentialSpecifierCase_ = 0;
                        this.credentialSpecifier_ = null;
                        onChanged();
                    }
                    return this;
                }

                public GoogleIAMCredentials.Builder getGoogleIamBuilder() {
                    return getGoogleIamFieldBuilder().getBuilder();
                }

                @Override
                // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentialsOrBuilder
                public GoogleIAMCredentialsOrBuilder getGoogleIamOrBuilder() {
                    SingleFieldBuilderV3<GoogleIAMCredentials, GoogleIAMCredentials.Builder, GoogleIAMCredentialsOrBuilder> singleFieldBuilderV3;
                    int i = this.credentialSpecifierCase_;
                    if (i == 5 && (singleFieldBuilderV3 = this.googleIamBuilder_) != null) {
                        return (GoogleIAMCredentialsOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
                    }
                    if (i == 5) {
                        return (GoogleIAMCredentials) this.credentialSpecifier_;
                    }
                    return GoogleIAMCredentials.getDefaultInstance();
                }

                private SingleFieldBuilderV3<GoogleIAMCredentials, GoogleIAMCredentials.Builder, GoogleIAMCredentialsOrBuilder> getGoogleIamFieldBuilder() {
                    if (this.googleIamBuilder_ == null) {
                        if (this.credentialSpecifierCase_ != 5) {
                            this.credentialSpecifier_ = GoogleIAMCredentials.getDefaultInstance();
                        }
                        this.googleIamBuilder_ = new SingleFieldBuilderV3<>((GoogleIAMCredentials) this.credentialSpecifier_, getParentForChildren(), isClean());
                        this.credentialSpecifier_ = null;
                    }
                    this.credentialSpecifierCase_ = 5;
                    onChanged();
                    return this.googleIamBuilder_;
                }

                @Override
                // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentialsOrBuilder
                public MetadataCredentialsFromPlugin getFromPlugin() {
                    SingleFieldBuilderV3<MetadataCredentialsFromPlugin, MetadataCredentialsFromPlugin.Builder, MetadataCredentialsFromPluginOrBuilder> singleFieldBuilderV3 = this.fromPluginBuilder_;
                    if (singleFieldBuilderV3 == null) {
                        if (this.credentialSpecifierCase_ == 6) {
                            return (MetadataCredentialsFromPlugin) this.credentialSpecifier_;
                        }
                        return MetadataCredentialsFromPlugin.getDefaultInstance();
                    }
                    if (this.credentialSpecifierCase_ == 6) {
                        return singleFieldBuilderV3.getMessage();
                    }
                    return MetadataCredentialsFromPlugin.getDefaultInstance();
                }

                public Builder setFromPlugin(MetadataCredentialsFromPlugin metadataCredentialsFromPlugin) {
                    SingleFieldBuilderV3<MetadataCredentialsFromPlugin, MetadataCredentialsFromPlugin.Builder, MetadataCredentialsFromPluginOrBuilder> singleFieldBuilderV3 = this.fromPluginBuilder_;
                    if (singleFieldBuilderV3 == null) {
                        metadataCredentialsFromPlugin.getClass();
                        this.credentialSpecifier_ = metadataCredentialsFromPlugin;
                        onChanged();
                    } else {
                        singleFieldBuilderV3.setMessage(metadataCredentialsFromPlugin);
                    }
                    this.credentialSpecifierCase_ = 6;
                    return this;
                }

                public Builder setFromPlugin(MetadataCredentialsFromPlugin.Builder builder) {
                    SingleFieldBuilderV3<MetadataCredentialsFromPlugin, MetadataCredentialsFromPlugin.Builder, MetadataCredentialsFromPluginOrBuilder> singleFieldBuilderV3 = this.fromPluginBuilder_;
                    if (singleFieldBuilderV3 == null) {
                        this.credentialSpecifier_ = builder.m15139build();
                        onChanged();
                    } else {
                        singleFieldBuilderV3.setMessage(builder.m15139build());
                    }
                    this.credentialSpecifierCase_ = 6;
                    return this;
                }

                public Builder mergeFromPlugin(MetadataCredentialsFromPlugin metadataCredentialsFromPlugin) {
                    SingleFieldBuilderV3<MetadataCredentialsFromPlugin, MetadataCredentialsFromPlugin.Builder, MetadataCredentialsFromPluginOrBuilder> singleFieldBuilderV3 = this.fromPluginBuilder_;
                    if (singleFieldBuilderV3 == null) {
                        if (this.credentialSpecifierCase_ != 6 || this.credentialSpecifier_ == MetadataCredentialsFromPlugin.getDefaultInstance()) {
                            this.credentialSpecifier_ = metadataCredentialsFromPlugin;
                        } else {
                            this.credentialSpecifier_ = MetadataCredentialsFromPlugin.newBuilder((MetadataCredentialsFromPlugin) this.credentialSpecifier_).mergeFrom(metadataCredentialsFromPlugin).m15141buildPartial();
                        }
                        onChanged();
                    } else {
                        if (this.credentialSpecifierCase_ == 6) {
                            singleFieldBuilderV3.mergeFrom(metadataCredentialsFromPlugin);
                        }
                        this.fromPluginBuilder_.setMessage(metadataCredentialsFromPlugin);
                    }
                    this.credentialSpecifierCase_ = 6;
                    return this;
                }

                public Builder clearFromPlugin() {
                    SingleFieldBuilderV3<MetadataCredentialsFromPlugin, MetadataCredentialsFromPlugin.Builder, MetadataCredentialsFromPluginOrBuilder> singleFieldBuilderV3 = this.fromPluginBuilder_;
                    if (singleFieldBuilderV3 != null) {
                        if (this.credentialSpecifierCase_ == 6) {
                            this.credentialSpecifierCase_ = 0;
                            this.credentialSpecifier_ = null;
                        }
                        singleFieldBuilderV3.clear();
                    } else if (this.credentialSpecifierCase_ == 6) {
                        this.credentialSpecifierCase_ = 0;
                        this.credentialSpecifier_ = null;
                        onChanged();
                    }
                    return this;
                }

                public MetadataCredentialsFromPlugin.Builder getFromPluginBuilder() {
                    return getFromPluginFieldBuilder().getBuilder();
                }

                @Override
                // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentialsOrBuilder
                public MetadataCredentialsFromPluginOrBuilder getFromPluginOrBuilder() {
                    SingleFieldBuilderV3<MetadataCredentialsFromPlugin, MetadataCredentialsFromPlugin.Builder, MetadataCredentialsFromPluginOrBuilder> singleFieldBuilderV3;
                    int i = this.credentialSpecifierCase_;
                    if (i == 6 && (singleFieldBuilderV3 = this.fromPluginBuilder_) != null) {
                        return (MetadataCredentialsFromPluginOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
                    }
                    if (i == 6) {
                        return (MetadataCredentialsFromPlugin) this.credentialSpecifier_;
                    }
                    return MetadataCredentialsFromPlugin.getDefaultInstance();
                }

                private SingleFieldBuilderV3<MetadataCredentialsFromPlugin, MetadataCredentialsFromPlugin.Builder, MetadataCredentialsFromPluginOrBuilder> getFromPluginFieldBuilder() {
                    if (this.fromPluginBuilder_ == null) {
                        if (this.credentialSpecifierCase_ != 6) {
                            this.credentialSpecifier_ = MetadataCredentialsFromPlugin.getDefaultInstance();
                        }
                        this.fromPluginBuilder_ = new SingleFieldBuilderV3<>((MetadataCredentialsFromPlugin) this.credentialSpecifier_, getParentForChildren(), isClean());
                        this.credentialSpecifier_ = null;
                    }
                    this.credentialSpecifierCase_ = 6;
                    onChanged();
                    return this.fromPluginBuilder_;
                }

                @Override
                // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentialsOrBuilder
                public StsService getStsService() {
                    SingleFieldBuilderV3<StsService, StsService.Builder, StsServiceOrBuilder> singleFieldBuilderV3 = this.stsServiceBuilder_;
                    if (singleFieldBuilderV3 == null) {
                        if (this.credentialSpecifierCase_ == 7) {
                            return (StsService) this.credentialSpecifier_;
                        }
                        return StsService.getDefaultInstance();
                    }
                    if (this.credentialSpecifierCase_ == 7) {
                        return singleFieldBuilderV3.getMessage();
                    }
                    return StsService.getDefaultInstance();
                }

                public Builder setStsService(StsService stsService) {
                    SingleFieldBuilderV3<StsService, StsService.Builder, StsServiceOrBuilder> singleFieldBuilderV3 = this.stsServiceBuilder_;
                    if (singleFieldBuilderV3 == null) {
                        stsService.getClass();
                        this.credentialSpecifier_ = stsService;
                        onChanged();
                    } else {
                        singleFieldBuilderV3.setMessage(stsService);
                    }
                    this.credentialSpecifierCase_ = 7;
                    return this;
                }

                public Builder setStsService(StsService.Builder builder) {
                    SingleFieldBuilderV3<StsService, StsService.Builder, StsServiceOrBuilder> singleFieldBuilderV3 = this.stsServiceBuilder_;
                    if (singleFieldBuilderV3 == null) {
                        this.credentialSpecifier_ = builder.m15231build();
                        onChanged();
                    } else {
                        singleFieldBuilderV3.setMessage(builder.m15231build());
                    }
                    this.credentialSpecifierCase_ = 7;
                    return this;
                }

                public Builder mergeStsService(StsService stsService) {
                    SingleFieldBuilderV3<StsService, StsService.Builder, StsServiceOrBuilder> singleFieldBuilderV3 = this.stsServiceBuilder_;
                    if (singleFieldBuilderV3 == null) {
                        if (this.credentialSpecifierCase_ != 7 || this.credentialSpecifier_ == StsService.getDefaultInstance()) {
                            this.credentialSpecifier_ = stsService;
                        } else {
                            this.credentialSpecifier_ = StsService.newBuilder((StsService) this.credentialSpecifier_).mergeFrom(stsService).m15233buildPartial();
                        }
                        onChanged();
                    } else {
                        if (this.credentialSpecifierCase_ == 7) {
                            singleFieldBuilderV3.mergeFrom(stsService);
                        }
                        this.stsServiceBuilder_.setMessage(stsService);
                    }
                    this.credentialSpecifierCase_ = 7;
                    return this;
                }

                public Builder clearStsService() {
                    SingleFieldBuilderV3<StsService, StsService.Builder, StsServiceOrBuilder> singleFieldBuilderV3 = this.stsServiceBuilder_;
                    if (singleFieldBuilderV3 != null) {
                        if (this.credentialSpecifierCase_ == 7) {
                            this.credentialSpecifierCase_ = 0;
                            this.credentialSpecifier_ = null;
                        }
                        singleFieldBuilderV3.clear();
                    } else if (this.credentialSpecifierCase_ == 7) {
                        this.credentialSpecifierCase_ = 0;
                        this.credentialSpecifier_ = null;
                        onChanged();
                    }
                    return this;
                }

                public StsService.Builder getStsServiceBuilder() {
                    return getStsServiceFieldBuilder().getBuilder();
                }

                @Override
                // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.CallCredentialsOrBuilder
                public StsServiceOrBuilder getStsServiceOrBuilder() {
                    SingleFieldBuilderV3<StsService, StsService.Builder, StsServiceOrBuilder> singleFieldBuilderV3;
                    int i = this.credentialSpecifierCase_;
                    if (i == 7 && (singleFieldBuilderV3 = this.stsServiceBuilder_) != null) {
                        return (StsServiceOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
                    }
                    if (i == 7) {
                        return (StsService) this.credentialSpecifier_;
                    }
                    return StsService.getDefaultInstance();
                }

                private SingleFieldBuilderV3<StsService, StsService.Builder, StsServiceOrBuilder> getStsServiceFieldBuilder() {
                    if (this.stsServiceBuilder_ == null) {
                        if (this.credentialSpecifierCase_ != 7) {
                            this.credentialSpecifier_ = StsService.getDefaultInstance();
                        }
                        this.stsServiceBuilder_ = new SingleFieldBuilderV3<>((StsService) this.credentialSpecifier_, getParentForChildren(), isClean());
                        this.credentialSpecifier_ = null;
                    }
                    this.credentialSpecifierCase_ = 7;
                    onChanged();
                    return this.stsServiceBuilder_;
                }

                /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public final Builder m15081setUnknownFields(UnknownFieldSet unknownFieldSet) {
                    return (Builder) super.setUnknownFields(unknownFieldSet);
                }

                /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
                public final Builder m15075mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                    return (Builder) super.mergeUnknownFields(unknownFieldSet);
                }
            }
        }

        public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements GoogleGrpcOrBuilder {
            private int bitField0_;
            private RepeatedFieldBuilderV3<CallCredentials, CallCredentials.Builder, CallCredentialsOrBuilder> callCredentialsBuilder_;
            private List<CallCredentials> callCredentials_;
            private SingleFieldBuilderV3<ChannelCredentials, ChannelCredentials.Builder, ChannelCredentialsOrBuilder> channelCredentialsBuilder_;
            private ChannelCredentials channelCredentials_;
            private SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> configBuilder_;
            private Struct config_;
            private Object credentialsFactoryName_;
            private Object statPrefix_;
            private Object targetUri_;

            private Builder() {
                this.targetUri_ = "";
                this.callCredentials_ = Collections.emptyList();
                this.statPrefix_ = "";
                this.credentialsFactoryName_ = "";
                maybeForceBuilderInitialization();
            }

            private Builder(GeneratedMessageV3.BuilderParent builderParent) {
                super(builderParent);
                this.targetUri_ = "";
                this.callCredentials_ = Collections.emptyList();
                this.statPrefix_ = "";
                this.credentialsFactoryName_ = "";
                maybeForceBuilderInitialization();
            }

            public static final Descriptors.Descriptor getDescriptor() {
                return GrpcServiceProto.internal_static_envoy_api_v2_core_GrpcService_GoogleGrpc_descriptor;
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpcOrBuilder
            public boolean hasChannelCredentials() {
                return (this.channelCredentialsBuilder_ == null && this.channelCredentials_ == null) ? false : true;
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpcOrBuilder
            public boolean hasConfig() {
                return (this.configBuilder_ == null && this.config_ == null) ? false : true;
            }

            public final boolean isInitialized() {
                return true;
            }

            protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
                return GrpcServiceProto.internal_static_envoy_api_v2_core_GrpcService_GoogleGrpc_fieldAccessorTable.ensureFieldAccessorsInitialized(GoogleGrpc.class, Builder.class);
            }

            private void maybeForceBuilderInitialization() {
                if (GoogleGrpc.alwaysUseFieldBuilders) {
                    getCallCredentialsFieldBuilder();
                }
            }

            /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m15007clear() {
                super.clear();
                this.targetUri_ = "";
                if (this.channelCredentialsBuilder_ == null) {
                    this.channelCredentials_ = null;
                } else {
                    this.channelCredentials_ = null;
                    this.channelCredentialsBuilder_ = null;
                }
                RepeatedFieldBuilderV3<CallCredentials, CallCredentials.Builder, CallCredentialsOrBuilder> repeatedFieldBuilderV3 = this.callCredentialsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    this.callCredentials_ = Collections.emptyList();
                    this.bitField0_ &= -2;
                } else {
                    repeatedFieldBuilderV3.clear();
                }
                this.statPrefix_ = "";
                this.credentialsFactoryName_ = "";
                if (this.configBuilder_ == null) {
                    this.config_ = null;
                } else {
                    this.config_ = null;
                    this.configBuilder_ = null;
                }
                return this;
            }

            public Descriptors.Descriptor getDescriptorForType() {
                return GrpcServiceProto.internal_static_envoy_api_v2_core_GrpcService_GoogleGrpc_descriptor;
            }

            /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public GoogleGrpc m15020getDefaultInstanceForType() {
                return GoogleGrpc.getDefaultInstance();
            }

            /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
            /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public GoogleGrpc m15001build() throws UninitializedMessageException {
                GoogleGrpc googleGrpcM15003buildPartial = m15003buildPartial();
                if (googleGrpcM15003buildPartial.isInitialized()) {
                    return googleGrpcM15003buildPartial;
                }
                throw newUninitializedMessageException(googleGrpcM15003buildPartial);
            }

            /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public GoogleGrpc m15003buildPartial() {
                GoogleGrpc googleGrpc = new GoogleGrpc(this);
                googleGrpc.targetUri_ = this.targetUri_;
                SingleFieldBuilderV3<ChannelCredentials, ChannelCredentials.Builder, ChannelCredentialsOrBuilder> singleFieldBuilderV3 = this.channelCredentialsBuilder_;
                if (singleFieldBuilderV3 == null) {
                    googleGrpc.channelCredentials_ = this.channelCredentials_;
                } else {
                    googleGrpc.channelCredentials_ = singleFieldBuilderV3.build();
                }
                RepeatedFieldBuilderV3<CallCredentials, CallCredentials.Builder, CallCredentialsOrBuilder> repeatedFieldBuilderV3 = this.callCredentialsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    if ((this.bitField0_ & 1) != 0) {
                        this.callCredentials_ = Collections.unmodifiableList(this.callCredentials_);
                        this.bitField0_ &= -2;
                    }
                    googleGrpc.callCredentials_ = this.callCredentials_;
                } else {
                    googleGrpc.callCredentials_ = repeatedFieldBuilderV3.build();
                }
                googleGrpc.statPrefix_ = this.statPrefix_;
                googleGrpc.credentialsFactoryName_ = this.credentialsFactoryName_;
                SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV32 = this.configBuilder_;
                if (singleFieldBuilderV32 == null) {
                    googleGrpc.config_ = this.config_;
                } else {
                    googleGrpc.config_ = singleFieldBuilderV32.build();
                }
                onBuilt();
                return googleGrpc;
            }

            /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m15019clone() {
                return (Builder) super.clone();
            }

            /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m15031setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.setField(fieldDescriptor, obj);
            }

            /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m15009clearField(Descriptors.FieldDescriptor fieldDescriptor) {
                return (Builder) super.clearField(fieldDescriptor);
            }

            /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m15012clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
                return (Builder) super.clearOneof(oneofDescriptor);
            }

            /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m15033setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
                return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
            }

            /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m14999addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
                return (Builder) super.addRepeatedField(fieldDescriptor, obj);
            }

            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public Builder m15024mergeFrom(Message message) {
                if (message instanceof GoogleGrpc) {
                    return mergeFrom((GoogleGrpc) message);
                }
                super.mergeFrom(message);
                return this;
            }

            public Builder mergeFrom(GoogleGrpc googleGrpc) {
                if (googleGrpc == GoogleGrpc.getDefaultInstance()) {
                    return this;
                }
                if (!googleGrpc.getTargetUri().isEmpty()) {
                    this.targetUri_ = googleGrpc.targetUri_;
                    onChanged();
                }
                if (googleGrpc.hasChannelCredentials()) {
                    mergeChannelCredentials(googleGrpc.getChannelCredentials());
                }
                if (this.callCredentialsBuilder_ == null) {
                    if (!googleGrpc.callCredentials_.isEmpty()) {
                        if (this.callCredentials_.isEmpty()) {
                            this.callCredentials_ = googleGrpc.callCredentials_;
                            this.bitField0_ &= -2;
                        } else {
                            ensureCallCredentialsIsMutable();
                            this.callCredentials_.addAll(googleGrpc.callCredentials_);
                        }
                        onChanged();
                    }
                } else if (!googleGrpc.callCredentials_.isEmpty()) {
                    if (!this.callCredentialsBuilder_.isEmpty()) {
                        this.callCredentialsBuilder_.addAllMessages(googleGrpc.callCredentials_);
                    } else {
                        this.callCredentialsBuilder_.dispose();
                        this.callCredentialsBuilder_ = null;
                        this.callCredentials_ = googleGrpc.callCredentials_;
                        this.bitField0_ &= -2;
                        this.callCredentialsBuilder_ = GoogleGrpc.alwaysUseFieldBuilders ? getCallCredentialsFieldBuilder() : null;
                    }
                }
                if (!googleGrpc.getStatPrefix().isEmpty()) {
                    this.statPrefix_ = googleGrpc.statPrefix_;
                    onChanged();
                }
                if (!googleGrpc.getCredentialsFactoryName().isEmpty()) {
                    this.credentialsFactoryName_ = googleGrpc.credentialsFactoryName_;
                    onChanged();
                }
                if (googleGrpc.hasConfig()) {
                    mergeConfig(googleGrpc.getConfig());
                }
                m15029mergeUnknownFields(googleGrpc.unknownFields);
                onChanged();
                return this;
            }

            /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
            /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.Builder m15025mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
                /*
                    r2 = this;
                    r0 = 0
                    com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.access$11500()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                    io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService$GoogleGrpc r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                    io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService$GoogleGrpc r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc) r4     // Catch: java.lang.Throwable -> L11
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
                throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpc.Builder.m15025mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService$GoogleGrpc$Builder");
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpcOrBuilder
            public String getTargetUri() {
                Object obj = this.targetUri_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.targetUri_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            public Builder setTargetUri(String str) {
                str.getClass();
                this.targetUri_ = str;
                onChanged();
                return this;
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpcOrBuilder
            public ByteString getTargetUriBytes() {
                Object obj = this.targetUri_;
                if (obj instanceof String) {
                    ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.targetUri_ = byteStringCopyFromUtf8;
                    return byteStringCopyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setTargetUriBytes(ByteString byteString) {
                byteString.getClass();
                GoogleGrpc.checkByteStringIsUtf8(byteString);
                this.targetUri_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearTargetUri() {
                this.targetUri_ = GoogleGrpc.getDefaultInstance().getTargetUri();
                onChanged();
                return this;
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpcOrBuilder
            public ChannelCredentials getChannelCredentials() {
                SingleFieldBuilderV3<ChannelCredentials, ChannelCredentials.Builder, ChannelCredentialsOrBuilder> singleFieldBuilderV3 = this.channelCredentialsBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessage();
                }
                ChannelCredentials channelCredentials = this.channelCredentials_;
                return channelCredentials == null ? ChannelCredentials.getDefaultInstance() : channelCredentials;
            }

            public Builder setChannelCredentials(ChannelCredentials channelCredentials) {
                SingleFieldBuilderV3<ChannelCredentials, ChannelCredentials.Builder, ChannelCredentialsOrBuilder> singleFieldBuilderV3 = this.channelCredentialsBuilder_;
                if (singleFieldBuilderV3 == null) {
                    channelCredentials.getClass();
                    this.channelCredentials_ = channelCredentials;
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(channelCredentials);
                }
                return this;
            }

            public Builder setChannelCredentials(ChannelCredentials.Builder builder) {
                SingleFieldBuilderV3<ChannelCredentials, ChannelCredentials.Builder, ChannelCredentialsOrBuilder> singleFieldBuilderV3 = this.channelCredentialsBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.channelCredentials_ = builder.m15277build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.m15277build());
                }
                return this;
            }

            public Builder mergeChannelCredentials(ChannelCredentials channelCredentials) {
                SingleFieldBuilderV3<ChannelCredentials, ChannelCredentials.Builder, ChannelCredentialsOrBuilder> singleFieldBuilderV3 = this.channelCredentialsBuilder_;
                if (singleFieldBuilderV3 == null) {
                    ChannelCredentials channelCredentials2 = this.channelCredentials_;
                    if (channelCredentials2 != null) {
                        this.channelCredentials_ = ChannelCredentials.newBuilder(channelCredentials2).mergeFrom(channelCredentials).m15279buildPartial();
                    } else {
                        this.channelCredentials_ = channelCredentials;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(channelCredentials);
                }
                return this;
            }

            public Builder clearChannelCredentials() {
                if (this.channelCredentialsBuilder_ == null) {
                    this.channelCredentials_ = null;
                    onChanged();
                } else {
                    this.channelCredentials_ = null;
                    this.channelCredentialsBuilder_ = null;
                }
                return this;
            }

            public ChannelCredentials.Builder getChannelCredentialsBuilder() {
                onChanged();
                return getChannelCredentialsFieldBuilder().getBuilder();
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpcOrBuilder
            public ChannelCredentialsOrBuilder getChannelCredentialsOrBuilder() {
                SingleFieldBuilderV3<ChannelCredentials, ChannelCredentials.Builder, ChannelCredentialsOrBuilder> singleFieldBuilderV3 = this.channelCredentialsBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return (ChannelCredentialsOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
                }
                ChannelCredentials channelCredentials = this.channelCredentials_;
                return channelCredentials == null ? ChannelCredentials.getDefaultInstance() : channelCredentials;
            }

            private SingleFieldBuilderV3<ChannelCredentials, ChannelCredentials.Builder, ChannelCredentialsOrBuilder> getChannelCredentialsFieldBuilder() {
                if (this.channelCredentialsBuilder_ == null) {
                    this.channelCredentialsBuilder_ = new SingleFieldBuilderV3<>(getChannelCredentials(), getParentForChildren(), isClean());
                    this.channelCredentials_ = null;
                }
                return this.channelCredentialsBuilder_;
            }

            private void ensureCallCredentialsIsMutable() {
                if ((this.bitField0_ & 1) == 0) {
                    this.callCredentials_ = new ArrayList(this.callCredentials_);
                    this.bitField0_ |= 1;
                }
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpcOrBuilder
            public List<CallCredentials> getCallCredentialsList() {
                RepeatedFieldBuilderV3<CallCredentials, CallCredentials.Builder, CallCredentialsOrBuilder> repeatedFieldBuilderV3 = this.callCredentialsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return Collections.unmodifiableList(this.callCredentials_);
                }
                return repeatedFieldBuilderV3.getMessageList();
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpcOrBuilder
            public int getCallCredentialsCount() {
                RepeatedFieldBuilderV3<CallCredentials, CallCredentials.Builder, CallCredentialsOrBuilder> repeatedFieldBuilderV3 = this.callCredentialsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.callCredentials_.size();
                }
                return repeatedFieldBuilderV3.getCount();
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpcOrBuilder
            public CallCredentials getCallCredentials(int i) {
                RepeatedFieldBuilderV3<CallCredentials, CallCredentials.Builder, CallCredentialsOrBuilder> repeatedFieldBuilderV3 = this.callCredentialsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.callCredentials_.get(i);
                }
                return repeatedFieldBuilderV3.getMessage(i);
            }

            public Builder setCallCredentials(int i, CallCredentials callCredentials) {
                RepeatedFieldBuilderV3<CallCredentials, CallCredentials.Builder, CallCredentialsOrBuilder> repeatedFieldBuilderV3 = this.callCredentialsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    callCredentials.getClass();
                    ensureCallCredentialsIsMutable();
                    this.callCredentials_.set(i, callCredentials);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.setMessage(i, callCredentials);
                }
                return this;
            }

            public Builder setCallCredentials(int i, CallCredentials.Builder builder) {
                RepeatedFieldBuilderV3<CallCredentials, CallCredentials.Builder, CallCredentialsOrBuilder> repeatedFieldBuilderV3 = this.callCredentialsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureCallCredentialsIsMutable();
                    this.callCredentials_.set(i, builder.m15047build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.setMessage(i, builder.m15047build());
                }
                return this;
            }

            public Builder addCallCredentials(CallCredentials callCredentials) {
                RepeatedFieldBuilderV3<CallCredentials, CallCredentials.Builder, CallCredentialsOrBuilder> repeatedFieldBuilderV3 = this.callCredentialsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    callCredentials.getClass();
                    ensureCallCredentialsIsMutable();
                    this.callCredentials_.add(callCredentials);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(callCredentials);
                }
                return this;
            }

            public Builder addCallCredentials(int i, CallCredentials callCredentials) {
                RepeatedFieldBuilderV3<CallCredentials, CallCredentials.Builder, CallCredentialsOrBuilder> repeatedFieldBuilderV3 = this.callCredentialsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    callCredentials.getClass();
                    ensureCallCredentialsIsMutable();
                    this.callCredentials_.add(i, callCredentials);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(i, callCredentials);
                }
                return this;
            }

            public Builder addCallCredentials(CallCredentials.Builder builder) {
                RepeatedFieldBuilderV3<CallCredentials, CallCredentials.Builder, CallCredentialsOrBuilder> repeatedFieldBuilderV3 = this.callCredentialsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureCallCredentialsIsMutable();
                    this.callCredentials_.add(builder.m15047build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(builder.m15047build());
                }
                return this;
            }

            public Builder addCallCredentials(int i, CallCredentials.Builder builder) {
                RepeatedFieldBuilderV3<CallCredentials, CallCredentials.Builder, CallCredentialsOrBuilder> repeatedFieldBuilderV3 = this.callCredentialsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureCallCredentialsIsMutable();
                    this.callCredentials_.add(i, builder.m15047build());
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addMessage(i, builder.m15047build());
                }
                return this;
            }

            public Builder addAllCallCredentials(Iterable<? extends CallCredentials> iterable) {
                RepeatedFieldBuilderV3<CallCredentials, CallCredentials.Builder, CallCredentialsOrBuilder> repeatedFieldBuilderV3 = this.callCredentialsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureCallCredentialsIsMutable();
                    AbstractMessageLite.Builder.addAll(iterable, this.callCredentials_);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.addAllMessages(iterable);
                }
                return this;
            }

            public Builder clearCallCredentials() {
                RepeatedFieldBuilderV3<CallCredentials, CallCredentials.Builder, CallCredentialsOrBuilder> repeatedFieldBuilderV3 = this.callCredentialsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    this.callCredentials_ = Collections.emptyList();
                    this.bitField0_ &= -2;
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.clear();
                }
                return this;
            }

            public Builder removeCallCredentials(int i) {
                RepeatedFieldBuilderV3<CallCredentials, CallCredentials.Builder, CallCredentialsOrBuilder> repeatedFieldBuilderV3 = this.callCredentialsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    ensureCallCredentialsIsMutable();
                    this.callCredentials_.remove(i);
                    onChanged();
                } else {
                    repeatedFieldBuilderV3.remove(i);
                }
                return this;
            }

            public CallCredentials.Builder getCallCredentialsBuilder(int i) {
                return getCallCredentialsFieldBuilder().getBuilder(i);
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpcOrBuilder
            public CallCredentialsOrBuilder getCallCredentialsOrBuilder(int i) {
                RepeatedFieldBuilderV3<CallCredentials, CallCredentials.Builder, CallCredentialsOrBuilder> repeatedFieldBuilderV3 = this.callCredentialsBuilder_;
                if (repeatedFieldBuilderV3 == null) {
                    return this.callCredentials_.get(i);
                }
                return (CallCredentialsOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(i);
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpcOrBuilder
            public List<? extends CallCredentialsOrBuilder> getCallCredentialsOrBuilderList() {
                RepeatedFieldBuilderV3<CallCredentials, CallCredentials.Builder, CallCredentialsOrBuilder> repeatedFieldBuilderV3 = this.callCredentialsBuilder_;
                if (repeatedFieldBuilderV3 != null) {
                    return repeatedFieldBuilderV3.getMessageOrBuilderList();
                }
                return Collections.unmodifiableList(this.callCredentials_);
            }

            public CallCredentials.Builder addCallCredentialsBuilder() {
                return getCallCredentialsFieldBuilder().addBuilder(CallCredentials.getDefaultInstance());
            }

            public CallCredentials.Builder addCallCredentialsBuilder(int i) {
                return getCallCredentialsFieldBuilder().addBuilder(i, CallCredentials.getDefaultInstance());
            }

            public List<CallCredentials.Builder> getCallCredentialsBuilderList() {
                return getCallCredentialsFieldBuilder().getBuilderList();
            }

            private RepeatedFieldBuilderV3<CallCredentials, CallCredentials.Builder, CallCredentialsOrBuilder> getCallCredentialsFieldBuilder() {
                if (this.callCredentialsBuilder_ == null) {
                    this.callCredentialsBuilder_ = new RepeatedFieldBuilderV3<>(this.callCredentials_, (this.bitField0_ & 1) != 0, getParentForChildren(), isClean());
                    this.callCredentials_ = null;
                }
                return this.callCredentialsBuilder_;
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpcOrBuilder
            public String getStatPrefix() {
                Object obj = this.statPrefix_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.statPrefix_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            public Builder setStatPrefix(String str) {
                str.getClass();
                this.statPrefix_ = str;
                onChanged();
                return this;
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpcOrBuilder
            public ByteString getStatPrefixBytes() {
                Object obj = this.statPrefix_;
                if (obj instanceof String) {
                    ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.statPrefix_ = byteStringCopyFromUtf8;
                    return byteStringCopyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setStatPrefixBytes(ByteString byteString) {
                byteString.getClass();
                GoogleGrpc.checkByteStringIsUtf8(byteString);
                this.statPrefix_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearStatPrefix() {
                this.statPrefix_ = GoogleGrpc.getDefaultInstance().getStatPrefix();
                onChanged();
                return this;
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpcOrBuilder
            public String getCredentialsFactoryName() {
                Object obj = this.credentialsFactoryName_;
                if (!(obj instanceof String)) {
                    String stringUtf8 = ((ByteString) obj).toStringUtf8();
                    this.credentialsFactoryName_ = stringUtf8;
                    return stringUtf8;
                }
                return (String) obj;
            }

            public Builder setCredentialsFactoryName(String str) {
                str.getClass();
                this.credentialsFactoryName_ = str;
                onChanged();
                return this;
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpcOrBuilder
            public ByteString getCredentialsFactoryNameBytes() {
                Object obj = this.credentialsFactoryName_;
                if (obj instanceof String) {
                    ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                    this.credentialsFactoryName_ = byteStringCopyFromUtf8;
                    return byteStringCopyFromUtf8;
                }
                return (ByteString) obj;
            }

            public Builder setCredentialsFactoryNameBytes(ByteString byteString) {
                byteString.getClass();
                GoogleGrpc.checkByteStringIsUtf8(byteString);
                this.credentialsFactoryName_ = byteString;
                onChanged();
                return this;
            }

            public Builder clearCredentialsFactoryName() {
                this.credentialsFactoryName_ = GoogleGrpc.getDefaultInstance().getCredentialsFactoryName();
                onChanged();
                return this;
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpcOrBuilder
            public Struct getConfig() {
                SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.configBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessage();
                }
                Struct struct = this.config_;
                return struct == null ? Struct.getDefaultInstance() : struct;
            }

            public Builder setConfig(Struct struct) {
                SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.configBuilder_;
                if (singleFieldBuilderV3 == null) {
                    struct.getClass();
                    this.config_ = struct;
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(struct);
                }
                return this;
            }

            public Builder setConfig(Struct.Builder builder) {
                SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.configBuilder_;
                if (singleFieldBuilderV3 == null) {
                    this.config_ = builder.build();
                    onChanged();
                } else {
                    singleFieldBuilderV3.setMessage(builder.build());
                }
                return this;
            }

            public Builder mergeConfig(Struct struct) {
                SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.configBuilder_;
                if (singleFieldBuilderV3 == null) {
                    Struct struct2 = this.config_;
                    if (struct2 != null) {
                        this.config_ = Struct.newBuilder(struct2).mergeFrom(struct).buildPartial();
                    } else {
                        this.config_ = struct;
                    }
                    onChanged();
                } else {
                    singleFieldBuilderV3.mergeFrom(struct);
                }
                return this;
            }

            public Builder clearConfig() {
                if (this.configBuilder_ == null) {
                    this.config_ = null;
                    onChanged();
                } else {
                    this.config_ = null;
                    this.configBuilder_ = null;
                }
                return this;
            }

            public Struct.Builder getConfigBuilder() {
                onChanged();
                return getConfigFieldBuilder().getBuilder();
            }

            @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.GoogleGrpcOrBuilder
            public StructOrBuilder getConfigOrBuilder() {
                SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> singleFieldBuilderV3 = this.configBuilder_;
                if (singleFieldBuilderV3 != null) {
                    return singleFieldBuilderV3.getMessageOrBuilder();
                }
                Struct struct = this.config_;
                return struct == null ? Struct.getDefaultInstance() : struct;
            }

            private SingleFieldBuilderV3<Struct, Struct.Builder, StructOrBuilder> getConfigFieldBuilder() {
                if (this.configBuilder_ == null) {
                    this.configBuilder_ = new SingleFieldBuilderV3<>(getConfig(), getParentForChildren(), isClean());
                    this.config_ = null;
                }
                return this.configBuilder_;
            }

            /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m15035setUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.setUnknownFields(unknownFieldSet);
            }

            /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
            public final Builder m15029mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
                return (Builder) super.mergeUnknownFields(unknownFieldSet);
            }
        }
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements GrpcServiceOrBuilder {
        private int bitField0_;
        private SingleFieldBuilderV3<EnvoyGrpc, EnvoyGrpc.Builder, EnvoyGrpcOrBuilder> envoyGrpcBuilder_;
        private SingleFieldBuilderV3<GoogleGrpc, GoogleGrpc.Builder, GoogleGrpcOrBuilder> googleGrpcBuilder_;
        private RepeatedFieldBuilderV3<HeaderValue, HeaderValue.Builder, HeaderValueOrBuilder> initialMetadataBuilder_;
        private List<HeaderValue> initialMetadata_;
        private int targetSpecifierCase_;
        private Object targetSpecifier_;
        private SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> timeoutBuilder_;
        private Duration timeout_;

        private Builder() {
            this.targetSpecifierCase_ = 0;
            this.initialMetadata_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.targetSpecifierCase_ = 0;
            this.initialMetadata_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return GrpcServiceProto.internal_static_envoy_api_v2_core_GrpcService_descriptor;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcServiceOrBuilder
        public boolean hasEnvoyGrpc() {
            return this.targetSpecifierCase_ == 1;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcServiceOrBuilder
        public boolean hasGoogleGrpc() {
            return this.targetSpecifierCase_ == 2;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcServiceOrBuilder
        public boolean hasTimeout() {
            return (this.timeoutBuilder_ == null && this.timeout_ == null) ? false : true;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return GrpcServiceProto.internal_static_envoy_api_v2_core_GrpcService_fieldAccessorTable.ensureFieldAccessorsInitialized(GrpcService.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            if (GrpcService.alwaysUseFieldBuilders) {
                getInitialMetadataFieldBuilder();
            }
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m14915clear() {
            super.clear();
            if (this.timeoutBuilder_ == null) {
                this.timeout_ = null;
            } else {
                this.timeout_ = null;
                this.timeoutBuilder_ = null;
            }
            RepeatedFieldBuilderV3<HeaderValue, HeaderValue.Builder, HeaderValueOrBuilder> repeatedFieldBuilderV3 = this.initialMetadataBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.initialMetadata_ = Collections.emptyList();
                this.bitField0_ &= -2;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            this.targetSpecifierCase_ = 0;
            this.targetSpecifier_ = null;
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return GrpcServiceProto.internal_static_envoy_api_v2_core_GrpcService_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public GrpcService m14928getDefaultInstanceForType() {
            return GrpcService.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public GrpcService m14909build() throws UninitializedMessageException {
            GrpcService grpcServiceM14911buildPartial = m14911buildPartial();
            if (grpcServiceM14911buildPartial.isInitialized()) {
                return grpcServiceM14911buildPartial;
            }
            throw newUninitializedMessageException(grpcServiceM14911buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public GrpcService m14911buildPartial() {
            GrpcService grpcService = new GrpcService(this);
            if (this.targetSpecifierCase_ == 1) {
                SingleFieldBuilderV3<EnvoyGrpc, EnvoyGrpc.Builder, EnvoyGrpcOrBuilder> singleFieldBuilderV3 = this.envoyGrpcBuilder_;
                if (singleFieldBuilderV3 == null) {
                    grpcService.targetSpecifier_ = this.targetSpecifier_;
                } else {
                    grpcService.targetSpecifier_ = singleFieldBuilderV3.build();
                }
            }
            if (this.targetSpecifierCase_ == 2) {
                SingleFieldBuilderV3<GoogleGrpc, GoogleGrpc.Builder, GoogleGrpcOrBuilder> singleFieldBuilderV32 = this.googleGrpcBuilder_;
                if (singleFieldBuilderV32 == null) {
                    grpcService.targetSpecifier_ = this.targetSpecifier_;
                } else {
                    grpcService.targetSpecifier_ = singleFieldBuilderV32.build();
                }
            }
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV33 = this.timeoutBuilder_;
            if (singleFieldBuilderV33 == null) {
                grpcService.timeout_ = this.timeout_;
            } else {
                grpcService.timeout_ = singleFieldBuilderV33.build();
            }
            RepeatedFieldBuilderV3<HeaderValue, HeaderValue.Builder, HeaderValueOrBuilder> repeatedFieldBuilderV3 = this.initialMetadataBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if ((this.bitField0_ & 1) != 0) {
                    this.initialMetadata_ = Collections.unmodifiableList(this.initialMetadata_);
                    this.bitField0_ &= -2;
                }
                grpcService.initialMetadata_ = this.initialMetadata_;
            } else {
                grpcService.initialMetadata_ = repeatedFieldBuilderV3.build();
            }
            grpcService.targetSpecifierCase_ = this.targetSpecifierCase_;
            onBuilt();
            return grpcService;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m14927clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m14939setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m14917clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m14920clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m14941setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m14907addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m14932mergeFrom(Message message) {
            if (message instanceof GrpcService) {
                return mergeFrom((GrpcService) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(GrpcService grpcService) {
            if (grpcService == GrpcService.getDefaultInstance()) {
                return this;
            }
            if (grpcService.hasTimeout()) {
                mergeTimeout(grpcService.getTimeout());
            }
            if (this.initialMetadataBuilder_ == null) {
                if (!grpcService.initialMetadata_.isEmpty()) {
                    if (this.initialMetadata_.isEmpty()) {
                        this.initialMetadata_ = grpcService.initialMetadata_;
                        this.bitField0_ &= -2;
                    } else {
                        ensureInitialMetadataIsMutable();
                        this.initialMetadata_.addAll(grpcService.initialMetadata_);
                    }
                    onChanged();
                }
            } else if (!grpcService.initialMetadata_.isEmpty()) {
                if (!this.initialMetadataBuilder_.isEmpty()) {
                    this.initialMetadataBuilder_.addAllMessages(grpcService.initialMetadata_);
                } else {
                    this.initialMetadataBuilder_.dispose();
                    this.initialMetadataBuilder_ = null;
                    this.initialMetadata_ = grpcService.initialMetadata_;
                    this.bitField0_ &= -2;
                    this.initialMetadataBuilder_ = GrpcService.alwaysUseFieldBuilders ? getInitialMetadataFieldBuilder() : null;
                }
            }
            int i = AnonymousClass2.$SwitchMap$io$envoyproxy$envoy$api$v2$core$GrpcService$TargetSpecifierCase[grpcService.getTargetSpecifierCase().ordinal()];
            if (i == 1) {
                mergeEnvoyGrpc(grpcService.getEnvoyGrpc());
            } else if (i == 2) {
                mergeGoogleGrpc(grpcService.getGoogleGrpc());
            }
            m14937mergeUnknownFields(grpcService.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.Builder m14933mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.access$13000()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService.Builder.m14933mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService$Builder");
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcServiceOrBuilder
        public TargetSpecifierCase getTargetSpecifierCase() {
            return TargetSpecifierCase.forNumber(this.targetSpecifierCase_);
        }

        public Builder clearTargetSpecifier() {
            this.targetSpecifierCase_ = 0;
            this.targetSpecifier_ = null;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcServiceOrBuilder
        public EnvoyGrpc getEnvoyGrpc() {
            SingleFieldBuilderV3<EnvoyGrpc, EnvoyGrpc.Builder, EnvoyGrpcOrBuilder> singleFieldBuilderV3 = this.envoyGrpcBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.targetSpecifierCase_ == 1) {
                    return (EnvoyGrpc) this.targetSpecifier_;
                }
                return EnvoyGrpc.getDefaultInstance();
            }
            if (this.targetSpecifierCase_ == 1) {
                return singleFieldBuilderV3.getMessage();
            }
            return EnvoyGrpc.getDefaultInstance();
        }

        public Builder setEnvoyGrpc(EnvoyGrpc envoyGrpc) {
            SingleFieldBuilderV3<EnvoyGrpc, EnvoyGrpc.Builder, EnvoyGrpcOrBuilder> singleFieldBuilderV3 = this.envoyGrpcBuilder_;
            if (singleFieldBuilderV3 == null) {
                envoyGrpc.getClass();
                this.targetSpecifier_ = envoyGrpc;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(envoyGrpc);
            }
            this.targetSpecifierCase_ = 1;
            return this;
        }

        public Builder setEnvoyGrpc(EnvoyGrpc.Builder builder) {
            SingleFieldBuilderV3<EnvoyGrpc, EnvoyGrpc.Builder, EnvoyGrpcOrBuilder> singleFieldBuilderV3 = this.envoyGrpcBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.targetSpecifier_ = builder.m14955build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m14955build());
            }
            this.targetSpecifierCase_ = 1;
            return this;
        }

        public Builder mergeEnvoyGrpc(EnvoyGrpc envoyGrpc) {
            SingleFieldBuilderV3<EnvoyGrpc, EnvoyGrpc.Builder, EnvoyGrpcOrBuilder> singleFieldBuilderV3 = this.envoyGrpcBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.targetSpecifierCase_ != 1 || this.targetSpecifier_ == EnvoyGrpc.getDefaultInstance()) {
                    this.targetSpecifier_ = envoyGrpc;
                } else {
                    this.targetSpecifier_ = EnvoyGrpc.newBuilder((EnvoyGrpc) this.targetSpecifier_).mergeFrom(envoyGrpc).m14957buildPartial();
                }
                onChanged();
            } else {
                if (this.targetSpecifierCase_ == 1) {
                    singleFieldBuilderV3.mergeFrom(envoyGrpc);
                }
                this.envoyGrpcBuilder_.setMessage(envoyGrpc);
            }
            this.targetSpecifierCase_ = 1;
            return this;
        }

        public Builder clearEnvoyGrpc() {
            SingleFieldBuilderV3<EnvoyGrpc, EnvoyGrpc.Builder, EnvoyGrpcOrBuilder> singleFieldBuilderV3 = this.envoyGrpcBuilder_;
            if (singleFieldBuilderV3 != null) {
                if (this.targetSpecifierCase_ == 1) {
                    this.targetSpecifierCase_ = 0;
                    this.targetSpecifier_ = null;
                }
                singleFieldBuilderV3.clear();
            } else if (this.targetSpecifierCase_ == 1) {
                this.targetSpecifierCase_ = 0;
                this.targetSpecifier_ = null;
                onChanged();
            }
            return this;
        }

        public EnvoyGrpc.Builder getEnvoyGrpcBuilder() {
            return getEnvoyGrpcFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcServiceOrBuilder
        public EnvoyGrpcOrBuilder getEnvoyGrpcOrBuilder() {
            SingleFieldBuilderV3<EnvoyGrpc, EnvoyGrpc.Builder, EnvoyGrpcOrBuilder> singleFieldBuilderV3;
            int i = this.targetSpecifierCase_;
            if (i == 1 && (singleFieldBuilderV3 = this.envoyGrpcBuilder_) != null) {
                return (EnvoyGrpcOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            if (i == 1) {
                return (EnvoyGrpc) this.targetSpecifier_;
            }
            return EnvoyGrpc.getDefaultInstance();
        }

        private SingleFieldBuilderV3<EnvoyGrpc, EnvoyGrpc.Builder, EnvoyGrpcOrBuilder> getEnvoyGrpcFieldBuilder() {
            if (this.envoyGrpcBuilder_ == null) {
                if (this.targetSpecifierCase_ != 1) {
                    this.targetSpecifier_ = EnvoyGrpc.getDefaultInstance();
                }
                this.envoyGrpcBuilder_ = new SingleFieldBuilderV3<>((EnvoyGrpc) this.targetSpecifier_, getParentForChildren(), isClean());
                this.targetSpecifier_ = null;
            }
            this.targetSpecifierCase_ = 1;
            onChanged();
            return this.envoyGrpcBuilder_;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcServiceOrBuilder
        public GoogleGrpc getGoogleGrpc() {
            SingleFieldBuilderV3<GoogleGrpc, GoogleGrpc.Builder, GoogleGrpcOrBuilder> singleFieldBuilderV3 = this.googleGrpcBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.targetSpecifierCase_ == 2) {
                    return (GoogleGrpc) this.targetSpecifier_;
                }
                return GoogleGrpc.getDefaultInstance();
            }
            if (this.targetSpecifierCase_ == 2) {
                return singleFieldBuilderV3.getMessage();
            }
            return GoogleGrpc.getDefaultInstance();
        }

        public Builder setGoogleGrpc(GoogleGrpc googleGrpc) {
            SingleFieldBuilderV3<GoogleGrpc, GoogleGrpc.Builder, GoogleGrpcOrBuilder> singleFieldBuilderV3 = this.googleGrpcBuilder_;
            if (singleFieldBuilderV3 == null) {
                googleGrpc.getClass();
                this.targetSpecifier_ = googleGrpc;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(googleGrpc);
            }
            this.targetSpecifierCase_ = 2;
            return this;
        }

        public Builder setGoogleGrpc(GoogleGrpc.Builder builder) {
            SingleFieldBuilderV3<GoogleGrpc, GoogleGrpc.Builder, GoogleGrpcOrBuilder> singleFieldBuilderV3 = this.googleGrpcBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.targetSpecifier_ = builder.m15001build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m15001build());
            }
            this.targetSpecifierCase_ = 2;
            return this;
        }

        public Builder mergeGoogleGrpc(GoogleGrpc googleGrpc) {
            SingleFieldBuilderV3<GoogleGrpc, GoogleGrpc.Builder, GoogleGrpcOrBuilder> singleFieldBuilderV3 = this.googleGrpcBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (this.targetSpecifierCase_ != 2 || this.targetSpecifier_ == GoogleGrpc.getDefaultInstance()) {
                    this.targetSpecifier_ = googleGrpc;
                } else {
                    this.targetSpecifier_ = GoogleGrpc.newBuilder((GoogleGrpc) this.targetSpecifier_).mergeFrom(googleGrpc).m15003buildPartial();
                }
                onChanged();
            } else {
                if (this.targetSpecifierCase_ == 2) {
                    singleFieldBuilderV3.mergeFrom(googleGrpc);
                }
                this.googleGrpcBuilder_.setMessage(googleGrpc);
            }
            this.targetSpecifierCase_ = 2;
            return this;
        }

        public Builder clearGoogleGrpc() {
            SingleFieldBuilderV3<GoogleGrpc, GoogleGrpc.Builder, GoogleGrpcOrBuilder> singleFieldBuilderV3 = this.googleGrpcBuilder_;
            if (singleFieldBuilderV3 != null) {
                if (this.targetSpecifierCase_ == 2) {
                    this.targetSpecifierCase_ = 0;
                    this.targetSpecifier_ = null;
                }
                singleFieldBuilderV3.clear();
            } else if (this.targetSpecifierCase_ == 2) {
                this.targetSpecifierCase_ = 0;
                this.targetSpecifier_ = null;
                onChanged();
            }
            return this;
        }

        public GoogleGrpc.Builder getGoogleGrpcBuilder() {
            return getGoogleGrpcFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcServiceOrBuilder
        public GoogleGrpcOrBuilder getGoogleGrpcOrBuilder() {
            SingleFieldBuilderV3<GoogleGrpc, GoogleGrpc.Builder, GoogleGrpcOrBuilder> singleFieldBuilderV3;
            int i = this.targetSpecifierCase_;
            if (i == 2 && (singleFieldBuilderV3 = this.googleGrpcBuilder_) != null) {
                return (GoogleGrpcOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            if (i == 2) {
                return (GoogleGrpc) this.targetSpecifier_;
            }
            return GoogleGrpc.getDefaultInstance();
        }

        private SingleFieldBuilderV3<GoogleGrpc, GoogleGrpc.Builder, GoogleGrpcOrBuilder> getGoogleGrpcFieldBuilder() {
            if (this.googleGrpcBuilder_ == null) {
                if (this.targetSpecifierCase_ != 2) {
                    this.targetSpecifier_ = GoogleGrpc.getDefaultInstance();
                }
                this.googleGrpcBuilder_ = new SingleFieldBuilderV3<>((GoogleGrpc) this.targetSpecifier_, getParentForChildren(), isClean());
                this.targetSpecifier_ = null;
            }
            this.targetSpecifierCase_ = 2;
            onChanged();
            return this.googleGrpcBuilder_;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcServiceOrBuilder
        public Duration getTimeout() {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.timeoutBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            Duration duration = this.timeout_;
            return duration == null ? Duration.getDefaultInstance() : duration;
        }

        public Builder setTimeout(Duration duration) {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.timeoutBuilder_;
            if (singleFieldBuilderV3 == null) {
                duration.getClass();
                this.timeout_ = duration;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(duration);
            }
            return this;
        }

        public Builder setTimeout(Duration.Builder builder) {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.timeoutBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.timeout_ = builder.build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.build());
            }
            return this;
        }

        public Builder mergeTimeout(Duration duration) {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.timeoutBuilder_;
            if (singleFieldBuilderV3 == null) {
                Duration duration2 = this.timeout_;
                if (duration2 != null) {
                    this.timeout_ = Duration.newBuilder(duration2).mergeFrom(duration).buildPartial();
                } else {
                    this.timeout_ = duration;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(duration);
            }
            return this;
        }

        public Builder clearTimeout() {
            if (this.timeoutBuilder_ == null) {
                this.timeout_ = null;
                onChanged();
            } else {
                this.timeout_ = null;
                this.timeoutBuilder_ = null;
            }
            return this;
        }

        public Duration.Builder getTimeoutBuilder() {
            onChanged();
            return getTimeoutFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcServiceOrBuilder
        public DurationOrBuilder getTimeoutOrBuilder() {
            SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> singleFieldBuilderV3 = this.timeoutBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessageOrBuilder();
            }
            Duration duration = this.timeout_;
            return duration == null ? Duration.getDefaultInstance() : duration;
        }

        private SingleFieldBuilderV3<Duration, Duration.Builder, DurationOrBuilder> getTimeoutFieldBuilder() {
            if (this.timeoutBuilder_ == null) {
                this.timeoutBuilder_ = new SingleFieldBuilderV3<>(getTimeout(), getParentForChildren(), isClean());
                this.timeout_ = null;
            }
            return this.timeoutBuilder_;
        }

        private void ensureInitialMetadataIsMutable() {
            if ((this.bitField0_ & 1) == 0) {
                this.initialMetadata_ = new ArrayList(this.initialMetadata_);
                this.bitField0_ |= 1;
            }
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcServiceOrBuilder
        public List<HeaderValue> getInitialMetadataList() {
            RepeatedFieldBuilderV3<HeaderValue, HeaderValue.Builder, HeaderValueOrBuilder> repeatedFieldBuilderV3 = this.initialMetadataBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.initialMetadata_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcServiceOrBuilder
        public int getInitialMetadataCount() {
            RepeatedFieldBuilderV3<HeaderValue, HeaderValue.Builder, HeaderValueOrBuilder> repeatedFieldBuilderV3 = this.initialMetadataBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.initialMetadata_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcServiceOrBuilder
        public HeaderValue getInitialMetadata(int i) {
            RepeatedFieldBuilderV3<HeaderValue, HeaderValue.Builder, HeaderValueOrBuilder> repeatedFieldBuilderV3 = this.initialMetadataBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.initialMetadata_.get(i);
            }
            return repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setInitialMetadata(int i, HeaderValue headerValue) {
            RepeatedFieldBuilderV3<HeaderValue, HeaderValue.Builder, HeaderValueOrBuilder> repeatedFieldBuilderV3 = this.initialMetadataBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                headerValue.getClass();
                ensureInitialMetadataIsMutable();
                this.initialMetadata_.set(i, headerValue);
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, headerValue);
            }
            return this;
        }

        public Builder setInitialMetadata(int i, HeaderValue.Builder builder) {
            RepeatedFieldBuilderV3<HeaderValue, HeaderValue.Builder, HeaderValueOrBuilder> repeatedFieldBuilderV3 = this.initialMetadataBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureInitialMetadataIsMutable();
                this.initialMetadata_.set(i, builder.m15461build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.m15461build());
            }
            return this;
        }

        public Builder addInitialMetadata(HeaderValue headerValue) {
            RepeatedFieldBuilderV3<HeaderValue, HeaderValue.Builder, HeaderValueOrBuilder> repeatedFieldBuilderV3 = this.initialMetadataBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                headerValue.getClass();
                ensureInitialMetadataIsMutable();
                this.initialMetadata_.add(headerValue);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(headerValue);
            }
            return this;
        }

        public Builder addInitialMetadata(int i, HeaderValue headerValue) {
            RepeatedFieldBuilderV3<HeaderValue, HeaderValue.Builder, HeaderValueOrBuilder> repeatedFieldBuilderV3 = this.initialMetadataBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                headerValue.getClass();
                ensureInitialMetadataIsMutable();
                this.initialMetadata_.add(i, headerValue);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, headerValue);
            }
            return this;
        }

        public Builder addInitialMetadata(HeaderValue.Builder builder) {
            RepeatedFieldBuilderV3<HeaderValue, HeaderValue.Builder, HeaderValueOrBuilder> repeatedFieldBuilderV3 = this.initialMetadataBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureInitialMetadataIsMutable();
                this.initialMetadata_.add(builder.m15461build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.m15461build());
            }
            return this;
        }

        public Builder addInitialMetadata(int i, HeaderValue.Builder builder) {
            RepeatedFieldBuilderV3<HeaderValue, HeaderValue.Builder, HeaderValueOrBuilder> repeatedFieldBuilderV3 = this.initialMetadataBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureInitialMetadataIsMutable();
                this.initialMetadata_.add(i, builder.m15461build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.m15461build());
            }
            return this;
        }

        public Builder addAllInitialMetadata(Iterable<? extends HeaderValue> iterable) {
            RepeatedFieldBuilderV3<HeaderValue, HeaderValue.Builder, HeaderValueOrBuilder> repeatedFieldBuilderV3 = this.initialMetadataBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureInitialMetadataIsMutable();
                AbstractMessageLite.Builder.addAll(iterable, this.initialMetadata_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearInitialMetadata() {
            RepeatedFieldBuilderV3<HeaderValue, HeaderValue.Builder, HeaderValueOrBuilder> repeatedFieldBuilderV3 = this.initialMetadataBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.initialMetadata_ = Collections.emptyList();
                this.bitField0_ &= -2;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeInitialMetadata(int i) {
            RepeatedFieldBuilderV3<HeaderValue, HeaderValue.Builder, HeaderValueOrBuilder> repeatedFieldBuilderV3 = this.initialMetadataBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureInitialMetadataIsMutable();
                this.initialMetadata_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public HeaderValue.Builder getInitialMetadataBuilder(int i) {
            return getInitialMetadataFieldBuilder().getBuilder(i);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcServiceOrBuilder
        public HeaderValueOrBuilder getInitialMetadataOrBuilder(int i) {
            RepeatedFieldBuilderV3<HeaderValue, HeaderValue.Builder, HeaderValueOrBuilder> repeatedFieldBuilderV3 = this.initialMetadataBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.initialMetadata_.get(i);
            }
            return (HeaderValueOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcServiceOrBuilder
        public List<? extends HeaderValueOrBuilder> getInitialMetadataOrBuilderList() {
            RepeatedFieldBuilderV3<HeaderValue, HeaderValue.Builder, HeaderValueOrBuilder> repeatedFieldBuilderV3 = this.initialMetadataBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.initialMetadata_);
        }

        public HeaderValue.Builder addInitialMetadataBuilder() {
            return getInitialMetadataFieldBuilder().addBuilder(HeaderValue.getDefaultInstance());
        }

        public HeaderValue.Builder addInitialMetadataBuilder(int i) {
            return getInitialMetadataFieldBuilder().addBuilder(i, HeaderValue.getDefaultInstance());
        }

        public List<HeaderValue.Builder> getInitialMetadataBuilderList() {
            return getInitialMetadataFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<HeaderValue, HeaderValue.Builder, HeaderValueOrBuilder> getInitialMetadataFieldBuilder() {
            if (this.initialMetadataBuilder_ == null) {
                this.initialMetadataBuilder_ = new RepeatedFieldBuilderV3<>(this.initialMetadata_, (this.bitField0_ & 1) != 0, getParentForChildren(), isClean());
                this.initialMetadata_ = null;
            }
            return this.initialMetadataBuilder_;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m14943setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m14937mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }

    /* renamed from: io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.GrpcService$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$io$envoyproxy$envoy$api$v2$core$GrpcService$GoogleGrpc$CallCredentials$CredentialSpecifierCase;
        static final /* synthetic */ int[] $SwitchMap$io$envoyproxy$envoy$api$v2$core$GrpcService$GoogleGrpc$CallCredentials$MetadataCredentialsFromPlugin$ConfigTypeCase;
        static final /* synthetic */ int[] $SwitchMap$io$envoyproxy$envoy$api$v2$core$GrpcService$GoogleGrpc$ChannelCredentials$CredentialSpecifierCase;
        static final /* synthetic */ int[] $SwitchMap$io$envoyproxy$envoy$api$v2$core$GrpcService$TargetSpecifierCase;

        static {
            int[] iArr = new int[TargetSpecifierCase.values().length];
            $SwitchMap$io$envoyproxy$envoy$api$v2$core$GrpcService$TargetSpecifierCase = iArr;
            try {
                iArr[TargetSpecifierCase.ENVOY_GRPC.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$api$v2$core$GrpcService$TargetSpecifierCase[TargetSpecifierCase.GOOGLE_GRPC.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$api$v2$core$GrpcService$TargetSpecifierCase[TargetSpecifierCase.TARGETSPECIFIER_NOT_SET.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            int[] iArr2 = new int[GoogleGrpc.CallCredentials.CredentialSpecifierCase.values().length];
            $SwitchMap$io$envoyproxy$envoy$api$v2$core$GrpcService$GoogleGrpc$CallCredentials$CredentialSpecifierCase = iArr2;
            try {
                iArr2[GoogleGrpc.CallCredentials.CredentialSpecifierCase.ACCESS_TOKEN.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$api$v2$core$GrpcService$GoogleGrpc$CallCredentials$CredentialSpecifierCase[GoogleGrpc.CallCredentials.CredentialSpecifierCase.GOOGLE_COMPUTE_ENGINE.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$api$v2$core$GrpcService$GoogleGrpc$CallCredentials$CredentialSpecifierCase[GoogleGrpc.CallCredentials.CredentialSpecifierCase.GOOGLE_REFRESH_TOKEN.ordinal()] = 3;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$api$v2$core$GrpcService$GoogleGrpc$CallCredentials$CredentialSpecifierCase[GoogleGrpc.CallCredentials.CredentialSpecifierCase.SERVICE_ACCOUNT_JWT_ACCESS.ordinal()] = 4;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$api$v2$core$GrpcService$GoogleGrpc$CallCredentials$CredentialSpecifierCase[GoogleGrpc.CallCredentials.CredentialSpecifierCase.GOOGLE_IAM.ordinal()] = 5;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$api$v2$core$GrpcService$GoogleGrpc$CallCredentials$CredentialSpecifierCase[GoogleGrpc.CallCredentials.CredentialSpecifierCase.FROM_PLUGIN.ordinal()] = 6;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$api$v2$core$GrpcService$GoogleGrpc$CallCredentials$CredentialSpecifierCase[GoogleGrpc.CallCredentials.CredentialSpecifierCase.STS_SERVICE.ordinal()] = 7;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$api$v2$core$GrpcService$GoogleGrpc$CallCredentials$CredentialSpecifierCase[GoogleGrpc.CallCredentials.CredentialSpecifierCase.CREDENTIALSPECIFIER_NOT_SET.ordinal()] = 8;
            } catch (NoSuchFieldError unused11) {
            }
            int[] iArr3 = new int[GoogleGrpc.CallCredentials.MetadataCredentialsFromPlugin.ConfigTypeCase.values().length];
            $SwitchMap$io$envoyproxy$envoy$api$v2$core$GrpcService$GoogleGrpc$CallCredentials$MetadataCredentialsFromPlugin$ConfigTypeCase = iArr3;
            try {
                iArr3[GoogleGrpc.CallCredentials.MetadataCredentialsFromPlugin.ConfigTypeCase.CONFIG.ordinal()] = 1;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$api$v2$core$GrpcService$GoogleGrpc$CallCredentials$MetadataCredentialsFromPlugin$ConfigTypeCase[GoogleGrpc.CallCredentials.MetadataCredentialsFromPlugin.ConfigTypeCase.TYPED_CONFIG.ordinal()] = 2;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$api$v2$core$GrpcService$GoogleGrpc$CallCredentials$MetadataCredentialsFromPlugin$ConfigTypeCase[GoogleGrpc.CallCredentials.MetadataCredentialsFromPlugin.ConfigTypeCase.CONFIGTYPE_NOT_SET.ordinal()] = 3;
            } catch (NoSuchFieldError unused14) {
            }
            int[] iArr4 = new int[GoogleGrpc.ChannelCredentials.CredentialSpecifierCase.values().length];
            $SwitchMap$io$envoyproxy$envoy$api$v2$core$GrpcService$GoogleGrpc$ChannelCredentials$CredentialSpecifierCase = iArr4;
            try {
                iArr4[GoogleGrpc.ChannelCredentials.CredentialSpecifierCase.SSL_CREDENTIALS.ordinal()] = 1;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$api$v2$core$GrpcService$GoogleGrpc$ChannelCredentials$CredentialSpecifierCase[GoogleGrpc.ChannelCredentials.CredentialSpecifierCase.GOOGLE_DEFAULT.ordinal()] = 2;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$api$v2$core$GrpcService$GoogleGrpc$ChannelCredentials$CredentialSpecifierCase[GoogleGrpc.ChannelCredentials.CredentialSpecifierCase.LOCAL_CREDENTIALS.ordinal()] = 3;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$api$v2$core$GrpcService$GoogleGrpc$ChannelCredentials$CredentialSpecifierCase[GoogleGrpc.ChannelCredentials.CredentialSpecifierCase.CREDENTIALSPECIFIER_NOT_SET.ordinal()] = 4;
            } catch (NoSuchFieldError unused18) {
            }
        }
    }
}
