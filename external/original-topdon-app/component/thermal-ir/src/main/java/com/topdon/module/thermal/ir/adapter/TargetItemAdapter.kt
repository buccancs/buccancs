package com.topdon.module.thermal.ir.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.topdon.lib.core.bean.ObserveBean
import com.topdon.lib.ui.bean.ColorBean
import com.topdon.module.thermal.ir.R

// Stubbed: import kotlinx.android.synthetic.main.itme_target_mode.view.*

class TargetItemAdapter(
    val context: Context
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var listener: ((index: Int, code: Int) -> Unit)? =
        null
    private var type =
        0
    private var selected =
        -1

    fun selected(
        index: Int
    ) {
        selected =
            index
        notifyDataSetChanged()
    }

    fun getSelected(): Int {
        return selected
    }

    private val secondBean =
        arrayListOf(
            ColorBean(
                R.drawable.ic_menu_thermal6002,
                "",
                ObserveBean.TYPE_TARGET_HORIZONTAL
            ),
            ColorBean(
                R.drawable.ic_menu_thermal6001,
                "",
                ObserveBean.TYPE_TARGET_VERTICAL
            ),
            ColorBean(
                R.drawable.ic_menu_thermal6003,
                "",
                ObserveBean.TYPE_TARGET_CIRCLE
            ),
        )

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val view =
            LayoutInflater.from(
                parent.context
            )
                .inflate(
                    R.layout.itme_target_mode,
                    parent,
                    false
                )
        return ItemView(
            view
        )
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        if (holder is ItemView) {
            val bean =
                secondBean[position]
            holder.img.setImageResource(
                bean.res
            )
            holder.lay.setOnClickListener {
                listener?.invoke(
                    position,
                    bean.code
                )
                selected(
                    bean.code
                )
            }
            holder.img.isSelected =
                bean.code == selected
            holder.name.text =
                bean.name
            holder.name.isSelected =
                bean.code == selected
            holder.name.setTextColor(
                if (position == selected) ContextCompat.getColor(
                    context,
                    R.color.white
                )
                else ContextCompat.getColor(
                    context,
                    R.color.font_third_color
                )
            )
        }
    }

    override fun getItemCount(): Int {
        return secondBean.size
    }

    inner class ItemView(
        itemView: View
    ) : RecyclerView.ViewHolder(
        itemView
    ) {
        val lay: View =
            itemView.item_menu_tab_lay
        val img: ImageView =
            itemView.item_menu_tab_img
        val name: TextView =
            itemView.item_menu_tab_text
    }
}