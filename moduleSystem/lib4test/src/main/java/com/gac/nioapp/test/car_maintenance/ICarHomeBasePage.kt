package com.gac.nioapp.test.car_maintenance

import androidx.fragment.app.Fragment

/**
 * @className ICarHomeBasePage
 * @description TODO
 * @author heyufei
 * @since 2022/1/4 9:22 上午
 * @version 1.0
 */
interface ICarHomeBasePage {

    /**
     * 宿主fragment与root id
     * root_id:用于替换待显示的fragment
     */
    fun bindHostFragment(fragment: Fragment, root_id: Int)

}