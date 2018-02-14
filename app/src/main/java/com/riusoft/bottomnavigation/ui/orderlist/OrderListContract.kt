package com.riusoft.bottomnavigation.ui.orderlist

import com.riusoft.bottomnavigation.data.model.db.OrderModel
import com.riusoft.bottomnavigation.ui.base.BasePresenter
import com.riusoft.bottomnavigation.ui.base.BaseView
import com.riusoft.bottomnavigation.ui.base.FragmentListener

interface OrderListContract {
    interface Presenter : BasePresenter<View> {
        fun loadOrders()
        fun openOrderDetail(detail: OrderModel)
        fun setFragmentListener(listener: FragmentListener?)
    }

    interface View : BaseView<Presenter> {
        fun displayOrders(orders: List<OrderModel>)
    }
}
