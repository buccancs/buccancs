#pragma warning disable 0414, 1591
#region Designer generated code

using grpc = global::Grpc.Core;

namespace com.shimmerresearch.grpc {
  public static partial class ShimmerBLEByteServer
  {
    static readonly string __ServiceName = "shimmerBLEGRPC.ShimmerBLEByteServer";

    static void __Helper_SerializeMessage(global::Google.Protobuf.IMessage message, grpc::SerializationContext context)
    {
      #if !GRPC_DISABLE_PROTOBUF_BUFFER_SERIALIZATION
      if (message is global::Google.Protobuf.IBufferMessage)
      {
        context.SetPayloadLength(message.CalculateSize());
        global::Google.Protobuf.MessageExtensions.WriteTo(message, context.GetBufferWriter());
        context.Complete();
        return;
      }
      #endif
      context.Complete(global::Google.Protobuf.MessageExtensions.ToByteArray(message));
    }

    static class __Helper_MessageCache<T>
    {
      public static readonly bool IsBufferMessage = global::System.Reflection.IntrospectionExtensions.GetTypeInfo(typeof(global::Google.Protobuf.IBufferMessage)).IsAssignableFrom(typeof(T));
    }

    static T __Helper_DeserializeMessage<T>(grpc::DeserializationContext context, global::Google.Protobuf.MessageParser<T> parser) where T : global::Google.Protobuf.IMessage<T>
    {
      #if !GRPC_DISABLE_PROTOBUF_BUFFER_SERIALIZATION
      if (__Helper_MessageCache<T>.IsBufferMessage)
      {
        return parser.ParseFrom(context.PayloadAsReadOnlySequence());
      }
      #endif
      return parser.ParseFrom(context.PayloadAsNewBuffer());
    }

    static readonly grpc::Marshaller<global::com.shimmerresearch.grpc.StreamRequest> __Marshaller_shimmerBLEGRPC_StreamRequest = grpc::Marshallers.Create(__Helper_SerializeMessage, context => __Helper_DeserializeMessage(context, global::com.shimmerresearch.grpc.StreamRequest.Parser));
    static readonly grpc::Marshaller<global::com.shimmerresearch.grpc.ObjectClusterByteArray> __Marshaller_shimmerBLEGRPC_ObjectClusterByteArray = grpc::Marshallers.Create(__Helper_SerializeMessage, context => __Helper_DeserializeMessage(context, global::com.shimmerresearch.grpc.ObjectClusterByteArray.Parser));
    static readonly grpc::Marshaller<global::com.shimmerresearch.grpc.Reply> __Marshaller_shimmerBLEGRPC_Reply = grpc::Marshallers.Create(__Helper_SerializeMessage, context => __Helper_DeserializeMessage(context, global::com.shimmerresearch.grpc.Reply.Parser));
    static readonly grpc::Marshaller<global::com.shimmerresearch.grpc.Request> __Marshaller_shimmerBLEGRPC_Request = grpc::Marshallers.Create(__Helper_SerializeMessage, context => __Helper_DeserializeMessage(context, global::com.shimmerresearch.grpc.Request.Parser));
    static readonly grpc::Marshaller<global::com.shimmerresearch.grpc.StateStatus> __Marshaller_shimmerBLEGRPC_StateStatus = grpc::Marshallers.Create(__Helper_SerializeMessage, context => __Helper_DeserializeMessage(context, global::com.shimmerresearch.grpc.StateStatus.Parser));
    static readonly grpc::Marshaller<global::com.shimmerresearch.grpc.WriteBytes> __Marshaller_shimmerBLEGRPC_WriteBytes = grpc::Marshallers.Create(__Helper_SerializeMessage, context => __Helper_DeserializeMessage(context, global::com.shimmerresearch.grpc.WriteBytes.Parser));

    static readonly grpc::Method<global::com.shimmerresearch.grpc.StreamRequest, global::com.shimmerresearch.grpc.ObjectClusterByteArray> __Method_GetDataStream = new grpc::Method<global::com.shimmerresearch.grpc.StreamRequest, global::com.shimmerresearch.grpc.ObjectClusterByteArray>(
        grpc::MethodType.ServerStreaming,
        __ServiceName,
        "GetDataStream",
        __Marshaller_shimmerBLEGRPC_StreamRequest,
        __Marshaller_shimmerBLEGRPC_ObjectClusterByteArray);

