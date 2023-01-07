package devtitans.batscanapp;

public class Equipamento {
    private int id;
    private String mac;
    private String rssi;
    private String vendor;

    public Equipamento(int id, String mac, String rssi, String vendor) {
        this.id = id;
        this.mac = mac;
        this.rssi = rssi;
        this.vendor = vendor;
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
