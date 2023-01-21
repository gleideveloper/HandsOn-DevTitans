package com.gleidev.myclientbatscan.service;

import static android.content.ContentValues.TAG;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

import android.util.Log;

import com.gleidev.myclientbatscan.models.MacRssiModel;
import com.gleidev.myclientbatscan.models.MacVendorModel;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import devtitans.batscanmanager.BatscanManager;

public class ServiceBatscan {
    private BatscanManager manager;
    private List<MacRssiModel> macRssiModels = new ArrayList<>();
    private List<String> getRssiMacList = new ArrayList<>();

    public ServiceBatscan(BatscanManager manager) {
        this.manager = manager;
    }

    public List<MacRssiModel> getRemoveMacDuplicated() {
        rssiMacListGetScan();
        for (String s : getRssiMacList) {
            String[] data = s.split("\\|");
            macRssiModels.add(new MacRssiModel(data[0], data[1]));
        }
        return macRssiModels.stream()
                .collect(collectingAndThen(toCollection(
                        () -> new TreeSet<>(comparing(MacRssiModel::getMacAddress))), ArrayList::new));
    }

    public void rssiMacListGetScan() {
        for (int i = 0; i < 50; i++) {
            try {
                getRssiMacList.add(manager.getScan());
            } catch (android.os.RemoteException e) {
                //Toast.makeText(this, "Erro ao acessar o Binder!", LENGTH_LONG).show();
                Log.e(TAG, "Erro atualizando dados:", e);
                getRssiMacList.add("-86|00:1B:44:11:3A:B7");
            }
        }
    }
}
