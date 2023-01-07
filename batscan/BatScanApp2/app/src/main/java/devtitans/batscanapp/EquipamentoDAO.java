package devtitans.batscanapp;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;

public class EquipamentoDAO {
    private Context context;
    private static ArrayList<Equipamento> equipamentoList = new ArrayList<>();

    public EquipamentoDAO(Context context) {
        this.context = context;
    }

    public ArrayList<Equipamento> getEquipamentoList(){
        if (equipamentoList.size() == 0) {
            equipamentoList.add(new Equipamento(0, "00:22:44:55", "-15", "Intelbras"));
            equipamentoList.add(new Equipamento(1, "11:22:44:55", "-15", "Intelbras"));
            equipamentoList.add(new Equipamento(2, "22:22:44:55", "-15", "Intelbras"));
            equipamentoList.add(new Equipamento(3, "33:22:44:55", "-15", "Intelbras"));
            equipamentoList.add(new Equipamento(4, "44:22:44:55", "-15", "Intelbras"));

        }
        return equipamentoList;
    }
    public boolean add(Equipamento equipamento) {
        equipamento.setId(equipamentoList.size());
        equipamentoList.add(equipamento);
        Toast.makeText(context, "Equipamento adicionado!", Toast.LENGTH_SHORT).show();
        return true;
    }

    public Equipamento get(int id){
        return equipamentoList.get(id);
    }
}
