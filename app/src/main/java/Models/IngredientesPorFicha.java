package Models;

import java.util.List;

public class IngredientesPorFicha {
    private FichaDiaria FichaDiaria;
    private List<Ingrediente> ListaIngredientes;

    public IngredientesPorFicha() {
    }

    public IngredientesPorFicha(Models.FichaDiaria fichaDiaria, List<Ingrediente> listaIngredientes) {
        FichaDiaria = fichaDiaria;
        ListaIngredientes = listaIngredientes;
    }

    public Models.FichaDiaria getFichaDiaria() {
        return FichaDiaria;
    }

    public void setFichaDiaria(Models.FichaDiaria fichaDiaria) {
        FichaDiaria = fichaDiaria;
    }

    public List<Ingrediente> getListaIngredientes() {
        return ListaIngredientes;
    }

    public void setListaIngredientes(List<Ingrediente> listaIngredientes) {
        ListaIngredientes = listaIngredientes;
    }
}
