package com.riusoft.bottomnavigation.ui.orderlist

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.riusoft.bottomnavigation.R
import com.riusoft.bottomnavigation.data.model.db.OrderModel

class OrderListAdapter constructor(private val orders: MutableList<OrderModel>,
                                   val presenter: OrderListContract.Presenter)
    : BaseAdapter() {

    init {
        Log.d("AMANDA-TEST", "orderListAdapter: created")
    }

    fun setOrders(items: List<OrderModel>) {
        orders.clear()
        orders.addAll(items)
        notifyDataSetChanged()
    }

    override fun getItem(position: Int): Any = orders[position]

    override fun getCount(): Int = orders.count()

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        var view: View? = convertView
        val viewHolder: ViewHolder?
        val order: OrderModel = getItem(position) as OrderModel

        if (view == null) {
            view = LayoutInflater.from(parent!!.context).inflate(R.layout.view_order_list_item, parent, false)
            viewHolder = ViewHolder(view, order)
            view.tag= viewHolder
        } else {
            viewHolder = view.tag as ViewHolder
        }
        view?.setOnClickListener {
            val holder: ViewHolder = it.tag as ViewHolder
            val o: OrderModel = holder.order
            presenter.openOrderDetail(o)
        }

        viewHolder.tvName.text = order.name
        viewHolder.tvTotal.text = order.orderTotal.toString()

        return view!!
    }

    private class ViewHolder(view: View, val order: OrderModel) {

        val tvName: TextView = view.findViewById(R.id.fullName)
        val tvTotal: TextView = view.findViewById(R.id.orderTotal)
    }
}
