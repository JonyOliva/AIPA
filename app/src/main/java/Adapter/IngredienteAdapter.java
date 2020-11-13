package Adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aipa.R;

import java.util.ArrayList;
import java.util.List;

import Models.Ingrediente;

public class IngredienteAdapter extends RecyclerView.Adapter<IngredienteAdapter.ViewHolder> {
    private List<Ingrediente> lstIngrediente;
    private List<Ingrediente> selecIngredientes;
    private Context context;
    private ListView lsView;
    private Button btn;

    public IngredienteAdapter(List<Ingrediente> lsIng, List<Ingrediente> selec, ListView lv, Button btn,Context ctx){
        this.context = ctx;
        this.lstIngrediente = lsIng;
        this.selecIngredientes = selec;
        this.lsView = lv;
        this.btn = btn;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View v;
        v= LayoutInflater.from(parent.getContext()).inflate(R.layout.ingredient_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Ingrediente ing = lstIngrediente.get(position);
        final IngredienteAdapter.ViewHolder ingAdapter= (IngredienteAdapter.ViewHolder) holder;
        String nombre = ing.getNombre();
        Integer puntaje = 5;//ing.getPuntaje();
        holder.txtNombre.setText(ing.getNombre());

        if(puntaje < 2) {
            holder.btnEstado.setBackgroundColor(context.getResources().getColor(R.color.verde));
        }
        if(puntaje > 1 && puntaje < 4){
            holder.btnEstado.setBackgroundColor(context.getResources().getColor(R.color.naranja));
        }
        if(puntaje > 4){
            holder.btnEstado.setBackgroundColor(context.getResources().getColor(R.color.rojo));
        }
    }

    @Override
    public int getItemCount() {
        return lstIngrediente.size();
    }

    public void setFilter(ArrayList<Ingrediente> ingredientes){
        this.lstIngrediente = new ArrayList<>();
        this.lstIngrediente.addAll(ingredientes);
        notifyDataSetChanged();
    }

    public Ingrediente getIngredienteSeleccionado(Integer position){
        return lstIngrediente.get(position);
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        final TextView txtNombre;
        final Button btnEstado;

        ViewHolder(View v) {
            super(v);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Log.i("POSITION", lstIngrediente.get(getBindingAdapterPosition()).toString());
                    IngredienteListaAdapter listaAdapter;

                    if(!selecIngredientes.contains(lstIngrediente.get(getBindingAdapterPosition()))) {
                        selecIngredientes.add(lstIngrediente.get(getBindingAdapterPosition()));
                        lsView.setAdapter(new ArrayAdapter<Ingrediente>(context.getApplicationContext(),R.layout.ingrediente_lista_layout));
                        notifyDataSetChanged();
                    }else{
                        Toast.makeText(context.getApplicationContext(),"Ese ingrediente ya fue ingresado", Toast.LENGTH_SHORT).show();
                    }

                    if(selecIngredientes.size() > 0)
                        btn.setVisibility(View.VISIBLE);
                    else btn.setVisibility(View.INVISIBLE);

                    listaAdapter = new IngredienteListaAdapter(context.getApplicationContext(), btn,selecIngredientes);
                    lsView.setAdapter(listaAdapter);
                }

            });
            this.txtNombre = v.findViewById(R.id.txtNombre_lista);
            this.btnEstado = v.findViewById(R.id.btnEstado);
        }
    }
}
