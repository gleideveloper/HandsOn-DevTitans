package com.gleidev.myclientbatscan.models;

public class MacRssiModel {
    private String rssi;
    private String macAddress;

    public MacRssiModel(String rssi, String macAddress) {
        this.rssi = rssi;
        this.macAddress = macAddress;
    }

    public String getMacAddress() {
        return macAddress;
    }
}
