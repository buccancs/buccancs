package com.topdon.house.view

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.blankj.utilcode.util.SizeUtils
import com.bumptech.glide.Glide
import com.topdon.house.R
import com.topdon.house.activity.ImagesDetailActivity
import com.topdon.house.activity.ItemEditActivity
import com.topdon.lib.core.config.ExtraKeyConfig
import com.topdon.lib.core.db.entity.DirDetect
import com.topdon.lib.core.db.entity.ItemDetect
import com.topdon.lib.core.tools.SpanBuilder
import com.topdon.lms.sdk.weiget.TToast
import kotlinx.android.synthetic.main.item_report_add_default.view.*
import kotlinx.android.synthetic.main.item_report_add_head.view.*

/**
 * 检测界面执行检测的列表自定义 View.
 *
 * Created by LCG on 2024/8/24.
 */
@SuppressLint("NotifyDataSetChanged")
class HouseDetectView : FrameLayout {
    /**
     * 所有目录列表，仅用于全部收起及全部展开调用.
     */
    private var dirList: ArrayList<DirDetect> = ArrayList()
    /**
     * 目录+项目拆开的列表.
     */
    private val dataList: ArrayList<Any> = ArrayList()

    /**
     * 当前展示的标题在 [dataList] 中的 position
     */
    private var currentPosition: Int = 0


    private val adapter = MyAdapter()
    private val onScrollListener = MyOnScrollListener()
    private val layoutManager: LinearLayoutManager
    private val recyclerView: RecyclerView

    private val titleView: View

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : this(context, attrs, defStyleAttr, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes:Int) : super(context, attrs, defStyleAttr, defStyleRes) {
        titleView = LayoutInflater.from(context).inflate(R.layout.item_report_add_head, this, false)
        titleView.isVisible = false

        layoutManager = LinearLayoutManager(context)
        recyclerView = RecyclerView(context, attrs, defStyleAttr)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = layoutManager
        recyclerView.addOnScrollListener(onScrollListener)
        recyclerView.addOnLayoutChangeListener(MyOnLayoutChangeListener())
        recyclerView.isVisible = true

        addView(recyclerView, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        addView(titleView, titleView.layoutParams ?: LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT))

        titleView.setOnClickListener {
            adapter.switchExpand(currentPosition)
        }
        titleView.view_dir_edit.setOnClickListener {
            val intent = Intent(context, ItemEditActivity::class.java)
            intent.putExtra(ExtraKeyConfig.DIR_ID, (dataList[currentPosition] as DirDetect).id)
            context.startActivity(intent)
        }
        titleView.view_dir_copy.setOnClickListener {
            onDirCopyListener?.invoke(Pair(currentPosition, dataList[currentPosition] as DirDetect))
        }
    }

    /**
     * 指定 position 位置的目录复制事件监听.
     */
    var onDirCopyListener: ((pair: Pair<Int, DirDetect>) -> Unit)? = null
    /**
     * 指定 position 位置的项目复制事件监听.
     */
    var onItemCopyListener: ((pair: Pair<Int, ItemDetect>) -> Unit)? = null
    /**
     * 指定 position 位置的项目删除事件监听.
     */
    var onItemDelListener: ((pair: Pair<Int, ItemDetect>) -> Unit)? = null


    /**
     * 某个 item 的添加图片按钮点击事件监听.
     */
    var onImageAddListener: ((layoutIndex: Int, v: View, item: ItemDetect) -> Unit)? = null
    /**
     * 某个 item 的输入文字按钮点击事件监听.
     */
    var onTextInputListener: ((pair: Pair<Int, ItemDetect>) -> Unit)? = null


    /**
     * 一个目录发生变更事件(3种状态数量变更)监听.
     */
    var onDirChangeListener: ((dirDetect: DirDetect) -> Unit)? = null
    /**
     * 一个目录展开或收起变更事件监听.
     */
    var onDirExpandListener: ((isExpand: Boolean) -> Unit)? = null
    /**
     * 一个项目发生变更(3种状态变更、图片删除)事件监听.
     */
    var onItemChangeListener: ((itemDetect: ItemDetect) -> Unit)? = null


