package com.riusoft.bottomnavigation.ui.order

import com.riusoft.bottomnavigation.ui.base.BasePresenter
import com.riusoft.bottomnavigation.ui.base.BaseView

interface OrderContract {
    interface Presenter : BasePresenter<View>

    interface View : BaseView<Presenter>
}