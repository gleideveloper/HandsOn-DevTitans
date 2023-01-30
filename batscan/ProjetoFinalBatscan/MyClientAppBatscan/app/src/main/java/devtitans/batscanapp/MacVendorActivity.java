package devtitans.batscanapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.logging.Level;
import java.util.logging.Logger;

import devtitans.batscanapp.bufferedreader.ResponseBufferedReader;
import devtitans.batscanapp.service.network.MicroserviceRetrofit;
import devtitans.batscanapp.service.network.response.MacVendorMicroservice;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MacVendorActivity extends AppCompatActivity {
    private Button saveMacVendor, delMacVendor;
    private EditText macPrefix, vendor, countryCode;
    private MacVendorMicroservice macVendorMicroservice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mac_vendor_register_form);
        macPrefix = findViewById(R.id.macAddress);
        vendor = findViewById(R.id.companyName);
        countryCode = findViewById(R.id.countryCode);
        Bundle receivedData = getIntent().getExtras();
        macPrefix.setText(receivedData.getString("macPrefix"));
        saveMacVendor = findViewById(R.id.btnSave);
        delMacVendor = findViewById(R.id.btnDel);
    }

    public void saveMac(View view) {
        macVendorMicroservice = getMacVendorMicroservice();
        MicroserviceRetrofit.getInstance()
                .saveMac(macVendorMicroservice)
                .enqueue(new Callback<MacVendorMicroservice>() {
                    @Override
                    public void onResponse(@NonNull Call<MacVendorMicroservice> call, @NonNull Response<MacVendorMicroservice> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(MacVendorActivity.this, "Salvo com sucesso!", Toast.LENGTH_SHORT).show();
                        } else {
                            ResponseBufferedReader<MacVendorMicroservice> responseBody = new ResponseBufferedReader<>(response);
                            Toast.makeText(MacVendorActivity.this, responseBody.getResponse(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<MacVendorMicroservice> call, Throwable t) {
                        Toast.makeText(MacVendorActivity.this, "Save failed!!!", Toast.LENGTH_SHORT).show();
                        Logger.getLogger(MacVendorActivity.class.getName()).log(Level.SEVERE, "Error occurred!!!");
                    }
                });
    }

    public void deleteMac(View view) {
        MicroserviceRetrofit.getInstance()
                .deleteMac(macPrefix.getText().toString())
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(MacVendorActivity.this, "Deletado com Sucesso!", Toast.LENGTH_SHORT).show();
                        } else {
                            ResponseBufferedReader<ResponseBody> responseBody = new ResponseBufferedReader<>(response);
                            Toast.makeText(MacVendorActivity.this, responseBody.getResponse(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                        Toast.makeText(MacVendorActivity.this, "Deleted failed!!!", Toast.LENGTH_SHORT).show();
                        Logger.getLogger(MacVendorActivity.class.getName()).log(Level.SEVERE, "Error occurred!!!");
                    }
                });
    }

    @NonNull
    private MacVendorMicroservice getMacVendorMicroservice() {
        macVendorMicroservice = new MacVendorMicroservice();
        macVendorMicroservice.setMacPrefix(macPrefix.getText().toString());
        macVendorMicroservice.setVendorName(vendor.getText().toString());
        macVendorMicroservice.setCountryCode(countryCode.getText().toString());
        return macVendorMicroservice;
    }
}