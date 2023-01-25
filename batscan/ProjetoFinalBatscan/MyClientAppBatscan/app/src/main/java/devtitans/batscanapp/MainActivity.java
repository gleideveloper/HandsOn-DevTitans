package devtitans.batscanapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import devtitans.batscanapp.service.batscan.ServiceBatscan;
import devtitans.batscanmanager.BatscanManager;

public class MainActivity extends AppCompatActivity {
    private ImageView homeSeachIcon;
    private TextView statusBatscan;
    ServiceBatscan serviceBatscan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        homeSeachIcon = findViewById(R.id.imvTelaPrincipal);
        statusBatscan = findViewById(R.id.txvStatusBatscan);
        serviceBatscan = new ServiceBatscan();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setEstadoEsp32();
        homeSeachIcon.setOnClickListener(view -> setEstadoEsp32());
    }

    private void setEstadoEsp32() {
        if(serviceBatscan.isConnected()){
            homeSeachIcon.setImageResource(R.drawable.home_search_on);
            statusBatscan.setText("Conectado com Esp32!");
        }else{
            homeSeachIcon.setImageResource(R.drawable.home_search_off);
            statusBatscan.setText("Desconectado!");
        }
    }

    public void deviceList(View view) {
        Intent intent = new Intent(this, MacVendorListActivity.class);
        startActivity(intent);
    }
}