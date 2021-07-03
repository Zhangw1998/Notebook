package com.zhangwww.newnotebook.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.BarUtils

/**
 * @Description:    项目中Activity的基类
 * @Author:         Zhangwww
 * @CreateDate:     2021/7/3 11:02
 * @UpdateUser:
 * @UpdateDate:     2021/7/3 11:02
 * @UpdateRemark:
 * @Version:        1.0
 */
abstract class BaseActivity(@LayoutRes layoutId: Int = 0) : AppCompatActivity(layoutId) {

    protected open fun initData() {}

    protected open fun initView() {}

    protected open fun initAction() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        BarUtils.transparentStatusBar(this)
        initData()
        initView()
        initAction()
    }
}