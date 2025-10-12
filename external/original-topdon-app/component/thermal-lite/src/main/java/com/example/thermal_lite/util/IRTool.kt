package com.example.thermal_lite.util
import android.util.Log
import com.elvishew.xlog.XLog
import com.energy.ac020library.bean.CommonParams
import com.energy.ac020library.bean.IrcmdError
import com.energy.irutilslibrary.LibIRTempAC020
import com.energy.irutilslibrary.bean.GainStatus
import com.example.thermal_lite.camera.CameraPreviewManager
import com.example.thermal_lite.camera.DeviceIrcmdControlManager
import com.topdon.lib.core.bean.CameraItemBean
import kotlinx.coroutines.delay
object IRTool {
    const val TAG: String = "IRTool"

    fun setAutoShutter(isAutoShutter: Boolean) {
        val basicAutoFFCStatusSet: IrcmdError? =
            DeviceIrcmdControlManager.getInstance().getIrcmdEngine()
                ?.basicAutoFFCStatusSet(
                    if (isAutoShutter) CommonParams.AutoFFCStatus.AUTO_FFC_ENABLE
                    else CommonParams.AutoFFCStatus.AUTO_FFC_DISABLED
                )
        Log.d(
            TAG,
            "basicAutoFFCStatusSet=$basicAutoFFCStatusSet"
        )
    }
    fun setOneShutter() {
        val basicFFCUpdate = DeviceIrcmdControlManager.getInstance().ircmdEngine?.basicFFCUpdate()
        Log.d(
            TAG,
            "basicFFCUpdate=$basicFFCUpdate"
        )
    }
    fun basicGainSet(gainType: Int) {
        if (gainType == CameraItemBean.TYPE_TMP_ZD) {
            CameraPreviewManager.getInstance().setAutoSwitchGainEnable(true)
        } else if (gainType == CameraItemBean.TYPE_TMP_C) {
            CameraPreviewManager.getInstance().setAutoSwitchGainEnable(false)
            val basicGainSet = DeviceIrcmdControlManager.getInstance().ircmdEngine
                ?.basicGainSet(CommonParams.GainStatus.HIGH_GAIN)
            Log.d(TAG, "basicGainSet=$basicGainSet--$gainType")
        } else if (gainType == CameraItemBean.TYPE_TMP_H) {
            CameraPreviewManager.getInstance().setAutoSwitchGainEnable(false)
            val basicGainSet = DeviceIrcmdControlManager.getInstance().ircmdEngine
                ?.basicGainSet(CommonParams.GainStatus.LOW_GAIN)
            Log.d(TAG, "basicGainSet=$basicGainSet--$gainType")
        }
    }
    fun basicGlobalContrastLevelSet(levelValue: Int) {
        val basicGlobalContrastLevelSetResult = DeviceIrcmdControlManager.getInstance().ircmdEngine
            ?.basicGlobalContrastLevelSet(levelValue)
        Log.d(
            TAG,
            "basicGlobalContrastLevelSet=$basicGlobalContrastLevelSetResult"
        )
    }
    fun basicImageDetailEnhanceLevelSet(levelValue: Int) {
    }
    fun basicMirrorAndFlipStatusSet(openMirror: Boolean) {
        val basicMirrorAndFlipStatusSet = DeviceIrcmdControlManager.getInstance().ircmdEngine
            ?.basicMirrorAndFlipStatusSet(
                if (openMirror) CommonParams.MirrorFlipType.ONLY_FLIP else
                    CommonParams.MirrorFlipType.NO_MIRROR_OR_FLIP
            )
        Log.d(TAG, "basicGlobalContrastLevelSet=$basicMirrorAndFlipStatusSet")
    }
    /**
     * 一次完成的锅盖标定流程
     * https://alidocs.dingtalk.com/i/p/QqWXwywDMb9xKG31/docs/14lgGw3P8vL0P2qbu7OR39d5V5daZ90D
     * Setp1：插上模组出图并确保当前模组达到热稳定状态，一般需要预热3-5分钟。
     * 预热完成后，移动模组至标定靶面前，靠近但不接触靶面。靶面的成像覆盖全部视场、 无杂散光进入为最佳)；
     * Setp2：重置锅盖标定数据，确保标定准确性
     * Setp3：关闭自动快门
     * Setp4：打快门
     * Setp5：进行自动锅盖标定
     * Setp6：恢复自动快门
     * Setp7：如果标定有误，或者需要取消自动标定结果，可调用指令
     * mIrcmdEngine.advRmcoverCaliCancel();
     * 如果观察标定没有问题，即可保存锅盖标定数据，可调用指令
     * mIrcmdEngine.basicSaveData(CommonParams.DeviceDataSaveType.BASIC_SAVE_RMCOVER_DATA);
     */
    fun onceAuto(): Boolean {
        DeviceIrcmdControlManager.getInstance().getIrcmdEngine()
            ?.basicRestoreDefaultData(CommonParams.DeviceRestoreTypeType.BASIC_RESTROE_RMCOVER_DATA)
        DeviceIrcmdControlManager.getInstance().getIrcmdEngine()
            ?.basicAutoFFCStatusSet(CommonParams.AutoFFCStatus.AUTO_FFC_DISABLED)
        DeviceIrcmdControlManager.getInstance().getIrcmdEngine()?.basicFFCUpdate()
        val result = DeviceIrcmdControlManager.getInstance().getIrcmdEngine()?.advAutoRmcoverCali()
        Log.d(TAG, "advAutoRmcoverCali=${result}")
        DeviceIrcmdControlManager.getInstance().getIrcmdEngine()
            ?.basicAutoFFCStatusSet(CommonParams.AutoFFCStatus.AUTO_FFC_ENABLE)
        val ircmdError = DeviceIrcmdControlManager.getInstance().getIrcmdEngine()
            ?.basicSaveData(CommonParams.DeviceDataSaveType.BASIC_SAVE_RMCOVER_DATA)
        return ircmdError == IrcmdError.IRCMD_SUCCESS
    }

