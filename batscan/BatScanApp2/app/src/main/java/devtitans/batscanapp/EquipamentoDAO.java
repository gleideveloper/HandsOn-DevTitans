package devtitans.batscanapp;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

//public class EquipamentoDAO {
//    private Context context;
//    private IBinder binder;
//    private IBatscan service;
//    private String textStatus, textRssiMac;
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
//            for (int i = 0; equipamentoList.size() < 5; i++) {
//                textRssiMac = findEquipamento();
//                equipamentoList.add(new Equipamento(i, textRssiMac, textRssiMac, "vendor"));
//            }
//
//        }
//        return equipamentoList;
//    }
//
//    public boolean add(Equipamento equipamento) {
//        equipamento.setId(equipamentoList.size());
//        equipamentoList.add(equipamento);
//        Toast.makeText(context, "Equipamento adicionado!", Toast.LENGTH_SHORT).show();
//        return true;
//    }
//
//    public Equipamento get(int id) {
//        return equipamentoList.get(id);
//    }
//
//    public String findEquipamento() {
//        binder = ServiceManager.getService("devtitans.batscan.IBatscan/default"); // Acessa e consulta o binder
//        if (binder != null) {
//            service = IBatscan.Stub.asInterface(binder);                   // Acessa o serviço batscan
////            if (service != null)
////                Toast.makeText(this, "Serviço batscan acessado com sucesso.", Toast.LENGTH_LONG).show();
////            else
////                Toast.makeText(this, "Erro ao acessar o serviço batscan!", Toast.LENGTH_LONG).show();
////        }
////        else
////            Toast.makeText(this, "Erro ao acessar o Binder!", Toast.LENGTH_LONG).show();
//
//            try {
//                textRssiMac.service.getScan();                    // Executa o método getScan via IPC
//
//                int status = service.connect();                              // Executa o método connect via IPC
//                if (status == 0) {
//                    textStatus = "Desconectado";
//                    //textStatus.setTextColor(Color.parseColor("#73312f"));
//                } else if (status == 1) {
//                    textStatus = "Conectado";
//                    //textStatus.setTextColor(Color.parseColor("#6d790c"));
//                } else {
//                    textStatus = "Simulado";
//                    textRssiMac = "00:11:11:|-86";
//                    //textStatus.setTextColor(Color.parseColor("#207fb5"));
//                }
//
//            } catch (android.os.RemoteException e) {
//                //Toast.makeText(this, "Erro ao acessar o Binder!", LENGTH_LONG).show();
//                Log.e(TAG, "Erro atualizando dados:", e);
//            }
//
//            return textRssiMac;
//        }
//        return textRssiMac;
//    }
//}
public class EquipamentoDAO {
    private Context context;
    private IBinder binder;
    private String textStatus, textRssiMac;


    private static ArrayList<Equipamento> equipamentoList = new ArrayList<>();

    public EquipamentoDAO(Context context) {
        this.context = context;
    }


    public ArrayList<Equipamento> getEquipamentoList() {
        if (equipamentoList.size() == 0) {
            for (int i = 0; equipamentoList.size() < 2; i++) {
                textRssiMac = findEquipamento();
                equipamentoList.add(new Equipamento(i, textRssiMac.substring(0,3), textRssiMac.substring(4,20), https://api.macvendors.com/FC-A1-3E-2A-1C-33));
            }

        }
        return equipamentoList;
    }

    public String findEquipamento() {
        textStatus = "Simulado";
        textRssiMac = "-86|00:1B:44:11:3A:B7";
        return textRssiMac;
    }
}