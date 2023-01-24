package devtitans.batscanapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.logging.Level;
import java.util.logging.Logger;

import devtitans.batscanapp.bufferedreader.ResponseBufferedReader;
import devtitans.batscanapp.service.network.MacVendroMicroserviceRetrofit;
import devtitans.batscanapp.service.network.response.MacVendorMicroserviceResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MacVendorActivity extends AppCompatActivity {
    private Button saveMacVendor;
    private EditText macPrefix, vendor, countryCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.save_mac_vendor_form);
        macPrefix = findViewById(R.id.macAddress);
        vendor = findViewById(R.id.companyName);
        countryCode = findViewById(R.id.countryCode);
        Bundle receivedData = getIntent().getExtras();
        macPrefix.setText(receivedData.getString("macPrefix"));
        vendor.setText(receivedData.getString("vendor"));
        countryCode.setText(receivedData.getString("countryCode"));
        saveMacVendor = findViewById(R.id.btnSave);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MacVendorMicroserviceResponse macVendorMicroserviceResponse = new MacVendorMicroserviceResponse();
        macVendorMicroserviceResponse.setMacPrefix(macPrefix.getText().toString());
        macVendorMicroserviceResponse.setVendorName(vendor.getText().toString());
        macVendorMicroserviceResponse.setCountryCode(countryCode.getText().toString());

        saveMacVendor.setOnClickListener(
                view -> MacVendroMicroserviceRetrofit.getInstance()
                        .saveMac(macVendorMicroserviceResponse)
                        .enqueue(new Callback<MacVendorMicroserviceResponse>() {
                            @Override
                            public void onResponse(@NonNull Call<MacVendorMicroserviceResponse> call, @NonNull Response<MacVendorMicroserviceResponse> response) {
                                if (response.isSuccessful()) {
                                    Toast.makeText(MacVendorActivity.this, "save successful", Toast.LENGTH_SHORT).show();
                                } else {
                                    ResponseBufferedReader<MacVendorMicroserviceResponse> br = new ResponseBufferedReader<>(response);
                                    Toast.makeText(MacVendorActivity.this, br.getResponse().toString(), Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(@NonNull Call<MacVendorMicroserviceResponse> call, Throwable t) {
                                Toast.makeText(MacVendorActivity.this, "Save failed!!!", Toast.LENGTH_SHORT).show();
                                Logger.getLogger(MacVendorActivity.class.getName()).log(Level.SEVERE, "Error occurred!!!");
                            }
                        }));
    }
}