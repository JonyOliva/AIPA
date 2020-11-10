package Gestion;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import Models.Fase;
import Models.Usuario;
import iGestion.iUsuariosGestion;

public class UsuariosGestion extends BaseGestion implements iUsuariosGestion {

    public UsuariosGestion(SQLiteDatabase db) {
        super(db);
    }

    public UsuariosGestion() {
        super();
    }

    @Override
    public Boolean save(Usuario usuario) {
        ContentValues values = new ContentValues();
        values.put("email", usuario.getEmail());
        values.put("password", usuario.getPassword());
        values.put("nombre", usuario.getNombre());
        values.put("apellido", usuario.getApellido());
        values.put("peso", usuario.getPeso());
        values.put("altura", usuario.getAltura());
        values.put("nrofase", usuario.getFase().getNroFase());
        long result;
        result = db.insert("Users",null, values);
        return (result != -1);
    }

    @Override
    public Boolean update(Usuario usuario) {
        ContentValues values = new ContentValues();
        values.put("nombre", usuario.getNombre());
        values.put("apellido", usuario.getApellido());
        values.put("peso", usuario.getPeso());
        values.put("altura", usuario.getAltura());
        values.put("nrofase", usuario.getFase().getNroFase());
        long result;
        result = db.update("Users", values,"", new String[0]);
        return (result != -1);
    }

    @Override
    public Usuario read() {
        String query = "select * from Users LIMIT 1;";
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.getCount() <= 0)
            return null;
        Usuario u = new Usuario();
        if(cursor.moveToFirst()){
            u.setEmail(cursor.getString(0));
            u.setPassword(cursor.getString(1));
            u.setNombre(cursor.getString(2));
            u.setApellido(cursor.getString(3));
            u.setPeso(cursor.getFloat(4));
            u.setAltura(cursor.getFloat(5));
            u.setFase(new Fase(cursor.getInt(6),""));
        }
        cursor.close();
        return u;
    }

    @Override
    public Boolean delete() {
        int res = db.delete("Users", null, null);
        return res > 0;
    }
}
