package com.zhangwww.newnotebook.ui.home.diary

import android.widget.TextView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.google.android.material.imageview.ShapeableImageView
import com.zhangwww.newnotebook.R
import com.zhangwww.newnotebook.data.DiaryCategoryModel

class DiaryCategoryAdapter :
    BaseQuickAdapter<DiaryCategoryModel, BaseViewHolder>(R.layout.item_dairy_category) {

    var onItemClick: ((DiaryCategoryModel) -> Unit)? = null

    override fun convert(holder: BaseViewHolder, item: DiaryCategoryModel) {
        holder.getView<ShapeableImageView>(R.id.iv_category_cover).apply {
            Glide.with(holder.itemView)
                .load(item.cover)
                .into(this)
        }
        holder.getView<TextView>(R.id.tv_diary_category).apply {
            text = item.categoryName
        }
        holder.itemView.setOnClickListener {
            onItemClick?.invoke(item)
        }
    }


}