package com.riusoft.bottomnavigation.ui.base

import android.support.v4.app.Fragment

interface FragmentListener {
    fun loadChildFragment(fragment: Fragment, tag: String)
}
