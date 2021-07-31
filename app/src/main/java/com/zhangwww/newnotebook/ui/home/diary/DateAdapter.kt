package com.zhangwww.newnotebook.ui.home.diary

import android.graphics.Color
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.google.android.material.card.MaterialCardView
import com.zhangwww.newnotebook.R
import com.zhangwww.newnotebook.extensions.dp
import com.zhangwww.newnotebook.extensions.getCompatColor

class DateAdapter : BaseQuickAdapter<Int, BaseViewHolder>(R.layout.item_day) {

    private var mSelectedPosition = -1

    var onItemClick: ((Int) -> Unit)? = null

    override fun convert(holder: BaseViewHolder, item: Int) {
        if (mSelectedPosition == getItemPosition(item)) {
            setItemSelected(item, holder)
        } else {
            setItemUnselected(item, holder)
        }
        holder.itemView.setOnClickListener {
            onItemClick?.invoke(item)

        }
    }

    private fun setItemSelected(value: Int, holder: BaseViewHolder) {
        holder.getView<MaterialCardView>(R.id.cv_root).apply {
            cardElevation = 10.dp
            setCardBackgroundColor(context.getCompatColor(R.color.colorWhite))
        }
        holder.getView<TextView>(R.id.tv_day).apply {
            text = context.getString(R.string.countOfDay, value)
            textSize = 16f
            setTextColor(context.getCompatColor(R.color.colorTheme))
        }
    }

    private fun setItemUnselected(value: Int, holder: BaseViewHolder) {
        holder.getView<MaterialCardView>(R.id.cv_root).apply {
            cardElevation = 0f
            setCardBackgroundColor(Color.TRANSPARENT)
        }
        holder.getView<TextView>(R.id.tv_day).apply {
            text = "$value"
            textSize = 12f
            setTextColor(context.getCompatColor(R.color.colorTextBlack))
        }
    }

    fun setSelectItem(position: Int) {
        if (mSelectedPosition == -1) {
            mSelectedPosition = position
            return
        }
        recyclerView.findViewHolderForLayoutPosition(mSelectedPosition)?.let {
            if (it is BaseViewHolder) {
                setItemUnselected(getItem(mSelectedPosition), it)
            }
        }
        mSelectedPosition = position
        recyclerView.findViewHolderForLayoutPosition(position)?.let {
            if (it is BaseViewHolder) {
                setItemSelected(getItem(position), it)
            }
        }
    }
}