package io.opencensus.proto.trace.v1;

import com.google.common.net.HttpHeaders;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.TimestampProto;
import com.google.protobuf.WrappersProto;
import io.opencensus.proto.resource.v1.ResourceProto;

/* loaded from: classes4.dex */
public final class TraceProto {
    static final Descriptors.Descriptor internal_static_opencensus_proto_trace_v1_AttributeValue_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_opencensus_proto_trace_v1_AttributeValue_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_opencensus_proto_trace_v1_Module_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_opencensus_proto_trace_v1_Module_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_opencensus_proto_trace_v1_Span_Attributes_AttributeMapEntry_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_opencensus_proto_trace_v1_Span_Attributes_AttributeMapEntry_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_opencensus_proto_trace_v1_Span_Attributes_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_opencensus_proto_trace_v1_Span_Attributes_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_opencensus_proto_trace_v1_Span_Link_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_opencensus_proto_trace_v1_Span_Link_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_opencensus_proto_trace_v1_Span_Links_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_opencensus_proto_trace_v1_Span_Links_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_opencensus_proto_trace_v1_Span_TimeEvent_Annotation_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_opencensus_proto_trace_v1_Span_TimeEvent_Annotation_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_opencensus_proto_trace_v1_Span_TimeEvent_MessageEvent_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_opencensus_proto_trace_v1_Span_TimeEvent_MessageEvent_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_opencensus_proto_trace_v1_Span_TimeEvent_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_opencensus_proto_trace_v1_Span_TimeEvent_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_opencensus_proto_trace_v1_Span_TimeEvents_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_opencensus_proto_trace_v1_Span_TimeEvents_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_opencensus_proto_trace_v1_Span_Tracestate_Entry_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_opencensus_proto_trace_v1_Span_Tracestate_Entry_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_opencensus_proto_trace_v1_Span_Tracestate_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_opencensus_proto_trace_v1_Span_Tracestate_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_opencensus_proto_trace_v1_Span_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_opencensus_proto_trace_v1_Span_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_opencensus_proto_trace_v1_StackTrace_StackFrame_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_opencensus_proto_trace_v1_StackTrace_StackFrame_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_opencensus_proto_trace_v1_StackTrace_StackFrames_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_opencensus_proto_trace_v1_StackTrace_StackFrames_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_opencensus_proto_trace_v1_StackTrace_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_opencensus_proto_trace_v1_StackTrace_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_opencensus_proto_trace_v1_Status_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_opencensus_proto_trace_v1_Status_fieldAccessorTable;
    static final Descriptors.Descriptor internal_static_opencensus_proto_trace_v1_TruncatableString_descriptor;
    static final GeneratedMessageV3.FieldAccessorTable internal_static_opencensus_proto_trace_v1_TruncatableString_fieldAccessorTable;
    private static Descriptors.FileDescriptor descriptor;

