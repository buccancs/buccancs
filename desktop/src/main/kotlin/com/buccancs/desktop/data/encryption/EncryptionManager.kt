package com.buccancs.desktop.data.encryption

import com.google.crypto.tink.*
import com.google.crypto.tink.aead.AeadConfig
import com.google.crypto.tink.aead.AesGcmKeyManager
import org.slf4j.LoggerFactory
import java.io.IOException
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.StandardOpenOption
import java.security.GeneralSecurityException
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

class EncryptionKeyProvider(
    private val keyFile: Path
) {
    private val logger = LoggerFactory.getLogger(EncryptionKeyProvider::class.java)
    private val lock = ReentrantLock()
    private val secretKeyAccess: SecretKeyAccess = InsecureSecretKeyAccess.get()

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
                readKeysetFromDisk()
            } else {
                keyFile.parent?.let(Files::createDirectories)
                val handle = KeysetHandle.generateNew(AesGcmKeyManager.aes256GcmTemplate())
                writeKeysetToDisk(handle)
                handle
            }
        } catch (ex: IOException) {
            logger.error("Failed to load encryption key, falling back to new key.", ex)
            val handle = KeysetHandle.generateNew(AesGcmKeyManager.aes256GcmTemplate())
            writeKeysetToDisk(handle)
            handle
        } catch (ex: GeneralSecurityException) {
            logger.error("Security error while loading encryption key.", ex)
            throw ex
        }
    }

    @Throws(IOException::class, GeneralSecurityException::class)
    private fun readKeysetFromDisk(): KeysetHandle {
        val serialized = Files.readString(keyFile, StandardCharsets.UTF_8)
        return TinkJsonProtoKeysetFormat.parseKeyset(serialized, secretKeyAccess)
    }

    @Throws(IOException::class, GeneralSecurityException::class)
    private fun writeKeysetToDisk(handle: KeysetHandle) {
        keyFile.parent?.let(Files::createDirectories)
        val serialized = TinkJsonProtoKeysetFormat.serializeKeyset(handle, secretKeyAccess)
        Files.writeString(
            keyFile,
            serialized,
            StandardCharsets.UTF_8,
            StandardOpenOption.CREATE,
            StandardOpenOption.TRUNCATE_EXISTING,
            StandardOpenOption.WRITE
        )
    }
}

class EncryptionManager(
    keyProvider: EncryptionKeyProvider
) {
    private val aead: Aead = keyProvider
        .keyset()
        .getPrimitive(RegistryConfiguration.get(), Aead::class.java)

    fun encrypt(plain: ByteArray, context: ByteArray): ByteArray = aead.encrypt(plain, context)
    fun decrypt(cipher: ByteArray, context: ByteArray): ByteArray = aead.decrypt(cipher, context)
}
