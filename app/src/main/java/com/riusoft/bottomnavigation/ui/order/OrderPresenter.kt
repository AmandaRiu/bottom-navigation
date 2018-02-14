package com.riusoft.bottomnavigation.ui.order

import com.riusoft.bottomnavigation.data.AppDataManager
import javax.inject.Inject

class OrderPresenter @Inject constructor(dataMgr: AppDataManager)
    : OrderContract.Presenter(dataMgr) {

    private var viewRef: OrderContract.View? = null

    override fun takeView(view: OrderContract.View) {
        viewRef = view
    }

    override fun dropView() {
        viewRef = null
    }
}