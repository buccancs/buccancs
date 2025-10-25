package com.buccancs.data.sensor.topdon.gallery

import android.content.Context
import com.buccancs.domain.model.ThermalImage
import com.buccancs.domain.model.ThermalMediaItem
import com.buccancs.domain.model.ThermalVideo
import com.buccancs.domain.model.TopdonPalette
import com.buccancs.domain.model.TopdonSuperSamplingFactor
import com.buccancs.domain.repository.TopdonGalleryRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import org.json.JSONObject
import java.io.File
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultTopdonGalleryRepository @Inject constructor(
    @ApplicationContext private val context: Context
) : TopdonGalleryRepository {

    private val images =
        MutableStateFlow<List<ThermalImage>>(
            emptyList()
        )
    private val videos =
        MutableStateFlow<List<ThermalVideo>>(
            emptyList()
        )

    private val storageDir: File by lazy {
        File(
            context.getExternalFilesDir(
                null
            ),
            "BuccanCS/Thermal"
        ).apply {
            mkdirs()
        }
    }

    init {
        loadMediaFromStorage()
    }

    override fun getAllMedia(): Flow<List<ThermalMediaItem>> {
        return images.map { imageList ->
            val videoList =
                videos.value
            val allMedia =
                mutableListOf<ThermalMediaItem>()
            allMedia.addAll(
                imageList.map {
                    ThermalMediaItem.Image(
                        it
                    )
                })
            allMedia.addAll(
                videoList.map {
                    ThermalMediaItem.Video(
                        it
                    )
                })
            allMedia.sortedByDescending { it.timestamp }
        }
    }

    override fun getImages(): Flow<List<ThermalImage>> =
        images

    override fun getVideos(): Flow<List<ThermalVideo>> =
        videos

    override suspend fun getImageById(
        id: String
    ): ThermalImage? {
        return images.value.find { it.id == id }
    }

    override suspend fun getVideoById(
        id: String
    ): ThermalVideo? {
        return videos.value.find { it.id == id }
    }

    override suspend fun deleteImage(
        id: String
    ): Result<Unit> {
        return try {
            val image =
                images.value.find { it.id == id }
            if (image != null) {
                File(
                    image.filePath
                ).delete()
                image.thumbnailPath?.let {
                    File(
                        it
                    ).delete()
                }
                images.value =
                    images.value.filter { it.id != id }
                Result.success(
                    Unit
                )
            } else {
                Result.failure(
                    Exception(
                        "Image not found"
                    )
                )
            }
        } catch (e: Exception) {
            Result.failure(
                e
            )
        }
    }

    override suspend fun deleteVideo(
        id: String
    ): Result<Unit> {
        return try {
            val video =
                videos.value.find { it.id == id }
            if (video != null) {
                File(
                    video.filePath
                ).delete()
                video.thumbnailPath?.let {
                    File(
                        it
                    ).delete()
                }
                videos.value =
                    videos.value.filter { it.id != id }
                Result.success(
                    Unit
                )
            } else {
                Result.failure(
                    Exception(
                        "Video not found"
                    )
                )
            }
        } catch (e: Exception) {
            Result.failure(
                e
            )
        }
    }

    override suspend fun deleteMultiple(
        ids: List<String>
    ): Result<Unit> {
        return try {
            ids.forEach { id ->
                deleteImage(
                    id
                )
                deleteVideo(
                    id
                )
            }
            Result.success(
                Unit
            )
        } catch (e: Exception) {
            Result.failure(
                e
            )
        }
    }

    override suspend fun shareImages(
        ids: List<String>
    ): Result<Unit> {
        return Result.success(
            Unit
        )
    }

    override suspend fun exportImages(
        ids: List<String>,
        destinationPath: String
    ): Result<Unit> {
        return try {
            val destDir =
                File(
                    destinationPath
                ).apply { mkdirs() }
            ids.forEach { id ->
                val image =
                    getImageById(
                        id
                    )
                if (image != null) {
                    val srcFile =
                        File(
                            image.filePath
                        )
                    val destFile =
                        File(
                            destDir,
                            srcFile.name
                        )
                    srcFile.copyTo(
                        destFile,
                        overwrite = true
                    )
                }
            }
            Result.success(
                Unit
            )
        } catch (e: Exception) {
            Result.failure(
                e
            )
        }
    }

    private fun loadMediaFromStorage() {
        val discoveredImages =
            storageDir.walkTopDown()
                .filter { file ->
                    file.isFile && file.extension.lowercase() in IMAGE_EXTENSIONS
                }
                .mapNotNull { file ->
                    toThermalImage(file)
                }
                .sortedByDescending { it.timestamp }
                .toList()

        val discoveredVideos =
            storageDir.walkTopDown()
                .filter { file ->
                    file.isFile && file.extension.lowercase() in VIDEO_EXTENSIONS
                }
                .mapNotNull { file ->
                    toThermalVideo(file)
                }
                .sortedByDescending { it.timestamp }
                .toList()

        images.value =
            discoveredImages
        videos.value =
            discoveredVideos
    }

    private fun toThermalImage(file: File): ThermalImage? {
        val metadataFile =
            File(
                file.parentFile,
                "${file.name}.json"
            )
        val metadata =
            runCatching { JSONObject(metadataFile.readText()) }.getOrNull()
        val timestamp =
            metadata?.optLong("timestamp")?.takeIf { it > 0 }
                ?.let { Instant.ofEpochMilli(it) }
                ?: Instant.ofEpochMilli(file.lastModified())
        val palette =
            metadata?.optString("palette")?.let { value ->
                runCatching { TopdonPalette.valueOf(value) }.getOrNull()
            }
                ?: TopdonPalette.IRONBOW
        val superSampling =
            metadata?.optInt("superSampling")?.let { multiplier ->
                TopdonSuperSamplingFactor.fromMultiplier(multiplier)
            }
                ?: TopdonSuperSamplingFactor.X1
        val minTemp = metadata?.optDouble("minTemp")?.toFloat() ?: 0f
        val maxTemp = metadata?.optDouble("maxTemp")?.toFloat() ?: 0f
        val avgTemp = metadata?.optDouble("avgTemp")?.toFloat() ?: 0f
        val width = metadata?.optInt("width") ?: 256
        val height = metadata?.optInt("height") ?: 192

        return ThermalImage(
            id = file.nameWithoutExtension,
            filePath = file.absolutePath,
            timestamp = LocalDateTime.ofInstant(
                timestamp,
                ZoneId.systemDefault()
            ),
            width = width,
            height = height,
            palette = palette,
            superSampling = superSampling,
            minTemperature = minTemp,
            maxTemperature = maxTemp,
            avgTemperature = avgTemp,
            thumbnailPath = null,
            fileSize = file.length()
        )
    }

    private fun toThermalVideo(file: File): ThermalVideo? {
        val metadataFile =
            File(
                file.parentFile,
                "${file.name}.json"
            )
        val metadata =
            runCatching { JSONObject(metadataFile.readText()) }.getOrNull()
        val startTime =
            metadata?.optLong("startTime")?.takeIf { it > 0 }
                ?: file.lastModified()
        val timestamp =
            LocalDateTime.ofInstant(
                Instant.ofEpochMilli(startTime),
                ZoneId.systemDefault()
            )
        val duration = metadata?.optLong("duration") ?: 0L
        val frameCount = metadata?.optInt("frameCount") ?: 0

        return ThermalVideo(
            id = file.nameWithoutExtension,
            filePath = file.absolutePath,
            timestamp = timestamp,
            duration = duration,
            frameCount = frameCount,
            thumbnailPath = null,
            fileSize = file.length()
        )
    }

    companion object {
        private val IMAGE_EXTENSIONS =
            setOf("jpg", "jpeg", "png")
        private val VIDEO_EXTENSIONS =
            setOf("raw")
    }
}
