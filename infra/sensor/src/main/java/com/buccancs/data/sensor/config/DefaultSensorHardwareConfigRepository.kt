package com.buccancs.data.sensor.config

import android.content.Context
import android.util.Log
import com.buccancs.core.serialization.StandardJson
import com.buccancs.core.storage.WriteResult
import com.buccancs.data.storage.AtomicFileWriter
import com.buccancs.di.ApplicationScope
import com.buccancs.domain.model.RgbCameraConfig
import com.buccancs.domain.model.SensorHardwareConfig
import com.buccancs.domain.model.ShimmerDeviceConfig
import com.buccancs.domain.model.TopdonDeviceConfig
import com.buccancs.domain.repository.SensorHardwareConfigRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import java.io.File
import java.nio.charset.StandardCharsets
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultSensorHardwareConfigRepository @Inject constructor(
    @ApplicationContext private val context: Context,
    @ApplicationScope scope: CoroutineScope,
    @StandardJson private val json: Json
) : SensorHardwareConfigRepository {
    private val configMutex =
        Mutex()
    private val inventoryFile =
        File(
            context.filesDir,
            STORAGE_FILE_NAME
        )
    private val _config =
        MutableStateFlow(
            loadConfig()
        )
    override val config: StateFlow<SensorHardwareConfig> =
        _config.asStateFlow()

    init {
        scope.launch(
            Dispatchers.IO
        ) {
            _config.emit(
                loadConfig()
            )
        }
    }

    override suspend fun reload() {
        val refreshed =
            withContext(
                Dispatchers.IO
            ) { loadConfig() }
        _config.value =
            refreshed
    }

    override suspend fun upsertShimmerDevice(
        device: ShimmerDeviceConfig
    ) {
        updateConfig { current ->
            val next =
                current.shimmer.associateBy { it.id }
                    .toMutableMap()
            next[device.id] =
                device
            current.copy(
                shimmer = next.values.sortedBy { it.id })
        }
    }

    override suspend fun upsertTopdonDevice(
        device: TopdonDeviceConfig
    ) {
        updateConfig { current ->
            val next =
                current.topdon.associateBy { it.id }
                    .toMutableMap()
            next[device.id] =
                device
            current.copy(
                topdon = next.values.sortedBy { it.id })
        }
    }

    override suspend fun upsertRgbCamera(
        config: RgbCameraConfig
    ) {
        updateConfig { current ->
            val next =
                current.rgb.associateBy { it.id }
                    .toMutableMap()
            next[config.id] =
                config
            current.copy(
                rgb = next.values.sortedBy { it.id }
            )
        }
    }

    override suspend fun updateConfig(
        transform: (SensorHardwareConfig) -> SensorHardwareConfig
    ) {
        configMutex.withLock {
            val baseline =
                loadConfig()
            val updated =
                transform(
                    baseline
                ).sanitize()
            persist(
                updated
            )
            _config.value =
                updated
        }
    }

    private fun loadConfig(): SensorHardwareConfig {
        val payload =
            runCatching { readInventoryText() }
                .getOrElse { error ->
                    Log.w(
                        TAG,
                        "Unable to load $STORAGE_FILE_NAME: ${error.message}"
                    )
                    return defaultConfig()
                }
        return runCatching {
            json.decodeFromString<SensorHardwareConfig>(
                payload
            )
        }
            .getOrElse { error ->
                Log.w(
                    TAG,
                    "Failed to parse hardware config: ${error.message}"
                )
                defaultConfig()
            }
            .sanitize()
    }

    private fun readInventoryText(): String {
        ensureInventoryFile()
        return inventoryFile.readText(
            StandardCharsets.UTF_8
        )
    }

    private suspend fun persist(
        config: SensorHardwareConfig
    ) {
        ensureInventoryFile()
        val encoded =
            json.encodeToString(
                config
            )

        when (val result =
            AtomicFileWriter.writeAtomicSafe(
                inventoryFile,
                encoded,
                StandardCharsets.UTF_8,
                checkSpace = true
            )) {
            is WriteResult.Success -> {
                Log.d(
                    TAG,
                    "Config persisted successfully"
                )
            }

            is WriteResult.Failure.InsufficientSpace -> {
                Log.e(
                    TAG,
                    "Insufficient space to write config"
                )
            }

            is WriteResult.Failure.WriteError -> {
                Log.e(
                    TAG,
                    "Failed to write config: ${result.message}",
                    result.cause
                )
            }
        }
    }

    private fun ensureInventoryFile() {
        if (inventoryFile.exists()) return
        runCatching {
            context.assets.open(
                ASSET_NAME
            )
                .use { input ->
                    inventoryFile.outputStream()
                        .use { output ->
                            input.copyTo(
                                output
                            )
                        }
                }
        }.onFailure { error ->
            Log.w(
                TAG,
                "Unable to seed inventory asset: ${error.message}"
            )
            val seed =
                json.encodeToString(
                    defaultConfig()
                )
            inventoryFile.writeText(
                seed,
                StandardCharsets.UTF_8
            )
        }
    }

    private fun defaultConfig(): SensorHardwareConfig =
        SensorHardwareConfig(
            shimmer = listOf(
                ShimmerDeviceConfig(
                    id = "shimmer-primary",
                    displayName = "Shimmer3 GSR (Primary)"
                )
            ),
            topdon = listOf(
                TopdonDeviceConfig(
                    id = "topdon-tc001",
                    displayName = "Topdon TC001"
                )
            ),
            rgb = listOf(
                RgbCameraConfig(
                    id = "android-rgb",
                    displayName = "Phone RGB Camera"
                )
            )
        )

    private fun SensorHardwareConfig.sanitize(): SensorHardwareConfig {
        val shimmerSanitized =
            shimmer
                .filter { it.id.isNotBlank() }
                .ifEmpty { defaultConfig().shimmer }
        val topdonSanitized =
            topdon
                .filter { it.id.isNotBlank() }
                .ifEmpty { defaultConfig().topdon }
        val rgbSanitized =
            rgb
                .filter { it.id.isNotBlank() }
                .ifEmpty { defaultConfig().rgb }
        return SensorHardwareConfig(
            shimmer = shimmerSanitized.distinctBy { it.id },
            topdon = topdonSanitized.distinctBy { it.id },
            rgb = rgbSanitized.distinctBy { it.id }
        )
    }

    private companion object {
        private const val TAG =
            "HardwareConfigRepo"
        private const val ASSET_NAME =
            "device-inventory.json"
        private const val STORAGE_FILE_NAME =
            "device-inventory.json"
    }
}
