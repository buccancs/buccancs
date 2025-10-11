package com.topdon.pseudo.activity

import android.content.Intent
import android.graphics.drawable.GradientDrawable
import android.view.View
import androidx.annotation.ColorInt
import androidx.core.view.isVisible
import com.blankj.utilcode.util.SizeUtils
import com.blankj.utilcode.util.ToastUtils
import com.topdon.lib.core.common.ProductType
import com.topdon.lib.core.config.ExtraKeyConfig
import com.topdon.lib.core.ktbase.BaseActivity
import com.topdon.lib.core.tools.UnitTools
import com.topdon.pseudo.R
import com.topdon.pseudo.bean.CustomPseudoBean
import com.topdon.pseudo.constant.*
import kotlinx.android.synthetic.main.activity_pseudo_set.*
import java.lang.NumberFormatException
import java.math.BigDecimal
import java.math.RoundingMode

/**
 * 颜色模式（自定义渲染）设置界面.
 *
 * 需要传递
 * - [ExtraKeyConfig.IS_TC007] - 是否设置 TC007 的自定义渲染
 * - [ExtraKeyConfig.CUSTOM_PSEUDO_BEAN] - 自定义渲染相关设置项.（可选，不传则从 SharedPreferences 中读取配置.）
 *
 * 返回 result
 * - [ExtraKeyConfig.CUSTOM_PSEUDO_BEAN] - 自定义渲染相关设置项.
 */
class PseudoSetActivity : BaseActivity(), View.OnClickListener {
    /**
     * 从上一界面传递过来的，自定义渲染相关设置项.
     */
    private lateinit var customPseudoBean: CustomPseudoBean


    override fun initContentView() = R.layout.activity_pseudo_set

    override fun initView() {
        val isTC007 = intent.getBooleanExtra(ExtraKeyConfig.IS_TC007, false)
        customPseudoBean = intent.getParcelableExtra(ExtraKeyConfig.CUSTOM_PSEUDO_BEAN) ?: CustomPseudoBean.loadFromShared(isTC007)
        switchDynamicCustom(customPseudoBean.isUseCustomPseudo)

        //加载温度配置
        et_max_temp.setText(UnitTools.showNoUnit(customPseudoBean.maxTemp))
        et_min_temp.setText(UnitTools.showNoUnit(customPseudoBean.minTemp))
        tv_max_temp_unit.text = UnitTools.showUnit()
        tv_min_temp_unit.text = UnitTools.showUnit()

        switchColorType(customPseudoBean.isColorCustom)

        //加载自定义颜色配置
        pseudo_pick_view.onSelectChangeListener = {
            reset6CustomColor()
            color_select_view.reset()
            when (pseudo_pick_view.sourceColors[it]) {
                0xff0000ff.toInt() -> view_custom_color1.isSelected = true
                0xffff0000.toInt() -> view_custom_color2.isSelected = true
                0xff00ff00.toInt() -> view_custom_color3.isSelected = true
                0xffffff00.toInt() -> view_custom_color4.isSelected = true
                0xff000000.toInt() -> view_custom_color5.isSelected = true
                0xffffffff.toInt() -> view_custom_color6.isSelected = true
            }
            color_select_view.selectColor(pseudo_pick_view.sourceColors[it])
            iv_custom_add.isEnabled = pseudo_pick_view.sourceColors.size < 7
            iv_custom_del.isEnabled = pseudo_pick_view.sourceColors.size > 3 && !pseudo_pick_view.isCurrentOnlyLimit()
        }
        pseudo_pick_view.reset(
            customPseudoBean.selectIndex,
            customPseudoBean.getCustomColors(),
            customPseudoBean.getCustomZAltitudes(),
            customPseudoBean.getCustomPlaces()
        )

        //加载推荐颜色配置
        view_recommend_color1.background = buildRectDrawableArray(ColorRecommend.colorList1)
        view_recommend_color2.background = buildRectDrawableArray(ColorRecommend.colorList2)
        view_recommend_color3.background = buildRectDrawableArray(ColorRecommend.getColorByIndex(isTC007, 2))
        view_recommend_color4.background = buildRectDrawableArray(ColorRecommend.colorList4)
        view_recommend_color5.background = buildRectDrawableArray(ColorRecommend.colorList5)
        switchRecommendColorIndex(customPseudoBean.customRecommendIndex)

        switchUseGray(customPseudoBean.isUseGray)


        cl_dynamic.setOnClickListener(this)
        cl_custom.setOnClickListener(this)
        tv_color_custom.setOnClickListener(this)
        tv_color_recommend.setOnClickListener(this)
        view_custom_color1.setOnClickListener(this)
        view_custom_color2.setOnClickListener(this)
        view_custom_color3.setOnClickListener(this)
        view_custom_color4.setOnClickListener(this)
        view_custom_color5.setOnClickListener(this)
        view_custom_color6.setOnClickListener(this)
        iv_custom_add.setOnClickListener(this)
        iv_custom_del.setOnClickListener(this)
        view_recommend_bg_color1.setOnClickListener(this)
        view_recommend_bg_color2.setOnClickListener(this)
        view_recommend_bg_color3.setOnClickListener(this)
        view_recommend_bg_color4.setOnClickListener(this)
        view_recommend_bg_color5.setOnClickListener(this)
        cl_over_grey.setOnClickListener(this)
        cl_over_color.setOnClickListener(this)
        tv_confirm.setOnClickListener(this)
        tv_cancel.setOnClickListener(this)

        color_select_view.onSelectListener = {
            reset6CustomColor()
            when (it) {
                0xff0000ff.toInt() -> view_custom_color1.isSelected = true
                0xffff0000.toInt() -> view_custom_color2.isSelected = true
                0xff00ff00.toInt() -> view_custom_color3.isSelected = true
                0xffffff00.toInt() -> view_custom_color4.isSelected = true
                0xff000000.toInt() -> view_custom_color5.isSelected = true
                0xffffffff.toInt() -> view_custom_color6.isSelected = true
            }
            pseudo_pick_view.refreshColor(it)
        }
    }

