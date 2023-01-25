package devtitans.batscanapp;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import devtitans.batscanapp.adapter.MacVendorAdapter;
import devtitans.batscanapp.service.network.MacVendorMicroserviceRetrofit;
import devtitans.batscanapp.service.network.response.MacVendorMicroservice;
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
        MacVendorMicroserviceRetrofit.getInstance()
                .getAllMacVendors()
                .enqueue(new Callback<List<MacVendorMicroservice>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<MacVendorMicroservice>> call, @NonNull Response<List<MacVendorMicroservice>> response) {
                        if(response.isSuccessful()) {
                            populateListView(response.body());
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<List<MacVendorMicroservice>> call, @NonNull Throwable t) {
                        Toast.makeText(MacVendorListActivity.this, "Deleted failed!!!", Toast.LENGTH_SHORT).show();
                        Logger.getLogger(MacVendorListActivity.class.getName()).log(Level.SEVERE, "Error occurred!!!");
                    }
                });
    }
    private void populateListView(List<MacVendorMicroservice> macVendorMicroserviceList) {
        macVendorAdapter = new MacVendorAdapter(macVendorMicroserviceList, this);
        recyclerView.setAdapter(macVendorAdapter);
    }
}