    /**
     * 使用指定的目录列表刷新界面，默认所有目录处于收起状态
     */
    fun refresh(newList: ArrayList<DirDetect>) {
        this.dirList = newList
        titleView.isVisible = newList.isNotEmpty()
        if (newList.isNotEmpty()) {
            adapter.refreshDir(titleView, newList[0])
        }
        dataList.clear()
        for (dir in newList) {
            dataList.add(dir)
        }
        adapter.notifyDataSetChanged()
    }

    fun notifyDirInsert(position: Int, dirDetect: DirDetect) {
        if (dirDetect.isExpand) {//在展开状态执行复制
            dataList.add(position + dirDetect.itemList.size + 1, dirDetect)
            dataList.addAll(position + dirDetect.itemList.size + 2, dirDetect.itemList)
            adapter.notifyItemRangeInserted(position + dirDetect.itemList.size + 1, dirDetect.itemList.size + 1)
        } else {
            dataList.add(position + 1, dirDetect)
            adapter.notifyItemInserted(position + 1)
        }
    }

    fun notifyItemInsert(position: Int, itemDetect: ItemDetect) {
        val isLastItemInDir = position == dataList.size - 1 || dataList[position + 1] is DirDetect
        dataList.add(position + 1, itemDetect)
        adapter.notifyItemInserted(position + 1)
        if (isLastItemInDir) {
            adapter.notifyItemChanged(position)
        }
        if (itemDetect.state > 0) {
            val dirPosition = findDirPosition(position)
            adapter.notifyItemChanged(dirPosition)
            if (dirPosition == currentPosition) {
                adapter.refreshDir(titleView, dataList[dirPosition] as DirDetect)
            }
        }
    }

    fun notifyItemRemove(position: Int, itemDetect: ItemDetect) {
        val dirPosition = findDirPosition(position)
        val isLastItemInDir = position == dataList.size - 1 || dataList[position + 1] is DirDetect
        dataList.removeAt(position)
        adapter.notifyItemRemoved(position)
        if (itemDetect.dirDetect.itemList.isEmpty()) {//该目录最后一个项目都删掉了，目录也删掉
            dataList.removeAt(position - 1)
            adapter.notifyItemRemoved(position - 1)
            if (dataList.isEmpty()) {
                titleView.isVisible = false
            }
        } else {
            if (isLastItemInDir) {
                adapter.notifyItemChanged(position - 1)
            }
            if (itemDetect.state > 0) {
                adapter.notifyItemChanged(dirPosition)
                if (dirPosition == currentPosition) {
                    adapter.refreshDir(titleView, dataList[dirPosition] as DirDetect)
                }
            }
        }
    }

    fun notifyItemChange(position: Int) {
        adapter.notifyItemChanged(position)
    }

    /**
     * 展开所有目录
     */
    fun expandAllDir() {
        dataList.clear()
        for (dir in dirList) {
            dir.isExpand = true
            dataList.add(dir)
            dataList.addAll(dir.itemList)
        }
        adapter.notifyDataSetChanged()
    }

    /**
     * 收起所有目录
     */
    fun retractAllDir() {
        dataList.clear()
        for (dir in dirList) {
            dir.isExpand = false
            dataList.add(dir)
        }
        adapter.notifyDataSetChanged()
    }




    private inner class MyAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        /**
         * 切换 position 处目录的展开收起状态
         */
        fun switchExpand(position: Int) {
            val dirDetect: DirDetect = dataList[position] as DirDetect
            dirDetect.isExpand = !dirDetect.isExpand
            if (dirDetect.isExpand) {//关闭->展开
                dataList.addAll(position + 1, dirDetect.itemList)
            } else {//展开->关闭
                dataList.removeAll(dirDetect.itemList.toSet())
            }
            notifyDataSetChanged()
            onDirExpandListener?.invoke(dirDetect.isExpand)
        }

