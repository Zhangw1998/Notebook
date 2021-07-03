package com.zhangwww.newnotebook.util.viewbinding

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.ComponentActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

typealias ViewBinder<R, VB> = (R) -> VB

@JvmName("viewBindingActivity")
inline fun <A : ComponentActivity, VB : ViewBinding> viewBinding(
    crossinline viewBinder: ViewBinder<LayoutInflater, VB>,
): ViewBindingProperty<A, VB> = ActivityViewBindingProperty { activity: A ->
    viewBinder(activity.layoutInflater)
}

inline fun <A : ComponentActivity, VB : ViewBinding> viewBinding(
    crossinline viewBinder: ViewBinder<View, VB>,
): ViewBindingProperty<A, VB> = ActivityViewBindingProperty { activity: A ->
    viewBinder(findRootView(activity))
}

@JvmName("viewBindingFragment")
inline fun <F : Fragment, VB : ViewBinding> Fragment.viewBinding(
    crossinline viewBinder: (View) -> VB
): ViewBindingProperty<F, VB> = FragmentViewBindingProperty { fragment: F ->
    viewBinder(fragment.requireView())
}

@JvmName("viewBindingDialogFragment")
inline fun <F : DialogFragment, VB : ViewBinding> DialogFragment.viewBinding(
    crossinline viewBinder: (View) -> VB
): ViewBindingProperty<F, VB> = DialogFragmentViewBindingProperty { fragment: F ->
    viewBinder(fragment.requireView())
}

fun findRootView(activity: Activity): View {
    val contentView = activity.findViewById<ViewGroup>(android.R.id.content)
    checkNotNull(contentView) { "Activity has no content view" }
    return when (contentView.childCount) {
        1 -> contentView.getChildAt(0)
        0 -> error("Content view has no children. Provide root view explicitly")
        else -> error("More than one child view found in Activity content view")
    }
}