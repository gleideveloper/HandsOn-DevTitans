package devtitans.batscanapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import devtitans.batscanapp.MacVendorActivity;
import devtitans.batscanapp.R;
import devtitans.batscanapp.models.MacVendorItemAdapterModel;
import devtitans.batscanapp.service.batscan.ServiceBatscan;
import devtitans.batscanapp.service.batscan.response.MacRssiBatscan;
import devtitans.batscanapp.service.network.response.MacVendorMicroservice;

public class MacVendorAdapter extends RecyclerView.Adapter<MacVendorHolder> {
    private Context context;
    private List<MacVendorItemAdapterModel> macVendorItemAdapterModelList;
    private List<MacVendorMicroservice> macVendorMicroserviceList;
    private List<MacRssiBatscan> macRssiBatscanList;
    private ServiceBatscan serviceBatscan;

    public MacVendorAdapter(List<MacVendorMicroservice> macVendorMicroserviceList, Context context) {
        this.context = context;
        this.macVendorItemAdapterModelList = new ArrayList<>();
        this.macVendorMicroserviceList = macVendorMicroserviceList;
        serviceBatscan = new ServiceBatscan();
        loadMacVendorModelList();
    }

    private void loadMacVendorModelList() {
        macRssiBatscanList = serviceBatscan.getRemoveMacDuplicated();
        for (MacRssiBatscan mac : macRssiBatscanList) {
            MacVendorItemAdapterModel macVendorItemAdapterModel = new MacVendorItemAdapterModel();
            macVendorItemAdapterModel.setMacAddress(mac.getMacAddress());
            macVendorItemAdapterModel.setRssi(mac.getRssi());
            String macPrefix = mac.getMacAddress().replaceAll(":", "").substring(0, 6).toUpperCase();
            macVendorItemAdapterModel.setMacPrefix(macPrefix);
            MacVendorMicroservice macVendorMicroservice = macVendorMicroserviceList.stream()
                    .filter(o -> o.getMacPrefix().equals(macPrefix))
                    .findFirst()
                    .orElse(null);
            if (macVendorMicroservice != null) {
                macVendorItemAdapterModel.setVendor(macVendorMicroservice.getVendorName());
                macVendorItemAdapterModel.setCountryCode(macVendorMicroservice.getCountryCode());
                macVendorItemAdapterModel.setCamera(true);
            } else {
                macVendorItemAdapterModel.setVendor("Fabricante desconhecido");
                macVendorItemAdapterModel.setCountryCode("XX");
                macVendorItemAdapterModel.setCamera(false);
            }
            macVendorItemAdapterModelList.add(macVendorItemAdapterModel);
        }
    }

    @NonNull
    @Override
    public MacVendorHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.mac_vendor_list_item, parent, false);
        return new MacVendorHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MacVendorHolder holder, int position) {
        MacVendorItemAdapterModel macVendorItemAdapterModel = macVendorItemAdapterModelList.get(position);
        holder.rssi.setText(macVendorItemAdapterModel.getRssi());
        holder.mac.setText(macVendorItemAdapterModel.getMacAddress());
        holder.vendor.setText(macVendorItemAdapterModel.getVendor());
        holder.countryCode.setText(macVendorItemAdapterModel.getCountryCode());
        if (macVendorItemAdapterModel.isCamera()) {
            holder.iconWifi.setImageResource(R.drawable.ic_cam_wifi);
            holder.isCamera.setText(R.string.is_camera);
        }
        holder.itemView.setOnClickListener(view -> {
            Bundle saveData = new Bundle();
            saveData.putString("macPrefix", macVendorItemAdapterModel.getMacPrefix());
            saveData.putString("vendor", macVendorItemAdapterModel.getVendor());
            saveData.putString("countryCode", macVendorItemAdapterModel.getCountryCode());
            Toast.makeText(context, macVendorItemAdapterModel.getMacAddress(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(context, MacVendorActivity.class);
            intent.putExtras(saveData);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return macVendorItemAdapterModelList.size();
    }
}
