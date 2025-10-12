package com.infisense.usbir.thread;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.SystemClock;
import android.util.Log;

import com.infisense.iruvc.sdkisp.LibIRParse;
import com.infisense.iruvc.sdkisp.LibIRProcess;
import com.infisense.iruvc.utils.CommonParams;
import com.infisense.iruvc.utils.SynchronizedBitmap;
import com.infisense.usbir.utils.PseudocolorModeTable;

import java.nio.ByteBuffer;

public class ImageThread extends Thread {

    private String TAG = "ImageThread";
    private Context mContext;
    private Bitmap bitmap;
    private SynchronizedBitmap syncimage;
    private int imageWidth;
    private int imageHeight;
    private byte[] imageSrc;
    private byte[] temperatureSrc;
    private boolean rotate;
    private boolean biaochistatus = false;
    private CommonParams.DataFlowMode dataFlowMode = CommonParams.DataFlowMode.IMAGE_AND_TEMP_OUTPUT;
    private byte[] imageYUV422;
    private byte[] imageARGB;
    private byte[] imageDst;
    private byte[] imageY8;

    public ImageThread(Context context, int imageWidth, int imageHeight) {
        Log.i(TAG, "ImageThread create->imageWidth = " + imageWidth + " imageHeight = " + imageHeight);
        this.mContext = context;
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
        imageYUV422 = new byte[imageWidth * imageHeight * 2];
        imageARGB = new byte[imageWidth * imageHeight * 4];
        imageDst = new byte[imageWidth * imageHeight * 4];
        imageY8 = new byte[imageWidth * imageHeight];
    }

    public void setSyncimage(SynchronizedBitmap syncimage) {
        this.syncimage = syncimage;
    }

    public void setImageSrc(byte[] imageSrc) {
        this.imageSrc = imageSrc;
    }

    public void setTemperatureSrc(byte[] temperatureSrc) {
        this.temperatureSrc = temperatureSrc;
    }

    public void setRotate(boolean rotate) {
        this.rotate = rotate;
    }

    public void setDataFlowMode(CommonParams.DataFlowMode dataFlowMode) {
        this.dataFlowMode = dataFlowMode;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            synchronized (syncimage.dataLock) {
                if (syncimage.start) {
                    Log.i(TAG, "IRUVC_DATA run->dataFlowMode = " + dataFlowMode + " rotate = " + rotate +
                            " syncimage.valid = " + syncimage.valid +
                            " imageSrc.length = " + imageSrc.length +
                            " imageSrc[100] = " + imageSrc[100]);
                    if (dataFlowMode == CommonParams.DataFlowMode.IMAGE_AND_TEMP_OUTPUT ||
                            dataFlowMode == CommonParams.DataFlowMode.IMAGE_OUTPUT) {
                        LibIRParse.converyArrayYuv422ToARGB(imageSrc, imageHeight * imageWidth, imageARGB);


                    } else {
                        LibIRParse.convertArrayY14ToYuv422(imageSrc, imageHeight * imageWidth, imageYUV422);
                        LibIRParse.converyArrayYuv422ToARGB(imageYUV422, imageHeight * imageWidth, imageARGB);
                    }

                    if (biaochistatus && temperatureSrc != null) {
                        int j = 0;
                        int imageDstLength = imageWidth * imageHeight * 4;
                        float biaochiMax = 40, biaochiMin = 25;
                        for (int index = 0; index < imageDstLength; ) {
                            float temperature0 = (temperatureSrc[j] & 0xff) + (temperatureSrc[j + 1] & 0xff) * 256;
                            temperature0 = (float) (temperature0 / 64 - 273.15);
                            if (temperature0 < biaochiMin) {
                                imageARGB[index] = (byte) PseudocolorModeTable.BLUE_RGB[0];
                                imageARGB[index + 1] = (byte) PseudocolorModeTable.BLUE_RGB[1];
                                imageARGB[index + 2] = (byte) PseudocolorModeTable.BLUE_RGB[2];
                            } else if (temperature0 > biaochiMax) {
                                imageARGB[index] = (byte) PseudocolorModeTable.RED_RGB[0];
                                imageARGB[index + 1] = (byte) PseudocolorModeTable.RED_RGB[1];
                                imageARGB[index + 2] = (byte) PseudocolorModeTable.RED_RGB[2];
                            }
                            imageARGB[index + 3] = (byte) 255;
                            index += 4;
                            j += 2;
                        }
                    }

                    if (rotate) {
                        LibIRProcess.ImageRes_t imageRes = new LibIRProcess.ImageRes_t();
                        imageRes.height = (char) imageWidth;
                        imageRes.width = (char) imageHeight;
                        LibIRProcess.rotateRight90(imageARGB, imageRes,
                                CommonParams.IRPROCSRCFMTType.IRPROC_SRC_FMT_ARGB8888, imageDst);
                    } else {
                        imageDst = imageARGB;
                    }
                }
            }

            synchronized (syncimage.viewLock) {
                if (!syncimage.valid) {
                    bitmap.copyPixelsFromBuffer(ByteBuffer.wrap(imageDst));
                    syncimage.valid = true;
                    syncimage.viewLock.notify();
                }
            }
            SystemClock.sleep(20);
        }
        Log.i(TAG, "ImageThread exit");
    }

}