package com.zhangwww.newnotebook.extensions

import androidx.recyclerview.widget.RecyclerView

inline fun<reified  VH : RecyclerView.ViewHolder> RecyclerView.updateItem(position: Int, block: (VH) -> Unit) {
    val itemViewHolder = findViewHolderForLayoutPosition(position)
    if (itemViewHolder == null) {
        adapter?.notifyItemChanged(position)
    } else {
        if (itemViewHolder is VH) {
            block(itemViewHolder)
        }
    }
}