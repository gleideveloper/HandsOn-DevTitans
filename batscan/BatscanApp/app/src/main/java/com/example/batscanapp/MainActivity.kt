package com.example.batscanapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var liveAdapter: EquipamentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecyclerView()
        addDataSource()

    }

    private fun initRecyclerView() {

        liveAdapter = EquipamentAdapter { live ->
            openLink(live.link)
        }

        recyclerview.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = liveAdapter
        }

    }

    private fun openLink(link: String) {

        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
        startActivity(browserIntent)

    }

    private fun addDataSource() {

        val dataSource = DataSource.createDataSet()
        liveAdapter.setList(dataSource)

    }

}