    static readonly grpc::Method<global::com.shimmerresearch.grpc.StreamRequest, global::com.shimmerresearch.grpc.ObjectClusterByteArray> __Method_GetTestDataStream = new grpc::Method<global::com.shimmerresearch.grpc.StreamRequest, global::com.shimmerresearch.grpc.ObjectClusterByteArray>(
        grpc::MethodType.ServerStreaming,
        __ServiceName,
        "GetTestDataStream",
        __Marshaller_shimmerBLEGRPC_StreamRequest,
        __Marshaller_shimmerBLEGRPC_ObjectClusterByteArray);

    static readonly grpc::Method<global::com.shimmerresearch.grpc.ObjectClusterByteArray, global::com.shimmerresearch.grpc.Reply> __Method_SendDataStream = new grpc::Method<global::com.shimmerresearch.grpc.ObjectClusterByteArray, global::com.shimmerresearch.grpc.Reply>(
        grpc::MethodType.ClientStreaming,
        __ServiceName,
        "SendDataStream",
        __Marshaller_shimmerBLEGRPC_ObjectClusterByteArray,
        __Marshaller_shimmerBLEGRPC_Reply);

    static readonly grpc::Method<global::com.shimmerresearch.grpc.Request, global::com.shimmerresearch.grpc.StateStatus> __Method_ConnectShimmer = new grpc::Method<global::com.shimmerresearch.grpc.Request, global::com.shimmerresearch.grpc.StateStatus>(
        grpc::MethodType.ServerStreaming,
        __ServiceName,
        "ConnectShimmer",
        __Marshaller_shimmerBLEGRPC_Request,
        __Marshaller_shimmerBLEGRPC_StateStatus);

    static readonly grpc::Method<global::com.shimmerresearch.grpc.Request, global::com.shimmerresearch.grpc.Reply> __Method_DisconnectShimmer = new grpc::Method<global::com.shimmerresearch.grpc.Request, global::com.shimmerresearch.grpc.Reply>(
        grpc::MethodType.Unary,
        __ServiceName,
        "DisconnectShimmer",
        __Marshaller_shimmerBLEGRPC_Request,
        __Marshaller_shimmerBLEGRPC_Reply);

    static readonly grpc::Method<global::com.shimmerresearch.grpc.WriteBytes, global::com.shimmerresearch.grpc.Reply> __Method_WriteBytesShimmer = new grpc::Method<global::com.shimmerresearch.grpc.WriteBytes, global::com.shimmerresearch.grpc.Reply>(
        grpc::MethodType.Unary,
        __ServiceName,
        "WriteBytesShimmer",
        __Marshaller_shimmerBLEGRPC_WriteBytes,
        __Marshaller_shimmerBLEGRPC_Reply);

    static readonly grpc::Method<global::com.shimmerresearch.grpc.Request, global::com.shimmerresearch.grpc.Reply> __Method_SayHello = new grpc::Method<global::com.shimmerresearch.grpc.Request, global::com.shimmerresearch.grpc.Reply>(
        grpc::MethodType.Unary,
        __ServiceName,
        "SayHello",
        __Marshaller_shimmerBLEGRPC_Request,
        __Marshaller_shimmerBLEGRPC_Reply);
    public static global::Google.Protobuf.Reflection.ServiceDescriptor Descriptor
    {
      get { return global::com.shimmerresearch.grpc.ShimmerBLEGrpcAndPacketByteArrayReflection.Descriptor.Services[0]; }
    }
    [grpc::BindServiceMethod(typeof(ShimmerBLEByteServer), "BindService")]
    public abstract partial class ShimmerBLEByteServerBase
    {
      public virtual global::System.Threading.Tasks.Task GetDataStream(global::com.shimmerresearch.grpc.StreamRequest request, grpc::IServerStreamWriter<global::com.shimmerresearch.grpc.ObjectClusterByteArray> responseStream, grpc::ServerCallContext context)
      {
        throw new grpc::RpcException(new grpc::Status(grpc::StatusCode.Unimplemented, ""));
      }
      public virtual global::System.Threading.Tasks.Task GetTestDataStream(global::com.shimmerresearch.grpc.StreamRequest request, grpc::IServerStreamWriter<global::com.shimmerresearch.grpc.ObjectClusterByteArray> responseStream, grpc::ServerCallContext context)
      {
        throw new grpc::RpcException(new grpc::Status(grpc::StatusCode.Unimplemented, ""));
      }
      public virtual global::System.Threading.Tasks.Task<global::com.shimmerresearch.grpc.Reply> SendDataStream(grpc::IAsyncStreamReader<global::com.shimmerresearch.grpc.ObjectClusterByteArray> requestStream, grpc::ServerCallContext context)
      {
        throw new grpc::RpcException(new grpc::Status(grpc::StatusCode.Unimplemented, ""));
      }

