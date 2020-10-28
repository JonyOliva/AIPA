package Dao;

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
        SqlDatabase.execSQL("create table Users (email text unique, username text primary key, password text)");
        SqlDatabase.execSQL("create table Parkings (idParking integer primary key autoincrement , user text, time integer not null, licensePlate varchar(10) not null" +
                ", Constraint FKUsers FOREIGN KEY (user) REFERENCES Users(username))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
