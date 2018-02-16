package com.riusoft.bottomnavigation.ui.products

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("AMANDA-TEST", "products: onCreate")
    }

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
        Log.d("AMANDA-TEST", "products: onAttach")
    }

    override fun onCreateView(inflater: LayoutInflater?,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        activity.title = getString(R.string.title_products)
        Log.d("AMANDA-TEST", "products: onCreateView")
        return inflater?.inflate(R.layout.fragment_products, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("AMANDA-TEST", "products: onViewCreated")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("AMANDA-TEST", "products: onDestroyView")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d("AMANDA-TEST", "products: onDetach")
    }

    override fun onDestroy() {
        Log.d("AMANDA-TEST", "products: onDestroy")
        super.onDestroy()
    }
}
