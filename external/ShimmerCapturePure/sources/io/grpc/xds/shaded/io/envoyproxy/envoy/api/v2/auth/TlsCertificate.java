package io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth;

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
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth.PrivateKeyProvider;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.DataSource;
import io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.core.DataSourceOrBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes3.dex */
public final class TlsCertificate extends GeneratedMessageV3 implements TlsCertificateOrBuilder {
    public static final int CERTIFICATE_CHAIN_FIELD_NUMBER = 1;
    public static final int OCSP_STAPLE_FIELD_NUMBER = 4;
    public static final int PASSWORD_FIELD_NUMBER = 3;
    public static final int PRIVATE_KEY_FIELD_NUMBER = 2;
    public static final int PRIVATE_KEY_PROVIDER_FIELD_NUMBER = 6;
    public static final int SIGNED_CERTIFICATE_TIMESTAMP_FIELD_NUMBER = 5;
    private static final long serialVersionUID = 0;
    private static final TlsCertificate DEFAULT_INSTANCE = new TlsCertificate();
    private static final Parser<TlsCertificate> PARSER = new AbstractParser<TlsCertificate>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth.TlsCertificate.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public TlsCertificate m13842parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new TlsCertificate(codedInputStream, extensionRegistryLite);
        }
    };
    private DataSource certificateChain_;
    private byte memoizedIsInitialized;
    private DataSource ocspStaple_;
    private DataSource password_;
    private PrivateKeyProvider privateKeyProvider_;
    private DataSource privateKey_;
    private List<DataSource> signedCertificateTimestamp_;

    private TlsCertificate(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private TlsCertificate() {
        this.memoizedIsInitialized = (byte) -1;
        this.signedCertificateTimestamp_ = Collections.emptyList();
    }

    private TlsCertificate(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            DataSource dataSource = this.certificateChain_;
                            DataSource.Builder builderM14720toBuilder = dataSource != null ? dataSource.m14720toBuilder() : null;
                            DataSource dataSource2 = (DataSource) codedInputStream.readMessage(DataSource.parser(), extensionRegistryLite);
                            this.certificateChain_ = dataSource2;
                            if (builderM14720toBuilder != null) {
                                builderM14720toBuilder.mergeFrom(dataSource2);
                                this.certificateChain_ = builderM14720toBuilder.m14727buildPartial();
                            }
                        } else if (tag == 18) {
                            DataSource dataSource3 = this.privateKey_;
                            DataSource.Builder builderM14720toBuilder2 = dataSource3 != null ? dataSource3.m14720toBuilder() : null;
                            DataSource dataSource4 = (DataSource) codedInputStream.readMessage(DataSource.parser(), extensionRegistryLite);
                            this.privateKey_ = dataSource4;
                            if (builderM14720toBuilder2 != null) {
                                builderM14720toBuilder2.mergeFrom(dataSource4);
                                this.privateKey_ = builderM14720toBuilder2.m14727buildPartial();
                            }
                        } else if (tag == 26) {
                            DataSource dataSource5 = this.password_;
                            DataSource.Builder builderM14720toBuilder3 = dataSource5 != null ? dataSource5.m14720toBuilder() : null;
                            DataSource dataSource6 = (DataSource) codedInputStream.readMessage(DataSource.parser(), extensionRegistryLite);
                            this.password_ = dataSource6;
                            if (builderM14720toBuilder3 != null) {
                                builderM14720toBuilder3.mergeFrom(dataSource6);
                                this.password_ = builderM14720toBuilder3.m14727buildPartial();
                            }
                        } else if (tag == 34) {
                            DataSource dataSource7 = this.ocspStaple_;
                            DataSource.Builder builderM14720toBuilder4 = dataSource7 != null ? dataSource7.m14720toBuilder() : null;
                            DataSource dataSource8 = (DataSource) codedInputStream.readMessage(DataSource.parser(), extensionRegistryLite);
                            this.ocspStaple_ = dataSource8;
                            if (builderM14720toBuilder4 != null) {
                                builderM14720toBuilder4.mergeFrom(dataSource8);
                                this.ocspStaple_ = builderM14720toBuilder4.m14727buildPartial();
                            }
                        } else if (tag == 42) {
                            if (!(z2 & true)) {
                                this.signedCertificateTimestamp_ = new ArrayList();
                                z2 |= true;
                            }
                            this.signedCertificateTimestamp_.add(codedInputStream.readMessage(DataSource.parser(), extensionRegistryLite));
                        } else if (tag == 50) {
                            PrivateKeyProvider privateKeyProvider = this.privateKeyProvider_;
                            PrivateKeyProvider.Builder builderM13702toBuilder = privateKeyProvider != null ? privateKeyProvider.m13702toBuilder() : null;
                            PrivateKeyProvider privateKeyProvider2 = (PrivateKeyProvider) codedInputStream.readMessage(PrivateKeyProvider.parser(), extensionRegistryLite);
                            this.privateKeyProvider_ = privateKeyProvider2;
                            if (builderM13702toBuilder != null) {
                                builderM13702toBuilder.mergeFrom(privateKeyProvider2);
                                this.privateKeyProvider_ = builderM13702toBuilder.m13709buildPartial();
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
                if (z2 & true) {
                    this.signedCertificateTimestamp_ = Collections.unmodifiableList(this.signedCertificateTimestamp_);
                }
                this.unknownFields = builderNewBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static TlsCertificate getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<TlsCertificate> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return CommonProto.internal_static_envoy_api_v2_auth_TlsCertificate_descriptor;
    }

    public static TlsCertificate parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (TlsCertificate) PARSER.parseFrom(byteBuffer);
    }

    public static TlsCertificate parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (TlsCertificate) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static TlsCertificate parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (TlsCertificate) PARSER.parseFrom(byteString);
    }

    public static TlsCertificate parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (TlsCertificate) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static TlsCertificate parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (TlsCertificate) PARSER.parseFrom(bArr);
    }

    public static TlsCertificate parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (TlsCertificate) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static TlsCertificate parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static TlsCertificate parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static TlsCertificate parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static TlsCertificate parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static TlsCertificate parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static TlsCertificate parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m13840toBuilder();
    }

    public static Builder newBuilder(TlsCertificate tlsCertificate) {
        return DEFAULT_INSTANCE.m13840toBuilder().mergeFrom(tlsCertificate);
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public TlsCertificate m13835getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public Parser<TlsCertificate> getParserForType() {
        return PARSER;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth.TlsCertificateOrBuilder
    public List<DataSource> getSignedCertificateTimestampList() {
        return this.signedCertificateTimestamp_;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth.TlsCertificateOrBuilder
    public List<? extends DataSourceOrBuilder> getSignedCertificateTimestampOrBuilderList() {
        return this.signedCertificateTimestamp_;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth.TlsCertificateOrBuilder
    public boolean hasCertificateChain() {
        return this.certificateChain_ != null;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth.TlsCertificateOrBuilder
    public boolean hasOcspStaple() {
        return this.ocspStaple_ != null;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth.TlsCertificateOrBuilder
    public boolean hasPassword() {
        return this.password_ != null;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth.TlsCertificateOrBuilder
    public boolean hasPrivateKey() {
        return this.privateKey_ != null;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth.TlsCertificateOrBuilder
    public boolean hasPrivateKeyProvider() {
        return this.privateKeyProvider_ != null;
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
        return new TlsCertificate();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return CommonProto.internal_static_envoy_api_v2_auth_TlsCertificate_fieldAccessorTable.ensureFieldAccessorsInitialized(TlsCertificate.class, Builder.class);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth.TlsCertificateOrBuilder
    public DataSource getCertificateChain() {
        DataSource dataSource = this.certificateChain_;
        return dataSource == null ? DataSource.getDefaultInstance() : dataSource;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth.TlsCertificateOrBuilder
    public DataSourceOrBuilder getCertificateChainOrBuilder() {
        return getCertificateChain();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth.TlsCertificateOrBuilder
    public DataSource getPrivateKey() {
        DataSource dataSource = this.privateKey_;
        return dataSource == null ? DataSource.getDefaultInstance() : dataSource;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth.TlsCertificateOrBuilder
    public DataSourceOrBuilder getPrivateKeyOrBuilder() {
        return getPrivateKey();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth.TlsCertificateOrBuilder
    public PrivateKeyProvider getPrivateKeyProvider() {
        PrivateKeyProvider privateKeyProvider = this.privateKeyProvider_;
        return privateKeyProvider == null ? PrivateKeyProvider.getDefaultInstance() : privateKeyProvider;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth.TlsCertificateOrBuilder
    public PrivateKeyProviderOrBuilder getPrivateKeyProviderOrBuilder() {
        return getPrivateKeyProvider();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth.TlsCertificateOrBuilder
    public DataSource getPassword() {
        DataSource dataSource = this.password_;
        return dataSource == null ? DataSource.getDefaultInstance() : dataSource;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth.TlsCertificateOrBuilder
    public DataSourceOrBuilder getPasswordOrBuilder() {
        return getPassword();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth.TlsCertificateOrBuilder
    public DataSource getOcspStaple() {
        DataSource dataSource = this.ocspStaple_;
        return dataSource == null ? DataSource.getDefaultInstance() : dataSource;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth.TlsCertificateOrBuilder
    public DataSourceOrBuilder getOcspStapleOrBuilder() {
        return getOcspStaple();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth.TlsCertificateOrBuilder
    public int getSignedCertificateTimestampCount() {
        return this.signedCertificateTimestamp_.size();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth.TlsCertificateOrBuilder
    public DataSource getSignedCertificateTimestamp(int i) {
        return this.signedCertificateTimestamp_.get(i);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth.TlsCertificateOrBuilder
    public DataSourceOrBuilder getSignedCertificateTimestampOrBuilder(int i) {
        return this.signedCertificateTimestamp_.get(i);
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (this.certificateChain_ != null) {
            codedOutputStream.writeMessage(1, getCertificateChain());
        }
        if (this.privateKey_ != null) {
            codedOutputStream.writeMessage(2, getPrivateKey());
        }
        if (this.password_ != null) {
            codedOutputStream.writeMessage(3, getPassword());
        }
        if (this.ocspStaple_ != null) {
            codedOutputStream.writeMessage(4, getOcspStaple());
        }
        for (int i = 0; i < this.signedCertificateTimestamp_.size(); i++) {
            codedOutputStream.writeMessage(5, this.signedCertificateTimestamp_.get(i));
        }
        if (this.privateKeyProvider_ != null) {
            codedOutputStream.writeMessage(6, getPrivateKeyProvider());
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeMessageSize = this.certificateChain_ != null ? CodedOutputStream.computeMessageSize(1, getCertificateChain()) : 0;
        if (this.privateKey_ != null) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(2, getPrivateKey());
        }
        if (this.password_ != null) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(3, getPassword());
        }
        if (this.ocspStaple_ != null) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(4, getOcspStaple());
        }
        for (int i2 = 0; i2 < this.signedCertificateTimestamp_.size(); i2++) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(5, this.signedCertificateTimestamp_.get(i2));
        }
        if (this.privateKeyProvider_ != null) {
            iComputeMessageSize += CodedOutputStream.computeMessageSize(6, getPrivateKeyProvider());
        }
        int serializedSize = iComputeMessageSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TlsCertificate)) {
            return super.equals(obj);
        }
        TlsCertificate tlsCertificate = (TlsCertificate) obj;
        if (hasCertificateChain() != tlsCertificate.hasCertificateChain()) {
            return false;
        }
        if ((hasCertificateChain() && !getCertificateChain().equals(tlsCertificate.getCertificateChain())) || hasPrivateKey() != tlsCertificate.hasPrivateKey()) {
            return false;
        }
        if ((hasPrivateKey() && !getPrivateKey().equals(tlsCertificate.getPrivateKey())) || hasPrivateKeyProvider() != tlsCertificate.hasPrivateKeyProvider()) {
            return false;
        }
        if ((hasPrivateKeyProvider() && !getPrivateKeyProvider().equals(tlsCertificate.getPrivateKeyProvider())) || hasPassword() != tlsCertificate.hasPassword()) {
            return false;
        }
        if ((!hasPassword() || getPassword().equals(tlsCertificate.getPassword())) && hasOcspStaple() == tlsCertificate.hasOcspStaple()) {
            return (!hasOcspStaple() || getOcspStaple().equals(tlsCertificate.getOcspStaple())) && getSignedCertificateTimestampList().equals(tlsCertificate.getSignedCertificateTimestampList()) && this.unknownFields.equals(tlsCertificate.unknownFields);
        }
        return false;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = 779 + getDescriptor().hashCode();
        if (hasCertificateChain()) {
            iHashCode = (((iHashCode * 37) + 1) * 53) + getCertificateChain().hashCode();
        }
        if (hasPrivateKey()) {
            iHashCode = (((iHashCode * 37) + 2) * 53) + getPrivateKey().hashCode();
        }
        if (hasPrivateKeyProvider()) {
            iHashCode = (((iHashCode * 37) + 6) * 53) + getPrivateKeyProvider().hashCode();
        }
        if (hasPassword()) {
            iHashCode = (((iHashCode * 37) + 3) * 53) + getPassword().hashCode();
        }
        if (hasOcspStaple()) {
            iHashCode = (((iHashCode * 37) + 4) * 53) + getOcspStaple().hashCode();
        }
        if (getSignedCertificateTimestampCount() > 0) {
            iHashCode = (((iHashCode * 37) + 5) * 53) + getSignedCertificateTimestampList().hashCode();
        }
        int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode2;
        return iHashCode2;
    }

    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m13837newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m13840toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements TlsCertificateOrBuilder {
        private int bitField0_;
        private SingleFieldBuilderV3<DataSource, DataSource.Builder, DataSourceOrBuilder> certificateChainBuilder_;
        private DataSource certificateChain_;
        private SingleFieldBuilderV3<DataSource, DataSource.Builder, DataSourceOrBuilder> ocspStapleBuilder_;
        private DataSource ocspStaple_;
        private SingleFieldBuilderV3<DataSource, DataSource.Builder, DataSourceOrBuilder> passwordBuilder_;
        private DataSource password_;
        private SingleFieldBuilderV3<DataSource, DataSource.Builder, DataSourceOrBuilder> privateKeyBuilder_;
        private SingleFieldBuilderV3<PrivateKeyProvider, PrivateKeyProvider.Builder, PrivateKeyProviderOrBuilder> privateKeyProviderBuilder_;
        private PrivateKeyProvider privateKeyProvider_;
        private DataSource privateKey_;
        private RepeatedFieldBuilderV3<DataSource, DataSource.Builder, DataSourceOrBuilder> signedCertificateTimestampBuilder_;
        private List<DataSource> signedCertificateTimestamp_;

        private Builder() {
            this.signedCertificateTimestamp_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.signedCertificateTimestamp_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return CommonProto.internal_static_envoy_api_v2_auth_TlsCertificate_descriptor;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth.TlsCertificateOrBuilder
        public boolean hasCertificateChain() {
            return (this.certificateChainBuilder_ == null && this.certificateChain_ == null) ? false : true;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth.TlsCertificateOrBuilder
        public boolean hasOcspStaple() {
            return (this.ocspStapleBuilder_ == null && this.ocspStaple_ == null) ? false : true;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth.TlsCertificateOrBuilder
        public boolean hasPassword() {
            return (this.passwordBuilder_ == null && this.password_ == null) ? false : true;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth.TlsCertificateOrBuilder
        public boolean hasPrivateKey() {
            return (this.privateKeyBuilder_ == null && this.privateKey_ == null) ? false : true;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth.TlsCertificateOrBuilder
        public boolean hasPrivateKeyProvider() {
            return (this.privateKeyProviderBuilder_ == null && this.privateKeyProvider_ == null) ? false : true;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return CommonProto.internal_static_envoy_api_v2_auth_TlsCertificate_fieldAccessorTable.ensureFieldAccessorsInitialized(TlsCertificate.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            if (TlsCertificate.alwaysUseFieldBuilders) {
                getSignedCertificateTimestampFieldBuilder();
            }
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m13851clear() {
            super.clear();
            if (this.certificateChainBuilder_ == null) {
                this.certificateChain_ = null;
            } else {
                this.certificateChain_ = null;
                this.certificateChainBuilder_ = null;
            }
            if (this.privateKeyBuilder_ == null) {
                this.privateKey_ = null;
            } else {
                this.privateKey_ = null;
                this.privateKeyBuilder_ = null;
            }
            if (this.privateKeyProviderBuilder_ == null) {
                this.privateKeyProvider_ = null;
            } else {
                this.privateKeyProvider_ = null;
                this.privateKeyProviderBuilder_ = null;
            }
            if (this.passwordBuilder_ == null) {
                this.password_ = null;
            } else {
                this.password_ = null;
                this.passwordBuilder_ = null;
            }
            if (this.ocspStapleBuilder_ == null) {
                this.ocspStaple_ = null;
            } else {
                this.ocspStaple_ = null;
                this.ocspStapleBuilder_ = null;
            }
            RepeatedFieldBuilderV3<DataSource, DataSource.Builder, DataSourceOrBuilder> repeatedFieldBuilderV3 = this.signedCertificateTimestampBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.signedCertificateTimestamp_ = Collections.emptyList();
                this.bitField0_ &= -2;
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return CommonProto.internal_static_envoy_api_v2_auth_TlsCertificate_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public TlsCertificate m13864getDefaultInstanceForType() {
            return TlsCertificate.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public TlsCertificate m13845build() throws UninitializedMessageException {
            TlsCertificate tlsCertificateM13847buildPartial = m13847buildPartial();
            if (tlsCertificateM13847buildPartial.isInitialized()) {
                return tlsCertificateM13847buildPartial;
            }
            throw newUninitializedMessageException(tlsCertificateM13847buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public TlsCertificate m13847buildPartial() {
            TlsCertificate tlsCertificate = new TlsCertificate(this);
            SingleFieldBuilderV3<DataSource, DataSource.Builder, DataSourceOrBuilder> singleFieldBuilderV3 = this.certificateChainBuilder_;
            if (singleFieldBuilderV3 == null) {
                tlsCertificate.certificateChain_ = this.certificateChain_;
            } else {
                tlsCertificate.certificateChain_ = singleFieldBuilderV3.build();
            }
            SingleFieldBuilderV3<DataSource, DataSource.Builder, DataSourceOrBuilder> singleFieldBuilderV32 = this.privateKeyBuilder_;
            if (singleFieldBuilderV32 == null) {
                tlsCertificate.privateKey_ = this.privateKey_;
            } else {
                tlsCertificate.privateKey_ = singleFieldBuilderV32.build();
            }
            SingleFieldBuilderV3<PrivateKeyProvider, PrivateKeyProvider.Builder, PrivateKeyProviderOrBuilder> singleFieldBuilderV33 = this.privateKeyProviderBuilder_;
            if (singleFieldBuilderV33 == null) {
                tlsCertificate.privateKeyProvider_ = this.privateKeyProvider_;
            } else {
                tlsCertificate.privateKeyProvider_ = singleFieldBuilderV33.build();
            }
            SingleFieldBuilderV3<DataSource, DataSource.Builder, DataSourceOrBuilder> singleFieldBuilderV34 = this.passwordBuilder_;
            if (singleFieldBuilderV34 == null) {
                tlsCertificate.password_ = this.password_;
            } else {
                tlsCertificate.password_ = singleFieldBuilderV34.build();
            }
            SingleFieldBuilderV3<DataSource, DataSource.Builder, DataSourceOrBuilder> singleFieldBuilderV35 = this.ocspStapleBuilder_;
            if (singleFieldBuilderV35 == null) {
                tlsCertificate.ocspStaple_ = this.ocspStaple_;
            } else {
                tlsCertificate.ocspStaple_ = singleFieldBuilderV35.build();
            }
            RepeatedFieldBuilderV3<DataSource, DataSource.Builder, DataSourceOrBuilder> repeatedFieldBuilderV3 = this.signedCertificateTimestampBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                if ((this.bitField0_ & 1) != 0) {
                    this.signedCertificateTimestamp_ = Collections.unmodifiableList(this.signedCertificateTimestamp_);
                    this.bitField0_ &= -2;
                }
                tlsCertificate.signedCertificateTimestamp_ = this.signedCertificateTimestamp_;
            } else {
                tlsCertificate.signedCertificateTimestamp_ = repeatedFieldBuilderV3.build();
            }
            onBuilt();
            return tlsCertificate;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m13863clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m13875setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m13853clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m13856clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m13877setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m13843addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m13868mergeFrom(Message message) {
            if (message instanceof TlsCertificate) {
                return mergeFrom((TlsCertificate) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(TlsCertificate tlsCertificate) {
            if (tlsCertificate == TlsCertificate.getDefaultInstance()) {
                return this;
            }
            if (tlsCertificate.hasCertificateChain()) {
                mergeCertificateChain(tlsCertificate.getCertificateChain());
            }
            if (tlsCertificate.hasPrivateKey()) {
                mergePrivateKey(tlsCertificate.getPrivateKey());
            }
            if (tlsCertificate.hasPrivateKeyProvider()) {
                mergePrivateKeyProvider(tlsCertificate.getPrivateKeyProvider());
            }
            if (tlsCertificate.hasPassword()) {
                mergePassword(tlsCertificate.getPassword());
            }
            if (tlsCertificate.hasOcspStaple()) {
                mergeOcspStaple(tlsCertificate.getOcspStaple());
            }
            if (this.signedCertificateTimestampBuilder_ == null) {
                if (!tlsCertificate.signedCertificateTimestamp_.isEmpty()) {
                    if (this.signedCertificateTimestamp_.isEmpty()) {
                        this.signedCertificateTimestamp_ = tlsCertificate.signedCertificateTimestamp_;
                        this.bitField0_ &= -2;
                    } else {
                        ensureSignedCertificateTimestampIsMutable();
                        this.signedCertificateTimestamp_.addAll(tlsCertificate.signedCertificateTimestamp_);
                    }
                    onChanged();
                }
            } else if (!tlsCertificate.signedCertificateTimestamp_.isEmpty()) {
                if (!this.signedCertificateTimestampBuilder_.isEmpty()) {
                    this.signedCertificateTimestampBuilder_.addAllMessages(tlsCertificate.signedCertificateTimestamp_);
                } else {
                    this.signedCertificateTimestampBuilder_.dispose();
                    this.signedCertificateTimestampBuilder_ = null;
                    this.signedCertificateTimestamp_ = tlsCertificate.signedCertificateTimestamp_;
                    this.bitField0_ &= -2;
                    this.signedCertificateTimestampBuilder_ = TlsCertificate.alwaysUseFieldBuilders ? getSignedCertificateTimestampFieldBuilder() : null;
                }
            }
            m13873mergeUnknownFields(tlsCertificate.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth.TlsCertificate.Builder m13869mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth.TlsCertificate.access$1200()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth.TlsCertificate r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth.TlsCertificate) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth.TlsCertificate r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth.TlsCertificate) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth.TlsCertificate.Builder.m13869mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth.TlsCertificate$Builder");
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth.TlsCertificateOrBuilder
        public DataSource getCertificateChain() {
            SingleFieldBuilderV3<DataSource, DataSource.Builder, DataSourceOrBuilder> singleFieldBuilderV3 = this.certificateChainBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            DataSource dataSource = this.certificateChain_;
            return dataSource == null ? DataSource.getDefaultInstance() : dataSource;
        }

        public Builder setCertificateChain(DataSource dataSource) {
            SingleFieldBuilderV3<DataSource, DataSource.Builder, DataSourceOrBuilder> singleFieldBuilderV3 = this.certificateChainBuilder_;
            if (singleFieldBuilderV3 == null) {
                dataSource.getClass();
                this.certificateChain_ = dataSource;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(dataSource);
            }
            return this;
        }

        public Builder setCertificateChain(DataSource.Builder builder) {
            SingleFieldBuilderV3<DataSource, DataSource.Builder, DataSourceOrBuilder> singleFieldBuilderV3 = this.certificateChainBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.certificateChain_ = builder.m14725build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m14725build());
            }
            return this;
        }

        public Builder mergeCertificateChain(DataSource dataSource) {
            SingleFieldBuilderV3<DataSource, DataSource.Builder, DataSourceOrBuilder> singleFieldBuilderV3 = this.certificateChainBuilder_;
            if (singleFieldBuilderV3 == null) {
                DataSource dataSource2 = this.certificateChain_;
                if (dataSource2 != null) {
                    this.certificateChain_ = DataSource.newBuilder(dataSource2).mergeFrom(dataSource).m14727buildPartial();
                } else {
                    this.certificateChain_ = dataSource;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(dataSource);
            }
            return this;
        }

        public Builder clearCertificateChain() {
            if (this.certificateChainBuilder_ == null) {
                this.certificateChain_ = null;
                onChanged();
            } else {
                this.certificateChain_ = null;
                this.certificateChainBuilder_ = null;
            }
            return this;
        }

        public DataSource.Builder getCertificateChainBuilder() {
            onChanged();
            return getCertificateChainFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth.TlsCertificateOrBuilder
        public DataSourceOrBuilder getCertificateChainOrBuilder() {
            SingleFieldBuilderV3<DataSource, DataSource.Builder, DataSourceOrBuilder> singleFieldBuilderV3 = this.certificateChainBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (DataSourceOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            DataSource dataSource = this.certificateChain_;
            return dataSource == null ? DataSource.getDefaultInstance() : dataSource;
        }

        private SingleFieldBuilderV3<DataSource, DataSource.Builder, DataSourceOrBuilder> getCertificateChainFieldBuilder() {
            if (this.certificateChainBuilder_ == null) {
                this.certificateChainBuilder_ = new SingleFieldBuilderV3<>(getCertificateChain(), getParentForChildren(), isClean());
                this.certificateChain_ = null;
            }
            return this.certificateChainBuilder_;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth.TlsCertificateOrBuilder
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

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth.TlsCertificateOrBuilder
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

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth.TlsCertificateOrBuilder
        public PrivateKeyProvider getPrivateKeyProvider() {
            SingleFieldBuilderV3<PrivateKeyProvider, PrivateKeyProvider.Builder, PrivateKeyProviderOrBuilder> singleFieldBuilderV3 = this.privateKeyProviderBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            PrivateKeyProvider privateKeyProvider = this.privateKeyProvider_;
            return privateKeyProvider == null ? PrivateKeyProvider.getDefaultInstance() : privateKeyProvider;
        }

        public Builder setPrivateKeyProvider(PrivateKeyProvider privateKeyProvider) {
            SingleFieldBuilderV3<PrivateKeyProvider, PrivateKeyProvider.Builder, PrivateKeyProviderOrBuilder> singleFieldBuilderV3 = this.privateKeyProviderBuilder_;
            if (singleFieldBuilderV3 == null) {
                privateKeyProvider.getClass();
                this.privateKeyProvider_ = privateKeyProvider;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(privateKeyProvider);
            }
            return this;
        }

        public Builder setPrivateKeyProvider(PrivateKeyProvider.Builder builder) {
            SingleFieldBuilderV3<PrivateKeyProvider, PrivateKeyProvider.Builder, PrivateKeyProviderOrBuilder> singleFieldBuilderV3 = this.privateKeyProviderBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.privateKeyProvider_ = builder.m13707build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m13707build());
            }
            return this;
        }

        public Builder mergePrivateKeyProvider(PrivateKeyProvider privateKeyProvider) {
            SingleFieldBuilderV3<PrivateKeyProvider, PrivateKeyProvider.Builder, PrivateKeyProviderOrBuilder> singleFieldBuilderV3 = this.privateKeyProviderBuilder_;
            if (singleFieldBuilderV3 == null) {
                PrivateKeyProvider privateKeyProvider2 = this.privateKeyProvider_;
                if (privateKeyProvider2 != null) {
                    this.privateKeyProvider_ = PrivateKeyProvider.newBuilder(privateKeyProvider2).mergeFrom(privateKeyProvider).m13709buildPartial();
                } else {
                    this.privateKeyProvider_ = privateKeyProvider;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(privateKeyProvider);
            }
            return this;
        }

        public Builder clearPrivateKeyProvider() {
            if (this.privateKeyProviderBuilder_ == null) {
                this.privateKeyProvider_ = null;
                onChanged();
            } else {
                this.privateKeyProvider_ = null;
                this.privateKeyProviderBuilder_ = null;
            }
            return this;
        }

        public PrivateKeyProvider.Builder getPrivateKeyProviderBuilder() {
            onChanged();
            return getPrivateKeyProviderFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth.TlsCertificateOrBuilder
        public PrivateKeyProviderOrBuilder getPrivateKeyProviderOrBuilder() {
            SingleFieldBuilderV3<PrivateKeyProvider, PrivateKeyProvider.Builder, PrivateKeyProviderOrBuilder> singleFieldBuilderV3 = this.privateKeyProviderBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (PrivateKeyProviderOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            PrivateKeyProvider privateKeyProvider = this.privateKeyProvider_;
            return privateKeyProvider == null ? PrivateKeyProvider.getDefaultInstance() : privateKeyProvider;
        }

        private SingleFieldBuilderV3<PrivateKeyProvider, PrivateKeyProvider.Builder, PrivateKeyProviderOrBuilder> getPrivateKeyProviderFieldBuilder() {
            if (this.privateKeyProviderBuilder_ == null) {
                this.privateKeyProviderBuilder_ = new SingleFieldBuilderV3<>(getPrivateKeyProvider(), getParentForChildren(), isClean());
                this.privateKeyProvider_ = null;
            }
            return this.privateKeyProviderBuilder_;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth.TlsCertificateOrBuilder
        public DataSource getPassword() {
            SingleFieldBuilderV3<DataSource, DataSource.Builder, DataSourceOrBuilder> singleFieldBuilderV3 = this.passwordBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            DataSource dataSource = this.password_;
            return dataSource == null ? DataSource.getDefaultInstance() : dataSource;
        }

        public Builder setPassword(DataSource dataSource) {
            SingleFieldBuilderV3<DataSource, DataSource.Builder, DataSourceOrBuilder> singleFieldBuilderV3 = this.passwordBuilder_;
            if (singleFieldBuilderV3 == null) {
                dataSource.getClass();
                this.password_ = dataSource;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(dataSource);
            }
            return this;
        }

        public Builder setPassword(DataSource.Builder builder) {
            SingleFieldBuilderV3<DataSource, DataSource.Builder, DataSourceOrBuilder> singleFieldBuilderV3 = this.passwordBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.password_ = builder.m14725build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m14725build());
            }
            return this;
        }

        public Builder mergePassword(DataSource dataSource) {
            SingleFieldBuilderV3<DataSource, DataSource.Builder, DataSourceOrBuilder> singleFieldBuilderV3 = this.passwordBuilder_;
            if (singleFieldBuilderV3 == null) {
                DataSource dataSource2 = this.password_;
                if (dataSource2 != null) {
                    this.password_ = DataSource.newBuilder(dataSource2).mergeFrom(dataSource).m14727buildPartial();
                } else {
                    this.password_ = dataSource;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(dataSource);
            }
            return this;
        }

        public Builder clearPassword() {
            if (this.passwordBuilder_ == null) {
                this.password_ = null;
                onChanged();
            } else {
                this.password_ = null;
                this.passwordBuilder_ = null;
            }
            return this;
        }

        public DataSource.Builder getPasswordBuilder() {
            onChanged();
            return getPasswordFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth.TlsCertificateOrBuilder
        public DataSourceOrBuilder getPasswordOrBuilder() {
            SingleFieldBuilderV3<DataSource, DataSource.Builder, DataSourceOrBuilder> singleFieldBuilderV3 = this.passwordBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (DataSourceOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            DataSource dataSource = this.password_;
            return dataSource == null ? DataSource.getDefaultInstance() : dataSource;
        }

        private SingleFieldBuilderV3<DataSource, DataSource.Builder, DataSourceOrBuilder> getPasswordFieldBuilder() {
            if (this.passwordBuilder_ == null) {
                this.passwordBuilder_ = new SingleFieldBuilderV3<>(getPassword(), getParentForChildren(), isClean());
                this.password_ = null;
            }
            return this.passwordBuilder_;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth.TlsCertificateOrBuilder
        public DataSource getOcspStaple() {
            SingleFieldBuilderV3<DataSource, DataSource.Builder, DataSourceOrBuilder> singleFieldBuilderV3 = this.ocspStapleBuilder_;
            if (singleFieldBuilderV3 != null) {
                return singleFieldBuilderV3.getMessage();
            }
            DataSource dataSource = this.ocspStaple_;
            return dataSource == null ? DataSource.getDefaultInstance() : dataSource;
        }

        public Builder setOcspStaple(DataSource dataSource) {
            SingleFieldBuilderV3<DataSource, DataSource.Builder, DataSourceOrBuilder> singleFieldBuilderV3 = this.ocspStapleBuilder_;
            if (singleFieldBuilderV3 == null) {
                dataSource.getClass();
                this.ocspStaple_ = dataSource;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(dataSource);
            }
            return this;
        }

        public Builder setOcspStaple(DataSource.Builder builder) {
            SingleFieldBuilderV3<DataSource, DataSource.Builder, DataSourceOrBuilder> singleFieldBuilderV3 = this.ocspStapleBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.ocspStaple_ = builder.m14725build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builder.m14725build());
            }
            return this;
        }

        public Builder mergeOcspStaple(DataSource dataSource) {
            SingleFieldBuilderV3<DataSource, DataSource.Builder, DataSourceOrBuilder> singleFieldBuilderV3 = this.ocspStapleBuilder_;
            if (singleFieldBuilderV3 == null) {
                DataSource dataSource2 = this.ocspStaple_;
                if (dataSource2 != null) {
                    this.ocspStaple_ = DataSource.newBuilder(dataSource2).mergeFrom(dataSource).m14727buildPartial();
                } else {
                    this.ocspStaple_ = dataSource;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(dataSource);
            }
            return this;
        }

        public Builder clearOcspStaple() {
            if (this.ocspStapleBuilder_ == null) {
                this.ocspStaple_ = null;
                onChanged();
            } else {
                this.ocspStaple_ = null;
                this.ocspStapleBuilder_ = null;
            }
            return this;
        }

        public DataSource.Builder getOcspStapleBuilder() {
            onChanged();
            return getOcspStapleFieldBuilder().getBuilder();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth.TlsCertificateOrBuilder
        public DataSourceOrBuilder getOcspStapleOrBuilder() {
            SingleFieldBuilderV3<DataSource, DataSource.Builder, DataSourceOrBuilder> singleFieldBuilderV3 = this.ocspStapleBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (DataSourceOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            DataSource dataSource = this.ocspStaple_;
            return dataSource == null ? DataSource.getDefaultInstance() : dataSource;
        }

        private SingleFieldBuilderV3<DataSource, DataSource.Builder, DataSourceOrBuilder> getOcspStapleFieldBuilder() {
            if (this.ocspStapleBuilder_ == null) {
                this.ocspStapleBuilder_ = new SingleFieldBuilderV3<>(getOcspStaple(), getParentForChildren(), isClean());
                this.ocspStaple_ = null;
            }
            return this.ocspStapleBuilder_;
        }

        private void ensureSignedCertificateTimestampIsMutable() {
            if ((this.bitField0_ & 1) == 0) {
                this.signedCertificateTimestamp_ = new ArrayList(this.signedCertificateTimestamp_);
                this.bitField0_ |= 1;
            }
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth.TlsCertificateOrBuilder
        public List<DataSource> getSignedCertificateTimestampList() {
            RepeatedFieldBuilderV3<DataSource, DataSource.Builder, DataSourceOrBuilder> repeatedFieldBuilderV3 = this.signedCertificateTimestampBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return Collections.unmodifiableList(this.signedCertificateTimestamp_);
            }
            return repeatedFieldBuilderV3.getMessageList();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth.TlsCertificateOrBuilder
        public int getSignedCertificateTimestampCount() {
            RepeatedFieldBuilderV3<DataSource, DataSource.Builder, DataSourceOrBuilder> repeatedFieldBuilderV3 = this.signedCertificateTimestampBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.signedCertificateTimestamp_.size();
            }
            return repeatedFieldBuilderV3.getCount();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth.TlsCertificateOrBuilder
        public DataSource getSignedCertificateTimestamp(int i) {
            RepeatedFieldBuilderV3<DataSource, DataSource.Builder, DataSourceOrBuilder> repeatedFieldBuilderV3 = this.signedCertificateTimestampBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.signedCertificateTimestamp_.get(i);
            }
            return repeatedFieldBuilderV3.getMessage(i);
        }

        public Builder setSignedCertificateTimestamp(int i, DataSource dataSource) {
            RepeatedFieldBuilderV3<DataSource, DataSource.Builder, DataSourceOrBuilder> repeatedFieldBuilderV3 = this.signedCertificateTimestampBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                dataSource.getClass();
                ensureSignedCertificateTimestampIsMutable();
                this.signedCertificateTimestamp_.set(i, dataSource);
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, dataSource);
            }
            return this;
        }

        public Builder setSignedCertificateTimestamp(int i, DataSource.Builder builder) {
            RepeatedFieldBuilderV3<DataSource, DataSource.Builder, DataSourceOrBuilder> repeatedFieldBuilderV3 = this.signedCertificateTimestampBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureSignedCertificateTimestampIsMutable();
                this.signedCertificateTimestamp_.set(i, builder.m14725build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.setMessage(i, builder.m14725build());
            }
            return this;
        }

        public Builder addSignedCertificateTimestamp(DataSource dataSource) {
            RepeatedFieldBuilderV3<DataSource, DataSource.Builder, DataSourceOrBuilder> repeatedFieldBuilderV3 = this.signedCertificateTimestampBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                dataSource.getClass();
                ensureSignedCertificateTimestampIsMutable();
                this.signedCertificateTimestamp_.add(dataSource);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(dataSource);
            }
            return this;
        }

        public Builder addSignedCertificateTimestamp(int i, DataSource dataSource) {
            RepeatedFieldBuilderV3<DataSource, DataSource.Builder, DataSourceOrBuilder> repeatedFieldBuilderV3 = this.signedCertificateTimestampBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                dataSource.getClass();
                ensureSignedCertificateTimestampIsMutable();
                this.signedCertificateTimestamp_.add(i, dataSource);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, dataSource);
            }
            return this;
        }

        public Builder addSignedCertificateTimestamp(DataSource.Builder builder) {
            RepeatedFieldBuilderV3<DataSource, DataSource.Builder, DataSourceOrBuilder> repeatedFieldBuilderV3 = this.signedCertificateTimestampBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureSignedCertificateTimestampIsMutable();
                this.signedCertificateTimestamp_.add(builder.m14725build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(builder.m14725build());
            }
            return this;
        }

        public Builder addSignedCertificateTimestamp(int i, DataSource.Builder builder) {
            RepeatedFieldBuilderV3<DataSource, DataSource.Builder, DataSourceOrBuilder> repeatedFieldBuilderV3 = this.signedCertificateTimestampBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureSignedCertificateTimestampIsMutable();
                this.signedCertificateTimestamp_.add(i, builder.m14725build());
                onChanged();
            } else {
                repeatedFieldBuilderV3.addMessage(i, builder.m14725build());
            }
            return this;
        }

        public Builder addAllSignedCertificateTimestamp(Iterable<? extends DataSource> iterable) {
            RepeatedFieldBuilderV3<DataSource, DataSource.Builder, DataSourceOrBuilder> repeatedFieldBuilderV3 = this.signedCertificateTimestampBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureSignedCertificateTimestampIsMutable();
                AbstractMessageLite.Builder.addAll(iterable, this.signedCertificateTimestamp_);
                onChanged();
            } else {
                repeatedFieldBuilderV3.addAllMessages(iterable);
            }
            return this;
        }

        public Builder clearSignedCertificateTimestamp() {
            RepeatedFieldBuilderV3<DataSource, DataSource.Builder, DataSourceOrBuilder> repeatedFieldBuilderV3 = this.signedCertificateTimestampBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                this.signedCertificateTimestamp_ = Collections.emptyList();
                this.bitField0_ &= -2;
                onChanged();
            } else {
                repeatedFieldBuilderV3.clear();
            }
            return this;
        }

        public Builder removeSignedCertificateTimestamp(int i) {
            RepeatedFieldBuilderV3<DataSource, DataSource.Builder, DataSourceOrBuilder> repeatedFieldBuilderV3 = this.signedCertificateTimestampBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                ensureSignedCertificateTimestampIsMutable();
                this.signedCertificateTimestamp_.remove(i);
                onChanged();
            } else {
                repeatedFieldBuilderV3.remove(i);
            }
            return this;
        }

        public DataSource.Builder getSignedCertificateTimestampBuilder(int i) {
            return getSignedCertificateTimestampFieldBuilder().getBuilder(i);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth.TlsCertificateOrBuilder
        public DataSourceOrBuilder getSignedCertificateTimestampOrBuilder(int i) {
            RepeatedFieldBuilderV3<DataSource, DataSource.Builder, DataSourceOrBuilder> repeatedFieldBuilderV3 = this.signedCertificateTimestampBuilder_;
            if (repeatedFieldBuilderV3 == null) {
                return this.signedCertificateTimestamp_.get(i);
            }
            return (DataSourceOrBuilder) repeatedFieldBuilderV3.getMessageOrBuilder(i);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.auth.TlsCertificateOrBuilder
        public List<? extends DataSourceOrBuilder> getSignedCertificateTimestampOrBuilderList() {
            RepeatedFieldBuilderV3<DataSource, DataSource.Builder, DataSourceOrBuilder> repeatedFieldBuilderV3 = this.signedCertificateTimestampBuilder_;
            if (repeatedFieldBuilderV3 != null) {
                return repeatedFieldBuilderV3.getMessageOrBuilderList();
            }
            return Collections.unmodifiableList(this.signedCertificateTimestamp_);
        }

        public DataSource.Builder addSignedCertificateTimestampBuilder() {
            return getSignedCertificateTimestampFieldBuilder().addBuilder(DataSource.getDefaultInstance());
        }

        public DataSource.Builder addSignedCertificateTimestampBuilder(int i) {
            return getSignedCertificateTimestampFieldBuilder().addBuilder(i, DataSource.getDefaultInstance());
        }

        public List<DataSource.Builder> getSignedCertificateTimestampBuilderList() {
            return getSignedCertificateTimestampFieldBuilder().getBuilderList();
        }

        private RepeatedFieldBuilderV3<DataSource, DataSource.Builder, DataSourceOrBuilder> getSignedCertificateTimestampFieldBuilder() {
            if (this.signedCertificateTimestampBuilder_ == null) {
                this.signedCertificateTimestampBuilder_ = new RepeatedFieldBuilderV3<>(this.signedCertificateTimestamp_, (this.bitField0_ & 1) != 0, getParentForChildren(), isClean());
                this.signedCertificateTimestamp_ = null;
            }
            return this.signedCertificateTimestampBuilder_;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m13879setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m13873mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }
}