    override fun initData() {
    }

    override fun onClick(v: View?) {
        when (v) {
            cl_dynamic -> {//动态渲染
                switchDynamicCustom(false)
            }
            cl_custom -> {//自定义
                switchDynamicCustom(true)
            }
            tv_color_custom -> {//颜色-自定义
                switchColorType(true)
            }
            tv_color_recommend -> {//颜色-推荐
                switchColorType(false)
                switchRecommendColorIndex(customPseudoBean.customRecommendIndex)
            }

            view_custom_color1 -> {//颜色-自定义-颜色值拾取1
                reset6CustomColor()
                view_custom_color1.isSelected = true
                color_select_view.selectColor(0xff0000ff.toInt())
                pseudo_pick_view.refreshColor(0xff0000ff.toInt())
            }
            view_custom_color2 -> {//颜色-自定义-颜色值拾取2
                reset6CustomColor()
                view_custom_color2.isSelected = true
                color_select_view.selectColor(0xffff0000.toInt())
                pseudo_pick_view.refreshColor(0xffff0000.toInt())
            }
            view_custom_color3 -> {//颜色-自定义-颜色值拾取3
                reset6CustomColor()
                view_custom_color3.isSelected = true
                color_select_view.selectColor(0xff00ff00.toInt())
                pseudo_pick_view.refreshColor(0xff00ff00.toInt())
            }
            view_custom_color4 -> {//颜色-自定义-颜色值拾取4
                reset6CustomColor()
                view_custom_color4.isSelected = true
                color_select_view.selectColor(0xffffff00.toInt())
                pseudo_pick_view.refreshColor(0xffffff00.toInt())
            }
            view_custom_color5 -> {//颜色-自定义-颜色值拾取5
                reset6CustomColor()
                view_custom_color5.isSelected = true
                color_select_view.selectColor(0xff000000.toInt())
                pseudo_pick_view.refreshColor(0xff000000.toInt())
            }
            view_custom_color6 -> {//颜色-自定义-颜色值拾取6
                reset6CustomColor()
                view_custom_color6.isSelected = true
                color_select_view.selectColor(0xffffffff.toInt())
                pseudo_pick_view.refreshColor(0xffffffff.toInt())
            }

            iv_custom_add -> {//颜色-自定义-添加
                pseudo_pick_view.add()
            }
            iv_custom_del -> {//颜色-自定义-删除
                pseudo_pick_view.del()
            }

            view_recommend_bg_color1 -> {//颜色-推荐-铁红
                switchRecommendColorIndex(0)
            }
            view_recommend_bg_color2 -> {//颜色-推荐-黑红
                switchRecommendColorIndex(1)
            }
            view_recommend_bg_color3 -> {//颜色-推荐-自然
                switchRecommendColorIndex(2)
            }
            view_recommend_bg_color4 -> {//颜色-推荐-岩浆
                switchRecommendColorIndex(3)
            }
            view_recommend_bg_color5 -> {//颜色-推荐-辉金
                switchRecommendColorIndex(4)
            }

            cl_over_grey -> {//灰度渐变
                switchUseGray(true)
            }
            cl_over_color -> {//等色
                switchUseGray(false)
            }

            tv_confirm -> {//确定
                if (cl_custom_content.isVisible) {//使用自定义渲染
                    val inputMax = et_max_temp.text.toString()
                    if (inputMax.isEmpty()) {
                        ToastUtils.showShort(R.string.tip_input_format)
                        return
                    }
                    val inputMin = et_min_temp.text.toString()
                    if (inputMin.isEmpty()) {
                        ToastUtils.showShort(R.string.tip_input_format)
                        return
                    }

                    val maxTemp = try {
                        UnitTools.showToCValue(BigDecimal(inputMax).setScale(1, RoundingMode.HALF_UP).toFloat())
                    } catch (e: NumberFormatException) {
                        null
                    }
                    val minTemp = try {
                        UnitTools.showToCValue(BigDecimal(inputMin).setScale(1, RoundingMode.HALF_UP).toFloat())
                    } catch (e: NumberFormatException) {
                        null
                    }
                    if(maxTemp == null || minTemp == null || maxTemp < minTemp || maxTemp > 550f || minTemp < -20f) {
                        ToastUtils.showShort(R.string.tip_input_format)
                        return
                    }
                    if (maxTemp - minTemp < 0.1f) {
                        ToastUtils.showShort(R.string.tip_input_format)
                        return
                    }
                    customPseudoBean.maxTemp = maxTemp
                    customPseudoBean.minTemp = minTemp
                    customPseudoBean.selectIndex = pseudo_pick_view.selectIndex
                    customPseudoBean.colors = pseudo_pick_view.sourceColors
                    customPseudoBean.zAltitudes = pseudo_pick_view.zAltitudes
                    customPseudoBean.places = pseudo_pick_view.places
                }

                val resultIntent = Intent()
                resultIntent.putExtra(ExtraKeyConfig.CUSTOM_PSEUDO_BEAN, customPseudoBean)
                setResult(RESULT_OK, resultIntent)
                finish()
            }
            tv_cancel -> {//取消
                setResult(RESULT_CANCELED)
                finish()
            }
        }
    }