        fun refreshDir(dirView: View, dirDetect: DirDetect) {
            dirView.tv_dir_name.text = dirDetect.dirName
            dirView.tv_good_count.text = dirDetect.getGoodCountStr()
            dirView.tv_warn_count.text = dirDetect.getWarnCountStr()
            dirView.tv_danger_count.text = dirDetect.getDangerCountStr()
            if (dirDetect.isExpand) {
                dirView.iv_triangle.setImageResource(R.drawable.svg_house_triangle_up)
                dirView.view_bg_dir.setBackgroundResource(R.drawable.bg_corners10_top_solid_23202e)
            } else {
                dirView.iv_triangle.setImageResource(R.drawable.svg_house_triangle_down)
                dirView.view_bg_dir.setBackgroundResource(R.drawable.bg_corners10_solid_23202e)
            }
        }

        override fun getItemViewType(position: Int): Int = if (dataList[position] is DirDetect) 0 else 1

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            return if (viewType == 0) {
                HeadViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_report_add_head, parent, false))
            } else {
                ItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_report_add_default, parent, false))
            }
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            if (holder is HeadViewHolder) {
                val dirDetect: DirDetect = dataList[position] as DirDetect
                refreshDir(holder.itemView, dirDetect)
            } else {
                val itemDetect: ItemDetect = dataList[position] as ItemDetect
                holder.itemView.tv_item_name.text = itemDetect.itemName
                holder.itemView.tv_good.isSelected = itemDetect.state == 1
                holder.itemView.tv_warn.isSelected = itemDetect.state == 2
                holder.itemView.tv_danger.isSelected = itemDetect.state == 3
                holder.itemView.tv_state.text = itemDetect.getStateStr(context)
                holder.itemView.tv_input_text.text = itemDetect.inputText
                holder.itemView.tv_input_text.isVisible = itemDetect.inputText.isNotEmpty()

                holder.itemView.cl_image.isVisible = itemDetect.image1.isNotEmpty()
                holder.itemView.iv_image1.isVisible = itemDetect.image1.isNotEmpty()
                holder.itemView.iv_del_image1.isVisible = itemDetect.image1.isNotEmpty()
                holder.itemView.iv_image2.isVisible = itemDetect.image2.isNotEmpty()
                holder.itemView.iv_del_image2.isVisible = itemDetect.image2.isNotEmpty()
                holder.itemView.iv_image3.isVisible = itemDetect.image3.isNotEmpty()
                holder.itemView.iv_del_image3.isVisible = itemDetect.image3.isNotEmpty()
                holder.itemView.iv_image4.isVisible = itemDetect.image4.isNotEmpty()
                holder.itemView.iv_del_image4.isVisible = itemDetect.image4.isNotEmpty()
                holder.itemView.tv_image_count_tips.text = SpanBuilder(itemDetect.getImageSize().toString()).appendColor("/4", 0x80ffffff.toInt())
                if (itemDetect.image1.isNotEmpty()) {
                    Glide.with(context).load(itemDetect.image1).into(holder.itemView.iv_image1)
                }
                if (itemDetect.image2.isNotEmpty()) {
                    Glide.with(context).load(itemDetect.image2).into(holder.itemView.iv_image2)
                }
                if (itemDetect.image3.isNotEmpty()) {
                    Glide.with(context).load(itemDetect.image3).into(holder.itemView.iv_image3)
                }
                if (itemDetect.image4.isNotEmpty()) {
                    Glide.with(context).load(itemDetect.image4).into(holder.itemView.iv_image4)
                }

                if (position == dataList.size - 1 || dataList[position + 1] is DirDetect) {
                    holder.itemView.setBackgroundResource(R.drawable.bg_corners10_bottom_solid_23202e)
                    holder.itemView.setPadding(0, 0, 0, SizeUtils.dp2px(16f))
                } else {
                    holder.itemView.setBackgroundColor(0xff23202e.toInt())
                    holder.itemView.setPadding(0, 0, 0, SizeUtils.dp2px(12f))
                }
            }
        }

        override fun getItemCount(): Int = dataList.size

        inner class HeadViewHolder(rootView: View) : RecyclerView.ViewHolder(rootView) {
            init {
                rootView.setOnClickListener {
                    val position = bindingAdapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        switchExpand(position)
                    }
                }
                rootView.view_dir_edit.setOnClickListener {//编辑目录
                    val position = bindingAdapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        val intent = Intent(context, ItemEditActivity::class.java)
                        intent.putExtra(ExtraKeyConfig.DIR_ID, (dataList[position] as DirDetect).id)
                        context.startActivity(intent)
                    }
                }
                rootView.view_dir_copy.setOnClickListener {//复制目录
                    val position = bindingAdapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        onDirCopyListener?.invoke(Pair(position, dataList[position] as DirDetect))
                    }
                }
            }
        }

        inner class ItemViewHolder(rootView: View) : RecyclerView.ViewHolder(rootView) {
            init {
                rootView.tv_good.setOnClickListener {
                    handleStateChange(1)
                }
                rootView.tv_warn.setOnClickListener {
                    handleStateChange(2)
                }
                rootView.tv_danger.setOnClickListener {
                    handleStateChange(3)
                }
                rootView.iv_image1.setOnClickListener {
                    handleImageClick(0)
                }
                rootView.iv_image2.setOnClickListener {
                    handleImageClick(1)
                }
                rootView.iv_image3.setOnClickListener {
                    handleImageClick(2)
                }
                rootView.iv_image4.setOnClickListener {
                    handleImageClick(3)
                }
                rootView.iv_del_image1.setOnClickListener {
                    handleImageDel(1)
                }
                rootView.iv_del_image2.setOnClickListener {
                    handleImageDel(2)
                }
                rootView.iv_del_image3.setOnClickListener {
                    handleImageDel(3)
                }
                rootView.iv_del_image4.setOnClickListener {
                    handleImageDel(4)
                }
                rootView.tv_item_add_img.setOnClickListener {//添加图片
                    val position = bindingAdapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        val itemDetect: ItemDetect = dataList[position] as ItemDetect
                        if (itemDetect.getImageSize() < 4) {
                            onImageAddListener?.invoke(position, it, itemDetect)
                        } else {
                            TToast.shortToast(context, R.string.upload_img_limit)
                        }
                    }
                }
                rootView.tv_item_add_text.setOnClickListener { //添加文字
                    val position = bindingAdapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        onTextInputListener?.invoke(Pair(position, dataList[position] as ItemDetect))
                    }
                }
                rootView.tv_item_copy.setOnClickListener {
                    val position = bindingAdapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        onItemCopyListener?.invoke(Pair(position, dataList[position] as ItemDetect))
                    }
                }
                rootView.tv_item_del.setOnClickListener {
                    val position = bindingAdapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        rootView.swipe_menu_layout.switchState()
                    }
                }
                rootView.iv_red_del.setOnClickListener {
                    val position = bindingAdapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        onItemDelListener?.invoke(Pair(position, dataList[position] as ItemDetect))
                    }
                }
            }

            private fun handleStateChange(newState: Int) {
                val position = bindingAdapterPosition
                if (position == RecyclerView.NO_POSITION) {
                    return
                }
                val itemDetect: ItemDetect = dataList[position] as ItemDetect
                if (itemDetect.state == newState) {
                    return
                }
                val dirPosition = findDirPosition(position)
                val dirDetect: DirDetect = dataList[dirPosition] as DirDetect
                when (itemDetect.state) {
                    1 -> dirDetect.goodCount--
                    2 -> dirDetect.warnCount--
                    3 -> dirDetect.dangerCount--
                }
                when (newState) {
                    1 -> dirDetect.goodCount++
                    2 -> dirDetect.warnCount++
                    3 -> dirDetect.dangerCount++
                }
                itemDetect.state = newState
                notifyItemChanged(dirPosition)
                notifyItemChanged(position)
                if (dirPosition == currentPosition) {
                    titleView.tv_good_count.text = dirDetect.getGoodCountStr()
                    titleView.tv_warn_count.text = dirDetect.getWarnCountStr()
                    titleView.tv_danger_count.text = dirDetect.getDangerCountStr()
                }
                onDirChangeListener?.invoke(dirDetect)
                onItemChangeListener?.invoke(itemDetect)
            }

            private fun handleImageClick(imagePosition: Int) {
                val position = bindingAdapterPosition
                if (position == RecyclerView.NO_POSITION) {
                    return
                }
                val itemDetect: ItemDetect = dataList[position] as ItemDetect
                val intent = Intent(context, ImagesDetailActivity::class.java)
                intent.putExtra(ExtraKeyConfig.CURRENT_ITEM, imagePosition)
                intent.putExtra(ExtraKeyConfig.IMAGE_PATH_LIST, itemDetect.buildImageList())
                context.startActivity(intent)
            }

            /**
             * 执行一张图片删除处理
             * @param imageNum 第几张图片，取值`[1,4]`
             */
            private fun handleImageDel(imageNum: Int) {
                val position = bindingAdapterPosition
                if (position == RecyclerView.NO_POSITION) {
                    return
                }
                val itemDetect: ItemDetect = dataList[position] as ItemDetect
                itemDetect.delOneImage(imageNum)
                onItemChangeListener?.invoke(itemDetect)
                notifyItemChanged(position)
            }
        }
    }

    /**
     * 从指定 itemPosition 处，往上遍历查找该 item 对应的 Dir position.
     */
    private fun findDirPosition(itemPosition: Int): Int {
        for (i in itemPosition downTo 0) {
            if (dataList[i] is DirDetect) {
                return i
            }
        }
        return 0
    }

    private inner class MyOnLayoutChangeListener : OnLayoutChangeListener {
        override fun onLayoutChange(v: View?, left: Int, top: Int, right: Int, bottom: Int, oldLeft: Int, oldTop: Int, oldRight: Int, oldBottom: Int) {
            val seeFirstPosition = layoutManager.findFirstVisibleItemPosition()
            if (seeFirstPosition == RecyclerView.NO_POSITION || seeFirstPosition >= dataList.size) {
                return
            }
            // notify 后旧 currentPosition 已不准确，需要刷新
            currentPosition = if (dataList[seeFirstPosition] is DirDetect) seeFirstPosition else findDirPosition(seeFirstPosition)
            titleView.translationY = 0f
            adapter.refreshDir(titleView, dataList[currentPosition] as DirDetect)
            onScrollListener.onScrolled(recyclerView, 0, 0)
        }
    }

    private inner class MyOnScrollListener : RecyclerView.OnScrollListener() {

        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {

        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            val seeFirstPosition = layoutManager.findFirstVisibleItemPosition()
            if (seeFirstPosition == RecyclerView.NO_POSITION || seeFirstPosition >= dataList.size - 1) {
                return
            }

            //刷新 currentPosition
            if (currentPosition != seeFirstPosition) {
                if (dataList[seeFirstPosition] is DirDetect) {
                    currentPosition = seeFirstPosition
                    titleView.translationY = 0f
                    adapter.refreshDir(titleView, dataList[currentPosition] as DirDetect)
                } else {
                    if (dataList[seeFirstPosition + 1] is DirDetect) {//第1个可见的item为尾部
                        currentPosition = findDirPosition(seeFirstPosition)
                        titleView.translationY = 0f
                        adapter.refreshDir(titleView, dataList[currentPosition] as DirDetect)
                    }
                }
            }

            //刷新 titleView 背景
            /*if ((dataList[currentPosition] as DirDetect).isExpand) {
                if (dataList[seeFirstPosition] is ItemDetect && dataList[seeFirstPosition + 1] is DirDetect) {
                    titleView.view_bg_dir.setBackgroundResource(R.drawable.bg_corners10_solid_23202e)
                } else {
                    titleView.view_bg_dir.setBackgroundResource(R.drawable.bg_corners10_top_solid_23202e)
                }
            } else {//收起肯定是4圆角
                titleView.view_bg_dir.setBackgroundResource(R.drawable.bg_corners10_solid_23202e)
            }*/
            if (dataList[seeFirstPosition] is ItemDetect && dataList[seeFirstPosition + 1] is DirDetect) {
                titleView.view_bg_dir.setBackgroundResource(R.drawable.bg_corners10_solid_23202e)
            }


            //刷新 titleView 位置
            if (dataList[seeFirstPosition + 1] is DirDetect) {//第1个可见的item为尾部
                val nextView: View? = layoutManager.findViewByPosition(seeFirstPosition + 1)
                if (nextView != null) {
                    if (nextView.top <= titleView.height) {
                        titleView.translationY = -(titleView.height - nextView.top).toFloat()
                    } else {
                        titleView.translationY = 0f
                    }
                }
            } else {
                if (titleView.translationY != 0f) {
                    titleView.translationY = 0f
                }
            }
        }
    }
}