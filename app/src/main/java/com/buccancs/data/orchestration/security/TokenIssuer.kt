package com.buccancs.data.orchestration.security

import android.util.Base64
import com.buccancs.util.nowInstant
import kotlin.time.Instant
import java.nio.charset.StandardCharsets
import javax.crypto.Mac
import javax.crypto.SecretKey
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TokenIssuer @Inject constructor(
    private val keyStoreProvider: KeyStoreProvider
) {
    private val charset = StandardCharsets.UTF_8
    private val mac: Mac = Mac.getInstance(HMAC_ALGORITHM).apply {
        init(resolveKey())
    }

    fun issue(sessionId: String, ttlMillis: Long): IssuedToken {
        val issuedAt = nowInstant()
        val expiresAt = Instant.fromEpochMilliseconds(
            issuedAt.toEpochMilliseconds() + ttlMillis.coerceAtLeast(MIN_TTL_MS)
        )
        val payload = "${sessionId}|${expiresAt.toEpochMilliseconds()}"
        val signature = sign(payload)
        val tokenValue = listOf(sessionId, expiresAt.toEpochMilliseconds(), signature)
            .joinToString(separator = ":")
        return IssuedToken(
            value = tokenValue,
            expiresAt = expiresAt
        )
    }

    fun verify(token: String, expectedSessionId: String?): TokenVerification {
        if (token.isBlank()) {
            return TokenVerification(false, null, null, "Missing token")
        }
        val parts = token.split(":")
        if (parts.size != 3) {
            return TokenVerification(false, null, null, "Malformed token")
        }
        val sessionId = parts[0]
        val expiresEpoch = parts[1].toLongOrNull()
            ?: return TokenVerification(false, sessionId, null, "Invalid expiry")
        val signature = parts[2]
        val expiresAt = Instant.fromEpochMilliseconds(expiresEpoch)
        if (expiresAt < nowInstant()) {
            return TokenVerification(false, sessionId, expiresAt, "Expired token")
        }
        val payload = "${sessionId}|$expiresEpoch"
        val expectedSignature = sign(payload)
        if (signature != expectedSignature) {
            return TokenVerification(false, sessionId, expiresAt, "Signature mismatch")
        }
        if (expectedSessionId != null && expectedSessionId.isNotEmpty() && expectedSessionId != sessionId) {
            return TokenVerification(false, sessionId, expiresAt, "Session mismatch")
        }
        return TokenVerification(true, sessionId, expiresAt, null)
    }

    private fun sign(payload: String): String {
        val signature = synchronized(mac) {
            mac.doFinal(payload.toByteArray(charset))
        }
        return Base64.encodeToString(signature, Base64.NO_WRAP)
    }

    private fun resolveKey(): SecretKey = keyStoreProvider.getOrCreateHmacKey(ALIAS)

    data class IssuedToken(
        val value: String,
        val expiresAt: Instant
    )

    data class TokenVerification(
        val valid: Boolean,
        val sessionId: String?,
        val expiresAt: Instant?,
        val message: String?
    )

    companion object {
        private const val ALIAS = "control-service-hmac"
        private const val HMAC_ALGORITHM = "HmacSHA256"
        private const val MIN_TTL_MS = 5_000L
    }
}

