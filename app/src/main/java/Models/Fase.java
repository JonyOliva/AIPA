package Models;

public class Fase {
    private int NroFase;
    private String Descripcion;

    public Fase(int nroFase, String descripcion) {
        NroFase = nroFase;
        Descripcion = descripcion;
    }

    public Fase() {

    }

    public int getNroFase() {
        return NroFase;
    }

    public void setNroFase(int nroFase) {
        NroFase = nroFase;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }
}
