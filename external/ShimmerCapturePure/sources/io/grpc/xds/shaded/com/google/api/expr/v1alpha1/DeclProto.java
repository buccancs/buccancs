package io.grpc.xds.shaded.com.google.api.expr.v1alpha1;

import com.google.protobuf.Descriptors;
import com.google.protobuf.EmptyProto;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.StructProto;

/* loaded from: classes3.dex */
public final class DeclProto {
    static final Descriptors.Descriptor internal_static_google_api_expr_v1alpha1_CheckedExpr_ReferenceMapEntry_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_api_expr_v1alpha1_CheckedExpr_ReferenceMapEntry_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_google_api_expr_v1alpha1_CheckedExpr_TypeMapEntry_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_api_expr_v1alpha1_CheckedExpr_TypeMapEntry_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_google_api_expr_v1alpha1_CheckedExpr_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_api_expr_v1alpha1_CheckedExpr_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_google_api_expr_v1alpha1_Decl_FunctionDecl_Overload_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_api_expr_v1alpha1_Decl_FunctionDecl_Overload_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_google_api_expr_v1alpha1_Decl_FunctionDecl_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_api_expr_v1alpha1_Decl_FunctionDecl_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_google_api_expr_v1alpha1_Decl_IdentDecl_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_api_expr_v1alpha1_Decl_IdentDecl_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_google_api_expr_v1alpha1_Decl_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_api_expr_v1alpha1_Decl_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_google_api_expr_v1alpha1_Reference_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_api_expr_v1alpha1_Reference_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_google_api_expr_v1alpha1_Type_AbstractType_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_api_expr_v1alpha1_Type_AbstractType_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_google_api_expr_v1alpha1_Type_FunctionType_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_api_expr_v1alpha1_Type_FunctionType_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_google_api_expr_v1alpha1_Type_ListType_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_api_expr_v1alpha1_Type_ListType_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_google_api_expr_v1alpha1_Type_MapType_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_api_expr_v1alpha1_Type_MapType_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_google_api_expr_v1alpha1_Type_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_google_api_expr_v1alpha1_Type_fieldAccessorTable;
    private static Descriptors.FileDescriptor descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n&google/api/expr/v1alpha1/checked.proto\u0012\u0018google.api.expr.v1alpha1\u001a%google/api/expr/v1alpha1/syntax.proto\u001a\u001bgoogle/protobuf/empty.proto\u001a\u001cgoogle/protobuf/struct.proto\"¶\u0003\n\u000bCheckedExpr\u0012N\n\rreference_map\u0018\u0002 \u0003(\u000b27.google.api.expr.v1alpha1.CheckedExpr.ReferenceMapEntry\u0012D\n\btype_map\u0018\u0003 \u0003(\u000b22.google.api.expr.v1alpha1.CheckedExpr.TypeMapEntry\u00129\n\u000bsource_info\u0018\u0005 \u0001(\u000b2$.google.api.expr.v1alpha1.SourceInfo\u0012,\n\u0004expr\u0018\u0004 \u0001(\u000b2\u001e.google.api.expr.v1alpha1.Expr\u001aX\n\u0011ReferenceMapEntry\u0012\u000b\n\u0003key\u0018\u0001 \u0001(\u0003\u00122\n\u0005value\u0018\u0002 \u0001(\u000b2#.google.api.expr.v1alpha1.Reference:\u00028\u0001\u001aN\n\fTypeMapEntry\u0012\u000b\n\u0003key\u0018\u0001 \u0001(\u0003\u0012-\n\u0005value\u0018\u0002 \u0001(\u000b2\u001e.google.api.expr.v1alpha1.Type:\u00028\u0001\"\u0082\n\n\u0004Type\u0012%\n\u0003dyn\u0018\u0001 \u0001(\u000b2\u0016.google.protobuf.EmptyH\u0000\u0012*\n\u0004null\u0018\u0002 \u0001(\u000e2\u001a.google.protobuf.NullValueH\u0000\u0012A\n\tprimitive\u0018\u0003 \u0001(\u000e2,.google.api.expr.v1alpha1.Type.PrimitiveTypeH\u0000\u0012?\n\u0007wrapper\u0018\u0004 \u0001(\u000e2,.google.api.expr.v1alpha1.Type.PrimitiveTypeH\u0000\u0012B\n\nwell_known\u0018\u0005 \u0001(\u000e2,.google.api.expr.v1alpha1.Type.WellKnownTypeH\u0000\u0012<\n\tlist_type\u0018\u0006 \u0001(\u000b2'.google.api.expr.v1alpha1.Type.ListTypeH\u0000\u0012:\n\bmap_type\u0018\u0007 \u0001(\u000b2&.google.api.expr.v1alpha1.Type.MapTypeH\u0000\u0012?\n\bfunction\u0018\b \u0001(\u000b2+.google.api.expr.v1alpha1.Type.FunctionTypeH\u0000\u0012\u0016\n\fmessage_type\u0018\t \u0001(\tH\u0000\u0012\u0014\n\ntype_param\u0018\n \u0001(\tH\u0000\u0012.\n\u0004type\u0018\u000b \u0001(\u000b2\u001e.google.api.expr.v1alpha1.TypeH\u0000\u0012'\n\u0005error\u0018\f \u0001(\u000b2\u0016.google.protobuf.EmptyH\u0000\u0012D\n\rabstract_type\u0018\u000e \u0001(\u000b2+.google.api.expr.v1alpha1.Type.AbstractTypeH\u0000\u001a=\n\bListType\u00121\n\telem_type\u0018\u0001 \u0001(\u000b2\u001e.google.api.expr.v1alpha1.Type\u001ao\n\u0007MapType\u00120\n\bkey_type\u0018\u0001 \u0001(\u000b2\u001e.google.api.expr.v1alpha1.Type\u00122\n\nvalue_type\u0018\u0002 \u0001(\u000b2\u001e.google.api.expr.v1alpha1.Type\u001av\n\fFunctionType\u00123\n\u000bresult_type\u0018\u0001 \u0001(\u000b2\u001e.google.api.expr.v1alpha1.Type\u00121\n\targ_types\u0018\u0002 \u0003(\u000b2\u001e.google.api.expr.v1alpha1.Type\u001aU\n\fAbstractType\u0012\f\n\u0004name\u0018\u0001 \u0001(\t\u00127\n\u000fparameter_types\u0018\u0002 \u0003(\u000b2\u001e.google.api.expr.v1alpha1.Type\"s\n\rPrimitiveType\u0012\u001e\n\u001aPRIMITIVE_TYPE_UNSPECIFIED\u0010\u0000\u0012\b\n\u0004BOOL\u0010\u0001\u0012\t\n\u0005INT64\u0010\u0002\u0012\n\n\u0006UINT64\u0010\u0003\u0012\n\n\u0006DOUBLE\u0010\u0004\u0012\n\n\u0006STRING\u0010\u0005\u0012\t\n\u0005BYTES\u0010\u0006\"V\n\rWellKnownType\u0012\u001f\n\u001bWELL_KNOWN_TYPE_UNSPECIFIED\u0010\u0000\u0012\u0007\n\u0003ANY\u0010\u0001\u0012\r\n\tTIMESTAMP\u0010\u0002\u0012\f\n\bDURATION\u0010\u0003B\u000b\n\ttype_kind\"¹\u0004\n\u0004Decl\u0012\f\n\u0004name\u0018\u0001 \u0001(\t\u00129\n\u0005ident\u0018\u0002 \u0001(\u000b2(.google.api.expr.v1alpha1.Decl.IdentDeclH\u0000\u0012?\n\bfunction\u0018\u0003 \u0001(\u000b2+.google.api.expr.v1alpha1.Decl.FunctionDeclH\u0000\u001ay\n\tIdentDecl\u0012,\n\u0004type\u0018\u0001 \u0001(\u000b2\u001e.google.api.expr.v1alpha1.Type\u00121\n\u0005value\u0018\u0002 \u0001(\u000b2\".google.api.expr.v1alpha1.Constant\u0012\u000b\n\u0003doc\u0018\u0003 \u0001(\t\u001a\u009e\u0002\n\fFunctionDecl\u0012G\n\toverloads\u0018\u0001 \u0003(\u000b24.google.api.expr.v1alpha1.Decl.FunctionDecl.Overload\u001aÄ\u0001\n\bOverload\u0012\u0013\n\u000boverload_id\u0018\u0001 \u0001(\t\u0012.\n\u0006params\u0018\u0002 \u0003(\u000b2\u001e.google.api.expr.v1alpha1.Type\u0012\u0013\n\u000btype_params\u0018\u0003 \u0003(\t\u00123\n\u000bresult_type\u0018\u0004 \u0001(\u000b2\u001e.google.api.expr.v1alpha1.Type\u0012\u001c\n\u0014is_instance_function\u0018\u0005 \u0001(\b\u0012\u000b\n\u0003doc\u0018\u0006 \u0001(\tB\u000b\n\tdecl_kind\"a\n\tReference\u0012\f\n\u0004name\u0018\u0001 \u0001(\t\u0012\u0013\n\u000boverload_id\u0018\u0003 \u0003(\t\u00121\n\u0005value\u0018\u0004 \u0001(\u000b2\".google.api.expr.v1alpha1.ConstantBl\n\u001ccom.google.api.expr.v1alpha1B\tDeclProtoP\u0001Z<google.golang.org/genproto/googleapis/api/expr/v1alpha1;exprø\u0001\u0001b\u0006proto3"}, new Descriptors.FileDescriptor[]{SyntaxProto.getDescriptor(), EmptyProto.getDescriptor(), StructProto.getDescriptor()});

    static {
        Descriptors.Descriptor descriptor2 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(0);
        internal_static_google_api_expr_v1alpha1_CheckedExpr_descriptor = descriptor2;
        internal_static_google_api_expr_v1alpha1_CheckedExpr_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor2, new String[]{"ReferenceMap", "TypeMap", "SourceInfo", "Expr"});
        Descriptors.Descriptor descriptor3 = (Descriptors.Descriptor) descriptor2.getNestedTypes().get(0);
        internal_static_google_api_expr_v1alpha1_CheckedExpr_ReferenceMapEntry_descriptor = descriptor3;
        internal_static_google_api_expr_v1alpha1_CheckedExpr_ReferenceMapEntry_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor3, new String[]{"Key", "Value"});
        Descriptors.Descriptor descriptor4 = (Descriptors.Descriptor) descriptor2.getNestedTypes().get(1);
        internal_static_google_api_expr_v1alpha1_CheckedExpr_TypeMapEntry_descriptor = descriptor4;
        internal_static_google_api_expr_v1alpha1_CheckedExpr_TypeMapEntry_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor4, new String[]{"Key", "Value"});
        Descriptors.Descriptor descriptor5 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(1);
        internal_static_google_api_expr_v1alpha1_Type_descriptor = descriptor5;
        internal_static_google_api_expr_v1alpha1_Type_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor5, new String[]{"Dyn", "Null", "Primitive", "Wrapper", "WellKnown", "ListType", "MapType", "Function", "MessageType", "TypeParam", "Type", "Error", "AbstractType", "TypeKind"});
        Descriptors.Descriptor descriptor6 = (Descriptors.Descriptor) descriptor5.getNestedTypes().get(0);
        internal_static_google_api_expr_v1alpha1_Type_ListType_descriptor = descriptor6;
        internal_static_google_api_expr_v1alpha1_Type_ListType_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor6, new String[]{"ElemType"});
        Descriptors.Descriptor descriptor7 = (Descriptors.Descriptor) descriptor5.getNestedTypes().get(1);
        internal_static_google_api_expr_v1alpha1_Type_MapType_descriptor = descriptor7;
        internal_static_google_api_expr_v1alpha1_Type_MapType_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor7, new String[]{"KeyType", "ValueType"});
        Descriptors.Descriptor descriptor8 = (Descriptors.Descriptor) descriptor5.getNestedTypes().get(2);
        internal_static_google_api_expr_v1alpha1_Type_FunctionType_descriptor = descriptor8;
        internal_static_google_api_expr_v1alpha1_Type_FunctionType_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor8, new String[]{"ResultType", "ArgTypes"});
        Descriptors.Descriptor descriptor9 = (Descriptors.Descriptor) descriptor5.getNestedTypes().get(3);
        internal_static_google_api_expr_v1alpha1_Type_AbstractType_descriptor = descriptor9;
        internal_static_google_api_expr_v1alpha1_Type_AbstractType_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor9, new String[]{"Name", "ParameterTypes"});
        Descriptors.Descriptor descriptor10 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(2);
        internal_static_google_api_expr_v1alpha1_Decl_descriptor = descriptor10;
        internal_static_google_api_expr_v1alpha1_Decl_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor10, new String[]{"Name", "Ident", "Function", "DeclKind"});
        Descriptors.Descriptor descriptor11 = (Descriptors.Descriptor) descriptor10.getNestedTypes().get(0);
        internal_static_google_api_expr_v1alpha1_Decl_IdentDecl_descriptor = descriptor11;
        internal_static_google_api_expr_v1alpha1_Decl_IdentDecl_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor11, new String[]{"Type", "Value", "Doc"});
        Descriptors.Descriptor descriptor12 = (Descriptors.Descriptor) descriptor10.getNestedTypes().get(1);
        internal_static_google_api_expr_v1alpha1_Decl_FunctionDecl_descriptor = descriptor12;
        internal_static_google_api_expr_v1alpha1_Decl_FunctionDecl_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor12, new String[]{"Overloads"});
        Descriptors.Descriptor descriptor13 = (Descriptors.Descriptor) descriptor12.getNestedTypes().get(0);
        internal_static_google_api_expr_v1alpha1_Decl_FunctionDecl_Overload_descriptor = descriptor13;
        internal_static_google_api_expr_v1alpha1_Decl_FunctionDecl_Overload_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor13, new String[]{"OverloadId", "Params", "TypeParams", "ResultType", "IsInstanceFunction", "Doc"});
        Descriptors.Descriptor descriptor14 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(3);
        internal_static_google_api_expr_v1alpha1_Reference_descriptor = descriptor14;
        internal_static_google_api_expr_v1alpha1_Reference_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor14, new String[]{"Name", "OverloadId", "Value"});
        SyntaxProto.getDescriptor();
        EmptyProto.getDescriptor();
        StructProto.getDescriptor();
    }

    private DeclProto() {
    }

    public static Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    public static void registerAllExtensions(ExtensionRegistry extensionRegistry) {
        registerAllExtensions((ExtensionRegistryLite) extensionRegistry);
    }
}
