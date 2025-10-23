package udpa.annotations;

import com.google.protobuf.DescriptorProtos;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessage;
import com.google.protobuf.Message;

/* loaded from: classes2.dex */
public final class Sensitive {
    public static final int SENSITIVE_FIELD_NUMBER = 76569463;
    public static final GeneratedMessage.GeneratedExtension<DescriptorProtos.FieldOptions, Boolean> sensitive;
    private static Descriptors.FileDescriptor descriptor;

    static {
        GeneratedMessage.GeneratedExtension<DescriptorProtos.FieldOptions, Boolean> generatedExtensionNewFileScopedGeneratedExtension = GeneratedMessage.newFileScopedGeneratedExtension(Boolean.class, (Message) null);
        sensitive = generatedExtensionNewFileScopedGeneratedExtension;
        Descriptors.FileDescriptor fileDescriptorInternalBuildGeneratedFileFrom = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n udpa/annotations/sensitive.proto\u0012\u0010udpa.annotations\u001a google/protobuf/descriptor.proto:3\n\tsensitive\u0012\u001d.google.protobuf.FieldOptions\u0018÷¶Á$ \u0001(\bb\u0006proto3"}, new Descriptors.FileDescriptor[]{DescriptorProtos.getDescriptor()});
        descriptor = fileDescriptorInternalBuildGeneratedFileFrom;
        generatedExtensionNewFileScopedGeneratedExtension.internalInit((Descriptors.FieldDescriptor) fileDescriptorInternalBuildGeneratedFileFrom.getExtensions().get(0));
        DescriptorProtos.getDescriptor();
    }

    private Sensitive() {
    }

    public static Descriptors.FileDescriptor getDescriptor() {
        return descriptor;
    }

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
        extensionRegistryLite.add(sensitive);
    }

    public static void registerAllExtensions(ExtensionRegistry extensionRegistry) {
        registerAllExtensions((ExtensionRegistryLite) extensionRegistry);
    }
}
