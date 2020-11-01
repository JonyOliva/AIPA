package Conexion;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLHelper extends SQLiteOpenHelper {

    public SQLHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase SqlDatabase) {
        SqlDatabase.execSQL("create table Sintomas(idsintoma integer primary key autoincrement, descripcion text, modpuntaje integer)");
        SqlDatabase.execSQL("create table Fases(nrofase integer primary key, descripcion text)");
        SqlDatabase.execSQL("create table Users (email text primary key, password text, nombre text, apellido text, peso real, altura real, nrofase integer, Constraint FKfaseu FOREIGN KEY (nrofase) REFERENCES Fases(nrofase))");
        SqlDatabase.execSQL("create table Ingredientes(idingrediente integer primary key autoincrement, nombre text, puntaje integer, fase integer, Constraint FKfasei FOREIGN KEY (fase) REFERENCES Fases(nrofase))");
        SqlDatabase.execSQL("create table FichasDiarias(idficha integer primary key autoincrement, fecha date, comentario text, tiempoejercicio real, idsintoma integer, Constraint FKsintomaf FOREIGN KEY (idsintoma) REFERENCES Sintomas(idsintoma))");
        SqlDatabase.execSQL("create table IngredientesXFicha(idingrediente integer, idficha integer, FOREIGN KEY(idingrediente) REFERENCES Ingredientes(idingrediente), FOREIGN KEY(idficha) REFERENCES FichasDiarias(idficha))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        /*db.execSQL("drop table if exists Sintomas");
        db.execSQL("drop table if exists Fases");
        db.execSQL("drop table if exists Users");
        db.execSQL("drop table if exists Ingredientes");
        db.execSQL("drop table if exists FichasDiarias");
        db.execSQL("drop table if exists IngredientesXFicha");
        onCreate(db);*/
    }
}
