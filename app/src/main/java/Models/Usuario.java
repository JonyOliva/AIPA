package Models;

public class Usuario {
    private int IdUsuario;
    private String Nombre;
    private  String Apellido;
    private float Peso;
    private float Altura;
    private  Fase fase;

    public Usuario() {
    }

    public Usuario(int idUsuario, String nombre, String apellido, float peso, float altura, Fase fase) {
        IdUsuario = idUsuario;
        Nombre = nombre;
        Apellido = apellido;
        Peso = peso;
        Altura = altura;
        this.fase = fase;
    }

    public int getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        IdUsuario = idUsuario;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }

    public float getPeso() {
        return Peso;
    }

    public void setPeso(float peso) {
        Peso = peso;
    }

    public float getAltura() {
        return Altura;
    }

    public void setAltura(float altura) {
        Altura = altura;
    }

    public Fase getFase() {
        return fase;
    }

    public void setFase(Fase fase) {
        this.fase = fase;
    }
}
