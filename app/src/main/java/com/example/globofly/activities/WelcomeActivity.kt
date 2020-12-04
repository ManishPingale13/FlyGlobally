@file:Suppress("UNUSED_PARAMETER")

package com.example.globofly.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.example.globofly.R
import com.example.globofly.services.MessageService
import com.example.globofly.services.ServiceBuilder
import kotlinx.android.synthetic.main.activity_welcome.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Suppress("UNUSED_PARAMETER")
class WelcomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        // To be replaced by retrofit code
//		message.text = "Black Friday! Get 50% cash back on saving your first spot."

        val messageService = ServiceBuilder.buildService(MessageService::class.java)
        val requestCall = messageService.getPromoMessage("http://192.168.2.6:8000/messages")

        requestCall.enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful) {
                    val msg = response.body()

                    msg?.let {
                        message.text = msg
                    }
                } else {
                    Toast.makeText(this@WelcomeActivity, "Failed!", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Toast.makeText(this@WelcomeActivity, "Error Occurred", Toast.LENGTH_SHORT).show()
            }

        })
    }

    fun getStarted(view: View) {
        val intent = Intent(this, DestinationListActivity::class.java)
        startActivity(intent)
        finish()
    }
}
