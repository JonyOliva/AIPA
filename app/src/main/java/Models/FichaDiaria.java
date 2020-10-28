package Models;

import java.util.Date;

public class FichaDiaria {
    private int IdFicha;
    private Date Fecha;
    private String Comentario;
    private int TiempoEjercicio;
    private Sintoma Sintoma;

    public FichaDiaria() {
    }

    public FichaDiaria(int idFicha, Date fecha, String comentario, int tiempoEjercicio, Models.Sintoma sintoma) {
        IdFicha = idFicha;
        Fecha = fecha;
        Comentario = comentario;
        TiempoEjercicio = tiempoEjercicio;
        Sintoma = sintoma;
    }

    public int getIdFicha() {
        return IdFicha;
    }

    public void setIdFicha(int idFicha) {
        IdFicha = idFicha;
    }

    public Date getFecha() {
        return Fecha;
    }

    public void setFecha(Date fecha) {
        Fecha = fecha;
    }

    public String getComentario() {
        return Comentario;
    }

    public void setComentario(String comentario) {
        Comentario = comentario;
    }

    public int getTiempoEjercicio() {
        return TiempoEjercicio;
    }

    public void setTiempoEjercicio(int tiempoEjercicio) {
        TiempoEjercicio = tiempoEjercicio;
    }

    public Models.Sintoma getSintoma() {
        return Sintoma;
    }

    public void setSintoma(Models.Sintoma sintoma) {
        Sintoma = sintoma;
    }
}
