package com.zhangwww.newnotebook.ui.home

import android.os.Bundle
import androidx.core.view.isInvisible
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.blankj.utilcode.util.BarUtils
import com.zhangwww.newnotebook.R
import com.zhangwww.newnotebook.base.BaseActivity
import com.zhangwww.newnotebook.databinding.ActivityHomeBinding
import com.zhangwww.newnotebook.util.viewbinding.viewBinding
import dagger.hilt.android.AndroidEntryPoint

import com.google.android.material.bottomnavigation.BottomNavigationView
import com.zhangwww.newnotebook.extensions.getCompatColor

// todo 移除Navigation库

@AndroidEntryPoint
class HomeActivity : BaseActivity() {

    private val mBinding by viewBinding(ActivityHomeBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        overridePendingTransition(0, 0)
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        val navController = findNavController(R.id.nav_host_fragment)
        mBinding.bottomNavBar.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            mBinding.bottomNavBar.isInvisible = destination.id == R.id.navigation_diary
        }
    }

}