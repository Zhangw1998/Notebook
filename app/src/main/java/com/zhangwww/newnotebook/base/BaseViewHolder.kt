package com.zhangwww.newnotebook.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

open class BaseViewHolder<T, VB: ViewBinding>(protected val binding: VB) : RecyclerView.ViewHolder(binding.root) {

    open fun bind(value: T) {

    }

    open fun bind(value: T, position: Int, payloads: MutableList<Any>) {

    }

}