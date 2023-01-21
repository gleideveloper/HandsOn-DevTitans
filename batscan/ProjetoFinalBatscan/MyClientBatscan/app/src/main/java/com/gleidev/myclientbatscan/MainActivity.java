package com.gleidev.myclientbatscan;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.gleidev.myclientbatscan.bufferedreader.ResponseBufferedReader;
import com.gleidev.myclientbatscan.models.MacVendorModel;
import com.gleidev.myclientbatscan.retrofit.MacVendorApi;
import com.gleidev.myclientbatscan.retrofit.RetrofitService;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_save_mac_vendor);
        initializeComponents();
    }

    private void initializeComponents() {
        TextInputEditText macAddress = findViewById(R.id.macAddress);
        TextInputEditText companyName = findViewById(R.id.companyName);
        TextInputEditText countryCode = findViewById(R.id.countryCode);
        MaterialButton save = findViewById(R.id.save_btn);

        RetrofitService retrofitService = new RetrofitService();
        MacVendorApi macCompanyApi = retrofitService.getRetrofit().create(MacVendorApi.class);

        save.setOnClickListener(view -> {
            MacVendorModel macVendorModel = new MacVendorModel();
            macVendorModel.setMacAddress(String.valueOf(macAddress.getText()));
            macVendorModel.setCompanyName(String.valueOf(companyName.getText()));
            macVendorModel.setCountryCode(String.valueOf(countryCode.getText()));

            macCompanyApi.saveMac(macVendorModel)
                    .enqueue(new Callback<MacVendorModel>() {
                        @Override
                        public void onResponse(@NonNull Call<MacVendorModel> call, @NonNull Response<MacVendorModel> response) {
                            if (response.isSuccessful()) {
                                Toast.makeText(MainActivity.this, response.body().getMacAddress() + " save successful", Toast.LENGTH_SHORT).show();
                                macAddress.setText("");
                                companyName.setText("");
                                countryCode.setText("");

                            } else {
                                ResponseBufferedReader<MacVendorModel> responseBr = new ResponseBufferedReader<>(response);
                                Toast.makeText(MainActivity.this, responseBr.getResponse().toString(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(@NonNull Call<MacVendorModel> call, @NonNull Throwable t) {
                            Toast.makeText(MainActivity.this, "Save failed!!!", Toast.LENGTH_SHORT).show();
                            Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, "Error occurred!!!");
                        }
                    });
        });
    }
}