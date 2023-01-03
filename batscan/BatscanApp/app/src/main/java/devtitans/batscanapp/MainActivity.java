package devtitans.batscanapp;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

import android.os.RemoteException;

import devtitans.batscanmanager.BatscanManager;                          // Biblioteca do Manager

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "DevTITANS.BatscanApp";

    private TextView textStatus, textRssiMac;
    private BatscanManager manager;                                        // Atributo para o Manager

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textStatus =     findViewById(R.id.textStatus);                      // Acessa os componentes da tela
        textRssiMac = findViewById(R.id.textRssiMac);


        manager = BatscanManager.getInstance();

        updateAll(null);
    }

    public void updateAll(View view) {
        Log.d(TAG, "Atualizando dados do dispositivo ...");

        textStatus.setText("Atualizando ...");
        textStatus.setTextColor(Color.parseColor("#c47e00"));

        try {
            String equipamento = manager.getScan();                        // Executa o método getLuminosity via manager
            textRssiMac.setText(equipamento);

            int status = manager.connect();                                  // Executa o método connect via manager
            if (status == 0) {
                textStatus.setText("Desconectado");
                textStatus.setTextColor(Color.parseColor("#73312f"));
            }
            else if (status == 1) {
                textStatus.setText("Conectado");
                textStatus.setTextColor(Color.parseColor("#6d790c"));
            }
            else {
                textStatus.setText("Simulado");
                textStatus.setTextColor(Color.parseColor("#207fb5"));
            }

        } catch (android.os.RemoteException e) {
            Toast.makeText(this, "Erro ao acessar o Binder!", Toast.LENGTH_LONG).show();
            Log.e(TAG, "Erro atualizando dados:", e);
        }

    }

    // Executado ao clicar no botão "SET" do Led.
//    public void updateLed(View view) {
//        try {
//            int newLed = Integer.parseInt(editLed.getText().toString());     // Executa o método getLed via manager
//            manager.setLed(newLed);
//        } catch (android.os.RemoteException e) {
//            Toast.makeText(this, "Erro ao setar led!", Toast.LENGTH_LONG).show();
//        }
//    }
}