      public virtual global::System.Threading.Tasks.Task ConnectShimmer(global::com.shimmerresearch.grpc.Request request, grpc::IServerStreamWriter<global::com.shimmerresearch.grpc.StateStatus> responseStream, grpc::ServerCallContext context)
      {
        throw new grpc::RpcException(new grpc::Status(grpc::StatusCode.Unimplemented, ""));
      }

      public virtual global::System.Threading.Tasks.Task<global::com.shimmerresearch.grpc.Reply> DisconnectShimmer(global::com.shimmerresearch.grpc.Request request, grpc::ServerCallContext context)
      {
        throw new grpc::RpcException(new grpc::Status(grpc::StatusCode.Unimplemented, ""));
      }

      public virtual global::System.Threading.Tasks.Task<global::com.shimmerresearch.grpc.Reply> WriteBytesShimmer(global::com.shimmerresearch.grpc.WriteBytes request, grpc::ServerCallContext context)
      {
        throw new grpc::RpcException(new grpc::Status(grpc::StatusCode.Unimplemented, ""));
      }

      public virtual global::System.Threading.Tasks.Task<global::com.shimmerresearch.grpc.Reply> SayHello(global::com.shimmerresearch.grpc.Request request, grpc::ServerCallContext context)
      {
        throw new grpc::RpcException(new grpc::Status(grpc::StatusCode.Unimplemented, ""));
      }

    }
    public partial class ShimmerBLEByteServerClient : grpc::ClientBase<ShimmerBLEByteServerClient>
    {
      public ShimmerBLEByteServerClient(grpc::ChannelBase channel) : base(channel)
      {
      }
      public ShimmerBLEByteServerClient(grpc::CallInvoker callInvoker) : base(callInvoker)
      {
      }
      protected ShimmerBLEByteServerClient() : base()
      {
      }
      protected ShimmerBLEByteServerClient(ClientBaseConfiguration configuration) : base(configuration)
      {
      }
      public virtual grpc::AsyncServerStreamingCall<global::com.shimmerresearch.grpc.ObjectClusterByteArray> GetDataStream(global::com.shimmerresearch.grpc.StreamRequest request, grpc::Metadata headers = null, global::System.DateTime? deadline = null, global::System.Threading.CancellationToken cancellationToken = default(global::System.Threading.CancellationToken))
      {
        return GetDataStream(request, new grpc::CallOptions(headers, deadline, cancellationToken));
      }
      public virtual grpc::AsyncServerStreamingCall<global::com.shimmerresearch.grpc.ObjectClusterByteArray> GetDataStream(global::com.shimmerresearch.grpc.StreamRequest request, grpc::CallOptions options)
      {
        return CallInvoker.AsyncServerStreamingCall(__Method_GetDataStream, null, options, request);
      }
      public virtual grpc::AsyncServerStreamingCall<global::com.shimmerresearch.grpc.ObjectClusterByteArray> GetTestDataStream(global::com.shimmerresearch.grpc.StreamRequest request, grpc::Metadata headers = null, global::System.DateTime? deadline = null, global::System.Threading.CancellationToken cancellationToken = default(global::System.Threading.CancellationToken))
      {
        return GetTestDataStream(request, new grpc::CallOptions(headers, deadline, cancellationToken));
      }
      public virtual grpc::AsyncServerStreamingCall<global::com.shimmerresearch.grpc.ObjectClusterByteArray> GetTestDataStream(global::com.shimmerresearch.grpc.StreamRequest request, grpc::CallOptions options)
      {
        return CallInvoker.AsyncServerStreamingCall(__Method_GetTestDataStream, null, options, request);
      }
      public virtual grpc::AsyncClientStreamingCall<global::com.shimmerresearch.grpc.ObjectClusterByteArray, global::com.shimmerresearch.grpc.Reply> SendDataStream(grpc::Metadata headers = null, global::System.DateTime? deadline = null, global::System.Threading.CancellationToken cancellationToken = default(global::System.Threading.CancellationToken))
      {
        return SendDataStream(new grpc::CallOptions(headers, deadline, cancellationToken));
      }
      public virtual grpc::AsyncClientStreamingCall<global::com.shimmerresearch.grpc.ObjectClusterByteArray, global::com.shimmerresearch.grpc.Reply> SendDataStream(grpc::CallOptions options)
      {
        return CallInvoker.AsyncClientStreamingCall(__Method_SendDataStream, null, options);
      }
      public virtual grpc::AsyncServerStreamingCall<global::com.shimmerresearch.grpc.StateStatus> ConnectShimmer(global::com.shimmerresearch.grpc.Request request, grpc::Metadata headers = null, global::System.DateTime? deadline = null, global::System.Threading.CancellationToken cancellationToken = default(global::System.Threading.CancellationToken))
      {
        return ConnectShimmer(request, new grpc::CallOptions(headers, deadline, cancellationToken));
      }
      public virtual grpc::AsyncServerStreamingCall<global::com.shimmerresearch.grpc.StateStatus> ConnectShimmer(global::com.shimmerresearch.grpc.Request request, grpc::CallOptions options)
      {
        return CallInvoker.AsyncServerStreamingCall(__Method_ConnectShimmer, null, options, request);
      }
      public virtual global::com.shimmerresearch.grpc.Reply DisconnectShimmer(global::com.shimmerresearch.grpc.Request request, grpc::Metadata headers = null, global::System.DateTime? deadline = null, global::System.Threading.CancellationToken cancellationToken = default(global::System.Threading.CancellationToken))
      {
        return DisconnectShimmer(request, new grpc::CallOptions(headers, deadline, cancellationToken));
      }
      public virtual global::com.shimmerresearch.grpc.Reply DisconnectShimmer(global::com.shimmerresearch.grpc.Request request, grpc::CallOptions options)
      {
        return CallInvoker.BlockingUnaryCall(__Method_DisconnectShimmer, null, options, request);
      }
      public virtual grpc::AsyncUnaryCall<global::com.shimmerresearch.grpc.Reply> DisconnectShimmerAsync(global::com.shimmerresearch.grpc.Request request, grpc::Metadata headers = null, global::System.DateTime? deadline = null, global::System.Threading.CancellationToken cancellationToken = default(global::System.Threading.CancellationToken))
      {
        return DisconnectShimmerAsync(request, new grpc::CallOptions(headers, deadline, cancellationToken));
      }
      public virtual grpc::AsyncUnaryCall<global::com.shimmerresearch.grpc.Reply> DisconnectShimmerAsync(global::com.shimmerresearch.grpc.Request request, grpc::CallOptions options)
      {
        return CallInvoker.AsyncUnaryCall(__Method_DisconnectShimmer, null, options, request);
      }
      public virtual global::com.shimmerresearch.grpc.Reply WriteBytesShimmer(global::com.shimmerresearch.grpc.WriteBytes request, grpc::Metadata headers = null, global::System.DateTime? deadline = null, global::System.Threading.CancellationToken cancellationToken = default(global::System.Threading.CancellationToken))
      {
        return WriteBytesShimmer(request, new grpc::CallOptions(headers, deadline, cancellationToken));
      }
      public virtual global::com.shimmerresearch.grpc.Reply WriteBytesShimmer(global::com.shimmerresearch.grpc.WriteBytes request, grpc::CallOptions options)
      {
        return CallInvoker.BlockingUnaryCall(__Method_WriteBytesShimmer, null, options, request);
      }
      public virtual grpc::AsyncUnaryCall<global::com.shimmerresearch.grpc.Reply> WriteBytesShimmerAsync(global::com.shimmerresearch.grpc.WriteBytes request, grpc::Metadata headers = null, global::System.DateTime? deadline = null, global::System.Threading.CancellationToken cancellationToken = default(global::System.Threading.CancellationToken))
      {
        return WriteBytesShimmerAsync(request, new grpc::CallOptions(headers, deadline, cancellationToken));
      }
      public virtual grpc::AsyncUnaryCall<global::com.shimmerresearch.grpc.Reply> WriteBytesShimmerAsync(global::com.shimmerresearch.grpc.WriteBytes request, grpc::CallOptions options)
      {
        return CallInvoker.AsyncUnaryCall(__Method_WriteBytesShimmer, null, options, request);
      }
      public virtual global::com.shimmerresearch.grpc.Reply SayHello(global::com.shimmerresearch.grpc.Request request, grpc::Metadata headers = null, global::System.DateTime? deadline = null, global::System.Threading.CancellationToken cancellationToken = default(global::System.Threading.CancellationToken))
      {
        return SayHello(request, new grpc::CallOptions(headers, deadline, cancellationToken));
      }
      public virtual global::com.shimmerresearch.grpc.Reply SayHello(global::com.shimmerresearch.grpc.Request request, grpc::CallOptions options)
      {
        return CallInvoker.BlockingUnaryCall(__Method_SayHello, null, options, request);
      }
      public virtual grpc::AsyncUnaryCall<global::com.shimmerresearch.grpc.Reply> SayHelloAsync(global::com.shimmerresearch.grpc.Request request, grpc::Metadata headers = null, global::System.DateTime? deadline = null, global::System.Threading.CancellationToken cancellationToken = default(global::System.Threading.CancellationToken))
      {
        return SayHelloAsync(request, new grpc::CallOptions(headers, deadline, cancellationToken));
      }
      public virtual grpc::AsyncUnaryCall<global::com.shimmerresearch.grpc.Reply> SayHelloAsync(global::com.shimmerresearch.grpc.Request request, grpc::CallOptions options)
      {
        return CallInvoker.AsyncUnaryCall(__Method_SayHello, null, options, request);
      }
      protected override ShimmerBLEByteServerClient NewInstance(ClientBaseConfiguration configuration)
      {
        return new ShimmerBLEByteServerClient(configuration);
      }
    }
    public static grpc::ServerServiceDefinition BindService(ShimmerBLEByteServerBase serviceImpl)
    {
      return grpc::ServerServiceDefinition.CreateBuilder()
          .AddMethod(__Method_GetDataStream, serviceImpl.GetDataStream)
          .AddMethod(__Method_GetTestDataStream, serviceImpl.GetTestDataStream)
          .AddMethod(__Method_SendDataStream, serviceImpl.SendDataStream)
          .AddMethod(__Method_ConnectShimmer, serviceImpl.ConnectShimmer)
          .AddMethod(__Method_DisconnectShimmer, serviceImpl.DisconnectShimmer)
          .AddMethod(__Method_WriteBytesShimmer, serviceImpl.WriteBytesShimmer)
          .AddMethod(__Method_SayHello, serviceImpl.SayHello).Build();
    }
    public static void BindService(grpc::ServiceBinderBase serviceBinder, ShimmerBLEByteServerBase serviceImpl)
    {
      serviceBinder.AddMethod(__Method_GetDataStream, serviceImpl == null ? null : new grpc::ServerStreamingServerMethod<global::com.shimmerresearch.grpc.StreamRequest, global::com.shimmerresearch.grpc.ObjectClusterByteArray>(serviceImpl.GetDataStream));
      serviceBinder.AddMethod(__Method_GetTestDataStream, serviceImpl == null ? null : new grpc::ServerStreamingServerMethod<global::com.shimmerresearch.grpc.StreamRequest, global::com.shimmerresearch.grpc.ObjectClusterByteArray>(serviceImpl.GetTestDataStream));
      serviceBinder.AddMethod(__Method_SendDataStream, serviceImpl == null ? null : new grpc::ClientStreamingServerMethod<global::com.shimmerresearch.grpc.ObjectClusterByteArray, global::com.shimmerresearch.grpc.Reply>(serviceImpl.SendDataStream));
      serviceBinder.AddMethod(__Method_ConnectShimmer, serviceImpl == null ? null : new grpc::ServerStreamingServerMethod<global::com.shimmerresearch.grpc.Request, global::com.shimmerresearch.grpc.StateStatus>(serviceImpl.ConnectShimmer));
      serviceBinder.AddMethod(__Method_DisconnectShimmer, serviceImpl == null ? null : new grpc::UnaryServerMethod<global::com.shimmerresearch.grpc.Request, global::com.shimmerresearch.grpc.Reply>(serviceImpl.DisconnectShimmer));
      serviceBinder.AddMethod(__Method_WriteBytesShimmer, serviceImpl == null ? null : new grpc::UnaryServerMethod<global::com.shimmerresearch.grpc.WriteBytes, global::com.shimmerresearch.grpc.Reply>(serviceImpl.WriteBytesShimmer));
      serviceBinder.AddMethod(__Method_SayHello, serviceImpl == null ? null : new grpc::UnaryServerMethod<global::com.shimmerresearch.grpc.Request, global::com.shimmerresearch.grpc.Reply>(serviceImpl.SayHello));
    }

  }
}
#endregion
