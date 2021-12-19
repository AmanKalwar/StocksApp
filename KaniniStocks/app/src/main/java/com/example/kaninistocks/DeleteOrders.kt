package com.example.kaninistocks

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.kaninistocks.databinding.ActivityDeleteOrdersBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DeleteOrders : AppCompatActivity() {
    lateinit var session:SessionManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete_orders)
        var apiClient=application as MyApplication
        var orderid=intent.getIntExtra("orderid",0)
        var intentt= Intent(this,MyStockActivity::class.java)
        var token=SessionManager(this).fetchAuthToken()
        CoroutineScope(Dispatchers.IO).launch {
            var result=apiClient.apiService.DeleteOwnings("Bearer "+token,orderid)
            withContext(Dispatchers.Main){
                if(result.isSuccessful){
                    Toast.makeText(this@DeleteOrders,"Deleted Successfully", Toast.LENGTH_LONG).show()
                    startActivity(intentt)
                }
                else{
                    Toast.makeText(this@DeleteOrders,"Delete not Successfull", Toast.LENGTH_LONG).show()
                    startActivity(intentt)
                }
            }
        }
    }
}