package io.grpc.xds.shaded.io.envoyproxy.envoy.config.rbac.v2;

import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MapEntry;
import com.google.protobuf.MapField;
import com.google.protobuf.Message;
import com.google.protobuf.Parser;
import com.google.protobuf.ProtocolMessageEnum;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;
import com.google.protobuf.WireFormat;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Map;

/* loaded from: classes4.dex */
public final class RBAC extends GeneratedMessageV3 implements RBACOrBuilder {
    public static final int ACTION_FIELD_NUMBER = 1;
    public static final int POLICIES_FIELD_NUMBER = 2;
    private static final RBAC DEFAULT_INSTANCE = new RBAC();
    private static final Parser<RBAC> PARSER = new AbstractParser<RBAC>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.config.rbac.v2.RBAC.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public RBAC m27761parsePartialFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return new RBAC(codedInputStream, extensionRegistryLite);
        }
    };
    private static final long serialVersionUID = 0;
    private int action_;
    private byte memoizedIsInitialized;
    private MapField<String, Policy> policies_;

    private RBAC(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private RBAC() {
        this.memoizedIsInitialized = (byte) -1;
        this.action_ = 0;
    }

    private RBAC(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
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
                        if (tag == 8) {
                            this.action_ = codedInputStream.readEnum();
                        } else if (tag == 18) {
                            if (!(z2 & true)) {
                                this.policies_ = MapField.newMapField(PoliciesDefaultEntryHolder.defaultEntry);
                                z2 |= true;
                            }
                            MapEntry message = codedInputStream.readMessage(PoliciesDefaultEntryHolder.defaultEntry.getParserForType(), extensionRegistryLite);
                            this.policies_.getMutableMap().put(message.getKey(), message.getValue());
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

    public static RBAC getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<RBAC> parser() {
        return PARSER;
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return RbacProto.internal_static_envoy_config_rbac_v2_RBAC_descriptor;
    }

    public static RBAC parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (RBAC) PARSER.parseFrom(byteBuffer);
    }

    public static RBAC parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (RBAC) PARSER.parseFrom(byteBuffer, extensionRegistryLite);
    }

    public static RBAC parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (RBAC) PARSER.parseFrom(byteString);
    }

    public static RBAC parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (RBAC) PARSER.parseFrom(byteString, extensionRegistryLite);
    }

    public static RBAC parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (RBAC) PARSER.parseFrom(bArr);
    }

    public static RBAC parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (RBAC) PARSER.parseFrom(bArr, extensionRegistryLite);
    }

    public static RBAC parseFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream);
    }

    public static RBAC parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static RBAC parseDelimitedFrom(InputStream inputStream) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream);
    }

    public static RBAC parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, inputStream, extensionRegistryLite);
    }

    public static RBAC parseFrom(CodedInputStream codedInputStream) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream);
    }

    public static RBAC parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m27759toBuilder();
    }

    public static Builder newBuilder(RBAC rbac) {
        return DEFAULT_INSTANCE.m27759toBuilder().mergeFrom(rbac);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.rbac.v2.RBACOrBuilder
    public int getActionValue() {
        return this.action_;
    }

    /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public RBAC m27754getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }

    public Parser<RBAC> getParserForType() {
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
        return new RBAC();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    protected MapField internalGetMapField(int i) {
        if (i == 2) {
            return internalGetPolicies();
        }
        throw new RuntimeException("Invalid map field number: " + i);
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return RbacProto.internal_static_envoy_config_rbac_v2_RBAC_fieldAccessorTable.ensureFieldAccessorsInitialized(RBAC.class, Builder.class);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.rbac.v2.RBACOrBuilder
    public Action getAction() {
        Action actionValueOf = Action.valueOf(this.action_);
        return actionValueOf == null ? Action.UNRECOGNIZED : actionValueOf;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public MapField<String, Policy> internalGetPolicies() {
        MapField<String, Policy> mapField = this.policies_;
        return mapField == null ? MapField.emptyMapField(PoliciesDefaultEntryHolder.defaultEntry) : mapField;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.rbac.v2.RBACOrBuilder
    public int getPoliciesCount() {
        return internalGetPolicies().getMap().size();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.rbac.v2.RBACOrBuilder
    public boolean containsPolicies(String str) {
        str.getClass();
        return internalGetPolicies().getMap().containsKey(str);
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.rbac.v2.RBACOrBuilder
    @Deprecated
    public Map<String, Policy> getPolicies() {
        return getPoliciesMap();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.rbac.v2.RBACOrBuilder
    public Map<String, Policy> getPoliciesMap() {
        return internalGetPolicies().getMap();
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.rbac.v2.RBACOrBuilder
    public Policy getPoliciesOrDefault(String str, Policy policy) {
        str.getClass();
        Map map = internalGetPolicies().getMap();
        return map.containsKey(str) ? (Policy) map.get(str) : policy;
    }

    @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.rbac.v2.RBACOrBuilder
    public Policy getPoliciesOrThrow(String str) {
        str.getClass();
        Map map = internalGetPolicies().getMap();
        if (!map.containsKey(str)) {
            throw new IllegalArgumentException();
        }
        return (Policy) map.get(str);
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (this.action_ != Action.ALLOW.getNumber()) {
            codedOutputStream.writeEnum(1, this.action_);
        }
        GeneratedMessageV3.serializeStringMapTo(codedOutputStream, internalGetPolicies(), PoliciesDefaultEntryHolder.defaultEntry, 2);
        this.unknownFields.writeTo(codedOutputStream);
    }

    public int getSerializedSize() {
        int i = this.memoizedSize;
        if (i != -1) {
            return i;
        }
        int iComputeEnumSize = this.action_ != Action.ALLOW.getNumber() ? CodedOutputStream.computeEnumSize(1, this.action_) : 0;
        for (Map.Entry entry : internalGetPolicies().getMap().entrySet()) {
            iComputeEnumSize += CodedOutputStream.computeMessageSize(2, PoliciesDefaultEntryHolder.defaultEntry.newBuilderForType().setKey(entry.getKey()).setValue(entry.getValue()).build());
        }
        int serializedSize = iComputeEnumSize + this.unknownFields.getSerializedSize();
        this.memoizedSize = serializedSize;
        return serializedSize;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof RBAC)) {
            return super.equals(obj);
        }
        RBAC rbac = (RBAC) obj;
        return this.action_ == rbac.action_ && internalGetPolicies().equals(rbac.internalGetPolicies()) && this.unknownFields.equals(rbac.unknownFields);
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int iHashCode = ((((779 + getDescriptor().hashCode()) * 37) + 1) * 53) + this.action_;
        if (!internalGetPolicies().getMap().isEmpty()) {
            iHashCode = (((iHashCode * 37) + 2) * 53) + internalGetPolicies().hashCode();
        }
        int iHashCode2 = (iHashCode * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = iHashCode2;
        return iHashCode2;
    }

    /* renamed from: newBuilderForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m27756newBuilderForType() {
        return newBuilder();
    }

    /* renamed from: toBuilder, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m27759toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Builder newBuilderForType(GeneratedMessageV3.BuilderParent builderParent) {
        return new Builder(builderParent);
    }

    public enum Action implements ProtocolMessageEnum {
        ALLOW(0),
        DENY(1),
        UNRECOGNIZED(-1);

        public static final int ALLOW_VALUE = 0;
        public static final int DENY_VALUE = 1;
        private static final Internal.EnumLiteMap<Action> internalValueMap = new Internal.EnumLiteMap<Action>() { // from class: io.grpc.xds.shaded.io.envoyproxy.envoy.config.rbac.v2.RBAC.Action.1
            public Action findValueByNumber(int i) {
                return Action.forNumber(i);
            }
        };
        private static final Action[] VALUES = values();
        private final int value;

        Action(int i) {
            this.value = i;
        }

        public static Action forNumber(int i) {
            if (i == 0) {
                return ALLOW;
            }
            if (i != 1) {
                return null;
            }
            return DENY;
        }

        public static Internal.EnumLiteMap<Action> internalGetValueMap() {
            return internalValueMap;
        }

        @Deprecated
        public static Action valueOf(int i) {
            return forNumber(i);
        }

        public static final Descriptors.EnumDescriptor getDescriptor() {
            return (Descriptors.EnumDescriptor) RBAC.getDescriptor().getEnumTypes().get(0);
        }

        public static Action valueOf(Descriptors.EnumValueDescriptor enumValueDescriptor) {
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

    private static final class PoliciesDefaultEntryHolder {
        static final MapEntry<String, Policy> defaultEntry = MapEntry.newDefaultInstance(RbacProto.internal_static_envoy_config_rbac_v2_RBAC_PoliciesEntry_descriptor, WireFormat.FieldType.STRING, "", WireFormat.FieldType.MESSAGE, Policy.getDefaultInstance());

        private PoliciesDefaultEntryHolder() {
        }
    }

    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements RBACOrBuilder {
        private int action_;
        private int bitField0_;
        private MapField<String, Policy> policies_;

        private Builder() {
            this.action_ = 0;
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent builderParent) {
            super(builderParent);
            this.action_ = 0;
            maybeForceBuilderInitialization();
        }

        public static final Descriptors.Descriptor getDescriptor() {
            return RbacProto.internal_static_envoy_config_rbac_v2_RBAC_descriptor;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.rbac.v2.RBACOrBuilder
        public int getActionValue() {
            return this.action_;
        }

        public Builder setActionValue(int i) {
            this.action_ = i;
            onChanged();
            return this;
        }

        public final boolean isInitialized() {
            return true;
        }

        protected MapField internalGetMapField(int i) {
            if (i == 2) {
                return internalGetPolicies();
            }
            throw new RuntimeException("Invalid map field number: " + i);
        }

        protected MapField internalGetMutableMapField(int i) {
            if (i == 2) {
                return internalGetMutablePolicies();
            }
            throw new RuntimeException("Invalid map field number: " + i);
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return RbacProto.internal_static_envoy_config_rbac_v2_RBAC_fieldAccessorTable.ensureFieldAccessorsInitialized(RBAC.class, Builder.class);
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = RBAC.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m27771clear() {
            super.clear();
            this.action_ = 0;
            internalGetMutablePolicies().clear();
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return RbacProto.internal_static_envoy_config_rbac_v2_RBAC_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public RBAC m27784getDefaultInstanceForType() {
            return RBAC.getDefaultInstance();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: com.google.protobuf.UninitializedMessageException */
        /* renamed from: build, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public RBAC m27765build() throws UninitializedMessageException {
            RBAC rbacM27767buildPartial = m27767buildPartial();
            if (rbacM27767buildPartial.isInitialized()) {
                return rbacM27767buildPartial;
            }
            throw newUninitializedMessageException(rbacM27767buildPartial);
        }

        /* renamed from: buildPartial, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public RBAC m27767buildPartial() {
            RBAC rbac = new RBAC(this);
            rbac.action_ = this.action_;
            rbac.policies_ = internalGetPolicies();
            rbac.policies_.makeImmutable();
            onBuilt();
            return rbac;
        }

        /* renamed from: clone, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m27783clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m27795setField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.setField(fieldDescriptor, obj);
        }

        /* renamed from: clearField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m27773clearField(Descriptors.FieldDescriptor fieldDescriptor) {
            return (Builder) super.clearField(fieldDescriptor);
        }

        /* renamed from: clearOneof, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m27776clearOneof(Descriptors.OneofDescriptor oneofDescriptor) {
            return (Builder) super.clearOneof(oneofDescriptor);
        }

        /* renamed from: setRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m27797setRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, int i, Object obj) {
            return (Builder) super.setRepeatedField(fieldDescriptor, i, obj);
        }

        /* renamed from: addRepeatedField, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m27763addRepeatedField(Descriptors.FieldDescriptor fieldDescriptor, Object obj) {
            return (Builder) super.addRepeatedField(fieldDescriptor, obj);
        }

        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m27788mergeFrom(Message message) {
            if (message instanceof RBAC) {
                return mergeFrom((RBAC) message);
            }
            super.mergeFrom(message);
            return this;
        }

        public Builder mergeFrom(RBAC rbac) {
            if (rbac == RBAC.getDefaultInstance()) {
                return this;
            }
            if (rbac.action_ != 0) {
                setActionValue(rbac.getActionValue());
            }
            internalGetMutablePolicies().mergeFrom(rbac.internalGetPolicies());
            m27793mergeUnknownFields(rbac.unknownFields);
            onChanged();
            return this;
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0023  */
        /* renamed from: mergeFrom, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public io.grpc.xds.shaded.io.envoyproxy.envoy.config.rbac.v2.RBAC.Builder m27789mergeFrom(com.google.protobuf.CodedInputStream r3, com.google.protobuf.ExtensionRegistryLite r4) throws java.lang.Throwable {
            /*
                r2 = this;
                r0 = 0
                com.google.protobuf.Parser r1 = io.grpc.xds.shaded.io.envoyproxy.envoy.config.rbac.v2.RBAC.access$800()     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                java.lang.Object r3 = r1.parsePartialFrom(r3, r4)     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
                io.grpc.xds.shaded.io.envoyproxy.envoy.config.rbac.v2.RBAC r3 = (io.grpc.xds.shaded.io.envoyproxy.envoy.config.rbac.v2.RBAC) r3     // Catch: java.lang.Throwable -> L11 com.google.protobuf.InvalidProtocolBufferException -> L13
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
                io.grpc.xds.shaded.io.envoyproxy.envoy.config.rbac.v2.RBAC r4 = (io.grpc.xds.shaded.io.envoyproxy.envoy.config.rbac.v2.RBAC) r4     // Catch: java.lang.Throwable -> L11
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
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.xds.shaded.io.envoyproxy.envoy.config.rbac.v2.RBAC.Builder.m27789mergeFrom(com.google.protobuf.CodedInputStream, com.google.protobuf.ExtensionRegistryLite):io.grpc.xds.shaded.io.envoyproxy.envoy.config.rbac.v2.RBAC$Builder");
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.rbac.v2.RBACOrBuilder
        public Action getAction() {
            Action actionValueOf = Action.valueOf(this.action_);
            return actionValueOf == null ? Action.UNRECOGNIZED : actionValueOf;
        }

        public Builder setAction(Action action) {
            action.getClass();
            this.action_ = action.getNumber();
            onChanged();
            return this;
        }

        public Builder clearAction() {
            this.action_ = 0;
            onChanged();
            return this;
        }

        private MapField<String, Policy> internalGetPolicies() {
            MapField<String, Policy> mapField = this.policies_;
            return mapField == null ? MapField.emptyMapField(PoliciesDefaultEntryHolder.defaultEntry) : mapField;
        }

        private MapField<String, Policy> internalGetMutablePolicies() {
            onChanged();
            if (this.policies_ == null) {
                this.policies_ = MapField.newMapField(PoliciesDefaultEntryHolder.defaultEntry);
            }
            if (!this.policies_.isMutable()) {
                this.policies_ = this.policies_.copy();
            }
            return this.policies_;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.rbac.v2.RBACOrBuilder
        public int getPoliciesCount() {
            return internalGetPolicies().getMap().size();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.rbac.v2.RBACOrBuilder
        public boolean containsPolicies(String str) {
            str.getClass();
            return internalGetPolicies().getMap().containsKey(str);
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.rbac.v2.RBACOrBuilder
        @Deprecated
        public Map<String, Policy> getPolicies() {
            return getPoliciesMap();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.rbac.v2.RBACOrBuilder
        public Map<String, Policy> getPoliciesMap() {
            return internalGetPolicies().getMap();
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.rbac.v2.RBACOrBuilder
        public Policy getPoliciesOrDefault(String str, Policy policy) {
            str.getClass();
            Map map = internalGetPolicies().getMap();
            return map.containsKey(str) ? (Policy) map.get(str) : policy;
        }

        @Override // io.grpc.xds.shaded.io.envoyproxy.envoy.config.rbac.v2.RBACOrBuilder
        public Policy getPoliciesOrThrow(String str) {
            str.getClass();
            Map map = internalGetPolicies().getMap();
            if (!map.containsKey(str)) {
                throw new IllegalArgumentException();
            }
            return (Policy) map.get(str);
        }

        public Builder clearPolicies() {
            internalGetMutablePolicies().getMutableMap().clear();
            return this;
        }

        public Builder removePolicies(String str) {
            str.getClass();
            internalGetMutablePolicies().getMutableMap().remove(str);
            return this;
        }

        @Deprecated
        public Map<String, Policy> getMutablePolicies() {
            return internalGetMutablePolicies().getMutableMap();
        }

        public Builder putPolicies(String str, Policy policy) {
            str.getClass();
            policy.getClass();
            internalGetMutablePolicies().getMutableMap().put(str, policy);
            return this;
        }

        public Builder putAllPolicies(Map<String, Policy> map) {
            internalGetMutablePolicies().getMutableMap().putAll(map);
            return this;
        }

        /* renamed from: setUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m27799setUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.setUnknownFields(unknownFieldSet);
        }

        /* renamed from: mergeUnknownFields, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m27793mergeUnknownFields(UnknownFieldSet unknownFieldSet) {
            return (Builder) super.mergeUnknownFields(unknownFieldSet);
        }
    }
}
