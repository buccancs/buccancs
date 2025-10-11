package com.guide.zm04c.matrix.utils

import android.graphics.Bitmap
import android.graphics.Bitmap.CompressFormat
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.ExifInterface
import com.guide.zm04c.matrix.Logger
import java.io.*


/**
 * created by liuhongwei gd02527 on 2018年08月29日
 */
class FileUtils {

    companion object {

        private val TAG = "RealTimeImpl"

        fun isFileExist(filePath: String): Boolean {
            if (StringUtils.isBlank(filePath)) {
                return false
            }

            val file = File(filePath)
            return file.exists() && file.isFile
        }


        /**
         * 删除文件夹以及目录下的文件
         * @param   filePath 被删除目录的文件路径
         * @return  目录删除成功返回true，否则返回false
         */
        fun deleteDirectory(filePath: String): Boolean {
            var filePath = filePath
            var flag = false
            //如果filePath不以文件分隔符结尾，自动添加文件分隔符
            if (!filePath.endsWith(File.separator)) {
                filePath = filePath + File.separator
            }
            val dirFile = File(filePath)
            if (!dirFile.exists() || !dirFile.isDirectory) {
                return false
            }
            flag = true
            val files = dirFile.listFiles()
            //遍历删除文件夹下的所有文件(包括子目录)
            for (i in files.indices) {
                if (files[i].isFile) {
                    //删除子文件
                    flag = deleteFile(files[i].absolutePath)
                    if (!flag) break
                } else {
                    //删除子目录
                    flag = deleteDirectory(files[i].absolutePath)
                    if (!flag) break
                }
            }
            return if (!flag) false else dirFile.delete()
            //删除当前空目录
        }

        fun deleteFile(path: String): Boolean {
            if (StringUtils.isBlank(path)) {
                return true
            }

            val file = File(path)
            if (!file.exists()) {
                return true
            }
            if (file.isFile) {
                return file.delete()
            }
            if (!file.isDirectory) {
                return false
            }
            for (f in file.listFiles()!!) {
                if (f.isFile) {
                    f.delete()
                } else if (f.isDirectory) {
                    deleteFile(f.absolutePath)
                }
            }
            return file.delete()
        }


        fun appFile(data: ByteArray, filePath: String) {
            // 打开一个随机访问文件流，按读写方式
            var randomFile = RandomAccessFile(filePath, "rw")
            // 文件长度，字节数
            var fileLength = randomFile.length()
            //将写文件指针移到文件尾。
            randomFile.seek(fileLength);
            randomFile.write(data);

            randomFile.close();

        }

        fun saveFile(data: ByteArray, filePath: String, isAppend: Boolean) {
            var outputFile: FileOutputStream? = null
            var inputStream: ByteArrayInputStream? = null

            try {
                outputFile = FileOutputStream(filePath, isAppend)
                inputStream = ByteArrayInputStream(data)

                val buff = ByteArray(1024)
                var len = inputStream.read(buff)

                while (len != -1) {
                    outputFile.write(buff, 0, len)
                    len = inputStream.read(buff)
                }

            } catch (io: IOException) {
                io.printStackTrace()
            } finally {
                outputFile?.close()
                inputStream?.close()
            }
        }

        fun saveFile(data: ShortArray, filePath: String, isAppend: Boolean) {
            saveFile(BaseDataTypeConvertUtils.convertShortArr2LittleEndianByteArr(data), filePath, isAppend)
        }

        /**
         * 保存Bitmap为JPG文件
         *
         * @param bmp
         * @param filePath
         * @return
         */
        fun saveBitmap2JpegFile(bmp: Bitmap, filePath: String): Boolean {

            val format = CompressFormat.JPEG
            val quality = 100
            var stream: OutputStream? = null
            try {
                val f = File(filePath)

                if (!f.exists()) {
                    Logger.d("FileUtils", "file not exists")
                    f.createNewFile()
                }

                stream = FileOutputStream(f)

                Logger.d("FileUtils", "end")

            } catch (e: FileNotFoundException) {
                e.printStackTrace()
                Logger.d("FileUtils", "FileNotFoundException: " + e.message)
            } catch (e: IOException) {
                e.printStackTrace()
                Logger.d("FileUtils", "IOException: " + e.message)
            }

            if (null != bmp && null != stream) {
                return bmp.compress(format, quality, stream)
            }
            return false
        }

        /**
         * 旋转Bitmap
         *
         * @param srcBitmap    源Bitmap
         * @param rotateDegree 旋转角度
         * @return
         */
        fun rotateBitmap(srcBitmap: Bitmap, rotateDegree: Float): Bitmap? {
            var dstBitmap: Bitmap? = null
            val matrix = Matrix()
            matrix.setRotate(rotateDegree, srcBitmap.width.toFloat() / 2, srcBitmap.height.toFloat() / 2)
            dstBitmap = Bitmap.createBitmap(srcBitmap, 0, 0, srcBitmap.width, srcBitmap.height, matrix, true)
            return dstBitmap
        }

        private fun calculateInSampleSize(options: BitmapFactory.Options, reqWidth: Int, reqHeight: Int): Int {

            val height = options.outHeight
            val width = options.outWidth
            var inSampleSize = 1

            if (height > reqHeight || width > reqWidth) {
                if (width > height) {
                    inSampleSize = Math.round(height.toFloat() / reqHeight.toFloat())
                } else {
                    inSampleSize = Math.round(width.toFloat() / reqWidth.toFloat())
                }
            }

            return inSampleSize
        }

        fun getBitmapFromPath(imagePath: String, width: Int, height: Int): Bitmap? {

            val file = File(imagePath)
            var bitmap: Bitmap? = null

            if (file.exists()) {

                val options = BitmapFactory.Options()
                options.inJustDecodeBounds = true
                BitmapFactory.decodeFile(imagePath, options)

                if (width != 0 && height != 0) {
                    options.inSampleSize = calculateInSampleSize(options, width, height)
                } else {
                    return null
                }

                options.inJustDecodeBounds = false
                options.inDither = false
                options.inScaled = true

                var fs: FileInputStream? = null
                try {
                    fs = FileInputStream(file)
                } catch (e: FileNotFoundException) {
                    e.printStackTrace()
                }

                if (fs != null) {
                    try {

                        bitmap = BitmapFactory.decodeFileDescriptor(fs.fd, null, options)

                        if (imagePath.contains(".jpg")) {
                            var rotate = 0
                            val exif = ExifInterface(imagePath)
                            //获取方向信息
                            val orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_UNDEFINED)

                            when (orientation) {
                                ExifInterface.ORIENTATION_ROTATE_90 -> rotate = 90
                                ExifInterface.ORIENTATION_ROTATE_180 -> rotate = 180
                                ExifInterface.ORIENTATION_ROTATE_270 -> rotate = 270
                            }

                            if (0 != rotate) {
                                bitmap = rotateBitmap(bitmap, rotate.toFloat())
                            }
                        }
                    } catch (e: IOException) {
                        e.printStackTrace()
                    } finally {
                        if (fs != null) {
                            try {
                                fs.close()
                            } catch (e: IOException) {
                                e.printStackTrace()
                            }

                        }
                    }
                }
            }

            return bitmap
        }

