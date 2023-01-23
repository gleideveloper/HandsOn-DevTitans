package devtitans.batscanapp;

public class Equipamento {
    private int id;
    private String rssi;
    private String mac;
    private String vendor;
    private boolean camera;

    public Equipamento(int id, String rssi, String mac, String vendor, boolean camera) {
        this.id = id;
        this.rssi = rssi;
        this.mac = mac;
        this.vendor = vendor;
        this.camera = camera;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getRssi() {
        return rssi;
    }

    public void setRssi(String rssi) {
        this.rssi = rssi;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }
}
