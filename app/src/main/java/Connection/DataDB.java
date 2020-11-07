package Connection;

public class DataDB {

    private static SQLHelper LocalDb;
    public final static String host="remotemysql.com";
    public final static String port="3306";
    public final static String nameBD="LGTzezroDl";
    public final static String user="LGTzezroDl";
    public final static String pass="PHtjrDCurJ";

    public final static String urlMySQL = "jdbc:mysql://" + host + ":" + port + "/"+nameBD;
    public final static String driver = "com.mysql.jdbc.Driver";

    public static SQLHelper getSqldb() {
        return LocalDb;
    }
    public static void setSqldb(SQLHelper _db) {
        if(_db != null)
            LocalDb = _db;
    }

    @Override
    protected void finalize() throws Throwable {
        if(LocalDb != null){
            LocalDb.close();
        }
        super.finalize();
    }

}

