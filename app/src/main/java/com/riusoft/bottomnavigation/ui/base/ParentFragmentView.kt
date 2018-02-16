package com.riusoft.bottomnavigation.ui.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

interface ParentFragmentView : FragmentManager.OnBackStackChangedListener {
    fun loadChildFragment(fragment: Fragment)
    fun onCreateFragmentView(inflater: LayoutInflater?,
                             container: ViewGroup?,
                             savedInstanceState: Bundle?): View?
}