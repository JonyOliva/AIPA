package Service;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import Models.FichaDiaria;
import Models.Sintoma;
import Models.Usuario;
import iService.iFichasService;

public class FichasService extends BaseService implements iFichasService {
    @Override
    public ArrayList<FichaDiaria> getAllFromUser(String email) {
        openConn();
        if(con == null)
            return null;
        try{
            ArrayList<FichaDiaria> fichas = new ArrayList<FichaDiaria>();
            Statement st = con.createStatement();
            String query = "SELECT idficha, fecha, comentario, tiempoejercicio, idsintoma FROM FichasDiarias WHERE usuario = ':email'";
            query = query.replace(":email", email);
            ResultSet rs = st.executeQuery(query);
            while(rs.next()) {
                FichaDiaria ficha  = new FichaDiaria();
                ficha.setIdFicha(rs.getInt("idficha"));
                ficha.setFecha(rs.getString("fecha"));
                ficha.setComentario(rs.getString("comentario"));
                ficha.setTiempoEjercicio(rs.getInt("tiempoejercicio"));
                ficha.setSintoma(new Sintoma(rs.getInt("idsintoma"), "", 0));
                fichas.add(ficha);
            }
            con.close();
            return fichas;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Boolean deleteAll(String email) {
        openConn();
        if(con == null)
            return false;
        try{
            Statement st = con.createStatement();
            String query = "DELETE FROM FichasDiarias WHERE usuario =':mail';";
            query = query.replace(":mail", email);
            int result = st.executeUpdate(query);
            con.close();
            return (result != -1);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean save(FichaDiaria fd, String email) {
        openConn();
        if(con == null)
            return false;
        try{
            Statement st = con.createStatement();
            String query = "INSERT INTO `FichasDiarias` (`idficha`, `fecha`, `comentario`," +
                    " `tiempoejercicio`, `idsintoma`, `usuario`) VALUES (";
            String ins = ":id,':fecha',':com',:te,:sintoma,':user')";
            query += ins;
            query = query.replace(":id", Integer.toString(fd.getIdFicha()));
            query = query.replace(":user", email);
            query = query.replace(":fecha", fd.getFecha());
            query = query.replace(":com", fd.getComentario());
            query = query.replace(":te", Integer.toString(fd.getTiempoEjercicio()));
            query = query.replace(":sintoma", Integer.toString(fd.getSintoma().getIdSintoma()));
            int result = st.executeUpdate(query);
            con.close();
            return (result != -1);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean update(FichaDiaria fd) {
        openConn();
        if(con == null)
            return false;
        try{
            Statement st = con.createStatement();
            String query = "UPDATE FichasDiarias SET comentario = ':com', TiempoEjercicio = ':tej'," +
                    "Sintoma = :sin" +
                    "WHERE IdFicha = ':id'";
            query = query.replace(":com", fd.getComentario());
            query = query.replace(":tej", Integer.toString(fd.getTiempoEjercicio()));
            query = query.replace(":sin", Integer.toString(fd.getSintoma().getIdSintoma()));
            query = query.replace(":id", Integer.toString(fd.getIdFicha()));
            int result = st.executeUpdate(query);
            con.close();
            return (result != -1);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }


}
