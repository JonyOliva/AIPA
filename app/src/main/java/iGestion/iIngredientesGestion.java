package iGestion;

import java.util.ArrayList;

import Models.Ingrediente;

public interface iIngredientesGestion {
    public ArrayList<Ingrediente> getAll();
    public Boolean save(Ingrediente ingrediente);
    public ArrayList<Ingrediente> getIngredientesXFase(Integer fase);
    public ArrayList<Ingrediente> getIngredientesXFicha(Integer idficha);
    public Boolean deleteAll();
    public Boolean existByName(String name);
    public Boolean update(Ingrediente ingrediente);
}
