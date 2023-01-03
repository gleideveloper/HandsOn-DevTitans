package devtitans.batscanmanager;

import android.util.Log;
import android.os.ServiceManager;
import android.os.IBinder;
import android.os.RemoteException;

import devtitans.batscan.IBatscan;                      // Criado pelo AIDL

public class BatscanManager {
    private static final String TAG = "DevTITANS.BatscanManager";
    private IBinder binder;
    private IBatscan service;

    private static BatscanManager instance;

    // Construtor. Configura a "instância da classe" (objeto) recém-criada.
    // Note o "private" no construtor. Essa classe só pode ser instanciada dentro desse arquivo.
    private BatscanManager() {
        Log.d(TAG, "Nova (única) instância do BatscanManager ...");

        binder = ServiceManager.getService("devtitans.batscan.IBatscan/default");
        if (binder != null) {
            service = IBatscan.Stub.asInterface(binder);
            if (service != null)
                Log.d(TAG, "Serviço Batscan acessado com sucesso.");
            else
                Log.e(TAG, "Erro ao acessar o serviço Batscan!");
        }
        else
            Log.e(TAG, "Erro ao acessar o Binder!");
    }

    // Acessa a (única) instância dessa classe. Se ela não existir ainda, cria.
    // Note o "static" no método. Podemos executá-lo sem precisar instanciar um objeto.
    public static BatscanManager getInstance() {
        if (instance == null)
            instance = new BatscanManager();

        return instance;
    }

    public int connect() throws RemoteException {
        Log.d(TAG, "Executando método connect() ...");
        return service.connect();
    }

    public String getScan() throws RemoteException {
        Log.d(TAG, "Executando método getScan() ...");
        return service.getScan();
    }

//     public boolean setLed(int ledValue) throws RemoteException {
//         Log.d(TAG, "Executando método setLed(" + ledValue + ") ...");
//         return service.setLed(ledValue);
//     }
//
//     public int getLuminosity() throws RemoteException {
//         Log.d(TAG, "Executando método getLuminosity() ...");
//         return service.getLuminosity();
//     }
}
