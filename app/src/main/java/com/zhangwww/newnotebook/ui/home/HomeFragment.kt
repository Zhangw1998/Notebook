package com.zhangwww.newnotebook.ui.home

import androidx.core.view.GravityCompat
import com.zhangwww.newnotebook.R
import com.zhangwww.newnotebook.base.BaseFragment
import com.zhangwww.newnotebook.databinding.FragmentHomeBinding
import com.zhangwww.newnotebook.util.viewbinding.viewBinding

class HomeFragment : BaseFragment(R.layout.fragment_home) {

    private val mViewBinding by viewBinding(FragmentHomeBinding::bind)

    override fun initView() {
        mViewBinding.drawerLayoutHome.setDrawerShadow(R.drawable.ic_menu, GravityCompat.START)
    }

    override fun initAction() {

        mViewBinding.ibNavHome.setOnClickListener {
            mViewBinding.drawerLayoutHome.openDrawer(GravityCompat.START, true)
        }

        mViewBinding.fab.setOnClickListener {


        }
    }

}