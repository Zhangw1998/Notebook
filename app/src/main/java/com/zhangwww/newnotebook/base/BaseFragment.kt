package com.zhangwww.newnotebook.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

/**
 * @Description:    项目Fragment的基类
 * @Author:         Zhangwww
 * @CreateDate:     2021/7/3 11:06
 * @UpdateUser:
 * @UpdateDate:     2021/7/3 11:06
 * @UpdateRemark:
 * @Version:        1.0
 */
abstract class BaseFragment(@LayoutRes layoutId: Int) : Fragment(layoutId) {

    open fun initView() { }

    open fun initData() { }

    open fun initAction() { }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initData()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initAction()
    }

}