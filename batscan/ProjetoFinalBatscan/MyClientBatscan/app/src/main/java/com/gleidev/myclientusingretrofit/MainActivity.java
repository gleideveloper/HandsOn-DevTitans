package com.gleidev.myclientusingretrofit;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.gleidev.myclientusingretrofit.models.ParkingSpotModel;
import com.gleidev.myclientusingretrofit.retrofit.ParkingSpotApi;
import com.gleidev.myclientusingretrofit.retrofit.RetrofitService;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_parking_spot);
        initializeComponents();
    }

    private void initializeComponents() {
        TextInputEditText parkingSpotNumber = findViewById(R.id.parkingSpotNumber);
        TextInputEditText licensePlateCar = findViewById(R.id.licensePlateCar);
        TextInputEditText brandCar = findViewById(R.id.brandCar);
        TextInputEditText modelCar = findViewById(R.id.modelCar);
        TextInputEditText colorCar = findViewById(R.id.colorCar);
        TextInputEditText responsibleName = findViewById(R.id.responsibleName);
        TextInputEditText apartment = findViewById(R.id.apartment);
        TextInputEditText block = findViewById(R.id.block);
        MaterialButton save = findViewById(R.id.save_btn);

        RetrofitService retrofitService = new RetrofitService();
        ParkingSpotApi parkingSpotApi = retrofitService.getRetrofit().create(ParkingSpotApi.class);

        save.setOnClickListener(view -> {
            ParkingSpotModel parkingSpotModel = new ParkingSpotModel();
            parkingSpotModel.setParkingSpotNumber(String.valueOf(parkingSpotNumber.getText()));
            parkingSpotModel.setLicensePlateCar(String.valueOf(licensePlateCar.getText()));
            parkingSpotModel.setBrandCar(String.valueOf(brandCar.getText()));
            parkingSpotModel.setModelCar(String.valueOf(modelCar.getText()));
            parkingSpotModel.setColorCar(String.valueOf(colorCar.getText()));
            parkingSpotModel.setResponsibleName(String.valueOf(responsibleName.getText()));
            parkingSpotModel.setApartment(String.valueOf(apartment.getText()));
            parkingSpotModel.setBlock(String.valueOf(block.getText()));

            parkingSpotApi.saveParkingSpot(parkingSpotModel)
                    .enqueue(new Callback<ParkingSpotModel>() {
                        @Override
                        public void onResponse(@NonNull Call<ParkingSpotModel> call, @NonNull Response<ParkingSpotModel> response) {
                            if (response.isSuccessful()) {
                                Toast.makeText(MainActivity.this, response.body().getParkingSpotNumber() + "save successful", Toast.LENGTH_SHORT).show();
                            } else {
                                assert response.errorBody() != null;
                                BufferedReader reader = new BufferedReader(new InputStreamReader(response.errorBody().byteStream()));
                                StringBuilder sb = new StringBuilder();
                                String line;
                                try {
                                    while ((line = reader.readLine()) != null) {
                                        sb.append(line);
                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                Toast.makeText(MainActivity.this, sb.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(@NonNull Call<ParkingSpotModel> call, @NonNull Throwable t) {
                            Toast.makeText(MainActivity.this, "Save failed!!!", Toast.LENGTH_SHORT).show();
                            Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, "Error occurred!!!");
                        }
                    });
        });
    }
}