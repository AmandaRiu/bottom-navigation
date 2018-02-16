package com.riusoft.bottomnavigation.ui.orderlist

import android.content.Context
import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.riusoft.bottomnavigation.R
import com.riusoft.bottomnavigation.data.model.db.OrderModel
import com.riusoft.bottomnavigation.ui.base.ParentFragment
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_orderlist.*
import javax.inject.Inject

class OrderListFragment : ParentFragment(),
        OrderListContract.View, FragmentManager.OnBackStackChangedListener {

    companion object {
        val TAG: String = OrderListFragment::class.java.simpleName
        fun newInstance() = OrderListFragment()
    }

    @Inject lateinit var presenter: OrderListContract.Presenter
    @Inject lateinit var ordersAdapter: OrderListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("AMANDA-TEST", "orders: onCreate")
    }

    override fun onCreateFragmentView(inflater: LayoutInflater?,
                                      container: ViewGroup?,
                                      savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_orderlist, container, false)
    }

    override fun onAttach(context: Context?) {
        Log.d("AMANDA-TEST", "orders: onAttach")
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("AMANDA-TEST", "orders: onViewCreated")
        topView.adapter = ordersAdapter
        presenter.takeView(this)
    }

    override fun onDestroyView() {
        Log.d("AMANDA-TEST", "orders: onDestroyView")
        presenter.dropView()
        super.onDestroyView()
    }

    override fun onDetach() {
        super.onDetach()
        Log.d("AMANDA-TEST", "orders: onDetach")
    }

    override fun onDestroy() {
        Log.d("AMANDA-TEST", "orders: onDestroy")
        super.onDestroy()
    }

    override fun displayOrders(orders: List<OrderModel>) {
        ordersAdapter.setOrders(orders)
    }
}
