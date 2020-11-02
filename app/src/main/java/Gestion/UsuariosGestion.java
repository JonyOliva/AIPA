package Gestion;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import Connection.DataDB;
import Models.Usuario;
import iGestion.iUsuariosGestion;

public class UsuariosGestion implements iUsuariosGestion {
    private SQLiteDatabase db;

    public UsuariosGestion() {
        db = DataDB.getSqldb().getWritableDatabase();
    }

    @Override
    public Boolean save(Usuario usuario) {
        ContentValues values = new ContentValues();
        values.put("email", usuario.getEmail());
        values.put("password", usuario.getPassword());
        values.put("apellido", usuario.getApellido());
        values.put("peso", usuario.getPeso());
        values.put("altura", usuario.getAltura());
        values.put("fase", usuario.getFase().getNroFase());
        long result;
        result = db.insert("Users",null, values);
        return (result != -1);
    }

    @Override
    public Usuario read() {
        return null;
    }
}
