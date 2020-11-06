package Dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import Gestion.FichasGestion;
import Gestion.IngredientesGestion;
import Service.IngredientesSync;
import iGestion.iFichasGestion;
import iGestion.iIngredientesGestion;

import com.example.aipa.R;

public class ConfirmReset extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.delete);
        builder.setPositiveButton(R.string.delete_positive, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                iFichasGestion fg = new FichasGestion();
                fg.deleteAll();
                iIngredientesGestion ig = new IngredientesGestion();
                ig.deleteAll();
                IngredientesSync ingredientesSync = new IngredientesSync();
                ingredientesSync.execute();
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
