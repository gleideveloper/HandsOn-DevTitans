package com.gleidev.myclientbatscan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.gleidev.myclientbatscan.adapter.MacVendorAdapter;
import com.gleidev.myclientbatscan.models.MacVendorModel;
import com.gleidev.myclientbatscan.models.MacDetails;
import com.gleidev.myclientbatscan.retrofit.MacVendorApi;
import com.gleidev.myclientbatscan.retrofit.RetrofitService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MacVendorListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MacVendorAdapter macVendorAdapter;
    private List<MacVendorModel> macVendorModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mac_vendor_list);

        recyclerView = findViewById(R.id.mac_vendor_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //loadMacVendors();
        loadVendorDetails();
    }

    private void loadVendorDetails() {
        RetrofitService retrofitService = new RetrofitService();
        MacVendorApi macVendorApi = retrofitService.getRetrofit().create(MacVendorApi.class);

        macVendorApi.getMacDetails("D8C6787B1410").enqueue(new Callback<MacDetails>() {
            @Override
            public void onResponse(Call<MacDetails> call, Response<MacDetails> response) {
                MacDetails macDetails = response.body();
            }

            @Override
            public void onFailure(Call<MacDetails> call, Throwable t) {

            }
        });
    }

    private void loadMacVendors() {
        RetrofitService retrofitService = new RetrofitService();
        MacVendorApi macVendorApi = retrofitService.getRetrofit().create(MacVendorApi.class);
        macVendorApi.getAllMacs().enqueue(new Callback<List<MacVendorModel>>() {
            @Override
            public void onResponse(Call<List<MacVendorModel>> call, Response<List<MacVendorModel>> response) {
                populateListView(response.body());                
            }

            @Override
            public void onFailure(Call<List<MacVendorModel>> call, Throwable t) {

            }
        });
    }

    private void populateListView(List<MacVendorModel> body) {
        macVendorAdapter = new MacVendorAdapter(body);
        recyclerView.setAdapter(macVendorAdapter);
    }
}