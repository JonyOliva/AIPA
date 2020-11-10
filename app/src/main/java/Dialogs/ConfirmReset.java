package Dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.aipa.R;
import com.example.aipa.ui.MainActivity;

import AsyncTasks.IngredientesSync;
import Gestion.FichasGestion;
import Gestion.IngredientesXFichaGestion;
import Gestion.UsuariosGestion;
import Models.Usuario;
import Service.FichasService;
import Service.IngredientesService;
import Service.IngredientesXFichaService;
import iGestion.iFichasGestion;
import iGestion.iIngredientesXFichaGestion;
import iGestion.iUsuariosGestion;
import iService.iFichasService;
import iService.iIngredientesService;
import iService.iIngredientesXFichaService;

public class ConfirmReset extends DialogFragment {
    Button[] btns;
    ProgressBar progressBar;
    public ConfirmReset(View[] btn, View _progressBar) {
        btns = new Button[] {(Button) btn[0], (Button) btn[1], (Button) btn[2]};
        progressBar = (ProgressBar) _progressBar;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.reset_view, null);
        CheckBox check = (CheckBox) view.findViewById(R.id.checkBoxReset);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);
        builder.setMessage(R.string.reset);
        builder.setTitle(R.string.resetTitle);
        builder.setPositiveButton(R.string.reset_positive, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                for (Button btn:btns) {
                    btn.setVisibility(View.GONE);
                }
                progressBar.setVisibility(View.VISIBLE);
                iFichasGestion fg = new FichasGestion();
                fg.deleteAll();
                iUsuariosGestion ug = new UsuariosGestion();
                Usuario user = ug.read();
                ug.delete();
                iIngredientesXFichaGestion ixfg = new IngredientesXFichaGestion();
                ixfg.deleteAll();
                IngredientesSync ingredientesSync = new IngredientesSync();
                ingredientesSync.execute();
                if(check.isChecked()){
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            iIngredientesXFichaService ixfs = new IngredientesXFichaService();
                            ixfs.deleteAll(user.getEmail());
                            iFichasService fs = new FichasService();
                            fs.deleteAll(user.getEmail());
                            iIngredientesService is = new IngredientesService();
                            is.deleteAll(user.getEmail());
                        }
                    }).start();
                }
                Intent p = new Intent(getContext(), MainActivity.class);
                startActivity(p);
            }
        });
        builder.setNegativeButton(R.string.reset_negative, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        return builder.create();
    }
}
