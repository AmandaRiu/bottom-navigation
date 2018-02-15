package com.riusoft.bottomnavigation.ui.order


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.riusoft.bottomnavigation.R
import com.riusoft.bottomnavigation.data.model.db.OrderModel
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_order.*
import javax.inject.Inject

class OrderFragment : Fragment(), OrderContract.View {

    companion object {
        val TAG: String = OrderFragment::class.java.simpleName
        const val FIELD_NAME = "name"
        const val FIELD_TOTAL = "total"

        fun newInstance(order: OrderModel): OrderFragment {
            val args = Bundle()
            args.putString(FIELD_NAME, order.name)
            args.putFloat(FIELD_TOTAL, order.orderTotal)
            val fragment = OrderFragment()
            fragment.arguments = args
            return fragment
        }
    }

    @Inject lateinit var presenter: OrderContract.Presenter

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        activity.title = getString(R.string.order_detail)
        return inflater?.inflate(R.layout.fragment_order, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (savedInstanceState == null) {
            val args: Bundle = arguments
            txt_name.text = args.getString(FIELD_NAME, "")
            txt_orderTotal.text = args.getFloat(FIELD_TOTAL, 0F).toString()
        }
        presenter.takeView(this)
    }

    override fun onDestroyView() {
        presenter.dropView()
        super.onDestroyView()
    }
}
