package com.riusoft.bottomnavigation.ui.order

import com.riusoft.bottomnavigation.data.AppDataManager
import com.riusoft.bottomnavigation.ui.base.BasePresenter
import com.riusoft.bottomnavigation.ui.base.BaseView

interface OrderContract {
    abstract class Presenter(dataMgr: AppDataManager)
        : BasePresenter<View>(dataMgr) {

    }

    interface View : BaseView<Presenter>
}