    static {
        Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(new String[]{"\n%opencensus/proto/trace/v1/trace.proto\u0012\u0019opencensus.proto.trace.v1\u001a+opencensus/proto/resource/v1/resource.proto\u001a\u001fgoogle/protobuf/timestamp.proto\u001a\u001egoogle/protobuf/wrappers.proto\"ì\u0011\n\u0004Span\u0012\u0010\n\btrace_id\u0018\u0001 \u0001(\f\u0012\u000f\n\u0007span_id\u0018\u0002 \u0001(\f\u0012>\n\ntracestate\u0018\u000f \u0001(\u000b2*.opencensus.proto.trace.v1.Span.Tracestate\u0012\u0016\n\u000eparent_span_id\u0018\u0003 \u0001(\f\u0012:\n\u0004name\u0018\u0004 \u0001(\u000b2,.opencensus.proto.trace.v1.TruncatableString\u00126\n\u0004kind\u0018\u000e \u0001(\u000e2(.opencensus.proto.trace.v1.Span.SpanKind\u0012.\n\nstart_time\u0018\u0005 \u0001(\u000b2\u001a.google.protobuf.Timestamp\u0012,\n\bend_time\u0018\u0006 \u0001(\u000b2\u001a.google.protobuf.Timestamp\u0012>\n\nattributes\u0018\u0007 \u0001(\u000b2*.opencensus.proto.trace.v1.Span.Attributes\u0012:\n\u000bstack_trace\u0018\b \u0001(\u000b2%.opencensus.proto.trace.v1.StackTrace\u0012?\n\u000btime_events\u0018\t \u0001(\u000b2*.opencensus.proto.trace.v1.Span.TimeEvents\u00124\n\u0005links\u0018\n \u0001(\u000b2%.opencensus.proto.trace.v1.Span.Links\u00121\n\u0006status\u0018\u000b \u0001(\u000b2!.opencensus.proto.trace.v1.Status\u00128\n\bresource\u0018\u0010 \u0001(\u000b2&.opencensus.proto.resource.v1.Resource\u0012?\n\u001bsame_process_as_parent_span\u0018\f \u0001(\u000b2\u001a.google.protobuf.BoolValue\u00126\n\u0010child_span_count\u0018\r \u0001(\u000b2\u001c.google.protobuf.UInt32Value\u001at\n\nTracestate\u0012A\n\u0007entries\u0018\u0001 \u0003(\u000b20.opencensus.proto.trace.v1.Span.Tracestate.Entry\u001a#\n\u0005Entry\u0012\u000b\n\u0003key\u0018\u0001 \u0001(\t\u0012\r\n\u0005value\u0018\u0002 \u0001(\t\u001aã\u0001\n\nAttributes\u0012S\n\rattribute_map\u0018\u0001 \u0003(\u000b2<.opencensus.proto.trace.v1.Span.Attributes.AttributeMapEntry\u0012 \n\u0018dropped_attributes_count\u0018\u0002 \u0001(\u0005\u001a^\n\u0011AttributeMapEntry\u0012\u000b\n\u0003key\u0018\u0001 \u0001(\t\u00128\n\u0005value\u0018\u0002 \u0001(\u000b2).opencensus.proto.trace.v1.AttributeValue:\u00028\u0001\u001a¿\u0004\n\tTimeEvent\u0012(\n\u0004time\u0018\u0001 \u0001(\u000b2\u001a.google.protobuf.Timestamp\u0012J\n\nannotation\u0018\u0002 \u0001(\u000b24.opencensus.proto.trace.v1.Span.TimeEvent.AnnotationH\u0000\u0012O\n\rmessage_event\u0018\u0003 \u0001(\u000b26.opencensus.proto.trace.v1.Span.TimeEvent.MessageEventH\u0000\u001a\u008f\u0001\n\nAnnotation\u0012A\n\u000bdescription\u0018\u0001 \u0001(\u000b2,.opencensus.proto.trace.v1.TruncatableString\u0012>\n\nattributes\u0018\u0002 \u0001(\u000b2*.opencensus.proto.trace.v1.Span.Attributes\u001aÏ\u0001\n\fMessageEvent\u0012I\n\u0004type\u0018\u0001 \u0001(\u000e2;.opencensus.proto.trace.v1.Span.TimeEvent.MessageEvent.Type\u0012\n\n\u0002id\u0018\u0002 \u0001(\u0004\u0012\u0019\n\u0011uncompressed_size\u0018\u0003 \u0001(\u0004\u0012\u0017\n\u000fcompressed_size\u0018\u0004 \u0001(\u0004\"4\n\u0004Type\u0012\u0014\n\u0010TYPE_UNSPECIFIED\u0010\u0000\u0012\b\n\u0004SENT\u0010\u0001\u0012\f\n\bRECEIVED\u0010\u0002B\u0007\n\u0005value\u001a\u0094\u0001\n\nTimeEvents\u0012=\n\ntime_event\u0018\u0001 \u0003(\u000b2).opencensus.proto.trace.v1.Span.TimeEvent\u0012!\n\u0019dropped_annotations_count\u0018\u0002 \u0001(\u0005\u0012$\n\u001cdropped_message_events_count\u0018\u0003 \u0001(\u0005\u001aï\u0001\n\u0004Link\u0012\u0010\n\btrace_id\u0018\u0001 \u0001(\f\u0012\u000f\n\u0007span_id\u0018\u0002 \u0001(\f\u00127\n\u0004type\u0018\u0003 \u0001(\u000e2).opencensus.proto.trace.v1.Span.Link.Type\u0012>\n\nattributes\u0018\u0004 \u0001(\u000b2*.opencensus.proto.trace.v1.Span.Attributes\"K\n\u0004Type\u0012\u0014\n\u0010TYPE_UNSPECIFIED\u0010\u0000\u0012\u0015\n\u0011CHILD_LINKED_SPAN\u0010\u0001\u0012\u0016\n\u0012PARENT_LINKED_SPAN\u0010\u0002\u001aX\n\u0005Links\u00122\n\u0004link\u0018\u0001 \u0003(\u000b2$.opencensus.proto.trace.v1.Span.Link\u0012\u001b\n\u0013dropped_links_count\u0018\u0002 \u0001(\u0005\"=\n\bSpanKind\u0012\u0019\n\u0015SPAN_KIND_UNSPECIFIED\u0010\u0000\u0012\n\n\u0006SERVER\u0010\u0001\u0012\n\n\u0006CLIENT\u0010\u0002\"'\n\u0006Status\u0012\f\n\u0004code\u0018\u0001 \u0001(\u0005\u0012\u000f\n\u0007message\u0018\u0002 \u0001(\t\"¢\u0001\n\u000eAttributeValue\u0012D\n\fstring_value\u0018\u0001 \u0001(\u000b2,.opencensus.proto.trace.v1.TruncatableStringH\u0000\u0012\u0013\n\tint_value\u0018\u0002 \u0001(\u0003H\u0000\u0012\u0014\n\nbool_value\u0018\u0003 \u0001(\bH\u0000\u0012\u0016\n\fdouble_value\u0018\u0004 \u0001(\u0001H\u0000B\u0007\n\u0005value\"í\u0004\n\nStackTrace\u0012G\n\fstack_frames\u0018\u0001 \u0001(\u000b21.opencensus.proto.trace.v1.StackTrace.StackFrames\u0012\u001b\n\u0013stack_trace_hash_id\u0018\u0002 \u0001(\u0004\u001a\u008a\u0003\n\nStackFrame\u0012C\n\rfunction_name\u0018\u0001 \u0001(\u000b2,.opencensus.proto.trace.v1.TruncatableString\u0012L\n\u0016original_function_name\u0018\u0002 \u0001(\u000b2,.opencensus.proto.trace.v1.TruncatableString\u0012?\n\tfile_name\u0018\u0003 \u0001(\u000b2,.opencensus.proto.trace.v1.TruncatableString\u0012\u0013\n\u000bline_number\u0018\u0004 \u0001(\u0003\u0012\u0015\n\rcolumn_number\u0018\u0005 \u0001(\u0003\u00126\n\u000bload_module\u0018\u0006 \u0001(\u000b2!.opencensus.proto.trace.v1.Module\u0012D\n\u000esource_version\u0018\u0007 \u0001(\u000b2,.opencensus.proto.trace.v1.TruncatableString\u001al\n\u000bStackFrames\u0012?\n\u0005frame\u0018\u0001 \u0003(\u000b20.opencensus.proto.trace.v1.StackTrace.StackFrame\u0012\u001c\n\u0014dropped_frames_count\u0018\u0002 \u0001(\u0005\"\u0086\u0001\n\u0006Module\u0012<\n\u0006module\u0018\u0001 \u0001(\u000b2,.opencensus.proto.trace.v1.TruncatableString\u0012>\n\bbuild_id\u0018\u0002 \u0001(\u000b2,.opencensus.proto.trace.v1.TruncatableString\"@\n\u0011TruncatableString\u0012\r\n\u0005value\u0018\u0001 \u0001(\t\u0012\u001c\n\u0014truncated_byte_count\u0018\u0002 \u0001(\u0005Bp\n\u001cio.opencensus.proto.trace.v1B\nTraceProtoP\u0001ZBgithub.com/census-instrumentation/opencensus-proto/gen-go/trace/v1b\u0006proto3"}, new Descriptors.FileDescriptor[]{ResourceProto.getDescriptor(), TimestampProto.getDescriptor(), WrappersProto.getDescriptor()}, new Descriptors.FileDescriptor.InternalDescriptorAssigner() { // from class: io.opencensus.proto.trace.v1.TraceProto.1
            public ExtensionRegistry assignDescriptors(Descriptors.FileDescriptor fileDescriptor) {
                Descriptors.FileDescriptor unused = TraceProto.descriptor = fileDescriptor;
                return null;
            }
        });
        Descriptors.Descriptor descriptor2 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(0);
        internal_static_opencensus_proto_trace_v1_Span_descriptor = descriptor2;
        internal_static_opencensus_proto_trace_v1_Span_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor2, new String[]{"TraceId", "SpanId", "Tracestate", "ParentSpanId", "Name", "Kind", "StartTime", "EndTime", "Attributes", "StackTrace", "TimeEvents", "Links", "Status", "Resource", "SameProcessAsParentSpan", "ChildSpanCount"});
        Descriptors.Descriptor descriptor3 = (Descriptors.Descriptor) descriptor2.getNestedTypes().get(0);
        internal_static_opencensus_proto_trace_v1_Span_Tracestate_descriptor = descriptor3;
        internal_static_opencensus_proto_trace_v1_Span_Tracestate_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor3, new String[]{"Entries"});
        Descriptors.Descriptor descriptor4 = (Descriptors.Descriptor) descriptor3.getNestedTypes().get(0);
        internal_static_opencensus_proto_trace_v1_Span_Tracestate_Entry_descriptor = descriptor4;
        internal_static_opencensus_proto_trace_v1_Span_Tracestate_Entry_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor4, new String[]{"Key", "Value"});
        Descriptors.Descriptor descriptor5 = (Descriptors.Descriptor) descriptor2.getNestedTypes().get(1);
        internal_static_opencensus_proto_trace_v1_Span_Attributes_descriptor = descriptor5;
        internal_static_opencensus_proto_trace_v1_Span_Attributes_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor5, new String[]{"AttributeMap", "DroppedAttributesCount"});
        Descriptors.Descriptor descriptor6 = (Descriptors.Descriptor) descriptor5.getNestedTypes().get(0);
        internal_static_opencensus_proto_trace_v1_Span_Attributes_AttributeMapEntry_descriptor = descriptor6;
        internal_static_opencensus_proto_trace_v1_Span_Attributes_AttributeMapEntry_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor6, new String[]{"Key", "Value"});
        Descriptors.Descriptor descriptor7 = (Descriptors.Descriptor) descriptor2.getNestedTypes().get(2);
        internal_static_opencensus_proto_trace_v1_Span_TimeEvent_descriptor = descriptor7;
        internal_static_opencensus_proto_trace_v1_Span_TimeEvent_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor7, new String[]{"Time", "Annotation", "MessageEvent", "Value"});
        Descriptors.Descriptor descriptor8 = (Descriptors.Descriptor) descriptor7.getNestedTypes().get(0);
        internal_static_opencensus_proto_trace_v1_Span_TimeEvent_Annotation_descriptor = descriptor8;
        internal_static_opencensus_proto_trace_v1_Span_TimeEvent_Annotation_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor8, new String[]{"Description", "Attributes"});
        Descriptors.Descriptor descriptor9 = (Descriptors.Descriptor) descriptor7.getNestedTypes().get(1);
        internal_static_opencensus_proto_trace_v1_Span_TimeEvent_MessageEvent_descriptor = descriptor9;
        internal_static_opencensus_proto_trace_v1_Span_TimeEvent_MessageEvent_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor9, new String[]{"Type", "Id", "UncompressedSize", "CompressedSize"});
        Descriptors.Descriptor descriptor10 = (Descriptors.Descriptor) descriptor2.getNestedTypes().get(3);
        internal_static_opencensus_proto_trace_v1_Span_TimeEvents_descriptor = descriptor10;
        internal_static_opencensus_proto_trace_v1_Span_TimeEvents_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor10, new String[]{"TimeEvent", "DroppedAnnotationsCount", "DroppedMessageEventsCount"});
        Descriptors.Descriptor descriptor11 = (Descriptors.Descriptor) descriptor2.getNestedTypes().get(4);
        internal_static_opencensus_proto_trace_v1_Span_Link_descriptor = descriptor11;
        internal_static_opencensus_proto_trace_v1_Span_Link_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor11, new String[]{"TraceId", "SpanId", "Type", "Attributes"});
        Descriptors.Descriptor descriptor12 = (Descriptors.Descriptor) descriptor2.getNestedTypes().get(5);
        internal_static_opencensus_proto_trace_v1_Span_Links_descriptor = descriptor12;
        internal_static_opencensus_proto_trace_v1_Span_Links_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor12, new String[]{HttpHeaders.LINK, "DroppedLinksCount"});
        Descriptors.Descriptor descriptor13 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(1);
        internal_static_opencensus_proto_trace_v1_Status_descriptor = descriptor13;
        internal_static_opencensus_proto_trace_v1_Status_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor13, new String[]{"Code", "Message"});
        Descriptors.Descriptor descriptor14 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(2);
        internal_static_opencensus_proto_trace_v1_AttributeValue_descriptor = descriptor14;
        internal_static_opencensus_proto_trace_v1_AttributeValue_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor14, new String[]{"StringValue", "IntValue", "BoolValue", "DoubleValue", "Value"});
        Descriptors.Descriptor descriptor15 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(3);
        internal_static_opencensus_proto_trace_v1_StackTrace_descriptor = descriptor15;
        internal_static_opencensus_proto_trace_v1_StackTrace_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor15, new String[]{"StackFrames", "StackTraceHashId"});
        Descriptors.Descriptor descriptor16 = (Descriptors.Descriptor) descriptor15.getNestedTypes().get(0);
        internal_static_opencensus_proto_trace_v1_StackTrace_StackFrame_descriptor = descriptor16;
        internal_static_opencensus_proto_trace_v1_StackTrace_StackFrame_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor16, new String[]{"FunctionName", "OriginalFunctionName", "FileName", "LineNumber", "ColumnNumber", "LoadModule", "SourceVersion"});
        Descriptors.Descriptor descriptor17 = (Descriptors.Descriptor) descriptor15.getNestedTypes().get(1);
        internal_static_opencensus_proto_trace_v1_StackTrace_StackFrames_descriptor = descriptor17;
        internal_static_opencensus_proto_trace_v1_StackTrace_StackFrames_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor17, new String[]{"Frame", "DroppedFramesCount"});
        Descriptors.Descriptor descriptor18 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(4);
        internal_static_opencensus_proto_trace_v1_Module_descriptor = descriptor18;
        internal_static_opencensus_proto_trace_v1_Module_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor18, new String[]{"Module", "BuildId"});
        Descriptors.Descriptor descriptor19 = (Descriptors.Descriptor) getDescriptor().getMessageTypes().get(5);
        internal_static_opencensus_proto_trace_v1_TruncatableString_descriptor = descriptor19;
        internal_static_opencensus_proto_trace_v1_TruncatableString_fieldAccessorTable = new GeneratedMessageV3.FieldAccessorTable(descriptor19, new String[]{"Value", "TruncatedByteCount"});
        ResourceProto.getDescriptor();
        TimestampProto.getDescriptor();
        WrappersProto.getDescriptor();
    }

    private TraceProto() {
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
