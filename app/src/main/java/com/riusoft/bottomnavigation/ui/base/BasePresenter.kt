package com.riusoft.bottomnavigation.ui.base

import com.riusoft.bottomnavigation.data.AppDataManager

abstract class BasePresenter<in T> constructor(val dataManager: AppDataManager) {
    abstract fun takeView(view: T)
    abstract fun dropView()
}
