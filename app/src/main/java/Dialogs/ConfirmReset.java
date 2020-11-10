package Dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.aipa.R;
import com.example.aipa.ui.MainActivity;

import AsyncTasks.IngredientesSync;
import Gestion.FichasGestion;
import Gestion.UsuariosGestion;
import iGestion.iFichasGestion;
import iGestion.iUsuariosGestion;

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
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.delete);
        builder.setPositiveButton(R.string.delete_positive, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                for (Button btn:btns) {
                    btn.setVisibility(View.GONE);
                }
                progressBar.setVisibility(View.VISIBLE);
                iFichasGestion fg = new FichasGestion();
                fg.deleteAll();
                iUsuariosGestion ug = new UsuariosGestion();
                ug.delete();
                IngredientesSync ingredientesSync = new IngredientesSync();
                ingredientesSync.execute();
                Intent p = new Intent(getContext(), MainActivity.class);
                startActivity(p);
            }
        });
        builder.setNegativeButton(R.string.delete_negative, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        return builder.create();
    }
}
