package devtitans.batscanapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import devtitans.batscanapp.adapter.MacVendorAdapter;
import devtitans.batscanapp.service.network.MacVendroMicroserviceRetrofit;
import devtitans.batscanapp.service.network.response.MacVendorMicroserviceResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MacVendorListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MacVendorAdapter macVendorAdapter;

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
        MacVendroMicroserviceRetrofit.getInstance()
                .getAllMacVendors()
                .enqueue(new Callback<List<MacVendorMicroserviceResponse>>() {
                    @Override
                    public void onResponse(Call<List<MacVendorMicroserviceResponse>> call, Response<List<MacVendorMicroserviceResponse>> response) {
                        if(response.isSuccessful()) {
                            assert response.body() != null;
                            populateListView(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<MacVendorMicroserviceResponse>> call, Throwable t) {

                    }
                });
    }
    private void populateListView(List<MacVendorMicroserviceResponse> macVendorMicroserviceResponseList) {
        macVendorAdapter = new MacVendorAdapter(macVendorMicroserviceResponseList, this);
        recyclerView.setAdapter(macVendorAdapter);
    }
}