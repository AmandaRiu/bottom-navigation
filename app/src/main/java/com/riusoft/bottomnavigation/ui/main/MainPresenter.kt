package com.riusoft.bottomnavigation.ui.main

import com.riusoft.bottomnavigation.data.AppDataManager
import javax.inject.Inject

class MainPresenter @Inject constructor(dataManager: AppDataManager) : MainContract.Presenter(dataManager) {

    private var mainView: MainContract.View? = null

    override fun takeView(view: MainContract.View) {
        mainView = view
    }

    override fun dropView() {
        mainView = null
    }
}