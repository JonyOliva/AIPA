package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aipa.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import Models.Ingrediente;

public class IngredienteListaAdapter extends ArrayAdapter<Ingrediente>{
    private List<Ingrediente> selecIngredientes;
    private Button btn;
    private Context context;

    public IngredienteListaAdapter(Context cxt, Button btn,List<Ingrediente> selecIngredientes){
        super(cxt, 0, selecIngredientes);
        this.selecIngredientes = selecIngredientes;
        this.context = cxt;
        this.btn = btn;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final Ingrediente ing = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.ingrediente_lista_layout, parent, false);
        }

        TextView nombre = (TextView) convertView.findViewById(R.id.txtViewNombre);
        ImageButton borrar = (ImageButton) convertView.findViewById(R.id.btnBorrar);

        nombre.setText(ing.getNombre().toString());

        borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selecIngredientes.get(position) != null) {
                    selecIngredientes.remove(position);
                    if (selecIngredientes.size() == 0)
                        btn.setVisibility(View.INVISIBLE);
                }
                notifyDataSetChanged();
            }
        });

        return convertView;
    }
}
