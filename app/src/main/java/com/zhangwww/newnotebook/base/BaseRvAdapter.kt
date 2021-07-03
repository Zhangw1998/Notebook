package com.zhangwww.newnotebook.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseRvAdapter<T, VB : ViewBinding>(private val mDataList: List<T> = mutableListOf()) :
    RecyclerView.Adapter<BaseViewHolder<T, VB>>() {

    abstract val bindingInflater: (LayoutInflater, ViewGroup, Boolean) -> VB

    private var mRecyclerView: RecyclerView? = null

    protected val recyclerView: RecyclerView
        get() = checkNotNull(mRecyclerView) {
            "Please get it after onAttachedToRecyclerView()"
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T, VB> {
        val itemRecordBinding = bindingInflater(LayoutInflater.from(parent.context), parent, false)
        return BaseViewHolder(itemRecordBinding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<T, VB>, position: Int) {
        holder.bind(mDataList[position])
    }

    override fun onBindViewHolder(
        holder: BaseViewHolder<T, VB>,
        position: Int,
        payloads: MutableList<Any>
    ) {
        holder.bind(mDataList[position], position, payloads)
        super.onBindViewHolder(holder, position, payloads)
    }

    override fun getItemCount(): Int {
        return mDataList.size
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        mRecyclerView = recyclerView
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        mRecyclerView = null
    }
}

