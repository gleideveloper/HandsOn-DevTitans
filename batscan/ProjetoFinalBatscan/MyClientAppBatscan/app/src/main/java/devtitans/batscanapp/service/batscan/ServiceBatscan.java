package devtitans.batscanapp.service.batscan;

import static android.content.ContentValues.TAG;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

import android.os.RemoteException;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import devtitans.batscanapp.service.batscan.response.MacRssiBatscan;
import devtitans.batscanmanager.BatscanManager;

public class ServiceBatscan {
    private final BatscanManager manager;
    private final List<MacRssiBatscan> macRssiBatscanRespons = new ArrayList<>();
    private final List<String> getRssiMacList = new ArrayList<>();

    public ServiceBatscan() {
        this.manager = BatscanManager.getInstance();
    }

    public List<MacRssiBatscan> getRemoveMacDuplicated() {
        rssiMacListGetScan();
        for (String s : getRssiMacList) {
            String[] data = s.split("\\|");
            macRssiBatscanRespons.add(new MacRssiBatscan(data[0], data[1]));
        }
        return macRssiBatscanRespons.stream()
                .collect(collectingAndThen(toCollection(
                        () -> new TreeSet<>(comparing(MacRssiBatscan::getMacAddress))), ArrayList::new));
    }

    public void rssiMacListGetScan() {
        int MAX_REQUEST_MAC = 30;
        for (int i = 0; i < MAX_REQUEST_MAC; i++) {
            try {
                getRssiMacList.add(manager.getScan());
            } catch (android.os.RemoteException e) {
                //Toast.makeText(this, "Erro ao acessar o Binder!", LENGTH_LONG).show();
                Log.e(TAG, "Erro atualizando dados:", e);
                getRssiMacList.add("-86|00:1B:44:11:3A:B7");
            }
        }
    }
    public boolean isConnected() {
        try {
            if (manager.connect() == 1) return true;
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return false;
    }
}
