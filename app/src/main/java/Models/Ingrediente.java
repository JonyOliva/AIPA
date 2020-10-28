package Models;

public class Ingrediente {
    private int IdIngrediente;
    private String Nombre;
    private int Puntaje;
    private Fase fase;

    public Ingrediente() {
    }

    public Ingrediente(int idIngrediente, String nombre, int puntaje, Fase fase) {
        IdIngrediente = idIngrediente;
        Nombre = nombre;
        Puntaje = puntaje;
        this.fase = fase;
    }

    public int getIdIngrediente() {
        return IdIngrediente;
    }

    public void setIdIngrediente(int idIngrediente) {
        IdIngrediente = idIngrediente;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public int getPuntaje() {
        return Puntaje;
    }

    public void setPuntaje(int puntaje) {
        Puntaje = puntaje;
    }

    public Fase getFase() {
        return fase;
    }

    public void setFase(Fase fase) {
        this.fase = fase;
    }
}
