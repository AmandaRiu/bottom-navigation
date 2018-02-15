package com.riusoft.bottomnavigation.ui.products

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.riusoft.bottomnavigation.R
import dagger.android.support.AndroidSupportInjection

class ProductsFragment : Fragment() {

    companion object {
        val TAG: String = ProductsFragment::class.java.simpleName
        fun newInstance() = ProductsFragment()
    }

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater?,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        activity.title = getString(R.string.title_products)
        return inflater?.inflate(R.layout.fragment_products, container, false)
    }
}
