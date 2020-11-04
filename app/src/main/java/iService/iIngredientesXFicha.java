package iService;

import java.util.ArrayList;

import Models.IngredientesXFicha;

public interface iIngredientesXFicha {
    public ArrayList<IngredientesXFicha> getAllFromUser(String email);
}
