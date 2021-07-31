package com.zhangwww.newnotebook.ui.splash

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.blankj.utilcode.util.BarUtils
import com.zhangwww.newnotebook.R
import com.zhangwww.newnotebook.base.BaseActivity
import com.zhangwww.newnotebook.databinding.ActivitySplashBinding
import com.zhangwww.newnotebook.extensions.launchActivity
import com.zhangwww.newnotebook.ui.home.HomeActivity
import com.zhangwww.newnotebook.util.viewbinding.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashActivity : BaseActivity() {

    private val mBinding by viewBinding(ActivitySplashBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        BarUtils.transparentStatusBar(this)
        super.onCreate(savedInstanceState)
        lifecycleScope.launch(Dispatchers.Main) {
            delay(1000)
            launchActivity<HomeActivity>(this@SplashActivity)
            finish()
        }
    }
}