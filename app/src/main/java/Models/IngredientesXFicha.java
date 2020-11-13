package Models;

public class IngredientesXFicha {
    private int idingrediente;
    private int idficha;

    public IngredientesXFicha() {
    }

    public IngredientesXFicha(int idficha, int idingrediente) {
        this.idficha =idficha;
        this.idingrediente = idingrediente;
    }

    public int getIdingrediente() {
        return idingrediente;
    }

    public void setIdingrediente(int idingrediente) {
        this.idingrediente = idingrediente;
    }

    public int getIdficha() {
        return idficha;
    }

    public void setIdficha(int idficha) {
        this.idficha = idficha;
    }
}
