package com.zhangwww.newnotebook.ui.home

import android.os.Bundle
import com.zhangwww.newnotebook.R
import com.zhangwww.newnotebook.base.BaseFragment
import com.zhangwww.newnotebook.databinding.FragmentRecordBinding
import com.zhangwww.newnotebook.util.viewbinding.viewBinding

class RecordFragment : BaseFragment(R.layout.fragment_record) {

    private val mViewBinding by viewBinding(FragmentRecordBinding::bind)

    companion object {

        @JvmStatic
        fun newInstance() =
            RecordFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }


}