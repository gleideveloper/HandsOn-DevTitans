package devtitans.batscanapp;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private EquipamentosAdapter adapter;

    public class EquipamentosAdapter extends RecyclerView.Adapter<EquipamentosViewHolder> {
        private Context context;
        private ArrayList<Equipamento> equipamentos;
        EquipamentoDAO equipamentoDAO;

        public EquipamentosAdapter(Context context) {
            this.context = context;
            equipamentoDAO = new EquipamentoDAO(context);
            update();
        }

        public void update() {
            equipamentos = equipamentoDAO.getEquipamentoList();
        }

        public EquipamentosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            ConstraintLayout v = (ConstraintLayout) LayoutInflater
                    .from(parent.getContext())
                    .inflate(R.layout.list_item, parent, false);
            EquipamentosViewHolder vh = new EquipamentosViewHolder(v, context);
            return vh;
        }

        public void onBindViewHolder(EquipamentosViewHolder holder, int position) {
            holder.name.setText(equipamentos.get(position).getMac());
            // holder.login.setText(equipamentos.get(position).getQuantidade());
            holder.id = equipamentos.get(position).getId();
        }

        public int getItemCount() {
            return equipamentos.size();
        }
    }

    class EquipamentosViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public Context context;
        public TextView login, name;
        public int id;

        public EquipamentosViewHolder(ConstraintLayout v, Context context) {
            super(v);
            this.context = context;
            name = v.findViewById(R.id.itemMac);
            v.setOnClickListener(this);
        }

        public void onClick(View v) {
            Toast.makeText(context, "Olá " + this.login.getText().toString(), Toast.LENGTH_LONG)
                    .show();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Toast.makeText(this, "Está na itent de lista!", Toast.LENGTH_SHORT).show();

        recyclerView = findViewById(R.id.list_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new EquipamentosAdapter(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        adapter.update();
        adapter.notifyDataSetChanged();
    }


}