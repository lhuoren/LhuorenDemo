package com.gac.nioapp.test.kit.impl.business_test.park

import android.app.Activity
import com.gac.nioapp.test.R
import com.gac.nioapp.test.car_maintenance.wrap.CarMaintenanceWrapService
import com.gac.nioapp.test.kit.HycanAbstractKit
import com.gac.nioapp.test.kit.IDeveloperKit
import com.google.auto.service.AutoService

/**
 * @className ParkOutMock
 * @description
 * 模拟泊入
 * @author heyufei
 * @since 2021/12/6 2:11 下午
 * @version 1.0
 */
@AutoService(IDeveloperKit::class)
class SpaPark : IDeveloperKit {
    override fun getAbstractKit() = object : HycanAbstractKit() {
        override val name: Int
            get() = R.string.park_spa

        override fun onClickWithReturn(activity: Activity): Boolean {
            CarMaintenanceWrapService.instance.startSpaRemoteControlParking()
            return true
        }
    }
}