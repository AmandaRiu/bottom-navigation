package com.riusoft.bottomnavigation.data

import android.util.Log
import com.riusoft.bottomnavigation.data.model.db.OrderModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppDataManager @Inject constructor() {
    init {
        Log.d("AMANDA-TEST", "AppDataManager created")
    }

   fun getOrders(): MutableList<OrderModel> {
        val o1 = OrderModel("Amanda Riu", 10.75F)
        val o2 = OrderModel("Jason Pressley", 15.20F)
        val o3 = OrderModel("Olivia John", 1.10F)
        val o4 = OrderModel("Jesse Cunningham", 55.10F)
        val o5 = OrderModel("Felicia Bricks", 13.20F)

        return mutableListOf(o1, o2, o3, o4, o5)
    }
}
