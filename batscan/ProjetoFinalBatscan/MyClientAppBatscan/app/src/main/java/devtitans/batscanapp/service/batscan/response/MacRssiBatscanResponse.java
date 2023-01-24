package devtitans.batscanapp.service.batscan.response;

public class MacRssiBatscanResponse {
    private String rssi;
    private String macAddress;

    public MacRssiBatscanResponse(String rssi, String macAddress) {
        this.rssi = rssi;
        this.macAddress = macAddress;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public String getRssi() {
        return rssi;
    }
}
