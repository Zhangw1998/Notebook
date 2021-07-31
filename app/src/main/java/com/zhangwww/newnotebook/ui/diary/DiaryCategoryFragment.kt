package com.zhangwww.newnotebook.ui.diary

import com.blankj.utilcode.util.BarUtils
import com.zhangwww.newnotebook.R
import com.zhangwww.newnotebook.base.BaseFragment
import com.zhangwww.newnotebook.extensions.getCompatColor

class DiaryCategoryFragment : BaseFragment(R.layout.fragment_diary_category) {


    override fun initView() {
        BarUtils.setStatusBarColor(
            requireActivity().window,
            requireContext().getCompatColor(R.color.colorTheme)
        )
    }

}