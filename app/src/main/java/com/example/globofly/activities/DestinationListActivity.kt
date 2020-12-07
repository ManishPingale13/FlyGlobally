package com.example.globofly.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.example.globofly.R
import com.example.globofly.helpers.Destination
import com.example.globofly.helpers.DestinationAdapter
import com.example.globofly.services.DestinationService
import com.example.globofly.services.ServiceBuilder
import kotlinx.android.synthetic.main.activity_destiny_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DestinationListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_destiny_list)

        setSupportActionBar(toolbar)
        toolbar.title = title

        fab.setOnClickListener {
            val intent = Intent(this@DestinationListActivity, DestinationCreateActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()

        loadDestinations()
//        loadIndianDestinations()
    }

    private fun loadDestinations() {

        val destinationService = ServiceBuilder.buildService(DestinationService::class.java)

        val requestCall = destinationService.getDestinationList("EN")

        requestCall.enqueue(object : Callback<List<Destination>> {
            override fun onResponse(
                call: Call<List<Destination>>,
                response: Response<List<Destination>>,
            ) {
                if (response.isSuccessful) {
                    val destinationList: List<Destination> = response.body()!!
                    destiny_recycler_view.adapter = DestinationAdapter(destinationList)
                    Log.d(TAG, "onResponse: $destinationList")
                } else {
                    Toast.makeText(this@DestinationListActivity, "Failed ", Toast.LENGTH_SHORT)
                        .show()
                }
            }

            override fun onFailure(call: Call<List<Destination>>, t: Throwable) {
                Toast.makeText(this@DestinationListActivity, "Error Occurred", Toast.LENGTH_SHORT)
                    .show()
                Log.d(TAG, "onFailure: $t")

            }
        })
    }

    private fun loadIndianDestinations() {

        val destinationService = ServiceBuilder.buildService(DestinationService::class.java)

        var filter = HashMap<String, String>()
        filter["country"] = "India"
        filter["count"] = "1"
        val requestCall = destinationService.getIndiaDestinations(filter)

        requestCall.enqueue(object : Callback<List<Destination>> {
            override fun onResponse(
                call: Call<List<Destination>>,
                response: Response<List<Destination>>,
            ) {
                if (response.isSuccessful) {
                    val destinationList: List<Destination> = response.body()!!
                    destiny_recycler_view.adapter = DestinationAdapter(destinationList)
                    Log.d(TAG, "onResponse: $destinationList")
                } else {
                    Toast.makeText(this@DestinationListActivity, "Failed ", Toast.LENGTH_SHORT)
                        .show()
                }
            }

            override fun onFailure(call: Call<List<Destination>>, t: Throwable) {
                Toast.makeText(this@DestinationListActivity, "Error Occurred", Toast.LENGTH_SHORT)
                    .show()
                Log.d(TAG, "onFailure: $t")

            }
        })
    }


    companion object {
        private const val TAG = "DestinationListActivity"
    }
}
