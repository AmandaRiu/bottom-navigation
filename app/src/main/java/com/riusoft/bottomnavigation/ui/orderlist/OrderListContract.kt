package com.riusoft.bottomnavigation.ui.orderlist

import com.riusoft.bottomnavigation.data.model.db.OrderModel
import com.riusoft.bottomnavigation.ui.base.BasePresenter
import com.riusoft.bottomnavigation.ui.base.BaseView
import com.riusoft.bottomnavigation.ui.base.ParentFragmentView

interface OrderListContract {
    interface Presenter : BasePresenter<View> {
        fun loadOrders(refresh: Boolean)
        fun openOrderDetail(detail: OrderModel)
    }

    interface View : ParentFragmentView, BaseView<Presenter> {
        fun displayOrders(orders: List<OrderModel>)
    }
}
