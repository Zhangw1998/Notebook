package com.zhangwww.newnotebook.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.zhangwww.newnotebook.R
import com.zhangwww.newnotebook.base.BaseActivity
import com.zhangwww.newnotebook.databinding.ActivityHomeBinding
import com.zhangwww.newnotebook.ui.home.home.HomeFragment
import com.zhangwww.newnotebook.ui.home.mine.MineFragment
import com.zhangwww.newnotebook.util.viewbinding.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : BaseActivity() {

    private val mBinding by viewBinding(ActivityHomeBinding::inflate)

    private val mHomeFragment = HomeFragment()

    private val mMineFragment = MineFragment()

    private val mFragmentList = listOf(mHomeFragment, mMineFragment)

    override fun onCreate(savedInstanceState: Bundle?) {
        overridePendingTransition(0, 0)
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
    }

    override fun initView() {
        showFragment(mHomeFragment, HOME_FRAGMENT_TAG)
    }

    override fun initAction() {
        mBinding.bottomNavBar.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_home -> {
                    showFragment(mHomeFragment, HOME_FRAGMENT_TAG)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.navigation_mine -> {
                    showFragment(mMineFragment, MINE_FRAGMENT_TAG)
                    return@setOnNavigationItemSelectedListener true
                }
            }
            false
        }
    }

    private fun showFragment(fragment: Fragment, tag: String) {
        supportFragmentManager.commit {
            mFragmentList.forEach {
                if (it.isVisible) {
                    hide(it)
                }
            }
            if (isFragmentAdded(fragment, tag)) {
                show(fragment)
            } else {
                add(R.id.fl_container, fragment, tag)
            }
        }
    }

    private fun isFragmentAdded(fragment: Fragment, tag: String): Boolean {
        return fragment.isAdded
                || supportFragmentManager.findFragmentByTag(tag) != null
                || supportFragmentManager.fragments.contains(fragment)
    }

    companion object {
        private const val HOME_FRAGMENT_TAG = "HomeFragmentTAG"
        private const val MINE_FRAGMENT_TAG = "MineFragmentTAG"
    }
}