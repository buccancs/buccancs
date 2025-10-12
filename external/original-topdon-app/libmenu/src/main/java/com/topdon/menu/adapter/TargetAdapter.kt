package com.topdon.menu.adapter

import android.annotation.SuppressLint
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.topdon.menu.R
import com.topdon.menu.constant.TargetType

/**
 * 观测模式-菜单4-标靶 菜单所用 Adapter.
 *
 * 测量模式(MODE)、标靶(STYLE)、标靶颜色(COLOR)、删除(DELETE)、帮助(HELP)
 *
 * - 测量模式(MODE)、标靶(STYLE) 捆绑，要么都选中，要么都不选中，与 删除(DELETE) 互斥
 * - 删除(DELETE) 与 {测量模式(MODE)、标靶(STYLE)、标靶颜色(COLOR)} 互斥
 * - 标靶颜色(COLOR) 生效且未处于删除亮，颜色为默认绿色或处于删除不亮，丢给上层维护这个状态
 * - 帮助(HELP) 显示弹框亮，关闭弹框不亮，丢给上层维护这个状态
 *
 * Created by LCG on 2024/11/28.
 */
@SuppressLint("NotifyDataSetChanged")
internal class TargetAdapter : BaseMenuAdapter() {

    var onTargetListener: ((targetType: TargetType) -> Unit)? = null

    fun setSelected(targetType: TargetType, isSelected: Boolean) {
        for (i in dataArray.indices) {
            if (dataArray[i].targetType == targetType) {
                dataArray[i].isSelected = isSelected
                notifyItemChanged(i)
                break
            }
        }
    }

    fun setTargetMode(modeCode: Int) {
        for (i in dataArray.indices) {
            if (dataArray[i].targetType == TargetType.MODE) {
                dataArray[i].drawableId = when (modeCode) {
                    11 -> R.drawable.selector_menu2_target_1_sheep
                    12 -> R.drawable.selector_menu2_target_1_dog
                    13 -> R.drawable.selector_menu2_target_1_bird
                    else -> R.drawable.selector_menu2_target_1_person
                }
                notifyItemChanged(i)
                break
            }
        }
    }


    private val dataArray: Array<Data> = arrayOf(
        Data(R.string.main_tab_second_measure_mode, R.drawable.selector_menu2_target_1_person, TargetType.MODE),
        Data(R.string.main_tab_first_target, R.drawable.selector_menu2_target_2_style, TargetType.STYLE),
        Data(R.string.main_tab_second_target_color, R.drawable.selector_menu2_target_3_color, TargetType.COLOR),
        Data(R.string.thermal_delete, R.drawable.selector_menu2_del, TargetType.DELETE),
        Data(R.string.main_tab_second_target_help, R.drawable.selector_menu2_target_4_help, TargetType.HELP),
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data: Data = dataArray[position]
        holder.binding.ivIcon.setImageResource(data.drawableId)
        holder.binding.tvText.setText(data.stringId)
        holder.binding.ivIcon.isSelected = data.isSelected
        holder.binding.tvText.isSelected = data.isSelected
        holder.binding.clRoot.setOnClickListener {
            //            data.isSelected = !data.isSelected
//            holder.binding.ivIcon.isSelected = data.isSelected
//            holder.binding.tvText.isSelected = data.isSelected
            onTargetListener?.invoke(data.targetType)
        }
    }

    override fun getItemCount(): Int = dataArray.size

    data class Data(
        @StringRes val stringId: Int,
        @DrawableRes var drawableId: Int,
        val targetType: TargetType,
        var isSelected: Boolean = false,
    )
}