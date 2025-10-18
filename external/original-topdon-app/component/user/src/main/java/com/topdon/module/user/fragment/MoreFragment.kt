package com.topdon.module.user.fragment

import android.os.Build
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.ToastUtils
import com.elvishew.xlog.XLog
import com.topdon.lib.core.BaseApplication
import com.topdon.lib.core.bean.event.TS004ResetEvent
import com.topdon.lib.core.common.SaveSettingUtil
import com.topdon.lib.core.common.SharedManager
import com.topdon.lib.core.common.WifiSaveSettingUtil
import com.topdon.lib.core.config.ExtraKeyConfig
import com.topdon.lib.core.config.FileConfig
import com.topdon.lib.core.config.RouterConfig
import com.topdon.lib.core.dialog.ConfirmSelectDialog
import com.topdon.lib.core.dialog.FirmwareUpDialog
import com.topdon.lib.core.ktbase.BaseFragment
import com.topdon.lib.core.dialog.TipDialog
import com.topdon.lib.core.http.tool.DownloadTool
import com.topdon.lib.core.repository.ProductBean
import com.topdon.lib.core.socket.WebSocketProxy
import com.topdon.lib.core.tools.DeviceTools
import com.topdon.lib.core.viewmodel.FirmwareViewModel
import com.topdon.module.user.R
import com.topdon.module.user.databinding.FragmentMoreBinding
import com.topdon.module.user.dialog.DownloadProDialog
import com.topdon.module.user.dialog.FirmwareInstallDialog
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.greenrobot.eventbus.EventBus
import java.io.File
import java.text.DecimalFormat

