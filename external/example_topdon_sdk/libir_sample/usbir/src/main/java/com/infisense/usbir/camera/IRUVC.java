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
    // 机芯温度
    private int[] curVtemp = new int[1];
    //
    private USBMonitor mUSBMonitor;
    private ConnectCallback mConnectCallback; // usb连接回调
    private byte[] imageSrc;
    private byte[] temperatureSrc;
    private int imageOrTempDataLength = 256 * 192 * 2; // 红外或温度的数据长度
    private SynchronizedBitmap syncimage;
        private LibIRProcess.AutoGainSwitchInfo_t auto_gain_switch_info = new LibIRProcess.AutoGainSwitchInfo_t();
    private LibIRProcess.GainSwitchParam_t gain_switch_param = new LibIRProcess.GainSwitchParam_t();
    // 帧率展示
    private int count = 0;
    private long timeStart = 0;
    //
    private Handler mHandler;
    private boolean rotate = false;
    // 是否使用IRISP算法集成，从外部传入
    private boolean isUseIRISP;
    // 是否使用GPU方案
    private boolean isUseGPU;
    // 判断数据是否准备完毕，在准备完毕之前，画面可能会出现不正常
    private boolean isFrameReady = true;
    // 当前的增益状态
    private CommonParams.GainStatus gainStatus = CommonParams.GainStatus.HIGH_GAIN;
    // 模组支持的高低增益模式
    private CommonParams.GainMode gainMode = CommonParams.GainMode.GAIN_MODE_HIGH_LOW;
    private short[] nuc_table_high = new short[8192];
    private short[] nuc_table_low = new short[8192];
    private boolean isReadNuc = true; // 是否读取nuc信息，首次读取nuc信息，后续存储到文件中下次直接传入不用重新读取
    //
    private byte[] priv_high = new byte[1201];
    private byte[] priv_low = new byte[1201];
    private short[] kt_high = new short[1201];
    private short[] kt_low = new short[1201];
    private short[] bt_high = new short[1201];
    private short[] bt_low = new short[1201];
    private byte[] temperatureTemp = new byte[imageOrTempDataLength];
    //
    private CMDDataCallback cmdDataCallback;
    // 是否可以红外+TNR出图
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
        //
        LibIRProcess.ImageRes_t imageRes = new LibIRProcess.ImageRes_t();
        imageRes.height = (char) (dataFlowMode == CommonParams.DataFlowMode.IMAGE_AND_TEMP_OUTPUT ? cameraHeight / 2
                : cameraHeight);
        imageRes.width = (char) cameraWidth;
        //
        initUVCCamera();
        // 注意：USBMonitor的所有回调函数都是运行在线程中的
        mUSBMonitor = new USBMonitor(context, new USBMonitor.OnDeviceConnectListener() {

            // called by checking usb device
            // do request device permission
            @Override
            public void onAttach(UsbDevice device) {
                Log.w(TAG, "onAttach");
                if (uvcCamera == null || !uvcCamera.getOpenStatus()) {
                    mUSBMonitor.requestPermission(device);
                }
                // 监听回调
                if (usbMonitorCallback != null) {
                    usbMonitorCallback.onAttach();
                }
            }

            @Override
            public void onGranted(UsbDevice usbDevice, boolean granted) {
                Log.w(TAG, "onGranted");
                // 监听回调
                if (usbMonitorCallback != null) {
                    usbMonitorCallback.onGranted();
                }
            }

            // called by connect to usb camera
            // do open camera,start previewing
            @Override
            public void onConnect(final UsbDevice device, USBMonitor.UsbControlBlock ctrlBlock, boolean createNew) {
                Log.w(TAG, "onConnect");
                if (createNew) {
                    openUVCCamera(ctrlBlock);
                    // 获取设备的分辨率列表
                    List<CameraSize> previewList = getAllSupportedSize();
                    // 可以根据获取到的分辨率列表，来区分不同的模组，从而改变不同的cmd参数来调用不同的SDK
                    initIRCMD(previewList);
                    if (ircmd != null) {
                        // 根据设备的分辨率列表，这里可以动态的设置模组的宽高(这里作为示例，用的是从外部传入的方式)
                        isTempReplacedWithTNREnabled = ircmd.isTempReplacedWithTNREnabled(DeviceType.P2);
                        Log.i(TAG, "onConnect->isTempReplacedWithTNREnabled = " + isTempReplacedWithTNREnabled);
                        Log.i(TAG, "onConnect->isUseIRISP = " + isUseIRISP);
                        if (isUseIRISP && isTempReplacedWithTNREnabled) {
                            // 使用红外+TNR数据的方式，不用进行停图重新出图的流程，方便快速出图
                            setPreviewSize(cameraWidth, cameraHeight * 2);
                        } else {
                            // 单TNR数据
                            setPreviewSize(cameraWidth, cameraHeight);
                        }
                        //
                        startPreview();
                        // 监听回调
                        if (usbMonitorCallback != null) {
                            usbMonitorCallback.onConnect();
                        }
                    }
                }
            }

            // called by disconnect to usb camera
            // do nothing
            @Override
            public void onDisconnect(UsbDevice device, USBMonitor.UsbControlBlock ctrlBlock) {
                Log.w(TAG, "onDisconnect");
                // 监听回调
                if (usbMonitorCallback != null) {
                    usbMonitorCallback.onDisconnect();
                }
            }

            // called by taking out usb device
            // do close camera
            @Override
            public void onDettach(UsbDevice device) {
                Log.w(TAG, "onDettach");
                if (uvcCamera != null && uvcCamera.getOpenStatus()) {
//                    stopPreview();
                    // 监听回调
                    if (usbMonitorCallback != null) {
                        usbMonitorCallback.onDettach();
                    }
                }
            }

            @Override
            public void onCancel(UsbDevice device) {
                Log.w(TAG, "onCancel");
                // 监听回调
                if (usbMonitorCallback != null) {
                    usbMonitorCallback.onCancel();
                }
            }
        });
                // 自动增益切换参数auto gain switch parameter
        gain_switch_param.above_pixel_prop = 0.1f;    //用于high -> low gain,设备像素总面积的百分比
        gain_switch_param.above_temp_data = (int) ((130 + 273.15) * 16 * 4); //用于high -> low gain,高增益向低增益切换的触发温度
        gain_switch_param.below_pixel_prop = 0.95f;   //用于low -> high gain,设备像素总面积的百分比
        gain_switch_param.below_temp_data = (int) ((110 + 273.15) * 16 * 4);//用于low -> high gain,低增益向高增益切换的触发温度
        auto_gain_switch_info.switch_frame_cnt = 5 * 15; //连续满足触发条件帧数超过该阈值会触发自动增益切换(假设出图速度为15帧每秒，则5 * 15大概为5秒)
        auto_gain_switch_info.waiting_frame_cnt = 7 * 15;//触发自动增益切换之后，会间隔该阈值的帧数不进行增益切换监测(假设出图速度为15帧每秒，则7 * 15大概为7秒)
        // 防灼烧参数over_portect parameter
        int low_gain_over_temp_data = (int) ((550 + 273.15) * 16 * 4); //低增益下触发防灼烧的温度
        int high_gain_over_temp_data = (int) ((150 + 273.15) * 16 * 4); //高增益下触发防灼烧的温度
        float pixel_above_prop = 0.02f;//设备像素总面积的百分比
        int switch_frame_cnt = 7 * 15;//连续满足触发条件超过该阈值会触发防灼烧(假设出图速度为15帧每秒，则7 * 15大概为7秒)
        int close_frame_cnt = 10 * 15;//触发防灼烧之后，经过该阈值的帧数之后会解除防灼烧(假设出图速度为15帧每秒，则10 * 15大概为10秒)
        // 设备出图回调
        iFrameCallback = new IFrameCallback() {
            @Override
            public void onFrame(byte[] frame) {
                if (!isFrameReady) {
                    return;
                }
                // 帧率展示
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
                //
                if (syncimage == null) {
                    return;
                }
                syncimage.start = true;
                //
                synchronized (syncimage.dataLock) {
                    // 判断坏帧，出现坏帧则重启sensor
                    int length = frame.length - 1;
                    if (frame[length] == 1) {
                        // bad frame
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
//                            Log.i(TAG, "rotate = " + rotate +
//                                    " IRUVC->imageSrc.length = " + imageSrc.length +
//                                    " imageSrc[100] = " + imageSrc[100] +
//                                    " temperatureSrc.length = " + temperatureSrc.length +
//                                    " temperatureSrc[100] = " + temperatureSrc[100]);
                            if (ircmd != null) {
                                // 自动增益切换，不生效的话请您的设备是否支持自动增益切换
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
                                // 防灼烧保护
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
        // UVCCamera init
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
        // IRCMD init
        if (uvcCamera != null) {

            ConcreteIRCMDBuilder concreteIRCMDBuilder = new ConcreteIRCMDBuilder();
            ircmd = concreteIRCMDBuilder
                    .setIrcmdType(IRCMDType.USB_IR_256_384)
                    .setIdCamera(uvcCamera.getNativePtr())
                    .setCreateResultCallback(new OnCreateResultCallback() {
                        @Override
                        public void onInitResult(ResultCode resultCode) {
                            Log.d(TAG, "onInitResult : " + resultCode.toString());
                            //根据初始化回调，处理app相关逻辑
                            if (resultCode != ResultCode.SUCCESS) {
                                Message message = Message.obtain();
                                message.what = IRDisplayActivity.IRCMD_INIT_FAIL;
                                message.obj = resultCode;
                                mHandler.sendMessage(message);
                            }
                        }
                    })
                    .build();
            //这里可根据是否得到ircmd的对象，判断是否初始化成功，初始化失败，可做相应的失败错误提示
            //错误信息可以通过setCreateResultCallback的回调查看
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
        // uvc开启
        if (uvcCamera.openUVCCamera(ctrlBlock) == 0) {
            // UVCCamera开启成功
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
        //
        uvcCamera.onStartPreview();

        if (CommonParams.DataFlowMode.IMAGE_AND_TEMP_OUTPUT == defaultDataFlowMode ||
                CommonParams.DataFlowMode.IMAGE_OUTPUT == defaultDataFlowMode) {
                        Log.i(TAG, "defaultDataFlowMode = IMAGE_AND_TEMP_OUTPUT or IMAGE_OUTPUT");
            // YUV出图流程
            setFrameReady(false);
            if (isRestart) {
                if (ircmd.stopPreview(CommonParams.PreviewPathChannel.PREVIEW_PATH0) == 0) {
                    Log.i(TAG, "stopPreview complete");
                    // 2. 发出图命令，设置分辨率为256*384
                    if (ircmd.startPreview(CommonParams.PreviewPathChannel.PREVIEW_PATH0,
                            CommonParams.StartPreviewSource.SOURCE_SENSOR,
                            ScreenUtils.getPreviewFPSByDataFlowMode(defaultDataFlowMode),
                            CommonParams.StartPreviewMode.VOC_DVP_MODE, defaultDataFlowMode) == 0) {
                        Log.i(TAG, "startPreview complete");
                        //===
                        handleStartPreviewComplete();
                    }
                } else {
                    Log.e(TAG, "stopPreview error");
                }
            } else {
                // 如果模组默认就是YUV出图，则不需要执行下面的1和2操作
                handleStartPreviewComplete();
                // 1.停图（全部停图，不是退出y16模式的停图）
//                                if (ircmd.stopPreview(CommonParams.PreviewPathChannel.PREVIEW_PATH0) == 0) {
//                                    Log.i(TAG, "stopPreview complete");
//                                    SystemClock.sleep(500);
//                                    // 2. 发出图命令，设置分辨率为256*384
//                                    if (ircmd.startPreview(CommonParams.PreviewPathChannel.PREVIEW_PATH0,
//                                            CommonParams.StartPreviewSource.SOURCE_SENSOR,
//                                            ScreenUtils.getPreviewFPSByDataFlowMode(defaultDataFlowMode),
//                                            CommonParams.StartPreviewMode.VOC_DVP_MODE, defaultDataFlowMode) == 0) {
//                                        //===
//                                        dealY16ModePreviewComplete();
//                                    }
//                                }
            }
        } else {
                        // Y16出图流程(例如TNR出图，使用ISP算法)
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
                        } catch (InterruptedException e) {
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
                                        // 对于P2模组来说，直接发送startY16ModePreview命令可以直接出图
//                    if (ircmd.startY16ModePreview(CommonParams.PreviewPathChannel.PREVIEW_PATH0,
//                            FileUtil.getY16SrcTypeByDataFlowMode(defaultDataFlowMode)) == 0) {
//                        handleStartPreviewComplete();
//                    } else {
//                        Log.e(TAG, "startY16ModePreview error");
//                    }
                    // 对于M2模组来说，需要先发送startPreview出图命令，再发送startY16ModePreview命令才可以重新出图
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
                            } catch (InterruptedException e) {
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
                                        // 调用 startY16ModePreview 中间出图方法之后，输出的数据格式为y16
                    if (ircmd.stopPreview(CommonParams.PreviewPathChannel.PREVIEW_PATH0) == 0) {
                        Log.i(TAG, "stopPreview complete 单TNR");
                        if (ircmd.startPreview(CommonParams.PreviewPathChannel.PREVIEW_PATH0,
                                CommonParams.StartPreviewSource.SOURCE_SENSOR,
                                ScreenUtils.getPreviewFPSByDataFlowMode(defaultDataFlowMode),
                                CommonParams.StartPreviewMode.VOC_DVP_MODE, defaultDataFlowMode) == 0) {
                            Log.i(TAG, "startPreview complete 单TNR");
                            try {
                                                                Thread.sleep(1500);
                            } catch (InterruptedException e) {
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
            // 从机芯中读取数据完毕，页面可以进行正常的操作了
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
            //IRCMD在不用时一定要回收
            if (ircmd != null) {
                ircmd.onDestroy();
                ircmd = null;
            }

            SystemClock.sleep(200);

            //initIRISPModule 与 destroyIRISPModule对应使用，回收资源
            camera.onDestroyPreview();

        }
    }

        private void handleStartPreviewComplete() {
        // 出图之后再去获取kt,bt,nuc_t等参数来设置温度数据，避免耗时操作导致这里的停图和出图受影响
        new Thread(new Runnable() {
            @Override
            public void run() {
                mHandler.sendEmptyMessage(IRDisplayActivity.DEAL_Y16_MODE_PREVIEW_COMPLETE);
            }
        }).start();
    }

}
