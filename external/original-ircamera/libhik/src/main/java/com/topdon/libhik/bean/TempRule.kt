package com.topdon.libhik.bean

import android.graphics.Point
import com.topdon.libhik.util.ByteArrayUtil.toFloat
import com.topdon.libhik.util.ByteArrayUtil.toInt
import com.topdon.libhik.util.ByteArrayUtil.toStr

/**
 * 专家测温规则及结果，共 208 byte.
 * @param enable 是否启用
 * @param regionId 区域 Id
 * @param distance 测温距离，单位 cm
 * @param regionType 区域类型 1-点 2-线 3-面
 * @param regionName 区域名称
 * @param emissivity 发射率百分比，如 97 表示 0.97
 * @param minTemp 最低温，单位摄氏度
 * @param maxTemp 最高温，单位摄氏度
 * @param aveTemp 平均温，单位摄氏度
 * @param diffTemp 温差，单位摄氏度
 * @param maxX 区域最高温归一化 X 轴坐标 [0, 1000]
 * @param maxY 区域最高温归一化 Y 轴坐标 [0, 1000]
 * @param minX 区域最低温归一化 X 轴坐标 [0, 1000]
 * @param minY 区域最低温归一化 Y 轴坐标 [0, 1000]
 * @param pointCount 多边形实际顶点数
 * @param pointList 点、线、面 顶点列表
 */
data class TempRule(
    val enable: Boolean,
    val regionId: Int,
    val distance: Int,
    val regionType: Int,
    val regionName: String,
    val emissivity: Int,
    val minTemp: Float,
    val maxTemp: Float,
    val aveTemp: Float,
    val diffTemp: Float,
    val maxX: Int,
    val maxY: Int,
    val minX: Int,
    val minY: Int,
    val pointCount: Int,
    val pointList: ArrayList<Point>,
) {
    constructor(byteArray: ByteArray, index: Int) : this(
        enable = byteArray[index] == 1.toByte(),
        regionId = byteArray[index + 1].toInt() and 0xff,
        distance = byteArray.toFloat(index + 28).toInt(),
        regionType = byteArray.toInt(index + 36),
        regionName = byteArray.toStr(index + 40, 32),
        emissivity = byteArray.toFloat(index + 72).toInt(),
        minTemp = byteArray.toFloat(index + 76),
        maxTemp = byteArray.toFloat(index + 80),
        aveTemp = byteArray.toFloat(index + 84),
        diffTemp = byteArray.toFloat(index + 88),
        maxX = byteArray.toInt(index + 92),
        maxY = byteArray.toInt(index + 96),
        minX = byteArray.toInt(index + 100),
        minY = byteArray.toInt(index + 104),
        pointCount = byteArray.toInt(index + 108),
        pointList = byteArray.toPointList(index + 112, byteArray.toInt(index + 108)),
    )

    companion object {
        /**
         * 从指定数组的 index 开始，解析共 count 个点坐标
         */
        private fun ByteArray.toPointList(index: Int, count: Int): ArrayList<Point> = try {
            val resultList: ArrayList<Point> = ArrayList(count)
            for (i in 0 until count) {
                val x: Int = toInt(index + i * 8)
                val y: Int = toInt(index + i * 8 + 4)
                resultList.add(Point(x, y))
            }
            resultList
        } catch (_: IndexOutOfBoundsException) {
            ArrayList(0)
        }
    }

    override fun toString(): String = "规则$regionId ${if (enable) "开启" else "关闭"} " +
            "距离:$distance cm，类型$regionType，" +
            "发射率:$emissivity，名称:$regionName，" +
            "最低温:($minX,$minY) ${minTemp}°C，" +
            "最高温:($maxX,$maxY) ${maxTemp}°C，" +
            "平均温:${aveTemp}°C，" +
            "顶点数:$pointCount"
}
