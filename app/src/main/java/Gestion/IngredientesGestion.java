package Gestion;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import Models.Fase;
import Models.Ingrediente;
import iGestion.iIngredientesGestion;

public class IngredientesGestion extends BaseGestion implements iIngredientesGestion {
    public IngredientesGestion(SQLiteDatabase db) {
        super(db);
    }

    public IngredientesGestion() {
        super();
    }

    @Override
    public ArrayList<Ingrediente> getAll() {
        ArrayList<Ingrediente> ingredientes = new ArrayList<Ingrediente>();
        String query = "Select idingrediente, nombre, puntaje, fase from Ingredientes";
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.getCount() <= 0)
            return null;
        if(cursor.moveToFirst()){
            do {
                Ingrediente ing = new Ingrediente();
                ing.setIdIngrediente(cursor.getInt(0));
                ing.setNombre(cursor.getString(1));
                ing.setPuntaje(cursor.getInt(2));
                ing.setFase(new Fase(cursor.getInt(3), ""));
                ingredientes.add(ing);
            }while(cursor.moveToNext());
        }
        cursor.close();
        return ingredientes;
    }

    @Override
    public Boolean save(Ingrediente ingrediente) {
        ContentValues values = new ContentValues();
        values.put("idingrediente", ingrediente.getIdIngrediente());
        values.put("nombre", ingrediente.getNombre());
        values.put("puntaje", ingrediente.getPuntaje());
        values.put("fase", ingrediente.getFase().getNroFase());
        long result;
        result = db.insert("Ingredientes",null, values);
        return (result != -1);
    }

    @Override
    public ArrayList<Ingrediente> getIngredientesXFase(Integer fase) {
        ArrayList<Ingrediente> ingredientes = new ArrayList<Ingrediente>();
        String query = "Select idingrediente, nombre, puntaje, fase from Ingredientes WHERE fase ="+fase;
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.getCount() <= 0)
            return null;
        if(cursor.moveToFirst()){
            do {
                Ingrediente ing = new Ingrediente();
                ing.setIdIngrediente(cursor.getInt(0));
                ing.setNombre(cursor.getString(1));
                ing.setPuntaje(cursor.getInt(2));
                ing.setFase(new Fase(cursor.getInt(3), ""));
                ingredientes.add(ing);
            }while(cursor.moveToNext());
        }
        cursor.close();
        return ingredientes;
    }

    @Override
    public Boolean deleteAll() {
        int res = db.delete("Ingredientes", null, null);
        return res > 0;
    }

}
