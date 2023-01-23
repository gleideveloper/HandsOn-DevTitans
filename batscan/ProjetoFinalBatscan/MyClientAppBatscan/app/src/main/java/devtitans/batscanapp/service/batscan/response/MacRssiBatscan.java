package devtitans.batscanapp.service.batscan.response;

public class MacRssiBatscan {
    private String rssi;
    private String macAddress;

    public MacRssiBatscan(String rssi, String macAddress) {
        this.rssi = rssi;
        this.macAddress = macAddress;
    }

    public String getMacAddress() {
        return macAddress;
    }
}
