package com.gac.nioapp.test.car_maintenance.wrap

import android.text.TextUtils
import androidx.fragment.app.Fragment
import cn.gacnio.it.database.SPUtils
import com.gac.nioapp.test.bean.SelectOrderCarsBean
import com.gac.nioapp.test.car_maintenance.ICarHomeOrder
import com.gac.nioapp.test.enums.ScreenEnum
import com.gac.nioapp.test.extension.DataErrorNeedRetryException
import com.gac.nioapp.test.repository.CarOrderRepository
import com.gac.nioapp.test.utils.CarSPUtil
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.retry
import java.lang.ref.WeakReference

/**
 * @className CarHomeOrderImpl
 * @description TODO
 * @author heyufei
 * @since 2022/1/4 9:46 上午
 * @version 1.0
 */
class CarHomeOrderImpl private constructor() : ICarHomeOrder {
    private var orders = ArrayList<SelectOrderCarsBean>()
    private val CODE_LAST_ORDER_ID = "CODE_LAST_ORDER_ID"
    private var hostFragment: WeakReference<Fragment>? = null
    private var rootId = -1


    companion object {
        @JvmStatic
        val instance by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) { CarHomeOrderImpl() }
    }

    private val orderRepository by lazy {
        CarOrderRepository()
    }

    override suspend fun updateCarOrders() =
        flow<List<SelectOrderCarsBean>> {
            val os = orderRepository.refreshOrders()
            orders.clear()
            if (!os?.carOrders.isNullOrEmpty()) {
                orders.addAll(os!!.carOrders)
            } else {
                CarSPUtil.putCarOrderType(CarSPUtil.DEFAULT_NO_ORDER_CAR)
            }
            emit(orders)
        }.retry(2) { e ->
            e is DataErrorNeedRetryException
        }

    override fun hasCarOrder() = !this.orders.isNullOrEmpty()

    override fun showCarOrder(orderId: String?): Boolean {
        val fragment = hostFragment?.get() ?: return false
        fragment.context ?: return false

        var result: Boolean
        var orderItem: SelectOrderCarsBean? = null
        if (TextUtils.isEmpty(orderId)) {
            if (!orders.isNullOrEmpty()) {
                orderItem = orders[0]
                result = true
            } else {
                result = false
            }
        } else {
            orders.find { it.carOrderBaseId == orderId || "${it.carWishId}" == orderId }.run {
                if (this == null) {
                    result = false
                } else {
                    orderItem = this
                    result = true
                }
            }
        }
        orderItem ?: return false

        if (result) {
            if (!TextUtils.isEmpty(orderId)) {
                SPUtils.put(CODE_LAST_ORDER_ID, orderId)
            } else {
                SPUtils.put(CODE_LAST_ORDER_ID, orderItem!!.carOrderBaseId)
            }
            CarSPUtil.putCarOrderBaseId(orderItem!!.carOrderBaseId)
            CarSPUtil.putCarOrderType(orderItem!!.carOrderType)
            CarSPUtil.putCarWishId(orderItem!!.carWishId)
            CarSPUtil.putNoFileNameCarWishId(orderItem!!.carWishId)
            hostFragment?.get().run {
                if (this == null) {
                    return false
                }

                val tag =
                    "CAR_ORDER_STATUS_FRAGMENT_${orderItem?.carOrderBaseId}_${orderItem?.carWishId}"
                val fragment =
                    childFragmentManager.findFragmentByTag(tag)
                if (fragment == null) {
                    val params = mutableMapOf<String, Any>()

                    params.apply {
                        put("carOrderBaseId", orderItem!!.carOrderBaseId ?: "-1")
                        put("carOrderType", orderItem!!.carOrderType ?: -1)
                        put("carWishId", orderItem!!.carWishId ?: -1)
                    }

                    val carFragment = ReactNativeWrapService.instance
                        .createRNFragment(ScreenEnum.loveCar2, false, params)
                    childFragmentManager.beginTransaction()
                        .replace(
                            rootId,
                            //OrderStatusFragment.newInstance(),
                            carFragment,
                            tag
                        )
                        .commit()
                } else {
//                    (fragment as OrderStatusFragment).onVisible()
                }
            }
        }
        return result
    }


    override fun orderExist(orderId: String?): Boolean {
        if (TextUtils.isEmpty(orderId)) {
            return false
        }
        return orders.find { it.carOrderBaseId == orderId || "${it.carWishId}" == orderId } != null
    }

    override fun getLastShowCarOrderId(): String {
        return SPUtils.getString(CODE_LAST_ORDER_ID, "")
    }

    override fun logOut() {
        orders.clear()
        SPUtils.put(CODE_LAST_ORDER_ID, "")
    }

    override fun bindHostFragment(fragment: Fragment, root_id: Int) {
        hostFragment = WeakReference(fragment)
        rootId = root_id
    }

}