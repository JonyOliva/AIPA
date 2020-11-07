package Service;

import java.sql.Connection;
import java.sql.DriverManager;

import Connection.DataDB;

public class BaseService {

    protected Connection con;

    protected void openConn(){
        try{
            Class.forName(DataDB.driver);
            con = DriverManager.getConnection(DataDB.urlMySQL, DataDB.user, DataDB.pass);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
