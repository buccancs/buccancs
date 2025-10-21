package com.buccancs.hardware.topdon
import com.infisense.iruvc.ircmd.IRCMD
import com.infisense.iruvc.ircmd.ResultCode
import com.infisense.iruvc.utils.CommonParams
import java.nio.ByteBuffer
import java.nio.ByteOrder
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class CommandLayer(
    private val delegate: IRCMD,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main
) {
    suspend fun setPalette(
        paletteIndex: Int
    ): Result<ResultCode> =
        executeCommand {
            val pseudoColor =
                pseudoColorForIndex(
                    paletteIndex
                )
            delegate.setPseudoColor(
                CommonParams.PreviewPathChannel.PREVIEW_PATH0,
                pseudoColor
            )
        }

    suspend fun setEmissivity(
        value: Float
    ): Result<ResultCode> =
        executeCommand {
            val payload =
                ByteBuffer.allocate(4)
                    .order(
                        ByteOrder.LITTLE_ENDIAN
                    )
                    .putFloat(
                        value.coerceIn(
                            0f,
                            1f
                        )
                    )
                    .array()
            delegate.setTPDEMS(
                payload
            )
        }

    suspend fun setDistance(
        value: Float
    ): Result<ResultCode> =
        executeCommand {
            val scaled =
                (value.coerceAtLeast(0f) * 100)
                    .toInt()
                    .coerceIn(
                        0,
                        10_000
                    )
            val buffer =
                charArrayOf(
                    (scaled and 0xFF).toChar(),
                    ((scaled shr 8) and 0xFF).toChar()
                )
            delegate.setTPDDistance(
                buffer
            )
        }

    suspend fun setShutterMode(
        enabled: Boolean
    ): Result<ResultCode> =
        executeCommand {
            val status =
                if (enabled) {
                    CommonParams.PropAutoShutterParameterValue.StatusSwith.ON
                } else {
                    CommonParams.PropAutoShutterParameterValue.StatusSwith.OFF
                }
            delegate.setPropAutoShutterParameter(
                CommonParams.PropAutoShutterParameter.SHUTTER_PROP_SWITCH,
                status
            )
        }

    suspend fun setPropImageParams(
        param: CommonParams.PropImageParams,
        value: CommonParams.PropImageParamsValue
    ): Result<ResultCode> =
        executeCommand {
            delegate.setPropImageParams(
                param,
                value
            )
        }

    suspend fun triggerManualCalibration(): Result<Unit> =
        runCatching {
            withContext(
                dispatcher
            ) {
                val primary =
                    delegate.tiny1bShutterManual()
                if (primary != SUCCESS_CODE) {
                    delegate.updateOOCOrB(
                        CommonParams.UpdateOOCOrBType.B_UPDATE
                    )
                }
            }
        }

    fun shutdown() {
        // Some IRCMD implementations expose explicit teardown APIs,
        // but the vendor library releases native resources automatically
        // when references are cleared. No-op for now.
    }

    private suspend fun executeCommand(
        block: () -> Int
    ): Result<ResultCode> =
        runCatching {
            withContext(
                dispatcher
            ) {
                mapResult(
                    block()
                )
            }
        }

    private fun pseudoColorForIndex(
        index: Int
    ): CommonParams.PseudoColorType =
        when (index) {
            0 -> CommonParams.PseudoColorType.PSEUDO_3 // Ironbow
            1 -> CommonParams.PseudoColorType.PSEUDO_1 // Grayscale
            2 -> CommonParams.PseudoColorType.PSEUDO_4 // Rainbow
            3 -> CommonParams.PseudoColorType.PSEUDO_5 // Arctic / Aurora
            else -> CommonParams.PseudoColorType.PSEUDO_3
        }

    private fun mapResult(
        code: Int
    ): ResultCode =
        if (code == SUCCESS_CODE) {
            ResultCode.SUCCESS
        } else {
            ResultCode.UNKNOWN_ERROR
        }

    private companion object {
        private const val SUCCESS_CODE =
            0
    }
}
