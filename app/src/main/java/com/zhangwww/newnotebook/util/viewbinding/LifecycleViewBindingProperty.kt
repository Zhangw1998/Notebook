package com.zhangwww.newnotebook.util.viewbinding

import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.annotation.MainThread
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import androidx.viewbinding.ViewBinding
import kotlin.reflect.KProperty

abstract class LifecycleViewBindingProperty<in R : Any, out VB : ViewBinding>(private val viewBinder: ViewBinder<R, VB>) :
    ViewBindingProperty<R, VB> {

    private var mViewBinding: VB? = null

    protected abstract fun getLifecycleOwner(thisRef: R): LifecycleOwner

    @MainThread
    override fun getValue(thisRef: R, property: KProperty<*>): VB {
        mViewBinding?.let { return it }
        val lifecycle = getLifecycleOwner(thisRef).lifecycle
        val viewBinding = viewBinder(thisRef)
        if (lifecycle.currentState == Lifecycle.State.DESTROYED) {
            Log.w(
                TAG, "Access to viewBinding after Lifecycle is destroyed or hasn't created yet. " +
                        "The instance of viewBinding will be not cached."
            )
        } else {
            lifecycle.addObserver(ClearOnDestroyLifecycleObserver(this))
            this.mViewBinding = viewBinding
        }
        return viewBinding
    }

    @MainThread
    override fun clear() {
        mViewBinding = null
    }

    private class ClearOnDestroyLifecycleObserver(private val property: LifecycleViewBindingProperty<*, *>) :
        LifecycleObserver {

        @MainThread
        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        fun onDestroy(owner: LifecycleOwner) {
            mainHandler.post { property.clear() }
        }

        private companion object {
            private val mainHandler = Handler(Looper.getMainLooper())
        }
    }

    companion object {
        private const val TAG = "ViewBindingProperty"
    }
}