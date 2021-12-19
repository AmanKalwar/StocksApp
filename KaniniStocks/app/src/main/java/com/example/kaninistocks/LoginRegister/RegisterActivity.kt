package com.example.kaninistocks.LoginRegister

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.kaninistocks.APIs.ApiClients
import com.example.kaninistocks.MainActivity
import com.example.kaninistocks.R
import com.example.kaninistocks.SessionManager
import com.example.kaninistocks.userData.LoginRequestData
import com.example.kaninistocks.userData.LoginRespondData

import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {
    lateinit var apiClients: ApiClients
    private lateinit var sessionManager: SessionManager
    private lateinit var loginActivity: LoginActivity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        apiClients = ApiClients()
        loginActivity = LoginActivity()
        sessionManager= SessionManager(this)


        val newRegister = findViewById<TextView>(R.id.registerButton)
        val newEmail = findViewById<TextInputLayout>(R.id.emailLayoutRegister)
        val newPassword = findViewById<TextInputLayout>(R.id.passwordLayoutRegister)

        val registerResult = findViewById<TextView>(R.id.msgDisplay)

        newRegister.setOnClickListener {

            if(newEmail.editText?.text.toString()=="" || newPassword.editText?.text.toString()==""){
                registerResult.text="Please enter both fields"

            }
            else{
                val newLogin = LoginRequestData(newEmail.editText?.text.toString(),newPassword.editText?.text.toString())
                CoroutineScope(Dispatchers.IO).launch{
                    val retrofitData = apiClients.getApiService().rigister(newLogin)
                    retrofitData?.enqueue(object : Callback<Void?> {
                        override fun onResponse(call: Call<Void?>?, response: Response<Void?>) {
                            if(response.code()==200 ){
                                registerResult.text="Registration Success"
                                loginUser(newEmail.editText?.text.toString(),newPassword.editText?.text.toString())
                            }else{
                                registerResult.text="User already exists"
                            }
                        }
                        override fun onFailure(call: Call<Void?>, t: Throwable) {
                            //Log.d("MainActivity","onFailure: "+t.message)

                        }
                    }

                    )

                }


            }


        }



        }

    private fun loginUser(email: String, password: String) {
        val newLogin = LoginRequestData(email,password)
        val retrofitData = apiClients.getApiService().loginUser(newLogin)
        retrofitData?.enqueue(object : Callback<LoginRespondData?> {
            override fun onResponse(call: Call<LoginRespondData?>?, response: Response<LoginRespondData?>) {
                if(response.code()==200 ){
                    val respondBody=response.body()!!
                    val em:String=respondBody.token
                    sessionManager.saveAuthToken(respondBody.token,respondBody.email,respondBody.memberSince)
                    callMainActivity()
                }

            }

            override fun onFailure(call: Call<LoginRespondData?>, t: Throwable) {
                //Log.d("MainActivity","onFailure: "+t.message)

            }
        }

        )

    }

    private fun callMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

}
