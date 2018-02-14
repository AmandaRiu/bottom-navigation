package com.riusoft.bottomnavigation.ui.orderlist

import com.riusoft.bottomnavigation.data.AppDataManager
import com.riusoft.bottomnavigation.data.model.db.OrderModel
import com.riusoft.bottomnavigation.ui.base.BasePresenter
import com.riusoft.bottomnavigation.ui.base.BaseView
import com.riusoft.bottomnavigation.ui.base.FragmentListener

interface OrderListContract {
    abstract class Presenter(dataMgr: AppDataManager)
        : BasePresenter<View>(dataMgr) {

        abstract fun loadOrders()

        abstract fun openOrderDetail(detail: OrderModel)

        abstract fun setFragmentListener(listener: FragmentListener?)
    }

    interface View : BaseView<Presenter> {
        fun displayOrders(orders: List<OrderModel>)
    }
}