        fun readFile2ByteArr(filePath: String, fileNotFoundErrAction: () -> Unit, ioErrAction: () -> Unit): ByteArray? {
            // 从文件读取
            var fis: FileInputStream? = null
            val inFile = File(filePath)
            val buffer: ByteArray?

            try {
                fis = FileInputStream(inFile)
                buffer = ByteArray(fis.available())
                fis.read(buffer)
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
                fileNotFoundErrAction()
                return null
            } catch (ioe: IOException) {
                ioe.printStackTrace()
                ioErrAction()
                return null
            } finally {
                if (null != fis) {
                    try {
                        fis.close()
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }

                }
            }

            return buffer
        }


        fun inputStream2ByteArray(inputStream: InputStream?): ByteArray? {
            var byteArr: ByteArray? = null
            try {
                if (null != inputStream) {
                    byteArr = ByteArray(inputStream.available())
                    inputStream.read(byteArr)
                }
            } catch (e1: Exception) {
                e1.printStackTrace()
                // 捕获异常后尝试读取下一遍
                try {
                    if (null != inputStream) {
                        byteArr = ByteArray(inputStream.available())
                        inputStream.read(byteArr)
                    }
                } catch (e2: Exception) {
                    e2.printStackTrace()
                }

            }

            return byteArr
        }

        fun getFileSize(path: String): Long {
            if (StringUtils.isBlank(path)) {
                return -1
            }

            val file = File(path)
            return if (file.exists() && file.isFile) file.length() else -1
        }

    }


    init {
        throw AssertionError("cannot be instantiated")
    }

}