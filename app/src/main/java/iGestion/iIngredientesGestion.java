package iGestion;

import java.util.ArrayList;

import Models.Ingrediente;

public interface iIngredientesGestion {
    public ArrayList<Ingrediente> getAll();
    public Boolean save(Ingrediente ingrediente);
}
