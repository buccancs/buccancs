package com.buccancs.data.orchestration.server

import com.buccancs.data.orchestration.security.TokenIssuer
import io.grpc.Metadata
import io.grpc.ServerCall
import io.grpc.ServerCallHandler
import io.grpc.ServerInterceptor
import io.grpc.Status
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthInterceptor @Inject constructor(
    private val tokenIssuer: TokenIssuer
) : ServerInterceptor {
    override fun <ReqT, RespT> interceptCall(
        call: ServerCall<ReqT, RespT>,
        headers: Metadata,
        next: ServerCallHandler<ReqT, RespT>
    ): ServerCall.Listener<ReqT> {
        val tokenHeader = headers.get(AUTHORIZATION_KEY)
        val sessionId = headers.get(SESSION_ID_KEY)
        val tokenValue = tokenHeader?.removePrefix(BEARER_PREFIX)?.trim().orEmpty()
        val verification = tokenIssuer.verify(tokenValue, sessionId)
        if (!verification.valid) {
            call.close(
                Status.UNAUTHENTICATED.withDescription(verification.message),
                Metadata()
            )
            return object : ServerCall.Listener<ReqT>() {}
        }
        return next.startCall(call, headers)
    }

    companion object {
        private const val BEARER_PREFIX = "Bearer "
        private val AUTHORIZATION_KEY: Metadata.Key<String> =
            Metadata.Key.of("authorization", Metadata.ASCII_STRING_MARSHALLER)
        private val SESSION_ID_KEY: Metadata.Key<String> =
            Metadata.Key.of("x-session-id", Metadata.ASCII_STRING_MARSHALLER)
    }
}

