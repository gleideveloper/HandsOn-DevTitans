package devtitans.batscanapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import devtitans.batscanapp.R;
import devtitans.batscanapp.models.MacVendorItemAdapterModel;
import devtitans.batscanapp.service.batscan.ServiceBatscan;
import devtitans.batscanapp.service.batscan.response.MacRssiBatscan;
import devtitans.batscanapp.service.network.response.MacVendorApiService;

public class MacVendorAdapter extends RecyclerView.Adapter<MacVendorHolder> {
    private List<MacVendorItemAdapterModel> macVendorItemAdapterModelList;
    private List<MacVendorApiService> macVendorApiServiceList;
    private List<MacRssiBatscan> macRssiBatscanList;
    private ServiceBatscan serviceBatscan;

    public MacVendorAdapter(List<MacVendorApiService> macVendorApiServiceList) {
        this.macVendorItemAdapterModelList = new ArrayList<>();
        this.macVendorApiServiceList = macVendorApiServiceList;
        serviceBatscan = new ServiceBatscan();
        loadMacVendorModelList();
    }

    private void loadMacVendorModelList() {
        macRssiBatscanList = serviceBatscan.getRemoveMacDuplicated();
        //String mac = macRssiBatscanList.get(0).getMacAddress();
        //String macPrefix = mac.replaceAll(":", "").substring(0,6);
        for (MacRssiBatscan mac : macRssiBatscanList) {
            MacVendorItemAdapterModel macVendorItemAdapterModel = new MacVendorItemAdapterModel();
            macVendorItemAdapterModel.setMacAddress(mac.getMacAddress());
            macVendorItemAdapterModel.setRssi(mac.getRssi());
            String macPrefix = mac.getMacAddress().replaceAll(":", "").substring(0, 6).toUpperCase();
            MacVendorApiService macVendorApiService = macVendorApiServiceList.stream()
                    .filter(o -> o.getMacPrefix().equals(macPrefix))
                    .findFirst()
                    .orElse(null);
            if(macVendorApiService != null) {
                macVendorItemAdapterModel.setVendor(macVendorApiService.getVendorName());
                macVendorItemAdapterModel.setCountryCode(macVendorApiService.getCountryCode());
                macVendorItemAdapterModel.setCamera(true);
            }else{
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
        if(!macVendorItemAdapterModel.isCamera()){
            holder.iconWifi.setImageResource(R.drawable.ic_wifi);
            holder.isCamera.setText("Não é uma camera!!!");
        }
    }

    @Override
    public int getItemCount() {
        return macVendorItemAdapterModelList.size();
    }
}
