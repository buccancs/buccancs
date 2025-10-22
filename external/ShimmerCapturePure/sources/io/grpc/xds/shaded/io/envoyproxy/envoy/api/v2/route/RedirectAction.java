package io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route;

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
import com.google.protobuf.Parser;
import com.google.protobuf.ProtocolMessageEnum;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: classes5.dex */
public final class RedirectAction extends GeneratedMessageV3 implements RedirectActionOrBuilder {
    public static final int HOST_REDIRECT_FIELD_NUMBER = 1;
    public static final int HTTPS_REDIRECT_FIELD_NUMBER = 4;
    public static final int PATH_REDIRECT_FIELD_NUMBER = 2;
    public static final int PORT_REDIRECT_FIELD_NUMBER = 8;
    public static final int PREFIX_REWRITE_FIELD_NUMBER = 5;
    public static final int RESPONSE_CODE_FIELD_NUMBER = 3;
    public static final int SCHEME_REDIRECT_FIELD_NUMBER = 7;
    public static final int STRIP_QUERY_FIELD_NUMBER = 6;
    private static final long serialVersionUID = 0;
    private static final RedirectAction DEFAULT_INSTANCE = new RedirectAction();
    private static final Parser<RedirectAction> PARSER = new AbstractParser<RedirectAction>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RedirectAction.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public RedirectAction m18555parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new RedirectAction(codedInputStream, extensionRegistryLite);
        }
    };
    private volatile Object hostRedirect_;
    private byte memoizedIsInitialized;
    private int pathRewriteSpecifierCase_;
    private Object pathRewriteSpecifier_;
    private int portRedirect_;
    private int responseCode_;
    private int schemeRewriteSpecifierCase_;
    private Object schemeRewriteSpecifier_;
    private boolean stripQuery_;

    private RedirectAction(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.schemeRewriteSpecifierCase_ = 0;
        this.pathRewriteSpecifierCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
    }

    private RedirectAction() {
        this.schemeRewriteSpecifierCase_ = 0;
        this.pathRewriteSpecifierCase_ = 0;
        this.memoizedIsInitialized = (byte) -1;
        this.hostRedirect_ = "";
        this.responseCode_ = 0;
    }

    private RedirectAction(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                            this.hostRedirect_ = codedInputStream.readStringRequireUtf8();
                        } else if (tag == 18) {
                            String stringRequireUtf8 = codedInputStream.readStringRequireUtf8();
                            this.pathRewriteSpecifierCase_ = 2;
                            this.pathRewriteSpecifier_ = stringRequireUtf8;
                        } else if (tag == 24) {
                            this.responseCode_ = codedInputStream.readEnum();
                        } else if (tag == 32) {
                            this.schemeRewriteSpecifierCase_ = 4;
                            this.schemeRewriteSpecifier_ = Boolean.valueOf(codedInputStream.readBool());
                        } else if (tag == 42) {
                            String stringRequireUtf82 = codedInputStream.readStringRequireUtf8();
                            this.pathRewriteSpecifierCase_ = 5;
                            this.pathRewriteSpecifier_ = stringRequireUtf82;
                        } else if (tag == 48) {
                            this.stripQuery_ = codedInputStream.readBool();
                        } else if (tag == 58) {
                            String stringRequireUtf83 = codedInputStream.readStringRequireUtf8();
                            this.schemeRewriteSpecifierCase_ = 7;
                            this.schemeRewriteSpecifier_ = stringRequireUtf83;
                        } else if (tag == 64) {
                            this.portRedirect_ = codedInputStream.readUInt32();
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
                this.unknownFields = builderNewBuilder.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static RedirectAction getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<RedirectAction> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return RouteComponentsProto.internal_static_envoy_api_v2_route_RedirectAction_descriptor;
    }

    public static RedirectAction parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (RedirectAction) PARSER.parseFrom(byteBuffer);
    }

    public static RedirectAction parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (RedirectAction) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static RedirectAction parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (RedirectAction) PARSER.parseFrom(byteString);
    }

    public static RedirectAction parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (RedirectAction) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static RedirectAction parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (RedirectAction) PARSER.parseFrom(bArr);
    }

    public static RedirectAction parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (RedirectAction) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static RedirectAction parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static RedirectAction parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static RedirectAction parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static RedirectAction parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static RedirectAction parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static RedirectAction parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m18553toBuilder();
    }

    public static Builder newBuilder(RedirectAction redirectAction) {
        return DEFAULT_INSTANCE.m18553toBuilder().mergeFrom(redirectAction);
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public RedirectAction m18548getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public Parser<RedirectAction> getParserForType() {
        return PARSER;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RedirectActionOrBuilder
    public int getPortRedirect() {
        return this.portRedirect_;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RedirectActionOrBuilder
    public int getResponseCodeValue() {
        return this.responseCode_;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RedirectActionOrBuilder
    public boolean getStripQuery() {
        return this.stripQuery_;
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
        return new RedirectAction();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return RouteComponentsProto.internal_static_envoy_api_v2_route_RedirectAction_fieldAccessorTable.ensureFieldAccessorsInitialized(RedirectAction.class, Builder.class);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RedirectActionOrBuilder
    public SchemeRewriteSpecifierCase getSchemeRewriteSpecifierCase() {
        return SchemeRewriteSpecifierCase.forNumber(this.schemeRewriteSpecifierCase_);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RedirectActionOrBuilder
    public PathRewriteSpecifierCase getPathRewriteSpecifierCase() {
        return PathRewriteSpecifierCase.forNumber(this.pathRewriteSpecifierCase_);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RedirectActionOrBuilder
    public boolean getHttpsRedirect() {
        if (this.schemeRewriteSpecifierCase_ == 4) {
            return ((Boolean) this.schemeRewriteSpecifier_).booleanValue();
        }
        return false;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RedirectActionOrBuilder
    public String getSchemeRedirect() {
        String str = this.schemeRewriteSpecifierCase_ == 7 ? this.schemeRewriteSpecifier_ : "";
        if (str instanceof String) {
            return (String) str;
        }
        String stringUtf8 = ((ByteString) str).toStringUtf8();
        if (this.schemeRewriteSpecifierCase_ == 7) {
            this.schemeRewriteSpecifier_ = stringUtf8;
        }
        return stringUtf8;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RedirectActionOrBuilder
    public ByteString getSchemeRedirectBytes() {
        String str = this.schemeRewriteSpecifierCase_ == 7 ? this.schemeRewriteSpecifier_ : "";
        if (str instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) str);
            if (this.schemeRewriteSpecifierCase_ == 7) {
                this.schemeRewriteSpecifier_ = byteStringCopyFromUtf8;
            }
            return byteStringCopyFromUtf8;
        }
        return (ByteString) str;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RedirectActionOrBuilder
    public String getHostRedirect() {
        Object obj = this.hostRedirect_;
        if (obj instanceof String) {
            return (String) obj;
        }
        String stringUtf8 = ((ByteString) obj).toStringUtf8();
        this.hostRedirect_ = stringUtf8;
        return stringUtf8;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RedirectActionOrBuilder
    public ByteString getHostRedirectBytes() {
        Object obj = this.hostRedirect_;
        if (obj instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
            this.hostRedirect_ = byteStringCopyFromUtf8;
            return byteStringCopyFromUtf8;
        }
        return (ByteString) obj;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RedirectActionOrBuilder
    public String getPathRedirect() {
        String str = this.pathRewriteSpecifierCase_ == 2 ? this.pathRewriteSpecifier_ : "";
        if (str instanceof String) {
            return (String) str;
        }
        String stringUtf8 = ((ByteString) str).toStringUtf8();
        if (this.pathRewriteSpecifierCase_ == 2) {
            this.pathRewriteSpecifier_ = stringUtf8;
        }
        return stringUtf8;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RedirectActionOrBuilder
    public ByteString getPathRedirectBytes() {
        String str = this.pathRewriteSpecifierCase_ == 2 ? this.pathRewriteSpecifier_ : "";
        if (str instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) str);
            if (this.pathRewriteSpecifierCase_ == 2) {
                this.pathRewriteSpecifier_ = byteStringCopyFromUtf8;
            }
            return byteStringCopyFromUtf8;
        }
        return (ByteString) str;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RedirectActionOrBuilder
    public String getPrefixRewrite() {
        String str = this.pathRewriteSpecifierCase_ == 5 ? this.pathRewriteSpecifier_ : "";
        if (str instanceof String) {
            return (String) str;
        }
        String stringUtf8 = ((ByteString) str).toStringUtf8();
        if (this.pathRewriteSpecifierCase_ == 5) {
            this.pathRewriteSpecifier_ = stringUtf8;
        }
        return stringUtf8;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RedirectActionOrBuilder
    public ByteString getPrefixRewriteBytes() {
        String str = this.pathRewriteSpecifierCase_ == 5 ? this.pathRewriteSpecifier_ : "";
        if (str instanceof String) {
            ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) str);
            if (this.pathRewriteSpecifierCase_ == 5) {
                this.pathRewriteSpecifier_ = byteStringCopyFromUtf8;
            }
            return byteStringCopyFromUtf8;
        }
        return (ByteString) str;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RedirectActionOrBuilder
    public RedirectResponseCode getResponseCode() {
        RedirectResponseCode redirectResponseCodeValueOf = RedirectResponseCode.valueOf(this.responseCode_);
        return redirectResponseCodeValueOf == null ? RedirectResponseCode.UNRECOGNIZED : redirectResponseCodeValueOf;
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (!getHostRedirectBytes().isEmpty()) {
            GeneratedMessageV3.writeString(codedOutputStream, 1, this.hostRedirect_);
        }
        if (this.pathRewriteSpecifierCase_ == 2) {
            GeneratedMessageV3.writeString(codedOutputStream, 2, this.pathRewriteSpecifier_);
        }
        if (this.responseCode_ != RedirectResponseCode.MOVED_PERMANENTLY.getNumber()) {
            codedOutputStream.writeEnum(3, this.responseCode_);
        }
        if (this.schemeRewriteSpecifierCase_ == 4) {
            codedOutputStream.writeBool(4, ((Boolean) this.schemeRewriteSpecifier_).booleanValue());
        }
        if (this.pathRewriteSpecifierCase_ == 5) {
            GeneratedMessageV3.writeString(codedOutputStream, 5, this.pathRewriteSpecifier_);
        }
        boolean z = this.stripQuery_;
        if (z) {
            codedOutputStream.writeBool(6, z);
        }
        if (this.schemeRewriteSpecifierCase_ == 7) {
            GeneratedMessageV3.writeString(codedOutputStream, 7, this.schemeRewriteSpecifier_);
        }
        int i = this.portRedirect_;
        if (i != 0) {
            codedOutputStream.writeUInt32(8, i);
        }
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeStringSize = !getHostRedirectBytes().isEmpty() ? GeneratedMessageV3.computeStringSize(1, this.hostRedirect_) : 0;
        if (this.pathRewriteSpecifierCase_ == 2) {
            iComputeStringSize += GeneratedMessageV3.computeStringSize(2, this.pathRewriteSpecifier_);
        }
        if (this.responseCode_ != RedirectResponseCode.MOVED_PERMANENTLY.getNumber()) {
            iComputeStringSize += CodedOutputStream.computeEnumSize(3, this.responseCode_);
        }
        if (this.schemeRewriteSpecifierCase_ == 4) {
            iComputeStringSize += CodedOutputStream.computeBoolSize(4, ((Boolean) this.schemeRewriteSpecifier_).booleanValue());
        }
        if (this.pathRewriteSpecifierCase_ == 5) {
            iComputeStringSize += GeneratedMessageV3.computeStringSize(5, this.pathRewriteSpecifier_);
        }
        boolean z = this.stripQuery_;
        if (z) {
            iComputeStringSize += CodedOutputStream.computeBoolSize(6, z);
        }
        if (this.schemeRewriteSpecifierCase_ == 7) {
            iComputeStringSize += GeneratedMessageV3.computeStringSize(7, this.schemeRewriteSpecifier_);
        }
        int i2 = this.portRedirect_;
        if (i2 != 0) {
            iComputeStringSize += CodedOutputStream.computeUInt32Size(8, i2);
        }
        int serializedSize = iComputeStringSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof RedirectAction)) {
            return super.equals(obj);
        }
        RedirectAction redirectAction = (RedirectAction) obj;
        if (!getHostRedirect().equals(redirectAction.getHostRedirect()) || getPortRedirect() != redirectAction.getPortRedirect() || this.responseCode_ != redirectAction.responseCode_ || getStripQuery() != redirectAction.getStripQuery() || !getSchemeRewriteSpecifierCase().equals(redirectAction.getSchemeRewriteSpecifierCase())) {
            return false;
        }
        int i = this.schemeRewriteSpecifierCase_;
        if (i == 4) {
            if (getHttpsRedirect() != redirectAction.getHttpsRedirect()) {
                return false;
            }
        } else if (i == 7 && !getSchemeRedirect().equals(redirectAction.getSchemeRedirect())) {
            return false;
        }
        if (!getPathRewriteSpecifierCase().equals(redirectAction.getPathRewriteSpecifierCase())) {
            return false;
        }
        int i2 = this.pathRewriteSpecifierCase_;
        if (i2 == 2) {
            if (!getPathRedirect().equals(redirectAction.getPathRedirect())) {
                return false;
            }
        } else if (i2 == 5 && !getPrefixRewrite().equals(redirectAction.getPrefixRewrite())) {
            return false;
        }
        return this.unknownFields.equals(redirectAction.unknownFields);
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0080  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public int hashCode() {
        /*
            r3 = this;
            int r0 = r3.memoizedHashCode
            if (r0 == 0) goto L7
            int r0 = r3.memoizedHashCode
            return r0
        L7:
            com.google.protobuf.Descriptors$Descriptor r0 = getDescriptor()
            int r0 = r0.hashCode()
            r1 = 779(0x30b, float:1.092E-42)
            int r1 = r1 + r0
            int r1 = r1 * 37
            int r1 = r1 + 1
            int r1 = r1 * 53
            java.lang.String r0 = r3.getHostRedirect()
            int r0 = r0.hashCode()
            int r1 = r1 + r0
            int r1 = r1 * 37
            int r1 = r1 + 8
            int r1 = r1 * 53
            int r0 = r3.getPortRedirect()
            int r1 = r1 + r0
            int r1 = r1 * 37
            int r1 = r1 + 3
            int r1 = r1 * 53
            int r0 = r3.responseCode_
            int r1 = r1 + r0
            int r1 = r1 * 37
            int r1 = r1 + 6
            int r1 = r1 * 53
            boolean r0 = r3.getStripQuery()
            int r0 = com.google.protobuf.Internal.hashBoolean(r0)
            int r1 = r1 + r0
            int r0 = r3.schemeRewriteSpecifierCase_
            r2 = 4
            if (r0 == r2) goto L5b
            r2 = 7
            if (r0 == r2) goto L4d
            goto L69
        L4d:
            int r1 = r1 * 37
            int r1 = r1 + r2
            int r1 = r1 * 53
            java.lang.String r0 = r3.getSchemeRedirect()
            int r0 = r0.hashCode()
            goto L68
        L5b:
            int r1 = r1 * 37
            int r1 = r1 + r2
            int r1 = r1 * 53
            boolean r0 = r3.getHttpsRedirect()
            int r0 = com.google.protobuf.Internal.hashBoolean(r0)
        L68:
            int r1 = r1 + r0
        L69:
            int r0 = r3.pathRewriteSpecifierCase_
            r2 = 2
            if (r0 == r2) goto L80
            r2 = 5
            if (r0 == r2) goto L72
            goto L8e
        L72:
            int r1 = r1 * 37
            int r1 = r1 + r2
            int r1 = r1 * 53
            java.lang.String r0 = r3.getPrefixRewrite()
            int r0 = r0.hashCode()
            goto L8d
        L80:
            int r1 = r1 * 37
            int r1 = r1 + r2
            int r1 = r1 * 53
            java.lang.String r0 = r3.getPathRedirect()
            int r0 = r0.hashCode()
        L8d:
            int r1 = r1 + r0
        L8e:
            int r1 = r1 * 29
            com.google.protobuf.UnknownFieldSet r0 = r3.unknownFields
            int r0 = r0.hashCode()
            int r1 = r1 + r0
            r3.memoizedHashCode = r1
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RedirectAction.hashCode():int");
    }

    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m18550newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m18553toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public enum RedirectResponseCode implements ProtocolMessageEnum {
        MOVED_PERMANENTLY(0),
        FOUND(1),
        SEE_OTHER(2),
        TEMPORARY_REDIRECT(3),
        PERMANENT_REDIRECT(4),
        UNRECOGNIZED(-1);

        public static final int FOUND_VALUE = 1;
        public static final int MOVED_PERMANENTLY_VALUE = 0;
        public static final int PERMANENT_REDIRECT_VALUE = 4;
        public static final int SEE_OTHER_VALUE = 2;
        public static final int TEMPORARY_REDIRECT_VALUE = 3;
        private static final Internal.EnumLiteMap<RedirectResponseCode> internalValueMap = new Internal.EnumLiteMap<RedirectResponseCode>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RedirectAction.RedirectResponseCode.1
            public RedirectResponseCode findValueByNumber(int i) {
                return RedirectResponseCode.forNumber(i);
            }
        };
        private static final RedirectResponseCode[] VALUES = values();
        private final int value;

        RedirectResponseCode(int i) {
            this.value = i;
        }

        public static RedirectResponseCode forNumber(int i) {
            if (i == 0) {
                return MOVED_PERMANENTLY;
            }
            if (i == 1) {
                return FOUND;
            }
            if (i == 2) {
                return SEE_OTHER;
            }
            if (i == 3) {
                return TEMPORARY_REDIRECT;
            }
            if (i != 4) {
                return null;
            }
            return PERMANENT_REDIRECT;
        }

        public static Internal.EnumLiteMap<RedirectResponseCode> internalGetValueMap() {
            return internalValueMap;
        }

        @Deprecated
        public static RedirectResponseCode valueOf(int i) {
            return forNumber(i);
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return (Descriptors.EnumDescriptor) RedirectAction.getDescriptor().getEnumTypes().get(0);
        }

        public static RedirectResponseCode valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
            if (enumValueDescriptor.getType() == getDescriptor()) {
                return enumValueDescriptor.getIndex() == -1 ? UNRECOGNIZED : VALUES[enumValueDescriptor.getIndex()];
            }
            throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
        }

        public final int getNumber() {
            if (this != UNRECOGNIZED) {
                return this.value;
            }
            throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
        }

        public final Descriptors.EnumValueDescriptor getValueDescriptor() {
            if (this == UNRECOGNIZED) {
                throw new IllegalStateException("Can't get the descriptor of an unrecognized enum value.");
            }
            return (Descriptors.EnumValueDescriptor) getDescriptor().getValues().get(ordinal());
        }

        public final Descriptors.EnumDescriptor getDescriptorForType() {
            return getDescriptor();
        }
    }

    public enum SchemeRewriteSpecifierCase implements Internal.EnumLite, AbstractMessageLite.InternalOneOfEnum {
        HTTPS_REDIRECT(4),
        SCHEME_REDIRECT(7),
        SCHEMEREWRITESPECIFIER_NOT_SET(0);

        private final int value;

        SchemeRewriteSpecifierCase(int i) {
            this.value = i;
        }

        public static SchemeRewriteSpecifierCase forNumber(int i) {
            if (i == 0) {
                return SCHEMEREWRITESPECIFIER_NOT_SET;
            }
            if (i == 4) {
                return HTTPS_REDIRECT;
            }
            if (i != 7) {
                return null;
            }
            return SCHEME_REDIRECT;
        }

        @Deprecated
        public static SchemeRewriteSpecifierCase valueOf(int i) {
            return forNumber(i);
        }

        public int getNumber() {
            return this.value;
        }
    }

    public enum PathRewriteSpecifierCase implements Internal.EnumLite, AbstractMessageLite.InternalOneOfEnum {
        PATH_REDIRECT(2),
        PREFIX_REWRITE(5),
        PATHREWRITESPECIFIER_NOT_SET(0);

        private final int value;

        PathRewriteSpecifierCase(int i) {
            this.value = i;
        }

        public static PathRewriteSpecifierCase forNumber(int i) {
            if (i == 0) {
                return PATHREWRITESPECIFIER_NOT_SET;
            }
            if (i == 2) {
                return PATH_REDIRECT;
            }
            if (i != 5) {
                return null;
            }
            return PREFIX_REWRITE;
        }

        @Deprecated
        public static PathRewriteSpecifierCase valueOf(int i) {
            return forNumber(i);
        }

        public int getNumber() {
            return this.value;
        }
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements RedirectActionOrBuilder {
        private Object hostRedirect_;
        private int pathRewriteSpecifierCase_;
        private Object pathRewriteSpecifier_;
        private int portRedirect_;
        private int responseCode_;
        private int schemeRewriteSpecifierCase_;
        private Object schemeRewriteSpecifier_;
        private boolean stripQuery_;

        private Builder() {
            this.schemeRewriteSpecifierCase_ = 0;
            this.pathRewriteSpecifierCase_ = 0;
            this.hostRedirect_ = "";
            this.responseCode_ = 0;
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.schemeRewriteSpecifierCase_ = 0;
            this.pathRewriteSpecifierCase_ = 0;
            this.hostRedirect_ = "";
            this.responseCode_ = 0;
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return RouteComponentsProto.internal_static_envoy_api_v2_route_RedirectAction_descriptor;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RedirectActionOrBuilder
        public int getPortRedirect() {
            return this.portRedirect_;
        }

        public Builder setPortRedirect(int i) {
            this.portRedirect_ = i;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RedirectActionOrBuilder
        public int getResponseCodeValue() {
            return this.responseCode_;
        }

        public Builder setResponseCodeValue(int i) {
            this.responseCode_ = i;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RedirectActionOrBuilder
        public boolean getStripQuery() {
            return this.stripQuery_;
        }

        public Builder setStripQuery(boolean z) {
            this.stripQuery_ = z;
            onChanged();
            return this;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return RouteComponentsProto.internal_static_envoy_api_v2_route_RedirectAction_fieldAccessorTable.ensureFieldAccessorsInitialized(RedirectAction.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = RedirectAction.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m18564clear() {
            super.clear();
            this.hostRedirect_ = "";
            this.portRedirect_ = 0;
            this.responseCode_ = 0;
            this.stripQuery_ = false;
            this.schemeRewriteSpecifierCase_ = 0;
            this.schemeRewriteSpecifier_ = null;
            this.pathRewriteSpecifierCase_ = 0;
            this.pathRewriteSpecifier_ = null;
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return RouteComponentsProto.internal_static_envoy_api_v2_route_RedirectAction_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public RedirectAction m18577getDefaultInstanceForType() {
            return RedirectAction.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public RedirectAction m18558build() throws UninitializedMessageException {
            RedirectAction redirectActionM18560buildPartial = m18560buildPartial();
            if (redirectActionM18560buildPartial.isInitialized()) {
                return redirectActionM18560buildPartial;
            }
            throw newUninitializedMessageException(redirectActionM18560buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public RedirectAction m18560buildPartial() {
            RedirectAction redirectAction = new RedirectAction(this);
            if (this.schemeRewriteSpecifierCase_ == 4) {
                redirectAction.schemeRewriteSpecifier_ = this.schemeRewriteSpecifier_;
            }
            if (this.schemeRewriteSpecifierCase_ == 7) {
                redirectAction.schemeRewriteSpecifier_ = this.schemeRewriteSpecifier_;
            }
            redirectAction.hostRedirect_ = this.hostRedirect_;
            redirectAction.portRedirect_ = this.portRedirect_;
            if (this.pathRewriteSpecifierCase_ == 2) {
                redirectAction.pathRewriteSpecifier_ = this.pathRewriteSpecifier_;
            }
            if (this.pathRewriteSpecifierCase_ == 5) {
                redirectAction.pathRewriteSpecifier_ = this.pathRewriteSpecifier_;
            }
            redirectAction.responseCode_ = this.responseCode_;
            redirectAction.stripQuery_ = this.stripQuery_;
            redirectAction.schemeRewriteSpecifierCase_ = this.schemeRewriteSpecifierCase_;
            redirectAction.pathRewriteSpecifierCase_ = this.pathRewriteSpecifierCase_;
            onBuilt();
            return redirectAction;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m18576clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m18588setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m18566clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m18569clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m18590setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m18556addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m18581mergeFrom(Message message) {
            if (message instanceof RedirectAction) {
                return mergeFrom((RedirectAction) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(RedirectAction redirectAction) {
            if (redirectAction == RedirectAction.getDefaultInstance()) {
                return this;
            }
            if (!redirectAction.getHostRedirect().isEmpty()) {
                this.hostRedirect_ = redirectAction.hostRedirect_;
                onChanged();
            }
            if (redirectAction.getPortRedirect() != 0) {
                setPortRedirect(redirectAction.getPortRedirect());
            }
            if (redirectAction.responseCode_ != 0) {
                setResponseCodeValue(redirectAction.getResponseCodeValue());
            }
            if (redirectAction.getStripQuery()) {
                setStripQuery(redirectAction.getStripQuery());
            }
            int i = AnonymousClass2.$SwitchMap$io$envoyproxy$envoy$api$v2$route$RedirectAction$SchemeRewriteSpecifierCase[redirectAction.getSchemeRewriteSpecifierCase().ordinal()];
            if (i == 1) {
                setHttpsRedirect(redirectAction.getHttpsRedirect());
            } else if (i == 2) {
                this.schemeRewriteSpecifierCase_ = 7;
                this.schemeRewriteSpecifier_ = redirectAction.schemeRewriteSpecifier_;
                onChanged();
            }
            int i2 = AnonymousClass2.$SwitchMap$io$envoyproxy$envoy$api$v2$route$RedirectAction$PathRewriteSpecifierCase[redirectAction.getPathRewriteSpecifierCase().ordinal()];
            if (i2 == 1) {
                this.pathRewriteSpecifierCase_ = 2;
                this.pathRewriteSpecifier_ = redirectAction.pathRewriteSpecifier_;
                onChanged();
            } else if (i2 == 2) {
                this.pathRewriteSpecifierCase_ = 5;
                this.pathRewriteSpecifier_ = redirectAction.pathRewriteSpecifier_;
                onChanged();
            }
            m18586mergeUnknownFields(redirectAction.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RedirectAction.Builder m18582mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RedirectAction.access$1300()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RedirectAction r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RedirectAction) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RedirectAction r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RedirectAction) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RedirectAction.Builder.m18582mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RedirectAction$Builder");
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RedirectActionOrBuilder
        public SchemeRewriteSpecifierCase getSchemeRewriteSpecifierCase() {
            return SchemeRewriteSpecifierCase.forNumber(this.schemeRewriteSpecifierCase_);
        }

        public Builder clearSchemeRewriteSpecifier() {
            this.schemeRewriteSpecifierCase_ = 0;
            this.schemeRewriteSpecifier_ = null;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RedirectActionOrBuilder
        public PathRewriteSpecifierCase getPathRewriteSpecifierCase() {
            return PathRewriteSpecifierCase.forNumber(this.pathRewriteSpecifierCase_);
        }

        public Builder clearPathRewriteSpecifier() {
            this.pathRewriteSpecifierCase_ = 0;
            this.pathRewriteSpecifier_ = null;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RedirectActionOrBuilder
        public boolean getHttpsRedirect() {
            if (this.schemeRewriteSpecifierCase_ == 4) {
                return ((Boolean) this.schemeRewriteSpecifier_).booleanValue();
            }
            return false;
        }

        public Builder setHttpsRedirect(boolean z) {
            this.schemeRewriteSpecifierCase_ = 4;
            this.schemeRewriteSpecifier_ = Boolean.valueOf(z);
            onChanged();
            return this;
        }

        public Builder clearHttpsRedirect() {
            if (this.schemeRewriteSpecifierCase_ == 4) {
                this.schemeRewriteSpecifierCase_ = 0;
                this.schemeRewriteSpecifier_ = null;
                onChanged();
            }
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RedirectActionOrBuilder
        public String getSchemeRedirect() {
            String str = this.schemeRewriteSpecifierCase_ == 7 ? this.schemeRewriteSpecifier_ : "";
            if (!(str instanceof String)) {
                String stringUtf8 = ((ByteString) str).toStringUtf8();
                if (this.schemeRewriteSpecifierCase_ == 7) {
                    this.schemeRewriteSpecifier_ = stringUtf8;
                }
                return stringUtf8;
            }
            return (String) str;
        }

        public Builder setSchemeRedirect(String str) {
            str.getClass();
            this.schemeRewriteSpecifierCase_ = 7;
            this.schemeRewriteSpecifier_ = str;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RedirectActionOrBuilder
        public ByteString getSchemeRedirectBytes() {
            String str = this.schemeRewriteSpecifierCase_ == 7 ? this.schemeRewriteSpecifier_ : "";
            if (str instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) str);
                if (this.schemeRewriteSpecifierCase_ == 7) {
                    this.schemeRewriteSpecifier_ = byteStringCopyFromUtf8;
                }
                return byteStringCopyFromUtf8;
            }
            return (ByteString) str;
        }

        public Builder setSchemeRedirectBytes(ByteString byteString) {
            byteString.getClass();
            RedirectAction.checkByteStringIsUtf8(byteString);
            this.schemeRewriteSpecifierCase_ = 7;
            this.schemeRewriteSpecifier_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearSchemeRedirect() {
            if (this.schemeRewriteSpecifierCase_ == 7) {
                this.schemeRewriteSpecifierCase_ = 0;
                this.schemeRewriteSpecifier_ = null;
                onChanged();
            }
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RedirectActionOrBuilder
        public String getHostRedirect() {
            Object obj = this.hostRedirect_;
            if (!(obj instanceof String)) {
                String stringUtf8 = ((ByteString) obj).toStringUtf8();
                this.hostRedirect_ = stringUtf8;
                return stringUtf8;
            }
            return (String) obj;
        }

        public Builder setHostRedirect(String str) {
            str.getClass();
            this.hostRedirect_ = str;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RedirectActionOrBuilder
        public ByteString getHostRedirectBytes() {
            Object obj = this.hostRedirect_;
            if (obj instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) obj);
                this.hostRedirect_ = byteStringCopyFromUtf8;
                return byteStringCopyFromUtf8;
            }
            return (ByteString) obj;
        }

        public Builder setHostRedirectBytes(ByteString byteString) {
            byteString.getClass();
            RedirectAction.checkByteStringIsUtf8(byteString);
            this.hostRedirect_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearHostRedirect() {
            this.hostRedirect_ = RedirectAction.getDefaultInstance().getHostRedirect();
            onChanged();
            return this;
        }

        public Builder clearPortRedirect() {
            this.portRedirect_ = 0;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RedirectActionOrBuilder
        public String getPathRedirect() {
            String str = this.pathRewriteSpecifierCase_ == 2 ? this.pathRewriteSpecifier_ : "";
            if (!(str instanceof String)) {
                String stringUtf8 = ((ByteString) str).toStringUtf8();
                if (this.pathRewriteSpecifierCase_ == 2) {
                    this.pathRewriteSpecifier_ = stringUtf8;
                }
                return stringUtf8;
            }
            return (String) str;
        }

        public Builder setPathRedirect(String str) {
            str.getClass();
            this.pathRewriteSpecifierCase_ = 2;
            this.pathRewriteSpecifier_ = str;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RedirectActionOrBuilder
        public ByteString getPathRedirectBytes() {
            String str = this.pathRewriteSpecifierCase_ == 2 ? this.pathRewriteSpecifier_ : "";
            if (str instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) str);
                if (this.pathRewriteSpecifierCase_ == 2) {
                    this.pathRewriteSpecifier_ = byteStringCopyFromUtf8;
                }
                return byteStringCopyFromUtf8;
            }
            return (ByteString) str;
        }

        public Builder setPathRedirectBytes(ByteString byteString) {
            byteString.getClass();
            RedirectAction.checkByteStringIsUtf8(byteString);
            this.pathRewriteSpecifierCase_ = 2;
            this.pathRewriteSpecifier_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearPathRedirect() {
            if (this.pathRewriteSpecifierCase_ == 2) {
                this.pathRewriteSpecifierCase_ = 0;
                this.pathRewriteSpecifier_ = null;
                onChanged();
            }
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RedirectActionOrBuilder
        public String getPrefixRewrite() {
            String str = this.pathRewriteSpecifierCase_ == 5 ? this.pathRewriteSpecifier_ : "";
            if (!(str instanceof String)) {
                String stringUtf8 = ((ByteString) str).toStringUtf8();
                if (this.pathRewriteSpecifierCase_ == 5) {
                    this.pathRewriteSpecifier_ = stringUtf8;
                }
                return stringUtf8;
            }
            return (String) str;
        }

        public Builder setPrefixRewrite(String str) {
            str.getClass();
            this.pathRewriteSpecifierCase_ = 5;
            this.pathRewriteSpecifier_ = str;
            onChanged();
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RedirectActionOrBuilder
        public ByteString getPrefixRewriteBytes() {
            String str = this.pathRewriteSpecifierCase_ == 5 ? this.pathRewriteSpecifier_ : "";
            if (str instanceof String) {
                ByteString byteStringCopyFromUtf8 = ByteString.copyFromUtf8((String) str);
                if (this.pathRewriteSpecifierCase_ == 5) {
                    this.pathRewriteSpecifier_ = byteStringCopyFromUtf8;
                }
                return byteStringCopyFromUtf8;
            }
            return (ByteString) str;
        }

        public Builder setPrefixRewriteBytes(ByteString byteString) {
            byteString.getClass();
            RedirectAction.checkByteStringIsUtf8(byteString);
            this.pathRewriteSpecifierCase_ = 5;
            this.pathRewriteSpecifier_ = byteString;
            onChanged();
            return this;
        }

        public Builder clearPrefixRewrite() {
            if (this.pathRewriteSpecifierCase_ == 5) {
                this.pathRewriteSpecifierCase_ = 0;
                this.pathRewriteSpecifier_ = null;
                onChanged();
            }
            return this;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RedirectActionOrBuilder
        public RedirectResponseCode getResponseCode() {
            RedirectResponseCode redirectResponseCodeValueOf = RedirectResponseCode.valueOf(this.responseCode_);
            return redirectResponseCodeValueOf == null ? RedirectResponseCode.UNRECOGNIZED : redirectResponseCodeValueOf;
        }

        public Builder setResponseCode(RedirectResponseCode redirectResponseCode) {
            redirectResponseCode.getClass();
            this.responseCode_ = redirectResponseCode.getNumber();
            onChanged();
            return this;
        }

        public Builder clearResponseCode() {
            this.responseCode_ = 0;
            onChanged();
            return this;
        }

        public Builder clearStripQuery() {
            this.stripQuery_ = false;
            onChanged();
            return this;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m18592setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m18586mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }

    /* renamed from: io.grpc.xds.shaded.io.envoyproxy.envoy.api.v2.route.RedirectAction$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$io$envoyproxy$envoy$api$v2$route$RedirectAction$PathRewriteSpecifierCase;
        static final /* synthetic */ int[] $SwitchMap$io$envoyproxy$envoy$api$v2$route$RedirectAction$SchemeRewriteSpecifierCase;

        static {
            int[] iArr = new int[PathRewriteSpecifierCase.values().length];
            $SwitchMap$io$envoyproxy$envoy$api$v2$route$RedirectAction$PathRewriteSpecifierCase = iArr;
            try {
                iArr[PathRewriteSpecifierCase.PATH_REDIRECT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$api$v2$route$RedirectAction$PathRewriteSpecifierCase[PathRewriteSpecifierCase.PREFIX_REWRITE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$api$v2$route$RedirectAction$PathRewriteSpecifierCase[PathRewriteSpecifierCase.PATHREWRITESPECIFIER_NOT_SET.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            int[] iArr2 = new int[SchemeRewriteSpecifierCase.values().length];
            $SwitchMap$io$envoyproxy$envoy$api$v2$route$RedirectAction$SchemeRewriteSpecifierCase = iArr2;
            try {
                iArr2[SchemeRewriteSpecifierCase.HTTPS_REDIRECT.ordinal()] = 1;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$api$v2$route$RedirectAction$SchemeRewriteSpecifierCase[SchemeRewriteSpecifierCase.SCHEME_REDIRECT.ordinal()] = 2;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$io$envoyproxy$envoy$api$v2$route$RedirectAction$SchemeRewriteSpecifierCase[SchemeRewriteSpecifierCase.SCHEMEREWRITESPECIFIER_NOT_SET.ordinal()] = 3;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }
}
