package com.gleidev.myclientbatscan.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gleidev.myclientbatscan.R;

public class MacVendorHolder extends RecyclerView.ViewHolder {
    protected TextView mac, vendor, countryCode;

    public MacVendorHolder(@NonNull View itemView) {
        super(itemView);
        mac = itemView.findViewById(R.id.macVendorListItem_mac);
        vendor = itemView.findViewById(R.id.macVendorListItem_vendor);
        countryCode = itemView.findViewById(R.id.macVendorListItem_country);
    }
}
