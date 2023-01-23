package devtitans.batscanapp.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import devtitans.batscanapp.R;

public class MacVendorHolder extends RecyclerView.ViewHolder {
    protected TextView rssi, mac, vendor, countryCode, isCamera;
    protected ImageView iconWifi;
    public MacVendorHolder(@NonNull View itemView) {
        super(itemView);
        rssi = itemView.findViewById(R.id.macVendorListItem_rssi);
        mac = itemView.findViewById(R.id.macVendorListItem_mac);
        vendor = itemView.findViewById(R.id.macVendorListItem_vendor);
        countryCode = itemView.findViewById(R.id.macVendorListItem_country);
        //isCamera = itemView.findViewById(R.id.macVendorListItem_isCamera);
    }
}
