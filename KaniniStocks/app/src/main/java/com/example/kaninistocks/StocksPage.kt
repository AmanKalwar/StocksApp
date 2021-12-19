package com.example.kaninistocks

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.kaninistocks.databinding.ActivityStocksPageBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class StocksPage : AppCompatActivity() {
    lateinit var session:SessionManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stocks_page)
        val img=findViewById<ImageView>(R.id.images)
        val type=findViewById<TextView>(R.id.itemtype)
        val price=findViewById<TextView>(R.id.itemprice)

        val itemName=findViewById<TextView>(R.id.ItemName)
        val content=findViewById<TextView>(R.id.content)


        val create=findViewById<Button>(R.id.Createorder)
        var text=intent.getIntExtra("ID",0)
        session= SessionManager(this)
        var token=session.fetchAuthToken()
        var apiClient=application as MyApplication
        CoroutineScope(Dispatchers.IO).launch {
            val result=apiClient.apiService.GetStocks("Bearer "+token)
            var i=0
            var res2:StockitemData?=null
            if(result.isSuccessful){
                while(i<result.body()?.stocks!!.size){
                    if(result.body()?.stocks!![i].id==text){
                        res2=result.body()?.stocks!![i]
                        break
                    }
                    i+=1
                }
            }
            else{

            }
            withContext(Dispatchers.Main){
                Picasso.get().load(res2?.url).into(img);
                type.text=res2?.name
                price.text="${res2?.price}"
                itemName.text=res2?.name
                content.text=res2?.description
            }
        }
        create.setOnClickListener{
            var intent= Intent(this,ConfirmOrders::class.java)
            intent.putExtra("Id",text)
            startActivity(intent)
        }

    }
}