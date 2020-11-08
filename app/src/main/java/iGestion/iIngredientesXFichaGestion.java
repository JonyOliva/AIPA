package iGestion;

import java.util.ArrayList;

import Models.IngredientesXFicha;

public interface iIngredientesXFichaGestion {
    public Boolean save(IngredientesXFicha ixf);
    public ArrayList<IngredientesXFicha> getAll();
    public Boolean deleteAll();
}
