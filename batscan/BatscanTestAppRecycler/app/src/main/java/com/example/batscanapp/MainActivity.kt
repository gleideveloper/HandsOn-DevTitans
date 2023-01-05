package com.example.batscanapp

import android.content.ContentValues.TAG
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.os.RemoteException
import android.service.controls.ControlsProviderService.TAG
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import devtitans.batscanmanager.BatscanManager
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private val TAG = "DevTITANS.BatscanApp"

    private var textStatus: TextView? = null
    private  var textRssiMac:TextView? = null
    private var manager: BatscanManager? = null


    private lateinit var liveAdapter: EquipamentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.xactivity_main)

        textStatus = findViewById(R.id.vendor) // Acessa os componentes da tela

        textRssiMac = findViewById(R.id.rssi_mac)


        manager = BatscanManager.getInstance()

        updateAll(null)

        initRecyclerView()
        addDataSource()

    }

    private fun updateAll(view: View?) {
        //Log.d(MainActivity.TAG, "Atualizando dados do dispositivo ...")
        textStatus!!.text = "Atualizando ..."
        textStatus!!.setTextColor(Color.parseColor("#c47e00"))
        try {
            val equipamento = manager!!.scan // Executa o método getLuminosity via manager
            textRssiMac!!.text = equipamento
            val status = manager!!.connect() // Executa o método connect via manager
            if (status == 0) {
                textStatus!!.text = "Desconectado"
                textStatus!!.setTextColor(Color.parseColor("#73312f"))
            } else if (status == 1) {
                textStatus!!.text = "Conectado"
                textStatus!!.setTextColor(Color.parseColor("#6d790c"))
            } else {
                textStatus!!.text = "Simulado"
                textStatus!!.setTextColor(Color.parseColor("#207fb5"))
            }
        } catch (e: RemoteException) {
            Toast.makeText(this, "Erro ao acessar o Binder!", Toast.LENGTH_LONG).show()
            //Log.e(MainActivity.TAG, "Erro atualizando dados:", e)
        }
    }

    private fun initRecyclerView() {

        liveAdapter = EquipamentAdapter { live ->
            openLink(live.rssi_mac)
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