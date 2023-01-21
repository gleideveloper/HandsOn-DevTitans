package com.gleidev.myclientbatscan.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gleidev.myclientbatscan.R;
import com.gleidev.myclientbatscan.models.MacVendorModel;

import java.util.List;

public class MacVendorAdapter extends RecyclerView.Adapter<MacVendorHolder> {
    private List<MacVendorModel> macVendorModelList;

    public MacVendorAdapter(List<MacVendorModel> macVendorModelList) {
        this.macVendorModelList = macVendorModelList;
    }

    @NonNull
    @Override
    public MacVendorHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.mac_vendor_item, parent, false);
        return new MacVendorHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MacVendorHolder holder, int position) {
        MacVendorModel macVendorModel = macVendorModelList.get(position);
        holder.mac.setText(macVendorModel.getMacAddress());
        holder.vendor.setText(macVendorModel.getCompanyName());
        holder.countryCode.setText(macVendorModel.getCountryCode());
    }

    @Override
    public int getItemCount() {
        return macVendorModelList.size();
    }
}
