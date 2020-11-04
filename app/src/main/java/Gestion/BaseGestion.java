package Gestion;

import android.database.sqlite.SQLiteDatabase;

import Connection.DataDB;

public class BaseGestion {
    protected SQLiteDatabase db;

    public BaseGestion() {
        db = DataDB.getSqldb().getWritableDatabase();
    }
}
