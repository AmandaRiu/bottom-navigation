package com.riusoft.bottomnavigation.ui.dashboard

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.riusoft.bottomnavigation.R
import dagger.android.support.AndroidSupportInjection

class DashboardFragment : Fragment() {

    companion object {
        val TAG: String = DashboardFragment::class.java.simpleName
        fun newInstance() = DashboardFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
        Log.d("AMANDA-TEST", "dashboard: onCreate")
    }

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        Log.d("AMANDA-TEST", "dashboard: onAttach")
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        activity.title = getString(R.string.title_dashboard)
        Log.d("AMANDA-TEST", "dashboard: onCreateView")
        return inflater?.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("AMANDA-TEST", "dashboard: onDestroyView")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d("AMANDA-TEST", "dashboard: onDetach")
    }

    override fun onDestroy() {
        Log.d("AMANDA-TEST", "dashboard: onDestroy")
        super.onDestroy()
    }
}
