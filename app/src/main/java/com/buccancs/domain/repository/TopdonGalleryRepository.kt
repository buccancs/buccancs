package com.buccancs.domain.repository

import com.buccancs.domain.model.ThermalImage
import com.buccancs.domain.model.ThermalMediaItem
import com.buccancs.domain.model.ThermalVideo
import kotlinx.coroutines.flow.Flow

interface TopdonGalleryRepository {
    fun getAllMedia(): Flow<List<ThermalMediaItem>>

    fun getImages(): Flow<List<ThermalImage>>

    fun getVideos(): Flow<List<ThermalVideo>>

    suspend fun getImageById(
        id: String
    ): ThermalImage?

    suspend fun getVideoById(
        id: String
    ): ThermalVideo?

    suspend fun deleteImage(
        id: String
    ): Result<Unit>

    suspend fun deleteVideo(
        id: String
    ): Result<Unit>

    suspend fun deleteMultiple(
        ids: List<String>
    ): Result<Unit>

    suspend fun shareImages(
        ids: List<String>
    ): Result<Unit>

    suspend fun exportImages(
        ids: List<String>,
        destinationPath: String
    ): Result<Unit>
}