    /**
     * 在 动态渲染 与 自定义 之间切换.
     * @param isToCustom true-切换到自定义 false-切换到动态渲染
     */
    private fun switchDynamicCustom(isToCustom: Boolean) {
        customPseudoBean.isUseCustomPseudo = isToCustom
        cl_custom_content.isVisible = isToCustom
        cl_dynamic.isSelected = !isToCustom
        cl_custom.isSelected = isToCustom
        iv_dynamic.setImageResource(if (isToCustom) R.drawable.svg_pseudo_set_dynamic_not_select else R.drawable.svg_pseudo_set_dynamic_select)
        iv_custom.setImageResource(if (isToCustom) R.drawable.svg_pseudo_set_custom_select else R.drawable.svg_pseudo_set_custom_not_select)
        tv_dynamic_title.setTextColor(if (isToCustom) 0xffffffff.toInt() else 0xffffba42.toInt())
        tv_custom_title.setTextColor(if (isToCustom) 0xffffba42.toInt() else 0xffffffff.toInt())
    }

    /**
     * 在自定义渲染-颜色设置中的 自定义 与 推荐 之间切换.
     * @param isToCustom true-切换到自定义 false-切换到推荐
     */
    private fun switchColorType(isToCustom: Boolean) {
        customPseudoBean.isColorCustom = isToCustom
        cl_color_custom.isVisible = isToCustom
        cl_color_recommend.isVisible = !isToCustom
        tv_color_custom.setTextColor(if (isToCustom) 0xffffba42.toInt() else 0xffffffff.toInt())
        tv_color_recommend.setTextColor(if (isToCustom) 0xffffffff.toInt() else 0xffffba42.toInt())
        tv_color_custom.setBackgroundResource(if (isToCustom) R.drawable.bg_corners50_solid_2a183e_stroke_theme else 0)
        tv_color_recommend.setBackgroundResource(if (isToCustom) 0 else R.drawable.bg_corners50_solid_2a183e_stroke_theme)
    }

