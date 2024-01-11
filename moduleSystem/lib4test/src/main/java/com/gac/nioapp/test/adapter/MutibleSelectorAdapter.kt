package com.gac.nioapp.test.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.gac.nioapp.test.R
import com.gac.nioapp.test.bean.MutibleSelectorBean

/**
 * @ClassName MutibleSelectorAdapter
 * @Description TODO
 * @Author LiuZhiQiang
 * @Date 2022/4/13 10:07
 */
class MutibleSelectorAdapter(data: List<MutibleSelectorBean>) : BaseQuickAdapter<MutibleSelectorBean, BaseViewHolder>(
    R.layout.dialog_bottom_mutible_selector_list_item, data) {

    /**
     * Implement this method and use the helper to adapt the view to the given item.
     *
     * @param helper A fully initialized helper.
     * @param item   The item that needs to be displayed.
     */
    override fun convert(helper: BaseViewHolder, item: MutibleSelectorBean) {
        helper.setText(R.id.tv__item, item.text)
        helper.setVisible(R.id.iv__item, item.isSelect)
        helper.addOnClickListener(R.id.ll_item)
    }


}