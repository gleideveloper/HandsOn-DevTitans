package devtitans.batscanapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import devtitans.batscanapp.R;
import devtitans.batscanapp.models.MacVendorModel;
import devtitans.batscanapp.service.batscan.ServiceBatscan;
import devtitans.batscanapp.service.batscan.response.MacRssiBatscan;
import devtitans.batscanapp.service.network.response.MacVendorApiService;

public class MacVendorAdapter extends RecyclerView.Adapter<MacVendorHolder> {
    private List<MacVendorModel> macVendorModelList;
    private List<MacVendorApiService> macVendorApiServiceList;
    private List<MacRssiBatscan> macRssiBatscanList;
    private ServiceBatscan serviceBatscan;

    public MacVendorAdapter(List<MacVendorApiService> macVendorApiServiceList) {
        this.macVendorApiServiceList = macVendorApiServiceList;
        getRssiMacBatscan();
    }

    private void getRssiMacBatscan() {
        serviceBatscan = new ServiceBatscan();
        macRssiBatscanList = serviceBatscan.getRemoveMacDuplicated();
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
        MacVendorModel macVendorModel = macVendorModelList.get(position);
        holder.rssi.setText(macVendorModel.getRssi());
        holder.mac.setText(macVendorModel.getMacAddress());
        holder.vendor.setText(macVendorModel.getVendor());
        holder.countryCode.setText(macVendorModel.getCountryCode());
    }

    @Override
    public int getItemCount() {
        return macVendorModelList.size();
    }
}