    /**
     * 将自定义颜色设置中，6个预设颜色值重置为均未选中状态.
     */
    private fun reset6CustomColor() {
        view_custom_color1.isSelected = false
        view_custom_color2.isSelected = false
        view_custom_color3.isSelected = false
        view_custom_color4.isSelected = false
        view_custom_color5.isSelected = false
        view_custom_color6.isSelected = false
    }

    /**
     * 切换 推荐颜色 中的 5 个选项.
     * @param 0-铁红 1-黑红 2-自然 3-岩浆 4-辉金
     */
    private fun switchRecommendColorIndex(index: Int) {
        when (customPseudoBean.customRecommendIndex) {
            0 -> {
                tv_recommend_color1.setTextColor(0x80ffffff.toInt())
                view_recommend_bg_color1.setBackgroundResource(R.drawable.bg_corners04_stroke_30_ff)
            }
            1 -> {
                tv_recommend_color2.setTextColor(0x80ffffff.toInt())
                view_recommend_bg_color2.setBackgroundResource(R.drawable.bg_corners04_stroke_30_ff)
            }
            2 -> {
                tv_recommend_color3.setTextColor(0x80ffffff.toInt())
                view_recommend_bg_color3.setBackgroundResource(R.drawable.bg_corners04_stroke_30_ff)
            }
            3 -> {
                tv_recommend_color4.setTextColor(0x80ffffff.toInt())
                view_recommend_bg_color4.setBackgroundResource(R.drawable.bg_corners04_stroke_30_ff)
            }
            4 -> {
                tv_recommend_color5.setTextColor(0x80ffffff.toInt())
                view_recommend_bg_color5.setBackgroundResource(R.drawable.bg_corners04_stroke_30_ff)
            }
        }
        when (index) {
            0 -> {
                tv_recommend_color1.setTextColor(0xffffba42.toInt())
                view_recommend_bg_color1.setBackgroundResource(R.drawable.bg_corners04_stroke_2dp_ffba42)
            }
            1 -> {
                tv_recommend_color2.setTextColor(0xffffba42.toInt())
                view_recommend_bg_color2.setBackgroundResource(R.drawable.bg_corners04_stroke_2dp_ffba42)
            }
            2 -> {
                tv_recommend_color3.setTextColor(0xffffba42.toInt())
                view_recommend_bg_color3.setBackgroundResource(R.drawable.bg_corners04_stroke_2dp_ffba42)
            }
            3 -> {
                tv_recommend_color4.setTextColor(0xffffba42.toInt())
                view_recommend_bg_color4.setBackgroundResource(R.drawable.bg_corners04_stroke_2dp_ffba42)
            }
            4 -> {
                tv_recommend_color5.setTextColor(0xffffba42.toInt())
                view_recommend_bg_color5.setBackgroundResource(R.drawable.bg_corners04_stroke_2dp_ffba42)
            }
        }
        customPseudoBean.customRecommendIndex = index
    }

    private fun switchUseGray(isUseGray: Boolean) {
        iv_over_grey_select.isVisible = isUseGray
        iv_over_color_select.isVisible = !isUseGray
        tv_over_grey.setTextColor(if (isUseGray) 0xffffba42.toInt() else 0xffffffff.toInt())
        tv_over_color.setTextColor(if (isUseGray) 0xffffffff.toInt() else 0xffffba42.toInt())
        cl_over_grey.setBackgroundResource(if (isUseGray) R.drawable.bg_corners05_solid_2a183e_stroke_theme else R.drawable.bg_corners05_solid_626569)
        cl_over_color.setBackgroundResource(if (isUseGray) R.drawable.bg_corners05_solid_626569 else R.drawable.bg_corners05_solid_2a183e_stroke_theme)
        customPseudoBean.isUseGray = isUseGray
    }




    private fun buildRectDrawableArray(color: IntArray): GradientDrawable {
        val drawable = GradientDrawable()
        drawable.shape = GradientDrawable.RECTANGLE
        drawable.cornerRadius = SizeUtils.dp2px(4f).toFloat()
        drawable.colors = color
        drawable.orientation = GradientDrawable.Orientation.LEFT_RIGHT
        return drawable
    }
}