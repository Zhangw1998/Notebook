package com.zhangwww.newnotebook.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import com.zhangwww.newnotebook.base.BaseRvAdapter
import com.zhangwww.newnotebook.data.RecordModel
import com.zhangwww.newnotebook.databinding.ItemRecordBinding

class RecordAdapter : BaseRvAdapter<RecordModel, ItemRecordBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup, Boolean) -> ItemRecordBinding
        get() = ItemRecordBinding::inflate

}