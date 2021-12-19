package com.example.kaninistocks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.kaninistocks.APIs.ApiClients
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class update_email : AppCompatActivity() {
    private lateinit var sessionManager: SessionManager
    lateinit var apiClients: ApiClients
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_email)
        apiClients = ApiClients()
        sessionManager = SessionManager(this)

        val emailButton =findViewById<Button>(R.id.button2)
        val changeEmail = findViewById<TextInputLayout>(R.id.newEmail)

        emailButton.setOnClickListener {
            val newEmail = changeEmail.editText?.text.toString()
            CoroutineScope(Dispatchers.IO).launch{
                var retrodata=apiClients.getApiService().changeEmail(newEmail)
                retrodata.enqueue(object : Callback<Void?> {
                        override fun onResponse(call: Call<Void?>?, response: Response<Void?>) {
                            if(response.code()==200 ){
                                var cuToken:String = sessionManager.fetchAuthToken()!!
                                var cumember:Long = sessionManager.fetchMemberSince()!!
                                sessionManager.saveAuthToken(cuToken,changeEmail.editText?.text.toString(),cumember)

                            }else{

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