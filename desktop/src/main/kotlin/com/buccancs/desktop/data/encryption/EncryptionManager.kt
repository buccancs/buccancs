package com.buccancs.desktop.data.encryption

import com.google.crypto.tink.Aead
import com.google.crypto.tink.KeysetHandle
import com.google.crypto.tink.aead.AeadConfig
import com.google.crypto.tink.aead.AesGcmKeyManager
import com.google.crypto.tink.io.CleartextKeysetHandle
import com.google.crypto.tink.JsonKeysetReader
import com.google.crypto.tink.JsonKeysetWriter
import org.slf4j.LoggerFactory
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Path
import java.security.GeneralSecurityException
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

class EncryptionKeyProvider(
    private val keyFile: Path
) {
    private val logger = LoggerFactory.getLogger(EncryptionKeyProvider::class.java)
    private val lock = ReentrantLock()
    @Volatile
    private var cachedHandle: KeysetHandle? = null

    init {
        AeadConfig.register()
    }

    fun keyset(): KeysetHandle = cachedHandle ?: loadOrCreate().also { cachedHandle = it }

    private fun loadOrCreate(): KeysetHandle = lock.withLock {
        cachedHandle?.let { return it }
        return try {
            if (Files.exists(keyFile)) {
                CleartextKeysetHandle.read(JsonKeysetReader.withFile(keyFile.toFile()))
            } else {
                Files.createDirectories(keyFile.parent)
                val handle = KeysetHandle.generateNew(AesGcmKeyManager.aes256GcmTemplate())
                CleartextKeysetHandle.write(handle, JsonKeysetWriter.withFile(keyFile.toFile()))
                handle
            }
        } catch (ex: IOException) {
            logger.error("Failed to load encryption key, falling back to new key.", ex)
            val handle = KeysetHandle.generateNew(AesGcmKeyManager.aes256GcmTemplate())
            CleartextKeysetHandle.write(handle, JsonKeysetWriter.withFile(keyFile.toFile()))
            handle
        } catch (ex: GeneralSecurityException) {
            logger.error("Security error while loading encryption key.", ex)
            throw ex
        }
    }
}

class EncryptionManager(
    keyProvider: EncryptionKeyProvider
) {
    private val aead: Aead = keyProvider.keyset().getPrimitive(Aead::class.java)

    fun encrypt(plain: ByteArray, context: ByteArray): ByteArray = aead.encrypt(plain, context)

    fun decrypt(cipher: ByteArray, context: ByteArray): ByteArray = aead.decrypt(cipher, context)
}
