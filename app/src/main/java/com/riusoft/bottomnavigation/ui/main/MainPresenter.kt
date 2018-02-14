package com.riusoft.bottomnavigation.ui.main

import javax.inject.Inject

class MainPresenter @Inject constructor() : MainContract.Presenter {
    override var viewRef: MainContract.View? = null
}