package com.example.kaninistocks

import android.content.Intent
import android.graphics.Camera
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.system.Os.close
import android.system.Os.open
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kaninistocks.APIs.ApiClient
import com.example.kaninistocks.LoginRegister.LoginActivity
import com.google.android.material.navigation.NavigationView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.nio.channels.AsynchronousFileChannel.open

class MainActivity : AppCompatActivity() {
    /*private lateinit var sessionManager: SessionManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sessionManager = SessionManager(this)*/


      /* *//* val profileButton = findViewById<Button>(R.id.profileButton)
        val stock = findViewById<Button>(R.id.stockButton)
        val te =findViewById<TextView>(R.id.textView16)*//*
        te.text=sessionManager.fetchAuthToken()


        profileButton.setOnClickListener{
            val intent = Intent(this, userProfile::class.java)
            startActivity(intent)

        }

        stock.setOnClickListener{
            val intent = Intent(this, StockBuyingActivity::class.java)
            startActivity(intent)

        }*/
    lateinit var toggle: ActionBarDrawerToggle
    lateinit var drawerLayout: DrawerLayout


    private lateinit var session: SessionManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val profile=findViewById<TextView>(R.id.DisplayUserEmail)
        // profile.text="user@gmail.com"
        drawerLayout=findViewById(R.id.drawerLayout)

        val intent1=Intent(this,userProfile::class.java)
        val order= Intent(this,MyStockActivity::class.java)

        val otheruser=Intent(this,OtherUserActivity::class.java)
        val pic=Intent(this, Camera::class.java)
        val navView=findViewById<NavigationView>(R.id.navView)

        //   val imgages=findViewById<ImageButton>(R.id.imageView)
        toggle= ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()


        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        /* profile.text="${session.FetchUserID()}"*/
        navView.setNavigationItemSelectedListener {

            when(it.itemId) {

                R.id.profile -> {startActivity(intent1)}
                R.id.otherusers-> {startActivity(otheruser)}
                R.id.profilepic->{startActivity(pic)}
                R.id.order->{startActivity(order)}

            }
            true
        }
        val string=intent.getStringExtra("string")

        val apiclient=application as MyApplication
        session=SessionManager(this)
        var intent= Intent(this,LoginActivity::class.java)

        var token=session.fetchAuthToken()

        val items:MutableList<StockitemData> = mutableListOf<StockitemData>()
        if(session.fetchAuthToken()!=null){
            CoroutineScope(Dispatchers.IO).launch{
                val result=apiclient.apiService.GetStocks("Bearer "+token)
                var i=0
                if(result.isSuccessful){
                    while(i<result.body()?.stocks!!.size){
                        items.add(result.body()?.stocks!![i])
                        i+=1
                    }
                }
                else{
                    startActivity(intent)
                }
                withContext(Dispatchers.Main){

                    val recycle=findViewById<RecyclerView>(R.id.recycle)
                    recycle.adapter=AdapterClass(items,this@MainActivity)
                    recycle.layoutManager= LinearLayoutManager(this@MainActivity)
                }

            }
        }
        else
            startActivity(intent)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){

            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
