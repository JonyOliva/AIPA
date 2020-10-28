package Dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataDB {
    //Información de la BD
    public final static String host="remotemysql.com";
    public static String port="3306";
    public static String nameBD="i0ECZ0lSa9";
    public static String user="i0ECZ0lSa9";
    public static String pass="6BHwSFwuuV";

    //Información para la conexion
    public static String urlMySQL = "jdbc:mysql://" + host + ":" + port + "/"+nameBD;
    public static String driver = "com.mysql.jdbc.Driver";

}

