package com.riusoft.bottomnavigation.ui.orderlist

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.riusoft.bottomnavigation.R
import com.riusoft.bottomnavigation.data.model.db.OrderModel
import com.riusoft.bottomnavigation.ui.base.FragmentListener
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_orderlist.*
import javax.inject.Inject

class OrderListFragment : Fragment(), OrderListContract.View {

    companion object {
        val TAG: String = OrderListFragment::class.java.simpleName
        fun newInstance() = OrderListFragment()
    }

    @Inject lateinit var presenter: OrderListContract.Presenter

    @Inject lateinit var ordersAdapter: OrderListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d("AMANDA-TEST", "OrderListFragment created")
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        activity.title = getString(R.string.title_orders)
        return inflater!!.inflate(R.layout.fragment_orderlist, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listView.adapter = ordersAdapter
    }

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
        presenter.takeView(this)
        if (context is FragmentListener) {
            presenter.setFragmentListener(context)
        }
    }

    override fun onDetach() {
        super.onDetach()
        presenter.dropView()
        presenter.setFragmentListener(null)
    }

    override fun displayOrders(orders: List<OrderModel>) {
        ordersAdapter.setOrders(orders)
    }
}