    suspend fun autoStart(): Boolean {
        basicGainSet(CameraItemBean.TYPE_TMP_C)
        delay(2000)
        XLog.d(TAG, "onceAuto=start")
        if (!onceAuto()) {
            return false
        }
        XLog.d(TAG, "basicGainSet=start")
        basicGainSet(CameraItemBean.TYPE_TMP_H)
        delay(2000)
        XLog.d(TAG, "onceAuto=start")
        return onceAuto()
    }

    fun advEnvCorrectSwitchSet(open: Boolean) {
        DeviceIrcmdControlManager.getInstance().getIrcmdEngine()
            ?.advEnvCorrectSwitchSet(
                if (open) CommonParams.BasicEnableStatus.BASIC_ENABLE
                else CommonParams.BasicEnableStatus.BASIC_DISABLE
            )
    }
    fun advEnvCorrectEMSSet(value: Int) {
        DeviceIrcmdControlManager.getInstance().getIrcmdEngine()
            .advEnvCorrectEMSSet(value);
    }
    fun advEnvCorrectTUSet(value: Int) {
        DeviceIrcmdControlManager.getInstance().getIrcmdEngine()
            ?.advEnvCorrectTUSet(value);
    }

    /**
     * lite项目的温度修正
     * @param temp Float
     * @param params_array FloatArray
     * @param tau_data_H ByteArray 高增益修正表
     * @param tau_data_L ByteArray 低增益修正表
     * @return Float
     */
    fun temperatureCorrection(
        temp: Float,
        params_array: FloatArray,
        tau_data_H: ByteArray,
        tau_data_L: ByteArray,
        basicGainGetValue: Int
    ): Float {
        var newTemp = temp
        try {
            if (tau_data_H == null || tau_data_L == null) return temp
            newTemp = LibIRTempAC020.temperatureCorrection(
                params_array[0],
                tau_data_H,
                tau_data_L,
                params_array[1],
                params_array[2],
                params_array[3],
                params_array[4],
                params_array[5],
                if (basicGainGetValue == 0) GainStatus.LOW_GAIN else GainStatus.HIGH_GAIN
            )
        } catch (e: Exception) {
            XLog.e("$TAG:temperatureCorrection-${e.message}")
        } finally {
            return newTemp
        }
    }
    fun setMode() {
    }

}