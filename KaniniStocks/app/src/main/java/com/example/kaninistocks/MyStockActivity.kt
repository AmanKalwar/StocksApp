package com.example.kaninistocks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MyStockActivity : AppCompatActivity() {
    lateinit var session:SessionManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_stock)
        var myApplication=application as MyApplication
        session= SessionManager(this)
        var stocks1:MutableList<stock> = mutableListOf<stock>()
        var token=session.fetchAuthToken()
        CoroutineScope(Dispatchers.IO).launch{

            var result=myApplication.apiService.fetchOwnings("Bearer "+token)
            if(result.isSuccessful) {
                var i = 0
                while(i<result.body()!!.stockOrders!!.size){
                    var res=myApplication.apiService.GetStocks("Bearer "+token)
                    var j=0
                    while(j<res.body()!!.stocks!!.size){
                        if(result.body()!!.stockOrders!![i].stockid==res.body()!!.stocks!![j].id)
                            stocks1.add(
                                stock(
                                    res.body()!!.stocks!![j].id.toString(),
                                    res.body()!!.stocks!![j].url,
                                    res.body()!!.stocks!![j].name,
                                    res.body()!!.stocks!![j].price,
                                    result.body()!!.stockOrders!![i].count!!,
                                    result.body()!!.stockOrders!![i].stockid
                                )
                            )
                        j+=1

                    }
                    i+=1
                }

            }
            withContext(Dispatchers.Main){
                var recycle=findViewById<RecyclerView>(R.id.recyclerr)
                recycle.adapter=OrdersAdapterClass(stocks1,this@MyStockActivity,application)
                recycle.layoutManager= LinearLayoutManager(this@MyStockActivity)
            }
        }

    }
}