@Route(path = RouterConfig.TC_MORE)
class MoreFragment : BaseFragment(), View.OnClickListener {
    private var isTC007 = false
    private var _binding: FragmentMoreBinding? = null
    private val binding get() = _binding!!
    private val firmwareViewModel: FirmwareViewModel by viewModels()
    override fun initContentView() = R.layout.fragment_more
    override fun initView() {
        _binding = FragmentMoreBinding.bind(requireView())
        val binding = binding
        isTC007 = arguments?.getBoolean(ExtraKeyConfig.IS_TC007, false) ?: false
        binding.settingItemModel.setOnClickListener(this)
        binding.settingItemCorrection.setOnClickListener(this)
        binding.settingItemDual.setOnClickListener(this)
        binding.settingItemUnit.setOnClickListener(this)
        binding.settingVersionRoot.setOnClickListener(this)
        binding.settingDeviceInformation.setOnClickListener(this)
        binding.settingReset.setOnClickListener(this)
        binding.settingReset.isVisible = false
        binding.settingVersionRoot.isVisible = isTC007 && Build.VERSION.SDK_INT >= 29
        binding.settingDeviceInformation.isVisible = isTC007
        binding.settingItemDual.isVisible = !isTC007 && DeviceTools.isTC001PlusConnect()
        if (isTC007) {
            refresh07Connect(WebSocketProxy.getInstance().isTC007Connect())
        }
        binding.settingItemAutoShow.isChecked =
            if (isTC007) SharedManager.isConnect07AutoOpen else SharedManager.isConnectAutoOpen
        binding.settingItemAutoShow.setOnCheckedChangeListener { _, isChecked ->
            if (isTC007) {
                SharedManager.isConnect07AutoOpen = isChecked
            } else {
                SharedManager.isConnectAutoOpen = isChecked
            }
        }
        binding.settingItemConfigSelect.isChecked =
            if (isTC007) WifiSaveSettingUtil.isSaveSetting else SaveSettingUtil.isSaveSetting
        binding.settingItemConfigSelect.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                TipDialog.Builder(requireContext())
                    .setMessage(R.string.save_setting_tips)
                    .setPositiveListener(R.string.app_ok) {
                        if (isTC007) {
                            WifiSaveSettingUtil.isSaveSetting = true
                        } else {
                            SaveSettingUtil.isSaveSetting = true
                        }
                    }
                    .setCancelListener(R.string.app_cancel) {
                        binding.settingItemConfigSelect.isChecked = false
                    }
                    .setCanceled(false)
                    .create().show()
            } else {
                if (isTC007) {
                    WifiSaveSettingUtil.reset()
                    WifiSaveSettingUtil.isSaveSetting = false
                } else {
                    SaveSettingUtil.reset()
                    SaveSettingUtil.isSaveSetting = false
                }
            }
        }
        firmwareViewModel.firmwareDataLD.observe(this) {
            binding.settingVersion.tvUpgradePoint.isVisible = it != null
            dismissLoadingDialog()
            if (it == null) {
                ToastUtils.showShort(R.string.setting_firmware_update_latest_version)
            } else {
                showFirmwareUpDialog(it)
            }
        }
        firmwareViewModel.failLD.observe(this) {
            dismissLoadingDialog()
            binding.settingVersion.tvUpgradePoint.isVisible = false
        }
    }

    override fun initData() {
    }

    override fun connected() {
        _binding?.let { binding ->
            binding.settingItemDual.isVisible = !isTC007 && DeviceTools.isTC001PlusConnect()
        }
    }

    override fun disConnected() {
        _binding?.settingItemDual?.isVisible = false
    }

    override fun onSocketConnected(isTS004: Boolean) {
        if (!isTS004 && isTC007) {
            refresh07Connect(true)
        }
    }

    override fun onSocketDisConnected(isTS004: Boolean) {
        if (!isTS004 && isTC007) {
            refresh07Connect(false)
        }
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.settingItemModel -> {
                ARouter.getInstance().build(RouterConfig.IR_SETTING)
                    .withBoolean(ExtraKeyConfig.IS_TC007, isTC007)
                    .navigation(requireContext())
            }

            binding.settingItemDual -> {
                ARouter.getInstance().build(RouterConfig.MANUAL_START).navigation(requireContext())
            }

            binding.settingItemUnit -> {
                ARouter.getInstance().build(RouterConfig.UNIT).navigation(requireContext())
            }

            binding.settingItemCorrection -> {
                ARouter.getInstance().build(RouterConfig.IR_CORRECTION)
                    .withBoolean(ExtraKeyConfig.IS_TC007, isTC007)
                    .navigation(requireContext())
            }

            binding.settingVersionRoot -> {
                val firmwareData = firmwareViewModel.firmwareDataLD.value
                if (firmwareData != null) {
                    showFirmwareUpDialog(firmwareData)
                } else {
                    XLog.i("TC007 固件升级 - 点击查询")
                    showLoadingDialog()
                    firmwareViewModel.queryFirmware(false)
                }
            }

            binding.settingDeviceInformation -> {
                if (WebSocketProxy.getInstance().isTC007Connect()) {
                    ARouter.getInstance()
                        .build(RouterConfig.DEVICE_INFORMATION)
                        .withBoolean(ExtraKeyConfig.IS_TC007, true)
                        .navigation(requireContext())
                }
            }

            binding.settingReset -> {
                if (WebSocketProxy.getInstance().isTC007Connect()) {
                    restoreFactory()
                }
            }
        }
    }

    private fun refresh07Connect(isConnect: Boolean) {
        _binding?.let { binding ->
            binding.settingDeviceInformation.isRightArrowVisible = isConnect
            binding.settingDeviceInformation.setRightTextId(if (isConnect) 0 else R.string.app_no_connect)
            binding.settingReset.isRightArrowVisible = isConnect
            binding.settingReset.setRightTextId(if (isConnect) 0 else R.string.app_no_connect)
            binding.settingVersion.tvRightText.isVisible = isConnect
            if (isConnect) {
                lifecycleScope.launch {
                    if (productBean == null) {
                    } else {
                        binding.settingVersion.itemSettingBottomText.text =
                            getString(R.string.setting_firmware_update_version) + "V" + productBean.getVersionStr()
                    }
                }
            } else {
                binding.settingVersion.itemSettingBottomText.setText(R.string.setting_firmware_update_version)
            }
        }
    }

    private fun showFirmwareUpDialog(firmwareData: FirmwareViewModel.FirmwareData) {
        val dialog = FirmwareUpDialog(requireContext())
        dialog.titleStr = "${getString(R.string.update_new_version)} ${firmwareData.version}"
        dialog.sizeStr = "${getString(R.string.detail_len)}: ${getFileSizeStr(firmwareData.size)}"
        dialog.contentStr = firmwareData.updateStr
        dialog.isShowRestartTips = true
        dialog.onConfirmClickListener = {
            installFirmware(FileConfig.getFirmwareFile(firmwareData.downUrl))
        }
        dialog.show()
    }

    private fun getFileSizeStr(size: Long): String = if (size < 1024) {
        "${size}B"
    } else if (size < 1024 * 1024) {
        DecimalFormat("#.0").format(size.toDouble() / 1024) + "KB"
    } else if (size < 1024 * 1024 * 1024) {
        DecimalFormat("#.0").format(size.toDouble() / 1024 / 1024) + "MB"
    } else {
        DecimalFormat("#.0").format(size.toDouble() / 1024 / 1024 / 1024) + "GB"
    }

    private fun downloadFirmware(firmwareData: FirmwareViewModel.FirmwareData) {
        lifecycleScope.launch {
            val progressDialog = DownloadProDialog(requireContext())
            progressDialog.show()
            val file = File(
                requireContext().getExternalFilesDir("firmware"),
                "TC007${firmwareData.version}.zip"
            )
            val isSuccess = DownloadTool.download(firmwareData.downUrl, file) { current, total ->
                progressDialog.refreshProgress(current, total)
            }
            progressDialog.dismiss()
            if (isSuccess) {
                installFirmware(file)
            } else {
                showReDownloadDialog(firmwareData)
            }
        }
    }

    private fun installFirmware(file: File) {
        lifecycleScope.launch {
            XLog.d("TC007 固件升级 - 开始安装固件升级包")
            val installDialog = FirmwareInstallDialog(requireContext())
            installDialog.show()
            installDialog.dismiss()
            if (isSuccess) {
                XLog.d("TC007 固件升级 - 固件升级包发送往 TC007 成功，即将断开连接")
                (requireActivity().application as BaseApplication).disconnectWebSocket()
                TipDialog.Builder(requireContext())
                    .setTitleMessage(getString(R.string.app_tip))
                    .setMessage(R.string.firmware_up_success)
                    .setPositiveListener(R.string.app_confirm) {
                        ARouter.getInstance().build(RouterConfig.MAIN).navigation(requireContext())
                        requireActivity().finish()
                    }
                    .setCancelListener(R.string.app_cancel) {
                    }
                    .create().show()
            } else {
                XLog.w("TC007 固件升级 - 固件升级包发送往 TC007 失败!")
                showReInstallDialog(file)
            }
        }
    }

    private fun showReInstallDialog(file: File) {
        val dialog = ConfirmSelectDialog(requireContext())
        dialog.setShowIcon(true)
        dialog.setTitleRes(R.string.ts004_install_tips)
        dialog.setCancelText(R.string.ts004_install_cancel)
        dialog.setConfirmText(R.string.ts004_install_continue)
        dialog.onConfirmClickListener = {
            installFirmware(file)
        }
        dialog.show()
    }

    private fun showReDownloadDialog(firmwareData: FirmwareViewModel.FirmwareData) {
        val dialog = ConfirmSelectDialog(requireContext())
        dialog.setShowIcon(true)
        dialog.setTitleRes(R.string.ts004_download_tips)
        dialog.setCancelText(R.string.ts004_download_cancel)
        dialog.setConfirmText(R.string.ts004_download_continue)
        dialog.onConfirmClickListener = {
            downloadFirmware(firmwareData)
        }
        dialog.show()
    }

    private fun restoreFactory() {
        TipDialog.Builder(requireContext())
            .setTitleMessage(getString(R.string.ts004_reset_tip1, "TC007"))
            .setMessage(getString(R.string.ts004_reset_tip2))
            .setPositiveListener(R.string.app_ok) {
                resetAll()
            }
            .setCancelListener(R.string.app_cancel) {
            }
            .setCanceled(true)
            .create().show()
    }

    private fun resetAll() {
        showLoadingDialog(R.string.ts004_reset_tip3)
        lifecycleScope.launch {
            if (isSuccess) {
                XLog.d("TC007 恢复出厂设置成功，即将断开连接")
                (requireActivity().application as BaseApplication).disconnectWebSocket()
                EventBus.getDefault().post(TS004ResetEvent())
                ARouter.getInstance().build(RouterConfig.MAIN).navigation(requireContext())
                requireActivity().finish()
            } else {
            }
            delay(500)
            dismissLoadingDialog()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
