package com.zhangwww.newnotebook.util.viewbinding

import androidx.annotation.MainThread
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty

interface ViewBindingProperty<in R : Any, out VB : ViewBinding> : ReadOnlyProperty<R, VB> {
    @MainThread
    fun clear()
}