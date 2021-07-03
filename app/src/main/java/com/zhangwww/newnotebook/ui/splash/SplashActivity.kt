package com.zhangwww.newnotebook.ui.splash

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.blankj.utilcode.util.BarUtils
import com.zhangwww.newnotebook.R
import com.zhangwww.newnotebook.base.BaseActivity
import com.zhangwww.newnotebook.extensions.launchActivity
import com.zhangwww.newnotebook.ui.home.HomeActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashActivity : BaseActivity(R.layout.activity_splash) {

    override fun onCreate(savedInstanceState: Bundle?) {
        BarUtils.transparentStatusBar(this)
        super.onCreate(savedInstanceState)
        lifecycleScope.launch(Dispatchers.Main) {
            delay(1500)
            launchActivity<HomeActivity>(this@SplashActivity)
            finish()
        }
    }
}