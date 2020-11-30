package com.example.globofly.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.globofly.R
import com.example.globofly.helpers.DestinationAdapter
import com.example.globofly.helpers.SampleData
import kotlinx.android.synthetic.main.activity_destiny_list.*

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
	}

	private fun loadDestinations() {

        // To be replaced by retrofit code
		destiny_recycler_view.adapter = DestinationAdapter(SampleData.DESTINATIONS)
    }
}
