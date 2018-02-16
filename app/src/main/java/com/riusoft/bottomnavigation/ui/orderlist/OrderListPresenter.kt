package com.riusoft.bottomnavigation.ui.orderlist

import android.util.Log
import com.riusoft.bottomnavigation.data.AppDataManager
import com.riusoft.bottomnavigation.data.model.db.OrderModel
import com.riusoft.bottomnavigation.ui.order.OrderFragment
import javax.inject.Inject

class OrderListPresenter @Inject constructor(val dataMgr: AppDataManager)
    : OrderListContract.Presenter {

    init {
        Log.d("AMANDA-TEST", "OrderListPresenter created")
    }

    override var viewRef: OrderListContract.View? = null

    override fun takeView(view: OrderListContract.View) {
        viewRef = view
        loadOrders(false)
    }

    override fun loadOrders(refresh: Boolean) {
        Log.d("AMANDA-TEST", "orderListPresenter: loadOrders")
        viewRef?.displayOrders(dataMgr.getOrders())
    }

    override fun openOrderDetail(detail: OrderModel) {
        val fragment = OrderFragment.newInstance(detail)
        viewRef?.loadChildFragment(fragment)
    }
}
