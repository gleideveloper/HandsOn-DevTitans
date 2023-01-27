package devtitans.batscanapp;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import devtitans.batscanapp.adapter.MacVendorAdapter;
import devtitans.batscanapp.bufferedreader.ResponseBufferedReader;
import devtitans.batscanapp.service.network.MacVendorMicroserviceRetrofit;
import devtitans.batscanapp.service.network.response.MacVendorMicroservice;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MacVendorListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MacVendorAdapter macVendorAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mac_vendor_list);
        recyclerView = findViewById(R.id.mac_vendor_recyclerView);
        swipeRefreshLayout = findViewById(R.id.swiperefreshlayout);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //setting Refreshing to false
                getMacVendorApiServiceList();
                macVendorAdapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

    }

    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            int position = viewHolder.getAdapterPosition();
            switch (direction){
                case ItemTouchHelper.LEFT:
                    macVendorAdapter.notifyItemRemoved(position);
                    break;
                case ItemTouchHelper.RIGHT:
                    break;
            }
        }
    };

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
                        if (response.isSuccessful()) {
                            populateListView(response.body());
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<List<MacVendorMicroservice>> call, @NonNull Throwable t) {
                        Toast.makeText(MacVendorListActivity.this, "Microservice not connected!!!", Toast.LENGTH_SHORT).show();
                        Logger.getLogger(MacVendorListActivity.class.getName()).log(Level.SEVERE, "Error occurred!!!");
                    }
                });
    }

    private void populateListView(List<MacVendorMicroservice> macVendorMicroserviceList) {
        macVendorAdapter = new MacVendorAdapter(macVendorMicroserviceList, this);
        recyclerView.setAdapter(macVendorAdapter);
    }

    /*public void deleteMac(View view) {
        MacVendorMicroserviceRetrofit.getInstance()
                .deleteMac(macPrefix.getText().toString())
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(MacVendorListActivity.this, "Deletado com Sucesso!", Toast.LENGTH_SHORT).show();
                        } else {
                            ResponseBufferedReader<ResponseBody> responseBody = new ResponseBufferedReader<>(response);
                            Toast.makeText(MacVendorListActivity.this, responseBody.getResponse(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                        Toast.makeText(MacVendorListActivity.this, "Deleted failed!!!", Toast.LENGTH_SHORT).show();
                        Logger.getLogger(MacVendorActivity.class.getName()).log(Level.SEVERE, "Error occurred!!!");
                    }
                });
    }*/
}