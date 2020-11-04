package iService;

import java.util.ArrayList;

import Models.Ingrediente;

public interface iIngredientesService {
    public ArrayList<Ingrediente> getAll();
    public ArrayList<Ingrediente> getAllForUser(String email);
}
