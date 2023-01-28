package devtitans.batscanapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import devtitans.batscanapp.service.batscan.ServiceBatscan;

public class MainActivity extends AppCompatActivity {
    private ImageView homeSeachIcon;
    private TextView statusBatscan;
    private Button btnBatScan;
    ServiceBatscan serviceBatscan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        homeSeachIcon = findViewById(R.id.imvTelaPrincipal);
        statusBatscan = findViewById(R.id.txvStatusBatscan);
        btnBatScan = findViewById(R.id.btnScannear);
        serviceBatscan = new ServiceBatscan();
        btnBatScan.setEnabled(false);
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
            btnBatScan.setEnabled(true);
        }else{
            homeSeachIcon.setImageResource(R.drawable.home_search_off);
            statusBatscan.setText("Desconectado!");
            btnBatScan.setEnabled(false);
        }
    }

    public void deviceList(View view) {
        Intent intent = new Intent(this, MacVendorListActivity.class);
        startActivity(intent);
    }
}