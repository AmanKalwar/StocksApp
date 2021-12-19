package com.example.kaninistocks.LoginRegister

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.kaninistocks.*
import com.example.kaninistocks.APIs.ApiClients
import com.example.kaninistocks.userData.*

import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.StringBuilder

class LoginActivity : AppCompatActivity() {
    private lateinit var sessionManager: SessionManager
    //private lateinit var apiClient: ApiClient
    lateinit var apiClients: ApiClients

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //apiClient = ApiClient()
        sessionManager = SessionManager(this)

       apiClients = ApiClients()

       // val newScreenActivity=Intent(this,MainActivity::class.java)


        val loginMessage = findViewById<TextView>(R.id.loginMessage)

        val login =findViewById<TextView>(R.id.loginButton)
        val register = findViewById<TextView>(R.id.redirectToRegister)

        val userEmail =findViewById<TextInputLayout>(R.id.emailLayout)
        val userPassword = findViewById<TextInputLayout>(R.id.passwordLayout)
        var isScreenChange:String = ""

        //val myApplication = application as MyApplication
        //val httpApiService = myApplication.httpApiService



        login.setOnClickListener{
            //loginMessage.text=userPassword.editText?.text.toString()

            if(userEmail.editText?.text.toString()=="" || userPassword.editText?.text.toString()==""){
                loginMessage.text="Please enter both fields"

            }
            else{
                loginUser(userEmail.editText?.text.toString(),userPassword.editText?.text.toString())

            }

            /*if(sessionManager.fetchAuthToken()!=null){

                loginMessage.text=sessionManager.fetchAuthToken()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)

            }*/


        }

            register.setOnClickListener {
                    val intent = Intent(this, RegisterActivity::class.java)

                    startActivity(intent)
            }

            }

     fun loginUser(email:String,password:String) {
        val newLogin = LoginRequestData(email,password)
        val loginMessage=findViewById<TextView>(R.id.loginMessage)

        CoroutineScope(Dispatchers.IO).launch{

            val retrofitData = apiClients.getApiService().loginUser(newLogin)
            retrofitData?.enqueue(object : Callback<LoginRespondData?> {
                override fun onResponse(call: Call<LoginRespondData?>?, response: Response<LoginRespondData?>) {
                    if(response.code()==200 ){
                        val respondBody=response.body()!!

                        val myStringBuilder= StringBuilder()
                        myStringBuilder.append(respondBody.email)
                        val em:String=respondBody.token
                        loginMessage.text=em
                        sessionManager.saveAuthToken(respondBody.token,respondBody.email,respondBody.memberSince)
                        callMainActivity()
                    }else{
                        loginMessage.text="no user"
                    }

                }

                override fun onFailure(call: Call<LoginRespondData?>, t: Throwable) {
                    //Log.d("MainActivity","onFailure: "+t.message)

                }
            }

            )

        }

    }

     fun callMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)

    }


}