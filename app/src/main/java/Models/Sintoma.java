package Models;

public class Sintoma {
    private int IdSintoma;
    private String Descripcion;
    private int ModificadorPuntaje;

    public Sintoma() {
    }

    public Sintoma(int idSintoma, String descripcion, int modificadorPuntaje) {
        IdSintoma = idSintoma;
        Descripcion = descripcion;
        ModificadorPuntaje = modificadorPuntaje;
    }

    public int getIdSintoma() {
        return IdSintoma;
    }

    public void setIdSintoma(int idSintoma) {
        IdSintoma = idSintoma;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public int getModificadorPuntaje() {
        return ModificadorPuntaje;
    }

    public void setModificadorPuntaje(int modificadorPuntaje) {
        ModificadorPuntaje = modificadorPuntaje;
    }

    @Override
    public String toString() {
        return Descripcion;
    }
}
