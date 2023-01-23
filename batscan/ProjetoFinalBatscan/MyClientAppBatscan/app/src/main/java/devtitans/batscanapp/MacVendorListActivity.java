package devtitans.batscanapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import devtitans.batscanapp.adapter.MacVendorAdapter;
import devtitans.batscanapp.service.network.ApiServiceRetrofit;
import devtitans.batscanapp.service.network.response.MacVendorApiService;
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
    }
    @Override
    protected void onResume() {
        super.onResume();
        getMacVendorApiServiceList();
    }
    private void getMacVendorApiServiceList() {
        ApiServiceRetrofit.getInstance()
                .getAllMacVendors()
                .enqueue(new Callback<List<MacVendorApiService>>() {
                    @Override
                    public void onResponse(Call<List<MacVendorApiService>> call, Response<List<MacVendorApiService>> response) {
                        if(response.isSuccessful()) {
                            assert response.body() != null;
                            populateListView(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<MacVendorApiService>> call, Throwable t) {

                    }
                });
    }
    private void populateListView(List<MacVendorApiService> body) {
        macVendorApiServiceList = body;
        macVendorAdapter = new MacVendorAdapter(macVendorApiServiceList);
        recyclerView.setAdapter(macVendorAdapter);
    }
}