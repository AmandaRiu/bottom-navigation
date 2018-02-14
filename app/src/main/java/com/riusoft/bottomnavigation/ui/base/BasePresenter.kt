package com.riusoft.bottomnavigation.ui.base

interface BasePresenter<T> {
    var viewRef: T?

    fun takeView(view: T) {
        viewRef = view
    }

    fun dropView() {
        viewRef = null
    }
}
