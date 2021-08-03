package com.zhangwww.newnotebook.ui.home.home

import androidx.fragment.app.viewModels
import com.blankj.utilcode.util.BarUtils
import com.leochuan.CenterSnapHelper
import com.leochuan.ScaleLayoutManager
import com.zhangwww.newnotebook.R
import com.zhangwww.newnotebook.base.BaseFragment
import com.zhangwww.newnotebook.databinding.FragmentHomeBinding
import com.zhangwww.newnotebook.extensions.getCompatColor
import com.zhangwww.newnotebook.ui.home.HomeViewModel
import com.zhangwww.newnotebook.util.viewbinding.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment(R.layout.fragment_home) {

    private val mBinding by viewBinding(FragmentHomeBinding::bind)

    private val mViewModel by viewModels<HomeViewModel>()

    override fun initView() {
        BarUtils.setStatusBarColor(
            requireActivity().window,
            requireContext().getCompatColor(R.color.colorBackground)
        )
        BarUtils.setStatusBarLightMode(requireActivity().window, true)
        mBinding.rvDay.apply {
            val dataList = mViewModel.getDateList()
            adapter = DateAdapter().apply {
                addData(dataList)
                setSelectItem(dataList.size - 1)
                onItemClick = {
                    mViewModel.setSelectDay(it)
                }
            }
            scrollToPosition(dataList.size - 1)
        }

        mBinding.rvDiaryCategory.apply {
            adapter = DiaryCategoryAdapter().apply {
                addData(mViewModel.getCategoryList())
                onItemClick = {

                }
            }
            layoutManager = ScaleLayoutManager.Builder(context, 20)
                .setMinScale(0.8f)
                .build()
            CenterSnapHelper().attachToRecyclerView(this)
        }
    }

    override fun initAction() {
        mViewModel.yearLiveData.observe(this) {
            mBinding.tvDate.text =
                getString(R.string.yearAndMonth, it, mViewModel.monthLiveData.value)
        }
        mViewModel.monthLiveData.observe(this) {
            mBinding.tvDate.text =
                getString(R.string.yearAndMonth, mViewModel.yearLiveData.value, it)
        }
        mViewModel.dayLiveData.observe(this) {
            mBinding.tvTitleHome.text = mViewModel.getTitle(requireContext(), it)
        }
        mBinding.flCreateContainer.setOnClickListener {
            // todo 新增日记
        }
    }

}