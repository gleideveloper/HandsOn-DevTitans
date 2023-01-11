package devtitans.batscanapp;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

import devtitans.batscanmanager.BatscanManager;

public class EquipamentoDAO {
    private Context context;
    private BatscanManager manager;
    private String textStatus;
    private String textRssiMac;
    private String textRssi;
    private String textMac;
    private String textVendor;


    private static ArrayList<Equipamento> equipamentoList = new ArrayList<>();

    public EquipamentoDAO(Context context) {
        this.context = context;
    }


    public ArrayList<Equipamento> getEquipamentoList() {
        manager = BatscanManager.getInstance();
        if (equipamentoList.size() == 0) {
            for (int i = 0; equipamentoList.size() < 20; i++) {
                textRssiMac = findEquipamento();
                textMac = textRssiMac.substring(4,21);
                textRssi = textRssiMac.substring(0,3);
                textVendor = "Intelbras (sim)";
                //String textVendor = Conexao.getDadosVendor("https://api.macvendors.com/" + textRssiMac.substring(4, 20));
                equipamentoList.add(new Equipamento(i, textMac, textRssi, textVendor, false));
                Log.i("app ","rssi = "+textRssi);
                Log.i("app ","mac = "+textMac);
                Log.i("app ","vendor = "+ textVendor);
            }

        }
        return equipamentoList;
    }


    public Equipamento get(int id) {
        return equipamentoList.get(id);
    }

    public String findEquipamento() {

        try {
            textRssiMac = manager.getScan();                    // Executa o método getScan via IPC
            int status = manager.connect();                              // Executa o método connect via IPC
            if (status == 0) {
                textStatus = "Desconectado";
                //textStatus.setTextColor(Color.parseColor("#73312f"));
            } else if (status == 1) {
                textStatus = "Conectado";
                //textStatus.setTextColor(Color.parseColor("#6d790c"));
            } else {
                textStatus = "Simulado";
                textRssiMac = "-86|00:1B:44:11:3A:B7";
                //textStatus.setTextColor(Color.parseColor("#207fb5"));
            }

        } catch (android.os.RemoteException e) {
            //Toast.makeText(this, "Erro ao acessar o Binder!", LENGTH_LONG).show();
            Log.e(TAG, "Erro atualizando dados:", e);
            textRssiMac = "-86|00:1B:44:11:3A:B7";
        }

        return textRssiMac;
    }
}

//public class EquipamentoDAO {
//    private Context context;
//    private IBinder binder;
//    private String textStatus, textRssiMac, textVendor, textRssi, textMac;
//
//
//    private static ArrayList<Equipamento> equipamentoList = new ArrayList<>();
//
//    public EquipamentoDAO(Context context) {
//        this.context = context;
//    }
//
//
//    public ArrayList<Equipamento> getEquipamentoList() {
//        if (equipamentoList.size() == 0) {
//            for (int i = 0; equipamentoList.size() < 2; i++) {
//                textRssiMac = findEquipamento();
//                textMac = textRssiMac.substring(4,21);
//                textRssi = textRssiMac.substring(0,3);
//                textVendor = Conexao.getDadosVendor("https://api.macvendors.com/"+textRssiMac.substring(4,20));
//                equipamentoList.add(new Equipamento(i, textMac, textRssi, textVendor, false));
//                Log.i("app ","rssi = "+textRssi);
//                Log.i("app ","mac = "+textMac);
//                Log.i("app ","vendor = "+textVendor);
//
//            }
//
//        }
//        return equipamentoList;
//    }
//
//    public String findEquipamento() {
//        textStatus = "Simulado";
//        textRssiMac = "-86|00:1B:44:11:3A:B7";
//        return textRssiMac;
//    }
