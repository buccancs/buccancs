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
import java.io.File
import java.time.LocalDateTime
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
        val imageFiles =
            storageDir.listFiles { file ->
                file.extension in listOf(
                    "png",
                    "jpg",
                    "jpeg"
                )
            }
                ?.toList()
                ?: emptyList()

        images.value =
            imageFiles.map { file ->
                ThermalImage(
                    id = file.nameWithoutExtension,
                    filePath = file.absolutePath,
                    timestamp = LocalDateTime.now(),
                    width = 256,
                    height = 192,
                    palette = TopdonPalette.IRONBOW,
                    superSampling = TopdonSuperSamplingFactor.X1,
                    minTemperature = 20f,
                    maxTemperature = 35f,
                    avgTemperature = 27.5f,
                    thumbnailPath = null,
                    fileSize = file.length()
                )
            }
    }
}
