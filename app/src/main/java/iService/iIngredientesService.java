package iService;

import java.util.ArrayList;

import Models.Ingrediente;

public interface iIngredientesService {
    public ArrayList<Ingrediente> getAllDefault();
    public ArrayList<Ingrediente> getAllForUser(String email);
}
