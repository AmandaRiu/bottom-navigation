package com.riusoft.bottomnavigation.ui.main

import android.support.v4.app.Fragment
import com.riusoft.bottomnavigation.data.AppDataManager
import com.riusoft.bottomnavigation.ui.base.BasePresenter
import com.riusoft.bottomnavigation.ui.base.BaseView

interface MainContract {
    abstract class Presenter(dataMgr: AppDataManager)
        : BasePresenter<View>(dataMgr)

    interface View : BaseView<Presenter> {
        fun loadChildFragment(fragment: Fragment, tag: String)
    }
}