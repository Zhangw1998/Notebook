package com.zhangwww.newnotebook.util.viewbinding

import androidx.activity.ComponentActivity
import androidx.annotation.RestrictTo
import androidx.lifecycle.LifecycleOwner
import androidx.viewbinding.ViewBinding

@RestrictTo(RestrictTo.Scope.LIBRARY)
class ActivityViewBindingProperty<in A : ComponentActivity, out VB : ViewBinding>(viewBinder: ViewBinder<A, VB>) :
    LifecycleViewBindingProperty<A, VB>(viewBinder) {

    override fun getLifecycleOwner(thisRef: A): LifecycleOwner {
        return thisRef
    }
}