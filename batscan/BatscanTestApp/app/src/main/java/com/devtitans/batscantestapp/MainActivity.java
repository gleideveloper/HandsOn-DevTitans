package com.devtitans.batscantestapp;

import android.graphics.Color;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import android.os.ServiceManager;

import androidx.appcompat.app.AppCompatActivity;

import devtitans.batscan.IBatscan;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "DevTITANS.Batscan";

    private TextView textStatus, textRssiMac;
    private IBinder binder;
    private IBatscan service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textStatus =     findViewById(R.id.textStatus);                      // Acessa os componentes da tela
        textRssiMac = findViewById(R.id.textRssiMac);

        binder = ServiceManager.getService("devtitans.batscan.IBatscan/default"); // Acessa e consulta o binder
        if (binder != null) {
            service = IBatscan.Stub.asInterface(binder);                   // Acessa o serviço batscan
            if (service != null)
                Toast.makeText(this, "Serviço batscan acessado com sucesso.", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(this, "Erro ao acessar o serviço batscan!", Toast.LENGTH_LONG).show();
        }
        else
            Toast.makeText(this, "Erro ao acessar o Binder!", Toast.LENGTH_LONG).show();

        updateAll(null);
    }

    public void updateAll(View view) {
        Log.d(TAG, "Atualizando dados do dispositivo ...");

        if (binder == null) {
            textStatus.setText("Erro no Binder");
            textStatus.setTextColor(Color.parseColor("#73312f"));
        }
        else if (service == null) {
            textStatus.setText("Erro no Serviço");
            textStatus.setTextColor(Color.parseColor("#73312f"));
        }
        else {
            textStatus.setText("Atualizando ...");
            textStatus.setTextColor(Color.parseColor("#c47e00"));

            try {
                textRssiMac.setText(service.getScan());                    // Executa o método getScan via IPC

                int status = service.connect();                              // Executa o método connect via IPC
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

    }

}