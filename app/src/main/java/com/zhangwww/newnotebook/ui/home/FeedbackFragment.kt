package com.zhangwww.newnotebook.ui.home

import com.zhangwww.newnotebook.R
import com.zhangwww.newnotebook.base.BaseFragment
import com.zhangwww.newnotebook.databinding.FragmentFeedbackBinding
import com.zhangwww.newnotebook.util.viewbinding.viewBinding

class FeedbackFragment : BaseFragment(R.layout.fragment_feedback) {

    private val mViewBinding by viewBinding(FragmentFeedbackBinding::bind)

    override fun initAction() {
        mViewBinding.ibBackFeedback.setOnClickListener {

        }
    }

}