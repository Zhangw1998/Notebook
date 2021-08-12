package com.zhangwww.newnotebook.ui.writing

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.blankj.utilcode.util.BarUtils
import com.blankj.utilcode.util.KeyboardUtils
import com.zhangwww.newnotebook.R
import com.zhangwww.newnotebook.base.BaseActivity
import com.zhangwww.newnotebook.databinding.ActivityWritingBinding
import com.zhangwww.newnotebook.extensions.getCompatColor
import com.zhangwww.newnotebook.util.viewbinding.viewBinding

class WritingActivity : BaseActivity() {

    private val mBinding by viewBinding(ActivityWritingBinding::inflate)
    private val mViewModel by viewModels<WriteViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        BarUtils.setStatusBarColor(window, getCompatColor(R.color.colorTheme))
        BarUtils.setStatusBarLightMode(this, true)
    }

    override fun onDestroy() {
        KeyboardUtils.unregisterSoftInputChangedListener(window)
        super.onDestroy()
    }

    override fun initData() {
        val timestamp = intent.getLongExtra(PARAM_TIMESTAMP, System.currentTimeMillis())
        mViewModel.setDairyTimestamp(timestamp)
    }

    override fun initAction() {

    }

    override fun initView() {
        mBinding.tvDate.text = mViewModel.getDairyData(this)
        mBinding.etContent.post {
            mBinding.etContent.requestFocus()
        }
    }


    companion object {

        private const val PARAM_TIMESTAMP = "param_timestamp"

        fun launchActivity(context: Context, timestamp: Long) {
            val intent = Intent(context, WritingActivity::class.java).apply {
                putExtra(PARAM_TIMESTAMP, timestamp)
            }
            context.startActivity(intent)
        }
    }
}