package com.buccancs.data.orchestration.security

import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import java.security.KeyStore
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class KeyStoreProvider @Inject constructor() {
    private val keyStore: KeyStore =
        KeyStore.getInstance(
            ANDROID_KEY_STORE
        )
            .apply {
                load(
                    null
                )
            }

    fun getOrCreateHmacKey(
        alias: String
    ): SecretKey {
        val existing =
            keyStore.getEntry(
                alias,
                null
            ) as? KeyStore.SecretKeyEntry
        if (existing != null) {
            return existing.secretKey
        }
        val generator =
            KeyGenerator.getInstance(
                KeyProperties.KEY_ALGORITHM_HMAC_SHA256,
                ANDROID_KEY_STORE
            )
        val spec =
            KeyGenParameterSpec.Builder(
                alias,
                KeyProperties.PURPOSE_SIGN or KeyProperties.PURPOSE_VERIFY
            )
                .setDigests(
                    KeyProperties.DIGEST_SHA256
                )
                .build()
        generator.init(
            spec
        )
        generator.generateKey()
        val created =
            keyStore.getEntry(
                alias,
                null
            ) as? KeyStore.SecretKeyEntry
                ?: throw IllegalStateException(
                    "Unable to generate key for $alias"
                )
        return created.secretKey
    }

    companion object {
        private const val ANDROID_KEY_STORE =
            "AndroidKeyStore"
    }
}

