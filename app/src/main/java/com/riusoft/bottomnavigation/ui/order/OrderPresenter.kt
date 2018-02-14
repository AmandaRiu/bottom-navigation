package com.riusoft.bottomnavigation.ui.order

import com.riusoft.bottomnavigation.data.AppDataManager
import javax.inject.Inject

class OrderPresenter @Inject constructor(dataMgr: AppDataManager) : OrderContract.Presenter {
    override var viewRef: OrderContract.View? = null
}