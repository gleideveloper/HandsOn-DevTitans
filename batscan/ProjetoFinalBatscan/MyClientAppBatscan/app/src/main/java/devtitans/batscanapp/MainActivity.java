package devtitans.batscanapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

        /*IntenFilter pega o estado da conexção UBS*/
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.hardware.usb.action.USB_STATE");
        registerReceiver(receiver, filter);
    }

    /*BroadcastReceiver evento para receber o estado da conexão USB*/
    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getExtras().getBoolean("connected")) {
                //start doing something for state - connected
                checkIsConnectedEsp32();
            } else {
                //start doing something for state - disconnected
                checkIsConnectedEsp32();
            }
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        homeSeachIcon.setOnClickListener(view -> checkIsConnectedEsp32());
    }

    private void checkIsConnectedEsp32() {
        if (serviceBatscan.isConnected()) {
            homeSeachIcon.setImageResource(R.drawable.home_search_on);
            statusBatscan.setText("Conectado com Esp32!");
            Toast.makeText(MainActivity.this, "ESP32 Connected!!!", Toast.LENGTH_SHORT).show();
            statusBatscan.setTextColor(Color.BLUE);
            btnBatScan.setEnabled(true);
        } else {
            homeSeachIcon.setImageResource(R.drawable.home_search_off);
            statusBatscan.setText("Desconectado!");
            statusBatscan.setTextColor(Color.RED);
            Toast.makeText(MainActivity.this, "ESP32 disconnected!!!", Toast.LENGTH_SHORT).show();
            btnBatScan.setEnabled(false);
        }
    }

    public void deviceList(View view) {
        Intent intent = new Intent(this, MacVendorListActivity.class);
        startActivity(intent);
    }
}