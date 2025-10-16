package com.topdon.libcom.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.core.content.ContextCompat
import androidx.core.graphics.ColorUtils
import com.blankj.utilcode.util.SizeUtils
import android.widget.SeekBar
import com.topdon.lib.core.utils.ScreenUtil
import com.topdon.libcom.R
import com.topdon.lib.core.view.ColorSelectView
import com.topdon.lib.core.R as CoreR

class ColorPickDialog(
    context: Context,
    @ColorInt private var color: Int,
    var textSize: Int,
    var textSizeIsDP: Boolean = false
) : Dialog(context, CoreR.style.InfoDialog), View.OnClickListener, SeekBar.OnSeekBarChangeListener {
    var onPickListener: ((color: Int, textSize: Int) -> Unit)? = null
    private val rootView: View = LayoutInflater.from(context).inflate(R.layout.dialog_color_pick, null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setCancelable(true)
        setCanceledOnTouchOutside(true)
        setContentView(rootView)
        window?.let {
            val layoutParams = it.attributes
            layoutParams.width = (ScreenUtil.getScreenWidth(context) * 0.9).toInt()
            layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT
            it.attributes = layoutParams
        }
        val baseColor = ContextCompat.getColor(context, R.color.we_read_theme_color)
        val activeTrackColor = ColorUtils.setAlphaComponent(baseColor, (0.1f * 255).toInt())
        val iconTintColor = ColorUtils.setAlphaComponent(baseColor, (0.7f * 255).toInt())
        when (color) {
            0xff0000ff.toInt() -> rootView.view_color1.isSelected = true
            0xffff0000.toInt() -> rootView.view_color2.isSelected = true
            0xff00ff00.toInt() -> rootView.view_color3.isSelected = true
            0xffffff00.toInt() -> rootView.view_color4.isSelected = true
            0xff000000.toInt() -> rootView.view_color5.isSelected = true
            0xffffffff.toInt() -> rootView.view_color6.isSelected = true
            else -> rootView.color_select_view.selectColor(color)
        }
        rootView.color_select_view.onSelectListener = {
            unSelect6Color()
            color = it
        }
        if (textSize != -1) {
            rootView.tv_size_title.visibility = View.VISIBLE
            rootView.tv_size_value.visibility = View.VISIBLE
            rootView.tv_nifty_left.visibility = View.VISIBLE
            rootView.tv_nifty_right.visibility = View.VISIBLE
            rootView.nifty_slider_view.visibility = View.VISIBLE
            rootView.nifty_slider_view.setOnSeekBarChangeListener(this)
        rootView.nifty_slider_view.progress = textSizeToProgress(textSize, textSizeIsDP)
        } else {
            rootView.nifty_slider_view.visibility = View.GONE
        }
        rootView.view_color1.setOnClickListener(this)
        rootView.view_color2.setOnClickListener(this)
        rootView.view_color3.setOnClickListener(this)
        rootView.view_color4.setOnClickListener(this)
        rootView.view_color5.setOnClickListener(this)
        rootView.view_color6.setOnClickListener(this)
        rootView.rl_close.setOnClickListener(this)
        rootView.tv_save.setOnClickListener(this)
    }

    private fun textSizeToProgress(size: Int, isDpValue: Boolean): Int {
        return if (isDpValue) {
            when (size) {
                14 -> 0
                16 -> 1
                else -> 2
            }
        } else {
            when (size) {
                SizeUtils.sp2px(14f) -> 0
                SizeUtils.sp2px(16f) -> 1
                else -> 2
            }
        }
    }

    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        val sizeValue = when (progress) {
            0 -> 14
            1 -> 16
            else -> 18
        }
        textSize = if (textSizeIsDP) sizeValue else SizeUtils.sp2px(sizeValue.toFloat())
        val textRes = when (progress) {
            0 -> CoreR.string.temp_text_standard
            1 -> CoreR.string.temp_text_big
            else -> CoreR.string.temp_text_sup_big
        }
        rootView.tv_size_value.text = context.getString(textRes)
    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {
    }

    override fun onStopTrackingTouch(seekBar: SeekBar?) {
    }

    override fun onClick(v: View?) {
        when (v) {
            rootView.rl_close -> dismiss()
            rootView.tv_save -> {
                dismiss()
                onPickListener?.invoke(color, textSize)
            }

            rootView.view_color1 -> {
                unSelect6Color()
                rootView.color_select_view.reset()
                rootView.view_color1.isSelected = true
                color = 0xff0000ff.toInt()
            }

            rootView.view_color2 -> {
                unSelect6Color()
                rootView.color_select_view.reset()
                rootView.view_color2.isSelected = true
                color = 0xffff0000.toInt()
            }

            rootView.view_color3 -> {
                unSelect6Color()
                rootView.color_select_view.reset()
                rootView.view_color3.isSelected = true
                color = 0xff00ff00.toInt()
            }

            rootView.view_color4 -> {
                unSelect6Color()
                rootView.color_select_view.reset()
                rootView.view_color4.isSelected = true
                color = 0xffffff00.toInt()
            }

            rootView.view_color5 -> {
                unSelect6Color()
                rootView.color_select_view.reset()
                rootView.view_color5.isSelected = true
                color = 0xff000000.toInt()
            }

            rootView.view_color6 -> {
                unSelect6Color()
                rootView.color_select_view.reset()
                rootView.view_color6.isSelected = true
                color = 0xffffffff.toInt()
            }
        }
    }

    private fun unSelect6Color() {
        rootView.view_color1.isSelected = false
        rootView.view_color2.isSelected = false
        rootView.view_color3.isSelected = false
        rootView.view_color4.isSelected = false
        rootView.view_color5.isSelected = false
        rootView.view_color6.isSelected = false
    }
}

private val View.rl_close: View
    get() = findViewById<View>(R.id.rl_close)
private val View.tv_save: View
    get() = findViewById<View>(R.id.tv_save)
private val View.view_color1: View
    get() = findViewById<View>(R.id.view_color1)
private val View.view_color2: View
    get() = findViewById<View>(R.id.view_color2)
private val View.view_color3: View
    get() = findViewById<View>(R.id.view_color3)
private val View.view_color4: View
    get() = findViewById<View>(R.id.view_color4)
private val View.view_color5: View
    get() = findViewById<View>(R.id.view_color5)
private val View.view_color6: View
    get() = findViewById<View>(R.id.view_color6)
private val View.color_select_view: ColorSelectView
    get() = findViewById<ColorSelectView>(R.id.color_select_view)
private val View.tv_size_title: TextView
    get() = findViewById<TextView>(R.id.tv_size_title)
private val View.tv_size_value: TextView
    get() = findViewById<TextView>(R.id.tv_size_value)
private val View.tv_nifty_left: TextView
    get() = findViewById<TextView>(R.id.tv_nifty_left)
private val View.tv_nifty_right: TextView
    get() = findViewById<TextView>(R.id.tv_nifty_right)
private val View.nifty_slider_view: SeekBar
    get() = findViewById<SeekBar>(R.id.nifty_slider_view)
