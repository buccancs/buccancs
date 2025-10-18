package com.infisense.usbir.camera;

import android.content.Context;
import android.hardware.usb.UsbDevice;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;

import com.infisense.iruvc.ircmd.ConcreteIRCMDBuilder;
import com.infisense.iruvc.ircmd.IRCMD;
import com.infisense.iruvc.ircmd.IRCMDType;
import com.infisense.iruvc.ircmd.ResultCode;
import com.infisense.iruvc.sdkisp.LibIRProcess;
import com.infisense.iruvc.usb.USBMonitor;
import com.infisense.iruvc.utils.AutoGainSwitchCallback;
import com.infisense.iruvc.utils.AvoidOverexposureCallback;
import com.infisense.iruvc.utils.CommonParams;
import com.infisense.iruvc.utils.DeviceType;
import com.infisense.iruvc.utils.IFrameCallback;
import com.infisense.iruvc.utils.OnCreateResultCallback;
import com.infisense.iruvc.utils.SynchronizedBitmap;
import com.infisense.iruvc.uvc.CameraSize;
import com.infisense.iruvc.uvc.ConcreateUVCBuilder;
import com.infisense.iruvc.uvc.ConnectCallback;
import com.infisense.iruvc.uvc.UVCCamera;
import com.infisense.iruvc.uvc.UVCType;
import com.infisense.usbir.MyApplication;
import com.infisense.usbir.activity.IRDisplayActivity;
import com.infisense.usbir.utils.CMDDataCallback;
import com.infisense.usbir.utils.FileUtil;
import com.infisense.usbir.utils.ScreenUtils;
import com.infisense.usbir.utils.SharedPreferencesUtil;
import com.infisense.usbir.utils.USBMonitorCallback;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class IRUVC {
    private static final String TAG = "IRUVC_DATA";
    private final int TinyB = 0x3901;
    private IFrameCallback iFrameCallback;
    private Context mContext;
    private UVCCamera uvcCamera;
    private IRCMD ircmd;
    private int[] curVtemp = new int[1];
    private USBMonitor mUSBMonitor;
    private ConnectCallback mConnectCallback;
    private byte[] imageSrc;
    private byte[] temperatureSrc;
    private int imageOrTempDataLength = 256 * 192 * 2;
    private SynchronizedBitmap syncimage;
    private LibIRProcess.AutoGainSwitchInfo_t auto_gain_switch_info = new LibIRProcess.AutoGainSwitchInfo_t();
    private LibIRProcess.GainSwitchParam_t gain_switch_param = new LibIRProcess.GainSwitchParam_t();
    private int count = 0;
    private long timeStart = 0;
    private Handler mHandler;
    private boolean rotate = false;
    private boolean isUseIRISP;
    private boolean isUseGPU;
    private boolean isFrameReady = true;
    private CommonParams.GainStatus gainStatus = CommonParams.GainStatus.HIGH_GAIN;
    private CommonParams.GainMode gainMode = CommonParams.GainMode.GAIN_MODE_HIGH_LOW;
    private short[] nuc_table_high = new short[8192];
    private short[] nuc_table_low = new short[8192];
    private boolean isReadNuc = true;
    private byte[] priv_high = new byte[1201];
    private byte[] priv_low = new byte[1201];
    private short[] kt_high = new short[1201];
    private short[] kt_low = new short[1201];
    private short[] bt_high = new short[1201];
    private short[] bt_low = new short[1201];
    private byte[] temperatureTemp = new byte[imageOrTempDataLength];
    private CMDDataCallback cmdDataCallback;
    private boolean isTempReplacedWithTNREnabled;
    private USBMonitorCallback usbMonitorCallback;
    private CommonParams.DataFlowMode defaultDataFlowMode;
    private boolean isRestart;

    public IRUVC(int cameraWidth, int cameraHeight, Context context, SynchronizedBitmap syncimage,
                 CommonParams.DataFlowMode dataFlowMode, boolean isUseIRISP, boolean isUseGPU,
                 ConnectCallback connectCallback, USBMonitorCallback usbMonitorCallback) {
        this.mContext = context;
        this.syncimage = syncimage;
        this.isUseIRISP = isUseIRISP;
        this.isUseGPU = isUseGPU;
        this.mConnectCallback = connectCallback;
        this.defaultDataFlowMode = dataFlowMode;
        LibIRProcess.ImageRes_t imageRes = new LibIRProcess.ImageRes_t();
        imageRes.height = (char) (dataFlowMode == CommonParams.DataFlowMode.IMAGE_AND_TEMP_OUTPUT ? cameraHeight / 2
                : cameraHeight);
        imageRes.width = (char) cameraWidth;
        initUVCCamera();
        mUSBMonitor = new USBMonitor(context, new USBMonitor.OnDeviceConnectListener() {

            @Override
            public void onAttach(UsbDevice device) {
                Log.w(TAG, "onAttach");
                if (uvcCamera == null || !uvcCamera.getOpenStatus()) {
                    mUSBMonitor.requestPermission(device);
                }
                if (usbMonitorCallback != null) {
                    usbMonitorCallback.onAttach();
                }
            }

            @Override
            public void onGranted(UsbDevice usbDevice, boolean granted) {
                Log.w(TAG, "onGranted");
                if (usbMonitorCallback != null) {
                    usbMonitorCallback.onGranted();
                }
            }

            @Override
            public void onConnect(final UsbDevice device, USBMonitor.UsbControlBlock ctrlBlock, boolean createNew) {
                Log.w(TAG, "onConnect");
                if (createNew) {
                    openUVCCamera(ctrlBlock);
                    List<CameraSize> previewList = getAllSupportedSize();
                    initIRCMD(previewList);
                    if (ircmd != null) {
                        isTempReplacedWithTNREnabled = ircmd.isTempReplacedWithTNREnabled(DeviceType.P2);
                        Log.i(TAG, "onConnect->isTempReplacedWithTNREnabled = " + isTempReplacedWithTNREnabled);
                        Log.i(TAG, "onConnect->isUseIRISP = " + isUseIRISP);
                        if (isUseIRISP && isTempReplacedWithTNREnabled) {
                            setPreviewSize(cameraWidth, cameraHeight * 2);
                        } else {
                            setPreviewSize(cameraWidth, cameraHeight);
                        }
                        startPreview();
                        if (usbMonitorCallback != null) {
                            usbMonitorCallback.onConnect();
                        }
                    }
                }
            }

            @Override
            public void onDisconnect(UsbDevice device, USBMonitor.UsbControlBlock ctrlBlock) {
                Log.w(TAG, "onDisconnect");
                if (usbMonitorCallback != null) {
                    usbMonitorCallback.onDisconnect();
                }
            }

            @Override
            public void onDettach(UsbDevice device) {
                Log.w(TAG, "onDettach");
                if (uvcCamera != null && uvcCamera.getOpenStatus()) {
                    if (usbMonitorCallback != null) {
                        usbMonitorCallback.onDettach();
                    }
                }
            }

            @Override
            public void onCancel(UsbDevice device) {
                Log.w(TAG, "onCancel");
                if (usbMonitorCallback != null) {
                    usbMonitorCallback.onCancel();
                }
            }
        });
        gain_switch_param.above_pixel_prop = 0.1f;
        gain_switch_param.above_temp_data = (int) ((130 + 273.15) * 16 * 4);
        gain_switch_param.below_pixel_prop = 0.95f;
        gain_switch_param.below_temp_data = (int) ((110 + 273.15) * 16 * 4);
        auto_gain_switch_info.switch_frame_cnt = 5 * 15;
        auto_gain_switch_info.waiting_frame_cnt = 7 * 15;
        int low_gain_over_temp_data = (int) ((550 + 273.15) * 16 * 4);
        int high_gain_over_temp_data = (int) ((150 + 273.15) * 16 * 4);
        float pixel_above_prop = 0.02f;
        int switch_frame_cnt = 7 * 15;
        int close_frame_cnt = 10 * 15;
        iFrameCallback = new IFrameCallback() {
            @Override
            public void onFrame(byte[] frame) {
                if (!isFrameReady) {
                    return;
                }
                count++;
                if (count == 100) {
                    count = 0;
                    long currentTimeMillis = System.currentTimeMillis();
                    if (timeStart != 0) {
                        long timeuse = currentTimeMillis - timeStart;
                        MyApplication.getInstance().FPS = 100 * 1000 / (timeuse + 0.0);
                    }
                    timeStart = currentTimeMillis;
                    Log.d(TAG, "frame.length = " + frame.length +
                            " fps=" + String.format(Locale.US, "%.1f", MyApplication.getInstance().FPS) +
                            " dataFlowMode = " + dataFlowMode + " rotate = " + rotate + " isUseIRISP = " + isUseIRISP);
                }
                if (syncimage == null) {
                    return;
                }
                syncimage.start = true;
                synchronized (syncimage.dataLock) {
                    int length = frame.length - 1;
                    if (frame[length] == 1) {
                        if (mHandler != null) {
                            mHandler.sendEmptyMessage(IRDisplayActivity.RESTART_USB);
                            Log.d(TAG, "RESTART_USB");
                        }
                        return;
                    }

                    if (dataFlowMode == CommonParams.DataFlowMode.IMAGE_AND_TEMP_OUTPUT || isUseIRISP) {
                        System.arraycopy(frame, 0, imageSrc, 0, imageOrTempDataLength);
                        if (length >= imageOrTempDataLength * 2) {
                            if (rotate) {
                                System.arraycopy(frame, imageOrTempDataLength, temperatureTemp, 0,
                                        imageOrTempDataLength);
                                LibIRProcess.rotateRight90(temperatureTemp, imageRes,
                                        CommonParams.IRPROCSRCFMTType.IRPROC_SRC_FMT_Y14, temperatureSrc);
                            } else {
                                System.arraycopy(frame, imageOrTempDataLength, temperatureSrc, 0,
                                        imageOrTempDataLength);
                            }
                            if (ircmd != null) {
                                if ((boolean) SharedPreferencesUtil.getData(mContext,
                                        MyApplication.getInstance().SWITCH_AUTO_GAIN_KEY, false)) {
                                    ircmd.autoGainSwitch(temperatureSrc, imageRes, auto_gain_switch_info,
                                            gain_switch_param, new AutoGainSwitchCallback() {
                                                @Override
                                                public void onAutoGainSwitchState(CommonParams.PropTPDParamsValue.GAINSELStatus gainselStatus) {
                                                    Log.i(TAG, "onAutoGainSwitchState->" + gainselStatus.getValue());
                                                }

                                                @Override
                                                public void onAutoGainSwitchResult(CommonParams.PropTPDParamsValue.GAINSELStatus gainselStatus, int result) {
                                                    Log.i(TAG,
                                                            "onAutoGainSwitchResult->" + gainselStatus.getValue() +
                                                                    " result=" + result);
                                                }
                                            });
                                }
                                if ((boolean) SharedPreferencesUtil.getData(mContext,
                                        MyApplication.getInstance().SWITCH_OVER_PROTECT, false)) {
                                    ircmd.avoidOverexposure(isUseIRISP, gainStatus, temperatureSrc, imageRes,
                                            low_gain_over_temp_data,
                                            high_gain_over_temp_data, pixel_above_prop, switch_frame_cnt,
                                            close_frame_cnt,
                                            new AvoidOverexposureCallback() {
                                                @Override
                                                public void onAvoidOverexposureState(boolean avoidOverexpol) {
                                                    Log.i(TAG,
                                                            "onAvoidOverexposureState->avoidOverexpol=" + avoidOverexpol);
                                                }
                                            });
                                }
                            }
                        }
                    } else {
                        System.arraycopy(frame, 0, imageSrc, 0, imageOrTempDataLength);
                    }
                }
            }
        };
    }

    public void setHandler(Handler mHandler) {
        this.mHandler = mHandler;
    }

    public void setRotate(boolean rotate) {
        this.rotate = rotate;
    }

    public void setImageSrc(byte[] image) {
        this.imageSrc = image;
    }

    public void setTemperatureSrc(byte[] temperatureSrc) {
        this.temperatureSrc = temperatureSrc;
    }

    public void setFrameReady(boolean frameReady) {
        isFrameReady = frameReady;
    }

    public void setCMDDataCallback(CMDDataCallback cmdDataCallback) {
        this.cmdDataCallback = cmdDataCallback;
    }

    public boolean isRestart() {
        return isRestart;
    }

    public void setRestart(boolean restart) {
        isRestart = restart;
    }

    public void initUVCCamera() {
        Log.w(TAG, "init");
        ConcreateUVCBuilder concreateUVCBuilder = new ConcreateUVCBuilder();
        uvcCamera = concreateUVCBuilder
                .setUVCType(UVCType.USB_UVC)
                .build();
        uvcCamera.setDefaultBandwidth(1F);
    }

    public void initIRCMD(List<CameraSize> previewList) {
        for (CameraSize size : previewList) {
            Log.i(TAG, "SupportedSize : " + size.width + " * " + size.height);
        }
        if (uvcCamera != null) {

            ConcreteIRCMDBuilder concreteIRCMDBuilder = new ConcreteIRCMDBuilder();
            ircmd = concreteIRCMDBuilder
                    .setIrcmdType(IRCMDType.USB_IR_256_384)
                    .setIdCamera(uvcCamera.getNativePtr())
                    .setCreateResultCallback(new OnCreateResultCallback() {
                        @Override
                        public void onInitResult(ResultCode resultCode) {
                            Log.d(TAG, "onInitResult : " + resultCode.toString());
                            if (resultCode != ResultCode.SUCCESS) {
                                Message message = Message.obtain();
                                message.what = IRDisplayActivity.IRCMD_INIT_FAIL;
                                message.obj = resultCode;
                                mHandler.sendMessage(message);
                            }
                        }
                    })
                    .build();
            if (ircmd == null) {
                mHandler.sendEmptyMessage(IRDisplayActivity.DEAL_Y16_MODE_PREVIEW_COMPLETE);
                return;
            }
            if (mConnectCallback != null) {
                mConnectCallback.onIRCMDCreate(ircmd);
            }
        }
    }

    public void registerUSB() {
        if (mUSBMonitor != null) {
            mUSBMonitor.register();
        }
    }

    public void unregisterUSB() {
        if (mUSBMonitor != null) {
            mUSBMonitor.unregister();
        }
    }

    public void openUVCCamera(USBMonitor.UsbControlBlock ctrlBlock) {
        Log.i(TAG, "openUVCCamera");
        if (ctrlBlock.getProductId() == TinyB) {
            if (syncimage != null) {
                syncimage.type = 1;
            }
        }
        if (uvcCamera == null) {
            initUVCCamera();
        }
        if (uvcCamera.openUVCCamera(ctrlBlock) == 0) {
            if (mConnectCallback != null && uvcCamera != null) {
                mConnectCallback.onCameraOpened(uvcCamera);
            }
        }
    }

    private List<CameraSize> getAllSupportedSize() {
        Log.w(TAG, "getSupportedSize = " + uvcCamera.getSupportedSize());
        List<CameraSize> previewList = new ArrayList<>();
        if (uvcCamera != null) {
            previewList = uvcCamera.getSupportedSizeList();
        }
        for (CameraSize size : previewList) {
            Log.i(TAG, "SupportedSize : " + size.width + " * " + size.height);
        }
        return previewList;
    }

    private void setPreviewSize(int cameraWidth, int cameraHeight) {
        if (uvcCamera != null) {
            uvcCamera.setUSBPreviewSize(cameraWidth, cameraHeight);
        }
    }

    public void startPreview() {
        if (ircmd == null) {
            return;
        }
        Log.i(TAG, "startPreview isRestart : " + isRestart + " defaultDataFlowMode : " + defaultDataFlowMode);
        uvcCamera.setOpenStatus(true);
        uvcCamera.setFrameCallback(iFrameCallback);
        uvcCamera.onStartPreview();

        if (CommonParams.DataFlowMode.IMAGE_AND_TEMP_OUTPUT == defaultDataFlowMode ||
                CommonParams.DataFlowMode.IMAGE_OUTPUT == defaultDataFlowMode) {
            Log.i(TAG, "defaultDataFlowMode = IMAGE_AND_TEMP_OUTPUT or IMAGE_OUTPUT");
            setFrameReady(false);
            if (isRestart) {
                if (ircmd.stopPreview(CommonParams.PreviewPathChannel.PREVIEW_PATH0) == 0) {
                    Log.i(TAG, "stopPreview complete");
                    if (ircmd.startPreview(CommonParams.PreviewPathChannel.PREVIEW_PATH0,
                            CommonParams.StartPreviewSource.SOURCE_SENSOR,
                            ScreenUtils.getPreviewFPSByDataFlowMode(defaultDataFlowMode),
                            CommonParams.StartPreviewMode.VOC_DVP_MODE, defaultDataFlowMode) == 0) {
                        Log.i(TAG, "startPreview complete");
                        handleStartPreviewComplete();
                    }
                } else {
                    Log.e(TAG, "stopPreview error");
                }
            } else {
                handleStartPreviewComplete();
            }
        } else {
            setFrameReady(false);
            if (isRestart) {
                if (ircmd.stopPreview(CommonParams.PreviewPathChannel.PREVIEW_PATH0) == 0) {
                    Log.i(TAG, "stopPreview complete 中间出图 restart");
                    if (ircmd.startPreview(CommonParams.PreviewPathChannel.PREVIEW_PATH0,
                            CommonParams.StartPreviewSource.SOURCE_SENSOR,
                            ScreenUtils.getPreviewFPSByDataFlowMode(defaultDataFlowMode),
                            CommonParams.StartPreviewMode.VOC_DVP_MODE, defaultDataFlowMode) == 0) {
                        Log.i(TAG, "startPreview complete 中间出图 restart");
                        try {
                            Thread.sleep(1500);
                        } catch (
                                InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (ircmd.startY16ModePreview(CommonParams.PreviewPathChannel.PREVIEW_PATH0,
                                FileUtil.getY16SrcTypeByDataFlowMode(defaultDataFlowMode)) == 0) {
                            handleStartPreviewComplete();
                        } else {
                            Log.e(TAG, "startY16ModePreview error 中间出图 restart");
                        }
                    } else {
                        Log.e(TAG, "startPreview error 中间出图 restart");
                    }
                } else {
                    Log.e(TAG, "stopPreview error 中间出图 restart");
                }
            } else {
                boolean isTempReplacedWithTNREnabled = ircmd.isTempReplacedWithTNREnabled(DeviceType.P2);
                Log.i(TAG,
                        "defaultDataFlowMode = others isTempReplacedWithTNREnabled = " + isTempReplacedWithTNREnabled);
                if (isTempReplacedWithTNREnabled) {
                    if (ircmd.stopPreview(CommonParams.PreviewPathChannel.PREVIEW_PATH0) == 0) {
                        Log.i(TAG, "stopPreview complete 红外+TNR");
                        if (ircmd.startPreview(CommonParams.PreviewPathChannel.PREVIEW_PATH0,
                                CommonParams.StartPreviewSource.SOURCE_SENSOR,
                                ScreenUtils.getPreviewFPSByDataFlowMode(CommonParams.DataFlowMode.IMAGE_AND_TEMP_OUTPUT),
                                CommonParams.StartPreviewMode.VOC_DVP_MODE,
                                CommonParams.DataFlowMode.IMAGE_AND_TEMP_OUTPUT) == 0) {
                            Log.i(TAG, "startPreview complete 红外+TNR");
                            try {
                                Thread.sleep(1500);
                            } catch (
                                    InterruptedException e) {
                                e.printStackTrace();
                            }
                            if (ircmd.startY16ModePreview(CommonParams.PreviewPathChannel.PREVIEW_PATH0,
                                    FileUtil.getY16SrcTypeByDataFlowMode(CommonParams.DataFlowMode.TNR_OUTPUT)) == 0) {
                                handleStartPreviewComplete();
                            } else {
                                Log.e(TAG, "startY16ModePreview error 红外+TNR");
                            }
                        } else {
                            Log.e(TAG, "startPreview error 红外+TNR");
                        }
                    } else {
                        Log.e(TAG, "stopPreview error 红外+TNR");
                    }
                } else {
                    if (ircmd.stopPreview(CommonParams.PreviewPathChannel.PREVIEW_PATH0) == 0) {
                        Log.i(TAG, "stopPreview complete 单TNR");
                        if (ircmd.startPreview(CommonParams.PreviewPathChannel.PREVIEW_PATH0,
                                CommonParams.StartPreviewSource.SOURCE_SENSOR,
                                ScreenUtils.getPreviewFPSByDataFlowMode(defaultDataFlowMode),
                                CommonParams.StartPreviewMode.VOC_DVP_MODE, defaultDataFlowMode) == 0) {
                            Log.i(TAG, "startPreview complete 单TNR");
                            try {
                                Thread.sleep(1500);
                            } catch (
                                    InterruptedException e) {
                                e.printStackTrace();
                            }
                            if (ircmd.startY16ModePreview(CommonParams.PreviewPathChannel.PREVIEW_PATH0,
                                    FileUtil.getY16SrcTypeByDataFlowMode(defaultDataFlowMode)) == 0) {
                                handleStartPreviewComplete();
                            } else {
                                Log.e(TAG, "startY16ModePreview error 单TNR");
                            }
                        } else {
                            Log.e(TAG, "startPreview error 单TNR");
                        }
                    } else {
                        Log.e(TAG, "stopPreview error 单TNR");
                    }
                }
            }
        }
        if (!isUseIRISP) {
            if (cmdDataCallback != null) {
                cmdDataCallback.onCMDDataComplete();
            }
        }
    }

    public void stopPreview() {
        Log.i(TAG, "stopPreview");
        if (uvcCamera != null) {
            if (uvcCamera.getOpenStatus()) {
                uvcCamera.onStopPreview();
            }
            uvcCamera.setFrameCallback(null);
            final UVCCamera camera;
            camera = uvcCamera;
            uvcCamera = null;
            if (ircmd != null) {
                ircmd.onDestroy();
                ircmd = null;
            }

            SystemClock.sleep(200);

            camera.onDestroyPreview();

        }
    }

    private void handleStartPreviewComplete() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mHandler.sendEmptyMessage(IRDisplayActivity.DEAL_Y16_MODE_PREVIEW_COMPLETE);
            }
        }).start();
    }

}
