package devtitans.batscanapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;

import devtitans.batscanapp.adapter.MacVendorAdapter;
import devtitans.batscanapp.service.batscan.ServiceBatscan;
import devtitans.batscanapp.service.batscan.response.MacRssiBatscan;
import devtitans.batscanapp.service.network.response.MacVendorApiService;
import devtitans.batscanapp.service.network.ApiServiceRetrofit;
import devtitans.batscanapp.service.network.MacVendorApi;
import devtitans.batscanapp.service.network.ServiceRetrofit;

import java.util.ArrayList;
import java.util.List;

import devtitans.batscanmanager.BatscanManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MacVendorListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MacVendorAdapter macVendorAdapter;
    private List<MacVendorApiService> macVendorApiServiceList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mac_vendor_list);

        recyclerView = findViewById(R.id.mac_vendor_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        getMacVendorApiServiceList();
//        macVendorAdapter = new MacVendorAdapter(macVendorApiServiceList);
//        recyclerView.setAdapter(macVendorAdapter);
    }

    private void getMacVendorApiServiceList() {
        ApiServiceRetrofit.getInstance()
                .getAllMacVendors()
                .enqueue(new Callback<List<MacVendorApiService>>() {
                    @Override
                    public void onResponse(Call<List<MacVendorApiService>> call, Response<List<MacVendorApiService>> response) {
                        if(response.isSuccessful()) {
                            assert response.body() != null;
                            macVendorApiServiceList = new ArrayList<>(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<MacVendorApiService>> call, Throwable t) {

                    }
                });
    }
    private void populateListView(List<MacVendorApiService> body) {

//        macVendorAdapter = new MacVendorAdapter(body);
//        recyclerView.setAdapter(macVendorAdapter);
    }
}