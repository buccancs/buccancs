package io.grpc.auth;

import com.google.auth.Credentials;
import com.google.common.base.Preconditions;
import io.grpc.CallOptions;
import io.grpc.Channel;
import io.grpc.ClientCall;
import io.grpc.ClientInterceptor;
import io.grpc.ClientInterceptors;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.Status;
import io.grpc.StatusException;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;

@Deprecated
/* loaded from: classes2.dex */
public final class ClientAuthInterceptor implements ClientInterceptor {
    private final Credentials credentials;
    private Metadata cached;
    private Map<String, List<String>> lastMetadata;

    public ClientAuthInterceptor(Credentials credentials, Executor executor) {
        this.credentials = (Credentials) Preconditions.checkNotNull(credentials, "credentials");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Metadata toHeaders(Map<String, List<String>> map) {
        Metadata metadata = new Metadata();
        if (map != null) {
            for (String str : map.keySet()) {
                Metadata.Key keyOf = Metadata.Key.of(str, Metadata.ASCII_STRING_MARSHALLER);
                Iterator<String> it2 = map.get(str).iterator();
                while (it2.hasNext()) {
                    metadata.put(keyOf, it2.next());
                }
            }
        }
        return metadata;
    }

    @Override // io.grpc.ClientInterceptor
    public <ReqT, RespT> ClientCall<ReqT, RespT> interceptCall(final MethodDescriptor<ReqT, RespT> methodDescriptor, CallOptions callOptions, final Channel channel) {
        return new ClientInterceptors.CheckedForwardingClientCall<ReqT, RespT>(channel.newCall(methodDescriptor, callOptions)) { // from class: io.grpc.auth.ClientAuthInterceptor.1
            @Override // io.grpc.ClientInterceptors.CheckedForwardingClientCall
            protected void checkedStart(ClientCall.Listener<RespT> listener, Metadata metadata) throws StatusException {
                Metadata metadata2;
                URI uriServiceUri = ClientAuthInterceptor.this.serviceUri(channel, methodDescriptor);
                synchronized (ClientAuthInterceptor.this) {
                    Map requestMetadata = ClientAuthInterceptor.this.getRequestMetadata(uriServiceUri);
                    if (ClientAuthInterceptor.this.lastMetadata == null || ClientAuthInterceptor.this.lastMetadata != requestMetadata) {
                        ClientAuthInterceptor.this.lastMetadata = requestMetadata;
                        ClientAuthInterceptor clientAuthInterceptor = ClientAuthInterceptor.this;
                        clientAuthInterceptor.cached = ClientAuthInterceptor.toHeaders(clientAuthInterceptor.lastMetadata);
                    }
                    metadata2 = ClientAuthInterceptor.this.cached;
                }
                metadata.merge(metadata2);
                delegate().start(listener, metadata);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public URI serviceUri(Channel channel, MethodDescriptor<?, ?> methodDescriptor) throws StatusException {
        String strAuthority = channel.authority();
        if (strAuthority == null) {
            throw Status.UNAUTHENTICATED.withDescription("Channel has no authority").asException();
        }
        try {
            URI uri = new URI("https", strAuthority, "/" + methodDescriptor.getServiceName(), null, null);
            return uri.getPort() == 443 ? removePort(uri) : uri;
        } catch (URISyntaxException e) {
            throw Status.UNAUTHENTICATED.withDescription("Unable to construct service URI for auth").withCause(e).asException();
        }
    }

    private URI removePort(URI uri) throws StatusException {
        try {
            return new URI(uri.getScheme(), uri.getUserInfo(), uri.getHost(), -1, uri.getPath(), uri.getQuery(), uri.getFragment());
        } catch (URISyntaxException e) {
            throw Status.UNAUTHENTICATED.withDescription("Unable to construct service URI after removing port").withCause(e).asException();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Map<String, List<String>> getRequestMetadata(URI uri) throws StatusException {
        try {
            return this.credentials.getRequestMetadata(uri);
        } catch (IOException e) {
            throw Status.UNAUTHENTICATED.withDescription("Unable to get request metadata").withCause(e).asException();
        }
    }
}
