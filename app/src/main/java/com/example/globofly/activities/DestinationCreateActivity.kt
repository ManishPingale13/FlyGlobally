package com.example.globofly.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.example.globofly.R
import com.example.globofly.helpers.Destination
import com.example.globofly.services.DestinationService
import com.example.globofly.services.ServiceBuilder
import kotlinx.android.synthetic.main.activity_destiny_create.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DestinationCreateActivity : AppCompatActivity() {

    @Suppress("UNUSED_VARIABLE")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_destiny_create)

        setSupportActionBar(toolbar)
        val context = this

        // Show the Up button in the action bar.
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        btn_add.setOnClickListener {
            val newDestination = Destination()
            newDestination.city = et_city.text.toString()
            newDestination.description = et_description.text.toString()
            newDestination.country = et_country.text.toString()

            val destinationService = ServiceBuilder.buildService(DestinationService::class.java)
            val requestCall = destinationService.addDestination(newDestination)

            requestCall.enqueue(object : Callback<Destination> {
                override fun onResponse(call: Call<Destination>, response: Response<Destination>) {
                    if (response.isSuccessful) {
                        val newlyCreatedDestination = response.body()
                        Log.d(TAG, "onResponse: BODY  $newlyCreatedDestination")
                        finish()
                        Toast.makeText(applicationContext,
                            "Destination Added Successfully!",
                            Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(applicationContext, "Failed!", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<Destination>, t: Throwable) {
                    Toast.makeText(applicationContext, "ERROR!", Toast.LENGTH_SHORT).show()
                    Log.d(TAG, "onFailure: ${t.message}")

                }

            })
        }
    }

    companion object {
        private const val TAG = "DestinationCreateActiv"
    }
}
